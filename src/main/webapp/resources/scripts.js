$(document).ready( function() {
    $('#data').dataTable();
});

window.onload = function() {
    document.getElementById("logout").onclick = function() {
        logout();
    }
};

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