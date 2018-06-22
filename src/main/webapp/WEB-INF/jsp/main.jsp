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
	<title>坤鹏企业微信消息发送DEMO</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.5.3/themes/black/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.5.3/themes/icon.css">
	<script type="text/javascript" src="${ctx}/js/jquery-easyui-1.5.3/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center', title:'坤鹏企业微信-消息推送功能演示'">
		<div class="easyui-tabs" data-options="tabPosition: 'left'" style="padding-top:1px;width:100%;height:100%">
			<div title="文本消息" style="padding:10px">
				<div style="padding:10px 60px 20px 60px">
				    <form method="post">
				    	<table cellpadding="5">
				    		<tr>
				    			<td>发送对象:</td>
				    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width:500px;"></></td>
				    		</tr>
				    		<tr>
				    			<td>是否保密发送:</td>
				    			<td>
				    				<select class="easyui-combobox" name="language" style="width:500px;"><option value="0">否</option><option value="1">是</option></select>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>发送消息:</td>
				    			<td><input class="easyui-textbox" name="message" data-options="multiline:true" style="width:500px;height:220px"></input></td>
				    		</tr>
				    	</table>
				    </form>
				    <div style="text-align:center;padding:5px">
				    	<a id="btnSendText" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" >发送消息</a>
				    </div>
			    </div>
			</div>
			<div title="图片消息" style="padding:10px">
				<div style="padding:10px 60px 20px 60px">
				    <form method="post">
				    	<table cellpadding="5">
				    		<tr>
				    			<td>发送对象:</td>
				    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width:500px;"></></td>
				    		</tr>
				    		<tr>
				    			<td>是否保密发送:</td>
				    			<td>
				    				<select class="easyui-combobox" name="language" style="width:500px;"><option value="0">否</option><option value="1">是</option></select>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>上传图片信息:</td>
				    			<td><input id="imgFile" class="easyui-filebox" name="imgFile" data-options="prompt:'Choose a file...'" style="width:100%"></input></td>
				    		</tr>
				    	</table>
				    </form>
				    <div style="text-align:center;padding:5px">
				    	<a id="btnSendText" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" >发送消息</a>
				    </div>
			    </div>
			</div>
			<div title="语音消息" style="padding:10px">
				<div style="padding:10px 60px 20px 60px">
				    <form method="post">
				    	<table cellpadding="5">
				    		<tr>
				    			<td>发送对象:</td>
				    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width:500px;"></></td>
				    		</tr>
				    		<tr>
				    			<td>是否保密发送:</td>
				    			<td>
				    				<select class="easyui-combobox" name="language" style="width:500px;"><option value="0">否</option><option value="1">是</option></select>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>上传语音信息:</td>
				    			<td><input class="easyui-filebox" name="file3" data-options="prompt:'Choose another file...'" style="width:100%"></input></td>
				    		</tr>
				    	</table>
				    </form>
				    <div style="text-align:center;padding:5px">
				    	<a id="btnSendText" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" >发送消息</a>
				    </div>
			    </div>
			</div>
			<div title="视频消息" style="padding:10px">
				<div style="padding:10px 60px 20px 60px">
				    <form method="post">
				    	<table cellpadding="5">
				    		<tr>
				    			<td>发送对象:</td>
				    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width:500px;"></></td>
				    		</tr>
				    		<tr>
				    			<td>是否保密发送:</td>
				    			<td>
				    				<select class="easyui-combobox" name="language" style="width:500px;"><option value="0">否</option><option value="1">是</option></select>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>上传视频信息:</td>
				    			<td><input class="easyui-filebox" name="file4" data-options="prompt:'Choose another file...'" style="width:100%"></input></td>
				    		</tr>
				    	</table>
				    </form>
				    <div style="text-align:center;padding:5px">
				    	<a id="btnSendText" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" >发送消息</a>
				    </div>
			    </div>
			</div>
			<div title="文件消息" style="padding:10px">
				<div style="padding:10px 60px 20px 60px">
				    <form method="post">
				    	<table cellpadding="5">
				    		<tr>
				    			<td>发送对象:</td>
				    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width:500px;"></></td>
				    		</tr>
				    		<tr>
				    			<td>是否保密发送:</td>
				    			<td>
				    				<select class="easyui-combobox" name="language" style="width:500px;"><option value="0">否</option><option value="1">是</option></select>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>上传文件信息:</td>
				    			<td><input class="easyui-filebox" name="file5" data-options="prompt:'Choose another file...'" style="width:100%" ></input></td>
				    		</tr>
				    	</table>
				    </form>
				    <div style="text-align:center;padding:5px">
				    	<a id="btnSendText" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" >发送消息</a>
				    </div>
			    </div>
			</div>
			<div title="文本卡片消息" style="padding:10px">
				<ul class="easyui-tree" ></ul>
			</div>
			<div title="图文消息" style="padding:10px">
				<div style="padding:10px 60px 20px 60px">
				    <form method="post">
				    	<table cellpadding="5">
				    		<tr>
				    			<td>发送对象:</td>
				    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width:500px;"></></td>
				    		</tr>
				    		<tr>
				    			<td>是否保密发送:</td>
				    			<td>
				    				<select class="easyui-combobox" name="language" style="width:500px;"><option value="0">否</option><option value="1">是</option></select>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>文章标题:</td>
				    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width:500px;"></></td>
				    		</tr>
				    		<tr>
				    			<td>文章跳转URL:</td>
				    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width:500px;"></></td>
				    		</tr>
				    		<tr>
				    			<td>图片URL:</td>
				    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width:500px;"></></td>
				    		</tr>
				    		<tr>
				    			<td>按钮文字:</td>
				    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width:500px;"></></td>
				    		</tr>
				    	</table>
				    </form>
				    <div style="text-align:center;padding:5px">
				    	<a id="btnSendNews" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" >发送消息</a>
				    </div>
			    </div>
			</div>
		</div>
	</div>
	
	<script language="javascript" type="text/javascript">
		$(function() {
			$("#btnSendText").click(function() {
				var receiver = $("input[name='name']").val();
				var message = $("input[name='message']").val();
				$.ajax({
		            type : "POST",
		            dataType : "json",
		            async : false,
		            data : {"touser":receiver,"text":message },
		            url : "${ctx}/demo/sendText",
		            success : function(data) {
						if(data.result == "success") {
			            	$.messager.alert('提示',data.message,'info');
						}else {
							$.messager.alert('错误',data.message,'error');
						}
		            },
		            error : function(XMLHttpRequest, textStatus, errorThrown) {
		                alert("error:" + textStatus);
		            }
				});
			});

			$("input[type='file'][name='imgFile']").change(function() {
				var file = this.files[0];
				var reader = new FileReader();
				reader.readAsDataURL(file);
				// reader.readAsBinaryString(file);
				reader.onloadend = function () {
					var receiver = $("input[name='name']").val();
	            	var base64string = reader.result.split(",")[1];
	            	console.info(base64string); //状态值 2
	            	console.info(reader.readyState); //状态值 2
					// alert(base64string);
					$.ajax({
						type : "POST",					
						url : "${ctx}/demo/sendImage",
						datatype : "json",
						data : {
							"touser":receiver,
							"base64string" : base64string
						},
						success : function(data) {
							if (data.success == false) {
								$.alert(data.error.message);
							} else {
								alert("success");
							}
						},					
						error : function() {
							weui.alert("读取失败，请手工输入！");
						}
					});       	
	            }
			}); 


			$("#btnSendNews").click(function() {
				alert("发送图文信息！");
				$.ajax({
		            type : "POST",
		            dataType : "json",
		            async : false,
		          //  data : {"touser":receiver,"text":message },
		            url : "${ctx}/demo/sendNews",
		            success : function(data) {
						if(data.result == "success") {
			            	$.messager.alert('提示',data.message,'info');
						}else {
							$.messager.alert('错误',data.message,'error');
						}
		            },
		            error : function(XMLHttpRequest, textStatus, errorThrown) {
		                alert("error:" + textStatus);
		            }
				});
			});
		});

	</script>
</body>
</html>