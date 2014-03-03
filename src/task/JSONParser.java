package task;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONParser {
	private ResultSet _results;
	private String _native;

	public JSONParser(RESTClient rc) {
		_native = rc.getLastResponse();
	}

	public void fromNative() {
		ObjectMapper mapper = new ObjectMapper().setVisibility(
				JsonMethod.FIELD, Visibility.ANY);
		mapper.configure(
				DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {

			// read from file, convert it to user class
			_results = mapper.readValue(_native, ResultSet.class);

		} catch (JsonGenerationException e) {
			if (task.Settings.DEBUG_MODE) {
				e.printStackTrace();
			} else {
				// notify debug service, pass
			}
		} catch (JsonMappingException e) {
			if (task.Settings.DEBUG_MODE) {
				e.printStackTrace();
			} else {
				// notify debug service, pass
			}
		} catch (JsonParseException e) {
			if (task.Settings.DEBUG_MODE) {
				e.printStackTrace();
			} else {
				// notify debug service, pass
			}
		} catch (IOException e) {
			if (task.Settings.DEBUG_MODE) {
				e.printStackTrace();
			} else {
				// notify debug service, pass
			}
		}

	}

	public String toNative() {
		// TODO - implememnt try -> catch
		return _results.toString();
	}

	public ResultSet getObject() {
		return _results;
	}
}
