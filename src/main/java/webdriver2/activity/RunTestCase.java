package webdriver2.activity;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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
		stepRunTestCase(test.getId(),test.getLsActionOfTestCase(),lsDataTestCase);

		// check result
		checkActualResult(test);

	}

	static void stepRunTestCase(String idTestCase, List<ActionOfTestStep> lsStep, List<TestCaseDataInput> lsDataTestCase ) {
		try {
			for (ActionOfTestStep action : lsStep) {
				if (action.getActionWeb().equals(ActionWebEnum.OPENURL)) {
					driver.get(action.getNameWebObject());
					wait.until(presenceOfElementLocated(By.cssSelector(".w3ls_logo_products_left>h1>a")));

				} else if (action.getActionWeb().equals(ActionWebEnum.INPUT)) {
					// nhap input
					if (WebObjectEnum.ID.equals(action.getWebObject())) {
						String dataInput=TestCaseDataInput.getValueOfFiledByIdTestCase(idTestCase, action.getNameWebObject(), lsDataTestCase);
						driver.findElement(By.id(action.getNameWebObject())).sendKeys(dataInput);
						watingView();
					} else if (WebObjectEnum.CLASS.equals(action.getWebObject())) {
						String dataInput=TestCaseDataInput.getValueOfFiledByIdTestCase(idTestCase, action.getNameWebObject(), lsDataTestCase);
						driver.findElement(By.className(action.getNameWebObject())).sendKeys(dataInput);
						watingView();
					}

				} else if (action.getActionWeb().equals(ActionWebEnum.CHECKBOX)) {
					// nhap input
					if (WebObjectEnum.ID.equals(action.getWebObject())) {
						driver.findElement(By.id(action.getNameWebObject())).click();
						watingView();
					} else if (WebObjectEnum.CLASS.equals(action.getWebObject())) {
						driver.findElement(By.className(action.getNameWebObject())).click();
						watingView();
					}
				} else if (action.getActionWeb().equals(ActionWebEnum.CLICK)) {
					// nhap input
					if (WebObjectEnum.ID.equals(action.getWebObject())) {
						driver.findElement(By.id(action.getNameWebObject())).click();
						watingView();
					} else if (WebObjectEnum.CLASS.equals(action.getWebObject())) {
						driver.findElement(By.className(action.getNameWebObject())).click();
						watingView();
					}
				}else if (action.getActionWeb().equals(ActionWebEnum.REFRESH)) {
					driver.navigate().refresh();
					wait.until(presenceOfElementLocated(By.cssSelector(".w3ls_logo_products_left>h1>a")));
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
				if (CheckResultEnumEnum.EXIST.equals(action.getCheckAction())) {
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
					}
				} else if (CheckResultEnumEnum.EMPTY.equals(action.getCheckAction())) {
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
				test.setStatus("Pass");
			} else {
				test.setActualResult(errorBuilder.toString());
				test.setStatus("Fail");
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

		// folder testcase
		final File folderTestCase = new File("../Testcase");
		List<String> dsFileTestCase = listFilesForFolder(folderTestCase);
		
		// folder testcase
		final File folderTestCaseData = new File("../DataInput");
		List<String> dsFileTestCaseData = listFilesForFolder(folderTestCaseData);

		for (int i=0;i<dsFileTestCase.size();i++) {
			// read all test case from excel
			List<TestCase> dsTestCase = ReadExcelToTestCase.readFile(dsFileTestCase.get(i));
			
			// read all test case from excel
			List<TestCaseDataInput> dsTestCaseInput = ReadExcelToTestCase.readFileDataInputOfTestCase(dsFileTestCaseData.get(i));
						
			// run all test case
			for (TestCase testcaseLoop : dsTestCase) {
				getInforToRun();
				runTestCase(testcaseLoop,dsTestCaseInput);
				watingView();
				// quit driverde
				quitDriver();
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
