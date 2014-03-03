package task;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ResultSet {
	private List<Location> results;

	@JsonProperty("results")
	public List<Location> getResults() {
		return results;
	}

	public void setResults(List<Location> res) {
		results = res;
	}

	@Override
	public String toString() {
		String output = "";
		for (Location loc : results) {
			output += loc.toString() + ",";
		}
		output = output.substring(0, output.length() - 1);
		return "{results: [" + output + "]}";
	}

}
