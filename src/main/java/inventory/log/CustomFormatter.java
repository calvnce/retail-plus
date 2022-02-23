package inventory.log;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter  extends Formatter {

    @Override
    public String format(LogRecord record) {
          return String.format("[%1$tF %1$tT [%2$-7s] %3$s %n]",
                new Date(record.getMillis()),
                record.getLevel().getLocalizedName(),
                record.getMessage());
    }
}
