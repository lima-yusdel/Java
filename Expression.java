package app;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import structures.Stack;

public class Expression {

	public static String delims = " \t*+-/()[]";
			
    /**
     * Populates the vars list with simple variables, and arrays lists with arrays
     * in the expression. For every variable (simple or array), a SINGLE instance is created 
     * and stored, even if it appears more than once in the expression.
     * At this time, values for all variables and all array items are set to
     * zero - they will be loaded from a file in the loadVariableValues method.
     * By Yusdel Lima Lorenzo
     * @param expr The expression
     * @param vars The variables array list - already created by the caller
     * @param arrays The arrays array list - already created by the caller
     */
    public static void 
    makeVariableLists(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays){
       		
    		String temp = null;
    		StringBuilder stringBuild = new StringBuilder();
    		
    		int length = expr.length();  		
    		
    		for(int i = 0; i < length;i++)
    		{
	    		if(Character.isAlphabetic(expr.charAt(i)))
				{
	    			temp = stringBuild.append(expr.charAt(i)).toString();    //builds a string of the names
				}
	    		else
	    		{
	    			if(expr.charAt(i) == '[')
	    			{
	    				if(temp != null)
	    				{
	    					Array arrayObj = new Array(null);			//checks if the name belongs to an array
	    					arrayObj.name = temp;
	    					if(!arrays.contains(arrayObj))
	    					{
	    					arrays.add(arrayObj);
	    					}
	    					stringBuild.delete(0, stringBuild.length());
	    					temp = null;
	    				}
	    			}
	    			else if(temp != null)
	    			{
	    				  Variable varObj = new Variable(null);
			              varObj.name = temp;							//if its not an array than its a variable name
			              if(!vars.contains(varObj))
			              {
			              vars.add(varObj);
			              }
			              stringBuild.delete(0, stringBuild.length());
			              temp = null;
	    				}
	    			}
	    				
	    			
	    		 }
    		if(temp != null)
			{
    			  Variable varObj = new Variable(null);
				  varObj.name = temp;
				  if(!vars.contains(varObj))
	              {
				  vars.add(varObj);
	              }
	              stringBuild.delete(0, stringBuild.length());
	              temp = null;
			}
    		
}
    
