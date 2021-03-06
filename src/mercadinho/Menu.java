/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadinho;

import java.util.Scanner;

/**
 *
 * @author MatheusToledo
 */
public class Menu {
  static void menuPrincipal() {
    Scanner teclado = new Scanner(System.in);
    System.out.println("Escolha uma das opções: " );
    System.out.println("1- Cadastro de Produto");
    System.out.println("2- Realizar Venda");
    System.out.println("0- Sair do sistema");

    String opmenu = teclado.nextLine();
 
    switch (Integer.parseInt(opmenu)) {
      case 1:
        cadastro();
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
  
  static void cadastro() {
    Scanner teclado = new Scanner(System.in);
    System.out.print("Nome do produto: ");
    String nome = teclado.nextLine();
    System.out.print("Valor: ");
    String valor = teclado.nextLine();
    System.out.print("Categoria: ");
    String categoria = teclado.nextLine();
    
    Produto produto = new Produto(nome, valor, categoria);
        
    menuPrincipal();
  }
  
  static void venda() {
    System.out.println("Vc entrou na venda");
  }
    
}
