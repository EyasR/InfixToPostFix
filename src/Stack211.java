public class Stack211 <T> {

	public int stackTop;// tracks what position objects are in stack.
	public Object[] myStack =  new Object [40];
	
	Stack211() {
		stackTop = -1;
	}
	//pushes char to stack.
	public void push(T c) {
		stackTop++;
		myStack[stackTop] = c;
	}
	//pops char from stack.
	public T pop() {
		Object c = myStack[stackTop];
		stackTop--;
		return (T) c;
		
	}
	// detects if contains objects or not.
	public boolean isEmpty() {
		if(stackTop<0)
			return true;
		else
			return false;
	}

}