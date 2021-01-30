package ru.itis.models;

public class Director {
    private Long id;
    private String name;
    private String picturePath;

    public Director(Long id, String name, String picturePath) {
        this.id = id;
        this.name = name;
        this.picturePath = picturePath;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPicturePath() {
        return picturePath;
    }
}
