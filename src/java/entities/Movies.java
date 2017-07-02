/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author StormNs
 */
public class Movies {
    
    private List<MovieType> movie;

    public List<MovieType> getMovies() {
        if(movie == null){
         movie = new ArrayList<>();   
        }
        return movie;
    }

    public void setMovies(List<MovieType> movie) {
        this.movie = movie;
    }
    
    
    
    
}
