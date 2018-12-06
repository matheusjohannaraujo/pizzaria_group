package br.com.pg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String cpf;
	
	@Column
	private String status;
	
	@Column
	private String cep;
	
	@Column
	private String logradouro;
	
	@Column
	private String numero;
	
	@Column
	private String complemento;
	
	@Column
	private String bairro;
	
	@Column
	private String cidade;
	
	@Column
	private String pizza;
	
	@Column
	private String qtdPizza;
	
	@Column
	private String refrigerante;
	
	@Column
	private String qtdRefrigerante;
	
	@Column
	private String total;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getPizza() {
		return pizza;
	}
	
	public void setPizza(String pizza) {
		this.pizza = pizza;
	}
	
	public String getQtdPizza() {
		return qtdPizza;
	}
	
	public void setQtdPizza(String qtdPizza) {
		this.qtdPizza = qtdPizza;
	
	}
	
	public String getRefrigerante() {
		return refrigerante;
	}
	
	public void setRefrigerante(String refrigerante) {
		this.refrigerante = refrigerante;
	}
	
	public String getQtdRefrigerante() {
		return qtdRefrigerante;
	}
	
	public void setQtdRefrigerante(String qtdRefrigerante) {
		this.qtdRefrigerante = qtdRefrigerante;
	}
	
	public String getTotal() {
		return total;
	}
	
	public void setTotal(String total) {
		this.total = total;
	}
}