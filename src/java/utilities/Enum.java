/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author StormNs
 */
public enum Enum {
     MOVIE_IMG("movie_img"), ACTOR_IMG("actor_img"), LARGE_SEQUENCE("@._V1__.jpg"), TOKEN("._V1");
    private String text;

    private Enum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
    
    
    
}
