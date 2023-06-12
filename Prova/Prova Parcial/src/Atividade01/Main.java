package Atividade01;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Stack<Integer> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();

		stack1.push(1);
		stack1.push(10);
		stack1.push(3);

		stack2.push(3);
		stack2.push(10);

		//Testa o funcionamento do teste pelo tamanho
		if (teste(stack1, stack2) == true) {
			System.out.println("As pilhas são iguais!");
		} else {
			System.out.println("As pilhas não são iguais!");
		}

		
		stack2.push(1);

		//Faz o teste real
		if (teste(stack1, stack2) == true) {
			System.out.println("As pilhas são iguais!");
		} else {
			System.out.println("As pilhas não são iguais!");
		}

	}

	public static boolean teste(Stack<Integer> stack1, Stack<Integer> stack2) {
		
		if(stack1.size() != stack2.size()) {
			return false;
			
		}else {
			
			while(!stack1.isEmpty()){
	            int check = stack1.pop();
	            boolean teste = false;
	            Stack<Integer> stackAux = new Stack<>();
	            for (int i = stack2.size();!stack2.isEmpty();) {
	                if (check == stack2.peek()) {
	                    teste = true;
	                    stack2.pop();
	                    break;
	                }
	                stackAux.push(stack2.pop());
	                i--;
	            }
	            
	            while(!stackAux.isEmpty()) {
	            	stack2.push(stackAux.pop());
	            }
	            if(teste == false) {
	            	return false;
	            }
			}
		return true;
	}

	}
}
