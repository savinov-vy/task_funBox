package ru.savinov.test_helpers.factories.visit;

import ru.savinov.app.controller.dto.VisitDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VisitDtoFactory {

    public static List<String> ofVists() {
        return List.of(
                "https://ya.ru",
                "https://ya.ru?q=123",
                "funbox.ru",
                "https://stackoverflow.com/questions/11828270/how-to-exit-the-vim-editor");
    }

    public static Set<String> ofDomain() {
        return Set.of(
                "ya.ru",
                "funbox.ru",
                "stackoverflow.com");
    }
    
    public static List<VisitDto> of() {
        return of(ofVists());
    }

    public static List<VisitDto> of(List<String> list) {
        return list
                .stream().map(VisitDto::new).collect(Collectors.toList());
    }
}
