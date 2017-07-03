/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author StormNs
 */
@Entity
@Table(name = "Movies")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "movieType", propOrder = {"id", "name", "alternateName",
    "description", "country", "rating", "releaseDate", "imageCover", "director"})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieType.findAll", query = "SELECT m FROM MovieType m")
    , @NamedQuery(name = "MovieType.findById", query = "SELECT m FROM MovieType m WHERE m.id = :id")
    , @NamedQuery(name = "MovieType.findByName", query = "SELECT m FROM MovieType m WHERE m.name = :name")
    , @NamedQuery(name = "MovieType.findByAlternateName", query = "SELECT m FROM MovieType m WHERE m.alternateName = :alternateName")
    , @NamedQuery(name = "MovieType.findByDescription", query = "SELECT m FROM MovieType m WHERE m.description = :description")
    , @NamedQuery(name = "MovieType.findByCountry", query = "SELECT m FROM MovieType m WHERE m.country = :country")
    , @NamedQuery(name = "MovieType.findByRuntime", query = "SELECT m FROM MovieType m WHERE m.runtime = :runtime")
//    , @NamedQuery(name = "MovieType.findByCategoryId", query = "SELECT m FROM MovieType m WHERE m.categoryId = :categoryId")
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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for auto generated ID
    @Column(name = "Id")
    @XmlElement(required = true)
    private Integer id;

    @Column(name = "Name")
    @XmlElement(required = true)
    private String name;

    @Column(name = "AlternateName")
    @XmlElement(required = true)
    private String alternateName;

    @Column(name = "Description")
    @XmlElement(required = true)
    private String description;

    @Column(name = "Country")
    @XmlElement(required = true)
    private String country;

    @Column(name = "Runtime")
    @XmlTransient
    private String runtime;
//    @Column(name = "CategoryId")
//    private Integer categoryId;
    @Column(name = "Language")
    @XmlTransient
    private String language;
    @Column(name = "ReleaseDate")
    @XmlElement(required = true)
    private String releaseDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Rating")
    @XmlElement(required = true)
    private Double rating;
    @Column(name = "ImageCover")
    @XmlElement(required = true)
    private String imageCover;
    @Column(name = "TrailerUrl")
    @XmlTransient
    private String trailerUrl;
    @Column(name = "Director")
    @XmlElement(required = true)
    private String director;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    @XmlTransient
    private Collection<Cast> castCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    @XmlTransient
    private Collection<UserRating> userRatingCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    @XmlTransient
    private Collection<MovieImages> movieImagesCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    @XmlTransient
    private Collection<MovieGenres> movieGenresCollection;
    
    @OneToMany(mappedBy = "movieId")
    @XmlTransient
    private Collection<Favourites> favouritesCollection;
    @OneToMany(mappedBy = "movieId")
    
    @XmlTransient
    private Collection<MovieUrl> movieUrlCollection;
    @XmlTransient
    private Collection<PersonType> personTypeCollection;

    public Collection<PersonType> getPersonTypeCollection() {
        return personTypeCollection;
    }

    public void setPersonTypeCollection(Collection<PersonType> personTypeCollection) {
        this.personTypeCollection = personTypeCollection;
    }

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

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

//    public Integer getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(Integer categoryId) {
//        this.categoryId = categoryId;
//    }
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
    public Collection<Cast> getCastCollection() {
        return castCollection;
    }

    public void setCastCollection(Collection<Cast> castCollection) {
        this.castCollection = castCollection;
    }

    @XmlTransient
    public Collection<UserRating> getUserRatingCollection() {
        return userRatingCollection;
    }

    public void setUserRatingCollection(Collection<UserRating> userRatingCollection) {
        this.userRatingCollection = userRatingCollection;
    }

    @XmlTransient
    public Collection<MovieImages> getMovieImagesCollection() {
        return movieImagesCollection;
    }

    public void setMovieImagesCollection(Collection<MovieImages> movieImagesCollection) {
        this.movieImagesCollection = movieImagesCollection;
    }

    @XmlTransient
    public Collection<MovieGenres> getMovieGenresCollection() {
        return movieGenresCollection;
    }

    public void setMovieGenresCollection(Collection<MovieGenres> movieGenresCollection) {
        this.movieGenresCollection = movieGenresCollection;
    }

    @XmlTransient
    public Collection<Favourites> getFavouritesCollection() {
        return favouritesCollection;
    }

    public void setFavouritesCollection(Collection<Favourites> favouritesCollection) {
        this.favouritesCollection = favouritesCollection;
    }

    @XmlTransient
    public Collection<MovieUrl> getMovieUrlCollection() {
        return movieUrlCollection;
    }

    public void setMovieUrlCollection(Collection<MovieUrl> movieUrlCollection) {
        this.movieUrlCollection = movieUrlCollection;
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
