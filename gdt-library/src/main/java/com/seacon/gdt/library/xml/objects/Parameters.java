package com.seacon.gdt.library.xml.objects;

import com.seacon.gdt.library.xml.Constants;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Peter
 */
@XmlType(name = Constants.parameters)
public class Parameters implements Serializable {
    
    public static final long serialVersionUID = 2015012945L;

    private String name;
    private String lastmodified;
    private String comment;
    private String asadminpath;
    
    public Parameters() {
        this.name = "";
        this.lastmodified = "";
        this.comment = "";
        this.asadminpath = "";
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getLastmodified() {
        return lastmodified;
    }

    @XmlElement
    public void setLastmodified(String lastmodified) {
        this.lastmodified = lastmodified;
    }

    public String getComment() {
        return comment;
    }

    @XmlElement
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAsadminpath() {
        return asadminpath;
    }

    @XmlElement
    public void setAsadminpath(String asadminpath) {
        this.asadminpath = asadminpath;
    }

}
