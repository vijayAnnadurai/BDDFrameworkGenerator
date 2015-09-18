package com.bdd.pages;

public void pageCreator(List<UI_element> uiElements)
{
	String title = com.bdd.innovation.objectCreation.core.launch.LaunchPad.driver.getTitle();
	
	String[] titleTrim = title.split("\\:");
	//replacing blank spaces with underscores in class names
	String modTitleTrim = titleTrim[0].toLowerCase().replaceAll("\\s","_");
	//TO-DO
	//change it to directory structure
	String outputFile = compareFolder();
	String pageLocation= outputFile +"/"+ DirectoryStructure.pagesPath;
	objLocation = outputFile +"/"+ DirectoryStructure.objMappingsPath;
			//ReadFromPropFile.getProperty("OutputLocation");
	
	String page = pageLocation+ "/"+ modTitleTrim +".rb" ;
	String pageMapping = objLocation+ "/"+ modTitleTrim +"_page_mapping.rb" ;
	System.out.println("Title of the page is "+ page);
	
	try {
		outPages = new BufferedWriter(new FileWriter(pageMapping,true));
		outPageMapping = new BufferedWriter(new FileWriter(page,true));
		outPages.append("require \"selenium-webdriver\"");
		outPages.append("\n");
		outPages.append("class "+ titleTrim[0].replaceAll("\\s","_"));
		outPages.append("\n");
		outPages.append("   def initialize browser");
		outPages.append("\n");
		outPages.append("    @browser = browser");
		outPages.append("\n");
		outPages.append("   end");
		outPages.append("\n");
	
		pg.getPageObjects(Ui_elements.getUiElements(),outPages);
		
		outPages.append("\n");
		outPages.append("end");
		
		outPages.close();
		outPageMapping.close();
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}



/* This method will identify last modified folder in specified output folder for insertion of pages into it */
public String compareFolder()
{
	File dir = new File(PropertyReader.getProperty("driveLocation")+"/");
	System.out.println("drive location is :"+ dir);

	File[] files = dir.listFiles();
	if (files.length == 0) {
	   System.out.println("No files in specified Directory");
	}

	File lastModifiedFile = files[0];
	for (int i = 1; i < files.length; i++) {
	   if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	       lastModifiedFile = files[i];
	   }
	  
	}
	 System.out.println("Last modified file is :"+lastModifiedFile);
	 return lastModifiedFile.toString();
}
}

