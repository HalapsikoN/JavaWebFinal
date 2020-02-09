package by.epam.finalTask.app.tag;

import by.epam.finalTask.app.tag.util.DateTagFormats;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Calendar;

public class DateTag extends TagSupport {

    private static final Logger logger= LogManager.getLogger(DateTag.class);
    
    private final static String TIME_DELIMITER = ":";
    private final static String DATE_DELIMITER = ".";

    private String format;
    private Calendar item;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Calendar getItem() {
        return item;
    }

    public void setItem(Calendar item) {
        this.item = item;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            DateTagFormats dateTagFormats = DateTagFormats.valueOf(format.toUpperCase());

            if(item==null){
                return SKIP_BODY;
            }

            String year= String.valueOf(item.get(Calendar.YEAR));
            String month= ((item.get(Calendar.MONTH)+1)<10)?"0"+(item.get(Calendar.MONTH)+1):""+(item.get(Calendar.MONTH)+1);
            String date= (item.get(Calendar.DATE)<10)?"0"+item.get(Calendar.DATE):""+item.get(Calendar.DATE);
            String second= (item.get(Calendar.SECOND)<10)?"0"+item.get(Calendar.SECOND):""+item.get(Calendar.SECOND);
            String minute= (item.get(Calendar.MINUTE)<10)?"0"+item.get(Calendar.MINUTE):""+item.get(Calendar.MINUTE);
            String hour= (item.get(Calendar.HOUR_OF_DAY)<10)?"0"+item.get(Calendar.HOUR_OF_DAY):""+item.get(Calendar.HOUR_OF_DAY);

            switch (dateTagFormats) {
                case YYYY:
                    pageContext.getOut().print(year);
                    break;
                case DD_MM_YYYY:
                    pageContext.getOut().print(date + DATE_DELIMITER + month + DATE_DELIMITER + year);
                    break;
                case HH_MM_SS_DD_MM_YYYY:
                    pageContext.getOut().print(hour + TIME_DELIMITER + minute + TIME_DELIMITER + second + " " + date + DATE_DELIMITER + month + DATE_DELIMITER + year);
                    break;
            }

        } catch (IOException e) {
            logger.error(e);
        }

        return SKIP_BODY;
    }
}
