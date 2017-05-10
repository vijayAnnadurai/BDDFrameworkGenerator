package com.bdd.pages;

import java.util.LinkedList;
import java.util.List;

/**
 * this class identifies web elements depending on their tag name and extracts their attributes
 * @author Vijay
 *
 */
public class Ui_Elements_Identifier {


	List<String> tagNames=null;
	List<String> attributes=null;
	// the below lists store the total web elements present in a web page
	static List <WebElement>WebPage=new LinkedList<WebElement>();
	static List <WebElement>WebElementCategory;

	/**
	 * method to find out web elements and return their atributes
	 */
	public void getElements(String windowId, WebDriver driver)
	{
		Ui_elements allEle=new Ui_elements();
		
		// loading the user defined tags and attribute values
		loader();

		// find all elements of a particular tag name and store in a list
		for(String tagName:tagNames){
			System.out.println("DEBUG:"+"tagNaame :"+tagName);
			System.out.println(" driver" +driver.findElements(By.tagName(tagName)));
			WebElementCategory= driver.findElements(By.tagName(tagName));
			
			if(WebPage.size()==0)
			{
				WebPage.addAll(WebElementCategory);
			}
			else{

				WebPage.addAll(WebPage.size(),WebElementCategory);
			}
			
			for(WebElement webEle: WebElementCategory)
			{					
				UI_element bean=new UI_element(tagName);
				
				for(String attr:attributes)
				{					

				
					// keep data into new object and call xml parser to persist in xml file
					
					if(attr.equals("text"))
						bean.persist(attr, webEle.getText());
					else if (attr.equals("posX"))
						bean.persist(attr, String.valueOf(webEle.getLocation().getX()));
					else if (attr.equals("posY"))
						bean.persist(attr, String.valueOf(webEle.getLocation().getY()));
					else {
						String value=webEle.getAttribute(attr);
						if(value==null)
							value="NA";
						System.out.println(" persisting a ui element in to ui elements list");
					bean.persist(attr,value);
					bean.persist("xpath", Xpath.findRelativeAttributeXpath(webEle));
					}
				}Ui_elements.takeElement(bean);
				
				
			}
		}Ui_elements.display();
		// comparision starts hereif

		WebPages.pilePages(allEle);
		// call teh xml parser to write this objedct
		new PrepareXml().write();
		
		//to print in pageobjects format
		
		
		//to create pages in lib
		pagesCreator pc = new pagesCreator();
		pc.pageCreator(Ui_elements.getUiElements());
		
		
		
		Ui_elements.flush();
		

	}

	public void loader()
	{
		/**
		 * loads tag names from the properties file to a list
		 */
		String tagNamescomb=ReadFromPropFile.getProperty("tagName");
		if(tagNamescomb.isEmpty()){
			System.out.println(" the property 'tagName' does not exist "+this.getClass().getName());
		}
		String tagNamesArray[]=tagNamescomb.split(";");
		tagNames=Arrays.asList(tagNamesArray);
		// debug
		System.out.println("***** tag names loaded *****");
		for(String tname:tagNames)
		{
			System.out.print(tname+",");
		}System.out.println("");
		System.out.println("****************************");

		/**
		 * loads attribute names from the properties file to a list
		 */
		String attributescomb=ReadFromPropFile.getProperty("attributes");
		if(attributescomb.isEmpty()){
			System.out.println(" the property 'attributes' does not exist "+this.getClass().getName());
		}
		String attributesArray[]=attributescomb.split(";");
		attributes=Arrays.asList(attributesArray);
		// debug
		System.out.println("***** attributes loaded *****");
		for(String attr:attributes)
		{
			System.out.print(attr+",");
		}System.out.println("");
		System.out.println("****************************");

	}
