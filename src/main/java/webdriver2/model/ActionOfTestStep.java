package webdriver2.model;

import webdriver2.enumaction.ActionWebEnum;
import webdriver2.enumaction.WebObjectEnum;

public class ActionOfTestStep {
	private int indexCurrent;
	private ActionWebEnum actionWeb;
	private WebObjectEnum webObject;
	private String nameWebObject;
	private String content;
	public int getIndexCurrent() {
		return indexCurrent;
	}
	public void setIndexCurrent(int indexCurrent) {
		this.indexCurrent = indexCurrent;
	}
	public ActionWebEnum getActionWeb() {
		return actionWeb;
	}
	public void setActionWeb(ActionWebEnum actionWeb) {
		this.actionWeb = actionWeb;
	}
	public WebObjectEnum getWebObject() {
		return webObject;
	}
	public void setWebObject(WebObjectEnum webObject) {
		this.webObject = webObject;
	}
	public String getNameWebObject() {
		return nameWebObject;
	}
	public void setNameWebObject(String nameWebObject) {
		this.nameWebObject = nameWebObject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ActionOfTestStep(int indexCurrent, ActionWebEnum actionWeb, WebObjectEnum webObject, String nameWebObject,
			String content) {
		super();
		this.indexCurrent = indexCurrent;
		this.actionWeb = actionWeb;
		this.webObject = webObject;
		this.nameWebObject = nameWebObject;
		this.content = content;
	}
	
	public ActionOfTestStep(int indexCurrent, ActionWebEnum actionWeb, WebObjectEnum webObject, String nameWebObject) {
		super();
		this.indexCurrent = indexCurrent;
		this.actionWeb = actionWeb;
		this.webObject = webObject;
		this.nameWebObject = nameWebObject;
		this.content = null;
	}
	 
	
}
