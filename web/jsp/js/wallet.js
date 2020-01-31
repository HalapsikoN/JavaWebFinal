function replenishBalanceFormById(id) {

    var message = "Invalid date:\n";
    var noError = true;

    if((!isFinite(document.getElementById("amount").value)) || document.getElementById("amount").value <= 0 ){
        message+=" *  Enter the amount which is more than 0\n";
        noError=false;
    }

    if (!noError) {
        alert(message);
    }else {
        document.getElementById(id).submit();
    }
}