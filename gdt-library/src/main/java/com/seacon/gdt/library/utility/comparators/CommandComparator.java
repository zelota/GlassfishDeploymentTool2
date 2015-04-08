package com.seacon.gdt.library.utility.comparators;

import com.seacon.gdt.library.xml.objects.servers.Command;
import java.util.Comparator;

/**
 *
 * @author varsanyi.peter
 */
public class CommandComparator implements Comparator<Command> {

    @Override
    public int compare(Command o1, Command o2) {
        if (o1 == null || o2 == null) {
            return 0;
        }
        return (new Integer(o1.getIndexInt()).compareTo(new Integer(o2.getIndexInt())));
    }
    
}
