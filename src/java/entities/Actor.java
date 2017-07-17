/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author StormNs
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actorType", propOrder = {"img", "name", "character"})
@XmlRootElement(name = "actor")
public class Actor {

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = false)
    private String img;
    @XmlElement(required = true)
    private String character;

    public Actor(String name, String img, String character) {
        this.name = name;
        this.img = img;
        this.character = character;
    }

    public Actor() {
    }
    


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

}
