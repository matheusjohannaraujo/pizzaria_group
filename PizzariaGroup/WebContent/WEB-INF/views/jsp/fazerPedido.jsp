<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="br.com.pg.model.Cliente"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <c:import url="./head.jsp"/>
        <title>Fazer Pedido</title>
        <script type="text/javascript" src="./resources/js/fazerPedido.js"></script>
        <script src="https://rawgit.com/RobinHerbots/Inputmask/3.x/dist/jquery.inputmask.bundle.js"></script>
    </head>
    <body>
    	<div id="conteiner">
	        <c:import url="../menu.jsp"/>
	        <section class="s4">
	            <h2>Fazer Pedido</h2>
	            <div>
	                <form method="POST" action="pedido">
	                    <input type="text" placeholder="Nome completo" name="nome" value="${cliente.nome}" disabled="disabled">
	                    <input type="text" placeholder="CPF" name="cpf" value="${cliente.cpf}" disabled="disabled">
	                    <input type="text" placeholder="CEP" name="cep" value="${cliente.cep}">
	                    <input type="text" placeholder="Logradouro" name="logradouro" value="${cliente.logradouro}">
	                    <input type="text" placeholder="Número" name="numero" value="${cliente.numero}">
	                    <input type="text" placeholder="Complemento" name="complemento" value="${cliente.complemento}">
	                    <input type="text" placeholder="Bairro" name="bairro" value="${cliente.bairro}">
	                    <input type="text" placeholder="Cidade" name="cidade" value="${cliente.cidade}">
						<input type="radio" id="p" name="tamanhoPizza" value="p"><label for="p">Pequena</label>
   	                    <input type="radio" id="m" name="tamanhoPizza" value="m"><label for="m">Média</label>
   	                    <input type="radio" id="g" name="tamanhoPizza" value="g" checked><label for="g">Grande</label>
	                    <select name="pizza">
	                    	<c:forEach var="p" items="${pizzas}" varStatus="id">	                    	
			            	 	<option class="pTamanho${p.tamanho}">${p.tipo} - ${p.tamanho} - R$${p.valor}</option>
			            	</c:forEach>
	                    </select>
	                    <input type="number" name="qtdPizza" placeholder="Quantidade de pizza" min="1" max="10" required="required">
	                    <select name="refrigerante">
	                        <c:forEach var="r" items="${refrigerantes}" varStatus="id">
			            	 	<option>${r.tipo} - R$${r.valor}</option>
			            	</c:forEach>
	                    </select>
	                    <input type="number" name="qtdRefrigerante" placeholder="Quantidade de refrigerante" min="1" max="10" required="required">
	                    <input type="reset" value="Limpar campos">
	                    <input type="submit" name="submit" value="Enviar pedido">
	                </form>
	            </div>
	        </section>
	        <div>
		        <section class="s1">
		        	<h2>Pizza selecionada</h2>
		        	<div>
		        		<c:forEach var="p" items="${pizzas}" varStatus="id">
		            	 <figure style="display: none;">
		                 	<img src="./resources/img/pizzas/${p.imagem}" alt="">
		                   	<p>${p.tipo} - ${p.tamanho} - R$${p.valor}</p>
		                 </figure>
		                 </c:forEach>
		        	</div>
		        </section>
	        </div>
	    </div>
        <c:import url="../rodape.jsp"/>
    </body>
</html>