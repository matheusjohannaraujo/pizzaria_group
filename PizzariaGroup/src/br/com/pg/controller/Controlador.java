package br.com.pg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.pg.exception.*;
import br.com.pg.model.*;
import br.com.pg.service.*;

@Controller
public class Controlador {
	
	@Autowired
	AlimentosRegra alimentoRegra;
		
	@Autowired
	PedidoRegra pedidoRegra;
	
	@Autowired
	ClienteRegra clienteRegra;
	
	@Autowired
	MensagemRegra mensagemRegra;
	
	Cliente sessaoCliente = null;
	
	private String mensagem(String msg) {
		return "<script>window.alert(\"" + msg + "\");</script>";
	}
	
	@RequestMapping("/home")
	public String home(){
		return "../../index";
	}
	
	@RequestMapping("/quemSomos")
	public String quemSomos(){
		return "jsp/quemSomos";
	}
  
	@RequestMapping("/faleConosco")
	public String faleConosco(Model model){
		if(sessaoCliente != null) {
			model.addAttribute("cliente", sessaoCliente);
		}
		return "jsp/faleConosco";
	}
	
	@RequestMapping("/contato")
	public String contato(Model model, Mensagem mensagem){
		if(sessaoCliente != null) {			
			mensagem.setCpf(sessaoCliente.getCpf());
			mensagemRegra.salvar(mensagem);
			model.addAttribute("mensagem", mensagem("Mensagem enviada com sucesso!"));
		}				
		return "../../index";
	}
		
	@RequestMapping("/fazerPedido")
	public String fazerPedido(Model model){
		if(sessaoCliente != null) {
			model.addAttribute("cliente", sessaoCliente);
			model.addAttribute("pizzas", alimentoRegra.getPizza());
			model.addAttribute("refrigerantes", alimentoRegra.getRefrigerante());
		}
		return "jsp/fazerPedido";
	}
	
	@RequestMapping("/pedido")
	public String pedido(Pedido pedido, Model model){
		if(sessaoCliente != null) {
			try{
				pedido.setCpf(sessaoCliente.getCpf());
				pedidoRegra.salvar(pedido);
				model.addAttribute("pedidos", pedidoRegra.buscar(sessaoCliente));
			}catch (PedidoException e){			
				model.addAttribute("mensagem", mensagem(e.getMessage()));
				return "jsp/fazerPedido";
			}
		}		
		return "jsp/meusPedidos";
	}	
	
	@RequestMapping("/cancelarPedido")
	public String cancelarPedido(Model model, @RequestParam(value = "id", required = false) int id){
		if(sessaoCliente != null) {
			pedidoRegra.cancelar(sessaoCliente, id);
			model.addAttribute("pedidos", pedidoRegra.buscar(sessaoCliente));
			model.addAttribute("mensagem", mensagem("Pedido cancelado com sucesso!"));
		}		
		return "jsp/meusPedidos";
	}
	
	@RequestMapping("/confirmarPedido")
	public String confirmarPedido(Model model, @RequestParam(value = "id", required = false) int id){
		if(sessaoCliente != null) {
			pedidoRegra.confirmar(sessaoCliente, id);
			model.addAttribute("pedidos", pedidoRegra.buscar(sessaoCliente));
			model.addAttribute("mensagem", mensagem("Aguarde a entrega, pedido efetuado com sucesso!"));
		}		
		return "jsp/meusPedidos";
	}
	
	@RequestMapping("/loginCadastro")
	public String loginCadastro(){
		return "jsp/loginCadastro";
	}
	
	@RequestMapping("/fazerLogoff")
	public String fazerLogoff(HttpSession session, Model model){
		if(sessaoCliente != null) {
			model.addAttribute("mensagem", mensagem("Volte sempre, " + ((Cliente)session.getAttribute("cliente")).getNome()));
			session.removeAttribute("cliente");
			sessaoCliente = null;
		}				
		return "../../index";
	}
	
	@RequestMapping("/meusDados")
	public String meusDados(Model model){
		if(sessaoCliente != null) {
			model.addAttribute("cliente", sessaoCliente);
		}
		return "jsp/meusDados";
	}
	
	@RequestMapping("/meusPedidos")
	public String meusPedidos(Model model){
		if(sessaoCliente != null) {
			model.addAttribute("pedidos", pedidoRegra.buscar(sessaoCliente));
		}		
		return "jsp/meusPedidos";
	}
	
	@RequestMapping("/cadastrarCliente")
	public String cadastrarCliente(HttpSession session, Model model, Cliente cliente){
		try{
			cliente = clienteRegra.cadastrar(cliente);
			if(session.getAttribute("cliente") != null) {
				session.removeAttribute("cliente");
			}
			sessaoCliente = cliente;
			session.setAttribute("cliente", cliente);
			model.addAttribute("cliente", cliente);
			model.addAttribute("mensagem", mensagem("Ola, " + cliente.getNome()));
			return "../../index";
		}catch (ClienteException e){			
			model.addAttribute("mensagem", mensagem(e.getMessage()));
			return "jsp/loginCadastro";
		}
	}
	
	@RequestMapping("/atualizarCliente")
	public String atualizarCliente(@RequestParam(value = "senhaAntiga", required = false) String senhaAntiga, @RequestParam(value = "senhaNova", required = false) String senhaNova, HttpSession session, Model model, Cliente cliente){
		try{
			cliente.setCpf(sessaoCliente.getCpf());
			cliente.setSenha(senhaNova);
			cliente = clienteRegra.atualizar(cliente, senhaAntiga, sessaoCliente.getSenha());
			if(session.getAttribute("cliente") != null){
				session.removeAttribute("cliente");
			}
			sessaoCliente = cliente;
			session.setAttribute("cliente", cliente);
			model.addAttribute("mensagem", mensagem("Atualizacao efetuada com sucesso!"));
		}catch(ClienteException e) {
			model.addAttribute("mensagem", mensagem(e.getMessage()));
		}
		return "../../index";
	}
	
	@RequestMapping("/logarCliente")
	public String logarCliente(HttpSession session, Model model, Cliente cliente){		
		try{
			cliente = clienteRegra.logar(cliente);
			if(session.getAttribute("cliente") != null) {
				session.removeAttribute("cliente");
			}
			sessaoCliente = cliente;
			session.setAttribute("cliente", cliente);			
			model.addAttribute("cliente", cliente);
			model.addAttribute("mensagem", mensagem("Ola, " + cliente.getNome()));
			return "../../index";
		}catch (ClienteException e){
			model.addAttribute("mensagem", mensagem(e.getMessage()));
			model.addAttribute("cliente", cliente);
			return "jsp/loginCadastro";
		}
	}
}