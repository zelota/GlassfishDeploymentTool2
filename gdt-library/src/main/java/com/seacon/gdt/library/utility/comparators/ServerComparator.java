package com.seacon.gdt.library.utility.comparators;

import com.seacon.gdt.library.xml.objects.servers.Server;
import java.util.Comparator;

/**
 *
 * @author varsanyi.peter
 */
public class ServerComparator implements Comparator<Server> {

    @Override
    public int compare(Server o1, Server o2) {
        if (o1 == null || o2 == null) {
            return 0;
        }
        return (new Integer(o1.getIndexInt()).compareTo(new Integer(o2.getIndexInt())));
    }
    
}
