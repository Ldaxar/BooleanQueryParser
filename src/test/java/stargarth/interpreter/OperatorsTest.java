package stargarth.interpreter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import stargarth.parser.AbstractSyntaxTree;

class OperatorsTest {

	@Test
	void orTest() throws MissingValueException, WrongDataTypeException {
		// A || B
		AbstractSyntaxTree ast = AstUtil.createSimpleAst("A", "||", "B");
		Interpreter interpreter = new Interpreter();
		Map<String, Object> valueMap = new HashMap<String, Object>();

		valueMap.put("A", true);
		valueMap.put("B", true);
		assertTrue(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", false);
		valueMap.put("B", true);
		assertTrue(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", true);
		valueMap.put("B", false);
		assertTrue(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", false);
		valueMap.put("B", false);
		assertFalse(interpreter.interpretAst(ast, valueMap));
	}

	@Test
	void andTest() throws MissingValueException, WrongDataTypeException {
		// A && B
		AbstractSyntaxTree ast = AstUtil.createSimpleAst("A", "&&", "B");
		Interpreter interpreter = new Interpreter();
		Map<String, Object> valueMap = new HashMap<String, Object>();

		valueMap.put("A", true);
		valueMap.put("B", true);
		assertTrue(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", false);
		valueMap.put("B", true);
		assertFalse(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", true);
		valueMap.put("B", false);
		assertFalse(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", false);
		valueMap.put("B", false);
		assertFalse(interpreter.interpretAst(ast, valueMap));
	}

	@Test
	void equalTest() throws MissingValueException, WrongDataTypeException {
		// A == B
		AbstractSyntaxTree ast = AstUtil.createSimpleAst("A", "==", "B");
		Interpreter interpreter = new Interpreter();
		Map<String, Object> valueMap = new HashMap<String, Object>();

		valueMap.put("A", "abc");
		valueMap.put("B", "abc");
		assertTrue(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", "abc");
		valueMap.put("B", "abd");
		assertFalse(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", 123);
		valueMap.put("B", 123);
		assertTrue(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", 123);
		valueMap.put("B", 124);
		assertFalse(interpreter.interpretAst(ast, valueMap));
	}

	@Test
	void notEqualTest() throws MissingValueException, WrongDataTypeException {
		// A != B
		AbstractSyntaxTree ast = AstUtil.createSimpleAst("A", "!=", "B");
		Interpreter interpreter = new Interpreter();
		Map<String, Object> valueMap = new HashMap<String, Object>();

		valueMap.put("A", "abc");
		valueMap.put("B", "abc");
		assertFalse(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", "abc");
		valueMap.put("B", "abd");
		assertTrue(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", 123);
		valueMap.put("B", 123);
		assertFalse(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", 123);
		valueMap.put("B", 124);
		assertTrue(interpreter.interpretAst(ast, valueMap));
	}

	@Test
	void inTestStaticArray() throws MissingValueException, WrongDataTypeException {
		// A -> B
		AbstractSyntaxTree ast = AstUtil.createSimpleAst("A", "->", "[aba,abc,abh]");
		Interpreter interpreter = new Interpreter();
		Map<String, Object> valueMap = new HashMap<String, Object>();

		valueMap.put("A", "abc");
		assertTrue(interpreter.interpretAst(ast, valueMap));

		valueMap.put("A", "abe");
		assertFalse(interpreter.interpretAst(ast, valueMap));
	}

	@Test
	void inTestDynamicArray() throws MissingValueException, WrongDataTypeException {
		// A -> B
		AbstractSyntaxTree ast = AstUtil.createSimpleAst("'abc'", "->", "B");
		Interpreter interpreter = new Interpreter();
		Map<String, Object> valueMap = new HashMap<String, Object>();
		List<String> dynamicArray = new ArrayList<String>();
		dynamicArray.add("efg");
		dynamicArray.add("abc");

		valueMap.put("B", dynamicArray);
		assertTrue(interpreter.interpretAst(ast, valueMap));

		dynamicArray = new ArrayList<String>();
		dynamicArray.add("efg");
		dynamicArray.add("abh");
		valueMap.put("B", dynamicArray);
		assertFalse(interpreter.interpretAst(ast, valueMap));
	}

	@Test
	void inTestNonStringArray() throws MissingValueException, WrongDataTypeException {
		// A -> B
		AbstractSyntaxTree ast = AstUtil.createSimpleAst("1234", "->", "B");
		Interpreter interpreter = new Interpreter();
		Map<String, Object> valueMap = new HashMap<String, Object>();
		List<Integer> dynamicArray = new ArrayList<Integer>();
		dynamicArray.add(1234);
		dynamicArray.add(12345);

		valueMap.put("B", dynamicArray);
		assertTrue(interpreter.interpretAst(ast, valueMap));

		dynamicArray = new ArrayList<Integer>();
		dynamicArray.add(123);
		dynamicArray.add(12345);
		valueMap.put("B", dynamicArray);
		assertFalse(interpreter.interpretAst(ast, valueMap));
	}

}
