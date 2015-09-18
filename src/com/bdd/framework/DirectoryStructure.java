package com.bdd.framework;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface DirectoryStructure {

	String sep = "/";
	String currentDate = new SimpleDateFormat("dd MMM, hh_mm")
	.format(new Date());
	String driveLocation = PropertyReader.getProperty("driveLocation")+sep+ currentDate;
	String projectName = driveLocation +sep
			+ PropertyReader.getProperty("ProjectName") + " - Automation";
	
	String config = projectName + sep + "config";
	String data = projectName + sep + "data";
	String features = projectName + sep + "features";
	String specifications = features + sep + "specifications";
	String stepDef = features + sep + "step_definitions";
	String support = features + sep + "support";
	String lib = projectName + sep + "lib";
	String pagesPath =  PropertyReader.getProperty("ProjectName") + " - Automation"+sep+"lib"+sep+"pages";
	String objMappingsPath =  PropertyReader.getProperty("ProjectName") + " - Automation"+sep+"lib"+sep+"object_mappings";
	String reports = projectName + sep + "reports";
	String gemFile = projectName + sep +"gemfile";
	String rakeFile = projectName + sep + "rakefile";
	String regFeature = specifications + sep + "regression";
	String sanFeature = specifications + sep + "sanity";
	String regStepDef = stepDef + sep + "regression";
	String sanStepDef = stepDef + sep + "sanity";
	String site_config = config + sep + "site_config";

}
	
