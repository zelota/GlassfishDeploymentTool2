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
 * @author Peter
 */
@XmlType(name = Constants.command)
public class Command implements Serializable {

    public static final long serialVersionUID = 2015013114L;

    private String id;
    private String skip;
    private String index;
    
    private List<Domain> domains;
    private List<Jdbcresource> jdbcresources;
    private List<Pool> pools;

    public Command() {
        this.id = "";
        this.skip = "";
        this.index = "";
        
        this.domains = new ArrayList<Domain>();
        this.jdbcresources = new ArrayList<Jdbcresource>();
        this.pools = new ArrayList<Pool>();
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

    public List<Domain> getDomains() {
        return domains;
    }

    @XmlElementWrapper
    @XmlElement(name = Constants.domain)
    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    public List<Jdbcresource> getJdbcresources() {
        return jdbcresources;
    }

    @XmlElementWrapper
    @XmlElement(name = Constants.jdbcresource)
    public void setJdbcresources(List<Jdbcresource> jdbcresources) {
        this.jdbcresources = jdbcresources;
    }

    public List<Pool> getPools() {
        return pools;
    }

    @XmlElementWrapper
    @XmlElement(name = Constants.pool)
    public void setPools(List<Pool> pools) {
        this.pools = pools;
    }

}
