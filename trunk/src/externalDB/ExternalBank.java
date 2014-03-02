/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package externalDB;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Panagis
 */
@Entity
@Table(name = "EXTERNAL_BANK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExternalBank.findAll", query = "SELECT e FROM ExternalBank e"),
    @NamedQuery(name = "ExternalBank.findByBankId", query = "SELECT e FROM ExternalBank e WHERE e.bankId = :bankId"),
    @NamedQuery(name = "ExternalBank.findByName", query = "SELECT e FROM ExternalBank e WHERE e.name = :name")})
public class ExternalBank implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BANK_ID")
    private Integer bankId;
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "bank")
    private Collection<CreditCardAuthority> creditCardAuthorityCollection;

    public ExternalBank() {
    }

    public ExternalBank(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        Integer oldBankId = this.bankId;
        this.bankId = bankId;
        changeSupport.firePropertyChange("bankId", oldBankId, bankId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    @XmlTransient
    public Collection<CreditCardAuthority> getCreditCardAuthorityCollection() {
        return creditCardAuthorityCollection;
    }

    public void setCreditCardAuthorityCollection(Collection<CreditCardAuthority> creditCardAuthorityCollection) {
        this.creditCardAuthorityCollection = creditCardAuthorityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bankId != null ? bankId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExternalBank)) {
            return false;
        }
        ExternalBank other = (ExternalBank) object;
        if ((this.bankId == null && other.bankId != null) || (this.bankId != null && !this.bankId.equals(other.bankId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "externalDB.ExternalBank[ bankId=" + bankId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
