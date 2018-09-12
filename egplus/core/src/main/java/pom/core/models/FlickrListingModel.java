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

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FlickrListingModel {

	/** The Constant DAM_ROOT_PATH. */
	private static final String DAM_ROOT_PATH = "/content/dam";

	/** The tag searched. */
	@Inject
	private static String[] flickerTags;

}