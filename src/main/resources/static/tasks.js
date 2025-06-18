function aggiungiTask() {
    const input = document.getElementById("task");
    const testo = input.value.trim();

    if (testo !== "") {
        const ul = document.getElementById("taskList");

        const li = document.createElement("li");

        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";

        const span = document.createElement("span");
        span.textContent = testo;

        const elimina = document.createElement("button");
        elimina.textContent = "delete";
        elimina.onclick = () => li.remove();

        li.appendChild(checkbox);
        li.appendChild(span);
        li.appendChild(elimina);
        ul.appendChild(li);

        backup(testo);
        input.value = "";
    }
}

function backup(text){
    username=document.cookie;

    fetch('http://localhost:8081/api/auth/backup', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username: username, text: text })
    }).catch(error => {
        alert(error.message);
    });
}