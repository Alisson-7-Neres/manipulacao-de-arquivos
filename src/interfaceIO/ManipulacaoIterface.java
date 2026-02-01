package interfaceIO;

import java.io.IOException;
import java.nio.file.Path;

public interface ManipulacaoIterface {

	void create(String name) throws InterruptedException ,IOException;
	void remove(String file) throws InterruptedException, IOException;
	void findAll() throws InterruptedException, IOException;
	void find(String file) throws InterruptedException, IOException;
	void findWord(String word) throws InterruptedException, IOException;
	void write(String file) throws InterruptedException, IOException;
	void replace(String word, String replace) throws InterruptedException, IOException;
	Path path(String name);
	
}
