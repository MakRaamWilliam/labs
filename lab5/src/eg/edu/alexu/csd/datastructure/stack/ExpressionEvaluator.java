package eg.edu.alexu.csd.datastructure.stack;

public class ExpressionEvaluator implements IExpressionEvaluator {

	public int priority (char a) {
	
		
		if(a== '(' || a== ')') return -1;
		else if(a == '*' || a=='/' || a=='%') return 2;
		else if(a== '+' || a=='-') return 1;
		else return 0;
	}
	
	 boolean isdigit(char a) {
		 if(a== '*' || a== '/' || a=='/' || a=='%' || a=='+' || a== '-' ) return false;
		 return true;
	 }
	
	
	public String infixToPostfix(String expression) {
		Stack s = new Stack(); String op = "";
		for(int i =0; i< expression.length();i++) {
			char a = expression.charAt(i);
		if ( (a== ' ' || a==',' || a=='$' || a=='&' ) == false) {
			if(priority(a) == 0) op = op + a + " ";
			else {
				if(s.isEmpty() ||( ( char)s.peek() == '(' ) || (a == '(' ))  s.push((char) a)  ;
				else if (a== ')' ) {
					while( (char)s.peek() != '(') op = op +(char) s.pop() + " ";
					s.pop();
				} else {
					while( !s.isEmpty() && priority(a) <= priority( (char) s.peek() )  ) {
						op = op + (char) s.pop() + " ";
					} s.push( (char ) a);
				}
				
			}
			
		}	} while (!s.isEmpty()) op=op+ (char) s.pop() + " ";
		
		
		return op;
	}

	@Override
	public int evaluate(String expression) {
		Stack s = new Stack();
		for(int i =0 ; i< expression.length() ;i++) {
			char a = expression.charAt(i);
			if(a != ' ') {
			if(isdigit(a)) s.push((int ) a -48); 
			else { if(s.size()<2) new  RuntimeException("Erorr expression");
				int x=  (int) s.pop(); int y =  (int) s.pop();
				if(a == '*') s.push( (int) ( y*x) );
				else if(a== '/' ) s.push( (int) ( y/x )  );
				else if (a== '%') s.push( (int) ( y%x )  );
				else if(a== '+' ) s.push( (int) ( y+x)  );
				else if(a== '-') s.push( (int) ( y-x )  );
			}
		}	}
		
		return (int) s.pop();
	}
	

}
