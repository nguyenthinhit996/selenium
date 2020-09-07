package webdriver2.excelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import webdriver2.model.TestCase;

public class WriteTestCaseInToExcel {
	
	static DataFormatter formatter = new DataFormatter();
	
	public static void writeTestcaseIntoExcel(final String fileName, List<TestCase> lsTestCase) {
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
				Cell cellId =row.getCell(0);
				String id = formatter.formatCellValue(cellId);
				
				for(TestCase testcaseloop: lsTestCase) {
					if(testcaseloop.getId().equals(id)) {
						// set actual result
						if(row.getCell(4)==null) {
							row.createCell(4).setCellValue(testcaseloop.getActualResult());
						}else {
							row.getCell(4).setCellValue(testcaseloop.getActualResult());
						}
						
						// set status
						if(row.getCell(5)==null) {
							row.createCell(5).setCellValue(testcaseloop.getStatus());
						}else {
							row.getCell(5).setCellValue(testcaseloop.getStatus());
						}
						
						// set color
						setBackgroudColorPassFail(testcaseloop.getStatus(),wb,row.getCell(5));
						
						// set user test
						if(row.getCell(6)==null) {
							row.createCell(6).setCellValue(testcaseloop.getNameUsertest());
						}else {
							row.getCell(6).setCellValue(testcaseloop.getNameUsertest());
						}
					
						
						// set date test
						if(row.getCell(7)==null) {
							  Date date = Calendar.getInstance().getTime();  
				                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
				                String strDate = dateFormat.format(date);  
							row.createCell(7).setCellValue(strDate);
						}else {
							row.getCell(7).setCellValue(testcaseloop.getTestedDate());
						}
					}
				}
			}
			// write file resutl 
			String nameFileResult="../ResultTest"+"/Result_"+file.getName();
			File yourFile = new File(nameFileResult);
			yourFile.getParentFile().mkdirs(); // Will create parent directories if not exists
			yourFile.createNewFile();
			 FileOutputStream outputStream = new FileOutputStream(yourFile);
			 wb.write(outputStream);
			 outputStream.close();
			 fis.close();
			 System.out.println("==================Test Done=========================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void setBackgroudColorPassFail(String status, XSSFWorkbook wb, Cell cell ) {
		   CellStyle style = wb.createCellStyle();  
		   // Set up font
	        Font font = wb.createFont();
	        font.setColor(IndexedColors.WHITE.getIndex());
	        font.setBold(true);
		   if("Pass".equals(status)) {
			   // Setting Background color  
			   style.setFont(font);
	           style.setFillForegroundColor(IndexedColors.GREEN.getIndex());  
	           style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		   }else {
			   // Setting Background color  
			   style.setFont(font);
	           style.setFillForegroundColor(IndexedColors.RED.getIndex());  
	           style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		   }
		   cell.setCellStyle(style);  
	}
}
