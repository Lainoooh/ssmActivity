<#include "student-header.ftl">
    <!-- End Navigation -->
    <div class="container-fluid main-content">
        <div class="page-title">
          <h1>
              活动列表
          </h1>
        </div>
        <!-- DataTables Example -->
        <div class="row">
          <div class="col-lg-12">
            <div class="widget-container fluid-height clearfix">
              <div class="heading">
                <i class="icon-table"></i>查询活动
                  <#--<a href="${request.contextPath }/admin/activity/add" class="btn btn-info pull-right">添加活动</a>-->
                  <#--<a href="" class="btn btn-warning pull-right">导入</a>-->
              </div>
              <div class="widget-content padded clearfix">
                <table class="table table-bordered table-striped" id="dataTable1">
                  <thead>
                    <th class="check-header hidden-xs">
                      <label><input id="checkAll" name="checkAll" type="checkbox"><span></span></label>
                    </th>
                    <th>
                      活动名称
                    </th>
                    <th>
                      活动时间
                    </th>
                    <th class="hidden-xs">
                      活动类型
                    </th>
                    <th class="hidden-xs">
                      已参与人数
                    </th>
					<th class="hidden-xs">
					  活动状态
					</th>
                    <th class="hidden-xs">
                      一等奖积分
                    </th>
					<th class="hidden-xs">
					  二等奖积分
					</th>
					<th class="hidden-xs">
					  三等奖积分
					</th>
					<th class="hidden-xs">
					  参与奖积分
					</th>
                    <th>操作</th>
                  </thead>
                  <tbody>
                    <#if activityList??>
                    <#list activityList as item>
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      <td>
                          ${item.activityName}
                      </td>
                      <td class="hidden-xs" style="font-size: 8px" width="150px">
                        ${item.activityTime}
                      </td>
                      <td class="hidden-xs">
                        <#if item.isRace == 1>
                            <span class="label label-warning">竞赛类</span>
                        <#else>
                            <span class="label label-info">非竞赛类</span>
                        </#if>
                      </td>
                      <td class="hidden-xs">
                        <#if item_index <=2 >
                            <span class="text-danger glyphicon glyphicon-fire"></span>${item.stuNum}<span class="text-danger glyphicon glyphicon-fire"></span>
                            <#else >
                            ${item.stuNum}
                        </#if>
                      </td>
					  <td class="hidden-xs">
                        <#switch  item.state>
                            <#case 0>
                                <span class="label label-success">报名中</span>
                                <#break >
                            <#case 1>
                                <span class="label label-danger">进行中</span>
                                <#break >
                            <#case 2>
                                <span class="label label-default">已结束</span>
                                <#break >
                        </#switch>
					  </td>
					  <td class="hidden-xs">
					    ${item.prize1}
					  </td>
					  <td class="hidden-xs">
					    ${item.prize2}
					  </td>
					  <td class="hidden-xs">
					    ${item.prize3}
					  </td>
					  <td class="hidden-xs">
					    ${item.prize4}
					  </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="${request.contextPath }/user/activity/detail?activityId=${item.id}&keyWord="><i class="icon-eye-open"></i></a>
                          <#--<a class="table-actions" href="${request.contextPath }/admin/activity/add?activityId=${item.id}"><i class="icon-pencil"></i></a>-->
                          <#--<a class="table-actions" onclick="deleteById('${item.id}')" href="javascript:;"><i class="icon-trash"></i></a>-->
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
        <!-- end DataTables Example -->
      </div>
    
    </div>
  </body>
</html>