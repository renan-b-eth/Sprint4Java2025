package br.com.fiap.roardemo.service;

import br.com.fiap.roardemo.model.Roar;

import java.util.ArrayList;
import java.util.List;

public class RoarService {

    private static List<Roar> roars = new ArrayList<>();

    public static List<Roar> getRoars() {
        return roars;
    }

    public static void addRoar(String content, String username) {
        Roar roar = new Roar(roars.size() + 1L, content, 0, new ArrayList<>(), username);
        roars.add(roar);
    }

    public static void likeRoar(Long tweetId) {
        for (Roar roar : roars) {
            if (roar.getId().equals(tweetId)) {
                roar.setLikes(roar.getLikes() + 1);
            }
        }
    }

}
