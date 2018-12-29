package com.geo.framework.domainmodel;

//import org.geo.framework.common.*;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
//import java.util.Date;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.geo.framework.common.EOUtil;

//import java.io.Serializable;
//import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * @author jjie
 * 基础实体，包含系统表中公共的字段   
 * 重写 toString() clone() equals() hashCode()
 */
@MappedSuperclass
public abstract class AuditedEntity extends BaseObject
{
	
	private static final long serialVersionUID = 1962905939086138888L;

    public AuditedEntity()
    {
    	//DateFormat datefmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        //this.setRkey("");
        this.isDelete = false;
        this.createDate = new Date();
    }
    
    private String rkey;
    private Date updateDate;
    private String updateBy;
    private Date createDate;
    private String createBy;
    private Boolean isDelete;
    

   //配置uuid，本来jpa是不支持uuid的，但借用hibernate的方法可以实现。
    @Id  
    @GenericGenerator(name="systemUUID",strategy="uuid")
    @GeneratedValue(generator="systemUUID")  
    @Column(name = "Rkey", unique = true, nullable = false,length = 32)  
    public String getRkey() {
        return rkey;
    }
    public void setRkey(String rkey) {
        this.rkey = rkey;
    }
    
    @Column(name="IsDelete")
    public Boolean getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Boolean isdelete) {
        this.isDelete = isdelete;
    }
    
    
    @Column(name="UpdateDate")
    public Date getUpdatedate() {
        return updateDate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updateDate = updatedate;
    }

    @Column(name="UpdateBy", length=30)
    public String getUpdateby() {
        return updateBy;
    }

    public void setUpdateby(String updateby) {
        this.updateBy = updateby;
    }
    
    @Column(name="CreateDate")
    public Date getCreatedate() {
        return createDate;
    }

    public void setCreatedate(Date createdate) {
        this.createDate = createdate;
    }

    
    @Column(name="CreateBy", length=30)
    public String getCreateby() {
        return createBy;
    }

    public void setCreateby(String createby) {
        this.createBy = createby;
    }

    @Transient
    public Object getPrimaryKey(){
    	return this.rkey;
    }
    
   
   
    //protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
    @Transient
    @JSONField(serialize=false)
    private transient EOUtil eoutil ;

    @Transient
    protected EOUtil getBeanUtility() {
        if (eoutil == null) {
            eoutil = new EOUtil(this);
        }
        return eoutil;
    }
    
    @Override
    public String toString() {
		return getBeanUtility().beanToString();
    }
    
    
    /**
     * 得到所有可持久化字段的名称
     * @return 名称列表
     */
    @Transient
    public String[] getAttributeNames(){
        return getBeanUtility().getAttributeNames();
    }
    
    /**
     * 得到某个字段的值
     * @param attributeName 字段名
     * @return  值
     */
    @Transient
    public Object getAttributeValue(String attributeName) {
        return getBeanUtility().getAttributeValue(attributeName);
    }

    /**
     * 设置某个字段的值
     * @param attributeName 字段名
     * @param value  值
     */
    @Transient
    public void setAttributeValue(String attributeName , Object value){
        getBeanUtility().setAttributeValue(attributeName,value);
    }
    
    /**
     * 获得实体对应的表名
     * @return
     */
    @Transient
    public String getTableName() {
        return getBeanUtility().getTableName();
    }
    
    
    
    /*
    protected boolean selected;

    @Transient
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object obj) {
        return getBeanUtility().equalsBean(obj);
    }

    @Override
    public int hashCode() {
        return getBeanUtility().hashCodeBean();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object obj = null;
        try {
            obj = getBeanUtility().cloneBean();
        } catch (Exception e) {
            throw new CloneNotSupportedException(e.getMessage());
        }

        return obj;
    }
     */
    
    /*

    @SuppressWarnings("static-access")
    @Transient
    public String getEnumDescription(String enumAttributeName){
        Object value = getAttributeValue(enumAttributeName);

        return getBeanUtility().getEnumDescription(value);
    }

    
    	
     //**
     * 比较此对象与另一个对象的差别，并返回值不同的字段的名称。
     * @param antherBean 将要比较的对象
     * @return 值不同的字段名
     *//*
    @Transient
    public List<String> getDifferentField(Entity anotherBean) {
        return getBeanUtility().getDifferentField(anotherBean);
    }

    *//**
     * 获取主键值
     * @return 主键值
     *//*
    @Transient
    public abstract Object getPrimaryKey();

    *//**
     * 比较主键值是否相同
     * @param obj
     * @return
     *//*
    @Transient
    public boolean equalsPK(Object obj) {
        if (obj == null)// 对象为空不比较
            return false;
        // 类型不同不必进行比较
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }

        // 不是Entity，不必比较
        if (!(obj instanceof Entity)) {
            return false;
        }

        Entity eo = (Entity) obj;

        if (getPrimaryKey()!=null
                && eo.getPrimaryKey()!=null) {
            if (getPrimaryKey().equals(eo.getPrimaryKey()))
                return true;
            return false;
        } else {
            return false;
        }

    }

    *//**
     * 拷贝另一个eo对象中的字段值到当前对象中
     * @param fromEO            从哪里拷贝
     * @param copyAttributes    拷贝哪些字段
     *//*
    public void copyAttributeValue(Entity fromEO , String[] copyAttributes){
        if(copyAttributes == null)
            return ;

        for (String attr : copyAttributes) {
            this.setAttributeValue(attr, fromEO.getAttributeValue(attr));
        }
    }

    *//**
     * 加载所有延迟加载字段
     *//*
    public void loadLazyAttributes(){
        getBeanUtility().loadLazyField();
    }*/
}
