package main;

import java.io.IOException;
import java.util.Scanner;

import model.MenuOption;
import service.ManipulacaoService;

@SuppressWarnings("resource")
public class Main {
	static ManipulacaoService manipulacaoService = new ManipulacaoService();
	public static void main(String... args) throws InterruptedException, IOException {
		menuOption();
	}

	public static void menuOption() throws InterruptedException, IOException {
		Scanner inputOption = new Scanner(System.in);
		System.out.println("---Menu---\nSelecione uma opção:");
		System.out.printf("1 - Criar arquivo" +
						   "\n2 - Deletar arquivo" +
						   "\n3 - Listar todos os arquivos" +
						   "\n4 - Procurar" +
						   "\n5 - Procurar por palavra" +
						   "\n6 - Escrever" +
						   "\n7 - Sair" +
						    "\n-> ");
		int selected = inputOption.nextInt();
		MenuOption selectedOption = MenuOption.values()[selected - 1];
		
		switch(selectedOption) {
		case CREATE -> create();
		case REMOVE -> remove();
		case FINDALL -> findAll();
		case FIND -> find();
		case FINDWORD -> findWord();
		case WRITE -> write();
		case EXIT -> System.exit(0);
		}
	}
	
	public static void create() throws InterruptedException, IOException {
		Scanner inputCreate = new Scanner(System.in);
		System.out.printf("Nome do arquivo\n-> ");
		String name = inputCreate.nextLine();
		manipulacaoService.create(name);
	}
	
	public static void remove() throws InterruptedException, IOException {
		Scanner inputRemove = new Scanner(System.in);
		System.out.println("Qual arquivo deseja excluir? ");
		String remove = inputRemove.nextLine();
		manipulacaoService.remove(remove);
	}
	
	public static void findAll() {
		manipulacaoService.findAll();
	}
	
	public static void find() throws InterruptedException, IOException {
		Scanner inputFile = new Scanner(System.in);
		System.out.println("Qual o nome do arquivo: ");
		String file = inputFile.nextLine();
		manipulacaoService.find(file);
	}
	
	public static void findWord() throws InterruptedException, IOException {
		Scanner inputWord = new Scanner(System.in);
		System.out.println("Qual palavra deseja procurar? ");
		String word = inputWord.nextLine();
		manipulacaoService.findWord(word);
	}
	
	public static void write() throws InterruptedException {
		findAll();
		Scanner inputFile = new Scanner(System.in);
		System.out.println("Qual o nome do arquivo: ");
		String file = inputFile.nextLine();
		//manipulacaoService.find(file);
		manipulacaoService.write(file);
	}
}

