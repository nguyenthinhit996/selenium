package webdriver2.enumaction;

public enum WebObjectEnum {
	// status
	
	LINK("link", "opend web link"),
	TITTLE("tittle", "tag tittle"),
	ID("id","id of page"),
	CLASS("class", "class"),
	VALUEOF("value-of", "value of object");
	
	
	 
	
	private String actionName;
	private String exapanAction;
	
	private WebObjectEnum(String actionName, String explan) {
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
	
	
	public static WebObjectEnum getWebObjectEnum(String actionName) {
		for(WebObjectEnum in : values()) {
			if(in.getActionName().equals(actionName)) {
				return in;
			}
		}
		return null;
	}
	
}
