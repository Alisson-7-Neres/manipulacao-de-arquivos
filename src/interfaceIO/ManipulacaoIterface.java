package interfaceIO;

public interface ManipulacaoIterface {

	String create(String name) throws InterruptedException;
	String remove(String file) throws InterruptedException;
	String findAll();
	
}
