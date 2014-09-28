<%-- 
    Document   : home
    Created on : Aug 27, 2014, 7:17:54 PM
    Author     : seijee
--%>
<%@page import="java.util.List"%>
<%@page import="controllers.ImdbDBController"%>
<%@page import="factory.Conn"%>
<%@page import="objects.ImdbData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <link rel="stylesheet" href="./css/cg-metro.css" />
        <link rel="stylesheet" href="./css/navbar.css" />
        <link rel="stylesheet" href="./css/font-awesome.min.css" />
        <title>Content</title>
    </head>
    <body>
        <%@include file="_components/navbar.jsp" %>
        <div id="backdrop" onclick="hideModal()">
            <div class="modal" onclick="event.stopPropagation();" id='myModal'>
                <div class='bgImage'></div>
                <p id="movieDescription">Loading...</p>
                <i onclick='hideModal()' class="close fa fa-times fa-1x"></i>
            </div>
        </div>
        
        <br/>
        <%
                int pageNo = 0, limit = 15;
                String orderBy = "RATING", mood = "test";
                
                //initialize default settings
                try{pageNo = Integer.parseInt(request.getParameter("pageNo"));}
                catch(Exception e) {pageNo = 0;}
                try {limit = Integer.parseInt(request.getParameter("limit"));}
                catch(Exception e){limit = 15;}
                try {orderBy = request.getParameter("orderBy");}
                catch(Exception e){orderBy = "RATING";}
                
                try {mood = request.getParameter("mood");}
                catch(Exception e){mood = "test";}
                
                if (pageNo < 0) pageNo=0;
                if (limit<10) limit = 10;

        %>
        <div class="tile-area" style="width: 80%;margin:0 10%" >
        <%
            List<ImdbData> list = ImdbDBController.getCache(mood);
            for (int i = pageNo*limit; i<pageNo*limit+limit && i<list.size(); i++){
                ImdbData movie = list.get(i);
        %>
        <a onclick='showDetails(this,"<%=movie.getId() %>")' style="display:none" class="tile">
            <div class="tile-content" style="background-image: url('<%= movie.getImage() %>'); ">
                
                <div class="title">
                    (<%= movie.getRating() %>)<b> <%= movie.getTitle()%></b>
                </div>
            </div>
        </a>
        <%  } %> 
        </div>
        <a class="p-navigation left" href="?limit=<%=limit%>&pageNo=<%=pageNo-1%>&orderBy=<%=orderBy%>&mood=<%=mood%>">
            <i class="fa fa-chevron-left fa-5x"></i>
        </a>
        <a class="p-navigation right" href="?limit=<%=limit%>&pageNo=<%=pageNo+1%>&orderBy=<%=orderBy%>&mood=<%=mood%>">
            <i class="fa fa-chevron-right fa-5x"></i>
        </a>
        <p id="res"></p>
        </div>
        <script src="./js/jquery-1.11.1.js"></script>
        <script src="./js/Modal.js"></script>
        <script>
//initial fade effect of tiles
$(document).ready(function(){
    $i=0;
    $(".tile").each(function (e){
        $(this).delay($i*35).fadeIn(800);
        $i++;
    });
});
        </script>
    </body>
</html>