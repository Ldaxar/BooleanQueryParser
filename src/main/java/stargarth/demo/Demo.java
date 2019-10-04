package stargarth.demo;

import java.util.HashMap;
import java.util.Map;

import stargarth.interpreter.MissingValueException;
import stargarth.interpreter.QueryRunner;
import stargarth.interpreter.WrongDataTypeException;

public class Demo {

	public Demo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws MissingValueException, WrongDataTypeException {
		//Create data
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("A", false);
		valueMap.put("C", true);
		valueMap.put("D", true);
		valueMap.put("E", "elo");
		String query = "A || $true == (C && D) || $false";
		//Get instance of runner
		QueryRunner qr = new QueryRunner();
		Boolean result = qr.runQuery(query, valueMap);
		System.out.println(result);

	}

}
