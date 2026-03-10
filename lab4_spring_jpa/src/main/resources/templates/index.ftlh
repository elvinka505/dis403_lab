<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Главная страница</title>
</head>
<body>

    <h2>Главная страница</h2>

    <nav>
        <a href="/persons">Все лица</a> |
        <a href="/persons/new">Добавить лицо</a>
    </nav>

    <h3>Телефоны</h3>
    <div>
        <#list phones as phone>
        <div>
            <span id="phone${phone.id}">${phone.id}</span>
            <span>${phone.number}</span>
        </div>
        </#list>
    </div>

</body>
</html>
