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
	$("section.s5 form input[name='cpf']").inputmask({
	    mask: ['999.999.999-99'],
	    keepStatic: true
	});	
	$("section.s5 form input[name='cep']").inputmask({
	    mask: ['99999-999'],
	    keepStatic: true
	});	
	inputReset = document.querySelectorAll("input[type='reset']");
	for(i = 0; i < inputReset.length; i++){
		inputReset[i].addEventListener("click", function(){
			inp = document.querySelectorAll("form input[type='text']");
			for(j in inp){
				inp[j].removeAttribute("value");
			}
		});
	}
	section = document.querySelectorAll("section.s5");	
	section[2].style.display = "none";	
	form = {
		nome : 'Logar-se',
		numero : 1
	};	
	btn = document.querySelector("section.s5 > h2 > button");
	btn.addEventListener("click", function(){
		if(form["numero"] == 1){
			temp = this.textContent;
			this.textContent = form["nome"];
			form["nome"] = temp;
			form["numero"] = 2;
			section[1].style.display = "none";
			section[2].style.display = "block";
		}else if(form["numero"] == 2){
			temp = this.textContent;
			this.textContent = form["nome"];
			form["nome"] = temp;
			form["numero"] = 1;
			section[1].style.display = "block";
			section[2].style.display = "none";			
		}
	});	
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