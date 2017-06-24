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
@Table(name = "Persons")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonType.findAll", query = "SELECT p FROM PersonType p")
    , @NamedQuery(name = "PersonType.findById", query = "SELECT p FROM PersonType p WHERE p.id = :id")
    , @NamedQuery(name = "PersonType.findByName", query = "SELECT p FROM PersonType p WHERE p.name = :name")
    , @NamedQuery(name = "PersonType.findByAlternateName", query = "SELECT p FROM PersonType p WHERE p.alternateName = :alternateName")
    , @NamedQuery(name = "PersonType.findByHeight", query = "SELECT p FROM PersonType p WHERE p.height = :height")
    , @NamedQuery(name = "PersonType.findByBirthDate", query = "SELECT p FROM PersonType p WHERE p.birthDate = :birthDate")
    , @NamedQuery(name = "PersonType.findByBirthPlace", query = "SELECT p FROM PersonType p WHERE p.birthPlace = :birthPlace")
    , @NamedQuery(name = "PersonType.findByBiography", query = "SELECT p FROM PersonType p WHERE p.biography = :biography")
    , @NamedQuery(name = "PersonType.findByIsDirector", query = "SELECT p FROM PersonType p WHERE p.isDirector = :isDirector")
    , @NamedQuery(name = "PersonType.findByIsActor", query = "SELECT p FROM PersonType p WHERE p.isActor = :isActor")
    , @NamedQuery(name = "PersonType.findByImageUrl", query = "SELECT p FROM PersonType p WHERE p.imageUrl = :imageUrl")})
public class PersonType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "AlternateName")
    private String alternateName;
    @Column(name = "Height")
    private String height;
    @Column(name = "BirthDate")
    private String birthDate;
    @Column(name = "BirthPlace")
    private String birthPlace;
    @Column(name = "Biography")
    private String biography;
    @Basic(optional = false)
    @Column(name = "IsDirector")
    private boolean isDirector;
    @Column(name = "IsActor")
    private Boolean isActor;
    @Column(name = "ImageUrl")
    private String imageUrl;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actorId")
    private Collection<Cast> castCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "directorId")
    private Collection<Directing> directingCollection;

    public PersonType() {
    }

    public PersonType(Integer id) {
        this.id = id;
    }

    public PersonType(Integer id, boolean isDirector) {
        this.id = id;
        this.isDirector = isDirector;
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public boolean getIsDirector() {
        return isDirector;
    }

    public void setIsDirector(boolean isDirector) {
        this.isDirector = isDirector;
    }

    public Boolean getIsActor() {
        return isActor;
    }

    public void setIsActor(Boolean isActor) {
        this.isActor = isActor;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @XmlTransient
    public Collection<Cast> getCastCollection() {
        return castCollection;
    }

    public void setCastCollection(Collection<Cast> castCollection) {
        this.castCollection = castCollection;
    }

    @XmlTransient
    public Collection<Directing> getDirectingCollection() {
        return directingCollection;
    }

    public void setDirectingCollection(Collection<Directing> directingCollection) {
        this.directingCollection = directingCollection;
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
        if (!(object instanceof PersonType)) {
            return false;
        }
        PersonType other = (PersonType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PersonType[ id=" + id + " ]";
    }
    
}
