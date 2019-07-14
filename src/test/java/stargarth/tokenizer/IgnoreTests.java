package stargarth.tokenizer;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class IgnoreTests {

	@Test
	void ignoresSpaces() {
		String queryString = "A ||  B ||    C";
		Tokenizer tokenizer = new SimpleTokenizer();
		
		List<Token> generatedTokens = tokenizer.tokenize(queryString);
		
		Assertions.assertThat(generatedTokens).usingFieldByFieldElementComparator().containsExactly(
				new Token("A", 1),
				new Token("||", 0),
				new Token("B", 1),
				new Token("||", 0),
				new Token("C", 1)
		);
	}
	
	@Test
	void ignoresTabs() {
		String queryString = "A	||		B		||					C";
		Tokenizer tokenizer = new SimpleTokenizer();
		
		List<Token> generatedTokens = tokenizer.tokenize(queryString);
		
		Assertions.assertThat(generatedTokens).usingFieldByFieldElementComparator().containsExactly(
				new Token("A", 1),
				new Token("||", 0),
				new Token("B", 1),
				new Token("||", 0),
				new Token("C", 1)
		);
	}
	
	@Test
	void ignoresNewLines() {
		String queryString = "A" +'\n' +'\n' +'\n' +'\n'
				+ "||" +'\r' +'\n'
				+ "B||"
				+ "C" +'\n';
		Tokenizer tokenizer = new SimpleTokenizer();
		
		List<Token> generatedTokens = tokenizer.tokenize(queryString);
		
		Assertions.assertThat(generatedTokens).usingFieldByFieldElementComparator().containsExactly(
				new Token("A", 1),
				new Token("||", 0),
				new Token("B", 1),
				new Token("||", 0),
				new Token("C", 1)
		);
	}
	
	@Test
	void ignoresAll() {
		String queryString = "A ||		B " +'\n'
				+ "||    " +'\r' +'\n'
				+ "  C";
		Tokenizer tokenizer = new SimpleTokenizer();
		
		List<Token> generatedTokens = tokenizer.tokenize(queryString);
		
		Assertions.assertThat(generatedTokens).usingFieldByFieldElementComparator().containsExactly(
				new Token("A", 1),
				new Token("||", 0),
				new Token("B", 1),
				new Token("||", 0),
				new Token("C", 1)
		);
	}

}
