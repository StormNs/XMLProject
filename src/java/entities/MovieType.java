/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author StormNs
 */
@Entity
@Table(name = "Movies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieType.findAll", query = "SELECT m FROM MovieType m")
    , @NamedQuery(name = "MovieType.findById", query = "SELECT m FROM MovieType m WHERE m.id = :id")
    , @NamedQuery(name = "MovieType.findByName", query = "SELECT m FROM MovieType m WHERE m.name = :name")
    , @NamedQuery(name = "MovieType.findByAlternateName", query = "SELECT m FROM MovieType m WHERE m.alternateName = :alternateName")
    , @NamedQuery(name = "MovieType.findByDescription", query = "SELECT m FROM MovieType m WHERE m.description = :description")
    , @NamedQuery(name = "MovieType.findByCountry", query = "SELECT m FROM MovieType m WHERE m.country = :country")
    , @NamedQuery(name = "MovieType.findByRuntime", query = "SELECT m FROM MovieType m WHERE m.runtime = :runtime")
    , @NamedQuery(name = "MovieType.findByCategoryId", query = "SELECT m FROM MovieType m WHERE m.categoryId = :categoryId")
    , @NamedQuery(name = "MovieType.findByLanguage", query = "SELECT m FROM MovieType m WHERE m.language = :language")
    , @NamedQuery(name = "MovieType.findByReleaseDate", query = "SELECT m FROM MovieType m WHERE m.releaseDate = :releaseDate")
    , @NamedQuery(name = "MovieType.findByRating", query = "SELECT m FROM MovieType m WHERE m.rating = :rating")
    , @NamedQuery(name = "MovieType.findByImageCover", query = "SELECT m FROM MovieType m WHERE m.imageCover = :imageCover")
    , @NamedQuery(name = "MovieType.findByTrailerUrl", query = "SELECT m FROM MovieType m WHERE m.trailerUrl = :trailerUrl")
    , @NamedQuery(name = "MovieType.findByDirector", query = "SELECT m FROM MovieType m WHERE m.director = :director")})
public class MovieType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "AlternateName")
    private String alternateName;
    @Column(name = "Description")
    private String description;
    @Column(name = "Country")
    private String country;
    @Column(name = "Runtime")
    private Integer runtime;
    @Column(name = "CategoryId")
    private Integer categoryId;
    @Column(name = "Language")
    private String language;
    @Column(name = "ReleaseDate")
    private String releaseDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Rating")
    private Double rating;
    @Column(name = "ImageCover")
    private String imageCover;
    @Column(name = "TrailerUrl")
    private String trailerUrl;
    @Column(name = "Director")
    private String director;
    @OneToMany(mappedBy = "movieId")
    private Collection<Favourites> favouritesCollection;

    public MovieType() {
    }

    public MovieType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlternateName() {
        return alternateName;
    }

    public void setAlternateName(String alternateName) {
        this.alternateName = alternateName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getImageCover() {
        return imageCover;
    }

    public void setImageCover(String imageCover) {
        this.imageCover = imageCover;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @XmlTransient
    public Collection<Favourites> getFavouritesCollection() {
        return favouritesCollection;
    }

    public void setFavouritesCollection(Collection<Favourites> favouritesCollection) {
        this.favouritesCollection = favouritesCollection;
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
        if (!(object instanceof MovieType)) {
            return false;
        }
        MovieType other = (MovieType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MovieType[ id=" + id + " ]";
    }
    
}