    /**
     * Loads values for variables and arrays in the expression
     * 
     * @param sc Scanner for values input
     * @throws IOException If there is a problem with the input 
     * @param vars The variables array list, previously populated by makeVariableLists
     * @param arrays The arrays array list - previously populated by makeVariableLists
     */
    public static void 
    loadVariableValues(Scanner sc, ArrayList<Variable> vars, ArrayList<Array> arrays) 
    throws IOException {
        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine().trim());
            int numTokens = st.countTokens();
            String tok = st.nextToken();
            Variable var = new Variable(tok);
            Array arr = new Array(tok);
            int vari = vars.indexOf(var);
            int arri = arrays.indexOf(arr);
            if (vari == -1 && arri == -1) {
            	continue;
            }
            int num = Integer.parseInt(st.nextToken());
            if (numTokens == 2) { // scalar symbol
                vars.get(vari).value = num;
            } else { // array symbol
            	arr = arrays.get(arri);
            	arr.values = new int[num];
                // following are (index,val) pairs
                while (st.hasMoreTokens()) {
                    tok = st.nextToken();
                    StringTokenizer stt = new StringTokenizer(tok," (,)");
                    int index = Integer.parseInt(stt.nextToken());
                    int val = Integer.parseInt(stt.nextToken());
                    arr.values[index] = val;              
                }
            }
        }
    }
    /**
     * 
     * @param String - revieves a string and computes the string recursivly
     * @returns a float which is the result of the computation on the string
	 * By Yusdel Lima Lorenzo
     */
    private static float doMath(String temp) 
    {
    	
    	StringBuilder Abuilder = new StringBuilder();
    	StringBuilder Bbuilder = new StringBuilder();
  
		int indexMultiply = 0;
		int indexDivide = 0;				//these are the indexs of the operators
		int indexAdd = 0;
		int indexSub = 0;
		
		String preOp = null;			//string holders for the operations
		String postOp = null;
		int startPre = 0;
		int endPre = 0;
		
		String stringDigits = temp;
		float a = 0;					//float varaibles to actually compute the values
		float b = 0;
		char operator = '0';
		float Result = 0;
		boolean coin = false;
		
		
		stringDigits= stringDigits.replaceAll("\\--", "+");
		stringDigits= stringDigits.replaceAll("\\+-", "-");		//replaces the respective pattern with the appropriate operator
		
		
		
		         
			for(int i = 0; i < stringDigits.length(); i++)
			{
				if(indexSub == 0)
				{
					if(stringDigits.charAt(i) == '-')
					{
						indexSub = i;						//finds the index of the operators
					}
				}
				if(indexAdd == 0)
				{
					if(stringDigits.charAt(i) == '+')
					{
						indexAdd = i;
					}
				}
				if(indexDivide == 0)
				{
					if(stringDigits.charAt(i) == '/')
					{
						indexDivide = i;
					}
				}
				if(indexMultiply == 0)
				{
					if(stringDigits.charAt(i) == '*')
					{
						indexMultiply = i;
					}
				}
			}
				
				if(indexMultiply != 0 && indexDivide != 0)
				{
					operator = '/';								//simulates order of operations and sets the operator for the switch statement below
				}
				if(indexMultiply == 0 && indexDivide != 0)
				{
					operator = '/';
				}
				if(indexMultiply != 0 && indexDivide == 0)
				{
					operator = '*';
				}
					if(indexMultiply == 0 && indexDivide == 0)
					{
						if(indexAdd != 0 && indexSub != 0)
						{
							operator = '+';
						}
						if(indexAdd == 0 && indexSub != 0)
						{
							operator = '-';
						}
						if(indexAdd != 0 && indexSub == 0)
						{
							operator = '+';
						}	
					}
				
				
				
		
			switch (operator)				//revieves an operator then goes to that case
			{
				case '*':
				{
					for(int i = indexMultiply-1; i >= 0 ;i--)
		    		{
		    			if(Character.isDigit(stringDigits.charAt(i)))
		    			{
		    				Abuilder.append(stringDigits.charAt(i)).toString();		//uses a for loop backwards and builds the number before the operator
		    				
		    				startPre = i;
		    			}
		    			if(i != 0)
		    			{
		    				if(stringDigits.charAt(i-1) == '.')
		    				{
		    					Abuilder.append(stringDigits.charAt(i-1)).toString();	
		    				}
		    				if(stringDigits.charAt(i-1) == '-')
		    				{
		    					Abuilder.append(stringDigits.charAt(i-1)).toString();
		    					startPre = i;
		    					coin = true;
		    				}
		    				if(!Character.isDigit(stringDigits.charAt(i-1)) && stringDigits.charAt(i-1) != '.')
		    				{
		    					
		    					break;
		    				}
		    			}
		    			if(i == 0 && stringDigits.charAt(i) == '-' )
		    			{
		    				Abuilder.append(stringDigits.charAt(i)).toString();		//checks for a negative sign also
		    			}
		    		}
		    			
					for(int i = indexMultiply+1; i < stringDigits.length() ;i++)
		    		{
						if(stringDigits.charAt(i)== '-')
						{
							Bbuilder.append(stringDigits.charAt(i)).toString(); 
						}
		    			if(Character.isDigit(stringDigits.charAt(i)))				//uses a for loop foward and builds the number after the operator
		    			{
		    				Bbuilder.append(stringDigits.charAt(i)).toString();	 
		    				
		    				endPre = i;
		    			}
		    			
		    			if(i != stringDigits.length()-1)
		    			{
		    				if(stringDigits.charAt(i+1) == '.')							//checks for a decimal and a negaive sign also
		    				{
		    					Bbuilder.append(stringDigits.charAt(i+1)).toString();	
			    				
		    				}
		    				if(!Character.isDigit(stringDigits.charAt(i+1)) && stringDigits.charAt(i+1) != '.')
		    				{
		    					
		    					break;
		    				}
		    			}
		    		}
					Abuilder.reverse();					//reverses becuase it was appended built backwards
				
					preOp = Abuilder.toString();
					postOp = Bbuilder.toString();
					a = Float.parseFloat(preOp);			
					b = Float.parseFloat(postOp);
					Abuilder.delete(0, Abuilder.length());
					Bbuilder.delete(0, Bbuilder.length());
					Result = a*b;
					Result = (float) (Math.round(Result * 10.0)/ 10.0);
					if(coin)
					{
					stringDigits = stringDigits.substring(0, startPre-1)+Result+ stringDigits.substring(endPre+1, stringDigits.length());
					}
					else
					{
						stringDigits = stringDigits.substring(0, startPre)+Result+ stringDigits.substring(endPre+1, stringDigits.length());
					}
					break;
				}
				
				case '/':
				{
					for(int i = indexDivide-1; i >= 0 ;i--)
		    		{
		    			if(Character.isDigit(stringDigits.charAt(i)))
		    			{
		    				Abuilder.append(stringDigits.charAt(i)).toString();		//uses a for loop backwards and builds the number before the operator
		    				
		    				startPre = i;
		    			}
		    			if(i != 0)
		    			{
		    				if(stringDigits.charAt(i-1) == '.')
		    				{
		    					Abuilder.append(stringDigits.charAt(i-1)).toString();	
		    				}
		    				if(stringDigits.charAt(i-1) == '-')
		    				{
		    					Abuilder.append(stringDigits.charAt(i-1)).toString();		//checks for a negative sign also
		    					startPre = i;
		    					coin = true;
		    				}
		    				if(!Character.isDigit(stringDigits.charAt(i-1)) && stringDigits.charAt(i-1) != '.')
		    				{
		    					
		    					break;
		    				}
		    			}
		    			if(i == 0 && stringDigits.charAt(i) == '-' )
		    			{
		    				Abuilder.append(stringDigits.charAt(i)).toString();
		    			}
		    		}
		    			
					for(int i = indexDivide+1; i < stringDigits.length() ;i++)
		    		{
						if(stringDigits.charAt(i)== '-')
						{
							Bbuilder.append(stringDigits.charAt(i)).toString(); 
						}
		    			if(Character.isDigit(stringDigits.charAt(i)))
		    			{
		    				Bbuilder.append(stringDigits.charAt(i)).toString();	 	//uses a for loop foward and builds the number after the operator
		    				
		    				endPre = i;
		    			}
		    			
		    			if(i != stringDigits.length()-1)
		    			{
		    				if(stringDigits.charAt(i+1) == '.')
		    				{
		    					Bbuilder.append(stringDigits.charAt(i+1)).toString();	
			    				
		    				}
		    				if(!Character.isDigit(stringDigits.charAt(i+1)) && stringDigits.charAt(i+1) != '.')
		    				{
		    					
		    					break;
		    				}
		    			}
		    		}
					Abuilder.reverse();		//reverses becuase it was appended built backwards
					
					preOp = Abuilder.toString();
					postOp = Bbuilder.toString();
					
					a = Float.parseFloat(preOp);
					b = Float.parseFloat(postOp);
					Abuilder.delete(0, Abuilder.length());
					Bbuilder.delete(0, Bbuilder.length());
					Result = a/b;
					
					if(coin)
					{
					stringDigits = stringDigits.substring(0, startPre-1)+Result+ stringDigits.substring(endPre+1, stringDigits.length());
					}
					else
					{
						stringDigits = stringDigits.substring(0, startPre)+Result+ stringDigits.substring(endPre+1, stringDigits.length());
					}
					
					break;
				}
				
				case '+':
				{
					for(int i = indexAdd-1; i >= 0 ;i--)
		    		{
		    			if(Character.isDigit(stringDigits.charAt(i)))
		    			{
		    				Abuilder.append(stringDigits.charAt(i)).toString();		//uses a for loop backwards and builds the number before the operator
		    				
		    				startPre = i;
		    			}
		    			if(i != 0)
		    			{
		    				if(stringDigits.charAt(i-1) == '.')
		    				{
		    					Abuilder.append(stringDigits.charAt(i-1)).toString();	
		    				}
		    				if(stringDigits.charAt(i-1) == '-')
		    				{
		    					Abuilder.append(stringDigits.charAt(i-1)).toString();		//checks for a negative sign also
		    					startPre = i;
		    					coin = true;
		    				}
		    				if(!Character.isDigit(stringDigits.charAt(i-1)) && stringDigits.charAt(i-1) != '.')
		    				{
		    					
		    					break;
		    				}
		    			}
		    			if(i == 0 && stringDigits.charAt(i) == '-' )
		    			{
		    				Abuilder.append(stringDigits.charAt(i)).toString();
		    			}
		    		}
		    			
					for(int i = indexAdd+1; i < stringDigits.length() ;i++)
		    		{
		    			if(Character.isDigit(stringDigits.charAt(i)))
		    			{
		    				Bbuilder.append(stringDigits.charAt(i)).toString();	 	//uses a for loop foward and builds the number after the operator
		    				
		    				endPre = i;
		    			}
		    			
		    			if(i != stringDigits.length()-1)
		    			{
		    				if(stringDigits.charAt(i+1) == '.')
		    				{
		    					Bbuilder.append(stringDigits.charAt(i+1)).toString();	
			    				
		    				}
		    				if(!Character.isDigit(stringDigits.charAt(i+1)) && stringDigits.charAt(i+1) != '.')
		    				{
		    					
		    					break;
		    				}
		    			}
		    		}
		    	
					Abuilder.reverse();		//reverses becuase it was appended built backwards
					
					preOp = Abuilder.toString();
					postOp = Bbuilder.toString();
					a = Float.parseFloat(preOp);
					b = Float.parseFloat(postOp);
					Abuilder.delete(0, Abuilder.length());
					Bbuilder.delete(0, Bbuilder.length());
					Result = a+b;
					Result = (float) (Math.round(Result * 10.0)/ 10.0);
					if(coin)
					{
					stringDigits = stringDigits.substring(0, startPre-1)+Result+ stringDigits.substring(endPre+1, stringDigits.length());
					}
					else
					{
						stringDigits = stringDigits.substring(0, startPre)+Result+ stringDigits.substring(endPre+1, stringDigits.length());
					}
					break;
				}
				
				case '-':
				{
					for(int i = indexSub-1; i >= 0 ;i--)
		    		{
		    			if(Character.isDigit(stringDigits.charAt(i)))
		    			{
		    				Abuilder.append(stringDigits.charAt(i)).toString();		//uses a for loop backwards and builds the number before the operator
		    				
		    				startPre = i;
		    			}
		    			if(i != 0)
		    			{
		    				if(stringDigits.charAt(i-1) == '.')
		    				{
		    					Abuilder.append(stringDigits.charAt(i-1)).toString();	
		    				}
		    				if(stringDigits.charAt(i-1) == '-')
		    				{
		    					Abuilder.append(stringDigits.charAt(i-1)).toString();		//checks for a negative sign also
		    					startPre = i;
		    					coin = true;
		    				}
		    				if(!Character.isDigit(stringDigits.charAt(i-1)) && stringDigits.charAt(i-1) != '.')
		    				{
		    					
		    					break;
		    				}
		    			}
		    			if(i == 0 && stringDigits.charAt(i) == '-' )
		    			{
		    				Abuilder.append(stringDigits.charAt(i)).toString();
		    			}
		    		}
		    			
					for(int i = indexSub+1; i < stringDigits.length() ;i++)
		    		{
		    			if(Character.isDigit(stringDigits.charAt(i)))
		    			{
		    				Bbuilder.append(stringDigits.charAt(i)).toString();	 
		    				
		    				endPre = i;
		    			}
		    			
		    			if(i != stringDigits.length()-1)
		    			{
		    				if(stringDigits.charAt(i+1) == '.')
		    				{
		    					Bbuilder.append(stringDigits.charAt(i+1)).toString();		//uses a for loop foward and builds the number after the operator
			    				
		    				}
		    				if(!Character.isDigit(stringDigits.charAt(i+1)) && stringDigits.charAt(i+1) != '.')
		    				{
		    					
		    					break;
		    				}
		    			}
		    		}
					Abuilder.reverse();		//reverses becuase it was appended built backwards
					
					preOp = Abuilder.toString();
					postOp = Bbuilder.toString();
					a = Float.parseFloat(preOp);
					b = Float.parseFloat(postOp);
					Abuilder.delete(0, Abuilder.length());
					Bbuilder.delete(0, Bbuilder.length());
					Result = a-b;
					Result = (float) (Math.round(Result * 10.0)/ 10.0);
					if(coin)
					{
					stringDigits = stringDigits.substring(0, startPre-1)+Result+ stringDigits.substring(endPre+1, stringDigits.length());
					}
					else
					{
						stringDigits = stringDigits.substring(0, startPre)+Result+ stringDigits.substring(endPre+1, stringDigits.length());
					}
					
					indexSub = 0;
					break;
				}
				default:
				{
					Result = Float.parseFloat(stringDigits);
					stringDigits = String.valueOf(Result);
					break;
				}
			}
		
		String Regex = "-?\\d+.\\d+";		//may contain digits such as 3 or 3.0 or -3 or -3.0
		if(stringDigits.matches(Regex))
		{			
			Result = Float.parseFloat(stringDigits);
			
		}
		else if(!temp.matches(Regex))	
		{
			Result = doMath(stringDigits);	//if the result is not a single digit then recursivly call this function
		}
		
		return Result;
    }
	/**
	 * revieves a string from evaluate with possible variables and replaces the names with their values 
     * 
     * @param String temp - a string with variables and or digits
	 * @param ArrayList<Variable> vars - the array list with the vars from makeVariableLists method
	 * @param ArrayList<Array> arrays - the array list with the arrays from the makeVariableLists method 
     * @returns String - a string with only digits inside of it and so that the doMath method can compute the answer
	 * By Yusdel Lima Lorenzo
     */
    private static String replaceVariables(String temp, ArrayList<Variable> vars, ArrayList<Array> arrays) 
    { 	
    	StringBuilder builder = new StringBuilder();
    	Stack<Object> intStack = new Stack<Object>();
    	String checkVariable= null;
    	String checkDigit = null;
    	String stringDigits = null;
    	boolean floatH = false;
    	
    	int variableValue = 0;
    	float digitValue = 0;
    	
    	
    	
		for(int i = 0; i < temp.length(); i++)
		{
			if(Character.isAlphabetic(temp.charAt(i)))
			{
				checkVariable = builder.append(temp.charAt(i)).toString();		//builds the name for the variable
			}
			else if(temp.charAt(i) != '*' && temp.charAt(i) != '/' &&  temp.charAt(i) != '-' && temp.charAt(i) != '+' )
			{
				checkDigit = builder.append(temp.charAt(i)).toString();		//builds the string of digits
				floatH = true;
			}
			else
			{
				if(floatH)
				{
					if(checkDigit != null)
					{
						digitValue = Float.valueOf(checkDigit);
						intStack.push(digitValue);
		    			builder.delete(0, builder.length());
		    			checkDigit = null;	
					}
				}
				else if(!floatH)
				{
					if(checkDigit != null)
					{
						digitValue = Float.valueOf(checkDigit);
						intStack.push(digitValue);
		    			builder.delete(0, builder.length());
		    			checkDigit = null;	
					}
				}
				
				if(checkVariable != null)
				{
						variableValue = vars.get(vars.indexOf(new Variable(checkVariable))).value;
		    			intStack.push(variableValue);
		    			builder.delete(0, builder.length());
						checkVariable = null;	
				}
				if(temp.charAt(i) == '*' || temp.charAt(i) == '/' || temp.charAt(i) == '-' ||temp.charAt(i) == '+' )		//pushes everything into a stack
				{
					intStack.push(temp.charAt(i));
				}
			}
		}
		if(checkVariable != null)
		{
			variableValue = vars.get(vars.indexOf(new Variable(checkVariable))).value;
			intStack.push(variableValue);
			builder.delete(0, builder.length());
			checkVariable = null;
		}
		if(checkDigit != null)
		{
			digitValue = Float.valueOf(checkDigit);
			intStack.push(digitValue);
			builder.delete(0, builder.length());
			checkDigit = null;	
		}
		
			intStack = reverseStack(intStack);		//reverses the stack to can pop in correct order
			
			
			while(!intStack.isEmpty())
			{
				stringDigits = builder.append(intStack.pop()).toString();		//builds the resulting string with no variables inside of it
			}
			
			
		return stringDigits;
		
	}
	/**
     * Evaluates the expression.
     * By Yusdel Lima Lorenzo
     * @param vars The variables array list, with values for all variables in the expression
     * @param arrays The arrays array list, with values for all array items
     * @return Result of evaluation
     */
    public static float evaluate(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) 
    {
    	float result = 0;
    	int openIndex = 0;
    	int closedIndex = 0;
    	String temp = null;
    	int counter = 0;
    	int calls = 0;
    	boolean array = false;
    	boolean check = false;
    	Stack<Character> stackObj = new Stack<Character>();    	
    	
    	expr= expr.replaceAll(" ", "");
    	expr= expr.replaceAll("\t", "");
    	
    	
    	for(int i = 0; i < expr.length(); i++)
    	{
    		check= true;
    		stackObj.push(expr.charAt(i));
    		if(expr.charAt(i) == '(' || expr.charAt(i) == '[')
    		{
    			if(expr.charAt(i) == '[')				//finds the inner most open ( or [
    			{
    				array = true;
    			}
      			openIndex = i;
      			counter++;
    		}
    		
    	}
    	for(int i = openIndex; i< expr.length(); i++)
    	{
    		if(expr.charAt(i) == ')' || expr.charAt(i) == ']')
    		{
    				if(closedIndex == 0)
    				{
    					closedIndex = i;			//after finding the inner most ( or [ finds the matching closing index
    					break;
    				}
    				
    		}
    	}
    	
    	
    	int innerMostOpen = openIndex+1;
    	int innerMostClose = closedIndex;
    	
    	String digitRegex = "^\\-?\\d*\\.?\\d*";		//may contain digits such as 3 or 3.0 or -3 or -3.0
		if(!expr.matches(digitRegex))
		{		
			if(openIndex != 0 && closedIndex != 0)
			{
				temp = expr.substring(innerMostOpen, innerMostClose);
				temp = replaceVariables(temp, vars, arrays);
				result = doMath(temp);
				expr = expr.substring(0, innerMostOpen-1)+result+ expr.substring(innerMostClose+1, expr.length());		//updates the expr string with the result of math
				check = false;
			}
			
		}
    	
    	StringBuilder builder = new StringBuilder();
    	String buildName = null;
    	int buildCounter = 0;
    	int i = 0;
    	int startHere = innerMostOpen-2;
    	
    	if(calls < counter)  
    	{
	    		for(i = startHere; i >= 0 ;i--)
	    		{
	    			if(Character.isAlphabetic(expr.charAt(i)))
	    			{
	    				buildName = builder.append(expr.charAt(i)).toString();
	    				buildCounter++;
	    			}
	    			else if(!Character.isAlphabetic(expr.charAt(i)))
    				{
			    		
    					break;
    				}											//builds the array name and gets the value of the array at the index from result
	    		}
	    		
	    		if(buildName != null)
	    		{
	    			builder.reverse();
	    			buildName = builder.toString();
	    			int startArray = innerMostOpen - buildCounter;
	    			result = (float) Math.floor(result);
		    		int arrayValue = arrays.get(arrays.indexOf(new Array(buildName))).values[(int) result];
		    		expr = expr.substring(0, startArray-1)+arrayValue+expr.substring(innerMostOpen+2, expr.length());		
		  
	    		}
	    		
    	}
    	if(counter == 0)
    	{
    		expr = replaceVariables(expr, vars, arrays);
    		result = doMath(expr);					//counter = 0 means that there are no [ or ( so just do the math and find the result
    		expr = String.valueOf(result);
    		
    	}
    	if(counter == 1 && !array && check)
    	{
    		temp = expr.substring(openIndex+1, closedIndex);
			temp = replaceVariables(temp, vars, arrays);		//if there are no arrays but (
			result = doMath(temp);
			 result = (float) (Math.round(result * 10.0)/ 10.0);
			 expr = expr.substring(0, innerMostOpen-1)+result+ expr.substring(innerMostClose+1, expr.length());
    	}
    	
    	
        if(expr.matches(digitRegex))
    	{			
    	  	result = Float.parseFloat(expr);		//if the expr string matches then store that into the result variable
    	  	 result = (float) (Math.round(result * 10.0)/ 10.0);
    	  	
    	}
    	else if(!expr.matches(digitRegex))
    	{
    	    result = evaluate(expr, vars, arrays);		//if it doesnt match then recursivly call the evaluate function
    	    calls++;
    	}
    		
			return result;				//only returns with an answer
    }
	/**
     * @param Stack<T> intStack -  takes a stack and reverses it 
     * @return a reversed stack with the same elements inside of it
	 * By Yusdel Lima Lorenzo
     */
    private static <T> Stack<T> reverseStack(Stack<T> intStack) 
    {
    	Stack<T> reversedStack = new Stack<T>();
        while (!intStack.isEmpty())
        {
            reversedStack.push(intStack.pop());		//reverses a stack by creating another stack
        }
        return reversedStack;
	}
}
