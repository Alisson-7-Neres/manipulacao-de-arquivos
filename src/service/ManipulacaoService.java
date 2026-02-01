package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import interfaceIO.ManipulacaoIterface;
import main.Main;
import model.MenuManipulacao;

public class ManipulacaoService implements ManipulacaoIterface {
	String originPath = System.getProperty("user.dir"); // Pegando o diretório atual

	@Override
	public void create(String name) throws InterruptedException, IOException {
		if (new File(originPath + "/" + name + ".txt").isFile()) { // Verificando se existi arquivo com o mesmo nome
			System.err.println("Arquivo já existe!");
			Thread.sleep(3000);
			Main.menuOption();
		}
		try {
			Files.createFile(path(name));
			// file.mkdirs();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Arquivo criando na pasta\n" + originPath);
		Thread.sleep(2000);
		menuOptionService();
	}

	@Override
	public void remove(String file) throws InterruptedException, IOException {
		if (new File(originPath + "/" + file + ".txt").isFile()) {
			try {
				Files.delete(path(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (new File(originPath + "/" + file + ".txt").isFile()) {
			System.err.println("Não foi possível excluir o arquivo! '" + file + "'");
		} else {
			System.err.println("Nome do arquivo incorreto ou arquivo inexistente!");
			Main.menuOption();
		}
		
		System.out.println("Arquivo deletado com sucesso!");
		Thread.sleep(2000);
		menuOptionService();
	}

	@Override
	public void findAll() throws InterruptedException, IOException {
		File directory = new File(originPath);
		File[] files = directory.listFiles(); // Pegando os arquivos do diretorio originPath
		int count = 0;
		System.out.println("#####Arquivos#####");
		if (directory != null) {
			for (File runFile : files) {
				if (runFile.toString().contains(".txt")) { // Pegando apenas arquivos com extensão '.txt'
					System.out.println(++count + ". " + runFile.getName());
				} 
			}
		}
		Thread.sleep(2000);
		menuOptionService();
	}

	@Override
	public void find(String file) throws InterruptedException, IOException {
		File diretory = new File(originPath);
		File[] files = diretory.listFiles();
		if (diretory != null) {
			for (File runFile : files) {
				if (runFile.toString().contains(file + ".txt")) {
					System.out.println("Arquivo encontrado!\n" + runFile.getName());
					Thread.sleep(2000);
					Main.menuOption();
				}
			}
		}
		
		Thread.sleep(2000);
		menuOptionService();
	}

	@Override
	public void findWord(String word) throws InterruptedException, IOException {
		Path diretory = Paths.get(originPath);
		// File[] files = diretory.listFiles();

		try (Stream<Path> stream = Files.walk(diretory)) {
			stream.filter(Files::isRegularFile) // só arquivos
					.forEach(diretoryFind -> {
						try {
							checkFile(diretoryFind, word);
						} catch (IOException  | InterruptedException e) {
							e.printStackTrace();
						} 
					});
		}
		Thread.sleep(2000);
		menuOptionService();
	}
	

	public static void checkFile(Path diretory, String word) throws IOException, InterruptedException {
		if(Files.isDirectory(diretory)) { return;}
	    try (BufferedReader reader = Files.newBufferedReader(diretory, Charset.forName("UTF-8"))) {
            searchWithContent(reader, diretory, word);
        } catch (IOException e) {
            // Se falhar, tente ISO-8859-1 (muito comum em arquivos antigos e Windows)
            try (BufferedReader reader = Files.newBufferedReader(diretory, Charset.forName("ISO-8859-1"))) {
            	searchWithContent(reader, diretory, word);
            } catch (IOException ex) {
                System.err.println("Erro ao ler arquivo: " + diretory);
                Thread.sleep(3000);
            }
        }
    }
	
	private static void searchWithContent(BufferedReader reader, Path diretory, String word) throws IOException, InterruptedException {
		String line;
		while((line = reader.readLine()) != null) {
			if (line.toLowerCase().contains(word.toLowerCase())) {
				System.out.println("Palavra encontrada em " + diretory.toAbsolutePath());
				Thread.sleep(3000);
				return;
			}
		}
	}

	@Override
	@SuppressWarnings("resource")
	public void write(String file) throws InterruptedException {
		try {
			File diretory = new File(originPath);
			BufferedWriter writer = new BufferedWriter(new FileWriter(diretory + "/" + file + ".txt", true));
			System.out.printf("-> ");
			java.util.Scanner input = new java.util.Scanner(System.in);
			String text = input.nextLine();
			writer.write(text);
			writer.close();
			
			Thread.sleep(2000);
			menuOptionService();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void replaceInFile(Path file, String word, String replace) throws IOException,InterruptedException {
		// Lê todo o conteúdo do arquivo
		String content = Files.readString(file, Charset.forName("UTF-8"));
		
		// Verifica se contém a palavra
		if(!content.contains(word) ) {
			return;
		}
		
		// Substitui
		//content = content.replace(word, replacement);
		content = content.replaceAll("\\b" + word + "\\b", replace);
		
		// Escreve de volta no arquivo
		Files.writeString(file, content, Charset.forName("UTF-8"));
		
		System.out.println("Substituição feita em:  " + file.toAbsolutePath());
		
		Thread.sleep(2000);
		menuOptionService();
	}
	
	@Override
	public void replace(String word, String replace) throws InterruptedException, IOException {
			Path diretory = Paths.get(originPath);
	
			try (Stream<Path> stream = Files.walk(diretory)) {
		        stream
	            .filter(path -> path.toString().endsWith(".txt")) // só arquivos
	            .forEach(file -> {
					try {
						replaceInFile(file, word, replace);
						
					} catch (IOException | InterruptedException e) {
						System.err.println("Erro ao processar: " + file);
						e.printStackTrace();
					}
				});
	    } catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public Path path(String name) {
		return Paths.get(name + ".txt");
	}
	
	@SuppressWarnings("resource")
	public static void menuOptionService() throws InterruptedException, IOException {
		
		System.out.println("---Menu---\nSelecione uma opção:");
		System.out.printf("1 - Voltar para o menu inicial" +
						   "\n2 - Sair" +
						    "\n-> ");
		
		Scanner inputOption = new Scanner(System.in);
		int selected = inputOption.nextInt();
		
		MenuManipulacao selectedOption = MenuManipulacao.values()[selected - 1];
		switch(selectedOption) {
		case MENU -> Main.menuOption();
		case SAIR -> System.exit(0);
		default -> throw new IllegalArgumentException("Valor inválido!: " + selectedOption); 
		}
	}

}
