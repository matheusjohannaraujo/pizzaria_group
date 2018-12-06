<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="br.com.pg.model.Cliente"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <c:import url="./head.jsp"/>
        <title>Meus Dados</title>
        <script type="text/javascript" src="./resources/js/meusDados.js"></script>
        <script src="https://rawgit.com/RobinHerbots/Inputmask/3.x/dist/jquery.inputmask.bundle.js"></script>
    </head>
    <body>
    	<div id="conteiner">
	       <c:import url="../menu.jsp"/>
	       <section class="s5">
	            <h2>Meus Dados</h2>
	            <div>
	           		<form action="atualizarCliente" method="POST">
	           			<input type="text" placeholder="Nome completo" name="nome" value="${cliente.nome}">
	                    <input type="text" placeholder="CPF" name="cpf" value="${cliente.cpf}" disabled="disabled">
	                    <input type="text" placeholder="CEP" name="cep" value="${cliente.cep}">
	                    <input type="text" placeholder="Logradouro" name="logradouro" value="${cliente.logradouro}">
	                    <input type="text" placeholder="Número" name="numero" value="${cliente.numero}">
	                    <input type="text" placeholder="Complemento" name="complemento" value="${cliente.complemento}">
	                    <input type="text" placeholder="Bairro" name="bairro" value="${cliente.bairro}">
	                    <input type="text" placeholder="Cidade" name="cidade" value="${cliente.cidade}">
	                    <input type="password" placeholder="Senha Antiga" name="senhaAntiga" required="required" minlength="6" maxlength="8">
	                    <input type="password" placeholder="Nova Senha" name="senhaNova" required="required" minlength="6" maxlength="8">
	           			<input type="reset" value="Limpar">
	           			<input type="submit" value="Salvar" name="submit">
	           		</form>
	           </div>	           
	        </section>
	    </div>
        <c:import url="../rodape.jsp"/>
    </body>
</html>