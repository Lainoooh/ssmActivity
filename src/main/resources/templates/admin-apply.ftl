<#include "admin-header.ftl">
<#if applyList??>
<!-- End Navigation -->
<div class="container-fluid main-content">
    <div class="page-title">
        <h1>
            申报列表
        </h1>
    </div>
    <!-- DataTables Example -->
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height clearfix">
                <div class="heading">
                    <i class="icon-table"></i>申报详情
                </div>
                <div class="widget-content padded clearfix">
                    <table class="table table-bordered table-striped" id="dataTable1">
                        <thead>
                        <th>
                            申报人
                        </th>
                        <th>
                            学号
                        </th>
                        <th class="hidden-xs">
                            手机号
                        </th>
                        <th class="hidden-xs">
                            邮箱
                        </th>
                        <th class="hidden-xs">
                            申报类型
                        </th>
                        <th class="hidden-xs">
                            活动名称
                        </th>
                        <th class="hidden-xs">
                            描述
                        </th>
                        <th class="hidden-xs">
                            申报时间
                        </th>
                        <th class="hidden-xs">
                            处理情况
                        </th>
                        <th class="hidden-xs">
                            处理人
                        </th>
                        <th class="hidden-xs">
                            处理时间
                        </th>
                        <th class="hidden-xs">
                            操作
                        </th>
                        </thead>
                        <tbody>
                        <#list applyList as item>
                            <tr>
                            <td>
                            ${item.userName}
                            </td>
                            <td style="font-size: 5px" width="90px">
                            ${item.stuNo}
                            </td>
                            <td class="hidden-xs" style="font-size: 5px" width="90px">
                            ${item.telNum?if_exists}
                            </td>
                            <td class="hidden-xs" style="font-size: 5px" width="90px">
                            ${item.email?if_exists}
                            </td>
                            <td class="hidden-xs" style="font-size: 5px" width="50px">
                            <#if item.applyType == 3>
                                <span class="label label-warning">重置密码</span>
                            <#elseif item.applyType == 2>
                                <span class="label label-primary">活动信息</span>
                            <#else>
                                <span class="label label-info">个人信息</span>
                            </#if>
                            </td>
                            <td class="hidden-xs" style="font-size: 5px" width="90px">
                            ${item.activityName?default("无")}
                            </td>
                            <td class="hidden-xs" style="font-size: 5px" width="300px">
                            ${item.dsc?if_exists}
                            </td>
                            <td class="hidden-xs" style="font-size: 8px" width="90px">
                            ${item.createTime?string("yyyy-MM-dd HH:mm:ss")}
                            </td>
                            <td class="hidden-xs">
                                <#if item.isDel == 1>
                                    <span class="label label-default">已处理</span>
                                <#else>
                                    <span class="label label-warning">未处理</span>
                                </#if>
                            </td>
                            <td class="hidden-xs" style="font-size: 8px" width="70px">
                            ${item.delName?default("无")}
                            </td>
                            <td class="hidden-xs" style="font-size: 8px" width="90px">
                            <#if item.updateTime??>
                                ${item.updateTime?string("yyyy-MM-dd HH:mm:ss")}
                            </#if>
                            </td>
                            <td>
                                <#if item.isDel == 1>
                                    <span class="label label-default">已处理</span>
                                <#else>
                                    <a onclick="del('${item.id}')" class="btn btn-sm btn-danger" >标记为处理</a>
                                </#if>
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
<script>

    function del(id) {
            $.ajax({
                url: "${request.contextPath }/apply/update",
                type: "post",
                dataType: "json",
                data: {
                    "id":id
                },
                success: function (data) {
                    if(data.code == 200){
                        alert("处理成功！");
                        location.href = "${request.contextPath }/admin/apply/list";
                    }else{
                        alert(data.error);
                    }
                }
            })
    }

</script>
</div>
</body>
</#if>
</html>