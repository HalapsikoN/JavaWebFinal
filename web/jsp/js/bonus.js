function bonusAddForm(id) {

    var message = "Invalid date:\n";
    var noError = true;

    if(document.getElementById("name").value == null || document.getElementById("name").value.length < 1){
        message+=" *  Enter the name(minimal length is 1 symbols!)\n";
        noError=false;
    }
    if(document.getElementById("discount").value == null || document.getElementById("discount").value < 1 || document.getElementById("discount").value >100){
        message+=" *  Enter the discount(from 1 to 100)\n";
        noError=false;
    }
    if(document.getElementById("start_date").value === "" || document.getElementById("end_date").value === ""){
        message+=" *  Enter the dates\n";
        noError=false;
    }else {
        var startDate=new Date(document.getElementById("start_date").value);
        var endDate=new Date(document.getElementById("end_date").value);
        var start=new Date("1970-01-01");
        var end=new Date("2099-12-31");

        if(startDate<start || startDate>end || endDate<start || endDate>end) {
            message += " *  The dates must be between(1970-01-01 and 2099-12-31)\n";
            noError = false;
        }

        if(startDate>endDate){
            message += " *  The end date must be later than start date\n";
            noError = false;
        }
    }

    if (!noError) {
        alert(message);
    }else {
        document.getElementById(id).submit();
    }
}