<#include "student-header.ftl">
 <#if activity??>
    <!-- End Navigation -->
    <div class="container-fluid main-content">
        <div class="page-title">
          <h1>
            ${activity.activityName}

			  <#switch  activity.state>
				  <#case 0>
					  <span class="text-danger glyphicon glyphicon-fire"></span>
					  <span class="label label-success">报名中</span>
					  <span class="text-danger glyphicon glyphicon-fire"></span>
					  <#break >
				  <#case 1>
					  <span class="label label-danger">进行中</span>
					  <#break >
				  <#case 2>
					  <span class="label label-default">已结束</span>
					  <#break >
			  </#switch>
          </h1>
        </div>
        <div class="row">
          <!-- Conversation -->
          <div class="col-lg-12">
            <div class="widget-container scrollable chat chat-page">
              <div class="contact-list">
                <div class="heading">
					<#if activity.state != 0>
						<a  disabled="disabled" class="btn btn-block btn-warning">停止报名，请联系管理员</a>
					<#else >
						<#if isActive != 1>
							<a onclick="addIntegral('${user.id}','${activity.id}')" class="btn btn-block btn-success">我要报名</a>
						<#else >
							<a onclick="delIntegral()"  class="btn btn-block btn-danger">取消报名</a>
						</#if >
					</#if>

                  <#--未参与学生列表 (${userNoActivityList?size})-->
                </div>
              </div>
              <div class="heading">
				  <i class="icon-bullhorn"></i>${activity.activityPlace?default("")} &nbsp;&nbsp;
				  <i class="icon-time"></i>${activity.activityTime?default("")}
				  <a data-toggle="modal" href="#myModal" class="btn btn-sm btn-danger pull-right">我要申报</a>
              </div>
				<div class="modal fade" id="myModal">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
								<h4 class="modal-title">
									我要申报
								</h4>
							</div>
							<div class="modal-body">
								<div class="widget-container fluid-height clearfix">
									<div class="widget-content padded form-horizontal">
										<div class="form-group">
											<label class="control-label col-md-2">学号</label>
											<div class="col-md-7">
												<input id="stuNo" disabled value="<#if user??>${user.stuNo?if_exists}</#if>" class="form-control" placeholder="请填写12位学号" type="text">
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-2">手机号</label>
											<div class="col-md-7">
												<input id="telNum" disabled value="<#if user??>${user.telNum?if_exists}</#if>" class="form-control" placeholder="请填写手机号" type="text">
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-2">邮箱</label>
											<div class="col-md-7">
												<input id="email" disabled value="<#if user??>${user.email?if_exists}</#if>" class="form-control" placeholder="请填写邮箱" type="email">
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-2">问题描述</label>
											<div class="col-md-7">
												<textarea class="form-control" rows="3" id="dsc" ></textarea>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button class="btn btn-primary" onclick="add()" type="button">确认申报</button>
								<button class="btn btn-default-outline" data-dismiss="modal" type="button">关闭</button>
							</div>
						</div>
					</div>
				</div>
			  <div class="post-message">
				  <form action="${request.contextPath }/user/activity/detail">
					  <input class="form-control" placeholder="按学号或姓名搜索..." name="keyWord" type="text" value="${keyWord?if_exists}">
		 			  <input   name="activityId" type="hidden" value="${activity.id}">
		  			  <input type="submit" value="搜索">
				  </form>
			  </div>
              <div class="widget-content padded">
				  <div class="col-sm-12">
				      <div class="well selected-filters">
						<#if integralList??>
							<#list integralList as item>
								<div class="label label-default">
									${item.userName}
								</div>
							</#list>
						</#if>

				      </div>
				      <div class="widget-container fluid-height">
				        <!-- Table -->
				        <table class="table table-filters text-center">
				          <thead >
				            <th class="text-center">
				              学生姓名
				            </th>
				            <th class="text-center">
				              学号
				            </th>
				            <th class="text-center">
				              获得奖项
				            </th>
							<th class="text-center">
							  获得积分
							</th>
							<#--<th class="text-center">-->
							  <#--操作-->
							<#--</th>-->
				          </thead>
				          <tbody>
						 <#if integralList??>
							 <#list integralList as item>
				            <tr>
				              <td class="">
				                ${item.userName}
				              </td>
				              <td class="hidden-sm hidden-xs">
								 ${item.stuNo}
				              </td>
				              <td>
								 <div class="form-group">
								   <div class="col-md-offset-3 col-md-6">
									 <#if item.rank??>
										 <#switch item.rank>
											 <#case 1>
												 <span class="label label-danger">一等奖</span>
												 <#break >
											 <#case 2>
												 <span class="label label-warning">二等奖</span>
												 <#break >
											 <#case 3>
												 <span class="label label-info">三等奖</span>
												 <#break >
											 <#case 4>
												 <span class="label label-success">参与</span>
												 <#break >
											 <#default >
												 <span class="label label-default">暂无</span>
										 </#switch>
									 <#else >
										 <span class="label label-default">暂无</span>
									 </#if>
								   </div>
								 </div>
				              </td>
							 <td class="hidden-xs">
								 <span class="label label-success">+${item.integral?default(0)}</span>
							 </td>
							  <#--<td>-->
							   <#--<div class="row">-->
							   <#--<a href="#" onclick="updateIntegral('${item.id}','${item.id}','${activity.id}')" class="btn btn-xs btn-success-outline">保存修改</a>-->
							   <#--<a href="${request.contextPath }/user/integral/delete?integralId=${item.id}&activityId=${activity.id}" class="btn btn-xs btn-danger-outline">移除</a>-->
							   <#--</div>-->
							  <#--</td>-->
				            </tr>
							 </#list>
						 </#if>
				          </tbody>
				        </table>
				      </div>
				  </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    
    </div>
  </body>

</html>
 <script>
	 /*将学生添加至活动*/
	 function addIntegral(userId,activityId){
		 $.ajax({
			 url: "${request.contextPath }/integral/add",
			 type: "post",
			 dataType: "json",
			 data: {
				 "userId": userId,
				 "activityId": activityId
			 },
			 success: function (data) {
				 alert("报名成功！");
				 location.href="${request.contextPath }/user/activity/detail?activityId="+activityId+"&keyWord=";
			 }
		 })
	 }

	 function delIntegral(){
		 alert("取消成功！");
		 location.href="${request.contextPath }/user/integral/delete?integralId=${integralId}&activityId=${activity.id}"
	 }

	 function add() {
		 var isSendFlag = false;
		 var dsc = $("#dsc").val();
		 var stuNo = $("#stuNo").val();
		 var telNum = $("#telNum").val();
		 var email = $("#email")   .val();
		 var reg = new RegExp(/^\d{6}$/);     //工作密码必须是8位数字

		 if (dsc == null || dsc == "") {
			 alert("问题描述不能为空");
		 } else {
			 isSendFlag = true;
		 }
		 if(isSendFlag){
			 $.ajax({
				 url: "${request.contextPath }/apply/add",
				 type: "post",
				 dataType: "json",
				 data: {
					 "userId":'${user.id}',
					 "activityId":'${activity.id}',
					 "applyType":2,
					 "dsc": dsc
				 },
				 success: function (data) {
					 if(data.code == 200){
						 alert("申报成功！");
						 location.href = "${request.contextPath }/user/activity/detail?activityId=${activity.id}";
					 }else{
						 alert(data.error);
					 }
				 }
			 })
		 }
	 }

 </script>
 </#if>