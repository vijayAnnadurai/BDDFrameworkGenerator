package com.bdd.pages;

import java.io.BufferedWriter;
import java.util.List;

public class PageObjectCreator {

	/* This method is used to create individual  methods for each page objects */ 
	public void getPageObjects(List<UI_element> uiElements,
			BufferedWriter outPages) {

		StringBuilder methods = null;
		
		String endSt = " end";
		String defKey = " def ";
		String newLine = "\n";

		for (UI_element ui_ele : uiElements) {

			String tagValue = null, nameValue = null, xpathValue = null, textValue = null,typeValue = null;

			Map<String, String> temp = ui_ele.getElementWithAttributes();
			for (String key : temp.keySet()) {

				switch (key) {

				case "tagName":
					tagValue = temp.get(key);
					continue;
				case "name":
					nameValue = temp.get(key);
					continue;
				case "text":
					textValue = temp.get(key);
					continue;
				case "xpath":
					xpathValue = temp.get(key);
					continue;
				case "type":
					typeValue= temp.get(key);
					continue;
				default:
					continue;
				}
			}

			// if(key.equalsIgnoreCase("tagname))

			if (tagValue.equalsIgnoreCase("input")) {
				String modName = nameValue;
				modName = modName.toLowerCase().replaceAll("[^a-zA-Z0-9]", "_");
				String methodName = defKey + modName + "_" + typeValue ;

				try {
					outPages.append(methodName);
					outPages.append(newLine);
					outPages.append("  @browser.find_element(:name,'" + nameValue+"')");
					outPages.append(newLine + endSt);
					outPages.append(newLine);
					outPages.append(newLine);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} if (tagValue.equalsIgnoreCase("select")) {
				String modName = nameValue;
				modName = modName.toLowerCase().replaceAll("[^a-zA-Z0-9]", "_");
				String methodName = defKey + modName + "_dropDown" ;

				try {
					outPages.append(methodName);
					outPages.append(newLine);
					outPages.append("  @browser.find_element(:name,'" + nameValue+"')");
					outPages.append(newLine + endSt);
					outPages.append(newLine);
					outPages.append(newLine);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}else if (tagValue.equalsIgnoreCase("a")) {
				String modName = textValue;
				
				modName = modName.toLowerCase().replaceAll("[^a-zA-Z0-9]", "_");
				String methodName = defKey + modName + "_link" ;

				try {
					outPages.append(methodName);
					outPages.append(newLine);
					outPages.append("  @browser.find_element(:link,'" + textValue+"')");
					outPages.append(newLine + endSt);
					outPages.append(newLine);
					outPages.append(newLine);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}else if (tagValue.equalsIgnoreCase("button")) {
				String modName = nameValue;
				
				modName = modName.toLowerCase().replaceAll("[^a-zA-Z0-9]", "_");
				String methodName = defKey + modName + "_"+typeValue ;

				try {
					outPages.append(methodName);
					outPages.append(newLine);
					outPages.append("  @browser.find_element(:name,'" + nameValue+"')");
					outPages.append(newLine + endSt);
					outPages.append(newLine);
					outPages.append(newLine);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
