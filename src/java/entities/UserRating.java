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
@Table(name = "UserRating")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserRating.findAll", query = "SELECT u FROM UserRating u")
    , @NamedQuery(name = "UserRating.findById", query = "SELECT u FROM UserRating u WHERE u.id = :id")
    , @NamedQuery(name = "UserRating.findByRating", query = "SELECT u FROM UserRating u WHERE u.rating = :rating")})
public class UserRating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Rating")
    private double rating;
    @JoinColumn(name = "AccountId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Accounts accountId;
    @JoinColumn(name = "MovieId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Movies movieId;

    public UserRating() {
    }

    public UserRating(Integer id) {
        this.id = id;
    }

    public UserRating(Integer id, double rating) {
        this.id = id;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Accounts getAccountId() {
        return accountId;
    }

    public void setAccountId(Accounts accountId) {
        this.accountId = accountId;
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
        if (!(object instanceof UserRating)) {
            return false;
        }
        UserRating other = (UserRating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserRating[ id=" + id + " ]";
    }
    
}
