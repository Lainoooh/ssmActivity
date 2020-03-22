<#include "admin-header.ftl">
    <!-- End Navigation -->
    <div class="container-fluid main-content">
        <div class="page-title">
          <h1>
            活动管理
          </h1>
        </div>
        <!-- DataTables Example -->
        <div class="row">
          <div class="col-lg-12">
            <div class="widget-container fluid-height clearfix">
              <div class="heading">
                <i class="icon-table"></i>活动列表
                  <a href="${request.contextPath }/admin/activity/add" class="btn btn-info pull-right">添加活动</a>
                  <a data-toggle="modal" href="#myModal" class="btn btn-warning pull-right">Excel导入</a>
              </div>
                <div class="modal fade" id="myModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
                                <h4 class="modal-title">
                                    Excel导入
                                </h4>
                            </div>
                            <form id= "uploadForm" enctype="multipart/form-data" action="${request.contextPath }/admin/activity/excel/import" method="post">
                                <div class="modal-body">
                                    <div class="widget-container fluid-height clearfix">
                                        <div class="widget-content padded form-horizontal">
                                            <div class="form-group">
                                                <a href="${request.contextPath }/admin/activity/export/template" class="btn btn-info pull-right">下载模板</a>
                                                <input type="file" class="btn btn-warning" name="file"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <input class="btn btn-primary" type="submit" value="确认导入"/>
                                    <button class="btn btn-default-outline" data-dismiss="modal" type="button">关闭</button>
                                </div>
                            </form>
                        </div>
                    </div>
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
                        ${item.stuNum}
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
                          <a class="table-actions" href="${request.contextPath }/admin/activity/detail?activityId=${item.id}&keyWord="><i class="icon-eye-open"></i></a>
                          <a class="table-actions" href="${request.contextPath }/admin/activity/add?activityId=${item.id}"><i class="icon-pencil"></i></a>
                          <a class="table-actions" onclick="deleteById('${item.id}')" href="javascript:;"><i class="icon-trash"></i></a>
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
  <script>
      function deleteById(id){
          $.ajax({
              url: "${request.contextPath }/activity/delete",
              type: "get",
              dataType: "json",
              data: {
                  "id":id
              },
              success: function (data) {
                  if(data.code == 200){
                      alert("操作成功！");
                      location.href = "${request.contextPath }/admin/activity/list";
                  }else{
                      alert(data.error);
                  }
              }
          })
      }

  </script>
</html>