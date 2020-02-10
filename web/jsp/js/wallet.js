function replenishBalanceFormById(id) {

    var message = "Invalid date:\n";
    var noError = true;

    if ((!isFinite(document.getElementById("amount").value)) || document.getElementById("amount").value <= 0) {
        message += " *  Enter the amount which is more than 0\n";
        noError = false;
    }

    if (!noError) {
        alert(message);
    } else {
        document.getElementById(id).submit();
    }
}

function addCreditFormById(id) {
    var message = "Invalid date:\n";
    var noError = true;

    if (document.getElementById("credit").value == null || document.getElementById("credit").value < 1 || document.getElementById("credit").value > 100) {
        message += " *  Enter the amount(from 1 to 100)\n";
        noError = false;
    }
    if (document.getElementById("date").value === "") {
        message += " *  Enter the end date\n";
        noError = false;
    } else {
        var today = new Date();
        var endDate = new Date(document.getElementById("date").value);
        var monthInMiliss = 30 * 86400 * 1000;

        if (endDate < today || endDate.getTime() - today.getTime() > monthInMiliss) {
            message += " *  The dates must be between max for a month from today\n";
            noError = false;
        }

    }

    if (!noError) {
        alert(message);
    } else {
        document.getElementById(id).submit();
    }
}