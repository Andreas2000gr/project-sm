/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LocalDB;

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
@Table(name = "PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :productId"),
    @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name = "Product.findByCode", query = "SELECT p FROM Product p WHERE p.code = :code"),
    @NamedQuery(name = "Product.findByPoints", query = "SELECT p FROM Product p WHERE p.points = :points"),
    @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price")})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    //@Basic(optional = false)  έγινε comment καθώς θα χρησιμοποιηθεί ο generator παρακάτω
    @SequenceGenerator(name="prod_id", sequenceName="SQ_PRODUCT_ID", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="prod_id")
    @Column(name = "PRODUCT_ID")
    private Integer productId;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "CODE")
    private String code;
    @Basic(optional = false)
    @Column(name = "POINTS")
    private int points;
    @Basic(optional = false)
    @Column(name = "PRICE")
    private float price;
    @JoinTable(name = "STORE_PRODUCT", joinColumns = {
        @JoinColumn(name = "PRODUCT", referencedColumnName = "PRODUCT_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "STORE", referencedColumnName = "STORE_ID")})
    @ManyToMany
    private Collection<Store> storeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<ProductPurchase> productPurchaseCollection;

    public Product() {
    }

    public Product(Integer productId) {
        this.productId = productId;
    }

    public Product(Integer productId, String name, String code, int points, float price) {
        this.productId = productId;
        this.name = name;
        this.code = code;
        this.points = points;
        this.price = price;
    }

    /* @EPA:: constructor */
    public Product( String name, String code, int points, float price) {
        this.name = name;
        this.code = code;
        this.points = points;
        this.price = price;
    }
    
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @XmlTransient
    public Collection<Store> getStoreCollection() {
        return storeCollection;
    }

    public void setStoreCollection(Collection<Store> storeCollection) {
        this.storeCollection = storeCollection;
    }

    @XmlTransient
    public Collection<ProductPurchase> getProductPurchaseCollection() {
        return productPurchaseCollection;
    }

    public void setProductPurchaseCollection(Collection<ProductPurchase> productPurchaseCollection) {
        this.productPurchaseCollection = productPurchaseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LocalDB.Product[ productId=" + productId + " ]";
    }
    
}
