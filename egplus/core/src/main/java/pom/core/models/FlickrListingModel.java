package pom.core.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

import pom.core.api.APIClient;
import pom.core.api.ApiException;
import pom.core.api.ApiResponse;
import pom.core.beans.ImageBean;
import pom.core.flickerfeed.Flickerfeed;
import pom.core.flickerfeed.Items;
import pom.core.flickerfeed.JsonFlickerfeed;
import pom.core.flickerfeed.Media;

/**
 * The Class FlickrListingModel.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FlickrListingModel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FlickrListingModel.class);

	/** The tag searched. */
	@Inject
	private String[] flickerTags;

	/** The resolver. */
	@Inject
	private ResourceResolver resolver;

	/**
	 * Gets the image list.
	 *
	 * @return the image list
	 */
	public List<ImageBean> getImageList() {
		LOGGER.debug("Inside getImageList() method");
		List<ImageBean> imageList = new ArrayList<>();
		List<String> tagNames = new ArrayList<>();
		TagManager tagManager = resolver.adaptTo(TagManager.class);
		if (null != tagManager) {
			createTagNameList(tagNames, tagManager);
			JsonFlickerfeed feed = getFlickerFeedData(tagNames);
			ImageBean imgBean = new ImageBean();
			createImageList(imageList, feed, imgBean);
		}
		LOGGER.debug("ImageList from Flickr Feed :: {}", imageList);
		return imageList;
	}

	/**
	 * Creates the image list.
	 *
	 * @param imageList
	 *            the image list
	 * @param feed
	 *            the feed
	 * @param imgBean
	 *            the img bean
	 */
	private void createImageList(List<ImageBean> imageList, JsonFlickerfeed feed, ImageBean imgBean) {
		LOGGER.debug("Entering createImageList() method");
		if (null != feed) {
			Flickerfeed flcrFeed = feed.getJsonFlickrFeed();
			if (null != flcrFeed && null != flcrFeed.getItems()) {
				createImgList(imageList, imgBean, flcrFeed);
			}
		}

		LOGGER.debug("Exiting createImageList() method");
	}

	/**
	 * Creates the img list.
	 *
	 * @param imageList
	 *            the image list
	 * @param imgBean
	 *            the img bean
	 * @param flcrFeed
	 *            the flcr feed
	 */
	private void createImgList(List<ImageBean> imageList, ImageBean imgBean, Flickerfeed flcrFeed) {
		LOGGER.debug("Entering createImgList() method");
		for (Items items : flcrFeed.getItems()) {
			if (null != items.getMedia()) {
				for (Media med : items.getMedia()) {
					imgBean.setImageUrl(med.getMedia());
					imageList.add(imgBean);
				}
			}
		}
		LOGGER.debug("Exiting createImgList() method");
	}

	/**
	 * Creates the tag name list.
	 *
	 * @param tagNames
	 *            the tag names
	 * @param tagManager
	 *            the tag manager
	 */
	private void createTagNameList(List<String> tagNames, TagManager tagManager) {
		LOGGER.debug("Entering createTagNameList() method");
		for (String tagId : Arrays.asList(flickerTags)) {
			Tag tag = tagManager.resolve(tagId);
			if (null != tag && null != tag.getName()) {
				tagNames.add(tag.getName());
			}
		}
		LOGGER.debug("Exiting createTagNameList() method");
	}

	/**
	 * Gets the flicker feed data.
	 *
	 * @param tags
	 *            the tags
	 * @return the flicker feed data
	 */
	private JsonFlickerfeed getFlickerFeedData(List<String> tags) {
		LOGGER.debug("Entering getFlickerFeedData() method");
		JsonFlickerfeed flickerFeed = new JsonFlickerfeed();
		StringBuilder sb = new StringBuilder();
		String params = StringUtils.EMPTY;
		try {
			if (null != tags) {
				for (String tag : tags) {
					params = sb.append(tag).append(",").toString();
				}
				params = StringUtils.substring(params, 0, StringUtils.lastIndexOf(params, ","));
			}
			ApiResponse<JsonFlickerfeed> flickerFeedResponse = APIClient.buildCall(
					"https://api.flickr.com/services/feeds/photos_public.gne?format=json&tags=" + params,
					JsonFlickerfeed.class);
			flickerFeed = flickerFeedResponse.getData();
		} catch (ApiException e) {
			LOGGER.error("ApiException occurred in getFlickerFeedData()", e);
		}
		LOGGER.debug("Exiting getFlickerFeedData() method");
		return flickerFeed;
	}
}