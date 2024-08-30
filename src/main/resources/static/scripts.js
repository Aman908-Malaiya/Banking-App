const baseUrl = 'http://localhost:8080/api/accounts';

function createAccount() {
    const accountId = document.getElementById('id').value;
    fetch(`${baseUrl}/create`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ id: accountId })
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('result').innerText = 'Account Created: ' + JSON.stringify(data);
    });
}

function getAccountById() {
    const accountId = document.getElementById('accountId').value;
    fetch(`${baseUrl}/${accountId}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('result').innerText = 'Account Details: ' + JSON.stringify(data);
        });
}

function deposit() {
    const accountId = document.getElementById('accountId').value;
    const amount = document.getElementById('amount').value;
    fetch(`${baseUrl}/${accountId}/deposit`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ amount: amount })
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('result').innerText = 'Deposit Successful: ' + JSON.stringify(data);
    });
}

function withdraw() {
    const accountId = document.getElementById('accountId').value;
    const amount = document.getElementById('amount').value;
    fetch(`${baseUrl}/${accountId}/withdraw`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ amount: amount })
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('result').innerText = 'Withdrawal Successful: ' + JSON.stringify(data);
    });
}

function getAllAccounts() {
    fetch(`${baseUrl}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('result').innerText = 'All Accounts: ' + JSON.stringify(data);
        });
}

function deleteAccount() {
    const accountId = document.getElementById('accountId').value;
    fetch(`${baseUrl}/${accountId}`, {
        method: 'DELETE'
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('result').innerText = 'Account Deleted: ' + JSON.stringify(data);
    });
}
