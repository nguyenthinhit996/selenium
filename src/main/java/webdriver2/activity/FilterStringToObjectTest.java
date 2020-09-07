package webdriver2.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import webdriver2.enumaction.ActionWebEnum;
import webdriver2.enumaction.CheckResultEnumEnum;
import webdriver2.enumaction.WebObjectEnum;
import webdriver2.model.ActionOfExpectedResult;
import webdriver2.model.ActionOfTestStep;

public class FilterStringToObjectTest {
		
	public static List<ActionOfTestStep> filterActionOfTestStep(String str) {
		List<ActionOfTestStep> lsActionOfTestSteps= new ArrayList<ActionOfTestStep>();	 
		if(!StringUtils.isEmpty(str)) {
			String[] arrStep=str.split("\n");
			for(int i=0;i<arrStep.length;i++) {
				String [] arrSplit=arrStep[i].split(" ");
				ActionOfTestStep actionOfTestStep = new ActionOfTestStep();
				if(arrSplit.length ==4) {
					int indexCurrent=StringUtils.isNumeric(arrSplit[0]) == true ? Integer.valueOf(arrSplit[0]) : 0;
					ActionWebEnum actionWebEnum=StringUtils.isEmpty(arrSplit[1]) != true ? ActionWebEnum.getActionWebEnum(arrSplit[1]) : null;
					WebObjectEnum webObjectEnum=StringUtils.isEmpty(arrSplit[2]) != true ? WebObjectEnum.getWebObjectEnum(arrSplit[2]) : null;
					 String nameWebObject=StringUtils.isEmpty(arrSplit[3]) != true ?arrSplit[3] : null;
					 actionOfTestStep.setIndexCurrent(indexCurrent);
					 actionOfTestStep.setActionWeb(actionWebEnum);
					 actionOfTestStep.setWebObject(webObjectEnum);
					 actionOfTestStep.setNameWebObject(nameWebObject);
					 lsActionOfTestSteps.add(actionOfTestStep);
				}else if(arrSplit.length == 5) {
					int indexCurrent=StringUtils.isNumeric(arrSplit[0]) == true ? Integer.valueOf(arrSplit[0]) : 0;
					ActionWebEnum actionWebEnum=StringUtils.isEmpty(arrSplit[1]) != true ? ActionWebEnum.getActionWebEnum(arrSplit[1]) : null;
					WebObjectEnum webObjectEnum=StringUtils.isEmpty(arrSplit[2]) != true ? WebObjectEnum.getWebObjectEnum(arrSplit[2]) : null;
					 String nameWebObject=StringUtils.isEmpty(arrSplit[3]) != true ?arrSplit[3] : null;
					 String content = StringUtils.isEmpty(arrSplit[4]) != true ? arrSplit[4]: null;
					 actionOfTestStep.setIndexCurrent(indexCurrent);
					 actionOfTestStep.setActionWeb(actionWebEnum);
					 actionOfTestStep.setWebObject(webObjectEnum);
					 actionOfTestStep.setNameWebObject(nameWebObject);
					 actionOfTestStep.setContent(content);
					 lsActionOfTestSteps.add(actionOfTestStep);
				}
			}
			 
		}
		return lsActionOfTestSteps;
	}
	
	
	public static List<ActionOfExpectedResult> fillterActionOfExpectedResult(String str) {
		List<ActionOfExpectedResult> lsActionOfTestSteps= new ArrayList<ActionOfExpectedResult>();	 
		if(!StringUtils.isEmpty(str)) {
			String[] arrStep=str.split("\n");
			for(int i=0;i<arrStep.length;i++) {
				String [] arrSplit=arrStep[i].split(" ");
				ActionOfExpectedResult actionOfTestStep = new ActionOfExpectedResult();
				if(arrSplit.length ==4) {
					int indexCurrent=StringUtils.isNumeric(arrSplit[0]) == true ? Integer.valueOf(arrSplit[0]) : 0;
					CheckResultEnumEnum checkResultEnumEnum=StringUtils.isEmpty(arrSplit[1]) != true ? CheckResultEnumEnum.getCheckResultEnumEnum(arrSplit[1]) : null;
					WebObjectEnum webObjectEnum=StringUtils.isEmpty(arrSplit[2]) != true ? WebObjectEnum.getWebObjectEnum(arrSplit[2]) : null;
					 String nameWebObject=StringUtils.isEmpty(arrSplit[3]) != true ?arrSplit[3] : null;
					 actionOfTestStep.setIndexCurrent(indexCurrent);
					 actionOfTestStep.setCheckAction(checkResultEnumEnum);
					 actionOfTestStep.setWebObject(webObjectEnum);
					 actionOfTestStep.setNameOfwebObject(nameWebObject);				 
					 lsActionOfTestSteps.add(actionOfTestStep);
				}else if(arrSplit.length == 6) {
					int indexCurrent=StringUtils.isNumeric(arrSplit[0]) == true ? Integer.valueOf(arrSplit[0]) : 0;
					CheckResultEnumEnum checkResultEnumEnum=StringUtils.isEmpty(arrSplit[1]) != true ? CheckResultEnumEnum.getCheckResultEnumEnum(arrSplit[1]) : null;
					WebObjectEnum webObjectEnum=StringUtils.isEmpty(arrSplit[2]) != true ? WebObjectEnum.getWebObjectEnum(arrSplit[2]) : null;
					 String nameWebObject=StringUtils.isEmpty(arrSplit[3]) != true ?arrSplit[3] : null;
					WebObjectEnum valueOf=StringUtils.isEmpty(arrSplit[4]) != true ? WebObjectEnum.getWebObjectEnum(arrSplit[4]) : null;
					 String content=StringUtils.isEmpty(arrSplit[5]) != true ?arrSplit[5] : null; 
					
					 actionOfTestStep.setIndexCurrent(indexCurrent);
					 actionOfTestStep.setCheckAction(checkResultEnumEnum);
					 actionOfTestStep.setWebObject(webObjectEnum);
					 actionOfTestStep.setNameOfwebObject(nameWebObject);	
					 actionOfTestStep.setValueOf(valueOf);
					 actionOfTestStep.setContentwebObject(content);
					 lsActionOfTestSteps.add(actionOfTestStep);
				}
			}
			 
		}
		return lsActionOfTestSteps;
	}
}


