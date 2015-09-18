package com.bdd.conversion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	//load the text file which is in (D:\BDD_automation\workspace\BDD_innovation\Global Settings.prop) drive
	//into java file object which ll be stored in JVM
	
	//Creating java file object
	static File propFile = null;
	//create input stream to connect to prop object
	
	
	
	//creating properties object
	static Properties prop = new Properties();

	public static String getProperty(String s)
	{
		InputStream input;
		try {
			propFile =new File(System.getProperty("user.dir")+"/src/com/bdd/innovation/utility/Global Settings Extn.properties");
			input = new FileInputStream(propFile);
			prop.load(input);
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop.getProperty(s);
	}
	
}
