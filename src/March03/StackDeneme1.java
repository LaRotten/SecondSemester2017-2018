package March03;

import java.util.Stack;

public class StackDeneme1 {
		
	public static void main(String []args) {
		
		Stack<String> stack=new Stack<String>();
		stack.push("Hey");
		stack.push("Yo");
		stack.push("Levo");
		printstack(stack);
		
		stack.clear();
		printstack(stack);
	}

	private static void printstack(Stack<String> a) {
		// TODO Auto-generated method stub
		if(a.isEmpty())		System.out.println("You don't have any elements in the stack");
		else 				System.out.printf("%s TOP\n",a);
	}
}
