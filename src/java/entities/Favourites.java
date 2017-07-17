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
@Table(name = "Favourites")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Favourites.findAll", query = "SELECT f FROM Favourites f")
    , @NamedQuery(name = "Favourites.findById", query = "SELECT f FROM Favourites f WHERE f.id = :id")})
public class Favourites implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "AccountId", referencedColumnName = "Id")
    @ManyToOne
    private AccountType accountId;
    @JoinColumn(name = "MovieId", referencedColumnName = "Id")
    @ManyToOne
    private MovieType movieId;

    public Favourites() {
    }

    public Favourites(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountType getAccountId() {
        return accountId;
    }

    public void setAccountId(AccountType accountId) {
        this.accountId = accountId;
    }

    public MovieType getMovie() {
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
        if (!(object instanceof Favourites)) {
            return false;
        }
        Favourites other = (Favourites) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Favourites[ id=" + id + " ]";
    }
    
}
