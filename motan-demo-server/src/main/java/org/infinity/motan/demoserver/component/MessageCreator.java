package org.infinity.motan.demoserver.component;

import org.infinity.motan.demoserver.config.ApplicationConstants;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageCreator {
    private final MessageSource messageSource;

    public MessageCreator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code, Object... arguments) {
        return messageSource.getMessage(code, arguments, ApplicationConstants.SYSTEM_LOCALE);
    }
}
