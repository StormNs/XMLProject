/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author StormNs
 */
@Entity
@Table(name = "Cast")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cast.findAll", query = "SELECT c FROM Cast c")
    , @NamedQuery(name = "Cast.findById", query = "SELECT c FROM Cast c WHERE c.id = :id")
    , @NamedQuery(name = "Cast.findByCharacter", query = "SELECT c FROM Cast c WHERE c.character = :character")})
public class Cast implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Character")
    private String character;
    @JoinColumn(name = "MovieId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private MovieType movieId;
    @JoinColumn(name = "ActorId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private PersonType actorId;

    public Cast() {
    }

    public Cast(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public MovieType getMovieId() {
        return movieId;
    }

    public void setMovieId(MovieType movieId) {
        this.movieId = movieId;
    }

    public PersonType getActorId() {
        return actorId;
    }

    public void setActorId(PersonType actorId) {
        this.actorId = actorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cast)) {
            return false;
        }
        Cast other = (Cast) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cast[ id=" + id + " ]";
    }
    
}
