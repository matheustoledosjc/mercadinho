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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static mercadinho.Menu.menuProdutos;

/**
 *
 * @author MatheusToledo
 */
public class Produto {

    private int id;
    private String descricao;
    private float valor_compra;
    private float valor_venda;
    
    Produto(String descricao, Float valor_compra, Float valor_venda) {
        setDescricao(descricao);
        setValorCompra(valor_compra);
        setValorVenda(valor_venda);
    }
    
    public static void cadastrar() throws Exception{
        Scanner teclado = new Scanner(System.in);
        System.out.print("Nome do produto: ");
        String nome = teclado.nextLine();
        System.out.print("Valor de compra: ");
        String valor_compra = teclado.nextLine();
        System.out.print("Valor de venda: ");
        String valor_venda = teclado.nextLine();
        Produto produto = new Produto(nome, Float.parseFloat(valor_compra), Float.parseFloat(valor_venda));
        try{
            produto.criar();
        } catch(Exception erro){
            System.out.println(erro);
        }
        menuProdutos();
    }
    
    public void criar() throws SQLException, Exception {
        String sql = "INSERT INTO produtos (descricao, valor_compra, valor_venda) VALUES (?, ?, ?)";
        
        Connection conn = null;       
        try{
            conn = ConexaoBancoDados.criarConexao();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, this.getDescricao());
            pstm.setFloat(2, this.getValorCompra());
            pstm.setFloat(3, this.getValorVenda());
            pstm.execute();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    public static List<Produto> listarTodos() throws Exception{
        String sql = "SELECT * FROM produtos";
        List<Produto> produtos = new ArrayList<Produto>();
        Connection conn = null;       
        try{
            conn = ConexaoBancoDados.criarConexao();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rset = pstm.executeQuery();
            while (rset.next()) {
                Produto produto = new Produto(rset.getString("descricao"), rset.getFloat("valor_compra"), rset.getFloat("valor_venda"));
                
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }        
        return produtos;
    }
    
    //SETERS
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public void setValorCompra(float valor_compra){
        this.valor_compra = valor_compra;
    }
    public void setValorVenda(float valor_venda){
        this.valor_venda = valor_venda;
    }    
    
    //GETERS
    public int getId(){
        return id;
    }
    public String getDescricao(){
        return descricao;
    }
    public float getValorCompra(){
        return valor_compra;
    }
    public float getValorVenda(){
        return valor_venda;
    }    
}
