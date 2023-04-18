const formDelete = document.forms["deleteUserForm"];

async function getUserId(id) {
    let res = await fetch("http://localhost:8085/admin/users/" + id);
    return await res.json();
}

async function Delete(id) {
    const modal = new bootstrap.Modal(document.getElementById('deleteUserModal'));
    modal.show();

    let user = await getUserId(id);
    let roles = [];
    user.roles.forEach(role => {
        roles.push(role.name)
    })
    formDelete.idDelete.value = user.id;
    formDelete.firstNameDelete.value = user.username;
    formDelete.lastNameDelete.value = user.secondName;
    formDelete.ageDelete.value = user.age;
    formDelete.emailDelete.value = user.email;
    formDelete.passwordDelete.value = user.password;
    formDelete.roleDelete.value = roles;

    formDelete.addEventListener("submit", e => {
        e.preventDefault()

        fetch("http://localhost:8085/admin/users/" + id, {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json"
            }
        })
        modal.hide();
    })

}
