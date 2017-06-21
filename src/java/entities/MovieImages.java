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
@Table(name = "MovieImages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieImages.findAll", query = "SELECT m FROM MovieImages m")
    , @NamedQuery(name = "MovieImages.findById", query = "SELECT m FROM MovieImages m WHERE m.id = :id")
    , @NamedQuery(name = "MovieImages.findByImageUrl", query = "SELECT m FROM MovieImages m WHERE m.imageUrl = :imageUrl")
    , @NamedQuery(name = "MovieImages.findByDate", query = "SELECT m FROM MovieImages m WHERE m.date = :date")})
public class MovieImages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "ImageUrl")
    private String imageUrl;
    @Column(name = "Date")
    private String date;
    @JoinColumn(name = "MovieId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Movies movieId;

    public MovieImages() {
    }

    public MovieImages(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        if (!(object instanceof MovieImages)) {
            return false;
        }
        MovieImages other = (MovieImages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MovieImages[ id=" + id + " ]";
    }
    
}
