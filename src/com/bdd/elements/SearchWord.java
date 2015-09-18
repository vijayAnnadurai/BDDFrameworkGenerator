package com.bdd.elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SearchWord {

	private static String directoryPath = "D://test";
	private static String searchWord = "java";

	/**
	 * Creating constructor
	 */
	public SearchWord() {
		super();
	}

	/**
	 * main method
	 * 
	 * @param ags
	 */
	public static void main(String[] args) {
		SearchWord crawler = new SearchWord();
		File directory = new File(directoryPath);

		if (directory == null || !directory.exists()) {
			System.out.println("Directory doesn’t exists!!!");
			return;
		}

		crawler.directoryCrawler(directory, searchWord);
	}

	/**
	 * Gets all the file and directories and prints accordingly
	 * 
	 * @param directory
	 *            Directory path where it should search
	 */
	public String[] directoryCrawler(File directory, String searchWord) {

		// Get List of files in folder and print
		File[] filesAndDirs = directory.listFiles();
         String[] result = null;
		// Print the root directory name
		// System.out.println(“-” + directory.getName());

		// Iterate the list of files, if it is identified as not a file call
		// directoryCrawler method to list all the files in that directory.
		for (File file : filesAndDirs) {

			if (file.isFile()) {
				result = searchWord(file, searchWord);
				if (result[1]!=null)
				{
					break;
				}
				// System.out.println(” |-” + file.getName());
			} else {
				directoryCrawler(file, searchWord);
			}
		}
		return result;
	}

	/**
	 * Search for word in a given file.
	 * 
	 * @param file
	 * @param searchWord
	 */
	private String[] searchWord(File file, String searchWord) {
		Scanner scanFile;
        String fileName = null;
		boolean flag = false;
		String[] methodName = null;
		String[] className = null;
		String[] result = new String[3] ;
		int count = 1;

		try {
			scanFile = new Scanner(file);

			while (scanFile.hasNextLine()) {

				String line = scanFile.nextLine().trim();

				if (line.startsWith("class")) {
					className = line.split("\\s");
				}

				if ((line.contains(searchWord)) && (line.startsWith("def"))) {
					flag = true;
					methodName = line.split("\\s+");
					if (methodName[1].contentEquals(searchWord.toLowerCase()))
						System.out.println("Method name is " + methodName[1]);
					result[1] = methodName[1];
					fileName = file.getName();
					String fileNameTrim = fileName.substring(0,fileName.lastIndexOf("."));
					result[2] = fileNameTrim;
					System.out.println("DEBUG-File name is "+fileNameTrim);

				}

				if ((methodName != null) && (count == 1)) {

					System.out.println("class name is " + className[1]);
					result[0] = className[1];
					count++;
				}
			
			}
			
			scanFile.close();
			
			
			
			
		
		
		} catch (FileNotFoundException e) {
			System.err.println("Search File Not Found !!!!! ");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.err.println("Null pointer exception !!!!! ");
			e.printStackTrace();
		}
		 
		return result;
	}
}

