package br.com.pg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pg.dao.IManipulaDAO;
import br.com.pg.exception.PedidoException;
import br.com.pg.model.*;

@Service
public class PedidoRegra {
	
	@Autowired
	IManipulaDAO manipulaDAO;
	
	public void salvar(Pedido p) throws PedidoException{
		
		if(p.getCpf().equals("") || p.getCpf().length() != 14)
			throw new PedidoException("CPF nao informado corretamente!");
		
		if(p.getCep().equals("") || p.getCep().length() != 9)
			throw new PedidoException("CEP nao informado corretamente!");
		
		if(p.getLogradouro().equals("") || p.getLogradouro().length() < 3)
			throw new PedidoException("Logradouro nao informado corretamente!");
		
		if(p.getNumero().equals(""))
			throw new PedidoException("Numero nao informado corretamente!");
		
		if(p.getBairro().equals(""))
			throw new PedidoException("Bairro nao informado corretamente!");
		
		if(p.getCidade().equals("") || p.getCidade().length() < 2)
			throw new PedidoException("Cidade nao informado corretamente!");
		
		if(p.getPizza().equals(""))
			throw new PedidoException("Pizza nao informada corretamente!");
		
		if(p.getRefrigerante().equals(""))
			throw new PedidoException("Refrigerante nao informado corretamente!");
		
		try{
			
			for(Cliente c : manipulaDAO.getCliente()) {
				
				if(c.getCpf().equals(p.getCpf())) {
				
					for(Pizza pizza : manipulaDAO.getPizza()) {
					
						if(p.getPizza().equals((pizza.getTipo() + " - " + pizza.getTamanho() + " - R$" + pizza.getValor()))) {
						
							for(Refrigerante refrigerante : manipulaDAO.getRefrigerante()) {
							
								if(p.getRefrigerante().equals((refrigerante.getTipo() + " - R$" + refrigerante.getValor()))) {
								
									p.setStatus("Nao efetuado");
									
									int qtdPizza = Integer.parseInt(p.getQtdPizza());
									int qtdRefrigerante = Integer.parseInt(p.getQtdRefrigerante());
									
									if(qtdPizza < 1 || qtdPizza > 10) {
										p.setQtdPizza("1");
									}
									
									if(qtdRefrigerante < 1 || qtdRefrigerante > 10){
										p.setRefrigerante("1");
									}else if(p.getRefrigerante().equals("Sem Ref - R$0.00")) {
										p.setQtdRefrigerante("0");
									}
									
									Double pizzaValor = Double.parseDouble(pizza.getValor()) * qtdPizza;
									
									Double refrigeranteValor = Double.parseDouble(refrigerante.getValor()) * qtdRefrigerante;
									
									Double total = pizzaValor + refrigeranteValor;
									
									p.setTotal(String.valueOf(total));
									
									manipulaDAO.setPedido(p);
									
									break;
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			throw new PedidoException(e.getMessage());
		}		
	}
	
	public void confirmar(Cliente cliente, int id){
		manipulaDAO.confirmarPedido(cliente, id);
	}
	
	public void cancelar(Cliente cliente, int id){
		manipulaDAO.cancelarPedido(cliente, id);
	}
	
	public List<Pedido> buscar(Cliente cliente){
		return manipulaDAO.getPedido(cliente);
	}
}