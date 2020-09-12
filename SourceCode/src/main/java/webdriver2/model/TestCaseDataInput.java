package webdriver2.model;

import java.util.List;
import java.util.stream.Collectors;

public class TestCaseDataInput {
	private String idTestCase;
	private String nameTestCase;
	private List<DataInputFiled> lsDataFiled;
	
	public String getIdTestCase() {
		return idTestCase;
	}
	public void setIdTestCase(String idTestCase) {
		this.idTestCase = idTestCase;
	}
	public String getNameTestCase() {
		return nameTestCase;
	}
	public void setNameTestCase(String nameTestCase) {
		this.nameTestCase = nameTestCase;
	}
	public List<DataInputFiled> getLsDataFiled() {
		return lsDataFiled;
	}
	public void setLsDataFiled(List<DataInputFiled> lsDataFiled) {
		this.lsDataFiled = lsDataFiled;
	}
	public TestCaseDataInput(String idTestCase, String nameTestCase, List<DataInputFiled> lsDataFiled) {
		super();
		this.idTestCase = idTestCase;
		this.nameTestCase = nameTestCase;
		this.lsDataFiled = lsDataFiled;
	}
	
	public TestCaseDataInput() {
		super();
	}
	
	
	public static String getValueOfFiledByIdTestCase(String idTestcase, final String filed,  List<TestCaseDataInput> lsTestCaseDataInput) {
		String value="";
		for(TestCaseDataInput testCaseInput: lsTestCaseDataInput) {
			if(testCaseInput.getIdTestCase()!=null && idTestcase.equals(testCaseInput.getIdTestCase())) {
				value=(String) testCaseInput.getLsDataFiled().stream().filter(p -> p.getNameFiled().equals(filed) == true).map(DataInputFiled::getValueFiled).collect(Collectors.toList()).get(0);
			}
		}
		
		return value;
	}
	
}
