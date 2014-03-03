package task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class RESTClient {
	/**
	 * @description RESTful API client class
	 * 
	 **/
	@SuppressWarnings("deprecation")
	private String _resource;
	private DefaultHttpClient _client;
	private String _lastResponse;

	public RESTClient(String resource) {
		_resource = resource;
		_client = new DefaultHttpClient();
		_lastResponse = null;
	}

	public void getRquest() {
		InputStream is = null;
		String ln, output = "";
		try {
			HttpGet getRequest = new HttpGet(task.Settings.API_ROOT + _resource);
			getRequest.addHeader("accept", "application/json");

			HttpResponse response = _client.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != task.Settings.HTTP_STATUS_OK) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			while ((ln = br.readLine()) != null) {
				output += ln;
			}

		} catch (ClientProtocolException e) {
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
		_lastResponse = output;
	}

	public String getLastResponse() {
		return _lastResponse;
	}

	public void closeConnection() {
		/*
		 * Giving the responsibility of closing the connection is BAD, normally
		 * I would implement a connection pool...
		 */
		_client.getConnectionManager().shutdown();
	}

	public static void main(String[] args) {
		RESTClient rc = new RESTClient("suggest/position/en/name/dev");
		rc.getRquest();
		rc.closeConnection();
		JSONParser jp = new JSONParser(rc);
		jp.fromNative();
		System.out.println(jp.getObject());

	}
}