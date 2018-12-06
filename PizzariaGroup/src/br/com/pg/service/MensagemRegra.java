package br.com.pg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pg.dao.IManipulaDAO;
import br.com.pg.model.Mensagem;

@Service
public class MensagemRegra {
	
	@Autowired
	IManipulaDAO manipulaDAO;
	
	public void salvar(Mensagem mensagem){
		manipulaDAO.setMensagem(mensagem);
	}
}
