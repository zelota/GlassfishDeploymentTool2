package com.seacon.gdt.library.xml.objects;

import com.seacon.gdt.library.xml.Constants;
import com.seacon.gdt.library.xml.objects.servers.Server;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Peter
 */
@XmlRootElement(name = Constants.gdt)
@XmlType (propOrder={"parameters","servers","data"})
public class Gdt implements Serializable {
    public static final long serialVersionUID = 2015012923L;
    
    private Parameters parameters;
    private List<Server> servers;
    private Data data;
    
    public Gdt() {
        this.parameters = new Parameters();
        this.servers = new ArrayList<Server>();
        this.data = new Data();
    }

    public Parameters getParameters() {
        return parameters;
    }

    @XmlElement
    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public List<Server> getServers() {
        return servers;
    }

    @XmlElementWrapper
    @XmlElement(name="server")
    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public Data getData() {
        return data;
    }

    @XmlElement(name="data")
    public void setData(Data data) {
        this.data = data;
    }
    
}
