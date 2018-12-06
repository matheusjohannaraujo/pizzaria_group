<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <c:import url="./head.jsp"/>
        <title>Login/Cadastro</title>
        <script type="text/javascript" src="./resources/js/loginCadastro.js"></script>
        <script src="https://rawgit.com/RobinHerbots/Inputmask/3.x/dist/jquery.inputmask.bundle.js"></script>
        ${mensagem}
    </head>
    <body>
    	<div id="conteiner">
	       <c:import url="../menu.jsp"/>
	       <section class="s5">
				<h2><button>Cadastrar-se</button></h2>				
	       </section>
	       <section class="s5">
	            <h2>Login</h2>
	           <div>
	           		<form action="logarCliente" method="POST">
	           			<input type="text" placeholder="Digite seu CPF" name="cpf" minlength="14" maxlength="14" value="${cliente.cpf}" required="required">
	           			<input type="password" placeholder="Digite sua senha" name="senha" minlength="6" maxlength="8" value="${cliente.senha}" required="required">
	           			<input type="reset" value="Limpar">
	           			<input type="submit" value="Logar" name="submit">
	           		</form>
	           </div>
	        </section>
	        <section class="s5">
	            <h2>Cadastro</h2>
	            <div>
	           		<form action="cadastrarCliente" method="POST">
	           			<input type="text" placeholder="Digite seu nome" name="nome" required="required" maxlength="50" value="Matheus Johann Araujo">
	           			<input type="text" placeholder="Digite seu CPF" name="cpf" required="required" minlength="14" maxlength="14" value="12345678910">
	           			<input type="text" placeholder="Digite seu CEP" name="cep" required="required" minlength="9" maxlength="9" value="54350340">
	           			<input type="text" placeholder="Logradouro (Rua, Avenida)" name="logradouro" required="required" maxlength="50" value="Rua Rosa Mesquita">
	           			<input type="text" placeholder="Número da residência" name="numero" required="required" maxlength="20" value="53">
	           			<input type="text" placeholder="Complemento de endereço" name="complemento" maxlength="50" value="Qd. 03">
	           			<input type="text" placeholder="Digite o Bairro" name="bairro" required="required" maxlength="50" value="Muribeca">
	           			<input type="text" placeholder="Digite a Cidade" name="cidade" required="required" maxlength="50" value="Jaboatão dos Guararapes">
	           			<input type="password" placeholder="Digite uma senha" name="senha" required="required" minlength="6" maxlength="8" value="123456">
	           			<input type="reset" value="Limpar">
	           			<input type="submit" value="Cadastrar" name="submit">
	           		</form>
	           </div>	           
	        </section>
	    </div>
        <c:import url="../rodape.jsp"/>
    </body>
</html>