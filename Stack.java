
public class Stack {
	
	/*Constants and Variables*/
	private int stack_array[];
	private int i, temp; //index of the stack_array
								//top
	
	/*Constructor*/
	public Stack(int arraySize) {
		super();
		stack_array = new int[arraySize];
		i = -1; //empty array occurs at 0th element, thus t = -1; 
	}
	
	/*Methods of a Stack*/
	public int size() {
		return (i+1); 	//because i indicates an array index, it actual size is +1. 
						//Also, will yield size = 0 for when array is empty
	}
	
	private boolean isEmpty() { //FIX THIS
		if(stack_array == null) {
			return true;
		} else if(i == -1 || i <= -1) {
			return true;
		} else {
			return false;
		}
	}
	
	public int top() throws EmptyStackException {
		if(isEmpty() == true) {
			throw new EmptyStackException(); //if i = -1
		} else {
			return stack_array[i]; //if i > -1
		}
	}
	
	public int pop() throws EmptyStackException {
		if(isEmpty() == true) {
			throw new EmptyStackException();
		} else {
			temp = stack_array[i];
			stack_array[i] = 0;
			i--;
			return temp; //i+1 because we had just decremented i by 1 in the previous line
		}
	}
	
	public void push(int element) throws FullStackException {
		if(i == stack_array.length-1) {
			throw new FullStackException();
		} else {
			i++;
			stack_array[i] = element;
		}
	}
	
	public void print() {
		System.out.print("Bottom of Stack ->   ");
		for(int i = 0; i < stack_array.length; i++) {
			System.out.print(stack_array[i] + "   ");
		}
		System.out.println(" <- Top of Stack ");
	}
}
