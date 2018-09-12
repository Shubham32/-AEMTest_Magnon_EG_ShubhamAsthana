package pom.core.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class APIClient.
 */
public class APIClient {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(APIClient.class);

	/**
	 * Build call and get response from service.
	 *
	 * @param path
	 *            The service path
	 * @param queryParams
	 *            The query params
	 * @param responseClassType
	 *            The response class type
	 * @return The Api response
	 * @throws ApiException
	 *             The Api exception
	 */
	public static <T> ApiResponse<T> buildCall(String path, Class<T> responseClassType) throws ApiException {
		LOGGER.debug("Inside buildCall method");
		ApiResponse<T> response = null;
		HttpURLConnection httpCon = null;
		try {
			final URL url = new URL(path);
			httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setRequestMethod("GET");
			httpCon.setRequestProperty("Accept", "application/json");
			if (httpCon.getResponseCode() != 200) {
				throw new ApiException(httpCon.getResponseCode(), httpCon.getResponseMessage());
			}
			response = handleResponse(httpCon, responseClassType);
			LOGGER.debug("Exit buildCall method");
		} catch (final IOException e) {
			LOGGER.error("Some Excepton occured :: ", e);
			throw new ApiException(100, "", e);
		} finally {
			LOGGER.debug("Disconnecting http url connection....");
			if (null != httpCon) {
				httpCon.disconnect();
			}
			LOGGER.debug("Disconnected http url connection....");

		}
		return response;
	}

	/**
	 * Handle the given response, return the deserialized object when the response
	 * is successful.
	 *
	 * @param <T>
	 *            Type
	 * @param response
	 *            Response
	 * @param returnType
	 *            Return type
	 * @throws ApiException
	 *             If the response has a unsuccessful status code or fail to
	 *             deserialize the response body
	 * @return Type
	 * @throws IOException
	 */
	public static <T> ApiResponse<T> handleResponse(HttpURLConnection httpCon, Class<T> responseClassType)
			throws IOException {
		final InputStream inputStream = httpCon.getInputStream();
		final T data = deserialize(inputStream, responseClassType);
		return new ApiResponse<>(200, httpCon.getHeaderFields(), data);
	}

	/**
	 * Deserialize response body to Java object
	 *
	 * @param inputStream
	 *            the Input Stream
	 * @param responseClassType
	 *            The response class type
	 * @return The deserialised object
	 * @throws IOException
	 *             The IO Exception
	 */
	public static <T> T deserialize(InputStream inputStream, Class<T> responseClassType) throws IOException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		LOGGER.debug("Inside deserialize method");
		String output = null;
		final StringBuilder sb = new StringBuilder();
		while ((output = br.readLine()) != null) {
			sb.append(output);
		}
		String outputString = sb.toString();
		outputString = StringUtils.replace(outputString, "jsonFlickrFeed(", "{\"jsonFlickrFeed\": ");
		outputString = StringUtils.replace(outputString, ")", "}");
		LOGGER.debug("Websevice response string :: {}", outputString);
		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		LOGGER.debug("Exit deserialize method");
		return mapper.readValue(outputString, responseClassType);
	}
}