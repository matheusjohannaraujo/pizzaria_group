package br.com.pg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pg.dao.IManipulaDAO;
import br.com.pg.exception.ClienteException;
import br.com.pg.model.Cliente;

@Service
public class ClienteRegra{
	
	@Autowired
	IManipulaDAO manipulaDAO;	
	
	public Cliente cadastrar(Cliente c) throws ClienteException{
		if(c.getNome().equals("") || c.getNome().length() < 4)
			throw new ClienteException("Nome nao informado corretamente!");
		
		if(c.getCpf().equals("") || c.getCpf().length() != 14)
			throw new ClienteException("CPF nao informado corretamente!");
		
		if(c.getCep().equals("") || c.getCep().length() != 9)
			throw new ClienteException("CEP nao informado corretamente!");
		
		if(c.getLogradouro().equals("") || c.getLogradouro().length() < 3)
			throw new ClienteException("Logradouro nao informado corretamente!");
		
		if(c.getNumero().equals(""))
			throw new ClienteException("Numero nao informado corretamente!");
		
		if(c.getBairro().equals(""))
			throw new ClienteException("Bairro nao informado corretamente!");
		
		if(c.getCidade().equals("") || c.getCidade().length() < 2)
			throw new ClienteException("Cidade nao informado corretamente!");
		
		if(c.getSenha().equals("") || c.getSenha().length() < 6)
			throw new ClienteException("Senha nao informado corretamente!");
		
		for(Cliente c1 : manipulaDAO.getCliente()) {
			if(c1.getCpf().equals(c.getCpf())) {
				throw new ClienteException("Este CPF ja se encontra cadastrado em nosso sistema!");
			}
		}
		
		manipulaDAO.setCliente(c);
		return c;
	}
	
	public Cliente logar(Cliente cliente) throws ClienteException{
		if(cliente.getCpf().equals("") || cliente.getCpf().length() != 14)
			throw new ClienteException("CPF nao informado corretamente!");
	
		if(cliente.getSenha().equals("") || cliente.getSenha().length() < 6)
			throw new ClienteException("Senha nao informado corretamente!");
		
		for(Cliente c : manipulaDAO.getCliente()) {
			if(c.getCpf().equals(cliente.getCpf()) && c.getSenha().equals(cliente.getSenha()))
				return c;
		}
		throw new ClienteException("Usuario nao cadastrado!");
	}
	
	public Cliente atualizar(Cliente c, String senhaAntiga, String senhaSessao) throws ClienteException{
		
		if(c.getNome().equals("") || c.getNome().length() < 4)
			throw new ClienteException("Nome nao informado corretamente!");
		
		if(c.getCpf().equals("") || c.getCpf().length() != 14)
			throw new ClienteException("CPF nao informado corretamente!");
		
		if(c.getCep().equals("") || c.getCep().length() != 9)
			throw new ClienteException("CEP nao informado corretamente!");
		
		if(c.getLogradouro().equals("") || c.getLogradouro().length() < 3)
			throw new ClienteException("Logradouro nao informado corretamente!");
		
		if(c.getNumero().equals(""))
			throw new ClienteException("Numero nao informado corretamente!");
		
		if(c.getBairro().equals(""))
			throw new ClienteException("Bairro nao informado corretamente!");
		
		if(c.getCidade().equals("") || c.getCidade().length() < 2)
			throw new ClienteException("Cidade nao informado corretamente!");
				
		if(c.getSenha().equals("") || c.getSenha().length() < 6)
			throw new ClienteException("Senha nao informado corretamente!");
		
		if(!senhaAntiga.equals(senhaSessao))
			throw new ClienteException("Senha incorreta");
		
		manipulaDAO.setUpdCliente(c, senhaAntiga);
		return c;
	}
}
