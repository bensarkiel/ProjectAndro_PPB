package com.example.CineTix.user.model;

public class HargaFilm {
    String MoviePrice;

    public HargaFilm(){
    }

    public HargaFilm(String moviePrice) {
        MoviePrice = moviePrice;
    }

    public String getMoviePrice() {
        return MoviePrice;
    }

    public void setMoviePrice(String moviePrice) {
        MoviePrice = moviePrice;
    }
}
