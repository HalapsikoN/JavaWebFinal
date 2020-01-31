function changeUsernameForm(id) {

    var message = "Invalid date:\n";
    var noError = true;

    if(document.getElementById("new_username").value == null || document.getElementById("new_username").value.length < 2){
        message+=" *  Enter the name(minimal length is 2 symbols!)\n";
        noError=false;
    }

    if (!noError) {
        alert(message);
    }else {
        document.getElementById(id).submit();
    }
}

function changePasswordForm(id) {

    var message = "Invalid date:\n";
    var noError = true;

    if(document.getElementById("new_password").value == null || document.getElementById("new_password").value.length < 6){
        message+=" *  Enter the password(minimal length is 6 symbols!)\n";
        noError=false;
    }
    if (document.getElementById("new_password").value !== document.getElementById("repeat_new_password").value) {
        noError=false;
        message+=" *  Repeated password is not the same!\n";

    }

    if (!noError) {
        alert(message);
    }else {
        document.getElementById(id).submit();
    }
}