package com.seacon.gdt.library.utility;

import com.seacon.gdt.library.xml.objects.Gdt;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * http://www.journaldev.com/1234/jaxb-tutorial-example-to-convert-object-to-xml-and-xml-to-object
 *
 * @author Peter
 */
public class Jaxb {

    public static Gdt readXml(String xmlPath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Gdt.class);
            Unmarshaller un = context.createUnmarshaller();
            Gdt emp = (Gdt) un.unmarshal(new File(xmlPath));
            return emp;
        } catch (JAXBException e) {
            GdtLog.error(e);
        }
        return null;
    }

    public static void writeXml(Gdt emp, String xmlPath) {

        if (xmlPath == null) {
            xmlPath = "c:\\Temp\\xxx.xml";
        }
        
        try {
            JAXBContext context = JAXBContext.newInstance(Gdt.class);
            Marshaller m = context.createMarshaller();
            m.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", new JaxbNamespaceMapper());
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, true);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            m.marshal(emp, new File(xmlPath));
        } catch (Exception e) {
            GdtLog.error(e);
        }
    }
}
