package com.bdd.framework;

import java.io.File;

public class FrameworkGenerator {

	/* This method is used to create folders in the specified directory */
	
	public static String testMethod() {
		File projName = new File(DirectoryStructure.projectName);

		File config = new File(DirectoryStructure.config);
		File data = new File(DirectoryStructure.data);
		File features = new File(DirectoryStructure.features);
		File specifications = new File(DirectoryStructure.specifications);
		File stepDef = new File(DirectoryStructure.stepDef);
		File reports = new File(DirectoryStructure.reports);
		File regFeature = new File(DirectoryStructure.regFeature);
		File sanFeature = new File(DirectoryStructure.sanFeature);
		File regStepDef = new File(DirectoryStructure.regStepDef);
		File sanStepDef = new File(DirectoryStructure.sanStepDef);
		File currentDate = new File(DirectoryStructure.driveLocation);
		

		try {
			projName.mkdir();
			config.mkdirs();
			data.mkdirs();
			currentDate.mkdir();
			features.mkdirs();
			specifications.mkdirs();
			stepDef.mkdirs();
			reports.mkdirs();
			regFeature.mkdir();
			sanFeature.mkdir();
			regStepDef.mkdir();
			sanStepDef.mkdir();

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	/*This method will copy the specified folders from sample project into specified directory */

	public static String fileCopier() {

		try {
			FileUtils.copyFolder(new File(
					"Output/Sample Framework/ProjectName - Automation/config/site_config.rb"),
					new File(DirectoryStructure.site_config));
			
			FileUtils.copyFolder(new File(
					"Output/Sample Framework/ProjectName - Automation/lib"),
					new File(DirectoryStructure.lib));
			FileUtils
					.copyFolder(
							new File(
									"Output/Sample Framework/ProjectName - Automation/specifications/support"),
							new File(DirectoryStructure.support));
			FileUtils
					.copyFolder(
							new File(
									"Output/Sample Framework/ProjectName - Automation/gemfile"),
							new File(DirectoryStructure.gemFile));

			FileUtils
					.copyFolder(
							new File(
									"Output/Sample Framework/ProjectName - Automation/rakefile.rb"),
							new File(DirectoryStructure.rakeFile));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null; }

