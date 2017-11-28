$(document).ready(function(){
    $('.btn-sm').click(function(){
			$('.modal').fadeIn('Slow');
		});
			$('.close').click(function(){
					$('.modal').fadeOut('Slow');
			});



    $('.opemModal').click(function(){
    	$('.modal').fadeIn('Slow');	
	});
			$('.close').click(function(){
					$('.modal').fadeOut('Slow');
			});
			
			
			$( ".testClick" ).click(function() {
				$("p").removeClass( "hide" );
			});
//				$( ".testClick" ).click(function() {
//				  $( ".paragraph" ).slideToggle( "slow" );
//				});
			
			$(".testClick").click(function () {
				   $(".paragraph").hide().slideDown('slow');
				});

				$(".testClick").click(function () {
				   $(".paragraph").slideUp(2000);
				});
});
