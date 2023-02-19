package org.example.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiConstants {

	// Mappings
	public static final String API_BASE_MAPPING = "/api";
	public static final String ELEMENTS_MAPPING = "/elements";
	public static final String ELEMENT_ID_MAPPING = "/{elementId}";
	public static final String ELEMENTS_BASE_MAPPING = API_BASE_MAPPING + ELEMENTS_MAPPING;
	public static final String ELEMENT_BASE_MAPPING = ELEMENTS_BASE_MAPPING + ELEMENT_ID_MAPPING;

	// Summaries
	public static final String GET_ELEMENTS_SUMMARY = "Elements list";
	public static final String POST_ELEMENT_SUMMARY = "Create element";
	public static final String PUT_ELEMENT_SUMMARY = "Update element";
	public static final String DELETE_ALL_ELEMENTS_SUMMARY = "Delete all elements";
	public static final String DELETE_ELEMENT_SUMMARY = "Delete element";

	// Descriptions
	public static final String GET_ELEMENT_DESCRIPTION = "Returns a list of elements";
	public static final String POST_ELEMENT_DESCRIPTION = "Create a new element";
	public static final String PUT_ELEMENT_DESCRIPTION = "Update an element";
	public static final String DELETE_ALL_ELEMENTS_DESCRIPTION = "Deletes all element";
	public static final String DELETE_ELEMENT_DESCRIPTION = "Delete an element";
}
