package ru.itis.dto;

public class MovieDto {
    private String title;
    private String link;

    public MovieDto(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}
