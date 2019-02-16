package ru.axxle.guestbook.pojo;

/**
 * Описание объекта "Отзыв" в гостевой книге
 */
public class Comment {

    private long id;

    private String name;

    private String text;

    /**
     * Конструктор без идентификатора отзыва
     * Используется при создании новых объектов, еще не сохраненных в БД
     * @param name - имя автора отзыва
     * @param text - текст отзыва
     */
    public Comment(String name, String text){
        this.name = name;
        this.text = text;
    }

    /**
     * Конструктор с идентификатором отзыва
     * Используется при извлечении отзывов из БД
     * @param id - идентификатор отзыва
     * @param name - имя автора отзыва
     * @param text - текст отзыва
     */
    public Comment(long id, String name, String text){
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
