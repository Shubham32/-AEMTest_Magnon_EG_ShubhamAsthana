package pom.core.flickerfeed;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class JsonFlickerfeed.
 */
public class JsonFlickerfeed {

	/** The title. */
	@JsonProperty("jsonFlickrFeed")
	private Flickerfeed jsonFlickrFeed;

	/**
	 * Gets the json flickr feed.
	 *
	 * @return the json flickr feed
	 */
	public Flickerfeed getJsonFlickrFeed() {
		return jsonFlickrFeed;
	}

	/**
	 * Sets the json flickr feed.
	 *
	 * @param jsonFlickrFeed
	 *            the new json flickr feed
	 */
	public void setJsonFlickrFeed(Flickerfeed jsonFlickrFeed) {
		this.jsonFlickrFeed = jsonFlickrFeed;
	}
}