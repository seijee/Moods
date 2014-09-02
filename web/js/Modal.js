function displayModal($modal){
    $(".modal").each(function (){
        $(this).hide(0);
    });
    $("body").css("overflow-y","hidden");
    $("#backdrop").fadeIn(600);
    $modal.fadeIn();
}
function hideModal (){
    $("#backdrop").fadeOut(600);
    $("body").css("overflow-y","auto");
    $(".modal").each(function (){       //should be optimised later, no need to close each modal
        $(this).hide(0);                //but given only 2-3 modals per page (max), its no big deal
    });
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
}