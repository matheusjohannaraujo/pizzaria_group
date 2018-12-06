<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="icon" href="./resources/img/favicon.png" type="image/x-icon"/>
		<link rel="shortcut icon" href="./resources/img/favicon.png" type="image/x-icon"/>
        <link rel="apple-touch-icon" href="./resources/img/favicon.png" type="image/x-icon"/>
        <link rel="stylesheet" type="text/css" href="./resources/css/index.css">
        <title>Pizzaria Group</title>
        <script type="text/javascript" src="./resources/js/jquery-3.2.1.js"></script>
        ${mensagem}
    </head>
    <body>
    	<div id="conteiner">
	        <c:import url="./WEB-INF/views/menu.jsp"/>
	        <section class="s0">
	            <div>
	                <tags:imgLogo id="Logo Pizzaria Group" />
	            </div>
	        </section>
        </div>
        <c:import url="./WEB-INF/views/rodape.jsp"/>
    </body>
</html>