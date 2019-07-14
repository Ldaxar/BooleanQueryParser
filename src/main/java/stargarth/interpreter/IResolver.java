package stargarth.interpreter;

import java.util.Map;

public interface IResolver {
	public Object resolveValue(String data) throws MissingValueException;

	public void setValueMap(Map<String, Object> valueMap);

}
