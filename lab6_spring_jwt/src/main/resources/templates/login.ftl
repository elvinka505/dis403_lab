<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Тест</title>
</head>
<body>
<div id="login_section" style="display:block">
    <form id="form" method="post">
        <div><input name="username" type="text" placeholder="username"/></div>
        <div><input name="password" type="password" placeholder="password"/></div>
        <div><input type="button" value="OK" onclick="login()"/></div>
    </form>
</div>

<div id="content_section" style="display:none">
    <h>Данные по бронированиям в гостинице:</h>
    <div>
        <table id="booking_table">

        </table>
    </div>

    <div id="edit_section" style="margin-top: 20px; display: none;">
        <h3>Редактирование бронирования</h3>
        <form id="edit_form">
            <input type="hidden" id="edit_id" />
            <input type="hidden" id="edit_person_id" />
            <div>
                <label>Имя гостя:</label>
                <input type="text" id="edit_name" />
            </div>
            <div>
                <label>Дата заезда:</label>
                <input type="date" id="edit_arrivaldate" />
            </div>
            <div>
                <label>Дата проживания:</label>
                <input type="date" id="edit_stayingdate" />
            </div>
            <div>
                <label>Дата выезда:</label>
                <input type="date" id="edit_departuredate" />
            </div>
            <div style="margin-top: 10px;">
                <input type="button" value="Сохранить" onclick="save_booking()" />
            </div>
        </form>
        <div id="save_status"></div>
    </div>
</div>

<div id="booking_data"></div>

<script>
    let jwt_token;
    function login() {

        const formDataToJson = (formData) => JSON.stringify(Object.fromEntries(formData));
        const formElement = document.getElementById('form');
        const jsonData = formDataToJson(new FormData(formElement));
        console.log(jsonData)

        fetch('http://localhost:8090/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: jsonData
        })
            .then(response => response.json())
            .then(json => {
                let token = json.token;
                jwt_token = token;
                //localStorage.setItem('jwt_token', token);
                //window.location = '/app';
                document.getElementById("login_section").style.display="none";
                document.getElementById("content_section").style.display="block";

                show_content();
            });
    }

    function show_content() {
        show_bookings();
    }

    function show_bookings() {
        fetch('http://localhost:8090/api/booking/allview', {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + jwt_token
            }
        })
            .then(response => response.json())
            .then(json => {
                make_booking_table(json);
            });
    }

    function make_booking_table(data) {
        let tbl = document.getElementById("booking_table");
        for (let i = 0; i < data.bookings.length; i++) {
            let tr = document.createElement('tr');
            let td_room = document.createElement('td');
            td_room.innerHTML = "<span onclick='open_booking("
                + data.bookings[i].id + ")'>" + data.bookings[i].room + "</span>";
            tr.append(td_room);
            let td_arrivaldate = document.createElement('td');
            td_arrivaldate.innerHTML = data.bookings[i].arrivaldate;
            tr.append(td_arrivaldate);
            let td_stayingdate = document.createElement('td');
            td_stayingdate.innerHTML = data.bookings[i].stayingdate;
            tr.append(td_stayingdate);

            tbl.append(tr);
        }
    }

    function open_booking(booking_id) {
        fetch('http://localhost:8090/api/booking/get/' + booking_id, {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + jwt_token
            }
        })
            .then(response => response.json())
            .then(json => {
                fill_booking(json);
            });
    }

    function fill_booking(data) {
        document.getElementById("edit_section").style.display = "block";
        document.getElementById("save_status").innerHTML = "";

        document.getElementById("edit_id").value = data.id;
        document.getElementById("edit_person_id").value = data.personId;
        document.getElementById("edit_name").value = data.name || '';

        document.getElementById("edit_arrivaldate").value = formatDate(data.arrivaldate);
        document.getElementById("edit_stayingdate").value = formatDate(data.stayingdate);
        document.getElementById("edit_departuredate").value = formatDate(data.departuredate);
    }

    function formatDate(timestamp) {
        if (!timestamp) return '';
        let d = new Date(timestamp);
        let month = ('0' + (d.getMonth() + 1)).slice(-2);
        let day = ('0' + d.getDate()).slice(-2);
        return d.getFullYear() + '-' + month + '-' + day;
    }

    function save_booking() {
        let bookingData = {
            id: parseInt(document.getElementById("edit_id").value),
            name: document.getElementById("edit_name").value,
            arrivaldate: document.getElementById("edit_arrivaldate").value,
            stayingdate: document.getElementById("edit_stayingdate").value,
            departuredate: document.getElementById("edit_departuredate").value,
            personId: parseInt(document.getElementById("edit_person_id").value)
        };

        fetch('http://localhost:8090/api/booking/update', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json;charset=utf-8',
                'Authorization': 'Bearer ' + jwt_token
            },
            body: JSON.stringify(bookingData)
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Ошибка сохранения');
                }
            })
            .then(json => {
                document.getElementById("save_status").innerHTML =
                    "<span style='color:green'>Сохранено!</span>";
            })
            .catch(err => {
                document.getElementById("save_status").innerHTML =
                    "<span style='color:red'>" + err.message + "</span>";
            });
    }
</script>


</body>
</html>