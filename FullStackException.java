
public class FullStackException extends Exception {

	public FullStackException() {
		super("Error: The stack is full! Program will exit");
		System.err.println("\nError: The stack is full! Program will exit. ");
	}
	
}
