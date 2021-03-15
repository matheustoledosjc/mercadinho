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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static mercadinho.Menu.menuProdutos;

public class Produto {

    private int id;
    private String descricao;
    private float valor_compra;
    private float valor_venda;
    private Float getValorCompra;
    private Float getValorVenda;
    
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
        System.out.println("---- PRODUTO INSERIDO COM SUCESSO ----");
        menuProdutos();
    }
    
    public static void listarTodos() throws Exception{
        String sql = "SELECT * FROM produtos";
        List<Produto> produtos;
        produtos = new ArrayList<>();   
        try{
            Connection conn = (Connection) ConexaoBancoDados.criarConexao();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rset = pstm.executeQuery();
            while (rset.next()) {
                Produto produto = new Produto(rset.getString("descricao"), rset.getFloat("valor_compra"), rset.getFloat("valor_venda"));
                produto.setId(rset.getInt("id"));
                
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        System.out.println("---- LISTA DE PRODUTOS ----");
        produtos.forEach(p -> {
            System.out.println("ID: "+p.getId()+" DESCRICAO: "+p.getDescricao()+" VALOR DE COMPRA: "+p.getValorCompra()+" VALOR DE VENDA: "+p.getValorVenda()+" MARGEM DE LUCRO: "+p.margemLucro());
        });
        menuProdutos();
    }
    
    public static void editar() throws Exception {
        Scanner teclado = new Scanner(System.in);
        System.out.println("---- PRODUTOS ----");
        System.out.print("Digite o ID do produto a ser editado: ");
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
                System.out.println("ID: "+produto.getId()+" DESCRICAO: "+produto.getDescricao()+" VALOR DE COMPRA: "+produto.getValorCompra()+" VALOR DE VENDA: "+produto.getValorVenda()+" MARGEM DE LUCRO: "+produto  .margemLucro());                
            }            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        System.out.print("Digite uma nova descrição: ");
        String descricao = teclado.nextLine();
        System.out.print("Digite um novo valor de compra: ");
        String valor_compra = teclado.nextLine();
        System.out.print("Digite um novo valor de venda: ");
        String valor_venda = teclado.nextLine();
        
        String sql_update = "UPDATE produtos SET descricao=? , valor_compra=?, valor_venda=? WHERE id=?";
             
        try{
            Connection conn = (Connection) ConexaoBancoDados.criarConexao();
            PreparedStatement pstm = conn.prepareStatement(sql_update);
            pstm.setString(1, descricao);
            pstm.setFloat(2, Float.parseFloat(valor_compra));
            pstm.setFloat(3, Float.parseFloat(valor_venda));
            pstm.setInt(4, Integer.parseInt(id_produto));
            pstm.execute();
            System.out.println("---- VALORES ATUALIZADOS ----");
            Produto produto = new Produto(descricao, Float.parseFloat(valor_compra), Float.parseFloat(valor_venda));
            System.out.println("ID: "+id_produto+" DESCRICAO: "+descricao+" VALOR DE COMPRA: "+valor_compra+" VALOR DE VENDA: "+valor_venda+" MARGEM DE LUCRO: "+produto.margemLucro());                
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        menuProdutos();
    }
    
    public static void mostrar() throws Exception{
        Scanner teclado = new Scanner(System.in);
        System.out.println("---- PRODUTOS ----");
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
                System.out.println("ID: "+produto.getId()+" DESCRICAO: "+produto.getDescricao()+" VALOR DE COMPRA: "+produto.getValorCompra()+" VALOR DE VENDA: "+produto.getValorVenda()+" MARGEM DE LUCRO: "+produto  .margemLucro());                
            }            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        menuProdutos();        
    }
    
    public static void deletar() throws Exception {
        Scanner teclado = new Scanner(System.in);
        System.out.println("---- PRODUTOS ----");
        System.out.print("Digite o ID do produto a ser deletado: ");
        String id_produto = teclado.nextLine();
        
        String sql = "SELECT * FROM produtos WHERE id=?";
        String delete_sql = "DELETE FROM produtos WHERE id=?";
        
        try{
            Connection conn = (Connection) ConexaoBancoDados.criarConexao();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(id_produto));
            ResultSet rset = pstm.executeQuery();
            while (rset.next()) {
                Produto produto = new Produto(rset.getString("descricao"), rset.getFloat("valor_compra"), rset.getFloat("valor_venda"));
                produto.setId(rset.getInt("id"));
                System.out.println("ID: "+produto.getId()+" DESCRICAO: "+produto.getDescricao()+" VALOR DE COMPRA: "+produto.getValorCompra()+" VALOR DE VENDA: "+produto.getValorVenda()+" MARGEM DE LUCRO: "+produto  .margemLucro());                
                System.out.print("Tem certeza que deseja excluir o produto acima?[S/N]");
                String resposta = teclado.nextLine();
                if("S".equals(resposta)){
                    PreparedStatement pstm_delete = conn.prepareStatement(delete_sql);
                    pstm_delete.setInt(1, Integer.parseInt(id_produto));
                    pstm_delete.execute();
                    System.out.println("---- PRODUTO EXCLUÍDO COM SUCESSO ----");
                }else{
                    System.out.println("---- AÇÃO CANCELADA PELO USUÁRIO ----");
                }
            }            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        menuProdutos();           
    }
    
    public String margemLucro(){
        DecimalFormat df = new DecimalFormat("#0.00"); 
        return df.format(this.getValorVenda() - this.getValorCompra());
    }
    
    //SETERS
    public void setId(int id){
        this.id = id;
    }    
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
