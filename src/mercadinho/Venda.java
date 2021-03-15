/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import static mercadinho.Menu.menuPrincipal;

/**
 *
 * @author MatheusToledo
 */
public class Venda {
    public static void realizarVenda() throws Exception{
        Scanner teclado = new Scanner(System.in);
        System.out.println("---- VENDA ----");
        System.out.print("Digite o ID do produto: ");
        String id_produto = teclado.nextLine();

        String sql = "SELECT * FROM produtos WHERE id=?";        
        try{
            Connection conn = (Connection) ConexaoBancoDados.criarConexao();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(id_produto));
            ResultSet rset = pstm.executeQuery();
            while (rset.next()) {
                Produto produto = new Produto(rset.getString("descricao"), rset.getFloat("valor_compra"), rset.getFloat("valor_venda"));
                produto.setId(rset.getInt("id"));
                System.out.println("DESCRICAO: "+produto.getDescricao()+" VALOR: "+produto.getValorVenda());
                System.out.print("Digite o a quantidade: ");
                String quantidade = teclado.nextLine();                
                Float valor_final = (Float) produto.getValorVenda()* Integer.parseInt(quantidade);
                System.out.println("Valor da venda: "+ valor_final);                
            }            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        menuPrincipal();
    }
}
