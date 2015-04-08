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
@XmlType(name = Constants.jdbcresource, namespace = "org.moooz.data")
public class Jdbcresource implements Serializable {
    public static final long serialVersionUID = 20150131224L;
    
    private String id;
    
    private String name;
    private String poolid;

    public Jdbcresource() {
        this.id = "";

        this.name = "";
        this.poolid = "";
    }

    public Boolean isExists(String asadminPath, Target targetServer) throws Exception {
        Boolean retVal = false;
        
        com.seacon.gdt.library.runtime.jdbcresource.List listCmd = new com.seacon.gdt.library.runtime.jdbcresource.List(asadminPath, targetServer);
        listCmd.execute();
        
        for (int i = 0; i < listCmd.getOutputLines().size() && retVal == false; i++) {
            if (listCmd.getOutputLines().get(i).equals(this.name)) {
                retVal = true;
            }
        }
        GdtLog.info("'" + this.name + "' is exists: " + retVal);
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

    public String getPoolid() {
        return poolid;
    }

    @XmlElement
    public void setPoolid(String poolid) {
        this.poolid = poolid;
    }
    
    
    
}
