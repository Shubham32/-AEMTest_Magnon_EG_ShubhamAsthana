package pom.core.flickerfeed;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class Media.
 */
public class Media {
	
	/** The media. */
	@JsonProperty("m")
	private String media;

	/**
	 * Gets the media.
	 *
	 * @return the media
	 */
	public String getMedia() {
		return media;
	}

	/**
	 * Sets the media.
	 *
	 * @param media the new media
	 */
	public void setMedia(String media) {
		this.media = media;
	}
}