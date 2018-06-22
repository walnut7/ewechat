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
	<title>${merchant.merchantName}</title>
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
      <c:forEach items="${merchant.items}" varStatus="i" var="item" >  
      	 <div class="weui-form-preview" style="margin-left:5px;margin-right:10px;background-color: #F8F8F8">
            <div class="weui-form-preview__bd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">客户姓名</label>
                    <span class="weui-form-preview__value" style="text-align: left;">${item.custName}</span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">审批通过日期</label>
                    <span class="weui-form-preview__value" style="text-align: left;">${item.authDate}</span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">放款日</label>
                    <span class="weui-form-preview__value" style="text-align: left;">${item.rentDate}</span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">融资额</label>
                    <span class="weui-form-preview__value" style="text-align: left;">${item.finance}</span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">车型</label>
                    <span class="weui-form-preview__value" style="text-align: left;">${item.carType}</span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">商品名称</label>
                    <span class="weui-form-preview__value" style="text-align: left;">${item.carDesc}</span>
                </div>
            </div>
        </div>
        <br>
        </c:forEach>
  	</div>
</div>
</body>
</html>