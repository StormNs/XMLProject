/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author StormNs
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder = "account")
@XmlRootElement(name="accounts")
public class Accounts {
    
    @XmlElement(required = true)
    protected List<AccountType> account;

    public List<AccountType> getAccounts() {
        if(account == null){
            account = new ArrayList<>();
        }
        return account;
    }

    public void setAccounts(List<AccountType> account) {
        this.account = account;
    }

    
}
