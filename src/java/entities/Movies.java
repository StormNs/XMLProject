/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder = "movie")
@XmlRootElement(name="movies")
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
