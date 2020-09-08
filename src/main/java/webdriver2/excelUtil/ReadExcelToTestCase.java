package webdriver2.excelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import webdriver2.activity.FilterStringToObjectTest;
import webdriver2.model.ActionOfExpectedResult;
import webdriver2.model.ActionOfTestStep;
import webdriver2.model.DataInputFiled;
import webdriver2.model.TestCase;
import webdriver2.model.TestCaseDataInput;

public class ReadExcelToTestCase {

	static DataFormatter formatter = new DataFormatter();

	public static List<TestCase> readFile(final String fileName) {
		List<TestCase> lsTestCase= new ArrayList<TestCase>();
		File file=null;
		FileInputStream fis= null;
		try {
			file = new File(fileName); // creating a new file instance
			fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
			int rowIndex = 1;
			while (itr.hasNext()) {
				if (rowIndex == 1) {
					itr.next();
					rowIndex++;
					continue;
				}
				Row row = itr.next();
				
				TestCase test=new TestCase();
				List<ActionOfTestStep> lsStepInput;
				List<ActionOfExpectedResult> lsExpect;

				Cell cellId =row.getCell(0);
				String id = formatter.formatCellValue(cellId);
				test.setId(id.trim());
				
				Cell cellname =row.getCell(1);
				String name = formatter.formatCellValue(cellname);
				test.setName(name.trim());
				
				Cell celllsActionOfTestCase =row.getCell(2);
				String lscelllsActionOfTestCase = formatter.formatCellValue(celllsActionOfTestCase);
				lsStepInput = FilterStringToObjectTest.filterActionOfTestStep(lscelllsActionOfTestCase.trim());
				test.setLsActionOfTestCase(lsStepInput);
				test.setDescription("none");
				
				Cell cellexpectResult =row.getCell(3);
				String lsexpectResult = formatter.formatCellValue(cellexpectResult);
				lsExpect = FilterStringToObjectTest.fillterActionOfExpectedResult(lsexpectResult.trim());
				test.setExpectResult(lsExpect);
				 
				Cell cellactualResult =row.getCell(4);
				String actualResult = formatter.formatCellValue(cellactualResult);
				test.setActualResult(actualResult.trim());
				
				Cell cellstatus =row.getCell(5);
				String status = formatter.formatCellValue(cellstatus);	 
				test.setStatus(status.trim());
				
				Cell cellnameUsertest =row.getCell(6);
				String nameUsertest = formatter.formatCellValue(cellnameUsertest);
				if(StringUtils.isEmpty(nameUsertest)) {
					test.setNameUsertest("Thinh");
				}
				
				Cell celltestedDate =row.getCell(7);
				String testedDate = formatter.formatCellValue(celltestedDate);
				 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
				try {
					 Date date1=formatter.parse(testedDate.trim()); 
						test.setTestedDate(date1);
				}catch (Exception e) {
					System.out.print("time null");
					  Date date = new Date();
					test.setTestedDate(date);
				}
				
				lsTestCase.add(test);
			
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lsTestCase;
	}

	public static List<TestCaseDataInput> readFileDataInputOfTestCase(final String fileName) {
		List<TestCaseDataInput> lsTestCase= new ArrayList<TestCaseDataInput>();
		File file=null;
		FileInputStream fis= null;
		try {
			file = new File(fileName); // creating a new file instance
			fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file

			Row rowFirst = itr.next();
			int numOfFiled=2;
			List<String> lsNameOfFiled= new ArrayList<String>();
			
			while(true) {
				Cell cellFirst=rowFirst.getCell(numOfFiled);
				if(cellFirst == null || StringUtils.isEmpty(formatter.formatCellValue(cellFirst))) {
					break;
				}
				lsNameOfFiled.add(formatter.formatCellValue(cellFirst).trim());
				numOfFiled++;
			}
			
			while (itr.hasNext()) {
				Row row = itr.next();			
				TestCaseDataInput test=new TestCaseDataInput();
 
				Cell cellId =row.getCell(0);
				String id = formatter.formatCellValue(cellId);
				test.setIdTestCase(id.trim());
				
				Cell cellname =row.getCell(1);
				String name = formatter.formatCellValue(cellname);
				test.setNameTestCase(name.trim());
				
				List<DataInputFiled> lsDataInputFiled= new ArrayList<DataInputFiled>();
				for(int i=0;i<lsNameOfFiled.size();i++) {
					DataInputFiled dataNew= new DataInputFiled();
					dataNew.setNameFiled(lsNameOfFiled.get(i));
					
					// 0 id, 1 name, start of value is 2
					Cell cellValue =row.getCell(i+2);
					if(cellValue != null) {
						String value = formatter.formatCellValue(cellValue);	 
						dataNew.setValueFiled(value);
					}else {
						// set default value of Filed blank
						dataNew.setValueFiled("");
					}
					lsDataInputFiled.add(dataNew);
				}
				test.setLsDataFiled(lsDataInputFiled);
				lsTestCase.add(test);	
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lsTestCase;
	}
	
	public static void main(String[] args) {
		//readExcelToTestCase.readFile();
	}

}
