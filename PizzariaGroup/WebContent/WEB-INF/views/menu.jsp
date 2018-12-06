<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="./resources/js/menu.js"></script>
<header>
	<h1><a href="home">Pizzaria Group</a></h1>
	<label class="fa fa-bars" aria-hidden="true" title="Abrir Menu"></label>
	<label class="fa fa-times" aria-hidden="true" title="Fechar Menu"></label>
	<nav>
		<ul> 
	    	<li><a href="home"><i class="fa fa-home" aria-hidden="true"></i> Home</a></li>
	        	        
	        <c:if test = "${cliente != null}">
	        	<li><a href='fazerPedido'><i class='fa fa-cutlery' aria-hidden='true'></i> Fazer Pedido</a></li>	
	        </c:if>
	        
	        <li><a href="quemSomos"><i class="fa fa-users" aria-hidden="true"></i> Quem Somos</a></li>
	        
	        <c:if test = "${cliente != null}">
	        	<li><a href="faleConosco"><i class="fa fa-comments" aria-hidden="true"></i> Fale Conosco</a></li>	
	        </c:if>
	        
	        <li><a href="loginCadastro"><i class="fa fa-user" aria-hidden="true"></i>
	        	<c:if test = "${cliente != null}"> 
	        		<c:if test = "${cliente.nome.length() > 15}">
	        			${cliente.nome.substring(0, 15)}
	        		</c:if>
	        		<c:if test = "${cliente.nome.length() <= 15}">
	        			${cliente.nome}
	        		</c:if>
	        	<nav>
	    	    	<ul>
	    	    		<li title="Sair"><a href="fazerLogoff"><i class="fa fa-lock" aria-hidden="true"></i> Fazer Logoff</a></li>
	    	    		<li title="Informações"><a href="meusDados"><i class="fa fa-cog" aria-hidden="true"></i> Meus Dados</a></li>
	    	    		<li title="Meus Pedidos"><a href="meusPedidos"><i class="fa fa-table" aria-hidden="true"></i> Meus Pedidos</a></li>
	    	    	</ul>
	    	    </nav>
	    	    </c:if>
	    	    <c:if test = "${cliente == null}">
	        		Login/Cadastro
	        	</c:if></a>
	        </li>
	    </ul>
	</nav>
	<div class="floatClear"></div>
</header>