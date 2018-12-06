package br.com.pg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="refrigerante")
public class Refrigerante {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String tipo;
	
	@Column
	private String valor;

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

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}	
	
	public Refrigerante() {}
	
	public Refrigerante(String tipo, String valor) {
		this.setTipo(tipo);
		this.setValor(valor);
	}
}