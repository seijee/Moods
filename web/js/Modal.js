function displayModal($modal){
    $("body").css("overflow-y","hidden");
    $("#backdrop").fadeIn(400);
    $modal.fadeIn();
    $("#backdrop").focus();
}
function hideModal (){
    $("#backdrop").fadeOut(400);
    $(document).unbind('keydown');
    $("body").css("overflow-y","scroll");
    $(".modal").each(function (){       //should be optimised later, no need to close each modal
        $(this).hide(0);                //but given only 2-3 modals per page (max), its no big deal
    });
}

function animateTiles(){
    $i=0;
    $(".tile").each(function (e){
        $(this).delay($i*35).fadeIn(800);
        $i++;
    });
}

function loadNextPage($showDetails){
    $mood = document.forms["state"]["mood"].value;
    $pageNo = document.forms["state"]["pageNo"].value;
    $limit = document.forms["state"]["limit"].value;

    AJAXgetMovieList($mood, $pageNo+1, $limit, $showDetails);
}
function loadPrevPage($showDetails){
    $mood = document.forms["state"]["mood"].value;
    $pageNo = document.forms["state"]["pageNo"].value;
    $limit = document.forms["state"]["limit"].value;
    
    AJAXgetMovieList($mood, $pageNo-1, $limit, $showDetails);
}

function AJAXgetMovieList($mood,$pageNo,$limit,$showDetails){
    $("#movieTiles").html("Fetching Suggestions... <i class='fa fa-lemon-o fa-spin fa-6x'></i>");
    
    var request = $.ajax({
        url: "MovieAJAX.jsp",
        type: "POST",
        data: {mood: $mood, pageNo: $pageNo, limit: $limit, task: "getMovieList"},
        dataType: "html"
    });
    request.done(function( msg ) {
        $("#movieTiles").html(msg);
    });
    
    request.always (function (){
        $("#movieTiles").ready(function (){
            animateTiles();
            if ($showDetails==="first"){
                $(".tile").first().click();
            }else if ($showDetails==="last"){
                $(".tile").last().click();
            }
        });
    });
}

function showDetails($tile , $id , $mood){
    $( "#movieDescription" ).html( "<i class='fa fa-refresh fa-spin fa-3x'></i> Loading..." );
    displayModal($("#myModal"));
    $("#myModal").find(".bgImage").css("background-image",$($tile).find(".tile-content").css("background-image"));
    
    var request = $.ajax({
        url: "MovieAJAX.jsp",
        type: "POST",
        data: { id : $id , mood: $mood, task: "getMovieDetails"},
        dataType: "html"
    });
    
    request.done(function( msg ) {
        $( "#movieDescription" ).html( msg );
    });
 
    request.fail(function( jqXHR, textStatus ) {
        $( "#movieDescription" ).html( "Ooops!! Could not serve request :(" );
    });
    request.always (function (){
        $(document).bind('keydown', function(evt){
        if (evt.keyCode === 27) {
           hideModal();
        }
        if (evt.keyCode === 39){
            $(document).unbind('keydown');
            if (isEmpty($($tile).next(".tile"))){
                loadNextPage("first");
            }else
            ($($tile).next(".tile")).click();
        }
        if (evt.keyCode === 37){
            $(document).unbind('keydown');
            if (isEmpty($($tile).prev(".tile"))){
                loadPrevPage("last");
            }else
            ($($tile).prev(".tile")).click();
        }
        $("#backdrop").focus();
        });
    });
}

function isEmpty( el ){
    return !$.trim(el.html());
}