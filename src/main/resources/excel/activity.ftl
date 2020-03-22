<#escape x as x!"">
    <table>
    <caption>${sheetName}</caption>
    <thead>
<tr>
    <#list titles as title>
        <th>${title}</th>
    </#list>
</tr>
    </thead>
    <tbody>
    <#list list as item>
        <tr>
        <td style="display:inline-block;width:400px">${item.activityName}</td>
        <td style="display:inline-block;width:400px">${item.activityDsc}</td>
        <td style="display:inline-block;width:400px">${item.activityPlace}</td>
        <#--<td string style="display:inline-block;width:400px">${item.startTime}</td>-->
        <#--<td string style="display:inline-block;width:400px">${item.endTime}</td>-->
        <td style="display:inline-block;width:400px">${item.isRaceString}</td>
        <td style="display:inline-block;width:400px">${item.prize1}</td>
        <td style="display:inline-block;width:400px">${item.prize2}</td>
        <td style="display:inline-block;width:400px">${item.prize3}</td>
        <td style="display:inline-block;width:400px">${item.prize4}</td>
        </tr>
    </#list>
    </tbody>
    </table>
</#escape>