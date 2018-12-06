package br.com.pg.exception;

@SuppressWarnings("serial")
public class ClienteException extends Exception{

	public ClienteException(String mensagem){
		super(mensagem);
	}
}
