/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadinho;

/**
 *
 * @author MatheusToledo
 */
public class Produto {

    private String[] produtos;
    
    Produto(String nome, String valor, String categoria) {
        System.out.println("Produto "+ nome +" cadastrado com sucesso");
        String[] produtos = new String[]{nome};
        System.out.println(produtos[0]);
    }
    
//    static void mostrarTodos(){
//        System.out.println(Produto.produtos[0]);
//        System.out.println(produtos[1]);
//    }
    
}
