/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadinho;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author MatheusToledo
 */
public class Menu {
  static void menuPrincipal() {
    Scanner teclado = new Scanner(System.in);
    System.out.println("---- MENU PRINCIPAL ----" );
    System.out.println("Escolha uma das opções:" );
    System.out.println("1- Produtos");
    System.out.println("2- Realizar Venda");
    System.out.println("0- Sair do sistema");
    String opmenu = teclado.nextLine();
 
    switch (Integer.parseInt(opmenu)) {
      case 1:
        menuProdutos();
        break;
      case 2:
        venda();
        break;
      case 0:
        System.out.println("Sistema encerrado!");
        break;
      default:
        System.out.println("Escolha uma opção válida");
        menuPrincipal();
    }    
  }
  
  static void menuProdutos(){
    Scanner teclado = new Scanner(System.in);
    System.out.println("---- PRODUTOS ----" );
    System.out.println("1- Listar todos");
    System.out.println("2- Cadastrar");
    System.out.println("3- Editar");
    System.out.println("4- Mostar");
    System.out.println("5- Apagar");
    System.out.println("0- Voltar ao menu principal");
    String opmenu = teclado.nextLine();
    
    switch (Integer.parseInt(opmenu)) {
      case 1:
        try{ 
            Produto.listarTodos();
        }catch(Exception e){}
        break;
      case 2:
        try{
            Produto.cadastrar();
        }catch(Exception e){}
        break;
      case 0:
        menuPrincipal();
        break;
      default:
        System.out.println("Escolha uma opção válida");
        menuProdutos();
    }   
    menuPrincipal();
  }
  
  static void venda() {
    System.out.println("Vc entrou na venda");
  }    
}
