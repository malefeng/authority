package com.innove.authority.bean.enums;

/**  
* @ClassName: BusinessCodeHeadEnum.java  
* @Description: 业务code开头两位
* @author mlf
* @date 2020/2/10
*/
public enum BusinessCodeHeadEnum {

	ROLE_CODE("11","t_role","role_code","角色编号"),
	ORG_CODE("12","t_org","org_code","组织机构编号");

	private BusinessCodeHeadEnum(String code, String tableName, String columnName, String desc) {
		this.code = code;
		this.tableName = tableName;
		this.columnName = columnName;
		this.desc = desc;
	}
	
	private String code;
	
	private String tableName;
	
	private String columnName;
	
	private String desc;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
