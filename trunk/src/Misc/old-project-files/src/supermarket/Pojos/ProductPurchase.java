/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supermarket.Pojos;

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
 * @author Euh
 */
@Entity
@Table(name = "PRODUCT_PURCHASE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductPurchase.findAll", query = "SELECT p FROM ProductPurchase p"),
    @NamedQuery(name = "ProductPurchase.findByProductPurchaseId", query = "SELECT p FROM ProductPurchase p WHERE p.productPurchaseId = :productPurchaseId"),
    @NamedQuery(name = "ProductPurchase.findByQuantity", query = "SELECT p FROM ProductPurchase p WHERE p.quantity = :quantity")})
public class ProductPurchase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PRODUCT_PURCHASE_ID")
    private Integer productPurchaseId;
    @Basic(optional = false)
    @Column(name = "QUANTITY")
    private int quantity;
    @JoinColumn(name = "PURCHASE", referencedColumnName = "PURCHASE_ID")
    @ManyToOne(optional = false)
    private Purchase purchase;
    @JoinColumn(name = "PRODUCT", referencedColumnName = "PRODUCT_ID")
    @ManyToOne(optional = false)
    private Product product;

    public ProductPurchase() {
    }

    public ProductPurchase(Integer productPurchaseId) {
        this.productPurchaseId = productPurchaseId;
    }

    public ProductPurchase(Integer productPurchaseId, int quantity) {
        this.productPurchaseId = productPurchaseId;
        this.quantity = quantity;
    }

    public Integer getProductPurchaseId() {
        return productPurchaseId;
    }

    public void setProductPurchaseId(Integer productPurchaseId) {
        this.productPurchaseId = productPurchaseId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productPurchaseId != null ? productPurchaseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductPurchase)) {
            return false;
        }
        ProductPurchase other = (ProductPurchase) object;
        if ((this.productPurchaseId == null && other.productPurchaseId != null) || (this.productPurchaseId != null && !this.productPurchaseId.equals(other.productPurchaseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "supermarket.Pojos.ProductPurchase[ productPurchaseId=" + productPurchaseId + " ]";
    }
    
}
