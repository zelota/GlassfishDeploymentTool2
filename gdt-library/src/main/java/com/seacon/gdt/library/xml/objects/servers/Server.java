package com.seacon.gdt.library.xml.objects.servers;

import com.seacon.gdt.library.utility.Utility;
import com.seacon.gdt.library.xml.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author varsanyi.peter
 */
@XmlType(name = Constants.server)
public class Server implements Serializable {

    public static final long serialVersionUID = 201501301116L;
    
    private Target target;
    private List<Command> commands;
    
    private String id;
    private String skip;
    private String index;
    
    public Server() {
        this.id = "";
        this.skip = "";
        this.index = "";
        
        this.target = new Target();
        this.commands = new ArrayList<Command>();
    }

    public int getIndexInt() {
        if (this.index == null || "".equals(this.index.trim())) {
            this.index = "0";
        }
        return Integer.valueOf(this.index).intValue();
    }

    public Boolean isSkip() {
        return Utility.convertStrToBoolean(this.skip);
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

    public String getIndex() {
        return index;
    }

    @XmlAttribute
    public void setIndex(String index) {
        this.index = index;
    }

    public Target getTarget() {
        return target;
    }

    @XmlElement(name=Constants.target)
    public void setTarget(Target target) {
        this.target = target;
    }

    public List<Command> getCommands() {
        return commands;
    }

    @XmlElementWrapper
    @XmlElement(name=Constants.command)
    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
    
    
}
