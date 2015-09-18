package com.bdd.elements;

public class Xpath {
	/**
	 * webdriver
	 */
	static WebDriver driver = null;
	/**
	 * web element
	 */
	static WebElement element = null;
	/**
	 * attribute constants
	 */
	// add up as many attributes available
	static final String attributes[] = { "id", "class", "name", "value" };

	/**
	 * creates a driver
	 * 
	 * @return WebDriver
	 */
	public static WebDriver createDriver() {
		driver = new FirefoxDriver();
		System.out.println("Driver Created: " + driver); //$NON-NLS-1$
		return driver;
	}

	/**
	 * Invokes url
	 */
	public static void invokeURL() {
		driver.get(Messages.getString("Xpath.URL1")); //$NON-NLS-1$
		System.out.println("URL : " + driver.getCurrentUrl()); //$NON-NLS-1$
		System.out.println("Title : " + driver.getTitle()); //$NON-NLS-1$

	}

	/**
	 * Finds an element
	 */
	public static WebElement findElement() {
		element = driver.findElement(By.id(Messages.getString("Xpath.id1")));
		return element;
	}

	/**
	 * Find parent element
	 */
	public static WebElement findParentOf(WebElement element) {
	
		// find its parent
		return element.findElement(By.xpath("..")); //$NON-NLS-1$ //$NON-NLS-2$

	
	}

	/**
	 * Finds absolute indexed XPath
	 */
	public static String findAbsoluteIndexedXpath(WebElement ele) {
		StringBuilder xpath = new StringBuilder();
		
		// iterate until the HTML tag is not reached
		do {
			
			xpath.insert(
					0,
					"/"
							+ ele.getTagName()
							+ "["
							+ ele.findElements(By.xpath("preceding-sibling::*"))
									.size() + "]");
			ele = ele.findElement(By.xpath(".."));
		} while (!ele.getTagName().equals("html"));

		xpath.insert(0, "/html");

		System.out.println("the XPath is : " + xpath.toString());

		// now add the html tag top the last

		return xpath.toString();

	}

	/**
	 * Finds XPath with CSS selectors
	 */
	public static String findCSSXpath(WebElement ele) {
		return "Coding in Progress";
		// to do
	}

	/**
	 * Finds attributes of an element
	 * 
	 * @return Map
	 */

	public static Map<String, String> findAttributes(WebElement ele) {
		Map<String, String> attributeMap = new TreeMap<>();
		for (String attribute : attributes) {
			String value = ele.getAttribute(attribute);
			attributeMap.put(attribute, value);
		}
		return attributeMap;

	}
	/**
	 * Finds relative XPath with attributes instead of indices
	 */
	public static String findRelativeAttributeXpath(WebElement ele) {

		StringBuilder xpath = new StringBuilder();
		

			
			xpath.insert(0, "/" + ele.getTagName()
					+ attributeString(findAttributes(ele)));
			System.out.println("DEBUG: " + ele.getTagName());
			//Parent  Element data
			ele = ele.findElement(By.xpath(".."));
			xpath.insert(0, "//" + ele.getTagName()+ attributeString(findAttributes(ele)));

		

		System.out.println("the XPath is : " + xpath.toString());
		
		return xpath.toString();
	}
	/**
	 * Finds absolute XPath with attributes instead of indices
	 */
	public static String findAbsoluteAttributeXpath(WebElement ele) {

		StringBuilder xpath = new StringBuilder();
		do {

		
			xpath.insert(0, "/" + ele.getTagName()
					+ attributeString(findAttributes(ele)));
			System.out.println("DEBUG: " + ele.getTagName());
			ele = ele.findElement(By.xpath(".."));
		} while (!ele.getTagName().equals("html"));

		xpath.insert(0, "/html");

		System.out.println("the XPath is : " + xpath.toString());
		// to do
		return xpath.toString();
	}

	/**
	 * Makes a attribute string in the form [@id="xxx" and @name="xxx" etc]
	 */
	public static String attributeString(Map<String, String> attributes) {
		// prepare a attribute relation for xpath

		Set<String> keySet = attributes.keySet();

		StringBuilder attString = new StringBuilder();
		attString.append("[");
		String val = null;
		for (String attr : keySet) {
			val = attributes.get(attr);
			if (val == null) {
				continue;
			} else if (val.equals("")) {
				continue;
			} else {
				attString
						.append("@" + attr + "=" + "\"" + val + "\"" + " and ");
				System.out.println("DEBUG: " + attString.toString());
			}
		}
		if (!(attString.length() <= 5))
			attString.delete(attString.length() - 5, attString.length());
		attString.append("]");

		return attString.toString();
	}

	/**
	 * Close the page and quit driver
	 */
	public static void close() {
		driver.close();
		System.out.println("Driver is Closed"); //$NON-NLS-1$
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createDriver();
		invokeURL();

		System.out.println("XPath with indexes: "
				+ findAbsoluteIndexedXpath(findElement()));
		System.out.println("XPath with Relations: "
				+ findAbsoluteAttributeXpath(findElement()));
		close();

	}

}


