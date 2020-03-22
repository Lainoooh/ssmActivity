<!DOCTYPE html>
<html>

<head>
	<title>
		到梦空间活动积分查询系统
	</title>
	<!--<link href="http://fonts.googleapis.com/css?family=Lato:100,300,400,700" media="all" rel="stylesheet" type="text/css" />-->
	<link href="${request.contextPath }/static/stylesheets/bootstrap.min.css" media="all" rel="stylesheet"
		  type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/font-awesome.css" media="all" rel="stylesheet"
		  type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/se7en-font.css" media="all" rel="stylesheet"
		  type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/isotope.css" media="all" rel="stylesheet" type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/jquery.fancybox.css" media="all" rel="stylesheet"
		  type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/fullcalendar.css" media="all" rel="stylesheet"
		  type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/wizard.css" media="all" rel="stylesheet" type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/select2.css" media="all" rel="stylesheet" type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/morris.css" media="all" rel="stylesheet" type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/datatables.css" media="all" rel="stylesheet"
		  type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/datepicker.css" media="all" rel="stylesheet"
		  type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/timepicker.css" media="all" rel="stylesheet"
		  type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/colorpicker.css" media="all" rel="stylesheet"
		  type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/bootstrap-switch.css" media="all" rel="stylesheet"
		  type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/daterange-picker.css" media="all" rel="stylesheet"
		  type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/typeahead.css" media="all" rel="stylesheet" type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/summernote.css" media="all" rel="stylesheet"
		  type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/pygments.css" media="all" rel="stylesheet" type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/style.css" media="all" rel="stylesheet" type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/color/green.css" media="all" rel="alternate stylesheet"
		  title="green-theme" type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/color/orange.css" media="all" rel="alternate stylesheet"
		  title="orange-theme" type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/color/magenta.css" media="all" rel="alternate stylesheet"
		  title="magenta-theme" type="text/css"/>
	<link href="${request.contextPath }/static/stylesheets/color/gray.css" media="all" rel="alternate stylesheet"
		  title="gray-theme" type="text/css"/>
	<script src="${request.contextPath }/static/javascripts/jquery.min.js" type="text/javascript"></script>
	<script src="http://www.jq22.com/jquery/jquery-ui-1.10.2.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/bootstrap.min.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/raphael.min.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/selectivizr-min.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/jquery.mousewheel.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/jquery.vmap.min.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/jquery.vmap.sampledata.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/jquery.vmap.world.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/jquery.bootstrap.wizard.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/fullcalendar.min.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/gcal.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/jquery.dataTables.min.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/datatable-editable.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/jquery.easy-pie-chart.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/excanvas.min.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/jquery.isotope.min.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/isotope_extras.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/modernizr.custom.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/jquery.fancybox.pack.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/select2.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/styleswitcher.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/wysiwyg.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/summernote.min.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/jquery.inputmask.min.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/jquery.validate.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/bootstrap-fileupload.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/bootstrap-datepicker.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/bootstrap-timepicker.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/bootstrap-colorpicker.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/bootstrap-switch.min.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/typeahead.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/daterange-picker.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/date.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/morris.min.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/skycons.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/fitvids.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/jquery.sparkline.min.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/main.js" type="text/javascript"></script>
	<script src="${request.contextPath }/static/javascripts/respond.js" type="text/javascript"></script>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
	<script type="text/javascript">
	</script>
</head>
<body class="login1">
<!-- Login Screen -->
<div class="login-wrapper">
	<div class="login-container">
		<a href="javascript:;">到梦空间活动积分查询系统
			<br>
			<br>
			忘记密码
		</a>
		<br><br><br>
		<#--<form action="${request.contextPath }/activity/forget"  method="post">-->
		<div class="form-group">
			<input id="userName" class="form-control" name="userName" value="<#if user??>${user.userName?if_exists}</#if>"
				   placeholder="请输入姓名" type="text">
		</div>
		<div class="form-group">
			<input id="stuNo" class="form-control" name="stuNo" value="<#if user??>${user.stuNo?if_exists}</#if>"
				   placeholder="学号 or 工号" type="text">
		</div>
	<br>


		<#if errorMsg??>
			<div class="form-group">
			<label class="control-label col-md-7 " style="background-color:#d6eaff;color: #007aff;border-left: 2px solid #5bc0de;">
			查无此生,请联系管理员
			<br>${errorMsg}
			</label>
				<a onclick="getCode()" class="btn btn-sm btn-warning pull-right">点击获取验证码</a>
			</div>
		<#else >
			<#if code??>
				<div class="form-group">
				<label class="control-label col-md-7 " style="padding:5px;font-size:15px;background-color:#d6eaff;color: #007aff;border-left: 2px solid #5bc0de;">
				验证码：${code}
				</label>
				<a onclick="getCode()" class=" btn btn-sm btn-warning pull-right">刷新验证码</a>
				</div>
				<div class="form-group">
					<a data-toggle="modal" href="#myModal" style="margin: 0px" class="btn btn-block btn-sm btn-info pull-right">点击重置密码</a>
				</div>
			<#else >
				<a onclick="getCode()" class="btn btn-sm btn-warning pull-right">点击获取验证码</a>
			</#if>
		</#if>




		<#--<div class="form-group">-->
		<#--<a data-toggle="modal" href="#myModal" class="btn btn-sm btn-warning pull-right">点击重置密码</a>-->
		<#--</div>-->
			<div class="modal fade" id="myModal">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
							<h4 class="modal-title">
								重置密码
							</h4>
						</div>
						<div class="modal-body">
							<input id="userId" type="hidden"  value="<#if user??>${user.id?if_exists}</#if>" />

							<div class="widget-container fluid-height clearfix">
								<div class="widget-content padded form-horizontal">
									<div class="form-group">
										<label class="control-label col-md-3">验证码</label>
										<div class="col-md-7">
											<input id="code" value="" class="form-control" placeholder="请输入验证码"
												   type="text">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">请输入密码</label>
										<div class="col-md-7">
											<input id="password" value="" class="form-control" placeholder="请输入密码"
												   type="password">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">再次输入密码</label>
										<div class="col-md-7">
											<input id="repassword" class="form-control" placeholder="请再次输入密码"
												   type="password">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button class="btn btn-primary" onclick="reset()" type="button">确认修改</button>
							<button class="btn btn-default-outline" data-dismiss="modal" type="button">关闭</button>
						</div>
					</div>
				</div>
			</div>
		<#--</form>-->
	</div>
</div>
<script>
	function reset() {
		var userId = $("#userId").val();
		var password = $("#password").val();
		var repassword = $("#repassword").val();
		var code = $("#code").val();
		$.ajax({
			url: "${request.contextPath }/activity/password",
			type: "post",
			dataType: "json",
			data: {
				"id":userId,
				"password":password,
				"repassword":repassword,
				"code": code
			},
			success: function (data) {
				if(data.code == 200){
					alert("重置密码成功，即将跳转至登录页面！");
					location.href = "${request.contextPath }/activity/login";
				}else{
					alert(data.error);
				}
			}
		})
	}

	function getCode() {
		var userName = $("#userName").val();
		var stuNo = $("#stuNo").val();
		location.href = "${request.contextPath }/activity/getCode?userName=" + userName + "&stuNo=" + stuNo;
	}

</script>
<!-- End Login Screen -->

</body>
</html>