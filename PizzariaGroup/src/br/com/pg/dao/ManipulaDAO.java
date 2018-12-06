package br.com.pg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.pg.model.Cliente;
import br.com.pg.model.Mensagem;
import br.com.pg.model.Pedido;
import br.com.pg.model.Pizza;
import br.com.pg.model.Refrigerante;

@Repository
public class ManipulaDAO extends DAO implements IManipulaDAO{
	
	private String dbname = "pizzaria_group";
	private String[] tbname = new String[] {
		"pizza",
		"refrigerante",
		"cliente",
		"pedido",
		"mensagem"
	};
	
	public void setPizza(Pizza pizza){
		try{
			Connection conexao = getConexao(this.dbname);
			PreparedStatement query = querySQL("SELECT * FROM `" + this.tbname[0] + "` WHERE `tipo` = ? AND `tamanho` = ?;", conexao);
			query.setString(1, pizza.getTipo());
			query.setString(2, pizza.getTamanho());
			ResultSet p = query.executeQuery();
			if(p.next())
				System.out.println("Registro da pizza '" + pizza.getTipo() + " - " + pizza.getTamanho() + "'ja existe!");
			else{
				query = querySQL("INSERT INTO `" + this.tbname[0] + "` VALUES (NULL, ?, ?, ?, ?);", conexao);
				query.setString(1, pizza.getTipo());
				query.setString(2, pizza.getTamanho());
				query.setString(3, pizza.getValor());
				query.setString(4, pizza.getImagem());
				query.executeUpdate();
			}
			query.close();
			conexao.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public List<Pizza> getPizza(){
		try{
			Connection conexao = getConexao(this.dbname);
			PreparedStatement query = querySQL("SELECT * FROM `" + this.tbname[0] + "`;", conexao);
			ResultSet p = query.executeQuery();
			List<Pizza> lista = new ArrayList<Pizza>();
			while(p.next()) {
				lista.add(new Pizza(p.getString("tipo"), p.getString("tamanho"), p.getString("valor"), p.getString("imagem")));
			}	
			query.close();
			conexao.close();
			return lista;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void setRefrigerante(Refrigerante refrigerante){
		try{
			Connection conexao = getConexao(this.dbname);
			PreparedStatement query = querySQL("SELECT * FROM `" + this.tbname[1] + "` WHERE `tipo` = ?;", conexao);
			query.setString(1, refrigerante.getTipo());
			ResultSet p = query.executeQuery();
			if(p.next())
				System.out.println("Registro do refrigerante '" + refrigerante.getTipo() + "'ja existe!");
			else{
				query = querySQL("INSERT INTO `" + this.tbname[1] + "` VALUES (NULL, ?, ?);", conexao);
				query.setString(1, refrigerante.getTipo());
				query.setString(2, refrigerante.getValor());
				query.executeUpdate();
			}
			query.close();
			conexao.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public List<Refrigerante> getRefrigerante(){
		try{
			Connection conexao = getConexao(this.dbname);
			PreparedStatement query = querySQL("SELECT * FROM `" + this.tbname[1] + "`;", conexao);
			ResultSet r = query.executeQuery();
			List<Refrigerante> lista = new ArrayList<Refrigerante>();
			while(r.next()) {
				lista.add(new Refrigerante(r.getString("tipo"), r.getString("valor")));
			}
			query.close();
			conexao.close();
			return lista;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void setCliente(Cliente cliente){
		try{
			Connection conexao = getConexao(this.dbname);
			PreparedStatement query = conexao.prepareStatement("SELECT * FROM `" + this.tbname[2] + "` WHERE `cpf` = ?;");
			query.setString(1, cliente.getCpf());
			ResultSet p = query.executeQuery();
			if(!p.next()){
				try {
					query = conexao.prepareStatement("INSERT INTO `" + this.tbname[2] + "` VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
					query.setString(1, cliente.getNome());
					query.setString(2, cliente.getCpf());
					query.setString(3, cliente.getCep());
					query.setString(4, cliente.getLogradouro());
					query.setString(5, cliente.getNumero());
					query.setString(6, cliente.getComplemento());
					query.setString(7, cliente.getBairro());
					query.setString(8, cliente.getCidade());
					query.setString(9, cliente.getSenha());
					query.executeUpdate();
					System.out.println("Usuario cadastrado com sucesso!");
				}catch(Exception e) {
					System.out.println("Erro ao cadastrar usuario!");
				}
			}
			query.close();
			conexao.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setUpdCliente(Cliente cliente, String senhaAntiga){
		try{
			Connection conexao = getConexao(this.dbname);
			PreparedStatement query = conexao.prepareStatement("UPDATE `" + this.tbname[2]+ "` SET `nome` = ?, `cep` = ?, `logradouro` = ?, `numero` = ?, `complemento` = ?, `bairro` = ?, `cidade` = ?, `senha` = ? WHERE `cpf` = ? AND `senha` = ?;");
			query.setString(1, cliente.getNome());				
			query.setString(2, cliente.getCep());
			query.setString(3, cliente.getLogradouro());
			query.setString(4, cliente.getNumero());
			query.setString(5, cliente.getComplemento());
			query.setString(6, cliente.getBairro());
			query.setString(7, cliente.getCidade());
			query.setString(8, cliente.getSenha());				
			query.setString(9, cliente.getCpf());
			query.setString(10, senhaAntiga);
			query.executeUpdate();
			query.close();
			conexao.close();
			System.out.println("Usuario atualizado com sucesso!");
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println("Erro ao atualizar usuario!");
		}
	}
	
	public List<Cliente> getCliente(){
		try{
			Connection conexao = getConexao(this.dbname);
			PreparedStatement query = querySQL("SELECT * FROM `" + this.tbname[2] + "`;", conexao);
			ResultSet c = query.executeQuery();
			List<Cliente> lista = new ArrayList<Cliente>();
			while(c.next()) {
				Cliente cliente = new Cliente();
				cliente.setNome(c.getString("nome"));
				cliente.setCpf(c.getString("cpf"));
				cliente.setCep(c.getString("cep"));
				cliente.setLogradouro(c.getString("logradouro"));
				cliente.setNumero(c.getString("numero"));
				cliente.setComplemento(c.getString("complemento"));
				cliente.setBairro(c.getString("bairro"));
				cliente.setCidade(c.getString("cidade"));
				cliente.setSenha(c.getString("senha"));
				lista.add(cliente);
			}	
			query.close();
			conexao.close();
			return lista;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public List<Pedido> getPedido(Cliente cliente){
		try{
			Connection conexao = getConexao(this.dbname);
			PreparedStatement query = conexao.prepareStatement("SELECT * FROM `" + this.tbname[3] + "` WHERE `cpf` = ?;");
			query.setString(1, cliente.getCpf());
			ResultSet c = query.executeQuery();
			List<Pedido> lista = new ArrayList<Pedido>();
			while(c.next()) {
				Pedido p = new Pedido();
				p.setId(c.getInt("id"));
				p.setCpf(c.getString("cpf"));
				p.setCep(c.getString("cep"));
				p.setLogradouro(c.getString("logradouro"));
				p.setNumero(c.getString("numero"));
				p.setComplemento(c.getString("complemento"));
				p.setBairro(c.getString("bairro"));
				p.setCidade(c.getString("cidade"));
				p.setPizza(c.getString("pizza"));
				p.setQtdPizza(c.getString("qtdpizza"));
				p.setRefrigerante(c.getString("refrigerante"));
				p.setQtdRefrigerante(c.getString("qtdRefrigerante"));
				p.setTotal(c.getString("total"));
				p.setStatus(c.getString("status"));
				lista.add(p);
			}	
			query.close();
			conexao.close();
			return lista;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void setPedido(Pedido p){
		try{
			Connection conexao = getConexao(this.dbname);
			PreparedStatement query = conexao.prepareStatement("INSERT INTO `" + this.tbname[3] + "` VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			query.setString(1, p.getCpf());
			query.setString(2, p.getCep());
			query.setString(3, p.getLogradouro());
			query.setString(4, p.getNumero());
			query.setString(5, p.getComplemento());
			query.setString(6, p.getBairro());
			query.setString(7, p.getCidade());
			query.setString(8, p.getPizza());
			query.setString(9, p.getQtdPizza());
			query.setString(10, p.getRefrigerante());
			query.setString(11, p.getQtdRefrigerante());
			query.setString(12, p.getTotal());
			query.setString(13, p.getStatus());
			query.executeUpdate();
			query.close();
			conexao.close();
			System.out.println("Pedido cadastrado com sucesso!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Erro ao cadastrar pedido!");
		}
	}
	
	public void confirmarPedido(Cliente cliente, int id){
		try{
			Connection conexao = getConexao(this.dbname);
			PreparedStatement query = conexao.prepareStatement("UPDATE `" + this.tbname[3] + "` SET `status` = 'Efetuado' WHERE `id` = ? AND `cpf` = ?;");
			query.setString(1, String.valueOf(id));
			query.setString(2, cliente.getCpf());
			query.executeUpdate();
			query.close();
			conexao.close();
			System.out.println("Pedido confirmado com sucesso!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Erro ao efetuar confirmacao do pedido!");
		}
	}
	
	public void cancelarPedido(Cliente cliente, int id){
		try{
			Connection conexao = getConexao(this.dbname);
			PreparedStatement query = conexao.prepareStatement("DELETE FROM `" + this.tbname[3] + "` WHERE `id` = ? AND `cpf` = ?;");
			query.setString(1, String.valueOf(id));
			query.setString(2, cliente.getCpf());
			query.executeUpdate();
			query.close();
			conexao.close();
			System.out.println("Pedido cancelado com sucesso!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Erro ao efetuar cancelamento do pedido!");
		}
	}
	
	public void setMensagem(Mensagem m){
		try{
			Connection conexao = getConexao(this.dbname);
			PreparedStatement query = conexao.prepareStatement("INSERT INTO `" + this.tbname[4] + "` VALUES (NULL, ?, ?, ?);");
			query.setString(1, m.getCpf());
			query.setString(2, m.getAssunto());
			query.setString(3, m.getMensagem());
			query.executeUpdate();
			query.close();
			conexao.close();
			System.out.println("Mensagem cadastrada com sucesso!");
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println("Erro ao cadastrar mensagem!");
		}
	}
	
	public ManipulaDAO(){		
		try{
			Connection conexao = getConexao();
			PreparedStatement query = querySQL("CREATE DATABASE IF NOT EXISTS `" + this.dbname + "` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;", conexao);
			query.executeUpdate();
			query = querySQL("USE `" + this.dbname + "`", conexao);
			query.executeUpdate();
			query = querySQL("CREATE TABLE IF NOT EXISTS `" + this.tbname[0] + "`(`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, `tipo` TEXT NOT NULL, `tamanho` TEXT NOT NULL, `valor` TEXT NOT NULL, `imagem` TEXT NOT NULL) ENGINE = MyISAM CHARSET=utf8 COLLATE utf8_general_ci;", conexao);
			query.executeUpdate();
			query = querySQL("CREATE TABLE IF NOT EXISTS `" + this.tbname[1] + "`(`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, `tipo` TEXT NOT NULL, `valor` TEXT NOT NULL) ENGINE = MyISAM CHARSET=utf8 COLLATE utf8_general_ci;", conexao);
			query.executeUpdate();
			query = querySQL("CREATE TABLE IF NOT EXISTS `" + this.tbname[2] + "`(`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, `nome` TEXT NOT NULL, `cpf` TEXT NOT NULL, `cep` TEXT NOT NULL, `logradouro` TEXT NOT NULL, `numero` TEXT NOT NULL, `complemento` TEXT, `bairro` TEXT NOT NULL, `cidade` TEXT NOT NULL, `senha` TEXT NOT NULL) ENGINE = MyISAM CHARSET=utf8 COLLATE utf8_general_ci;", conexao);
			query.executeUpdate();
			query = querySQL("CREATE TABLE IF NOT EXISTS `" + this.tbname[3] + "`(`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, `cpf` TEXT NOT NULL, `cep` TEXT NOT NULL, `logradouro` TEXT NOT NULL, `numero` TEXT NOT NULL, `complemento` TEXT, `bairro` TEXT NOT NULL, `cidade` TEXT NOT NULL, `pizza` TEXT NOT NULL, `qtdPizza` TEXT NOT NULL, `refrigerante` TEXT NOT NULL, `qtdRefrigerante` TEXT NOT NULL, `total` TEXT NOT NULL, `status` TEXT NOT NULL) ENGINE = MyISAM CHARSET=utf8 COLLATE utf8_general_ci;", conexao);
			query.executeUpdate();
			query = querySQL("CREATE TABLE IF NOT EXISTS `" + this.tbname[4] + "`(`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, `cpf` TEXT NOT NULL, `assunto` TEXT NOT NULL, `mensagem` TEXT NOT NULL) ENGINE = MyISAM CHARSET=utf8 COLLATE utf8_general_ci;", conexao);
			query.executeUpdate();
			query.close();
			conexao.close();
			
			//INSERE PIZZAS NO BANCO DE DADOS
			
			this.setPizza(new Pizza("Atum", "Pequena", "15.60", "atum.png"));//40% da Grande
			this.setPizza(new Pizza("Atum", "Media", "20.80", "atum.png"));//20% da Grande
			this.setPizza(new Pizza("Atum", "Grande", "26.00", "atum.png"));
			
			this.setPizza(new Pizza("Bacon", "Pequena", "13.80", "bacon.png"));
			this.setPizza(new Pizza("Bacon", "Media", "18.40", "bacon.png"));
			this.setPizza(new Pizza("Bacon", "Grande", "23.00", "bacon.png"));
			
			this.setPizza(new Pizza("Calabresa", "Pequena", "11.40", "calabresa.png"));
			this.setPizza(new Pizza("Calabresa", "Media", "15.20", "calabresa.png"));
			this.setPizza(new Pizza("Calabresa", "Grande", "19.00", "calabresa.png"));
			
			this.setPizza(new Pizza("Catupiry", "Pequena", "13.20", "catupiry.png"));
			this.setPizza(new Pizza("Catupiry", "Media", "17.60", "catupiry.png"));
			this.setPizza(new Pizza("Catupiry", "Grande", "22.00", "catupiry.png"));
			
			this.setPizza(new Pizza("Escarola", "Pequena", "13.80", "escarola.png"));
			this.setPizza(new Pizza("Escarola", "Media", "18.40", "escarola.png"));
			this.setPizza(new Pizza("Escarola", "Grande", "23.00", "escarola.png"));
			
			this.setPizza(new Pizza("Lombo com Cheddar", "Pequena", "14.70", "lombo_com_cheddar.png"));
			this.setPizza(new Pizza("Lombo com Cheddar", "Media", "19.60", "lombo_com_cheddar.png"));
			this.setPizza(new Pizza("Lombo com Cheddar", "Grande", "24.50", "lombo_com_cheddar.png"));
			
			this.setPizza(new Pizza("Marguerita", "Pequena", "12.90", "marguerita.png"));
			this.setPizza(new Pizza("Marguerita", "Media", "17.20", "marguerita.png"));
			this.setPizza(new Pizza("Marguerita", "Grande", "21.50", "marguerita.png"));
			
			this.setPizza(new Pizza("Mussarela", "Pequena", "10.80", "mussarela.png"));
			this.setPizza(new Pizza("Mussarela", "Media", "14.40", "mussarela.png"));
			this.setPizza(new Pizza("Mussarela", "Grande", "18.00", "mussarela.png"));
			
			this.setPizza(new Pizza("Portuguesa", "Pequena", "15.00", "portuguesa.png"));
			this.setPizza(new Pizza("Portuguesa", "Media", "20.00", "portuguesa.png"));
			this.setPizza(new Pizza("Portuguesa", "Grande", "25.00", "portuguesa.png"));
			
			this.setPizza(new Pizza("Presunto com Cheddar", "Pequena", "11.70", "presunto_com_cheddar.png"));
			this.setPizza(new Pizza("Presunto com Cheddar", "Media", "15.60", "presunto_com_cheddar.png"));
			this.setPizza(new Pizza("Presunto com Cheddar", "Grande", "19.50", "presunto_com_cheddar.png"));
			
			this.setPizza(new Pizza("Provolone", "Pequena", "16.20", "provolone.png"));
			this.setPizza(new Pizza("Provolone", "Media", "21.60", "provolone.png"));
			this.setPizza(new Pizza("Provolone", "Grande", "27.00", "provolone.png"));
			
			this.setPizza(new Pizza("Quatro Queijos", "Pequena", "13.20", "quatro_queijos.png"));
			this.setPizza(new Pizza("Quatro Queijos", "Media", "17.60", "quatro_queijos.png"));
			this.setPizza(new Pizza("Quatro Queijos", "Grande", "22.00", "quatro_queijos.png"));

			//INSERE REFRIGERANTES NO BANCO DE DADOS
			this.setRefrigerante(new Refrigerante("Sem Ref", "0.00"));
			this.setRefrigerante(new Refrigerante("Antartica", "4.50"));
			this.setRefrigerante(new Refrigerante("Kuat", "4.50"));
			this.setRefrigerante(new Refrigerante("Sprite", "5.00"));
			this.setRefrigerante(new Refrigerante("Fanta", "5.00"));
			this.setRefrigerante(new Refrigerante("Pepsi", "5.50"));
			this.setRefrigerante(new Refrigerante("CocaCola", "5.50"));
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e);
		}
	}
}