$(document).ready(function () {

    $("#submitRegistrationFormBtn").click(function (e) {
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

    var validationFailed = false;

    if (!(validateNotEmpty(email) & validateNotEmpty(password) & validateNotEmpty(passwordConfirmation) & validateNotEmpty(name) & validateNotEmpty(username))) {
        validationFailed = true;
    }


    if (!validateAgainstPattern(email.val(), emailRegex)) {
        validationFailed = true;
        email.closest('div').addClass("has-error");
    } else {
        email.closest('div').removeClass("has-error");
    }

    var validPasswords = true;

    if (!validateAgainstPattern(password.val(), passwordRegex)) {

        validationFailed = true;
        password.closest('div').addClass("has-error");
        validPasswords = false;
    } else {
        password.closest('div').removeClass("has-error");
    }

    if (!validateAgainstPattern(passwordConfirmation.val(), passwordRegex)) {
        validationFailed = true;
        passwordConfirmation.closest('div').addClass("has-error");
        validPasswords = false;
    } else {
        passwordConfirmation.closest('div').removeClass("has-error");
    }

    if (validPasswords) {
        if (!validatePasswordsMatch(password, passwordConfirmation)) {
            validationFailed = true;
        }
    }

    if (validationFailed) {
        $("#registrationPageGlobalError").html("<strong>Registration form validation error!</strong>Please fix errors below.").show();
        return false;
    }

    $("#registrationPageGlobalError").hide();
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

function validateNotEmpty(input) {
    if (input.val() == undefined || input.val() == "") {
        input.closest('div').addClass("has-error");
        return false;
    } else {
        input.closest('div').removeClass("has-error");
        return true;
    }
}

function validatePasswordsMatch(password, passwordConfirmation) {

    if (password.val() == passwordConfirmation.val()) {
        password.closest('div').removeClass("has-error");
        passwordConfirmation.closest('div').removeClass("has-error");
        return true;
    } else {
        password.closest('div').addClass("has-error");
        passwordConfirmation.closest('div').addClass("has-error");
        return false;
    }
}
