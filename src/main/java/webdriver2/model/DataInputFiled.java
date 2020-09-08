package webdriver2.model;

public class DataInputFiled {
	
	private String nameFiled;
	private String valueFiled;
	
	public String getNameFiled() {
		return nameFiled;
	}
	public void setNameFiled(String nameFiled) {
		this.nameFiled = nameFiled;
	}
	public String getValueFiled() {
		return valueFiled;
	}
	public void setValueFiled(String valueFiled) {
		this.valueFiled = valueFiled;
	}
	
	public DataInputFiled() {
		super();
	}
	
	public DataInputFiled(String nameFiled, String valueFiled) {
		super();
		this.nameFiled = nameFiled;
		this.valueFiled = valueFiled;
	}
}
