<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String baseUrl = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort();
	baseUrl += request.getContextPath() + "/";
	//注：path=""
	String path = request.getContextPath();
	request.setAttribute("path", path);
	request.setAttribute("baseUrl", baseUrl);
	request.setCharacterEncoding("utf-8");
%>
<%-- <base href="<%=baseUrl%>" /> --%>