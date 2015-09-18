package com.bdd.elements;

import java.util.TreeMap;

public class UI_element {
	
	private Map<String,String> elementWithAttributes=new TreeMap<>();
	
	UI_element(String tagName)
	{
		elementWithAttributes.put("tagName", tagName);
	}

	public Map<String, String> getElementWithAttributes() {
		return elementWithAttributes;
	}

	public void setElementWithAttributes(Map<String, String> elementWithAttributes) {
		this.elementWithAttributes = elementWithAttributes;
	}
	public void persist(String key, String value)
	{
		elementWithAttributes.put(key, value);
	}
	public String give(String attribute)
	{
		 return elementWithAttributes.get(attribute);
	}
	public int size()
	{
		return elementWithAttributes.size();
	}
	public Set keyset()
	{
		return elementWithAttributes.keySet();
	}

}


