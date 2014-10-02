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

function isEmpty( el ){
      return !$.trim(el.html());
  }
  
function showDetails($tile , $id){
    $( "#movieDescription" ).html( "<i class='fa fa-refresh fa-spin fa-3x'></i> Loading..." );
    displayModal($("#myModal"));
    $("#myModal").find(".bgImage").css("background-image",$($tile).find(".tile-content").css("background-image"));
    
    var request = $.ajax({
        url: "MovieAJAX.jsp",
        type: "POST",
        data: { id : $id },
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
                alert("redirect to next page...");
            }
            ($($tile).next(".tile")).click();
        }
        if (evt.keyCode === 37){
            $(document).unbind('keydown');
            if (isEmpty($($tile).prev(".tile"))){
                alert("redirect to previous page...");
            }
            ($($tile).prev(".tile")).click();
        }
        $("#backdrop").focus();
        });
    });
}