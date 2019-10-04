package stargarth.demo;

import java.util.HashMap;
import java.util.Map;

import stargarth.interpreter.MissingValueException;
import stargarth.interpreter.QueryRunner;
import stargarth.interpreter.WrongDataTypeException;
import stargarth.parser.AbstractSyntaxTree;

public class DemoAST {

	public DemoAST() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws MissingValueException, WrongDataTypeException {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("A", false);
		valueMap.put("C", true);
		valueMap.put("D", true);
		valueMap.put("E", "elo");

		//Create query
		String query = "A || $true == (C && D) || $false";

		//Generate AST
		QueryRunner qr = new QueryRunner();
		AbstractSyntaxTree ast = qr.generateAst(query);
		//Use it to run query
		Boolean result = qr.runQuery(ast, valueMap);
		//true
		System.out.println(result);

	}

}
