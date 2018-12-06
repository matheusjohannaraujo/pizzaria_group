<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="br.com.pg.model.Cliente"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <c:import url="./head.jsp"/>
        <title>Fale Conosco</title>
        <script type="text/javascript" src="./resources/js/faleConosco.js"></script>
    </head>
    <body>
    	<div id="conteiner">
	        <c:import url="../menu.jsp"/>
	        <section class="s3">
	            <h2>Fale Conosco</h2>
	            <div>
	                <form method="POST" action="contato">
	                    <input type="text" placeholder="Nome" value="${cliente.nome}" disabled="disabled">
	                    <input type="text" placeholder="CPF" value="${cliente.cpf}" disabled="disabled">
	                    <input type="text" placeholder="Assunto" name="assunto" maxlength="100" required="required">
	                    <textarea placeholder="Mensagem" name="mensagem" maxlength="1000" required="required"></textarea>
	                    <input type="reset" value="Limpar">
	                    <input type="submit" name="submit" value="Enviar">
	                </form>
	            </div>
	        </section>
	    </div>
        <c:import url="../rodape.jsp"/>
    </body>
</html>