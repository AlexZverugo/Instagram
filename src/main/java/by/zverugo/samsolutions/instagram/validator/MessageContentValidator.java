package by.zverugo.samsolutions.instagram.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MessageContentValidator {

    private final Logger LOGGER = Logger.getLogger(getClass());

    private static final Pattern VALID_URL_PATTERN = Pattern.compile("(.*)(http:\\/\\/|https:\\/\\/)+(www.)?" +
            "([a-zA-Z0-9а-яА-Я]+)(\\.[a-zA-Z0-9а-яА-Я])*\\.[a-zа-я]{2,4}(.*)", Pattern.CASE_INSENSITIVE);

    public String encodeMessage(String messageContent) {
        messageContent = messageContent.replaceAll("<", "&lt");
        messageContent = messageContent.replaceAll(">", "&gt");
        messageContent = messageContent.replaceAll("\"", "&quot");

        return messageContent;
    }

    public String findLink(String messageContent) {
        String[] strings = messageContent.split(" ");
        StringBuilder result = new StringBuilder();

        for(String  pieceOfMessage : strings) {
            Matcher matcher = VALID_URL_PATTERN.matcher(pieceOfMessage);
            if (matcher.find()) {
                String url = matcher.group().substring(matcher.group().indexOf("http"));
                result.append(pieceOfMessage.replace( url, "<a href=\"" + url + "\" target=\"_blank\">" +  url + "</a>"));
            } else {
                result.append(pieceOfMessage);
            }
            result.append(" ");
        }

        return result.toString();
    }
}
