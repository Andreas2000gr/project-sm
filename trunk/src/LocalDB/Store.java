/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LocalDB;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Panagis
 */
@Entity
@Table(name = "STORE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s"),
    @NamedQuery(name = "Store.findByStoreId", query = "SELECT s FROM Store s WHERE s.storeId = :storeId"),
    @NamedQuery(name = "Store.findByName", query = "SELECT s FROM Store s WHERE s.name = :name"),
    @NamedQuery(name = "Store.findByAddress", query = "SELECT s FROM Store s WHERE s.address = :address")})
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@Basic(optional = false)  έγινε comment καθώς θα χρησιμοποιηθεί ο generator παρακάτω
    @SequenceGenerator(name = "sto_id", sequenceName = "SQ_STORE_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sto_id")
    @Column(name = "STORE_ID")
    private Integer storeId;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "ADDRESS")
    private String address;
    @ManyToMany(mappedBy = "storeCollection")
    private Collection<Product> productCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
    private Collection<Purchase> purchaseCollection;
    
    public Store() {
    }

    public Store(Integer storeId) {
        this.storeId = storeId;
    }

    public Store(Integer storeId, String name, String address) {
        this.storeId = storeId;
        this.name = name;
        this.address = address;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
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
        hash += (storeId != null ? storeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Store)) {
            return false;
        }
        Store other = (Store) object;
        if ((this.storeId == null && other.storeId != null) || (this.storeId != null && !this.storeId.equals(other.storeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LocalDB.Store[ storeId=" + storeId + " ]";
    }

}
