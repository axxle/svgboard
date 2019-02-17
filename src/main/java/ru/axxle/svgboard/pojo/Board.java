package ru.axxle.svgboard.pojo;

/**
 * Описание объекта "Доска"
 */
public class Board {

    private long id;
    private String width;
    private String height;
    private String viewbox;

    public Board(String width, String height, String viewbox){
        this.width = width;
        this.height = height;
        this.viewbox = viewbox;
    }

    public Board(long id, String width, String height, String viewbox){
        this.id = id;
        this.width = width;
        this.height = height;
        this.viewbox = viewbox;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getViewbox() {
        return viewbox;
    }

    public void setViewbox(String viewbox) {
        this.viewbox = viewbox;
    }
}
