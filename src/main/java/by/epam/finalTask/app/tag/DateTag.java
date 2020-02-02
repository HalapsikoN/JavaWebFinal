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

            switch (dateTagFormats) {
                case YYYY:
                    pageContext.getOut().print(item.get(Calendar.YEAR));
                    break;
                case DD_MM_YYYY:
                    pageContext.getOut().print(item.get(Calendar.DATE) + DATE_DELIMITER + (item.get(Calendar.MONTH)+1) + DATE_DELIMITER + item.get(Calendar.YEAR));
                    break;
                case HH_MM_SS_DD_MM_YYYY:
                    pageContext.getOut().print(item.get(Calendar.HOUR_OF_DAY) + TIME_DELIMITER + item.get(Calendar.MINUTE) + TIME_DELIMITER + item.get(Calendar.SECOND) + " " + item.get(Calendar.DATE) + DATE_DELIMITER + (item.get(Calendar.MONTH)+1) + DATE_DELIMITER + item.get(Calendar.YEAR));
                    break;
            }

        } catch (IOException e) {
            logger.error(e);
        }

        return SKIP_BODY;
    }
}
