<#include "admin-header.ftl">
<#if userList??>
    <!-- End Navigation -->
    <div class="container-fluid main-content">
        <div class="page-title">
          <h1>
            学生列表
          </h1>
        </div>
        <!-- DataTables Example -->
        <div class="row">
          <div class="col-lg-12">
            <div class="widget-container fluid-height clearfix">
              <div class="heading">
                <i class="icon-table"></i>学生信息
                <a href="${request.contextPath }/admin/student/add" class="btn btn-info pull-right">添加学生</a>
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
                    <form id= "uploadForm" enctype="multipart/form-data" action="${request.contextPath }/admin/student/excel/import" method="post">
                    <div class="modal-body">
                      <div class="widget-container fluid-height clearfix">
                        <div class="widget-content padded form-horizontal">
                          <div class="form-group">
                              <a href="${request.contextPath }/admin/student/export/template" class="btn btn-info pull-right">下载模板</a>
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
                      学生名称
                    </th>
                    <th>
                      学号
                    </th>
                    <th class="hidden-xs">
                      参与活动数
                    </th>
					<th class="hidden-xs">
					  积分总和
					</th>
                    <th class="hidden-xs">
                      手机号
                    </th>
					<th class="hidden-xs">
					  邮箱
					</th>
                    <th>操作</th>
                  </thead>
                  <tbody>
                  <#list userList as item>

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
                        ${item.activityNum}
                      </td>
                      <td class="hidden-xs">
                        ${item.integralNum}
                      </td>
					  <td class="hidden-xs">
                        ${item.telNum?if_exists}
					  </td>
					  <td class="hidden-xs">
                        ${item.email?if_exists}
					  </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="${request.contextPath }/admin/student/info?userId=${item.id}"><i class="icon-eye-open"></i></a>
                          <a class="table-actions" href="${request.contextPath }/admin/student/add?id=${item.id}"><i class="icon-pencil"></i></a>
                          <a class="table-actions" onclick="deleteById('${item.id}')" href="javascript:;"><i class="icon-trash"></i></a>
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
  <script>
    <#if errorMsg??>
    alert('${errorMsg}');
    </#if>


    function deleteById(id){
      $.ajax({
        url: "${request.contextPath }/student/delete",
        type: "get",
        dataType: "json",
        data: {
          "id":id
        },
        success: function (data) {
          if(data.code == 200){
            alert("操作成功！");
            location.href = "${request.contextPath }/admin/student/list";
          }else{
            alert(data.error);
          }
        }
      })
    }
  </script>
  </body>
</#if>
</html>