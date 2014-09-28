/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paapi;

import controllers.ImdbDBController;
import java.io.BufferedInputStream;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objects.ImdbData;

/**
 *
 * @author seijee
 */
public class NewImageGrabber extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        String path =  getServletContext().getRealPath("/");
        out.print(path);
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
        FileWriter csv = new FileWriter(path+"/movieImages/csv.txt",true);
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
                if (count % 2 == 0) {
                    csv.flush();
                    Thread.sleep(10);
                    return ;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ImageGrabber.class.getName()).log(Level.SEVERE, null, ex);
            }
            String fileName = path+"/movieImages/" + fn + ".jpg"; //The file that will be saved on your computer
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
                sb = "Update into `imdb_data` SET `IMAGE`='"+fn+".jpg' WHERE `ID`='"+data.getId()+"';";
                out.println(sb+"<br/>");
                csv.write(sb);
            } catch (Exception e) {
                count--;
                out.println("--" + data.getTitle() +"  Id:"+ data.getId() + " could not be downloaded...");
                //sb = data.getId() + ", NA.jpg\n";
                csv.write("--"+data.getId().toString() + ", NA.jpg\n");
                csv.flush();
                continue;
            }
            //End download code
        }
        csv.flush();
        out.println("Finished");
    }  finally {
            out.print("chala" + getServletContext().getRealPath("/"));
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
