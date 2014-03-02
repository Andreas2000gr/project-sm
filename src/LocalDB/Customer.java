/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LocalDB;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Panagis
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
    @NamedQuery(name = "Customer.findByCreditCardId", query = "SELECT c FROM Customer c WHERE c.creditCardId = :creditCardId"),
    @NamedQuery(name = "Customer.findByAvailablePoints", query = "SELECT c FROM Customer c WHERE c.availablePoints = :availablePoints"),
    @NamedQuery(name = "Customer.findByPassword", query = "SELECT c FROM Customer c WHERE c.password = :password"),
    // Παναγής , προσθήκη Named query  για αναζήτηση πελάτη με password και κάρτα πόντων.
    @NamedQuery(name = "Customer.findByIdPassword", query = "SELECT c FROM Customer c WHERE c.password = :password and c.pointsCardNumber = :cardno")})
    
public class Customer implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    //@Basic(optional = false)  έγινε comment καθώς θα χρησιμοποιηθεί ο generator παρακάτω
    @SequenceGenerator(name="cust_id", sequenceName="SQ_CUSTOMER_ID", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cust_id")
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
    @Column(name = "CREDIT_CARD_ID")
    private Integer creditCardId;
    @Basic(optional = false)
    @Column(name = "AVAILABLE_POINTS")
    private int availablePoints;
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Collection<Purchase> purchaseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Collection<Voucher> voucherCollection;

    public Customer() {
    }

    public Customer(Integer customerId) {
        this.customerId = customerId;
    }

    public Customer(Integer customerId, String firstName, String lastName, String pointsCardNumber, int availablePoints) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pointsCardNumber = pointsCardNumber;
        this.availablePoints = availablePoints;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        Integer oldCustomerId = this.customerId;
        this.customerId = customerId;
        changeSupport.firePropertyChange("customerId", oldCustomerId, customerId);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        String oldFirstName = this.firstName;
        this.firstName = firstName;
        changeSupport.firePropertyChange("firstName", oldFirstName, firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        String oldLastName = this.lastName;
        this.lastName = lastName;
        changeSupport.firePropertyChange("lastName", oldLastName, lastName);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        String oldAddress = this.address;
        this.address = address;
        changeSupport.firePropertyChange("address", oldAddress, address);
    }

    public String getPointsCardNumber() {
        return pointsCardNumber;
    }

    public void setPointsCardNumber(String pointsCardNumber) {
        String oldPointsCardNumber = this.pointsCardNumber;
        this.pointsCardNumber = pointsCardNumber;
        changeSupport.firePropertyChange("pointsCardNumber", oldPointsCardNumber, pointsCardNumber);
    }

    public Integer getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Integer creditCardId) {
        Integer oldCreditCardId = this.creditCardId;
        this.creditCardId = creditCardId;
        changeSupport.firePropertyChange("creditCardId", oldCreditCardId, creditCardId);
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(int availablePoints) {
        int oldAvailablePoints = this.availablePoints;
        this.availablePoints = availablePoints;
        changeSupport.firePropertyChange("availablePoints", oldAvailablePoints, availablePoints);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String oldPassword = this.password;
        this.password = password;
        changeSupport.firePropertyChange("password", oldPassword, password);
    }

    @XmlTransient
    public Collection<Purchase> getPurchaseCollection() {
        return purchaseCollection;
    }

    public void setPurchaseCollection(Collection<Purchase> purchaseCollection) {
        this.purchaseCollection = purchaseCollection;
    }

    @XmlTransient
    public Collection<Voucher> getVoucherCollection() {
        return voucherCollection;
    }

    public void setVoucherCollection(Collection<Voucher> voucherCollection) {
        this.voucherCollection = voucherCollection;
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
        return "LocalDB.Customer[ customerId=" + customerId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
