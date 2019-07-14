package stargarth.tokenizer;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TokenizerTests {

	@Test
	void basicQueryCorrect() {
		String queryString = "A||B||C";
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
	void paranthesisAsLastToken() {
		String queryString = "A||(B||C)";
		Tokenizer tokenizer = new SimpleTokenizer();
		
		List<Token> generatedTokens = tokenizer.tokenize(queryString);
		
		Assertions.assertThat(generatedTokens).usingFieldByFieldElementComparator().containsExactly(
				new Token("A", 1),
				new Token("||", 0),
				new Token("(",2),
				new Token("B", 1),
				new Token("||", 0),
				new Token("C", 1),
				new Token(")",3)
		);
	}
	
	@Test
	void paranthesisInMiddle() {
		String queryString = "A||(B||C)||E";
		Tokenizer tokenizer = new SimpleTokenizer();
		
		List<Token> generatedTokens = tokenizer.tokenize(queryString);
		
		Assertions.assertThat(generatedTokens).usingFieldByFieldElementComparator().containsExactly(
				new Token("A", 1),
				new Token("||", 0),
				new Token("(",2),
				new Token("B", 1),
				new Token("||", 0),
				new Token("C", 1),
				new Token(")",3),
				new Token("||", 0),
				new Token("E", 1)
		);
	}
	
	@Test
	void nestedParanthesis() {
		String queryString = "A||((B||C)||(B||D))||E";
		Tokenizer tokenizer = new SimpleTokenizer();
		
		List<Token> generatedTokens = tokenizer.tokenize(queryString);
		
		Assertions.assertThat(generatedTokens).usingFieldByFieldElementComparator().containsExactly(
				new Token("A", 1),
				new Token("||", 0),
				new Token("(",2),
				new Token("(",2),
				new Token("B", 1),
				new Token("||", 0),
				new Token("C", 1),
				new Token(")",3),
				new Token("||", 0),
				new Token("(",2),
				new Token("B", 1),
				new Token("||", 0),
				new Token("D", 1),
				new Token(")",3),
				new Token(")",3),
				new Token("||", 0),
				new Token("E", 1)
		);
	}
	
	@Test
	void checkAllOperators() {
		String queryString = "A>B>=C<D<=E==F!=G&&H||(J->L)^M";
		Tokenizer tokenizer = new SimpleTokenizer();
		
		List<Token> generatedTokens = tokenizer.tokenize(queryString);
		
		Assertions.assertThat(generatedTokens).usingFieldByFieldElementComparator().containsExactly(
				new Token("A", 1),
				new Token(">", 0),
				new Token("B", 1),
				new Token(">=", 0),
				new Token("C", 1),
				new Token("<", 0),
				new Token("D", 1),
				new Token("<=", 0),
				new Token("E", 1),
				new Token("==", 0),
				new Token("F", 1),
				new Token("!=", 0),
				new Token("G", 1),
				new Token("&&", 0),
				new Token("H", 1),
				new Token("||", 0),
				new Token("(", 2),
				new Token("J", 1),
				new Token("->", 0),
				new Token("L", 1),
				new Token(")", 3),
				new Token("^", 0),
				new Token("M", 1)
		);
	}

}
