package com.geo.framework.domainmodel;

/**
 * @author jjie
 * 数据查询过滤器
 */
public class DataFilter
{

	private String type;
	private String field;
	private String value;
	private String comparison;
	
	
	public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
    
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getComparison() {
        return comparison;
    }
    public void setComparison(String comparison) {
        this.comparison = comparison;
    }
	
}