/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadinho;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDados {
    
    private static final String USUARIO = "root";
    
    private static final String SENHA = "";
    
    private static final String URL_BD = "jdbc:mysql://localhost:3306/mercadinho";
    
    public static Connection criarConexao() throws Exception {
        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
 
        try {
            conn = DriverManager.getConnection(URL_BD, USUARIO, SENHA);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return conn;
    }
    
}
