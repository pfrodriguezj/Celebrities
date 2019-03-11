package code;

import java.io.IOException;
import java.util.List;

public interface ICelebSource {

	public static final String COMMA_DELIMITER = ",";
	public List<List<Boolean>> readSource(String source) throws Exception;
}
