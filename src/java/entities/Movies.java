/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
    @NamedQuery(name = "Movies.findAll", query = "SELECT m FROM Movies m")
    , @NamedQuery(name = "Movies.findById", query = "SELECT m FROM Movies m WHERE m.id = :id")
    , @NamedQuery(name = "Movies.findByName", query = "SELECT m FROM Movies m WHERE m.name = :name")
    , @NamedQuery(name = "Movies.findByAlternateName", query = "SELECT m FROM Movies m WHERE m.alternateName = :alternateName")
    , @NamedQuery(name = "Movies.findByDescription", query = "SELECT m FROM Movies m WHERE m.description = :description")
    , @NamedQuery(name = "Movies.findByGenreId", query = "SELECT m FROM Movies m WHERE m.genreId = :genreId")
    , @NamedQuery(name = "Movies.findByCountry", query = "SELECT m FROM Movies m WHERE m.country = :country")
    , @NamedQuery(name = "Movies.findByRuntime", query = "SELECT m FROM Movies m WHERE m.runtime = :runtime")
    , @NamedQuery(name = "Movies.findByCategoryId", query = "SELECT m FROM Movies m WHERE m.categoryId = :categoryId")
    , @NamedQuery(name = "Movies.findByLanguage", query = "SELECT m FROM Movies m WHERE m.language = :language")
    , @NamedQuery(name = "Movies.findByReleaseDate", query = "SELECT m FROM Movies m WHERE m.releaseDate = :releaseDate")
    , @NamedQuery(name = "Movies.findByRating", query = "SELECT m FROM Movies m WHERE m.rating = :rating")
    , @NamedQuery(name = "Movies.findByImageCover", query = "SELECT m FROM Movies m WHERE m.imageCover = :imageCover")
    , @NamedQuery(name = "Movies.findByTrailerUrl", query = "SELECT m FROM Movies m WHERE m.trailerUrl = :trailerUrl")})
public class Movies implements Serializable {

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
    @Column(name = "GenreId")
    private Integer genreId;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    private Collection<Cast> castCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    private Collection<UserRating> userRatingCollection;
    @OneToMany(mappedBy = "movieId")
    private Collection<Comment> commentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    private Collection<MovieImages> movieImagesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    private Collection<MovieGenres> movieGenresCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    private Collection<Directing> directingCollection;
    @OneToMany(mappedBy = "movieId")
    private Collection<MovieUrl> movieUrlCollection;

    public Movies() {
    }

    public Movies(Integer id) {
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

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
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
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
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
    public Collection<Directing> getDirectingCollection() {
        return directingCollection;
    }

    public void setDirectingCollection(Collection<Directing> directingCollection) {
        this.directingCollection = directingCollection;
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
        if (!(object instanceof Movies)) {
            return false;
        }
        Movies other = (Movies) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Movies[ id=" + id + " ]";
    }
    
}
