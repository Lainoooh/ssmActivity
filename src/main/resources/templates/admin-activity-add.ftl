<#include "admin-header.ftl">
<!-- End Navigation -->
<div class="container-fluid main-content">
    <div class="page-title">
        <h1>
            <#if activity??>
                    更新活动
                <#else >
                    增加活动
            </#if>
        </h1>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="widget-container fluid-height clearfix">
                <div class="heading">
                    <i class="icon-reorder"></i>请填写活动信息
                </div>
                <div class="widget-content padded form-horizontal">

                        <input id="id" class="form-control" value="<#if activity??>${activity.id}</#if>"  type="hidden">
                        <div class="form-group">
                            <label class="control-label col-md-2">活动名称</label>
                            <div class="col-md-7">
                                <input id="activityName" value="<#if activity??>${activity.activityName}</#if>"  class="form-control" placeholder="请填写活动名称" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">活动地点</label>
                            <div class="col-md-7">
                                <input id="activityPlace" value="<#if activity??>${activity.activityPlace}</#if>" class="form-control" placeholder="请填写活动地点" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">活动描述</label>
                            <div class="col-md-7">
                                <input id="activityDsc" value="<#if activity??>${activity.activityDsc}</#if>" class="form-control" placeholder="请填写活动描述" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">活动开始时间</label>
                            <div class="col-md-4">
                                <div class="input-group date datepicker" data-date-autoclose="true" data-date-format="yyyy-mm-dd">
                                    <input id="startDay" value="${startDay?if_exists}" class="form-control" type="text"><span class="input-group-addon"><i class="icon-calendar"></i></span></input>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="input-group bootstrap-timepicker">
                                    <input id="startTime" value="${startTime?if_exists}" class="form-control timepicker-24h"   type="text"><span class="input-group-addon"><i class="icon-time"></i></span></input>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2">活动结束时间</label>
                            <div class="col-md-4">
                                <div class="input-group date datepicker" data-date-autoclose="true" data-date-format="yyyy-mm-dd">
                                    <input id="endDay" value="${endDay?if_exists}" class="form-control" type="text"><span class="input-group-addon"><i class="icon-calendar"></i></span></input>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="input-group bootstrap-timepicker">
                                    <input id="endTime" value="${endTime?if_exists}" class="form-control timepicker-24h" type="text"><span class="input-group-addon"><i class="icon-time"></i></span></input>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-2">活动类型</label>
                            <div class="col-md-7">
                                <label class="radio-inline">
                                    <input name="isRace"
                                    <#if activity??>
                                        <#if activity.isRace == 0>
                                            checked="checked"
                                        </#if>
                                        <#else >
                                            checked="checked"
                                    </#if> type="radio" value="0"><span>非竞赛类</span>
                                </label>
                                <label class="radio-inline">
                                    <input  name="isRace"
                                            <#if activity??>
                                            <#if activity.isRace == 1>
                                                checked="checked"
                                            </#if>
                                            </#if>
                                            type="radio" value="1"><span>竞赛类</span>
                                </label>
                            </div>
                        </div>

                        <div class="form-group" id="racePriceDiv">
                            <label class="control-label col-md-2">积分设置</label>
                            <div class="col-md-2">
                                <input id="prize1" value="<#if activity??>${activity.prize1}</#if>"  class="form-control" placeholder="一等奖" type="number">
                            </div>
                            <div class="col-md-2">
                                <input id="prize2" value="<#if activity??>${activity.prize2}</#if>" class="form-control" placeholder="二等奖" type="number">
                            </div>
                            <div class="col-md-2">
                                <input id="prize3" value="<#if activity??>${activity.prize3}</#if>" class="form-control" placeholder="三等奖" type="number">
                            </div>
                        </div>
                        <div class="form-group">
                            <label id="noRaceLabel" class="control-label col-md-2"></label>
                            <div class="col-md-2">
                                <input id="prize4" value="<#if activity??>${activity.prize4}</#if>" class="form-control" placeholder="参与奖" type="number">
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="control-label col-md-2"></label>
                            <div class="col-md-5">
                                <button class="btn btn-primary btn-block" onclick="add()">确认<#if activity??>修改<#else >添加</#if></button>
                            </div>
                            <div class="col-md-2">
                                <a href="${request.contextPath }/admin/activity/list" class="btn btn-default-outline btn-block">返回</a>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    </div>

</div>
    
    </div>
<script>
    <#if activity??>
        <#if activity.isRace == 1>
                $("#racePriceDiv").slideDown(500);
                $("#noRaceLabel").text("");
            <#else >
                $("#racePriceDiv").slideUp(500);
                $("#noRaceLabel").text("积分设置");
        </#if>
    <#else >
        $("#racePriceDiv").slideUp(500);
        $("#noRaceLabel").text("积分设置");
    </#if>


    $("input[name='isRace']").click(function () {
        var isRace = $("input[name='isRace']:checked").val();
        if (isRace == 1) {
            $("#racePriceDiv").slideDown(500);
            $("#noRaceLabel").text("");
        }else{
            $("#racePriceDiv").slideUp(500);
            $("#noRaceLabel").text("积分设置");
        }
    })

    function add() {
        var id = $("#id").val();
        var activityName = $("#activityName").val();

        var activityDsc = $("#activityDsc").val();
        var activityPlace = $("#activityPlace").val();
        var startTime = $("#startDay").val()+" "+$("#startTime").val();
        var endTime = $("#endDay").val()+" "+$("#endTime").val();

        var isRace = $("input[name='isRace']:checked").val();

        var prize1 = $("#prize1").val();
        var prize2 = $("#prize2")   .val();
        var prize3 = $("#prize3").val();
        var prize4 = $("#prize4").val();

        var start = new Date(startTime);
        var end = new Date(endTime);
        var isSendFlag = false;
        if (activityName == null || activityName == "") {
            alert("活动名称不能为空");
        }else if(activityDsc == null || activityDsc == "") {
            alert("活动描述不能为空");
        }else if(activityPlace == null || activityPlace == "") {
            alert("活动地址不能为空");
        }else if(activityDsc == null || activityDsc == "") {
            alert("活动描述不能为空");
        }else if (start < new Date()) {
            alert("开始时间不能早于当前时间");
        } else if (startTime == null || $("#startDay").val() == "") {
            alert("开始时间不能为空");
        } else if (endTime == null || $("#endDay").val() == "") {
            alert("结束时间不能为空");
        } else if (end <= start) {
            alert("结束时间不能早于开始时间");
        } else {
            if (isRace == 1) {
                if (prize1 == null || prize1 < 1) {
                    alert("一等奖积分不能为空");
                } else if (prize2 == null || prize2 < 1) {
                    alert("二等奖积分不能为空");
                } else if (prize3 == null || prize3 < 1) {
                    alert("三等奖积分不能为空");
                } else {
                    isSendFlag = true;
                }
            } else {
                if (prize4 == null || prize4 < 1) {
                    alert("参与奖积分不能为空");
                } else {
                    isSendFlag = true;
                }
            }
        }
        if(isSendFlag){
            $.ajax({
                url: "${request.contextPath }/activity/add",
                type: "post",
                dataType: "json",
                data: {
                    "id":id,
                    "activityName": activityName,
                    "activityDsc": activityDsc,
                    "activityPlace": activityPlace,
                    "startTime": startTime,
                    "endTime": endTime,
                    "isRace": isRace,
                    "prize1": prize1,
                    "prize2": prize2,
                    "prize3": prize3,
                    "prize4": prize4
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
    }
    
</script>
  </body>


</html>