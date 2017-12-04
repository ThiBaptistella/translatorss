package au.com.translatorss.dao;

import java.util.List;

import au.com.translatorss.bean.ServiceRequestCategory;

public interface ServiceRequestCategoryDao extends GenericDao<ServiceRequestCategory, Integer> {

    public ServiceRequestCategory findByDescription(String serviceRequestCategory);

	public List<ServiceRequestCategory> getAutomaticCategories();

}
