package br.com.fiap.roardemo.model;

import java.util.List;

public class Roar {
    private Long id;
    private String content;
    private int likes;
    private String username;


    public Roar(Long id, String content, int likes, List<String> replies, String username) {
        this.id = id;
        this.content = content;
        this.likes = likes;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
