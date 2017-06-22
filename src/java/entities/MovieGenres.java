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
@Table(name = "MovieGenres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieGenres.findAll", query = "SELECT m FROM MovieGenres m")
    , @NamedQuery(name = "MovieGenres.findById", query = "SELECT m FROM MovieGenres m WHERE m.id = :id")})
public class MovieGenres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @JoinColumn(name = "GenreId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Genres genreId;
    @JoinColumn(name = "MovieId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Movies movieId;

    public MovieGenres() {
    }

    public MovieGenres(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Genres getGenreId() {
        return genreId;
    }

    public void setGenreId(Genres genreId) {
        this.genreId = genreId;
    }

    public Movies getMovieId() {
        return movieId;
    }

    public void setMovieId(Movies movieId) {
        this.movieId = movieId;
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
        if (!(object instanceof MovieGenres)) {
            return false;
        }
        MovieGenres other = (MovieGenres) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MovieGenres[ id=" + id + " ]";
    }
    
}
