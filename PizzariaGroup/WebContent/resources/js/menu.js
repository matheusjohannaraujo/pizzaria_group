$(function(){
	if(document.querySelector("header .fa-bars")){
		document.querySelector("header .fa-bars").addEventListener("click", function(){
	        $(this).fadeOut(1000);
	        $("header > .fa-times").fadeIn(1500);
	        $("header > nav").fadeIn(1500);
	    });
	}
	if(document.querySelector("header .fa-times")){
		document.querySelector("header .fa-times").addEventListener("click", function(){
        	$(this).fadeOut(1000);
        	$("header > .fa-bars").fadeIn(1500);
        	$("header > nav").fadeOut(1000);    
    	});
	}    
    console.log("JavaScript do Menu - Ok");  
});