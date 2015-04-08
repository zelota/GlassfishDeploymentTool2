package com.seacon.gdt.library.utility;

// linux:???
//import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;
// windows: ????
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

/**
 *
 * @author Peter
 */
public class JaxbNamespaceMapper extends NamespacePrefixMapper {

    private static final String URN_PREFIX_D = "urnData"; 
    private static final String URN_PREFIX_S = "urnServers"; 

    private static final String URN_URI_D = "org.moooz.data";
    private static final String URN_URI_S = "org.moooz.servers";


    @Override
    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
        if (URN_URI_D.equals(namespaceUri)) {
            return URN_PREFIX_D;
        }
        if (URN_URI_S.equals(namespaceUri)) {
            return URN_PREFIX_S;
        }
        return suggestion;
    }

    @Override
    public String[] getPreDeclaredNamespaceUris() {
        return new String[]{URN_URI_D, URN_URI_S};
    }
}