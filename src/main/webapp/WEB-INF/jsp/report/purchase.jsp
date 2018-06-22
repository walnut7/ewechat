<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + path+"/";
%>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新采购车型</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <link rel="stylesheet" href="${ctx}/css/weui.min.css">
    <link rel="stylesheet" href="${ctx}/css/jquery-weui.min.css">
    <link rel="stylesheet" href="${ctx}/css/wxss.css">
    <script type="text/javascript" src="${ctx}/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-weui.min.js"></script>
</head>
<body ontouchstart>
<div class="weui-tab">
    <div class="weui-tab__bd">
    	<c:forEach items="${pf.merchants}" var="merchant" > 
    	  <c:forEach items="${merchant.items}" var="item" > 
    	    <div class="weui-form-preview" style="margin-left:5px;margin-right:10px; background-color:#F8F8F8">
	            <div class="weui-form-preview__bd" >
	                <div class="weui-form-preview__item">
	                    <label class="weui-form-preview__label">客户姓名：</label>
	                    <span class="weui-form-preview__value">${item.custName}</span>
	                </div>
	                <div class="weui-form-preview__item">
	                    <label class="weui-form-preview__label">车系：</label>
	                    <span class="weui-form-preview__value">${item.carModel}</span>
	                </div>
	                <div class="weui-form-preview__item">
	                    <label class="weui-form-preview__label">车型：</label>
	                    <span class="weui-form-preview__value">${item.carType}</span>
	                </div>
	                <div class="weui-form-preview__item">
	                    <label class="weui-form-preview__label">采购价：</label>
	                    <span class="weui-form-preview__value">${item.purcPrice}</span>
	                </div>
	                <div class="weui-form-preview__item">
	                    <label class="weui-form-preview__label">经销商：</label>
	                    <span class="weui-form-preview__value">${item.spName}</span>
	                </div>
	                <div class="weui-form-preview__item">
	                    <label class="weui-form-preview__label">网约车公司：</label>
	                    <span class="weui-form-preview__value">${merchant.merchantName}</span>
	                </div>
	            </div>
	          </div>
	          <br>
	          </c:forEach>  
            </c:forEach>  
  	</div>
</div>
</body>
</html>