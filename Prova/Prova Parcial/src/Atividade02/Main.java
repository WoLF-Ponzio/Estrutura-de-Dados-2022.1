package Atividade02;

import java.util.Scanner;
import java.util.Stack;

public class Main {
		public static void main(String[] args) {
		Lista<Integer> lista = new Lista<Integer>();
		Scanner teclado = new Scanner(System.in);
		
		for(int i = 0; i < 5; i++) {
			System.out.print("Digite o " + i + "º valor: ");
			lista.inserirFim(teclado.nextInt());
		}
		System.out.print("Ordem digita da lista -->  " + lista.imprimir());
		Lista <Integer> lista2 = new Lista<Integer>();
		lista2 = inversa(lista);
		System.out.println("\nLista inversa --> " + lista2.imprimir());
		
		
	}
	
	public static Lista<Integer> inversa(Lista<Integer> lista) {
		Lista<Integer> listaAux = new Lista<Integer>();
		for(int i = 5; i < 0; i--) {
			listaAux.inserirFim(lista.pesquisar(i).dado);;
		}
		return listaAux;
	}

}
