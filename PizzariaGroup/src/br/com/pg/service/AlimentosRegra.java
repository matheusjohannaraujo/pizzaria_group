package br.com.pg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pg.dao.IManipulaDAO;
import br.com.pg.model.Pizza;
import br.com.pg.model.Refrigerante;

@Service
public class AlimentosRegra {
	
	@Autowired
	IManipulaDAO manipulaDAO;
	
	public List<Pizza> getPizza(){
		return manipulaDAO.getPizza();
	}
	
	public void setPizza(Pizza p){		
		manipulaDAO.setPizza(p);
	}
	
	public List<Refrigerante> getRefrigerante(){		
		return manipulaDAO.getRefrigerante();
	}
	
	public void setRefrigerante(Refrigerante r){		
		manipulaDAO.setRefrigerante(r);
	}
}