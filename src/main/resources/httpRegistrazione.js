document.getElementById('registrationForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username: username, password: password })
    })
        .then(response => {
            if (!response.ok) throw new Error('Registrazione fallita');
            return response.json();
        })
        .then(data => {
            if (data.success) {
                window.location.href = 'RegistrationSuccesfull.html';
            } else {
                alert('Registrazione non riuscita: ' + (data.message || 'Errore sconosciuto'));
            }
        })
        .catch(error => {
            alert('Errore durante la registrazione: ' + error.message);
        });
})
