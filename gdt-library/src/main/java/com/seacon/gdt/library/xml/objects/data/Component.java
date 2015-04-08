package com.seacon.gdt.library.xml.objects.data;

import com.seacon.gdt.library.utility.GdtLog;
import com.seacon.gdt.library.xml.Constants;
import com.seacon.gdt.library.xml.objects.servers.Target;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Peter
 */
@XmlType(name = Constants.component, namespace = "org.moooz.data")
public class Component implements Serializable {
    public static final long serialVersionUID = 20150131223L;
    
    private String id;
    private String ctype;
    private String scappid;
    
    private String name;
    private String type;
    private String contextroot;
    private String path;
    private List<Property> properties;

    public Component() {
        this.id = "";
        this.ctype = "";
        this.scappid = "";

        this.name = "";
        this.type = "";
        this.contextroot = "";
        this.path = "";
        this.properties = new ArrayList<Property>();
    }
    
    public Boolean isExists(String asadminPath, Target targetServer, com.seacon.gdt.library.xml.objects.data.Component parentAppData, com.seacon.gdt.library.xml.objects.data.Domain domainData) throws Exception {
        Boolean retVal = false;
        
        com.seacon.gdt.library.runtime.component.List listCmd = new com.seacon.gdt.library.runtime.component.List(asadminPath, targetServer);
        listCmd.setParameters(this, parentAppData, domainData);
        listCmd.execute();
        
        for (int i = 0; i < listCmd.getOutputLines().size() && retVal == false; i++) {
            if (listCmd.getOutputLines().get(i).contains(this.name + " ")) {
                retVal = true;
            }
        }
        GdtLog.info("'" + this.name + "' is exists: " + retVal);
        return retVal;
    }  
    
    
    public Boolean isTypeApplication() {
        return (this.ctype != null && "application".equals(this.ctype.toLowerCase()));
    }    
    
    public Boolean isTypeComponent() {
        return (this.ctype != null && "component".equals(this.ctype.toLowerCase()));
    }    

    public Boolean isTypeSubcomponent() {
        return (this.ctype != null && "subcomponent".equals(this.ctype.toLowerCase()));
    }    
    
    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public String getCtype() {
        return ctype;
    }

    @XmlAttribute
    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getScappid() {
        return scappid;
    }
    
    @XmlAttribute
    public void setScappid(String scappid) {
        this.scappid = scappid;
    }

    public String getType() {
        return type;
    }

    @XmlElement
    public void setType(String type) {
        this.type = type;
    }
    
    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getContextroot() {
        return contextroot;
    }

    @XmlElement
    public void setContextroot(String contextroot) {
        this.contextroot = contextroot;
    }

    public String getPath() {
        return path;
    }

    @XmlElement
    public void setPath(String path) {
        this.path = path;
    }

    public List<Property> getProperties() {
        return properties;
    }

    @XmlElementWrapper
    @XmlElement(name=Constants.property)
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

}
