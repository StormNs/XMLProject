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
@Table(name = "Directing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Directing.findAll", query = "SELECT d FROM Directing d")
    , @NamedQuery(name = "Directing.findById", query = "SELECT d FROM Directing d WHERE d.id = :id")})
public class Directing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @JoinColumn(name = "MovieId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Movies movieId;
    @JoinColumn(name = "DirectorId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Persons directorId;

    public Directing() {
    }

    public Directing(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movies getMovieId() {
        return movieId;
    }

    public void setMovieId(Movies movieId) {
        this.movieId = movieId;
    }

    public Persons getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Persons directorId) {
        this.directorId = directorId;
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
        if (!(object instanceof Directing)) {
            return false;
        }
        Directing other = (Directing) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Directing[ id=" + id + " ]";
    }
    
}
