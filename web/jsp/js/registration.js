function cypherText(text) {
    var result = "";
    for (var i = text.length - 1; i >= 0; i--) {
        result += text[i];
    }
    return result;
}

function regForm() {

    var message = "Invalid date:\n";
    var noError = true;

    if(document.getElementById("name").value == null || document.getElementById("name").value.length < 2){
        message+=" *  Enter the name(minimal length is 2 symbols!)\n";
        noError=false;
    }
    if(document.getElementById("login").value == null || document.getElementById("login").value.length < 4){
        message+=" *  Enter the login(minimal length is 4 symbols!)\n";
        noError=false;
    }
    if(document.getElementById("password").value == null || document.getElementById("password").value.length < 6){
        message+=" *  Enter the password(minimal length is 6 symbols!)\n";
        noError=false;
    }
    if (document.getElementById("password").value !== document.getElementById("re_pass").value) {
        noError=false;
        message+=" *  Repeated password is not the same!\n";

    }

    if (!noError) {
        alert(message);
    }else {

        document.getElementById("password").value=cypherText(document.getElementById("password").value);
        document.getElementById("form").submit();

    }
}
