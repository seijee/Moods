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
    Thread.sleep(1500); //fake network latency
    for (ImdbData movie : list){
        if (movie.getId() == id){
            response.getWriter().print(movie.getTitle());
            break;
        }
    }
%>
