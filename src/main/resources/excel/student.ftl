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
        <td style="display:inline-block;width:400px">${item.userName}</td>
        <td style="display:inline-block;width:400px">${item.stuNo}</td>
        <td style="display:inline-block;width:400px">${item.telNum}</td>
        <td style="display:inline-block;width:400px">${item.email}</td>
        </tr>
    </#list>
    </tbody>
    </table>
</#escape>