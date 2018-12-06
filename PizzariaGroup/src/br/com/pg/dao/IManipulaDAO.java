package br.com.pg.dao;

import java.util.List;

import br.com.pg.model.*;

public interface IManipulaDAO {
	public void setPizza(Pizza pizza);
	public List<Pizza> getPizza();
	public void setRefrigerante(Refrigerante refrigerante);
	public List<Refrigerante> getRefrigerante();
	public void setCliente(Cliente cliente);
	public void setUpdCliente(Cliente cliente, String senhaAntiga);
	public List<Cliente> getCliente();
	public List<Pedido> getPedido(Cliente cliente);
	public void setPedido(Pedido p);
	public void confirmarPedido(Cliente cliente, int id);
	public void cancelarPedido(Cliente cliente, int id);
	public void setMensagem(Mensagem m);
}