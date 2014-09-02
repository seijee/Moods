package paapi;

import controllers.ImdbDBController;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.ImdbData;

public class ImageGrabber {

    public static void main(String[] args) throws IOException {
        //****************POROXY SETTINGS***********************/
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
            String fn = data.getTitle() + "_" + data.getId();
            fn = fn.trim();
            fn = fn.replace(' ', '-');
            fn = fn.replace(':', '-');
            fn = fn.replace(';', '-');
            fn = fn.replace('Â', '-');
            fn = fn.replace(',', '-');

            try {
                if (count % 100 == 0) {
                    Thread.sleep(500);
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
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int n = 0;
                while (-1 != (n = in.read(buf))) {
                    out.write(buf, 0, n);
                }
                out.close();
                in.close();
                byte[] response = out.toByteArray();

                FileOutputStream fos = new FileOutputStream(fileName);
                fos.write(response);
                fos.close();
                sb += data.getId() + "," + fn + ".jpg";
                csv.write(data.getId() + "," + fn + ".jpg\n");
            } catch (Exception e) {
                System.out.println(data.getTitle() + data.getId() + " could not do...");
                sb += data.getId() + ", NA.jpg\n";
                csv.write(data.getId().toString() + ", NA.jpg\n");
                csv.flush();
                continue;
            }
            
            //End download code
            //System.out.println("Finished");
        }
    } 
}
