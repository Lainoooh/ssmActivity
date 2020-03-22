<#include "admin-header.ftl">
<!-- End Navigation -->
<div class="container-fluid main-content">
    <div class="page-title">
        <h1>
            <#if student??>
                    更新学生
                <#else >
                    增加学生
            </#if>
        </h1>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height clearfix">
                <div class="heading">
                    <i class="icon-reorder"></i>请填写学生信息
                </div>
                <div class="widget-content padded form-horizontal">

                        <input id="id" class="form-control" value="<#if student??>${student.id}</#if>"  type="hidden">
                        <div class="form-group">
                            <label class="control-label col-md-2">学生名称</label>
                            <div class="col-md-7">
                                <input id="userName" value="<#if student??>${student.userName?if_exists}</#if>"  class="form-control" placeholder="请填写学生名称" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">学号</label>
                            <div class="col-md-7">
                                <input id="stuNo" value="<#if student??>${student.stuNo?if_exists}</#if>" class="form-control" placeholder="请填写12位学号" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">密码</label>
                            <div class="col-md-7">
                                <input id="password" value="<#if student??>${student.password}</#if>" class="form-control" placeholder="请填写密码" type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">手机号</label>
                            <div class="col-md-7">
                                <input id="telNum" value="<#if student??>${student.telNum?if_exists}</#if>" class="form-control" placeholder="请填写手机号" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">邮箱</label>
                            <div class="col-md-7">
                                <input id="email" value="<#if student??>${student.email?if_exists}</#if>" class="form-control" placeholder="请填写邮箱" type="email">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-2"></label>
                            <div class="col-md-5">
                                <button class="btn btn-primary btn-block" onclick="add()">确认<#if student??>修改<#else >添加</#if></button>
                            </div>
                            <div class="col-md-2">
                                <a href="${request.contextPath }/admin/student/list" class="btn btn-default-outline btn-block">返回</a>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    </div>

</div>
    
    </div>
<script>

    function add() {
        var isSendFlag = false;
        var id = $("#id").val();
        var userName = $("#userName").val();

        var password = $("#password").val();
        var stuNo = $("#stuNo").val();

        var telNum = $("#telNum").val();
        var email = $("#email")   .val();

        var reg = new RegExp(/^\d{6}$/);     //工作密码必须是8位数字
        if (userName == null || userName == "") {
            alert("学生名称不能为空");
        }else if(password == null || password == "") {
            alert("学生密码不能为空");
        }else if(stuNo == null || stuNo == "") {
            alert("学号不能为空");
        }else if (stuNo.length != 12) {
            alert("学号格式错误，请填写12位学号");
        }else if (password.length != 6 || !reg.test(password)) {
            alert("密码格式错误，请填写6位数字");
        } else if (telNum == null || telNum == "") {
            alert("手机号不能为空");
        } else if (email == null || email == "") {
            alert("邮箱不能为空");
        } else {
            isSendFlag = true;
        }
        if(isSendFlag){
            $.ajax({
                url: "${request.contextPath }/student/add",
                type: "post",
                dataType: "json",
                data: {
                    "id":id,
                    "userName": userName,
                    "password": password,
                    "stuNo": stuNo,
                    "telNum": telNum,
                    "email": email
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
    }
    
</script>
  </body>


</html>