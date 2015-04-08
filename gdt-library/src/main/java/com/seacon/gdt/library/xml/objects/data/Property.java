package com.seacon.gdt.library.xml.objects.data;

import com.seacon.gdt.library.xml.Constants;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Peter
 */
@XmlType(name = Constants.property)
public class Property implements Serializable {
    
    public static final long serialVersionUID = 20150131334L;
    
    private String name;
    private String value;
    private String description;
    
    public Property() {
        this.name = "";
        this.value = "";
        this.description = "";
    }

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    @XmlAttribute
    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    @XmlAttribute
    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
