package com.seacon.gdt.library.xml.objects.servers;

import com.seacon.gdt.library.xml.Constants;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Peter
 */
@XmlType(name = Constants.target)
public class Target implements Serializable {
    public static final long serialVersionUID = 2015013117L;
 
    private String host;
    private String port;
    private String user;
    private String password;
    private String adminpassword;
    private String userpassword;
    private String masterpassword;
    private String domainsrootdir;
    
    public Target() {
        this.host = "";
        this.port = "";
        this.user = "";
        this.password = "";
        this.adminpassword = "";
        this.userpassword = "";
        this.masterpassword = "";
        this.domainsrootdir = "";
    }

    public String getHost() {
        return host;
    }

    @XmlElement
    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    @XmlElement
    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    @XmlElement
    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    public String getDomainsrootdir() {
        return domainsrootdir;
    }

    @XmlElement
    public void setDomainsrootdir(String domainsrootdir) {
        this.domainsrootdir = domainsrootdir;
    }

    public String getAdminpassword() {
        return adminpassword;
    }

    @XmlElement
    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }

    public String getUserpassword() {
        return userpassword;
    }

    @XmlElement
    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getMasterpassword() {
        return masterpassword;
    }

    @XmlElement
    public void setMasterpassword(String masterpassword) {
        this.masterpassword = masterpassword;
    }
}
