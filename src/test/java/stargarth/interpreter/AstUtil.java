package stargarth.interpreter;

import stargarth.parser.AbstractSyntaxTree;

public class AstUtil {

	private AstUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static AbstractSyntaxTree createSimpleAst(String leftValue, String operator, String rightValue) {
		AbstractSyntaxTree ast = new AbstractSyntaxTree(operator);
		AbstractSyntaxTree leftNode = new AbstractSyntaxTree(leftValue);
		AbstractSyntaxTree rightNode = new AbstractSyntaxTree(rightValue);
		ast.setLeftChild(leftNode);
		ast.setRightChild(rightNode);
		return ast;
	}

}
