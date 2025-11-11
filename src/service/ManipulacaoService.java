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
import java.util.stream.Stream;

import interfaceIO.ManipulacaoIterface;
import main.Main;

public class ManipulacaoService implements ManipulacaoIterface {

	@Override
	public String create(String name) throws InterruptedException, IOException {
		String originPath = System.getProperty("user.dir"); // Pegando o diretório atual
		Path path = Paths.get(name + ".txt");
		if (new File(originPath + "/" + name + ".txt").isFile()) { // Verificando se existi arquivo com o mesmo nome
			System.err.println("Arquivo já existe!");
			Thread.sleep(3000);
			Main.menuOption();
		}
		try {
			Files.createFile(path);
			// file.mkdirs();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Arquivo criando na pasta\n" + originPath);
		return "Arquivo criado!";
	}

	@Override
	public String remove(String file) throws InterruptedException, IOException {
		String originPath = System.getProperty("user.dir");
		Path path = Paths.get(file + ".txt");
		if (new File(originPath + "/" + file + ".txt").isFile()) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (new File(originPath + "/" + file + ".txt").isFile()) {
			System.err.println("Não foi possível excluir o arquivo! '" + file + "'");
		} else {
			System.err.println("Nome do arquivo incorreto ou arquivo inexistente!");
			Main.menuOption();
		}
		return "Arquivo deletado com sucesso!";
	}

	@Override
	public String findAll() {
		String originPath = System.getProperty("user.dir");
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
		return null;
	}

	@Override
	public void find(String file) throws InterruptedException, IOException {
		String originPath = System.getProperty("user.dir");
		File diretory = new File(originPath);
		File[] files = diretory.listFiles();
		if (diretory != null) {
			for (File runFile : files) {
				if (runFile.toString().contains(file + ".txt")) {
					System.out.println("Arquivo encontrado!\n" + runFile.getName());
					Main.menuOption();
				}
			}
		}
	}

	@Override
	public void findWord(String word) throws InterruptedException, IOException {
		String originPath = System.getProperty("user.dir");
		Path diretory = Paths.get(originPath);
		// File[] files = diretory.listFiles();

		try (Stream<Path> stream = Files.walk(diretory)) {
	        stream
            .filter(Files::isRegularFile) // só arquivos
            .forEach(diretoryFind -> {
				try {
					checkFile(diretoryFind, word);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
    }
	}
	

	public static void checkFile(Path diretory, String word) throws IOException {
		if(Files.isDirectory(diretory)) { return;}
	    try (BufferedReader reader = Files.newBufferedReader(diretory, Charset.forName("UTF-8"))) {
            searchWithContent(reader, diretory, word);
        } catch (IOException e) {
            // Se falhar, tente ISO-8859-1 (muito comum em arquivos antigos e Windows)
            try (BufferedReader reader = Files.newBufferedReader(diretory, Charset.forName("ISO-8859-1"))) {
            	searchWithContent(reader, diretory, word);
            } catch (IOException ex) {
                System.err.println("Erro ao ler arquivo: " + diretory);
            }
        }
    }
	
	private static void searchWithContent(BufferedReader reader, Path diretory, String word) throws IOException {
		String line;
		while((line = reader.readLine()) != null) {
			if (line.toLowerCase().contains(word.toLowerCase())) {
				System.out.println("Palavra encontrada em " + diretory.toAbsolutePath());
				return;
			}
		}
	}

	@Override
	@SuppressWarnings("resource")
	public void write(String file) throws InterruptedException {
		try {
			String originPath = System.getProperty("user.dir");
			File diretory = new File(originPath);
			BufferedWriter writer = new BufferedWriter(new FileWriter(diretory + "/" + file + ".txt", true));
			System.out.printf("-> ");
			java.util.Scanner input = new java.util.Scanner(System.in);
			String text = input.nextLine();
			writer.write(text);
			writer.close();
			Main.menuOption();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
