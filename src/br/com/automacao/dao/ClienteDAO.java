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
    public void alterCli() {

    }

    //Metdodo exclui cliebntes
    public void excluCli() {

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

}
