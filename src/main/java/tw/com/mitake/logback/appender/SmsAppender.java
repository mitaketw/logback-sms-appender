package tw.com.mitake.logback.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import tw.com.mitake.sms.MitakeSms;

public class SmsAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    private LayoutWrappingEncoder<ILoggingEvent> encoder;
    private Layout<ILoggingEvent> layout;
    private String username;
    private String password;
    private String to;
    private String title;

    @Override
    public void start() {
        if (!checkProperty()) {
            addError("No set username / password / to / title [" + name + "].");

            return;
        }

        if (encoder == null) {
            addError("No encoder set for the appender named [" + name + "].");

            return;
        }

        try {
            encoder.init(System.out);

            layout = encoder.getLayout();
        } catch (Exception e) {
            addError("Exception", e);

            return;
        }

        MitakeSms.init(username, password);

        super.start();
    }

    private boolean checkProperty() {
        return username != null && username.length() != 0 && password != null && password.length() != 0 && to != null &&
                to.length() != 0 && title != null && title.length() != 0;
    }

    @Override
    public void append(ILoggingEvent event) {
        createIssue(event);
    }

    private void createIssue(ILoggingEvent event) {
        MitakeSms.send(to, layout.doLayout(event));
    }

    public LayoutWrappingEncoder<ILoggingEvent> getEncoder() {
        return encoder;
    }

    public void setEncoder(LayoutWrappingEncoder<ILoggingEvent> encoder) {
        this.encoder = encoder;
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