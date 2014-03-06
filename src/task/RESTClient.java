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
	private String _search;
	private DefaultHttpClient _client;
	private String _lastResponse;

	public RESTClient(String resource, String search) {
		_resource = resource;
		_search = search;
		_client = new DefaultHttpClient();
	}

	public String getRequest() {
		InputStream is = null;
		String ln, output = "";
		try {
			HttpGet getRequest = new HttpGet(task.Settings.API_ROOT + _resource
					+ _search);
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

		} finally {
			_client.getConnectionManager().shutdown();
		}
		return output;
	}

}