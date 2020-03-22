<#include "admin-header.ftl">
      <!-- Statistics -->
      <div class="row">
        <div class="col-lg-12">
          <div class="widget-container stats-container">
            <!-- <div class="col-md-3">
              <div class="number">
                <div class="icon globe"></div>
                86<small>%</small>
              </div>
              <div class="text">
                Overall growth
              </div>
            </div> -->
            <div class="col-md-4">
              <div class="number">
                <div class="icon visitors"></div>
                ${stuNum?default(0)}
              </div>
              <div class="text">
                学生总数
              </div>
            </div>
    			  <div class="col-md-4">
    			    <div class="number">
    			      <div class="icon-bar-chart"></div>
    			      ${activityNum?default(0)}
    			    </div>
    			    <div class="text">
    			      活动总数
    			    </div>
    			  </div>
            <div class="col-md-4">
              <div class="number">
                <div class="icon-warning-sign"></div>
                ${applyNum}
              </div>
              <div class="text">
                申请总数
              </div>
            </div>
            
          </div>
        </div>
      </div>
      <!-- 主体内容 -->
      <div class="row">
        <!-- Weather -->
        <div class="col-md-12">
                <div class="widget-container fluid-height clearfix">
                    <div class="heading">
                        <i class="icon-table"></i>最火活动
                    </div>
                    <div class="widget-content padded clearfix">
                        <table class="table table-hover">
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
                                <span class="text-danger glyphicon glyphicon-fire"></span>${item.stuNum}<span class="text-danger glyphicon glyphicon-fire"></span>
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
          <div class="col-md-12">
              <div class="widget-container fluid-height clearfix">
                  <div class="heading">
                      <i class="icon-table"></i>最活跃学生
                  </div>
                  <div class="widget-content padded clearfix">
                      <table class="table table-hover">
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
                                ${item.integralNum}<span class="text-success glyphicon glyphicon-thumbs-up"></span>
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
    </div>
    </div>
  </body>

</html>