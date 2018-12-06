package br.com.pg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pizza")
public class Pizza {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String tipo;
	
	@Column
	private String tamanho;
	
	@Column
	private String valor;
	
	@Column
	private String imagem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public Pizza() {}
	
	public Pizza(String tipo, String tamanho, String valor, String imagem) {
		this.setTipo(tipo);
		this.setTamanho(tamanho);
		this.setValor(valor);
		this.setImagem(imagem);
	}
	
}