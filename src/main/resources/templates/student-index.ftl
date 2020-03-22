<#include "student-header.ftl">

    <!-- End Navigation -->
    <div class="container-fluid main-content">
    <!-- 主体内容 -->
    <div class="container-fluid main-content">
      <div class="page-title">
        <h1>
          个人主页
        </h1>
      </div>
      <div class="invoice">
        <div class="row">
          <div class="col-md-12">
            <div class="well">
              <#if userInfo??>
              <strong>
                详细资料
                <a data-toggle="modal" href="#myModal" class="btn btn-sm btn-danger pull-right">我要申报</a>
              </strong>
              <h3>
                ${userInfo.userName?if_exists}
              </h3>
              <p>
                学号：${userInfo.stuNo?if_exists}<br>
                手机号：${userInfo.telNum?if_exists}<br>
                邮箱：${userInfo.email?if_exists}<br>
                累计学分：${userInfo.integralNum?default(0)}<br>
                累计参与活动：${userInfo.activityNum?default(0)}次<br>
               </p>
              </#if>
            </div>
          </div>
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
        <div class="row">
          <div class="col-lg-12">
            <div class="widget-container fluid-height">
              <div class="widget-content padded clearfix">
                <div class="col-lg-12">
                  <form action="${request.contextPath }/user/index">
                    <input   name="userId" type="hidden" value="${userInfo.id}">
                    <div class="col-lg-offset-7 col-lg-1">
                        <label class="control-label" >学年学期搜索：</label>
                    </div>
                    <div class="col-lg-1">
                    <div class="form-group">
                        <select class="form-control pull-right" name="year">
                          <#if yearSet??>
                            <#list yearSet as item>
                              <option value="${item}">${item}</option>
                            </#list>
                            <#else >
                              <option value="2020" >2020</option>
                          </#if>
                        </select>
                    </div>
                  </div>
                    <div class="col-lg-1">
                      <div class="form-group">
                        <select class="form-control pull-right" name="semester">
                          <option value="0" <#if semester??><#if semester == 0 > selected="selected"</#if></#if>>全年</option>
                          <option value="1" <#if semester??><#if semester == 1 > selected="selected"</#if></#if>>第一学期</option>
                          <option value="2" <#if semester??><#if semester == 2 > selected="selected"</#if></#if>>第二学期</option>
                        </select>
                      </div>
                    </div>
                  <div class="col-lg-1">
                        <input type="submit" class="btn btn-info pull-right" value="搜索">
                  </div>
                  </form>
                </div>

                <table class="table table-striped invoice-table">
                  <thead>
                  <th width="50"></th>
                  <th>
                    活动名称（点击查看详情）
                  </th>
                  <th width="200">
                    活动时间
                  </th>
                  <th width="120">
                    活动名次
                  </th>
                  <th width="100">
                    获得积分
                  </th>
                  </thead>
                  <tbody>
                  <#if integralList??>
                      <#list  integralList as item>
                        <tr>
                        <td>
                        #${item_index +1}
                        </td>
                        <td>
                      <a class="table-actions" href="${request.contextPath }/user/activity/detail?activityId=${item.activityId}&keyWord=">${item.activityName}</a>
                        </td>
                        <td>
                        ${item.activityTime}
                        </td>
                        <td>
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
                        <td>
                          <span class="label label-success">+${item.integral?default(0)}</span>
                        </td>
                        </tr>
                      </#list>
                  </#if>
                  </tbody>
                  <tfoot>
                  <tr>
                    <td class="text-right" colspan="4">
                      <strong>参与活动</strong>
                    </td>
                    <td>
                      ${activityNum?default(0)}次
                    </td>
                  </tr>
                  <tr>
                    <td class="text-right" colspan="4">
                      <h4 class="text-primary">
                        共计积分
                      </h4>
                    </td>
                    <td>
                      <h4 class="text-primary">
                        ${integralNum?default(0)}
                      </h4>
                    </td>
                  </tr>
                  </tfoot>
                </table>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12">
            <a class="btn btn-primary pull-right" onclick="javascript:window.print();"><i class="icon-print"></i>Print invoice</a>
          </div>
        </div>
      </div>
    </div>

  </div>
    
    </div>
  </body>
<script>

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
          "applyType":1,
          "dsc": dsc
        },
        success: function (data) {
          if(data.code == 200){
            alert("申报成功！");
            location.href = "${request.contextPath }/user/index";
          }else{
            alert(data.error);
          }
        }
      })
    }
  }

</script>
</html>