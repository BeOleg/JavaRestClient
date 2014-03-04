package task;

public final class Settings {
	public static final String API_ROOT = "https://api.goeuro.com/api/v1/";
	public static final String LOCATIONS_ENDPOINT = "suggest/position/en/name/";
	public static final String FILE_EXTENSION = ".csv";
	public static final String FILE_PREFIX = "search_";
	public static final String[] FILE_HEADERS = new String[] { "_type", "_id",
			"name", "type", "latitude", "longitude" };
	public static final Boolean DEBUG_MODE = true;
	public static final int HTTP_STATUS_OK = 200;
	public static final int EXIT_CODE_ERROR = 1;
	public static final int EXIT_CODE_OK = 0;
}
