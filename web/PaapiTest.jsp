<%-- 
    Document   : PaapiTest
    Created on : Aug 26, 2014, 7:06:50 PM
    Author     : seijee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            if (request.getParameter("keyWords")!=null){
            String title = request.getParameter("title");
            String actor = request.getParameter("actor");
            String keyWords = request.getParameter("keyWords");
            response.getWriter().print(paapi.PaapiCall.itemSearch(title, actor, keyWords));
            }
        %>
        <form action="#">
            <input name="title"  placeholder="title"/>
            <input name="actor" placeholder="actors" />
            <input name="keyWords" placeholder="key words" />
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>
