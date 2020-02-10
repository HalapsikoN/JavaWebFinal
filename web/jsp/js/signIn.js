function signInForm() {

    var message = "Invalid date:\n";
    var noError = true;

    if (document.getElementById("login").value == null || document.getElementById("login").value.length < 4) {
        message += " *  Enter the login(minimal length is 4 symbols!)\n";
        noError = false;
    }
    if (document.getElementById("password").value == null || document.getElementById("password").value.length < 6) {
        message += " *  Enter the password(minimal length is 6 symbols!)\n";
        noError = false;
    }

    if (!noError) {
        alert(message);
    } else {
        document.getElementById("form").submit();
    }
}
