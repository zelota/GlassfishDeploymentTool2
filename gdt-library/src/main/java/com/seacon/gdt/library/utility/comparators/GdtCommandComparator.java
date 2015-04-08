package com.seacon.gdt.library.utility.comparators;

import com.seacon.gdt.library.runtime.GdtCommand;
import java.util.Comparator;

/**
 *
 * @author varsanyi.peter
 * @since 2015.02.25
 * @version 1.0
 */
public class GdtCommandComparator implements Comparator<GdtCommand> {

    @Override
    public int compare(GdtCommand o1, GdtCommand o2) {
        if (o1 == null || o2 == null) {
            return 0;
        }
        return (new Integer(o1.getCommandExecuteIndex()).compareTo(new Integer(o2.getCommandExecuteIndex())));
    }
    
}
