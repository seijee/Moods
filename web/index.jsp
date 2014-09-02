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
        <div id="backdrop" onclick="hideModal()">
            <div class="modal" onclick="event.stopPropagation();" id='myModal'>
                <div class='bgImage'></div>
                <p id="movieDescription">Loading...</p>
                <i onclick='hideModal()' class="close fa fa-times fa-1x"></i>
            </div>
        </div>
        <%
        String[] urls = {
        "http://www.quotewallpapers.net/media/bgs_320x180/1/84-792503859.jpg",
        "http://www.quotewallpapers.net/media/bgs_320x180/1/84-792503859.jpg",
        "http://www.quotewallpapers.net/media/bgs_320x180/1/84-792503859.jpg",
        "http://www.quotewallpapers.net/media/bgs_320x180/1/84-792503859.jpg",
        "http://www.quotewallpapers.net/media/bgs_320x180/1/84-792503859.jpg",
        "http://www.quotewallpapers.net/media/bgs_320x180/1/84-792503859.jpg",
        "http://www.quotewallpapers.net/media/bgs_320x180/1/84-792503859.jpg",
        "http://www.quotewallpapers.net/media/bgs_320x180/1/84-792503859.jpg",
        "http://www.quotewallpapers.net/media/bgs_320x180/1/84-792503859.jpg",
        "http://www.quotewallpapers.net/media/bgs_320x180/1/84-792503859.jpg",
        "http://www.quotewallpapers.net/media/bgs_320x180/1/84-792503859.jpg"
        };%>
        <div class="tile-area">
        <% for(int i=0; i<9; i++){ %>
        <a href="content.jsp" style="display:none" class="tile">
            <div class="tile-content" style="background-image: url('<%=urls[i]%>'); ">
                <div class="title">
                    <b> Mood Title</b><br/>
                    <p class="moodDescription">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a blandit quam, eget consectetur lectus. Nam sed rutrum dolor, eget hendrerit nunc. Vivamus sagittis, nisi at malesuada porttitor, diam nibh mattis sapien, id pellentesque quam dolor vestibulum ante, vel fringilla puru</p>
                </div>
            </div>
        </a>
        <% } %>
        </div>
        </div>
        <script src="./js/jquery-1.11.1.js"></script>
        <script src="./js/Modal.js"></script>
        <script>
//initial fade effect of tiles
$(window).load(function(){
    $i=0;
    $(".tile").each(function (e){
        $(this).delay($i*25).fadeIn(800);
        $i++;
    });
});
        </script>
    </body>
</html>
