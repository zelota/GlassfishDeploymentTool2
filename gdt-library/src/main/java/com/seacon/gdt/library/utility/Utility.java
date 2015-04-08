package com.seacon.gdt.library.utility;

/**
 *
 * @author varsanyi.peter
 */
public class Utility {
    
    public static Boolean convertStrToBoolean(String str) {
        if (str == null || "".equals(str.trim())) {
            str = "false";
        }
        return ("true".equals(str.toLowerCase())) ? true : false;
    }
    
}
