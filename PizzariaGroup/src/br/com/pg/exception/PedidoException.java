package br.com.pg.exception;

@SuppressWarnings("serial")
public class PedidoException extends Exception{
	public PedidoException(String mensagem){
		super(mensagem);
	}
}