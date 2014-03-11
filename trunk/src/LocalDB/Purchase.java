/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LocalDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Panagis
 */
@Entity
@Table(name = "PURCHASE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Purchase.findAll", query = "SELECT p FROM Purchase p"),
    @NamedQuery(name = "Purchase.findByPurchaseId", query = "SELECT p FROM Purchase p WHERE p.purchaseId = :purchaseId"),
    @NamedQuery(name = "Purchase.findByDatetime", query = "SELECT p FROM Purchase p WHERE p.datetime = :datetime"),
    @NamedQuery(name = "Purchase.findByAmount", query = "SELECT p FROM Purchase p WHERE p.amount = :amount"),
    @NamedQuery(name = "Purchase.findByPointsEarned", query = "SELECT p FROM Purchase p WHERE p.pointsEarned = :pointsEarned"),
    @NamedQuery(name = "Purchase.findByDelivery", query = "SELECT p FROM Purchase p WHERE p.delivery = :delivery")})
public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    //@Basic(optional = false)  έγινε comment καθώς θα χρησιμοποιηθεί ο generator παρακάτω
    @SequenceGenerator(name="pur_id", sequenceName="SQ_PURCHASE_ID", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pur_id")
    @Column(name = "PURCHASE_ID")
    private Integer purchaseId;
    @Basic(optional = false)
    @Column(name = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @Basic(optional = false)
    @Column(name = "AMOUNT")
    private float amount;
    @Basic(optional = false)
    @Column(name = "POINTS_EARNED")
    private int pointsEarned;
    @Basic(optional = false)
    @Column(name = "DELIVERY")
    private Boolean delivery;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseId")
    private Collection<ProductPurchase> productPurchaseCollection;
    @JoinColumn(name = "STORE", referencedColumnName = "STORE_ID")
    @ManyToOne(optional = false)
    private Store store;
    @JoinColumn(name = "CUSTOMER", referencedColumnName = "CUSTOMER_ID")
    @ManyToOne(optional = false)
    private Customer customer;

    public Purchase() {
        this.productPurchaseCollection = new ArrayList<>(0);
    }

    public Purchase(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Purchase(Integer purchaseId, Date datetime, float amount, int pointsEarned, Boolean delivery) {
        this.purchaseId = purchaseId;
        this.datetime = datetime;
        this.amount = amount;
        this.pointsEarned = pointsEarned;
        this.delivery = delivery;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    @XmlTransient
    public Collection<ProductPurchase> getProductPurchaseCollection() {
        return productPurchaseCollection;
    }

    public void setProductPurchaseCollection(Collection<ProductPurchase> productPurchaseCollection) {
        this.productPurchaseCollection = productPurchaseCollection;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchaseId != null ? purchaseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Purchase)) {
            return false;
        }
        Purchase other = (Purchase) object;
        if ((this.purchaseId == null && other.purchaseId != null) || (this.purchaseId != null && !this.purchaseId.equals(other.purchaseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LocalDB.Purchase[ purchaseId=" + purchaseId + " ]";
    }
    
}
