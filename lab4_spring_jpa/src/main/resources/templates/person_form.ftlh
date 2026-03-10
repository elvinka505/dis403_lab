<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить лицо</title>
    <script>
        function toggleFields() {
            var type = document.getElementById("type").value;
            document.getElementById("emailField").style.display = (type === "admin") ? "block" : "none";
            document.getElementById("addressField").style.display = (type === "client") ? "block" : "none";
        }
    </script>
</head>
<body>

    <h2>Добавить новое лицо</h2>

    <nav>
        <a href="/">Главная</a> |
        <a href="/persons">Все лица</a>
    </nav>

    <br/>

    <form action="/persons" method="post">

        <label>Тип лица:</label><br/>
        <select id="type" name="type" onchange="toggleFields()">
            <option value="admin">Администратор (Admin)</option>
            <option value="client">Клиент (Client)</option>
        </select>
        <br/><br/>

        <label>Имя:</label><br/>
        <input type="text" name="name" required/>
        <br/><br/>

        <div id="emailField">
            <label>Email:</label><br/>
            <input type="text" name="email"/>
            <br/><br/>
        </div>

        <div id="addressField" style="display:none;">
            <label>Адрес:</label><br/>
            <input type="text" name="address"/>
            <br/><br/>
        </div>

        <button type="submit">Сохранить</button>

    </form>

</body>
</html>
