package br.com.pg.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception{
		
		String uri = request.getRequestURI();
		if(uri.endsWith("home") || uri.endsWith("quemSomos") || uri.endsWith("loginCadastro") || uri.endsWith("logarCliente") || uri.endsWith("cadastrarCliente") || uri.endsWith("logar") || uri.contains("resources")){
			return true;
		}
		
		if(request.getSession().getAttribute("cliente") != null){
			return true;
		}
		
		response.sendRedirect("home");
		return false;
	}
	
}
