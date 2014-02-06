/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supermarket.Pojos;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Euh
 */
@Entity
@Table(name = "CUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByCustomerId", query = "SELECT c FROM Customer c WHERE c.customerId = :customerId"),
    @NamedQuery(name = "Customer.findByFirstName", query = "SELECT c FROM Customer c WHERE c.firstName = :firstName"),
    @NamedQuery(name = "Customer.findByLastName", query = "SELECT c FROM Customer c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "Customer.findByAddress", query = "SELECT c FROM Customer c WHERE c.address = :address"),
    @NamedQuery(name = "Customer.findByPointsCardNumber", query = "SELECT c FROM Customer c WHERE c.pointsCardNumber = :pointsCardNumber"),
    @NamedQuery(name = "Customer.findByAvailablePoints", query = "SELECT c FROM Customer c WHERE c.availablePoints = :availablePoints"),
    @NamedQuery(name = "Customer.findByNoofchecks", query = "SELECT c FROM Customer c WHERE c.noofchecks = :noofchecks"),
    @NamedQuery(name = "Customer.findByPassword", query = "SELECT c FROM Customer c WHERE c.password = :password")})
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;
    @Basic(optional = false)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "ADDRESS")
    private String address;
    @Basic(optional = false)
    @Column(name = "POINTS_CARD_NUMBER")
    private String pointsCardNumber;
    @Basic(optional = false)
    @Column(name = "AVAILABLE_POINTS")
    private int availablePoints;
    @Column(name = "NOOFCHECKS")
    private Integer noofchecks;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @JoinColumn(name = "CARD_ID", referencedColumnName = "CARD_ID")
    @ManyToOne(optional = false)
    private CreditCard cardId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<CheckCard> checkCardCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Collection<Purchase> purchaseCollection;

    public Customer() {
    }

    public Customer(Integer customerId) {
        this.customerId = customerId;
    }

    public Customer(Integer customerId, String firstName, String lastName, String pointsCardNumber, int availablePoints, String password) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pointsCardNumber = pointsCardNumber;
        this.availablePoints = availablePoints;
        this.password = password;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPointsCardNumber() {
        return pointsCardNumber;
    }

    public void setPointsCardNumber(String pointsCardNumber) {
        this.pointsCardNumber = pointsCardNumber;
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(int availablePoints) {
        this.availablePoints = availablePoints;
    }

    public Integer getNoofchecks() {
        return noofchecks;
    }

    public void setNoofchecks(Integer noofchecks) {
        this.noofchecks = noofchecks;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CreditCard getCardId() {
        return cardId;
    }

    public void setCardId(CreditCard cardId) {
        this.cardId = cardId;
    }

    @XmlTransient
    public Collection<CheckCard> getCheckCardCollection() {
        return checkCardCollection;
    }

    public void setCheckCardCollection(Collection<CheckCard> checkCardCollection) {
        this.checkCardCollection = checkCardCollection;
    }

    @XmlTransient
    public Collection<Purchase> getPurchaseCollection() {
        return purchaseCollection;
    }

    public void setPurchaseCollection(Collection<Purchase> purchaseCollection) {
        this.purchaseCollection = purchaseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "supermarket.Pojos.Customer[ customerId=" + customerId + " ]";
    }
    
}
