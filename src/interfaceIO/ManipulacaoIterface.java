package interfaceIO;

public interface ManipulacaoIterface {

	String create(String name) throws InterruptedException;
	String delete();
	String findAll();
	
}
