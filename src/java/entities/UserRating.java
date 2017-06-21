/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "UserRating")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserRating.findAll", query = "SELECT u FROM UserRating u")
    , @NamedQuery(name = "UserRating.findByMovieId", query = "SELECT u FROM UserRating u WHERE u.userRatingPK.movieId = :movieId")
    , @NamedQuery(name = "UserRating.findByAccountId", query = "SELECT u FROM UserRating u WHERE u.userRatingPK.accountId = :accountId")
    , @NamedQuery(name = "UserRating.findByRating", query = "SELECT u FROM UserRating u WHERE u.rating = :rating")})
public class UserRating implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserRatingPK userRatingPK;
    @Basic(optional = false)
    @Column(name = "Rating")
    private double rating;
    @JoinColumn(name = "AccountId", referencedColumnName = "Id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Accounts accounts;
    @JoinColumn(name = "MovieId", referencedColumnName = "Id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Movies movies;

    public UserRating() {
    }

    public UserRating(UserRatingPK userRatingPK) {
        this.userRatingPK = userRatingPK;
    }

    public UserRating(UserRatingPK userRatingPK, double rating) {
        this.userRatingPK = userRatingPK;
        this.rating = rating;
    }

    public UserRating(int movieId, int accountId) {
        this.userRatingPK = new UserRatingPK(movieId, accountId);
    }

    public UserRatingPK getUserRatingPK() {
        return userRatingPK;
    }

    public void setUserRatingPK(UserRatingPK userRatingPK) {
        this.userRatingPK = userRatingPK;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userRatingPK != null ? userRatingPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRating)) {
            return false;
        }
        UserRating other = (UserRating) object;
        if ((this.userRatingPK == null && other.userRatingPK != null) || (this.userRatingPK != null && !this.userRatingPK.equals(other.userRatingPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserRating[ userRatingPK=" + userRatingPK + " ]";
    }
    
}
