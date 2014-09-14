<%-- 
    Document   : MovieAJAX
    Created on : Aug 24, 2014, 1:44:13 PM
    Author     : seijee
--%>

<%@page import="java.util.List"%>
<%@page import="objects.ImdbData"%>
<%@page import="controllers.ImdbDBController"%>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    List<ImdbData> list = ImdbDBController.getCache();
    Thread.sleep(1000); //fake network latency
    ImdbData myMovie = new ImdbData(id);
    
    if (list.contains(myMovie)){
        int i = list.indexOf(myMovie);
        myMovie = list.get(i);
        response.getWriter().print(myMovie.getTitle());
    }
%>
