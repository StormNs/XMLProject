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
@Table(name = "Accounts")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountType", propOrder = {"username", "password", "email"})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountType.findAll", query = "SELECT a FROM AccountType a")
    , @NamedQuery(name = "AccountType.findById", query = "SELECT a FROM AccountType a WHERE a.id = :id")
    , @NamedQuery(name = "AccountType.findByUsername", query = "SELECT a FROM AccountType a WHERE a.username = :username")
    , @NamedQuery(name = "AccountType.findByPassword", query = "SELECT a FROM AccountType a WHERE a.password = :password")
    , @NamedQuery(name = "AccountType.findByEmail", query = "SELECT a FROM AccountType a WHERE a.email = :email")})
public class AccountType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for auto generated ID
    @Column(name = "Id")
    @XmlTransient
    private Integer id;

    @Basic(optional = false)
    @Column(name = "Username")
    @XmlElement(required = true)
    private String username;

    @Basic(optional = false)
    @Column(name = "Password")
    @XmlElement(required = true)
    private String password;

    @Basic(optional = false)
    @Column(name = "Email")
    @XmlElement(required = true)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    @XmlTransient
    private Collection<UserRating> userRatingCollection;

    @OneToMany(mappedBy = "accountId")
    @XmlTransient
    private Collection<Favourites> favouritesCollection;

    public AccountType() {
    }

    public AccountType(Integer id) {
        this.id = id;
    }

    public AccountType(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public AccountType(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<UserRating> getUserRatingCollection() {
        return userRatingCollection;
    }

    public void setUserRatingCollection(Collection<UserRating> userRatingCollection) {
        this.userRatingCollection = userRatingCollection;
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
        if (!(object instanceof AccountType)) {
            return false;
        }
        AccountType other = (AccountType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AccountType[ id=" + id + " ]";
    }

}
