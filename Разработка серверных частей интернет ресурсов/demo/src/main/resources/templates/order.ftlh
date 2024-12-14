<#import "blocks/template.ftlh" as t>
<@t.template>

    <table border="1">
        <thead>
        <tr>
            <th>Order ID</th>
            <th>Order Date</th>
            <th>Customer Name</th>
            <th>Order Price</th>
        </tr>
        </thead>
        <tbody>
        <#list orders as order>
            <tr>
                <td>${order.orderID}</td>
                <td>${order.orderDate}</td>
                <td>${order.customerName}</td>
                <td>${order.orderPrice}</td>
            </tr>
        </#list>
        </tbody>
    </table>

</@t.template>