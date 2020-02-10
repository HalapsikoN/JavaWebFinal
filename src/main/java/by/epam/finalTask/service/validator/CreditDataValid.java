package by.epam.finalTask.service.validator;

import by.epam.finalTask.entity.Credit;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CreditDataValid {

    private CreditDataValid() {
    }

    public static boolean isAmountValid(double amount) {
        return amount > 0;
    }

    public static boolean isEndDateValid(Calendar calendar) {
        boolean result = true;

        if (calendar == null) {
            result = false;
        } else {
            Calendar today = new GregorianCalendar();
            if (today.compareTo(calendar) >= 0) {
                result = false;
            }
        }

        return result;
    }

    public static boolean isCreditValid(Credit credit) {
        return credit != null && isAmountValid(credit.getAmount()) && isEndDateValid(credit.getDate());
    }
}
