/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.automacao.jdbc;

import javax.swing.JOptionPane;

/**
 *
 * @author Julio
 */
public class TestaConexao {
    
    public static void main(String[] args) {
        try {
            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Conectou");
            
        } catch (Exception erro) {
             JOptionPane.showMessageDialog(null, "Ops nao Conectou" + erro);
        }
    }
    
}
