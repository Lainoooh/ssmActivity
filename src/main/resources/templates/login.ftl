<!DOCTYPE html>
<html>

<head>
    <title>
      到梦空间活动积分查询系统
    </title>
    <!--<link href="http://fonts.googleapis.com/css?family=Lato:100,300,400,700" media="all" rel="stylesheet" type="text/css" />-->
	<link href="${request.contextPath }/static/stylesheets/bootstrap.min.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/font-awesome.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/se7en-font.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/isotope.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/jquery.fancybox.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/fullcalendar.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/wizard.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/select2.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/morris.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/datatables.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/datepicker.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/timepicker.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/colorpicker.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/bootstrap-switch.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/daterange-picker.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/typeahead.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/summernote.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/pygments.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/style.css" media="all" rel="stylesheet" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/color/green.css" media="all" rel="alternate stylesheet" title="green-theme" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/color/orange.css" media="all" rel="alternate stylesheet" title="orange-theme" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/color/magenta.css" media="all" rel="alternate stylesheet" title="magenta-theme" type="text/css" />
	<link href="${request.contextPath }/static/stylesheets/color/gray.css" media="all" rel="alternate stylesheet" title="gray-theme" type="text/css" />
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
		<a href="javascript:;">到梦空间活动积分查询系统</a>
		<br><br><br>
		<form action="${request.contextPath }/activity/login"  method="post">
			<div class="form-group">
				<input class="form-control" name="userName" value="<#if user??>${user.userName?if_exists}</#if>" placeholder="请输入姓名" type="text">
			</div>
			<div class="form-group">
				<input class="form-control" name="stuNo" value="<#if user??>${user.stuNo?if_exists}</#if>" placeholder="学号 or 工号" type="text">
			</div>
			<div class="form-group">
				<input class="form-control" name="password" value="<#if user??>${user.password?if_exists}</#if>" placeholder="请输入密码" type="password">
				<input type="submit" value="&#xf054;">
			</div>
			<div class="form-options clearfix">
				<a class="pull-right" href="${request.contextPath }/activity/forget">忘记密码?</a>
			</div>
		</form>
	</div>
</div>
<script>
	<#if errorMsg??>
		alert('${errorMsg}');
	</#if>
</script>
<!-- End Login Screen -->
</body>