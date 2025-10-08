package main;

import java.util.Scanner;

import model.MenuOption;
import service.ManipulacaoService;

public class Main {
	static ManipulacaoService manipulacaoService = new ManipulacaoService();
	public static void main(String... args) throws InterruptedException {
		menuOption();
	}

	public static void menuOption() throws InterruptedException {
		Scanner inputOption = new Scanner(System.in);
		System.out.println("---Menu---\nSelecione uma opção:");
		System.out.printf("1 - Criar arquivo" +
						   "\n2 - Deletar arquivo" +
						   "\n3 - Procurar" +
						   "\n4 - Sair" +
						    "\n-> ");
		int selected = inputOption.nextInt();
		MenuOption selectedOption = MenuOption.values()[selected - 1];
		
		switch(selectedOption) {
		case CREATE -> create();
		case REMOVE -> remove();
		case FIND -> find();
		case EXIT -> System.exit(0);
		}
	}
	
	public static void create() throws InterruptedException {
		Scanner input = new Scanner(System.in);
		//System.out.printf("Local onde vai ser criado\n-> " + "/home/" + System.getProperty("user.name") + "/" );
		//String path = input.nextLine();
		System.out.printf("Nome do arquivo\n-> ");
		String name = input.nextLine();
		manipulacaoService.create(name);
	}
	
	public static void remove() {
		
	}
	
	public static void find() {
		
	}
}

