package ru.itis.models;

public class Movie {
    private Long id;
    private String title;
    private String description;
    private Long directorId;
    private Integer length;
    private Integer year;
    private String picturePath;

    public Movie(Long id, String title, String description, Long directorId, Integer length, Integer year, String picturePath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.directorId = directorId;
        this.length = length;
        this.year = year;
        this.picturePath = picturePath;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getDirectorId() {
        return directorId;
    }

    public Integer getLength() {
        return length;
    }

    public Integer getYear() {
        return year;
    }

    public String getPicturePath() {
        return picturePath;
    }
}
