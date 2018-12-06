window.onload = function(){  
	document.querySelector("input[type='reset']").addEventListener("click", function(){
		inp = document.querySelectorAll("form input[type='text']");
		for(i in inp){
			inp[i].removeAttribute("value");
		}
	});
};