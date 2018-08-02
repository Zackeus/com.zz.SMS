<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>测试</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>任务编号</th>
				<th>合同号</th>
				<th>客户姓名</th>
				<th>客户类型</th>
				<th>手机号</th>
				<th>逾期天数</th>
				<th>金额</th>
				<th>住址类型</th>
				<th>提交者</th>
				<th>提交日期</th>
				<th>提交标识</th>
				<th>提交标签</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${customer.taskId}</td>
				<td>${customer.contractNum}</td>
				<td>${customer.odName}</td>
				<td>${customer.rtype}</td>
				<td>${customer.phoneNum}</td>
				<td>${customer.odDays}</td>
				<td>${customer.odAmount}</td>
				<td>${customer.addressType}</td>
				<td>${customer.requestEmpno}</td>
				<td>${customer.requestDatetime}</td>
				<td>${customer.remark}</td>
				<td>${customer.sendBatch}</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
