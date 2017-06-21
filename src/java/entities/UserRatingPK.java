/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author StormNs
 */
@Embeddable
public class UserRatingPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "MovieId")
    private int movieId;
    @Basic(optional = false)
    @Column(name = "AccountId")
    private int accountId;

    public UserRatingPK() {
    }

    public UserRatingPK(int movieId, int accountId) {
        this.movieId = movieId;
        this.accountId = accountId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) movieId;
        hash += (int) accountId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRatingPK)) {
            return false;
        }
        UserRatingPK other = (UserRatingPK) object;
        if (this.movieId != other.movieId) {
            return false;
        }
        if (this.accountId != other.accountId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserRatingPK[ movieId=" + movieId + ", accountId=" + accountId + " ]";
    }
    
}
