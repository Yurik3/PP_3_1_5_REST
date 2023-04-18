getUserInfo();
const tbody = document.getElementById("tbodyUserInfo");

async function getUserInfo() {
    let res = await fetch("http://localhost:8085/user/info");

    let user = await res.json();
    let dataHtml = '';
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
    </tr>`


    tbody.innerHTML = dataHtml;

}

