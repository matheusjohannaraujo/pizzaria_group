function endereco(conteudo){
    if(!("erro" in conteudo)){
        document.querySelector("form input[name='logradouro']").setAttribute("value", conteudo.logradouro);
        document.querySelector("form input[name='complemento']").setAttribute("value", conteudo.complemento);
        document.querySelector("form input[name='bairro']").setAttribute("value", conteudo.bairro);
        document.querySelector("form input[name='cidade']").setAttribute("value", conteudo.localidade);
    }else
        alert("CEP não encontrado.");
}
window.onload = function(){
	
	document.querySelector("input[type='reset']").addEventListener("click", function(){
		inp = document.querySelectorAll("form input[type='text']");
		for(i in inp){
			inp[i].removeAttribute("value");
		}
	});
	
	$("form input[name='cpf']").inputmask({
	    mask: ['999.999.999-99'],
	    keepStatic: true
	});
	
	$("form input[name='cep']").inputmask({
	    mask: ['99999-999'],
	    keepStatic: true
	});
	
	pzSel = null;
	tamanhoPizza = null;
	
	setInterval(function(){
		
		if(pzSel != document.querySelector("select[name='pizza']").value){
			pzSel = document.querySelector("select[name='pizza']").value;
			figure = document.querySelectorAll("div > figure");
			p = document.querySelectorAll("div > figure > p");
    		for(i in p){
    			if(pzSel == p[i].textContent){
    				figure[i].setAttribute("style", "display: block");
    			}else{
    				figure[i].setAttribute("style", "display: none");
    			}
    		}
		}   
		
		if(tamanhoPizza != $("input[name='tamanhoPizza']:checked").val()){
			tamanhoPizza = $("input[name='tamanhoPizza']:checked").val();
			peq = $(".pTamanhoPequena");
			med = $(".pTamanhoMedia");
			gra = $(".pTamanhoGrande");
			if(tamanhoPizza == "p"){
				peq.show();
				med.hide();
				gra.hide();
				$("select[name='pizza'] option").removeAttr("selected");
				$("select[name='pizza'] .pTamanhoPequena").attr("selected", "selected");
			}else if(tamanhoPizza == "m"){
				peq.hide();
				med.show();
				gra.hide();
				$("select[name='pizza'] option").removeAttr("selected");
				$("select[name='pizza'] .pTamanhoMédia").attr("selected", "selected");
			}else if(tamanhoPizza == "g"){
				peq.hide();
				med.hide();
				gra.show();        				        		
				$("select[name='pizza'] option").removeAttr("selected");
				$("select[name='pizza'] .pTamanhoGrande").attr("selected", "selected");
        	}
		}
	}, 500);
	
	document.querySelector("input[name='cep']").addEventListener("keyup", function(){
        cep = this.value.replace("-","");
        if (cep != "" && cep.length == 8){
            //Cria um elemento javascript.
            var script = document.createElement('script');
            //Sincroniza com o callback.
            script.src = 'http://viacep.com.br/ws/'+ cep + '/json/?callback=endereco';
            //Insere script no documento e carrega o conteúdo.
            document.body.appendChild(script);
        }
    });    
};