const formEdit = document.forms["formEditUser"];

async function getUserId(id) {
    let res = await fetch("http://localhost:8085/admin/users/" + id);
    return await res.json();
}

async function Modal(id) {
    const modal = new bootstrap.Modal(document.getElementById('editUserModal'));
    modal.show();
    let user = await getUserId(id);
    formEdit.idEdit.value = user.id;
    formEdit.firstNameEdit.value = user.username;
    formEdit.lastNameEdit.value = user.secondName;
    formEdit.ageEdit.value = user.age;
    formEdit.emailEdit.value = user.email;
    formEdit.passwordEdit.value = user.password;

    editUser()

    function editUser() {
        formEdit.addEventListener("submit", e => {
            e.preventDefault()

            let editRoles = [];
            for (let i = 0; i < formEdit.roles.options.length; i++) {
                if (formEdit.roles.options[i].selected) editRoles.push({
                    id: formEdit.roles.options[i].value,
                    role: formEdit.roles.options[i].text
                });
            }

            fetch("http://localhost:8085/admin/users/" + id, {
                method: 'PATCH',
                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify({
                    username: formEdit.firstNameEdit.value,
                    secondName: formEdit.lastNameEdit.value,
                    age: formEdit.ageEdit.value,
                    email: formEdit.emailEdit.value,
                    password: formEdit.passwordEdit.value,
                    roles: editRoles
                })

            })
            modal.hide();
        })
    }
}


