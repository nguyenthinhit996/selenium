package webdriver2.model;

import java.util.Date;
import java.util.List;

public class TestCase {
	private String id;
	private String name;
	private String description;
	private List<ActionOfTestStep> lsActionOfTestCase;
	private List<ActionOfExpectedResult> expectResult;
	private String actualResult;
	private String status;
	private String nameUsertest;
	private Date testedDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ActionOfTestStep> getLsActionOfTestCase() {
		return lsActionOfTestCase;
	}
	public void setLsActionOfTestCase(List<ActionOfTestStep> lsActionOfTestCase) {
		this.lsActionOfTestCase = lsActionOfTestCase;
	}
	public List<ActionOfExpectedResult> getExpectResult() {
		return expectResult;
	}
	public void setExpectResult(List<ActionOfExpectedResult> expectResult) {
		this.expectResult = expectResult;
	}
	public String getActualResult() {
		return actualResult;
	}
	public void setActualResult(String actualResult) {
		this.actualResult = actualResult;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNameUsertest() {
		return nameUsertest;
	}
	public void setNameUsertest(String nameUsertest) {
		this.nameUsertest = nameUsertest;
	}
	public Date getTestedDate() {
		return testedDate;
	}
	public void setTestedDate(Date testedDate) {
		this.testedDate = testedDate;
	}
	public TestCase(String id, String name, String description, List<ActionOfTestStep> lsActionOfTestCase,
			List<ActionOfExpectedResult> expectResult, String actualResult, String status, String nameUsertest,
			Date testedDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.lsActionOfTestCase = lsActionOfTestCase;
		this.expectResult = expectResult;
		this.actualResult = actualResult;
		this.status = status;
		this.nameUsertest = nameUsertest;
		this.testedDate = testedDate;
	}
	
	public TestCase(String id, String name, String description, List<ActionOfTestStep> lsActionOfTestCase,
			List<ActionOfExpectedResult> expectResult, String actualResult) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.lsActionOfTestCase = lsActionOfTestCase;
		this.expectResult = expectResult;
		this.actualResult = actualResult;
		this.status = null;
		this.nameUsertest = null;
		this.testedDate = null;
	}
	 
	public TestCase() {
		super();
	
	}
}
