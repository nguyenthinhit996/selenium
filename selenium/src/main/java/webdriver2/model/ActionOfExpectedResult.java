package webdriver2.model;

import webdriver2.enumaction.CheckResultEnumEnum;
import webdriver2.enumaction.WebObjectEnum;

public class ActionOfExpectedResult {
	private int indexCurrent;
	private CheckResultEnumEnum checkAction;
	private WebObjectEnum webObject;
	private String nameOfwebObject;
	private WebObjectEnum valueOf;
	private String contentwebObject;
	
	public int getIndexCurrent() {
		return indexCurrent;
	}
	public void setIndexCurrent(int indexCurrent) {
		this.indexCurrent = indexCurrent;
	}
	public CheckResultEnumEnum getCheckAction() {
		return checkAction;
	}
	public void setCheckAction(CheckResultEnumEnum checkAction) {
		this.checkAction = checkAction;
	}
	public WebObjectEnum getWebObject() {
		return webObject;
	}
	public void setWebObject(WebObjectEnum webObject) {
		this.webObject = webObject;
	}
	public String getNameOfwebObject() {
		return nameOfwebObject;
	}
	public void setNameOfwebObject(String nameOfwebObject) {
		this.nameOfwebObject = nameOfwebObject;
	}
	public WebObjectEnum getValueOf() {
		return valueOf;
	}
	public void setValueOf(WebObjectEnum valueOf) {
		this.valueOf = valueOf;
	}
	public String getContentwebObject() {
		return contentwebObject;
	}
	
	public void setContentwebObject(String contentwebObject) {
		this.contentwebObject = contentwebObject;
	}
	
	public ActionOfExpectedResult(int indexCurrent, CheckResultEnumEnum checkAction, WebObjectEnum webObject,
			String nameOfwebObject, WebObjectEnum valueOf, String contentwebObject) {
		super();
		this.indexCurrent = indexCurrent;
		this.checkAction = checkAction;
		this.webObject = webObject;
		this.nameOfwebObject = nameOfwebObject;
		this.valueOf = valueOf;
		this.contentwebObject = contentwebObject;
	}
	
	
	public ActionOfExpectedResult(int indexCurrent, CheckResultEnumEnum checkAction, WebObjectEnum webObject,
			String nameOfwebObject) {
		super();
		this.indexCurrent = indexCurrent;
		this.checkAction = checkAction;
		this.webObject = webObject;
		this.nameOfwebObject = nameOfwebObject;
		this.valueOf = null;
		this.contentwebObject = null;
	}
	public ActionOfExpectedResult() {
		super();
	}
}
