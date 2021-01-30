package ru.itis.dto;

public class DirectorDto {
    private String name;
    private String link;

    public DirectorDto(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }
}
