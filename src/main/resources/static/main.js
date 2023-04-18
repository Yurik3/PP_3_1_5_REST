async function getRes() {
    let res = await fetch("http://localhost:8085/admin/users");

    let JS = await res.json();

    let tbody = document.getElementById("tbodyUser");

    let dataHtml = '';
    JS.forEach(user => {
        let roles = [];
        user.roles.forEach(role => {
            roles.push(role.name)
        })
        dataHtml +=
            `<tr>
       <td> ${user.id} </td> 
       <td>${user.username}</td>
       <td>${user.userLastName}</td>
        <td>${user.age}</td>
        <td>${user.email}</td>
        <td>${roles}</td>
        <td><a class="btn btn-info text-white" data-bs-toggle="modal"
         onclick="Modal(${user.id})">Редактировать</a></td>
        <td><a class="btn btn-danger text-white" data-bs-toggle="modal"
              onclick="Delete(${user.id})">Удалить</a></td>
    </tr>`
    })

    tbody.innerHTML = dataHtml;
}

getRes();



