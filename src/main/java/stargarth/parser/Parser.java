package stargarth.parser;

import java.util.HashMap;
import java.util.List;

import stargarth.tokenizer.Token;

public class Parser {
	private final static HashMap<String, Integer> weightTable = new HashMap<>();
	// Weight base HAS TO BE BIGGER than biggest weight in weight table
	private final static int weightBase = 18;

	static {
		// variable has to have biggest weight in weight table
		weightTable.put("variable", 17);
		weightTable.put("->", 15);
		weightTable.put(">" , 9);
		weightTable.put("<" , 9);
		weightTable.put(">=", 9);
		weightTable.put("<=", 9);
		weightTable.put("==", 8);
		weightTable.put("!=", 8);
		weightTable.put("^" , 6);
		weightTable.put("&&", 4);
		weightTable.put("||", 3);
	}

	public AbstractSyntaxTree generateAst(List<Token> tokens) {
		int calculatedWeight = 0;
		int weight;
		AbstractSyntaxTree previousNode = null;
		AbstractSyntaxTree newNode;
		//Take an action for each token, based on its type
		for (Token token : tokens) {
			switch (token.getType()) {
			case 0: // logical operator
				weight = weightTable.get(token.getData()) + calculatedWeight;
				newNode = new AbstractSyntaxTree(token.getData());
				newNode.setWeight(weight);
				if (previousNode == null)
					previousNode = newNode;
				else {
					previousNode.bindNode(newNode);
					previousNode = newNode;
				}
				break;
			case 1: // variable
				weight = weightTable.get("variable") + calculatedWeight;
				newNode = new AbstractSyntaxTree(token.getData());
				newNode.setWeight(weight);
				if (previousNode == null)
					previousNode = newNode;
				else
					previousNode.bindNode(newNode);
				break;
			case 2: // open parentheses
				calculatedWeight += weightBase;
				break;
			case 3: // close parentheses
				calculatedWeight -= weightBase;
				break;
			}
		}
		return previousNode;
	}
}
