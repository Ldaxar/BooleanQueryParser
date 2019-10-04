package stargarth.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stargarth.interpreter.Interpreter;
import stargarth.interpreter.MissingValueException;
import stargarth.interpreter.Resolver;
import stargarth.interpreter.WrongDataTypeException;
import stargarth.parser.AbstractSyntaxTree;
import stargarth.parser.Parser;
import stargarth.tokenizer.SimpleTokenizer;
import stargarth.tokenizer.Token;

public class TimeBanchmarking {

	public static void main(String[] args) {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("A", false);
		valueMap.put("C", true);
		valueMap.put("D", true);
		valueMap.put("E", "elo");
		String query = "A || $true == (C && D) || $false";
	//	String query2 = "E -> [one,two,elo,three]";
		//String query = "A || B == (C && D) || E";
//		String query = "A || B == (C && D || (H != J)) || E";
//		String query = "A || B == (C && D || (H != J)) || E || A || B == (C && D || (H != J)) || E "
//				+ "|| A || B == (C && D || (H != J)) || E || A || B == (C && D || (H != J)) || E" + "|| A || B == (C && D || (H != J)) || E || A || B == (C && D || (H != J)) || E" + "|| A || B == (C && D || (H != J)) || E || A || B == (C && D || (H != J)) || E" + "|| A || B == (C && D || (H != J)) || E || A || B == (C && D || (H != J)) || E";
		
		
		SimpleTokenizer tk = new SimpleTokenizer();
		List<Token> tokens = tk.tokenize(query);
		//System.out.println("Tokens done");
		//printTokens(tokens);
		
		Parser p = new Parser();
		AbstractSyntaxTree n=p.generateAst(tokens);
		long startTime = System.nanoTime();
		Interpreter i = new Interpreter(new Resolver(valueMap));
		
		
		boolean b=false;
		try {
			b = i.interpretAst(n, valueMap);
		} catch (MissingValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		System.out.println("Result " + b);
		System.out.println("Execution time:"+(endTime-startTime));
		
	}

}
