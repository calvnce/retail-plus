package inventory.log;

import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class Handler extends StreamHandler {
    @Override
    public synchronized void publish(LogRecord record) {
        super.publish(record);
    }

    @Override
    public synchronized void flush() {
        super.flush();
    }

    @Override
    public synchronized void close() throws SecurityException {
        super.close();
    }
}
