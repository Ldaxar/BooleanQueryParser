package stargarth.interpreter;

import java.util.List;
import java.util.Map;

import stargarth.parser.AbstractSyntaxTree;

public class Interpreter {
	private IResolver resolver;

	public Interpreter() {
		this.resolver = new Resolver(null);
	}

	public Interpreter(IResolver resolver) {
		this.resolver = resolver;
	}

	public Boolean interpretAst(AbstractSyntaxTree ast, Map<String, Object> valueMap) throws MissingValueException, WrongDataTypeException {
		resolver.setValueMap(valueMap);
		return (Boolean) resolveAst(ast);
	}

	public Object resolveAst(AbstractSyntaxTree ast) throws MissingValueException, WrongDataTypeException {
		Object result = null;
		Object leftValue = null, rightValue = null;
		Number leftNumber, rightNumber;

		String data = ast.getData();
		if (ast.getLeftChild() != null) leftValue = resolveAst(ast.getLeftChild());
		if (ast.getRightChild() != null) rightValue = resolveAst(ast.getRightChild());
		try {
			switch (data) {
			case "->":
				@SuppressWarnings("unchecked")
				List<Object> rightValues = (List<Object>) rightValue;
				result = rightValues.contains(leftValue);
				break;
			case ">=":
				leftNumber = (Number) leftValue;
				rightNumber = (Number) rightValue;
				result = leftNumber.doubleValue() >= rightNumber.doubleValue();
				break;
			case "<=":
				leftNumber = (Number) leftValue;
				rightNumber = (Number) rightValue;
				result = leftNumber.doubleValue() <= rightNumber.doubleValue();
				break;
			case ">":
				leftNumber = (Number) leftValue;
				rightNumber = (Number) rightValue;
				result = leftNumber.doubleValue() > rightNumber.doubleValue();
				break;
			case "<":
				leftNumber = (Number) leftValue;
				rightNumber = (Number) rightValue;
				result = leftNumber.doubleValue() < rightNumber.doubleValue();
				break;
			case "==":
				result = leftValue.equals(rightValue);
				break;
			case "^":
				result = (Boolean) leftValue ^ (Boolean) rightValue;
				break;
			case "&&":
				result = (Boolean) leftValue && (Boolean) rightValue;
				break;
			case "||":
				result = (Boolean) leftValue || (Boolean) rightValue;
				break;
			case "!=":
				result = !(leftValue.equals(rightValue));
				break;
			default:
				result = resolver.resolveValue(data);
				break;
			}
		} catch (ClassCastException | NullPointerException e) {
			throw new WrongDataTypeException(constructErrorMessage(ast, leftValue, rightValue));
		}

		return result;
	}

	public void setValueMap(Map<String, Object> valueMap) {
		this.resolver.setValueMap(valueMap);
	}

	public void setResolver(IResolver resolver) {
		this.resolver = resolver;
	}
	
	private String constructErrorMessage(AbstractSyntaxTree nodeInScope, Object leftValue, Object rightValue) {
		StringBuffer msg = new StringBuffer();
		if (nodeInScope != null ) {
			AbstractSyntaxTree leftChild = nodeInScope.getLeftChild(), righChild = nodeInScope.getRightChild();
			String operator = nodeInScope.getData();
			if (leftChild == null || righChild == null || operator == null) msg.append("Something went terribly wrong");
			else {
				boolean causeFound = false;
				String leftName = leftChild.getData(), rightName = righChild.getData();
				msg.append("It's not possible to compute following operation: "
						+leftName+" "+operator+" "+rightName);
				msg.append("\n");
				msg.append("\t");
				msg.append("Reason: ");
				if (leftValue == null) {
					msg.append(leftName);
					msg.append(" is null ") ;
					causeFound = true;
				}
				if (rightValue == null) {
					if (leftValue == null) msg.append(" and ");
					msg.append(rightName);
					msg.append(" is null") ;
					causeFound = true;
				}
				if (causeFound) return msg.toString();
				else {
					msg.append(leftName);
					msg.append(" type is "+leftValue.getClass());
					msg.append(" and ");
					msg.append(rightName);
					msg.append(" type is "+rightValue.getClass());
				}
			}
			
		}
		else msg.append("Something went terribly wrong");
		return msg.toString();
	}

}
