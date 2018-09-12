package pom.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.eval.PathPredicateEvaluator;
import com.day.cq.tagging.TagManager;

import pom.core.beans.ImageBean;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageListingModel {

	private static final String DAM_ROOT_PATH = "/content/dam";
	
	@Inject
	private static String[] tagSearched;

	@Inject
	private String searchForAllTags;
	
	@Inject
	private ResourceResolver resolver;

	public List<ImageBean> getImageListing() {
		List<ImageBean> imageList = new ArrayList<>();
		TagManager tagManager = resolver.adaptTo(TagManager.class);
		tagManager.find(DAM_ROOT_PATH, tagSearched);
		return imageList;
	}
	
	/**
	 * Find all pdp resources.
	 *
	 * @param resolver
	 *            the resolver
	 * @param contentPath
	 *            the content path
	 * @return the iterator
	 */
	private static Iterator<Resource> findAllImages(ResourceResolver resolver, String contentPath) {
		Iterator<Resource> resultIterator = null;
		Map<String, String> params = new HashMap<>();
		params.put(PathPredicateEvaluator.PATH, contentPath);
		params.put("1_property", "@jcr:content/jcr:primaryType");
		params.put("1_property.value", "dam:AssetContent");
		params.put("2_property", "@jcr:content/metadata/cq:tags");
		params.put("1_property.value", tagSearched);
		params.put("property.operation", "AND");
		params.put("p.limit", "-1");
		QueryBuilder queryBuilder = resolver.adaptTo(QueryBuilder.class);
		if (queryBuilder != null) {
			Query query = queryBuilder.createQuery(PredicateGroup.create(params), resolver.adaptTo(Session.class));
			resultIterator = query.getResult().getResources();
		}
		return resultIterator;
	}

}
