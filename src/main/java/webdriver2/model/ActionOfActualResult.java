package webdriver2.model;

import webdriver2.enumaction.ActionWebEnum;

public class ActionOfActualResult {
	private int indexCurrent;
	private ActionWebEnum actionWeb;
	private String dataAction;
	
	
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
	public String getDataAction() {
		return dataAction;
	}
	public void setDataAction(String dataAction) {
		this.dataAction = dataAction;
	}
	public ActionOfActualResult(int indexCurrent, ActionWebEnum actionWeb, String dataAction) {
		super();
		this.indexCurrent = indexCurrent;
		this.actionWeb = actionWeb;
		this.dataAction = dataAction;
	}
	@Override
	public String toString() {
		return "ActionOfTestCase [indexCurrent=" + indexCurrent + ", actionWeb=" + actionWeb.getActionName() + ", dataAction="
				+ dataAction + "]";
	}
	
	
}
