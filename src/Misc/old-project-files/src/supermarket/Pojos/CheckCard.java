/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supermarket.Pojos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Euh
 */
@Entity
@Table(name = "CHECK_CARD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CheckCard.findAll", query = "SELECT c FROM CheckCard c"),
    @NamedQuery(name = "CheckCard.findByCheckId", query = "SELECT c FROM CheckCard c WHERE c.checkId = :checkId"),
    @NamedQuery(name = "CheckCard.findByCheckCode", query = "SELECT c FROM CheckCard c WHERE c.checkCode = :checkCode"),
    @NamedQuery(name = "CheckCard.findByActive", query = "SELECT c FROM CheckCard c WHERE c.active = :active"),
    @NamedQuery(name = "CheckCard.findByCreateDate", query = "SELECT c FROM CheckCard c WHERE c.createDate = :createDate")})
public class CheckCard implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CHECK_ID")
    private Integer checkId;
    @Basic(optional = false)
    @Column(name = "CHECK_CODE")
    private String checkCode;
    @Basic(optional = false)
    @Column(name = "ACTIVE")
    private Serializable active;
    @Basic(optional = false)
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
    @ManyToOne(optional = false)
    private Customer customerId;

    public CheckCard() {
    }

    public CheckCard(Integer checkId) {
        this.checkId = checkId;
    }

    public CheckCard(Integer checkId, String checkCode, Serializable active, Date createDate) {
        this.checkId = checkId;
        this.checkCode = checkCode;
        this.active = active;
        this.createDate = createDate;
    }

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        Integer oldCheckId = this.checkId;
        this.checkId = checkId;
        changeSupport.firePropertyChange("checkId", oldCheckId, checkId);
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        String oldCheckCode = this.checkCode;
        this.checkCode = checkCode;
        changeSupport.firePropertyChange("checkCode", oldCheckCode, checkCode);
    }

    public Serializable getActive() {
        return active;
    }

    public void setActive(Serializable active) {
        Serializable oldActive = this.active;
        this.active = active;
        changeSupport.firePropertyChange("active", oldActive, active);
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        Date oldCreateDate = this.createDate;
        this.createDate = createDate;
        changeSupport.firePropertyChange("createDate", oldCreateDate, createDate);
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        Customer oldCustomerId = this.customerId;
        this.customerId = customerId;
        changeSupport.firePropertyChange("customerId", oldCustomerId, customerId);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (checkId != null ? checkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CheckCard)) {
            return false;
        }
        CheckCard other = (CheckCard) object;
        if ((this.checkId == null && other.checkId != null) || (this.checkId != null && !this.checkId.equals(other.checkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "supermarket.Pojos.CheckCard[ checkId=" + checkId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
