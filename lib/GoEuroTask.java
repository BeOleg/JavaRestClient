package task;

import src.task.FileHandler;
import src.task.JSONParser;
import src.task.RESTClient;

public static final class GoEuroTask {
	public static void main(String[] args) {
		if (args.length == 0 || args[0].length() == 0) {
			System.out.println("Please pass a serach string");
			System.exit(task.Settings.EXIT_CODE_ERROR);
		}
		String search = args[0];
		RESTClient rc = new RESTClient(task.Settings.LOCATIONS_ENDPOINT, search);
		JSONParser jp = new JSONParser(rc);
		FileHandler fw = new FileHandler(jp, System.getProperty("user.dir"));
		String filename = fw.output();

		System.out.println("Results saved to: ");
		System.out.println(filename);
		System.exit(task.Settings.EXIT_CODE_OK);
	}
}
