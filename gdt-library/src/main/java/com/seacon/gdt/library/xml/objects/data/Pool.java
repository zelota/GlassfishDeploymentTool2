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
@XmlType(name = Constants.pool, namespace = "org.moooz.data")
public class Pool implements Serializable {
    public static final long serialVersionUID = 20150131225L;

    private String id;
    
    private String jndiName;
    private String datasourceclassname;
    private String restype;
    private List<Property> properties;

    public Pool() {
        this.id = "";

        this.jndiName = "";
        this.datasourceclassname = "";
        this.restype = "";
        this.properties = new ArrayList<Property>();
    }

    public Boolean isExists(String asadminPath, Target targetServer) throws Exception {
        Boolean retVal = false;
        
        com.seacon.gdt.library.runtime.pool.List listCmd = new com.seacon.gdt.library.runtime.pool.List(asadminPath, targetServer);
        listCmd.execute();
        
        for (int i = 0; i < listCmd.getOutputLines().size() && retVal == false; i++) {
            if (listCmd.getOutputLines().get(i).equals(this.jndiName)) {
                retVal = true;
            }
        }
        GdtLog.info("'" + this.jndiName + "' is exists: " + retVal);
        return retVal;
    }

    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public String getJndiName() {
        return jndiName;
    }

    @XmlElement
    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }

    public List<Property> getProperties() {
        return properties;
    }

    @XmlElementWrapper
    @XmlElement(name=Constants.property)
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public String getDatasourceclassname() {
        return datasourceclassname;
    }

    @XmlElement
    public void setDatasourceclassname(String datasourceclassname) {
        this.datasourceclassname = datasourceclassname;
    }

    public String getRestype() {
        return restype;
    }

    @XmlElement
    public void setRestype(String restype) {
        this.restype = restype;
    }
    
    
    
}
