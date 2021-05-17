package ru.savinov.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.savinov.app.exeption.VisitFormatException;

import java.net.MalformedURLException;
import java.net.URL;

@Data
@AllArgsConstructor
public class VisitDTO {

    @JsonValue
    private String link;

    public String getDomain() throws Exception {
        if (link.isBlank()) {
            return "";
        }
        if (notStartWithProtocol()) {
            link = "https://" + link;
        }
        return getHost();
    }

    private String getHost() throws Exception {

        try {
            return new URL(link).getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        throw new VisitFormatException("links format exeption");
    }

    private boolean notStartWithProtocol() {
        return !(link.startsWith("https") || link.startsWith("http"));
    }

}
