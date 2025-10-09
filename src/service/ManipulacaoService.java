package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import interfaceIO.ManipulacaoIterface;
import main.Main;

public class ManipulacaoService implements ManipulacaoIterface {

	@Override
	public String create(String name) throws InterruptedException {
		String originPath = System.getProperty("user.dir"); 	// Pegando o diretório atual
		Path path = Paths.get(name + ".txt");
		if (new File(originPath + "/" + name + ".txt").isFile()) {	// Verificando se existi arquivo com o mesmo nome
			System.err.println("Arquivo já existe!");
			Thread.sleep(3000);
			Main.menuOption();
		}
		try {
			Files.createFile(path);
			//file.mkdirs();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Arquivo criando na pasta\n" + originPath);
		return "Arquivo criado!";
	}

	@Override
	public String remove(String file) throws InterruptedException {
		String originPath = System.getProperty("user.dir");
		Path path = Paths.get(file + ".txt");
		if(new File(originPath + "/" + file + ".txt").isFile()) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (new File(originPath + "/" + file + ".txt").isFile()) {
				System.err.println("Não foi possível excluir o arquivo! '" + file + "'");
		} 
		else {
			System.err.println("Nome do arquivo incorreto ou arquivo inexistente!"); 
			Main.menuOption();
		}
		return "Arquivo deletado com sucesso!";
	}

	@Override
	public String findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
