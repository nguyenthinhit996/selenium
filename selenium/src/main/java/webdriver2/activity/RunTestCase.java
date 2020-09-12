package webdriver2.activity;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import webdriver2.enumaction.ActionWebEnum;
import webdriver2.enumaction.CheckResultEnumEnum;
import webdriver2.enumaction.WebObjectEnum;
import webdriver2.excelUtil.ReadExcelToTestCase;
import webdriver2.excelUtil.WriteTestCaseInToExcel;
import webdriver2.model.ActionOfExpectedResult;
import webdriver2.model.ActionOfTestStep;
import webdriver2.model.TestCase;
import webdriver2.model.TestCaseDataInput;

public class RunTestCase {

	final static Logger appLogger = Logger.getLogger(RunTestCase.class);
	static WebDriverWait wait = null;
	static WebDriver driver;
	final static long numberSecondViewApp = 0;

	static void getInforToRun() {
		String current = null;
		try {
			current = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Current dir:" + current);
		System.setProperty("webdriver.gecko.driver", "./browserLib/geckodriver.exe");
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		/*
		 * ew WebDriverWait(driver, 1).until( webDriver -> ((JavascriptExecutor)
		 * webDriver).executeScript("return document.readyState").equals("complete"));
		 */

	}

	static void runTestCase(TestCase test,List<TestCaseDataInput> lsDataTestCase ) {
		// action step test
	
		appLogger.info("Start Run Step TestCase");
		stepRunTestCase(test.getId(),test.getLsActionOfTestCase(),lsDataTestCase);
		appLogger.info("End Run Step TestCase");

		appLogger.info("Start Run Check Result TestCase");
		// check result
		checkActualResult(test);
		appLogger.info("End Run Check Result TestCase");

	}

	static void stepRunTestCase(String idTestCase, List<ActionOfTestStep> lsStep, List<TestCaseDataInput> lsDataTestCase ) {
		try {
			for (ActionOfTestStep action : lsStep) {
				
				appLogger.info(action.getIndexCurrent());
				
				if (action.getActionWeb().equals(ActionWebEnum.OPENURL)) {
					driver.get(action.getNameWebObject());
					wait.until(presenceOfElementLocated(By.cssSelector(".w3ls_logo_products_left")));
					appLogger.info("OPENURL "+action.getNameWebObject());
				} else if (action.getActionWeb().equals(ActionWebEnum.INPUT)) {
					// nhap input
					if (WebObjectEnum.ID.equals(action.getWebObject())) {
						String dataInput=TestCaseDataInput.getValueOfFiledByIdTestCase(idTestCase, action.getNameWebObject(), lsDataTestCase);
						driver.findElement(By.id(action.getNameWebObject())).sendKeys(dataInput);
						watingView();
						appLogger.info("INPUT data id "+action.getNameWebObject()+" Value " +dataInput);
					} else if (WebObjectEnum.CLASS.equals(action.getWebObject())) {
						String dataInput=TestCaseDataInput.getValueOfFiledByIdTestCase(idTestCase, action.getNameWebObject(), lsDataTestCase);
						driver.findElement(By.className(action.getNameWebObject())).sendKeys(dataInput);
						watingView();
						appLogger.info("INPUT data class "+action.getNameWebObject()+" Value " +dataInput);
					}

				} else if (action.getActionWeb().equals(ActionWebEnum.CHECKBOX)) {
					// nhap input
					if (WebObjectEnum.ID.equals(action.getWebObject())) {
						driver.findElement(By.id(action.getNameWebObject())).click();
						watingView();
						appLogger.info("CHECKBOX  id "+action.getNameWebObject());
					} else if (WebObjectEnum.CLASS.equals(action.getWebObject())) {
						driver.findElement(By.className(action.getNameWebObject())).click();
						watingView();
						appLogger.info("CHECKBOX  class "+action.getNameWebObject());
					}
				} else if (action.getActionWeb().equals(ActionWebEnum.CLICK)) {
					// nhap input
					if (WebObjectEnum.ID.equals(action.getWebObject())) {
						driver.findElement(By.id(action.getNameWebObject())).click();
						watingView();
						appLogger.info("CLICK id "+action.getNameWebObject());
					} else if (WebObjectEnum.CLASS.equals(action.getWebObject())) {
						driver.findElement(By.className(action.getNameWebObject())).click();
						watingView();
						appLogger.info("CLICK class "+action.getNameWebObject());
					}
				}else if (action.getActionWeb().equals(ActionWebEnum.REFRESH)) {
					driver.navigate().refresh();
					wait.until(presenceOfElementLocated(By.cssSelector(".w3ls_logo_products_left>h1>a")));
					appLogger.info("REFRESH page "+ driver.getCurrentUrl());
				}else if (action.getActionWeb().equals(ActionWebEnum.SELECT)) {
					// nhap input
					if (WebObjectEnum.ID.equals(action.getWebObject())) {
						String dataInput=TestCaseDataInput.getValueOfFiledByIdTestCase(idTestCase, action.getNameWebObject(), lsDataTestCase);
						Select fruits = new Select(driver.findElement(By.id(action.getNameWebObject())));
						fruits.selectByVisibleText(dataInput.trim());
						watingView();
						appLogger.info("SELECT ID: "+dataInput);
					} else if (WebObjectEnum.CLASS.equals(action.getWebObject())) {
						String dataInput=TestCaseDataInput.getValueOfFiledByIdTestCase(idTestCase, action.getNameWebObject(), lsDataTestCase);
						Select fruits = new Select(driver.findElement(By.className(action.getNameWebObject())));
						fruits.selectByVisibleText(dataInput.trim());
						watingView();
						appLogger.info("SELECT CLASS: "+dataInput);
					}
				}
			}
		} catch (Exception e) {
			quitDriver();
		}
	}

	static void checkActualResult(TestCase test) {
		try {
			StringBuffer errorBuilder = new StringBuffer();
			List<ActionOfExpectedResult> lsExpectedResults = test.getExpectResult();
			for (ActionOfExpectedResult action : lsExpectedResults) {
				appLogger.info("checkActualResult "+action.getIndexCurrent());
				if (CheckResultEnumEnum.EXIST.equals(action.getCheckAction())) {
					appLogger.info("checkActualResult EXIST "+action.getNameOfwebObject());
					if (WebObjectEnum.ID.equals(action.getWebObject())) {
						try {
							WebElement element = driver.findElement(By.id(action.getNameOfwebObject()));
						} catch (NoSuchElementException e) {
							errorBuilder.append("Not EXIST ID: " + action.getNameOfwebObject());
							errorBuilder.append("\n");
						}
					} else if (WebObjectEnum.CLASS.equals(action.getWebObject())) {
						try {
							WebElement element = driver.findElement(By.className(action.getNameOfwebObject()));
						} catch (NoSuchElementException e) {
							errorBuilder.append("Not EXIST CLASS: " + action.getNameOfwebObject());
							errorBuilder.append("\n");
						}
					}

				} else if (CheckResultEnumEnum.NOTEXIST.equals(action.getCheckAction())) {
					appLogger.info("checkActualResult NOTEXIST "+action.getNameOfwebObject());
					if (WebObjectEnum.ID.equals(action.getWebObject())) {
						try {
							WebElement element = driver.findElement(By.id(action.getNameOfwebObject()));
							errorBuilder.append("EXIST ID: " + action.getNameOfwebObject());
							errorBuilder.append("\n");
						} catch (NoSuchElementException e) {

						}
					} else if (WebObjectEnum.CLASS.equals(action.getWebObject())) {
						try {
							WebElement element = driver.findElement(By.className(action.getNameOfwebObject()));
							errorBuilder.append("EXIST CLASS: " + action.getNameOfwebObject());
							errorBuilder.append("\n");
						} catch (NoSuchElementException e) {

						}
					}

				} else if (CheckResultEnumEnum.EQUAL.equals(action.getCheckAction())) {
					appLogger.info("checkActualResult EQUAL "+action.getNameOfwebObject());
					if (WebObjectEnum.ID.equals(action.getWebObject())) {
						try {
							WebElement element = driver.findElement(By.id(action.getNameOfwebObject()));
							String textId = element.getText();
							if (action.getValueOf() != null) {
								if (!action.getContentwebObject().equals(textId)) {
									errorBuilder.append("VALUE OF ID NOT EQUAL: " + action.getContentwebObject());
									errorBuilder.append("\n");
								}
							}
						} catch (NoSuchElementException e) {
							errorBuilder.append("Not EXIST ID: " + action.getNameOfwebObject());
							errorBuilder.append("\n");
						}
					} else if (WebObjectEnum.CLASS.equals(action.getWebObject())) {
						try {
							WebElement element = driver.findElement(By.className(action.getNameOfwebObject()));
							String textClass = element.getText();
							if (action.getValueOf() != null) {
								if (!action.getContentwebObject().equals(textClass)) {
									errorBuilder.append("VALUE OF CLASS NOT EQUAL: " + action.getContentwebObject());
									errorBuilder.append("\n");
								}
							}
						} catch (NoSuchElementException e) {
							errorBuilder.append("Not EXIST CLASS: " + action.getNameOfwebObject());
							errorBuilder.append("\n");
						}
					} else if (WebObjectEnum.LINK.equals(action.getWebObject())) {
						try {
							String element = driver.getCurrentUrl();
							if (!action.getNameOfwebObject().equals(element)) {
								errorBuilder.append("VALUE OF URL NOT EQUAL: " + element);
								errorBuilder.append("\n");
							}
						} catch (Exception e) {
							errorBuilder.append("Not GET URL : " + action.getNameOfwebObject());
							errorBuilder.append("\n");
						}

					}
				} else if (CheckResultEnumEnum.NOTEMPTY.equals(action.getCheckAction())) {
					appLogger.info("checkActualResult NOTEMPTY "+action.getNameOfwebObject());
					if (WebObjectEnum.ID.equals(action.getWebObject())) {
						try {
							WebElement element = driver.findElement(By.id(action.getNameOfwebObject()));
							String textId = element.getText(); 
							if (StringUtils.isEmpty(textId)) {
								errorBuilder.append("VALUE OF ID EMPTY: ");
								errorBuilder.append("\n");
							}
						} catch (NoSuchElementException e) {
							errorBuilder.append("Not EXIST ID: " + action.getNameOfwebObject());
							errorBuilder.append("\n");	
						}
					} else if (WebObjectEnum.CLASS.equals(action.getWebObject())) {
						try {
							WebElement element = driver.findElement(By.className(action.getNameOfwebObject()));
							String textClass = element.getText();
							if (StringUtils.isEmpty(textClass)) {
								errorBuilder.append("VALUE OF CLASS EMPTY: ");
								errorBuilder.append("\n");
							}
						} catch (NoSuchElementException e) {
							errorBuilder.append("Not EXIST CLASS: " + action.getNameOfwebObject());
							errorBuilder.append("\n");
						}
					}else if (WebObjectEnum.ALERT.equals(action.getWebObject())) {
						try {
							 Alert alert = driver.switchTo().alert();
							String textAlert = alert.getText();
							if (StringUtils.isEmpty(textAlert)) {
								errorBuilder.append("TEXT OF ALERT EMPTY ");
								errorBuilder.append("\n");
							}
						} catch (Exception e) {
							errorBuilder.append("Not Find ALERT: " + action.getNameOfwebObject());
							errorBuilder.append("\n");
						}
					}
				} else if (CheckResultEnumEnum.EMPTY.equals(action.getCheckAction())) {
					appLogger.info("checkActualResult EMPTY "+action.getNameOfwebObject());
					if (WebObjectEnum.ID.equals(action.getWebObject())) {
						try {
							WebElement element = driver.findElement(By.id(action.getNameOfwebObject()));
							String textId = element.getText();
							if (!StringUtils.isEmpty(textId)) {
								errorBuilder.append("VALUE OF ID NOT EMPTY: "+textId);
								errorBuilder.append("\n");
							}
						} catch (NoSuchElementException e) {
							errorBuilder.append("Not EXIST ID: " + action.getNameOfwebObject());
							errorBuilder.append("\n");
						}
					} else if (WebObjectEnum.CLASS.equals(action.getWebObject())) {
						try {
							WebElement element = driver.findElement(By.className(action.getNameOfwebObject()));
							String textClass = element.getText();
							if (!StringUtils.isEmpty(textClass)) {
								errorBuilder.append("VALUE OF CLASS NOT EMPTY: "+textClass);
								errorBuilder.append("\n");
							}

						} catch (NoSuchElementException e) {
							errorBuilder.append("Not EXIST CLASS: " + action.getNameOfwebObject());
							errorBuilder.append("\n");
						}
					}
				}else if (CheckResultEnumEnum.VISIBLE.equals(action.getCheckAction())) {
					appLogger.info("checkActualResult VISIBLE "+action.getNameOfwebObject());
					if (WebObjectEnum.ID.equals(action.getWebObject())) {
						try {
							boolean visibility = driver.findElement(By.id(action.getNameOfwebObject())).isDisplayed();
							if (!visibility) {
								errorBuilder.append("ID Display HIDDEN : ");
								errorBuilder.append("\n");
							}
						} catch (NoSuchElementException e) {
							errorBuilder.append("Not EXIST ID: " + action.getNameOfwebObject());
							errorBuilder.append("\n");
						}
					} else if (WebObjectEnum.CLASS.equals(action.getWebObject())) {
						try {
							boolean visibility = driver.findElement(By.className(action.getNameOfwebObject())).isDisplayed();
							if (!visibility) {
								errorBuilder.append("CLASS Display HIDDEN : ");
								errorBuilder.append("\n");
							}

						} catch (NoSuchElementException e) {
							errorBuilder.append("Not EXIST CLASS: " + action.getNameOfwebObject());
							errorBuilder.append("\n");
						}
					}
				}else if (CheckResultEnumEnum.HIDDEN.equals(action.getCheckAction())) {
					appLogger.info("checkActualResult HIDDEN "+action.getNameOfwebObject());
					if (WebObjectEnum.ID.equals(action.getWebObject())) {
						try {
							boolean visibility = driver.findElement(By.id(action.getNameOfwebObject())).isDisplayed();
							if (visibility) {
								errorBuilder.append("ID Display VISIBLE : ");
								errorBuilder.append("\n");
							}
						} catch (NoSuchElementException e) {
							errorBuilder.append("Not EXIST ID: " + action.getNameOfwebObject());
							errorBuilder.append("\n");
						}
					} else if (WebObjectEnum.CLASS.equals(action.getWebObject())) {
						try {
							boolean visibility = driver.findElement(By.className(action.getNameOfwebObject())).isDisplayed();
							if (visibility) {
								errorBuilder.append("CLASS Display VISIBLE : ");
								errorBuilder.append("\n");
							}

						} catch (NoSuchElementException e) {
							errorBuilder.append("Not EXIST CLASS: " + action.getNameOfwebObject());
							errorBuilder.append("\n");
						}
					}
				}
			}
			if (errorBuilder.length() == 0) {
				test.setActualResult("same with ExpectedResult");
				appLogger.info("same with ExpectedResult");
				test.setStatus("Pass");
				appLogger.info("Pass");
			} else {
				test.setActualResult(errorBuilder.toString());
				appLogger.info(errorBuilder.toString());
				test.setStatus("Fail");
				appLogger.info("Fail");
			}

		} catch (Exception e) {
			quitDriver();
		}

	}

	static void quitDriver() {
		driver.quit();
	}

	static List<String> listFilesForFolder(final File folder) {
		List<String> fileTestCase = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				fileTestCase.add(fileEntry.getAbsolutePath());
			}
		}
		return fileTestCase;
	}

	public static void main(String[] args) {

		appLogger.info("==============Run main=========================");
		// folder testcase
		final File folderTestCase = new File("../Testcase");
		List<String> dsFileTestCase = listFilesForFolder(folderTestCase);
		
		// folder testcase
		final File folderTestCaseData = new File("../DataInput");
		List<String> dsFileTestCaseData = listFilesForFolder(folderTestCaseData);

		for (int i=0;i<dsFileTestCase.size();i++) {
			// read all test case from excel
			List<TestCase> dsTestCase = ReadExcelToTestCase.readFile(dsFileTestCase.get(i));
			appLogger.info("======== Read TestCase ========= "+dsFileTestCase.get(i));
			// read all test case from excel
			List<TestCaseDataInput> dsTestCaseInput = ReadExcelToTestCase.readFileDataInputOfTestCase(dsFileTestCaseData.get(i));
			appLogger.info("======== Read Input TestCase ========= "+dsFileTestCaseData.get(i));
			// run all test case
			for (TestCase testcaseLoop : dsTestCase) {
				appLogger.info("Start========================="+testcaseLoop.getName()+"=================================");
				getInforToRun();
				runTestCase(testcaseLoop,dsTestCaseInput);
				watingView();
				// quit driverde
				quitDriver();			
				appLogger.info("End==========================="+testcaseLoop.getName()+"=================================");
			}

			// write all test case to excel
			 WriteTestCaseInToExcel.writeTestcaseIntoExcel(dsFileTestCase.get(i), dsTestCase);
		}
	}

	static void watingView() {
		try {
			Thread.sleep(numberSecondViewApp);
		} catch (Exception e) {
			System.out.println("Not Wating view !!!");
		}
	}
}
