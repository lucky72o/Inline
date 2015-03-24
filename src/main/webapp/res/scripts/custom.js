$(document).ready(function () {

    $("#submitRegistrationFormBtn").click(function(e) {
        e.preventDefault();
        if (validateRegistrationForm()) {
            $("#registrationForm").submit();
        }
    });

});













// ******************   VALIDATION   ****************** //

function validateRegistrationForm() {

    var password = $("#password");
    var passwordConfirmation = $("#passwordConfirmation");
    var name = $("#name");
    var username = $("#username");
    var email = $("#email");

    var fieldNotEmpty = false;

    if (validateNotEmpty(email) & validateNotEmpty(password) & validateNotEmpty(passwordConfirmation) & validateNotEmpty(name) & validateNotEmpty(username)) {
        fieldNotEmpty = true;
    } else {
        $("#registrationPageGlobalError").show();
        return false;
    }

    return true;
}

var emailRegex = /^[\w\.\-_]+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
var passwordRegex = /^[A-Za-z0-9/-]{6,18}$/;
var phoneNumber = /[0-9-()+]{3,20}/;

function validateAgainstPattern(text, pattern) {

    if (text.match(pattern)) {
        return true;
    }

    return false;
}

function validatePasswordsMatch(password, passwordConfirmation) {

    if (password == passwordConfirmation) {
        return true;
    } else {
        var newHtml = "<h4>Не совпадают пароли!</h4>";
        newHtml = newHtml + "<p>Пожалуйста заполните заново введённый пароль и подтверждение пароля.</p>";
        $("#popup_message").html(newHtml);
        $(".overlay").fadeIn(300);
        return false;
    }
}

function validateNotEmpty(input) {
    if (input.val() == undefined || input.val() == "") {
        input.closest('div').addClass("has-error");
        return false;
    } else {
        return true;
    }
}
