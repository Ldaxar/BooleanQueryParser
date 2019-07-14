package stargarth.interpreter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import stargarth.parser.AbstractSyntaxTree;

class NumericOperatorsTest {

	@Test
	void moreThanDynamicValues() throws MissingValueException, WrongDataTypeException {
		AbstractSyntaxTree ast = AstUtil.createSimpleAst("A", ">", "B");
		Interpreter interpreter = new Interpreter();
		Map<String, Object> valueMap = new HashMap<String, Object>();

		valueMap.put("A", 123.45);
		valueMap.put("B", 123);
		assertTrue(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", 123);
		valueMap.put("B", 123.45);
		assertFalse(interpreter.interpretAst(ast, valueMap));
	}

	@Test
	void moreThanStaticValues() throws MissingValueException, WrongDataTypeException {
		AbstractSyntaxTree ast = AstUtil.createSimpleAst("123.45", ">", "123");
		Interpreter interpreter = new Interpreter();

		assertTrue(interpreter.interpretAst(ast, null));

		ast = AstUtil.createSimpleAst("123", ">", "123.45");
		assertFalse(interpreter.interpretAst(ast, null));
	}

	@Test
	void moreEqualThanDynamicValues() throws MissingValueException, WrongDataTypeException {
		AbstractSyntaxTree ast = AstUtil.createSimpleAst("A", ">=", "B");
		Interpreter interpreter = new Interpreter();
		Map<String, Object> valueMap = new HashMap<String, Object>();

		valueMap.put("A", 123);
		valueMap.put("B", 123);
		assertTrue(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", 123);
		valueMap.put("B", 123.45);
		assertFalse(interpreter.interpretAst(ast, valueMap));
	}

	@Test
	void moreEqualThanStaticValues() throws MissingValueException, WrongDataTypeException {
		AbstractSyntaxTree ast = AstUtil.createSimpleAst("123", ">=", "123");
		Interpreter interpreter = new Interpreter();

		assertTrue(interpreter.interpretAst(ast, null));

		ast = AstUtil.createSimpleAst("123", ">=", "123.45");
		assertFalse(interpreter.interpretAst(ast, null));
	}

	@Test
	void lessThanDynamicValues() throws MissingValueException, WrongDataTypeException {
		AbstractSyntaxTree ast = AstUtil.createSimpleAst("A", "<", "B");
		Interpreter interpreter = new Interpreter();
		Map<String, Object> valueMap = new HashMap<String, Object>();

		valueMap.put("A", 123.45);
		valueMap.put("B", 123);
		assertFalse(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", 123);
		valueMap.put("B", 123.45);
		assertTrue(interpreter.interpretAst(ast, valueMap));
	}

	@Test
	void lessThanStaticValues() throws MissingValueException, WrongDataTypeException {
		AbstractSyntaxTree ast = AstUtil.createSimpleAst("123.45", "<", "123");
		Interpreter interpreter = new Interpreter();

		assertFalse(interpreter.interpretAst(ast, null));

		ast = AstUtil.createSimpleAst("123", "<", "123.45");
		assertTrue(interpreter.interpretAst(ast, null));
	}

	@Test
	void lessEqualThanDynamicValues() throws MissingValueException, WrongDataTypeException {
		AbstractSyntaxTree ast = AstUtil.createSimpleAst("A", "<=", "B");
		Interpreter interpreter = new Interpreter();
		Map<String, Object> valueMap = new HashMap<String, Object>();

		valueMap.put("A", 123);
		valueMap.put("B", 123);
		assertTrue(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", 123);
		valueMap.put("B", 123.45);
		assertTrue(interpreter.interpretAst(ast, valueMap));
	}

	@Test
	void lessEqualThanStaticValues() throws MissingValueException, WrongDataTypeException {
		AbstractSyntaxTree ast = AstUtil.createSimpleAst("123", "<=", "123");
		Interpreter interpreter = new Interpreter();

		assertTrue(interpreter.interpretAst(ast, null));

		ast = AstUtil.createSimpleAst("123", "<=", "123.45");
		assertTrue(interpreter.interpretAst(ast, null));
	}

}
