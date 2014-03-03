package task;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Position {
	private float latitude;
	private float longitude;

	@JsonProperty("latitude")
	public float getLatitude() {
		return latitude;
	}

	public void serLatitud(float lat) {
		latitude = lat;
	}

	@JsonProperty("longitude")
	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float lon) {
		longitude = lon;
	}

	@Override
	public String toString() {
		return "{latitude:" + latitude + ", longitude:" + longitude + "}";

	}
}
