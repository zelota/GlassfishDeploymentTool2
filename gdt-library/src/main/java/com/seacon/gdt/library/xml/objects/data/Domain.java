package com.seacon.gdt.library.xml.objects.data;

import com.seacon.gdt.library.utility.GdtLog;
import com.seacon.gdt.library.xml.Constants;
import com.seacon.gdt.library.xml.objects.servers.Target;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Peter
 */
@XmlType(name = Constants.domain, namespace = "org.moooz.data")
public class Domain implements Serializable {
    public static final long serialVersionUID = 20150131222L;
    
    private String id;
    
    private String name;
    private String adminport;
    private String instanceport;
    private String password;
    private String adminpassword;
    private String userpassword;
    private String masterpassword;
    
    public Domain() {
        this.id = "";
        
        this.name = "";
        this.adminport = "";
        this.instanceport = "";
        this.password = "";
        this.adminpassword = "";
        this.userpassword = "";
        this.masterpassword = "";
    }
    
    public Boolean isExists(String asadminPath, Target targetServer) throws Exception {
        Boolean retVal = false;
        
        com.seacon.gdt.library.runtime.domain.List listCmd = new com.seacon.gdt.library.runtime.domain.List(asadminPath, targetServer);
        listCmd.execute();
        
        for (int i = 0; i < listCmd.getOutputLines().size() && retVal == false; i++) {
            if (listCmd.getOutputLines().get(i).contains(this.name)) {
                retVal = true;
            }
        }
        GdtLog.info("'" + this.name + "' is exists: " + retVal);
        return retVal;
    }  
    
    public Boolean isRunning(String asadminPath, Target targetServer) throws Exception {
        Boolean retVal = true;
        
        com.seacon.gdt.library.runtime.domain.List listCmd = new com.seacon.gdt.library.runtime.domain.List(asadminPath, targetServer);
        listCmd.execute();
        
        for (int i = 0; i < listCmd.getOutputLines().size() && retVal == false; i++) {
            if (listCmd.getOutputLines().get(i).contains(this.name) && listCmd.getOutputLines().get(i).contains(" not running")) {
                retVal = false;
            }
        }
        GdtLog.info("'" + this.name + "' is running: " + retVal);
        return retVal;
    }  
    
    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getAdminport() {
        return adminport;
    }

    @XmlElement
    public void setAdminport(String adminport) {
        this.adminport = adminport;
    }

    public String getInstanceport() {
        return instanceport;
    }

    @XmlElement
    public void setInstanceport(String instanceport) {
        this.instanceport = instanceport;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
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
