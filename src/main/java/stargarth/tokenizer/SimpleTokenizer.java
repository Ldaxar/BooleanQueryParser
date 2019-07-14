package stargarth.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class SimpleTokenizer implements Tokenizer {

	public List<Token> tokenize(String queryString) {
		List<Token> tokens = new ArrayList<>();
		queryString = prepareForParsing(queryString);

		StringBuilder assembly = new StringBuilder();
		char currentChar, nextChar;
		boolean ignoreMode = false;
		for (int i = 0; i < queryString.length() - 1; i++) {
			currentChar = queryString.charAt(i);
			nextChar = queryString.charAt(i + 1);
			if (ignoreMode) {
				if (currentChar == '\\' && nextChar == '\'') {
					assembly.append(nextChar);
					i += 1;
					continue;
				} else if (currentChar == '\'')
					ignoreMode = false;
				assembly.append(currentChar);
			} else {
				switch (currentChar) {
				case '!':
					if (nextChar == '=') {
						assembly = addDataTokenAndResetAssembly(tokens, assembly);
						i += 1;
						tokens.add(new Token("" + currentChar + nextChar, 0));
					} else
						assembly.append(currentChar);
					break;
				case '>':
					if (nextChar == '=') {
						assembly = addDataTokenAndResetAssembly(tokens, assembly);
						i += 1;
						tokens.add(new Token("" + currentChar + nextChar, 0));
					} else {
						assembly = addDataTokenAndResetAssembly(tokens, assembly);
						tokens.add(new Token("" + currentChar, 0));
					}
					break;
				case '<':
					if (nextChar == '=') {
						assembly = addDataTokenAndResetAssembly(tokens, assembly);
						i += 1;
						tokens.add(new Token("" + currentChar + nextChar, 0));
					} else {
						assembly = addDataTokenAndResetAssembly(tokens, assembly);
						tokens.add(new Token("" + currentChar, 0));
					}
					break;
				case '^':
						assembly = addDataTokenAndResetAssembly(tokens, assembly);
						tokens.add(new Token("" + currentChar, 0));
					break;
				case '=':
					if (nextChar == '=') {
						assembly = addDataTokenAndResetAssembly(tokens, assembly);
						i += 1;
						tokens.add(new Token("" + currentChar + nextChar, 0));
					} else
						assembly.append(currentChar);
					break;
				case '-':
					if (nextChar == '>') {
						assembly = addDataTokenAndResetAssembly(tokens, assembly);
						i += 1;
						tokens.add(new Token("" + currentChar + nextChar, 0));
					} else
						assembly.append(currentChar);
					break;
				case '&':
					if (nextChar == '&') {
						assembly = addDataTokenAndResetAssembly(tokens, assembly);
						i += 1;
						tokens.add(new Token("" + currentChar + nextChar, 0));
					} else
						assembly.append(currentChar);
					break;
				case '|':
					if (nextChar == '|') {
						assembly = addDataTokenAndResetAssembly(tokens, assembly);
						i += 1;
						tokens.add(new Token("" + currentChar + nextChar, 0));
					} else
						assembly.append(currentChar);
					break;
				case '(':
					tokens.add(new Token("" + currentChar, 2));
					break;
				case ')':
					assembly = addDataTokenAndResetAssembly(tokens, assembly);
					tokens.add(new Token("" + currentChar, 3));
					break;
				case '\'':
					ignoreMode = true;
				default:
					assembly.append(currentChar);
					break;
				}
			}
		}
		currentChar = queryString.charAt(queryString.length() - 1);
		if (currentChar == ')') {
			assembly = addDataTokenAndResetAssembly(tokens, assembly);
			tokens.add(new Token("" + currentChar, 3));
		} else {
			assembly.append(currentChar);
			assembly = addDataTokenAndResetAssembly(tokens, assembly);
		}
		return tokens;
	}

	private StringBuilder addDataTokenAndResetAssembly(List<Token> tokens, StringBuilder assembly) {
		// If assembly is empty there is no point in adding empty token
		if (assembly.length() > 0) {
			tokens.add(new Token(assembly.toString(), 1));
			assembly = new StringBuilder();
		}
		return assembly;
	}

	// Remove new lines, tabs and spaces. They are really annoying in process of
	// parsing
	private String prepareForParsing(String queryString) {
		StringBuilder sb = new StringBuilder();
		char currentChar;
		// This flag helps to avoid removing spaces from static values
		// If something is between '' then spaces are fine.
		boolean outside = true;
		for (int i = 0; i < queryString.length(); i++) {
			currentChar = queryString.charAt(i);
			if (currentChar == '\'') {
				outside = !outside;
				sb.append(currentChar);
				continue;
			}
			if (outside) {
				if (currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '	')
					continue;
			}
			sb.append(currentChar);
		}
		return sb.toString();
	}
}
