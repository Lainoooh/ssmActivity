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
  <body>

	<div class="modal-shiftfix">
    <!--头部-->
	<div class="navbar navbar-fixed-top scroll-hide">
	  <div class="container-fluid top-bar">
	    <div class="pull-right">
	      <ul class="nav navbar-nav pull-right">

	        <li class="dropdown user hidden-xs">

				<a data-toggle="dropdown" class="dropdown-toggle" href="#">
					欢迎，尊敬的
					<#if user??>
					<#switch  user.isAdmin>
						<#case 0>
							学生：
							<#break >
						<#case 1>
							管理员：
							<#break >
					</#switch>
						${user.userName}
					</#if><b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="${request.contextPath }/user/index">
						<i class="icon-user"></i>个人主页</a>
					</li>
					<li><a href="${request.contextPath }/activity/logout">
							<i class="icon-signout"></i>登出</a>
					</li>
				</ul>
	        </li>
	      </ul>
	    </div>
			  <!-- logo -->
	    <button class="navbar-toggle"><span class="icon-bar"></span>
			  <span class="icon-bar"></span><span class="icon-bar"></span>
			  </button>
			  <a class="${request.contextPath }/user/index" href="javascripts:;">到梦空间活动积分查询系统</a>
	    <!-- <form class="navbar-form form-inline col-lg-2 hidden-xs">
	      <input class="form-control" placeholder="Search" type="text">
	    </form> -->
	  </div>
	  <div class="container-fluid main-nav clearfix">
	    <div class="nav-collapse">
	      <ul class="nav">
	        <li>
	          <a class="<#if index == 'index'> current </#if>" href="${request.contextPath }/user/index"><span aria-hidden="true" class="se7en-home"></span>个人主页</a>
	        </li>
			  <li>
				  <a class="<#if index == 'activity'> current </#if>" href="${request.contextPath }/user/activity/list"><span aria-hidden="true" class="se7en-star"></span>活动列表</a>
			  </li>
			  <li>
				  <a class="<#if index == 'apply'> current </#if>" href="${request.contextPath }/user/apply/list"><span aria-hidden="true" class="icon-warning-sign"></span>申报记录</a>
			  </li>
	      </ul>
	    </div>
	  </div>
	</div>
