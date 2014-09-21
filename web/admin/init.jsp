<%-- 
    Document   : init
    Created on : Aug 24, 2014, 1:43:29 AM
    Author     : seijee
--%>
<%@page import="paapi.ImageGrabber"%>
<%@page import="java.util.List"%>
<%@page import="controllers.ImdbDBController"%>
<%@page import="factory.Conn"%>
<%@page import="objects.ImdbData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/cg-metro.css" />
        <link rel="stylesheet" href="../css/navbar.css" />
        <link rel="stylesheet" href="../css/font-awesome.min.css" />
        <title>Home</title>
    </head>
    <body>
        <div class="navbar" >
            <ul>
                <li id="logo"><a href="../"> Moods </a></li>
                <li><a href="#">Option One</a></li>
                <li><a onclick="hideModal()">Option One</a></li>
            </ul>
        </div>
        <div id="backdrop" onclick="hideModal()">
            <div class="modal" onclick="event.stopPropagation();" id='myModal'>
            <div class='bgImage'></div>
            Lorem ipsum 
            <i onclick='hideModal()' class="close fa fa-times fa-1x"></i>
            </div>
        </div>
        <%
                int pageNo = 0, limit = 15;
                String orderBy = "RATING";
                try{pageNo = Integer.parseInt(request.getParameter("pageNo"));}
                catch(Exception e) {pageNo = 0;}
        %>
        <br/><%=request.getParameter("pageNo")%><br/>
        
        <div class="tile-area" style="width: 80%;margin:0 10% " >
        <%
            ImdbDBController.UpdateMovieCache();
            List<ImdbData> rest = ImdbDBController.getCache();
            rest.clear();
        %>
        Done!
        </div>
        
        <a class="p-navigation left" href="?limit=<%=limit%>&pageNo=<%=pageNo-1%>&orderBy=<%=orderBy%>">
            <i class="fa fa-chevron-left fa-5x"></i>
        </a>
        <a class="p-navigation right" href="?limit=<%=limit%>&pageNo=<%=pageNo+1%>&orderBy=<%=orderBy%>">
            <i class="fa fa-chevron-right fa-5x"></i>
        </a>
        <p id="res"></p>
        <script src="../js/jquery-1.11.1.js"></script>
        <script src="../js/Modal.js"></script>
        <script>
//initial fade effect of tiles
$(window).load(function(){
    $i=0;
    $(".tile").each(function (e){
        $(this).delay($i*50).fadeIn(600);
        $i++;
    });
});
        </script>
    </body>
</html>
