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
						   "\n3 - Listar todos os arquivos" +
						   "\n4 - Procurar" +
						   "\n5 - Sair" +
						    "\n-> ");
		int selected = inputOption.nextInt();
		MenuOption selectedOption = MenuOption.values()[selected - 1];
		
		switch(selectedOption) {
		case CREATE -> create();
		case REMOVE -> remove();
		case FINDALL -> findAll();
		case FIND -> find();
		case EXIT -> System.exit(0);
		}
	}
	
	public static void create() throws InterruptedException {
		Scanner inputCreate = new Scanner(System.in);
		System.out.printf("Nome do arquivo\n-> ");
		String name = inputCreate.nextLine();
		manipulacaoService.create(name);
	}
	
	public static void remove() throws InterruptedException {
		Scanner inputRemove = new Scanner(System.in);
		System.out.println("Qual arquivo deseja excluir? ");
		String remove = inputRemove.nextLine();
		manipulacaoService.remove(remove);
	}
	
	public static void findAll() {
		manipulacaoService.findAll();
	}
	
	public static void find() {
		
	}
}

