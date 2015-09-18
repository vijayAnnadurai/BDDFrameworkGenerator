package com.bdd.framework;

import java.io.File;

import com.bdd.elements.SearchWord;

public class StepDefPreparator {

	String separator = "|";
	static String variable = null;
	static boolean flag = false;
	StringBuilder sd = new StringBuilder();
	StringBuilder fileCheck = new StringBuilder();

	// To write number of params based on given string count
	public void paramWriter(String s, String fileName) {
		sd.setLength(0);

		if (!(fileCheck.toString().contains(fileName))) {

			sd.append("require_relative '../../../lib/object_mappings/"
					+ fileName + "'");
			sd.append("\n");
			sd.append("\n");
			fileCheck.append(fileName);
		}
		sd.append(s);

		if (s.contains("(.*)")) {
			sd.append(separator);
			variable = "var_1";
			sd.append(variable);
			sd.append(separator);

			System.out.println(sd);
		}

	}

	public String paramRemover(String b) {
		if (b.contains("\"")) {
			int startIndex = b.indexOf("\"");
			int nextIndex = b.indexOf("\"", startIndex + 1);
			int lastIndex = b.lastIndexOf("\"");
			// System.out.println(lastIndex);
			try {

				String c = b.substring(startIndex + 1, nextIndex);
				b = b.replace(c, "(.*)");
				System.out.println(b);

			} catch (StringIndexOutOfBoundsException e) {
				e.printStackTrace();
			}

		} else {
			b = b;
		}
		return b;

	}

	// to identify the type of operation to be performed based on method names
	public String operationIdentifier(String methodName) {

		String type = methodName.substring(methodName.lastIndexOf("_") + 1);
		System.out.println("type is " + type);
		String operation = null;

		switch (type) {
		case "text":
			operation = "send_keys " + variable;
			break;
		case "dropDown":
			operation = "send_keys " + variable;
			break;
		case "radio":
			operation = "click()";
			break;
		case "password":
			operation = "send_keys " + variable;
			break;
		case "image":
			operation = "submit";
			break;
		case "link":
			operation = "click()";
			break;
		case "submit":
			operation = "submit";
			break;

		}
		return operation;

	}

	// it will invoke other sub methods and print it into step definition files
	public String stepsWriter(String s, String keyword) {
		String operation = null, paramString = null;
		SearchWord search = new SearchWord();

		File dir = new File(pagesCreator.objLocation + "/");
		System.out.println("File path is " + dir);

		String[] result = search.directoryCrawler(dir, keyword);

		paramString = paramRemover(s);

		paramWriter(paramString, result[2]);

		System.out.println("DEBUG-method name is " + result[1]);
		operation = operationIdentifier(result[1]);

		sd.append("\n");
		sd.append("    " + result[0] + ".new($browser)." + result[1] + "."
				+ operation);
		sd.append("\n");
		System.out.println(sd);
		return sd.toString();

	}
}

