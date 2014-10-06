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
    String task = request.getParameter("task");
    if (task.equalsIgnoreCase("getMovieDetails")){
        int id = Integer.parseInt(request.getParameter("id"));
        String mood = request.getParameter("mood");
        List<ImdbData> list = controllers.ImdbDBController.getCache(mood);
        ImdbData myMovie = new ImdbData(id);

        if (list.contains(myMovie)){
            myMovie = list.get(list.indexOf(myMovie));
            MovieDetails md = controllers.MovieDetailsController.GetMovieDetails(myMovie);
            response.getWriter().print(md);
        }
    }
    if (task.equalsIgnoreCase("getMovieList")){
        int pageNo = 0, limit = 15;
        String orderBy = "", mood;

        try{pageNo = Integer.parseInt(request.getParameter("pageNo"));}
        catch(Exception e) {pageNo = 0;}
        try {limit = Integer.parseInt(request.getParameter("limit"));}
        catch(Exception e){limit = 15;}
        try {orderBy = request.getParameter("orderBy");}
        catch(Exception e){orderBy = "";}
        try {mood = request.getParameter("mood");}
        catch(Exception e){mood = "";}
        if (mood=="" || mood==null){
            mood = "test";
        }
        if (orderBy == null || orderBy==""){
            orderBy = "RATING";
        }
        if (pageNo < 0) pageNo=0;
        if (limit<10) limit = 10;
        List<ImdbData> list = ImdbDBController.getCache(mood); %>
<%      for (int i = pageNo*limit; i<pageNo*limit+limit && i<list.size(); i++){
            ImdbData movie = list.get(i); %>
            <a onclick='showDetails(this,"<%=movie.getId() %>","<%=mood%>")' style="display:none" class="tile">
                <div class="tile-content" style="background-image: url('<%= movie.getImage() %>'); ">
                    <div class="title">
                        (<%= movie.getRating() %>)<b> <%= movie.getTitle()%></b>
                    </div>
                </div>
            </a>
        <%  } %>
        <form id="state" name="state">
            <input type="text" name="mood" value="<%=mood%>" readonly="true"/>
            <input type="text" name="pageNo" value="<%=pageNo%>" readonly="true"/>
            <input type="text" name="limit" value="<%=limit%>" readonly="true"/>
        </form>
<%  }

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
