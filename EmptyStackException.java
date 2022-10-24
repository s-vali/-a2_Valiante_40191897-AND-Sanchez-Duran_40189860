
public class EmptyStackException extends Exception {
	
	public EmptyStackException() {
		super("Error: The stack is empty! Program will exit. ");
		System.err.println("\nError: The stack is empty! Program will exit. ");
	}

}
