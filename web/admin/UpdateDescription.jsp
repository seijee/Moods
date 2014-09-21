<%-- 
    Document   : UpdateDescription
    Created on : Sep 14, 2014, 8:59:14 PM
    Author     : seijee
--%>

<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="paapi.ImageGrabber"%>
<%@page import="controllers.ImdbDBController"%>
<%@page import="java.net.URL"%>
<%@page import="org.apache.log4j.*"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="objects.MovieProduct"%>
<%@page import="java.util.List"%>
<%@page import="objects.ImdbData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="#">
            <input name="id"  placeholder="Id"/>
            <input name="search" type="submit" value="Submit" />
        </form>
        <div>
        <%
            /*if (request.getParameter("search")!=null){
                int id = Integer.parseInt(request.getParameter("id"));
                List<ImdbData> list = controllers.ImdbDBController.getCache();
                ImdbData myMovie = new ImdbData(id);
                
                if (list.contains(myMovie)){
                    myMovie = list.get(list.indexOf(myMovie));
                }
                List<MovieProduct> mdList = paapi.PaapiCall.itemSearch(myMovie.getTitle(), "", "");
                response.getWriter().print(myMovie);
                response.getWriter().print("<hr/>");
                for (MovieProduct md : mdList){
                    response.getWriter().print(md);
                    response.getWriter().print("<hr/>");
                }
            }*/
            paapi.ImageGrabber im = new ImageGrabber();
            
        %>
        </div>
    </body>
</html>
