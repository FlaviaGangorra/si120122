package blog;

import java.util.LinkedList;
import java.util.Scanner;

public class Sistema {

	public static void main(String[] args) {
	
		
		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
		Scanner sc = new Scanner(System.in);
		int opcao = -1;
		
		do{
			System.out.println("1- cadastrar usuario /n2- postar links /n3- Logout");
			opcao = sc.nextInt();
			
			switch (opcao) {
			case 1:
				System.out.println("Digite um nome: ");
				Usuario usuario = new Usuario(sc.next());
				usuarios.add(usuario);
				break;
			
			case 2:
				System.out.println("Digite seu nome: ");
				String nome = sc.next();
				int indUser = -1;
				boolean cadastrado = false;
				for (int i = 0; i < usuarios.size(); i++) {
					if(usuarios.get(i).getNome().equalsIgnoreCase(nome)){
						cadastrado = true;
						indUser = i;
						break;
					} 
				}
				
				if (cadastrado) {
					System.out.println("Bem vindo: " + nome.toUpperCase() + "/n");
					System.out.println(usuarios.get(indUser).mostraLinha());
					System.out.println("\nPostar novo link: ");
					Link l = new Link(sc.next());
					usuarios.get(indUser).addLink(l);
					System.out.println(usuarios.get(indUser).mostraLinha());
					break;
				}else{
					System.out.println("Usuario nao cadastrado.");
				}
	
			case 3:
				System.out.println("Ate logo :D");
				break;
				
			}
			
		
	}while (opcao != 3);
	}
}
