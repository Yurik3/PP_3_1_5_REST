const formAdd = document.forms["newUserForm"];
addNewUser();


function addNewUser() {
    formAdd.addEventListener("submit", e => {
        e.preventDefault()

        let addRoles = [];
        for (let i = 0; i < formAdd.roles.options.length; i++) {
            if (formAdd.roles.options[i].selected) addRoles.push({
                id: formAdd.roles.options[i].value,
                role: formAdd.roles.options[i].text
            });
        }

        fetch("http://localhost:8085/admin/users/", {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                username: formAdd.firstName.value,
                secondName: formAdd.lastName.value,
                age: formAdd.age.value,
                email: formAdd.email.value,
                password: formAdd.password.value,
                roles: addRoles

            })
        })
        formAdd.reset()
    })

}