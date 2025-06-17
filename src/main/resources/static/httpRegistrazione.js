document.getElementById('registrationForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('http://localhost:8081/api/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username: username, password: password }) // match con @RequestBody User
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                window.location.href = 'registrationSuccessful.html';
            } else {
                alert('Registrazione non riuscita: ' + (data.message || 'Errore sconosciuto'));
            }
        })
        .catch(error => {
            alert('Errore durante la registrazione: ' + error.message);
        });
});