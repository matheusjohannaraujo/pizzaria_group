<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="br.com.pg.model.Cliente"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <c:import url="./head.jsp"/>
        <title>Meus Pedidos</title>
        <script type="text/javascript" src="./resources/js/meusPedidos.js"></script>
        ${mensagem}
    </head>
    <body>
    	<div id="conteiner">
	       <c:import url="../menu.jsp"/>
	        <section class="s1">
	            <h2>Meus Pedidos</h2>
	            <div>
	                <table border="1">
	                	<tr>
	                		<td>Pedido</td>
	                		<td>CPF</td>
	                		<td>CEP</td>
	                		<td>Logradouro</td>
	                		<td>Número</td>
	                		<td>Complemento</td>
	                		<td>Bairro</td>
	                		<td>Cidade</td>
	                		<td>Pizza</td>
	                		<td>Quantidade pizza</td>
	                		<td>Refrigerante</td>
	                		<td>Quantidade refrigerante</td>
	                		<td>Valor Total</td>
	                	</tr>
	                	<c:forEach var="p" items="${pedidos}" varStatus="id">
	            	 	<tr>
	            	 		<c:if test = "${p.status == 'Nao efetuado'}">
	            	 			<td><a href="confirmarPedido?id=${p.id}">Confirmar</a>/<a href="cancelarPedido?id=${p.id}">Cancelar</a></td>	
	            	 		</c:if>
	            	 		<c:if test = "${p.status == 'Efetuado'}">
	            	 			<td>${p.status}</td>	
	            	 		</c:if>
	            	 		<td>${p.cpf}</td>
	            	 		<td>${p.cep}</td>
	            	 		<td>${p.logradouro}</td>
	            	 		<td>${p.numero}</td>
	            	 		<td>${p.complemento}</td>
	            	 		<td>${p.bairro}</td>
	            	 		<td>${p.cidade}</td>
	            	 		<td>${p.pizza}</td>
	            	 		<td>${p.qtdPizza}</td>
	            	 		<td>${p.refrigerante}</td>
	            	 		<td>${p.qtdRefrigerante}</td>
	            	 		<td>R$${p.total}</td>
	            	 	</tr>
	                	</c:forEach>
	                </table>
	            </div>
	        </section>
	    </div>
        <c:import url="../rodape.jsp"/>
    </body>
</html>