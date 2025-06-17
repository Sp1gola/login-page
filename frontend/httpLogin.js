document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('http://localhost:8080/api/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ login: username, password: password })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Credenziali non valide');
            }
            return response.json();
        })
        .then(data => {
            window.location.href = 'loginSuccessfull.html';
        })
        .catch(error => {
            alert(error.message);
        });
});