package com.nosql.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "mongoLogInfo")
public class MongoEntity {
    @Id
    private String id;//主键
    private String interfaceMethod;//接口方法名称
    private String businessName;//业务名称
    private String clientReqParams;//客户端请求参数
    private String serviceRepParams;//服务端响应参数
    private String logInfo;//日志内容:包括异常等
    private Long clientReqTime;//客户端调用时间
    private Long serviceRepTime;//服务端响应时间
    private Long duration;//持续时间
    private String clientReqIpPort;//客户端请求IP和PORT
    private String serviceRepIpPort;//服务端响应IP和PORT
    private String ifSuccess;//服务调用是否成功标识,1成功2失败
    private Long createTime;//插入时间
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getInterfaceMethod() {
        return interfaceMethod;
    }
    public void setInterfaceMethod(String interfaceMethod) {
        this.interfaceMethod = interfaceMethod;
    }
    public String getBusinessName() {
        return businessName;
    }
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
    public String getClientReqParams() {
        return clientReqParams;
    }
    public void setClientReqParams(String clientReqParams) {
        this.clientReqParams = clientReqParams;
    }
    public String getServiceRepParams() {
        return serviceRepParams;
    }
    public void setServiceRepParams(String serviceRepParams) {
        this.serviceRepParams = serviceRepParams;
    }
    public String getLogInfo() {
        return logInfo;
    }
    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }
    public Long getClientReqTime() {
        return clientReqTime;
    }
    public void setClientReqTime(Long clientReqTime) {
        this.clientReqTime = clientReqTime;
    }
    public Long getServiceRepTime() {
        return serviceRepTime;
    }
    public void setServiceRepTime(Long serviceRepTime) {
        this.serviceRepTime = serviceRepTime;
    }
    public Long getDuration() {
        return duration;
    }
    public void setDuration(Long duration) {
        this.duration = duration;
    }
    public String getClientReqIpPort() {
        return clientReqIpPort;
    }
    public void setClientReqIpPort(String clientReqIpPort) {
        this.clientReqIpPort = clientReqIpPort;
    }
    public String getServiceRepIpPort() {
        return serviceRepIpPort;
    }
    public void setServiceRepIpPort(String serviceRepIpPort) {
        this.serviceRepIpPort = serviceRepIpPort;
    }
    public String getIfSuccess() {
        return ifSuccess;
    }
    public void setIfSuccess(String ifSuccess) {
        this.ifSuccess = ifSuccess;
    }
    public Long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}