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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "MovieUrl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieUrl.findAll", query = "SELECT m FROM MovieUrl m")
    , @NamedQuery(name = "MovieUrl.findById", query = "SELECT m FROM MovieUrl m WHERE m.id = :id")
    , @NamedQuery(name = "MovieUrl.findByUrl", query = "SELECT m FROM MovieUrl m WHERE m.url = :url")
    , @NamedQuery(name = "MovieUrl.findByEpisode", query = "SELECT m FROM MovieUrl m WHERE m.episode = :episode")
    , @NamedQuery(name = "MovieUrl.findByQuality", query = "SELECT m FROM MovieUrl m WHERE m.quality = :quality")})
public class MovieUrl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for auto generated ID
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Url")
    private String url;
    @Column(name = "Episode")
    private Integer episode;
    @Column(name = "Quality")
    private String quality;
    @JoinColumn(name = "MovieId", referencedColumnName = "Id")
    @ManyToOne
    private MovieType movieId;

    public MovieUrl() {
    }

    public MovieUrl(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public MovieType getMovieId() {
        return movieId;
    }

    public void setMovieId(MovieType movieId) {
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
        if (!(object instanceof MovieUrl)) {
            return false;
        }
        MovieUrl other = (MovieUrl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MovieUrl[ id=" + id + " ]";
    }
    
}
