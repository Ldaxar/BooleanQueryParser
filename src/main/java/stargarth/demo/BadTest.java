package stargarth.demo;

import java.util.List;

import stargarth.parser.AbstractSyntaxTree;
import stargarth.parser.Parser;
import stargarth.tokenizer.SimpleTokenizer;
import stargarth.tokenizer.Token;

public class BadTest {

	public static void main(String[] args) {
		String query = "A > B >= (C < D) <= E";
//		String query = "A || B == (C && D || (H != J)) || E";
//		String query = "A || B == (C && D || (H != J)) || E || A || B == (C && D || (H != J)) || E "
//				+ "|| A || B == (C && D || (H != J)) || E || A || B == (C && D || (H != J)) || E" + "|| A || B == (C && D || (H != J)) || E || A || B == (C && D || (H != J)) || E" + "|| A || B == (C && D || (H != J)) || E || A || B == (C && D || (H != J)) || E" + "|| A || B == (C && D || (H != J)) || E || A || B == (C && D || (H != J)) || E";
		
		long startTime = System.currentTimeMillis();
		SimpleTokenizer tk = new SimpleTokenizer();
		List<Token> tokens = tk.tokenize(query);
		//System.out.println("Tokens done");
		//printTokens(tokens);
		
		Parser p = new Parser();
		AbstractSyntaxTree n=p.generateAst(tokens);
		long endTime = System.currentTimeMillis();
		System.out.println("Execution time:"+(endTime-startTime));
		printParsingTree(n);
		
	}
	
	@SuppressWarnings("unused")
	private static void printTokens(List<Token> tokens) {
		for (Token token : tokens) {
			System.out.println(token.toString());
		}
	}
	
	private static void printParsingTree(AbstractSyntaxTree n) {

		System.out.println("Parsing done");
		
		Printer print = new Printer();
		n=print.getRoot(n);
		System.out.println("Have root");
		print.printTree(n, "root", 0);
		
		System.out.println("Done");
	}

}
