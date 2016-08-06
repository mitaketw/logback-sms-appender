package tw.com.mitake.logback.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import tw.com.mitake.sms.MitakeSms;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    private static final String DEFAULT_TITLE = "Logback SMS Appender";
    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd:HHmmssSSS");
    // 20160806:165926109,main,E,tw.com.mitake.AppTest,java.lang.RuntimeException:Oops,tw.com.mitake.AppTest.testSms,13
    private static final String FORMAT_MESSAGE = "%s - %s,%s,%s,%s,%s:%s,%s.%s,%d";

    private String username;
    private String password;
    private String to;
    private String title = DEFAULT_TITLE;

    @Override
    public void start() {
        if (!checkProperty()) {
            addError("No set username / password / to [" + name + "].");

            return;
        }

        MitakeSms.init(username, password);

        super.start();
    }

    private boolean checkProperty() {
        return username != null && username.length() != 0 && password != null && password.length() != 0
                && to != null && to.length() != 0;
    }

    @Override
    public void append(ILoggingEvent event) {
        createIssue(event);
    }

    private void createIssue(ILoggingEvent event) {
        String msg = transformStackTrace(event);

        MitakeSms.send(to, msg);
    }

    private String transformStackTrace(ILoggingEvent event) {
        IThrowableProxy throwableProxy = event.getThrowableProxy();
        StackTraceElement stackTraceElement = throwableProxy.getStackTraceElementProxyArray()[0].getStackTraceElement();

        String time = DEFAULT_DATE_FORMAT.format(new Date(event.getTimeStamp()));
        String threadName = event.getThreadName();
        String level = event.getLevel().toString().substring(0, 1);
        String logger = event.getLoggerName();
        String exception = throwableProxy.getClassName();
        String msg = throwableProxy.getMessage();
        String className = stackTraceElement.getClassName();
        String method = stackTraceElement.getMethodName();
        int lineNumber = stackTraceElement.getLineNumber();

        return String.format(FORMAT_MESSAGE, title, time, threadName, level, logger, exception, msg, className, method, lineNumber);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}