package pom.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import pom.core.beans.ImageBean;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageListingModel {

	@Inject
	private String[] tagSearched;

	@Inject
	private String searchForAllTags;

	public List<ImageBean> getImageListing() {
		List<ImageBean> imageList = new ArrayList<>();

		return imageList;
	}

}
