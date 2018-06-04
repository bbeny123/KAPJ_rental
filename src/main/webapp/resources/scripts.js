$(document).ready( function() {
    $('#data').DataTable();
});

window.onload = function() {
    document.getElementById("logout").onclick = function() {
        logout();
    }
};

function validatePassword(){
    var password = document.getElementById("password"), confirm_password = document.getElementById("confirm_password");
    if(password.value !== confirm_password.value) {
        confirm_password.setCustomValidity("Passwords Don't Match");
    } else {
        confirm_password.setCustomValidity('');
    }
}

function activateUserReservations() {
    activateMenu('user_reservations');
}

function activateCars() {
    activateMenu('cars');
}

function activateUsers() {
    activateMenu('users');
}

function activateReservations() {
    activateMenu('reservations');
}

function activateLogin() {
    activateMenu('login');
}

function activateRegister() {
    activateMenu('register');
}

function activateMenu(active) {
    document.getElementById(active).classList.add('active');
}

function logout() {
    document.forms['logout'].submit();
}