package paapi;

import controllers.ImdbDBController;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.ImdbData;

public class ImageGrabber {

    public static void grab(String[] args) throws IOException {
        /****************POROXY SETTINGS***********************
        String host = "172.16.0.87";
        String port = "8080";
        System.out.println("Using proxy: " + host + ":" + port);
        System.setProperty("http.proxyHost", host);
        System.setProperty("http.proxyPort", port);
        System.setProperty("http.proxySet", "true");
        System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1");
        /**
         * ****************END OF PROXY SETTINGS***************************
         */
        List<ImdbData> rest = ImdbDBController.getCache();
        //FileWriter csv = new PrintWriter(new BufferedWriter(new FileWriter("./web/movieImages/csv.txt", true)));
        FileWriter csv = new FileWriter("./web/movieImages/csv.txt",true);
        int count = 0;
        String sb = "";
        for (ImdbData data : rest) {
            count++;
            String fn = ""+ data.getId();
            fn = fn.trim();
            fn = fn.replace(' ', '-');
            fn = fn.replace(':', '-');
            fn = fn.replace(';', '-');
            fn = fn.replace('Â', '-');
            fn = fn.replace(',', '-');

            try {
                if (count % 10 == 0) {
                    csv.flush();
                    Thread.sleep(1);
                    return ;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ImageGrabber.class.getName()).log(Level.SEVERE, null, ex);
            }
            String fileName = "./web/movieImages/" + fn + ".jpg"; //The file that will be saved on your computer
            URL link = null;
            try {
                link = new URL(data.getImage());

                System.out.println(fileName);
                //Code to download
                InputStream in = new BufferedInputStream(link.openStream());
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int n = 0;
                while (-1 != (n = in.read(buf))) {
                    output.write(buf, 0, n);
                }
                output.close();
                in.close();
                byte[] UrlResponse = output.toByteArray();

                FileOutputStream fos = new FileOutputStream(fileName);
                fos.write(UrlResponse);
                fos.close();
                sb = "Update into `imdb_data` SET `IMAGE`='"+fn+".jpg' WHERE `ID`='"+data.getId()+"';\n";
                System.out.println("Dowloaded.. "+data.getId()+":\t"+data.getTitle());
                csv.write(sb);
            } catch (Exception e) {
                count--;
                System.out.println(data.getTitle() +"  Id:"+ data.getId() + " could not be downloaded...");
                //sb = data.getId() + ", NA.jpg\n";
                csv.write("--"+data.getId().toString() + ", NA.jpg\n");
                csv.flush();
                continue;
            }
            //End download code
        }
        csv.flush();
        System.out.println("Finished");
    } 
}
