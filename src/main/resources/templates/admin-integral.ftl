<#include "admin-header.ftl">
<#if integralList??>
    <!-- End Navigation -->
    <div class="container-fluid main-content">
        <div class="page-title">
          <h1>
            积分记录列表
          </h1>
        </div>
        <!-- DataTables Example -->
        <div class="row">
          <div class="col-lg-12">
            <div class="widget-container fluid-height clearfix">
              <div class="heading">
                <i class="icon-table"></i>积分记录
              </div>
              <div class="widget-content padded clearfix">
                <table class="table table-bordered table-striped" id="dataTable1">
                  <thead>
                    <th class="check-header hidden-xs">
                      <label><input id="checkAll" name="checkAll" type="checkbox"><span></span></label>
                    </th>
                    <th>
                      学生名称
                    </th>
                    <th>
                      学号
                    </th>
                    <th>
                      活动名称
                    </th>
                    <th class="hidden-xs">
                      活动时间
                    </th>
					<th class="hidden-xs">
					  活动名次
					</th>
                    <th class="hidden-xs">
                      活动积分
                    </th>
                    <th>操作</th>
                  </thead>
                  <tbody>
                  <#list integralList as item>
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
					  <td>
					    ${item.userName}
					  </td>
                      <td>
                        ${item.stuNo}
                      </td>
                      <td>
                        ${item.activityName}
                      </td>
                      <td>
                        ${item.activityTime}
                      </td>
                      <td class="hidden-xs">
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
                      </td>
					  <td class="hidden-xs">
					    <span class="label label-success">+${item.integral?default(0)}</span>
					  </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="${request.contextPath }/admin/activity/detail?activityId=${item.activityId}&keyWord="><i class="icon-eye-open"></i>查看详情</a>
                        </div>
                      </td>
                    </tr>
                    </#list>
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
</#if>
</html>