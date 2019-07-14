package stargarth.tokenizer;

public class Token {
	private final String data;
	private final int type;
	
	public Token(String data, int type) {
		this.data = data;
		this.type = type;
	}
	public String getData() {
		return data;
	}
	
	/**
	 * 0 - logical operator
	 * 1 - data
	 * 2 - open paranthesis
	 * 3 - close paranthesis  
	 * @return
	 */
	public int getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return ("Token: "+data+" type: "+type);
	}
	
	
}
