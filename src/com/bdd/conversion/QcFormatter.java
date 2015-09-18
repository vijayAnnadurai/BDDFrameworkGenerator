package com.bdd.conversion;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;

import junit.framework.TestCase;

public class QcFormatter implements ScenarioFinder {

	/*
	 * This method will convert QC test case format into feature and step
	 * definition files
	 */

	public void qcFormat(BufferedWriter outFeature, BufferedWriter outScenario,
			XSSFSheet sheet) {
		String[] result = new String[3];
		int countSteps = 0;
		// to iterate over rows in excel sheet
		Iterator<Row> rowIterator = sheet.iterator();

		for (int rowCount = 1; rowIterator.hasNext(); rowCount++) {
			String cellval = null;
			Row row = rowIterator.next();

			// to iterate over columns in each row
			Iterator<Cell> cellIterator = row.cellIterator();
			for (int colCount = 1; cellIterator.hasNext(); colCount++) {
				Cell cell = cellIterator.next();

				cellval = cell.getStringCellValue();

				if (cellval.equalsIgnoreCase("testcase name")) {
					Cell scenarioCell = cellIterator.next();
					scenarioFinder(scenarioCell.getStringCellValue(),
							outFeature);
				}

				if (cellval.equalsIgnoreCase("description")) {
					System.out.println("Inside description");
					// method to print test case steps
					testCaseWriter(rowIterator, outFeature, outScenario);

				}

			}
		}
	}

	// To retieve all the keywords in excel sheet and store it in string array
	public String keywordRetriever(Row row) {

		String cellValue = null;

		Iterator<Cell> cIt = row.cellIterator();
		for (int col = 1; cIt.hasNext(); col++) {
			Cell cell = cIt.next();
			if (col == 4) {

				cellValue = cell.getStringCellValue();

			}

		}
		return cellValue;

	}

	/*
	 * This method will find scenarios from test cases and writes it into
	 * feature files
	 */

	public void scenarioFinder(String scenarioName, BufferedWriter outScenario) {

		System.out.println("Scenario name : " + scenarioName);

		try {
			outScenario.newLine();
			outScenario.append("Scenario: " + scenarioName);
			outScenario.newLine();
			outScenario.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// method to print steps from test case sheet
	@SuppressWarnings("unused")
	private void testCaseWriter(Iterator<Row> rowItr,
			BufferedWriter outFeature, BufferedWriter outStepDef) {
		// creating object for test case class
		TestCase tc = new TestCase();

		String result[] = new String[3];
		int count = 0;
		Cell celValue = null;

		String value = "";
		for (int rowCount = 1; rowItr.hasNext(); rowCount++) {
			String cellval = null;
			Row row = rowItr.next();
			// to iterate over columns in each row
			// converting null to blank using Missing Cell Policy to avoid Null
			// pointer Exception
			celValue = row.getCell(1, row.CREATE_NULL_AS_BLANK);
			switch (celValue.getCellType()) {

			case Cell.CELL_TYPE_BLANK:

				break;

			case Cell.CELL_TYPE_STRING:
				Iterator<Cell> cIt = row.cellIterator();
				for (int col = 1; cIt.hasNext(); col++) {
					Cell cell = cIt.next();
					String cellVal = cell.getStringCellValue();

					// vector
					/*
					 * cellVectorValue.addElement(cellVal); String keyword =
					 * cellVectorValue.get(colCount+1).toString();
					 */

					if (cellVal.equalsIgnoreCase("testcase name")) {

						Cell scenarioCell = cIt.next();
						scenarioFinder(scenarioCell.getStringCellValue(),
								outFeature);
						break;
					}
					if (cellVal.equalsIgnoreCase("description")) {

						break;
					}
					// to identify description column
					if (col == 2) {
						try {

							String keyword = keywordRetriever(row);
							System.out.println("DEBUG-Keyword is" + keyword);
							result = tc.validateTestcase(cellVal,keyword);
							String featureSteps = result[0];
							String stepDefSteps = result[1];
							outFeature.append("   " + featureSteps);
							outStepDef.append("\n");
							outStepDef.append(stepDefSteps);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					// to identify expected result column
					if (col == 3) {
						try {
							String keyword = keywordRetriever(row);
							System.out.println("DEBUG-Keyword is" + keyword);
							
							result = tc.writeToThen(cellVal,keyword);
							String featureSteps = result[0];
							String stepDefSteps = result[1];
							outFeature.append("   " + featureSteps);
							outStepDef.append("\n");
							outStepDef.append(stepDefSteps);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					count++;
				}
			}
		}
		System.out.println(count);


