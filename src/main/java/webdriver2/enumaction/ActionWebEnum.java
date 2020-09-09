package webdriver2.enumaction;

public enum ActionWebEnum {
	OPENURL("open-url", "open link url web"),
	INPUT("input", "input data field"),
	CLICK("click","click button"),
	REFRESH("refresh","refresh page"),
	SELECT("select","select dropdown"),
	CHECKBOX("check-box", "check box or radio box");
	
	private String actionName;
	private String exapanAction;
	
	private ActionWebEnum(String actionName, String explan) {
		 this.actionName=actionName;
		 this.exapanAction=explan;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getExapanAction() {
		return exapanAction;
	}

	public void setExapanAction(String exapanAction) {
		this.exapanAction = exapanAction;
	}
	
	public static ActionWebEnum getActionWebEnum(String actionName) {
		for(ActionWebEnum in : values()) {
			if(in.getActionName().equals(actionName)) {
				return in;
			}
		}
		return null;
	}
	
}
