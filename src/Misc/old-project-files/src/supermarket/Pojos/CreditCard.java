/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supermarket.Pojos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Euh
 */
@Entity
@Table(name = "CREDIT_CARD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CreditCard.findAll", query = "SELECT c FROM CreditCard c"),
    @NamedQuery(name = "CreditCard.findByCardId", query = "SELECT c FROM CreditCard c WHERE c.cardId = :cardId"),
    @NamedQuery(name = "CreditCard.findByCardNumber", query = "SELECT c FROM CreditCard c WHERE c.cardNumber = :cardNumber"),
    @NamedQuery(name = "CreditCard.findByOwnerName", query = "SELECT c FROM CreditCard c WHERE c.ownerName = :ownerName"),
    @NamedQuery(name = "CreditCard.findByCvv", query = "SELECT c FROM CreditCard c WHERE c.cvv = :cvv")})
public class CreditCard implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CARD_ID")
    private Integer cardId;
    @Basic(optional = false)
    @Column(name = "CARD_NUMBER")
    private int cardNumber;
    @Basic(optional = false)
    @Column(name = "OWNER_NAME")
    private String ownerName;
    @Basic(optional = false)
    @Column(name = "CVV")
    private int cvv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cardId")
    private Collection<Customer> customerCollection;
    @JoinColumn(name = "BANK_ID", referencedColumnName = "BANK_ID")
    @ManyToOne(optional = false)
    private Bank bankId;

    public CreditCard() {
    }

    public CreditCard(Integer cardId) {
        this.cardId = cardId;
    }

    public CreditCard(Integer cardId, int cardNumber, String ownerName, int cvv) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.ownerName = ownerName;
        this.cvv = cvv;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        Integer oldCardId = this.cardId;
        this.cardId = cardId;
        changeSupport.firePropertyChange("cardId", oldCardId, cardId);
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        int oldCardNumber = this.cardNumber;
        this.cardNumber = cardNumber;
        changeSupport.firePropertyChange("cardNumber", oldCardNumber, cardNumber);
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        String oldOwnerName = this.ownerName;
        this.ownerName = ownerName;
        changeSupport.firePropertyChange("ownerName", oldOwnerName, ownerName);
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        int oldCvv = this.cvv;
        this.cvv = cvv;
        changeSupport.firePropertyChange("cvv", oldCvv, cvv);
    }

    @XmlTransient
    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
    }

    public Bank getBankId() {
        return bankId;
    }

    public void setBankId(Bank bankId) {
        Bank oldBankId = this.bankId;
        this.bankId = bankId;
        changeSupport.firePropertyChange("bankId", oldBankId, bankId);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cardId != null ? cardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditCard)) {
            return false;
        }
        CreditCard other = (CreditCard) object;
        if ((this.cardId == null && other.cardId != null) || (this.cardId != null && !this.cardId.equals(other.cardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "supermarket.Pojos.CreditCard[ cardId=" + cardId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
