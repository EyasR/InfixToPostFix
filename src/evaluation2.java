/* 
 * CS211 Assignment 05 (Stack Operations 2)
 * Eyas Rashid - rashid_e@hotmail.com
 * Student ID: 985 762 980
 * 2018.05.17 - Spring Quarter
 *
 * This Java program is designed to convert In-fix math notation into post-fix
 * and evaluate the post-fix notation
 */
import java.util.*;

public class evaluation2 {

	// This array contains mathematical statements that the program evaluate and
	// convert into post fix
	static String[] Statement = { "1+1+1+1+1", "(1 + 3) * (5 + 4)", "1 * 2 + 3 * 4", "1+2+3+4", "(2 * 2) / (3/3)" };

	public static void main(String[] args) {
		postFixConverter();
	}
	
	// this method converts  a mathematical expression from in-fix to post-fix notation.
	public static void postFixConverter() {
		for (int i = 0; i < Statement.length; i++) {// runs each statement in Statement array.

			Stack211<Character> charStack = new Stack211<>();

			String cStatement = Statement[i];// temporarily saves statements from array
			String postFix = "";
			int value = 0;

			System.out.println("In-fix notation  : " + cStatement);// prints mathematical expression.

			for (int j = 0; j < cStatement.length(); j++) {// loops through all characters in statement.
				char c = cStatement.charAt(j);
				
				if (c == ' ') {
				} else if (c >= '0' && c <= '9') {// if c 0-9 add to postFix String
					postFix += c;
				} else if (c == '(') { // if c = ( push add to stack.
					charStack.push(c);
				} else if (c == ')') { // if c = ) pop stack
					char test = 0;

					while (test != '(') { // while testing char != ( pop and add to postFix String
						test = charStack.pop();

						if (test != '(') {
							postFix += test;
						}
					}
				} else if (c == '+' || c == '-' || c == '*' || c == '/') {// if c is a math operation

					if (!charStack.isEmpty()) { // if stack is not empty compare stack top to math operation
						char charStackTop = (char) charStack.myStack[charStack.stackTop];

						if (charStackTop == '(') {// if stackTop == ( push c onto stack
							charStack.push(c);
						} else if (charStackTop == '+' || charStackTop == '-') {
							if (c == '*' || c == '/') {
								charStack.push(c);
							} else {
								char test = charStack.pop();
								charStack.push(c);
								postFix += test;
							}
						} else if (charStackTop == '*' || charStackTop == '/') {
							if (c == '+' || c == '-') {
								while (!charStack.isEmpty()) {
									char test = charStack.pop();
									postFix += test;
								}
								charStack.push(c);

							} else {
								charStack.push(c);
							}
						}
					} else {
						charStack.push(c);
					}
				}
			}
			
			while (!charStack.isEmpty()) {
				char test = charStack.pop();
				postFix += test;
			}

			System.out.println("Post-fix notation: " + postFix);

			postFixEvaluation(postFix);

		}
	}

	// this method evaluates post-fix notation mathematical expressions
	public static void postFixEvaluation(String postFix) {
		Stack211<Integer> intStack = new Stack211<>();
		int value = 0; 
		int tempValue = 0;
		int tempAdd = 0;
		int tempSub = 0;

		for (int k = 0; k < postFix.length(); k++) {//loops through postfix notation
			char c = postFix.charAt(k);

			if (c >= '0' && c <= '9') {// if char is 0-9 convert to integer and push to stack
				int v = c - '0';
				intStack.push(v);

			} else if (c == '+') { // if char is +, then pop stack
				int v1 = intStack.pop();

				if (intStack.isEmpty()) {//if stack is empty save v1 as tempAdd
					tempAdd = v1;
				} else {// if stack is not empty pop v2 and add v1+v2 and save the sum in the stack
					int v2 = intStack.pop();
					tempValue = v2 + v1;
					intStack.push(tempValue);
				}

			} else if (c == '-') { // if char is -, then pop stack
				int v1 = intStack.pop();

				if (intStack.isEmpty()) {//if stack is empty save v1 as tempSub
					tempSub = v1 - (2 * v1);
				} else {// if stack is not empty pop v2 and add v1-v2 and save the difference in the stack
					int v2 = intStack.pop();
					tempValue = v2 - v1;
					intStack.push(tempValue);
				}
			} else if (c == '*') { //if char is * pop stack twice and multiplies the values
				int v1 = intStack.pop();
				int v2 = intStack.pop();
				tempValue = v2 * v1;
				intStack.push(tempValue);// push product into stack

			} else if (c == '/') {//if char is / pop stack twice and divides the values
				int v1 = intStack.pop();
				int v2 = intStack.pop();
				tempValue = v2 / v1;
				intStack.push(tempValue);// push quotient into stack
			}
			if (tempAdd !=0 && tempSub != 0) {
				value = tempAdd + tempSub;
			} else if (tempAdd != 0) { // if tempAdd is not 0 calculate sum of tempAdd + tempValue
				value = tempAdd + tempValue;
			} else if (tempSub != 0) {// if tempSub is not 0 calculate difference of tempAdd - tempValue
				value = tempSub - tempValue;
			} else
			value = tempValue;
		}
		
		System.out.println("Evaluation       : " + value);
		System.out.println();
	}
}
