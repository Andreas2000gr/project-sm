/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package externalDB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "CREDIT_CARD_AUTHORITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CreditCardAuthority.findAll", query = "SELECT c FROM CreditCardAuthority c"),
    @NamedQuery(name = "CreditCardAuthority.findByPkCardId", query = "SELECT c FROM CreditCardAuthority c WHERE c.pkCardId = :pkCardId"),
    @NamedQuery(name = "CreditCardAuthority.findByCvv", query = "SELECT c FROM CreditCardAuthority c WHERE c.cvv = :cvv"),
    @NamedQuery(name = "CreditCardAuthority.findByNumber", query = "SELECT c FROM CreditCardAuthority c WHERE c.number = :number"),
    @NamedQuery(name = "CreditCardAuthority.findByOwnerName", query = "SELECT c FROM CreditCardAuthority c WHERE c.ownerName = :ownerName")})
public class CreditCardAuthority implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_CARD_ID")
    private Integer pkCardId;
    @Column(name = "CVV")
    private String cvv;
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "OWNER_NAME")
    private String ownerName;
    @JoinColumn(name = "BANK", referencedColumnName = "BANK_ID")
    @ManyToOne
    private ExternalBank bank;

    public CreditCardAuthority() {
    }

    public CreditCardAuthority(Integer pkCardId) {
        this.pkCardId = pkCardId;
    }

    public Integer getPkCardId() {
        return pkCardId;
    }

    public void setPkCardId(Integer pkCardId) {
        this.pkCardId = pkCardId;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public ExternalBank getBank() {
        return bank;
    }

    public void setBank(ExternalBank bank) {
        this.bank = bank;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCardId != null ? pkCardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditCardAuthority)) {
            return false;
        }
        CreditCardAuthority other = (CreditCardAuthority) object;
        if ((this.pkCardId == null && other.pkCardId != null) || (this.pkCardId != null && !this.pkCardId.equals(other.pkCardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "externalDB.CreditCardAuthority[ pkCardId=" + pkCardId + " ]";
    }
    
}
