/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.automacao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Julio
 */
public class ConnectionFactory {
    
    public Connection getConnection(){
        
        try {
            
            return  DriverManager.getConnection("jdbc:mysql://127.0.0.1/bdautomsys","userroot","123");
            
        } catch (Exception erro) {
            
            throw new RuntimeException(erro);
        }
        
    }
    
}
