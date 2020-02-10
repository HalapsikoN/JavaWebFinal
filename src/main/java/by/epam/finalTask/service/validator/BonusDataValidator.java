package by.epam.finalTask.service.validator;

import by.epam.finalTask.entity.Bonus;

import java.util.Calendar;

public class BonusDataValidator {

    private BonusDataValidator() {
    }

    public static boolean isValidName(String name) {
        return ((name != null) && (!name.isEmpty()));
    }

    public static boolean isValidDiscount(int discount) {
        return discount > 0 && discount <= 100;
    }

    public static boolean isValidDate(Calendar start, Calendar end) {
        boolean result = true;

        if (start == null || end == null) {
            result = false;
        } else {
            if (end.compareTo(start) < 0) {
                result = false;
            }
        }

        return result;
    }

    public static boolean isValidBonus(Bonus bonus) {
        return bonus != null && isValidName(bonus.getName()) && isValidDiscount(bonus.getDiscount()) && isValidDate(bonus.getStartDate(), bonus.getEndDate());
    }


}
