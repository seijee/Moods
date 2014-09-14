<%-- 
    Document   : index
    Created on : Aug 19, 2014, 3:09:11 PM
    Author     : Charchit Gupta
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
        <link rel="stylesheet" href="./css/homeCss.css" />
        <link rel="stylesheet" href="./css/navbar.css" />
        <link rel="stylesheet" href="./css/font-awesome.min.css" />
        <title>Home</title>
    </head>
    <body>
        <%@include file="_components/navbar.jsp" %>
        <div id="accordion">
            <div class="tile-area">
                <br/><br/>
                <h1>Welcome to Movie on Moods</h1>
                <p id="description">The Ultimate Movie Suggestion Solution</p>
            </div>
        </div>
        <div id="hexGridWrapper">
        <%@include file="_components/hexGrid.jsp" %>
        </div>
        <div id="backdrop" onclick="hideModal()">
            <div class="modal" onclick="event.stopPropagation();" id='myModal'>
                <div class='bgImage'></div>
                <p id="movieDescription">Loading...</p>
                <i onclick='hideModal()' class="close fa fa-times fa-1x"></i>
            </div>
        </div>
        
        <script src="./js/jquery-1.11.1.js"></script>
        <script src="./js/Modal.js"></script>
        <script>
//initial fade effect of tiles
$(window).load(function(){
    $i=0;
    $(".hex").each(function (e){
        $i++;
        $(this).mouseover(
        function (){
            $("#description").text($(this).find('.description').text());
        });
    });
});
        </script>
    </body>
</html>
