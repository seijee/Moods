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
        <%@include file="_components/navbar.jsp" %><br/>
        
        <div id="movieTiles" class="tile-area" style="width: 80%;margin:0 10%" >
            <% 
                String mood="";
                try{ mood =request.getParameter("mood");}
                catch (Exception e){mood="";}
            %>
            <form id="state" name="state">
                <input type="text" name="mood" value="<%=mood%>" readonly="true"/>
                <input type="text" name="pageNo" value="-1"  readonly="true" />
                <input type="text" name="limit" value="15" readonly="true" />
            </form>
        </div>
        
        <a id="nav-left" class="p-navigation left" onclick="loadPrevPage()">
        <i class="fa fa-chevron-left fa-5x"></i>
        </a>
        <a id="nav-right" class="p-navigation right" onclick="loadNextPage()">
        <i class="fa fa-chevron-right fa-5x"></i>
        </a>
        
        <div id="backdrop" onclick="hideModal()">    
            <div class="modal" onclick="event.stopPropagation();" id='myModal'>
                <div class='bgImage'></div>
                <p id="movieDescription">Loading...</p>
                <i onclick='hideModal()' class="close fa fa-times fa-2x"></i>
            </div>
        </div>
        <script src="./js/jquery-1.11.1.js"></script>
        <script src="./js/Modal.js"></script>
        <script>
        $(document).ready(function(){
            loadNextPage();
        });
        </script>
    </body>
</html>