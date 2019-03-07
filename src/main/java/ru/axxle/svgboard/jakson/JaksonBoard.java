package ru.axxle.svgboard.jakson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JaksonBoard {

    private long id;
    private String width;
    private String height;
    private String viewbox;

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

    @Override
    public String toString() {
        return "JaksonBoard{" +
                "id=" + id +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", viewbox='" + viewbox + '\'' +
                '}';
    }
}
