<%-- 
    Document   : MovieAJAX
    Created on : Aug 24, 2014, 1:44:13 PM
    Author     : seijee
--%>

<%@page import="objects.MovieDetails"%>
<%@page import="controllers.MovieProduct"%>
<%@page import="java.util.List"%>
<%@page import="objects.ImdbData"%>
<%@page import="controllers.ImdbDBController"%>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    List<ImdbData> list = controllers.ImdbDBController.getCache();
    ImdbData myMovie = new ImdbData(id);

    if (list.contains(myMovie)){
        myMovie = list.get(list.indexOf(myMovie));
    }
    
    MovieDetails md = controllers.MovieDetailsController.GetMovieDetails(myMovie);
    
    response.getWriter().print(md);
    /*List<MovieProduct> mdList = paapi.PaapiCall.itemSearch(myMovie.getTitle(), "", "");
    response.getWriter().print(myMovie);
    response.getWriter().print("<span style='clear:both;'></span><hr/>");
    response.getWriter().print(mdList.size());
    for (MovieProduct md : mdList){
        response.getWriter().print("<div width='100%'>");
        response.getWriter().print(md);
        response.getWriter().print("<hr/></div>");
    }*/
%>
