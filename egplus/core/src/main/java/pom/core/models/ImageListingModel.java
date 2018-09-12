package pom.core.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.day.cq.commons.RangeIterator;
import com.day.cq.tagging.TagManager;

import pom.core.beans.ImageBean;

/**
 * The Class ImageListingModel.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageListingModel {

	/** The Constant DAM_ROOT_PATH. */
	private static final String DAM_ROOT_PATH = "/content/dam";

	/** The tag searched. */
	@Inject
	private static String[] tagSearched;

	/** The search for all tags. */
	@Inject
	private String searchForAllTags;

	/** The resolver. */
	@Inject
	private ResourceResolver resolver;

	/**
	 * Gets the image listing.
	 *
	 * @return the image listing
	 */
	public List<ImageBean> getImageListing() {
		List<ImageBean> imageList = new ArrayList<>();
		TagManager tagManager = resolver.adaptTo(TagManager.class);
		boolean shouldSearchForAllTags = Boolean.parseBoolean(searchForAllTags);
		RangeIterator<Resource> imageResources = null;
		imageResources = tagManager.find(DAM_ROOT_PATH, tagSearched, !shouldSearchForAllTags);
		if (null != imageResources) {
			while (imageResources.hasNext()) {
				Resource imageRes = imageResources.next();
				if (null != imageRes) {
					ValueMap imgVMap = imageRes.getParent().getValueMap();
					if (null != imgVMap && imgVMap.containsKey("jcr:primaryType")) {
						String assetType = (String) imgVMap.get("jcr:primaryType");
						if (assetType.equals("dam:AssetContent") && imgVMap.containsKey("jcr:lastModified")) {
							long currentTimestamp = System.currentTimeMillis();
							long difference = Math.abs(
									currentTimestamp - getDateFromValueMap(imgVMap.get("jcr:lastModified")).getTime());
							if (difference < 1000 * 60 * 60 * 24 * 5) {
								ImageBean bean = new ImageBean();
								bean.setImageUrl(imageRes.getParent().getParent().getPath());
								ValueMap metaVMap = imageRes.getValueMap();
								if (null != metaVMap && metaVMap.containsKey("dc:title")) {
									String title = (String) metaVMap.get("metaVMap");
									bean.setImageTitle(title);
									bean.setImageAltText(title);
								}
								imageList.add(bean);
							}
						}
					}
				}
			}
		}
		return imageList;
	}

	/**
	 * Gets the date from value map.
	 *
	 * @param key
	 *            the key
	 * @return the date from value map
	 */
	private static Date getDateFromValueMap(Object key) {
		Date date = null;
		GregorianCalendar newGregCal = (GregorianCalendar) key;
		if (null != newGregCal) {
			date = newGregCal.getTime();
		}
		return date;
	}
}