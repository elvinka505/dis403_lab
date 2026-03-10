<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Все лица</title>
</head>
<body>

    <h2>Список всех лиц</h2>

    <nav>
        <a href="/">Главная</a> |
        <a href="/persons/new">Добавить лицо</a>
    </nav>

    <table border="1" cellpadding="5" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Тип</th>
                <th>Имя</th>
                <th>Доп. информация</th>
            </tr>
        </thead>
        <tbody>
            <#list persons as person>
            <tr>
                <td>${person.id}</td>
                <td>${person.type}</td>
                <td>${person.name}</td>
                <td>
                    <#if person.type == "Admin">
                        ${person.email!"-"}
                    <#elseif person.type == "Client">
                        ${person.address!"-"}
                    <#else>
                        -
                    </#if>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>

    <#if !persons?has_content>
        <p>Пока нет ни одного лица.</p>
    </#if>

</body>
</html>
