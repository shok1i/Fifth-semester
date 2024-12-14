<#import "blocks/template.ftlh" as t>
<@t.template>

    <table border="1">
        <thead>
        <tr>
            <th>Coffee ID</th>
            <th>Coffee Name</th>
            <th>Coffee Description</th>
            <th>Coffee Price</th>
        </tr>
        </thead>
        <tbody>
        <#list coffees as coffee>
            <tr>
                <td>${coffee.coffeeID}</td>
                <td>${coffee.coffeeName}</td>
                <td>${coffee.coffeeDescription}</td>
                <td>${coffee.coffeePrice}</td>
            </tr>
        </#list>
        </tbody>
    </table>

</@t.template>