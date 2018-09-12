/*
 * APi exception class
 */

package pom.core.api;

public class ApiException extends Exception {

	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 8763415117407480819L;
	private int code;

	/**
	 * default Constructor
	 */
	public ApiException() {
		/**
		 * Constructor
		 */
	}

	public ApiException(Throwable throwable) {
		super(throwable);
	}

	public ApiException(String message) {
		super(message);
	}

	public ApiException(int code, String message) {
		super(message);
		this.code = code;
	}

	public ApiException(int code, String message, Throwable throwable) {
		super(message, throwable);
		this.code = code;

	}

	/**
	 * Get the HTTP status code.
	 *
	 * @return HTTP status code
	 */
	public int getCode() {
		return code;
	}

}
