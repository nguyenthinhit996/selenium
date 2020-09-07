package webdriver2.excelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import webdriver2.activity.FilterStringToObjectTest;
import webdriver2.model.ActionOfTestStep;

public class readExcelToTestCase {

	static DataFormatter formatter = new DataFormatter();

	static void readFile() {
		try {
			File file = new File("testcase/testcasedemo.xlsx"); // creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
			int rowIndex = 1;
			List<ActionOfTestStep> lsStepInput;
			while (itr.hasNext()) {
				if (rowIndex == 1) {
					itr.next();
					rowIndex++;
					continue;
				}
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				int cellIndex = 1;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String val = formatter.formatCellValue(cell);
					System.out.print(val + " ~ ");
					if (cellIndex == 3) {
						lsStepInput = FilterStringToObjectTest.filterActionOfTestStep(val);
					}
					cellIndex++;
				}
				System.out.println("");
			}

			System.out.println("tôi sẽ đi đó ở nhà");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		readExcelToTestCase.readFile();
	}

}
