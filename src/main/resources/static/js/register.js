function disableButton() {
    var btn = document.getElementById('loginButton');
    btn.disabled = true;
    btn.innerHTML = 'Logging in...'; // Thay đổi nội dung nút để phản hồi nhanh
}

function registerUser() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const customer = {
        email: email,
        password: password
    };

    fetch('/api/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(customer)
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
        // Redirect to login page after successful registration
        if (data === "Người dùng đã đăng ký thành công!") {
            window.location.href = "/login";
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
