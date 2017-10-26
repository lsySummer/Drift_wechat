<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!--主要区域开始-->
	<div id="main">
		<div id="save_result_info"></div>
		<form action="admin/addBook.do" method="post" class="main_form"
			onsubmit="return checkSubmit();">
			<div class="text_info clearfix">
				<span>书名：</span>
			</div>
			<div class="input_info">
				<input id="bookName" type="text" class="width300" name="bookName" />
				<span class="required">*</span>
				<div id="bookNameErr" class="validate_msg_short">90长度的字母、汉字</div>
			</div>

			<div class="text_info clearfix">
				<span>作者：</span>
			</div>
			<div class="input_info">
				<input id="author" type="text" class="width300" name="author" /> <span
					class="required">*</span>
				<div id="authorErr" class="validate_msg_short">60长度的字母、汉字</div>
			</div>

			<div class="text_info clearfix">
				<span>价格：</span>
			</div>
			<div class="input_info">
				<input id="price" type="text" class="width300" name="price" /> <span
					class="required">*</span>
				<div id="priceErr" class="validate_msg_short">请填写正确的价格,允许保留两位小数</div>
			</div>

			<div class="text_info clearfix">
				<span>书籍分类:</span>
			</div>
			<div class="input_info">
				<select name="catalog" class="width300" id="catalog">
					<option value="0">全部</option>
					<c:forEach items="${catalogs}" var="catalog">
						<option
							${sessionScope.bookSearchConditions.catalog==catalog.name ? "selected":""}
							value="${catalog.id}">${catalog.name}</option>
					</c:forEach>
				</select> <span class="required">*</span>
				<div id="catalogErr" class="validate_msg_short">请选择类型</div>
			</div>

			<div class="text_info clearfix">
				<span>详细分类:</span>
			</div>
			<div class="input_info">
				<select name="secondCatalog" class="width300" id="secondCatalog">
					<option
						${sessionScope.bookSearchConditions.secondCatalog=="全部" ? "selected":""}
						value="0">全部</option>
					<!--  ${sessionScope.bookSearchConditions.secondCatalog=="全部" ? "selected":""} -->
					<c:forEach items="${secondCatalogs}" var="secondCatalog">
						<option value="${secondCatalog.id}"
							${sessionScope.bookSearchConditions.secondCatalog==secondCatalog.name ? "selected":""}>${secondCatalog.name}</option>
					</c:forEach>
				</select> <span class="required">*</span>
				<div id="secondCatalogErr" class="validate_msg_short">请选择类型</div>
			</div>

			<div class="text_info clearfix">
				<span>描述：</span>
			</div>
			<div class="textarea_info" style="height:255px">
				<textarea id="description" class="width300 left" name="description"
					style="height:245px"></textarea>
				<span class="required left" style="padding:5px 0 0 4px">*</span>
				<div id="descriptionErr" class="validate_msg_short">200长度限定</div>
			</div>

			<div class="button_info clearfix">
				<input type="submit" value="下一步" class="btn_save" id="saveBtn" /> <input
					type="button" value="取消" class="btn_save" id="cancelBtn" />
			</div>
		</form>
	</div>
	<!--主要区域结束-->
</body>
</html>