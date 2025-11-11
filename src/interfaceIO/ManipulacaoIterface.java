package interfaceIO;

import java.io.IOException;

public interface ManipulacaoIterface {

	String create(String name) throws InterruptedException ,IOException;
	String remove(String file) throws InterruptedException, IOException;
	String findAll();
	void find(String file) throws InterruptedException, IOException;
	void findWord(String word) throws InterruptedException, IOException;
	void write(String file) throws InterruptedException, IOException;
	
}
