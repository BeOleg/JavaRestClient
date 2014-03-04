package task;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

	private Position geo_position;
	private int _id;
	private String _type;
	private String name;
	private String type;

	@JsonProperty("geo_position")
	public Position get_posisition() {
		return geo_position;
	}

	public void set_posisition(Position _posisition) {
		this.geo_position = _posisition;
	}

	@JsonProperty("_id")
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	@JsonProperty("_tpye")
	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] toRow() {
		return new String[] { _type, String.valueOf(_id), name, type,
				String.valueOf(geo_position.getLatitude()),
				String.valueOf(geo_position.getLongitude()) };
	}

	@Override
	public String toString() {
		return "{id:" + _id + ", _type:" + _type + ", name:" + name + ", type:"
				+ type + "," + " geo_position:" + geo_position.toString() + "}";
	}

}
