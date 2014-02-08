/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LocalDB;

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
 * @author Orgasmatron
 */
@Entity
@Table(name = "VOUCHER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voucher.findAll", query = "SELECT v FROM Voucher v"),
    @NamedQuery(name = "Voucher.findByVoucherId", query = "SELECT v FROM Voucher v WHERE v.voucherId = :voucherId"),
    @NamedQuery(name = "Voucher.findByVoucherStatus", query = "SELECT v FROM Voucher v WHERE v.voucherStatus = :voucherStatus")})
public class Voucher implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "VOUCHER_ID")
    private Integer voucherId;
    @Basic(optional = false)
    @Column(name = "VOUCHER_STATUS")
    private String voucherStatus;
    @JoinColumn(name = "CUSTOMER", referencedColumnName = "CUSTOMER_ID")
    @ManyToOne(optional = false)
    private Customer customer;

    public Voucher() {
    }

    public Voucher(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public Voucher(Integer voucherId, String voucherStatus) {
        this.voucherId = voucherId;
        this.voucherStatus = voucherStatus;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherStatus() {
        return voucherStatus;
    }

    public void setVoucherStatus(String voucherStatus) {
        this.voucherStatus = voucherStatus;
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
        hash += (voucherId != null ? voucherId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voucher)) {
            return false;
        }
        Voucher other = (Voucher) object;
        if ((this.voucherId == null && other.voucherId != null) || (this.voucherId != null && !this.voucherId.equals(other.voucherId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LocalDB.Voucher[ voucherId=" + voucherId + " ]";
    }
    
}
