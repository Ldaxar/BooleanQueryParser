package stargarth.interpreter;

import java.time.Instant;
import java.util.Arrays;
import java.util.Map;

public class Resolver implements IResolver {

	private Map<String, Object> valueMap;

	public Resolver(Map<String, Object> valueMap) {
		this.valueMap = valueMap;
	}

	@Override
	public Object resolveValue(String data) throws MissingValueException {
		Object retVal = null;

		char firstChar = data.charAt(0);
		switch (firstChar) {
		case '\'':
			retVal = data.substring(1, data.length() - 1);
			break;
		case '[':
			data = data.substring(1, data.length() - 1);
			retVal = Arrays.asList(data.split(","));
			break;
		case '$':
			switch (data) {
			case "$true":
				retVal = true;
				break;
			case "$false":
				retVal = false;
				break;
			case "$null":
				retVal = null;
				break;
			case "$unixTime":
				retVal = Instant.now().getEpochSecond();
				break;
			default:
				throw new MissingValueException("Could not resolve special value "+data +". Please check your query");
			}
			break;
		case '-':
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			if (data.contains(".")) retVal = Double.valueOf(data) ;
			else retVal = Integer.valueOf(data) ;
			break;
		default:
			if (!valueMap.containsKey(data)) 
				throw new MissingValueException("Can't resolve variable "+data+" from valueMap");
			retVal = valueMap.get(data);
			break;
		}
		return retVal;
	}

	public void setValueMap(Map<String, Object> valueMap) {
		this.valueMap = valueMap;
	}

}
