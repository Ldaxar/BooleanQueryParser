package stargarth.tokenizer;

import java.util.List;

public interface Tokenizer {
	List<Token> tokenize(String queryString);
}
