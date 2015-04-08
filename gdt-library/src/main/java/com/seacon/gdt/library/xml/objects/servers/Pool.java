package com.seacon.gdt.library.xml.objects.servers;

import com.seacon.gdt.library.utility.Utility;
import com.seacon.gdt.library.xml.Constants;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Peter
 */
@XmlType(name = Constants.pool, namespace = "org.moooz.servers")
public class Pool implements Serializable {
    public static final long serialVersionUID = 2015013115L;

    private String id;
    private String skip;
    private String action;
    
    public Pool() {
        this.id = "";
        this.skip = "";
        this.action = "";
    }
    
    public Boolean isSkip() {
        return Utility.convertStrToBoolean(this.skip);
    }
    
    public Boolean isCreate() {
        return (this.action != null && "create".equals(this.action.toLowerCase()));
    }

    public Boolean isRecreate() {
        return (this.action != null && "recreate".equals(this.action.toLowerCase()));
    }

    public Boolean isDrop() {
        return (this.action != null && "drop".equals(this.action.toLowerCase()));
    }
    
    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public String getSkip() {
        return skip;
    }

    @XmlAttribute
    public void setSkip(String skip) {
        this.skip = skip;
    }

    public String getAction() {
        return action;
    }

    @XmlAttribute
    public void setAction(String action) {
        this.action = action;
    }

}
