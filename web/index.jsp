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
            <svg id="hexGrid" xmlns="http://www.w3.org/2000/svg"  version="1.1" viewBox="0 0 1100 410" preserveAspectRatio="xMidYMid meet" xmlns:xlink="http://www.w3.org/1999/xlink">
                <defs id="svgDefs"></defs>
            </svg>
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
        <%@include file="_components/hexGrid.jsp" %>
        <script>
            $(window).load(function(){
            var descArray = [
                "Description#1 : Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla justo leo, malesuada et augue et, malesuada auctor augue. Donec sit amet commodo lorem. Nam convallis nec felis elementum elementum. Cras quis feugiat ligula.",
                "Description#2 : Etiam quis ex nec massa rhoncus commodo ac nec lectus. Praesent est nunc, gravida luctus lobortis vel, aliquet non massa. Aenean sollicitudin bibendum justo,",
                "Description#3 :  Donec tincidunt scelerisque libero id lacinia. Phasellus augue massa, facilisis sit amet diam nec, commodo auctor erat. Nam erat odio, ultrices sit amet odio at, pretium eleifend ante",
                "Description#4 : Aenean euismod, nisi non dapibus interdum, mi lacus vehicula justo, iaculis semper est eros ut urna. Nam ut ullamcorper libero. Pellentesque velit dui, finibus ut finibus eu, gravida nec odio.",
                "Description#5 : Praesent lobortis pellentesque elit. Ut at efficitur massa. Fusce semper et tortor a rhoncus. Curabitur varius, quam quis condimentum tristique, dui felis faucibus magna, luctus commodo sapien velit vitae mauris. Cras ipsum tellus, dictum et porttitor dignissim, molestie",
                "Description#6 : Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla justo leo, malesuada et augue et, malesuada auctor augue. Donec sit amet commodo lorem. Nam convallis nec felis elementum elementum. Cras quis feugiat ligula.",
                "Description#7 : Etiam quis ex nec massa rhoncus commodo ac nec lectus. Praesent est nunc, gravida luctus lobortis vel, aliquet non massa. Aenean sollicitudin bibendum justo,",
                "Description#8 :  Donec tincidunt scelerisque libero id lacinia. Phasellus augue massa, facilisis sit amet diam nec, commodo auctor erat. Nam erat odio, ultrices sit amet odio at, pretium eleifend ante",
                "Description#9 : Aenean euismod, nisi non dapibus interdum, mi lacus vehicula justo, iaculis semper est eros ut urna. Nam ut ullamcorper libero. Pellentesque velit dui, finibus ut finibus eu, gravida nec odio.",
                "Description#10 : Praesent lobortis pellentesque elit. Ut at efficitur massa. Fusce semper et tortor a rhoncus. Curabitur varius, quam quis condimentum tristique, dui felis faucibus magna, luctus commodo sapien velit vitae mauris. Cras ipsum tellus, dictum et porttitor dignissim, molestie"
            ];
            var mySVG = document.getElementById("hexGrid");
            width= 200;padding = 5;
            h = width/2; v=(width/0.866025);
            row=0.5;count=0;
            for ($i=0; $i<10; $i++){
                mySVG.appendChild(createLink("title",descArray[$i],"./img/hex.jpg","content.jsp","id"+count, h  , row*v,width-padding));
                h+=width;count++;
                if ($i==4){ row+=0.75;h=width;}
            }
            $i=0;
            $(".hex").each(function (e){
                $i++;
                $(this).mouseover(
                function (){
                    $("#description").hide();
                    $("#description").text($(this).find('.description').text());
                    $("#description").fadeIn("fast","linear");
                });
            });
        });
        </script>
    </body>
</html>
