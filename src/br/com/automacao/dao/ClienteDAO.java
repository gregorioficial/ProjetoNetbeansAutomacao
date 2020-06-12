/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.automacao.dao;

import br.com.automacao.jdbc.ConnectionFactory;
import br.com.automacao.model.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Julio
 */
public class ClienteDAO {

    private Connection con;

    public ClienteDAO() {
        this.con = new ConnectionFactory().getConnection();
        //recebi a conexao jdbc
    }

    //Metdodo cadastra cliebntes
    public void cadasCli(Clientes obj) {

        try {

            String sql = "insert into tb_clientes(nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());

            //3º executa comando
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cadastro Efetuado com Sucesso!");
        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro:" + erro);

        }

    }

    //Metdodo altera cliebntes
    public void alterCli(Clientes obj) {
        try {

            String sql = "update tb_clientes set nome =?,rg=?,cpf=?,email=?,telefone=?,celular=?,cep=?,endereco=?,"
                    + "numero=?,complemento=?,bairro=?,cidade=?,estado=? where id=?";
                   
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());
            
            stmt.setInt(14, obj.getId()); //esse recebi o codigo a ser alterado

            //3º executa comando
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");
        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro:" + erro);

        }


    }

    //Metdodo exclui cliebntes
    public void excluCli(Clientes obj) {
        try {

            String sql = "delete from tb_clientes where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            //3º executa comando
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Excluído Com Sucesso!");
        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro:" + erro);

        }


    }

    //metodo listar todos clientes
    public List<Clientes> listarClientes() {

        try {
            //1passo cria a lista
            List<Clientes> lista = new ArrayList<>();

            //2passo cria comando sql
            String sql = "select * from tb_clientes";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Clientes obj = new Clientes();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setNumero(rs.getInt("numero"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);
            }
            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro:" + erro);
            return null;

        }
    }
    
    // Conuslta cliente Por Nome Metodo
    public Clientes consultaPorNome(String nome){
        try {
            String sql = "select * from tb_clientes where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
             ResultSet rs = stmt.executeQuery();
              Clientes obj = new Clientes();
             if (rs.next()) {
               
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setNumero(rs.getInt("numero"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                
            }
            return obj;
                      
             
        } catch (Exception erro) {
            
            JOptionPane.showMessageDialog(null, "Cliente Não Econtrado:" + erro);
            return null;
        }
        
    }
    
    //filtar cliente por nome
     //metodo listar todos clientes retorna uma lista 
    public List<Clientes> buscaCliNome(String nome) {

        try {
            //1passo cria a lista
            List<Clientes> lista = new ArrayList<>();

            //2passo cria comando sql
            String sql = "select * from tb_clientes where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Clientes obj = new Clientes();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setNumero(rs.getInt("numero"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);
            }
            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro:" + erro);
            return null;

        }
    }

}
