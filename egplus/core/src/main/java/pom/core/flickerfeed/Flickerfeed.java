package pom.core.flickerfeed;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class Flickerfeed.
 */
public class Flickerfeed {
	
	/** The title. */
	@JsonProperty("title")
	private String title;
	
	/** The link. */
	@JsonProperty("link")
	private String link;
	
	/** The description. */
	@JsonProperty("description")
	private String description;
	
	/** The nodified date. */
	@JsonProperty("modified")
	private String modifiedDate;
	
	/** The generator. */
	@JsonProperty("generator")
	private String generator;
	
	/** The items. */
	@JsonProperty("items")
	private List<Items> items;

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
	 * @param title the new title
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
	 * @param link the new link
	 */
	public void setLink(String link) {
		this.link = link;
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
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the modified date.
	 *
	 * @return the modified date
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * Sets the modified date.
	 *
	 * @param modifiedDate the new modified date
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * Gets the generator.
	 *
	 * @return the generator
	 */
	public String getGenerator() {
		return generator;
	}

	/**
	 * Sets the generator.
	 *
	 * @param generator the new generator
	 */
	public void setGenerator(String generator) {
		this.generator = generator;
	}

	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	public List<Items> getItems() {
		return items;
	}

	/**
	 * Sets the items.
	 *
	 * @param items the new items
	 */
	public void setItems(List<Items> items) {
		this.items = items;
	}
}