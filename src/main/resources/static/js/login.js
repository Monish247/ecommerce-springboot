function disableButton() {
    var btn = document.getElementById('loginButton');
    btn.disabled = true;
    btn.innerHTML = 'Logging in...'; // Thay đổi nội dung nút để phản hồi nhanh
}
