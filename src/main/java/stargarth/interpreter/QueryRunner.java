package stargarth.interpreter;

import java.util.List;
import java.util.Map;

import stargarth.parser.AbstractSyntaxTree;
import stargarth.parser.Parser;
import stargarth.tokenizer.SimpleTokenizer;
import stargarth.tokenizer.Token;
import stargarth.tokenizer.Tokenizer;

public class QueryRunner {
	private  Tokenizer tokenizer;
	private  Parser parser;
	private  Interpreter interpreter;

	public QueryRunner() {
		tokenizer = new SimpleTokenizer();
		parser = new Parser();
		interpreter = new Interpreter();
	}
	
	public QueryRunner(Tokenizer tokenizer, Parser parser, Interpreter interpreter) {
		this.tokenizer = tokenizer;
		this.parser = parser;
		this.interpreter = interpreter;
	}

	public Boolean runQuery(String query, Map<String, Object> valueMap) throws MissingValueException, WrongDataTypeException {
		List<Token> tokens = tokenizer.tokenize(query);
		AbstractSyntaxTree ast = parser.generateAst(tokens);

		return interpreter.interpretAst(ast, valueMap);
	}

	public AbstractSyntaxTree generateAst(String query) {
		List<Token> tokens = tokenizer.tokenize(query);
		return parser.generateAst(tokens);
	}

	public Boolean interpretAst(AbstractSyntaxTree ast, Map<String, Object> valueMap) throws MissingValueException, WrongDataTypeException {
		return interpreter.interpretAst(ast, valueMap);
	}

}
