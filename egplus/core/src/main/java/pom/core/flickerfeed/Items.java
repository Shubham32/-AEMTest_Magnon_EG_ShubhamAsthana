package pom.core.flickerfeed;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class Items.
 */
public class Items {

	/** The title. */
	@JsonProperty("title")
	private String title;

	/** The link. */
	@JsonProperty("link")
	private String link;

	/** The media. */
	@JsonProperty("media")
	private List<Media> media;

	/** The date taken. */
	@JsonProperty("date_taken")
	private String dateTaken;

	/** The description. */
	@JsonProperty("description")
	private String description;

	/** The published date. */
	@JsonProperty("published")
	private String publishedDate;

	/** The author. */
	@JsonProperty("author")
	private String author;

	/** The author id. */
	@JsonProperty("author_id")
	private String authorId;

	/** The tags. */
	@JsonProperty("tags")
	private String tags;

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the link.
	 *
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Sets the link.
	 *
	 * @param link
	 *            the new link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Gets the media.
	 *
	 * @return the media
	 */
	public List<Media> getMedia() {
		return media;
	}

	/**
	 * Sets the media.
	 *
	 * @param media
	 *            the new media
	 */
	public void setMedia(List<Media> media) {
		this.media = media;
	}

	/**
	 * Gets the date taken.
	 *
	 * @return the date taken
	 */
	public String getDateTaken() {
		return dateTaken;
	}

	/**
	 * Sets the date taken.
	 *
	 * @param dateTaken
	 *            the new date taken
	 */
	public void setDateTaken(String dateTaken) {
		this.dateTaken = dateTaken;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the published date.
	 *
	 * @return the published date
	 */
	public String getPublishedDate() {
		return publishedDate;
	}

	/**
	 * Sets the published date.
	 *
	 * @param publishedDate
	 *            the new published date
	 */
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author.
	 *
	 * @param author
	 *            the new author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets the author id.
	 *
	 * @return the author id
	 */
	public String getAuthorId() {
		return authorId;
	}

	/**
	 * Sets the author id.
	 *
	 * @param authorId
	 *            the new author id
	 */
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * Sets the tags.
	 *
	 * @param tags
	 *            the new tags
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
}