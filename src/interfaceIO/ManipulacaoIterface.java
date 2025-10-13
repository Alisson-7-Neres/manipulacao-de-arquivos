package interfaceIO;

public interface ManipulacaoIterface {

	String create(String name) throws InterruptedException;
	String remove(String file) throws InterruptedException;
	String findAll();
	void find(String file) throws InterruptedException;
	void write(String file) throws InterruptedException;
	
}
