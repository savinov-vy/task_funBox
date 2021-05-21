package ru.savinov.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.savinov.app.exception.VisitFormatException;

import java.net.MalformedURLException;
import java.net.URL;

@Data
@AllArgsConstructor
public class VisitDto {

    @JsonValue
    private String visit;

    public String getDomain() {
        if (visit.isBlank()) {
            return "";
        }
        if (notStartWithProtocol()) {
            visit = "https://" + visit;
        }
        return getHost();
    }

    private boolean notStartWithProtocol() {
        return !(visit.startsWith("https") || visit.startsWith("http"));
    }

    private String getHost() {
        try {
            return new URL(visit).getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        throw new VisitFormatException("this visit format is not provided");
    }
}
