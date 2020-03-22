<#include "admin-header.ftl">
 <#if activity??>
    <!-- End Navigation -->
    <div class="container-fluid main-content">
        <div class="page-title">
          <h1>
            ${activity.activityName}
          </h1>
        </div>
        <div class="row">
          <!-- Conversation -->
          <div class="col-lg-12">
            <div class="widget-container scrollable chat chat-page">
              <div class="contact-list">
                <div class="heading">
                  未参与学生列表 (${userNoActivityList?size})
                </div>
                <ul>
	              <#if userNoActivityList??>
					  <#list userNoActivityList as item>
						  <li>
						  <a href="javascript:;" data-toggle="tooltip-right" id="tooltip-right" title="学号：${item.stuNo}">
					  <#--<img width="30" height="30" src="${request.contextPath }/static/images/avatar-female.png" />-->
						  <#--<i class="icon-user pull-left"></i>-->
						  ${item.userName} &nbsp;
						  <span class="external-event label label-primary ui-draggable"
						  style="font-size:3px;background-color:#d6eaff;color: #007aff;border-left: 2px solid #5bc0de;"
						  >${item.stuNo}</span>

					  		<i class="icon-plus pull-right text-danger" onclick="addIntegral('${item.id}','${activity.id}')"></i>
						  <#--<a href="${request.contextPath }/admin/integral/add?userId=${item.id}&activityId=${activity.id}"</a>

						  <!-- <button class=" pull-right btn-xs btn-danger">加入</button> &ndash;&gt;-->
						  </a>
						  </li>
					  </#list>
	              </#if>
                </ul>
              </div>
              <div class="heading">
				  <i class="icon-bullhorn"></i>${activity.activityPlace?default("")} &nbsp;&nbsp;
				  <i class="icon-time"></i>${activity.activityTime?default("")}
              </div>
			  <div class="post-message">
				  <form action="${request.contextPath }/admin/activity/detail">
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
								<a href="${request.contextPath }/admin/integral/delete?integralId=${item.id}&activityId=${activity.id}"><i class="icon-remove text-warning"></i></a>
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
							<th class="text-center">
							  操作
							</th>
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
								     <select class="form-control" id="${item.id}">
								         <#if activity.isRace == 1>
											 <option value="1" <#if item.rank??><#if item.rank == 1 > selected="selected"</#if></#if>>一等奖</option>
											 <option value="2" <#if item.rank??><#if item.rank == 2 > selected="selected"</#if></#if>>二等奖</option>
											 <option value="3" <#if item.rank??><#if item.rank == 3 > selected="selected"</#if></#if>>三等奖</option>
								         </#if>
										 <option value="4" <#if item.rank??><#if item.rank == 4 > selected="selected"</#if></#if>>参与</option>
										 <option value="0" <#if !item.rank??> selected="selected"</#if>>无</option>
									 </select>
								   </div>
								 </div>
				              </td>
							 <td class="hidden-xs">
								 <span class="label label-success">+${item.integral?default(0)}</span>
							 </td>
							  <td>
							   <div class="row">
							   <a href="#" onclick="updateIntegral('${item.id}','${item.id}','${activity.id}')" class="btn btn-xs btn-success-outline">保存修改</a>
							   <a href="${request.contextPath }/admin/integral/delete?integralId=${item.id}&activityId=${activity.id}" class="btn btn-xs btn-danger-outline">移除</a>
							   </div>
							  </td>
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
				 alert("添加成功！");
				 location.href="${request.contextPath }/admin/activity/detail?activityId="+activityId+"&keyWord=";
			 }
		 })
	 }
	 /*修改学生活动的获得奖项*/
	 function updateIntegral(id,selectedId,activityId){
		 var selected =  $("#"+selectedId+" option:selected").val();
		 $.ajax({
			 url: "${request.contextPath }/integral/update",
			 type: "post",
			 dataType: "json",
			 data: {
				 "id": id,
				 "rank": selected
			 },
			 success: function (data) {
				 alert("修改成功！");
				 location.href = "${request.contextPath }/admin/activity/detail?activityId=" + activityId + "&keyWord=";
			 }
		 })
	 }

 </script>
 </#if>