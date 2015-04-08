package com.seacon.gdt.library.utility;

import org.slf4j.LoggerFactory;

/**
 *
 * @author Peter
 */
public class GdtLog {

    private static ch.qos.logback.classic.Logger logger = null;

    private static ch.qos.logback.classic.Logger Log() {
        if (logger == null) {
            logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("GDT_Logger");
        }
        return logger;
    }

    private static void logMessage(int level, String msg) {
        if (ch.qos.logback.classic.Level.DEBUG_INT == level) {
            Log().debug(msg);
        } else if (ch.qos.logback.classic.Level.ERROR_INT == level) {
            Log().error(msg);
        } else if (ch.qos.logback.classic.Level.TRACE_INT == level) {
            Log().trace(msg);
        } else if (ch.qos.logback.classic.Level.WARN_INT == level) {
            Log().warn(msg);
        } else {
            Log().info(msg);
        }
    }

    public static void error(String msg) {
        logMessage(ch.qos.logback.classic.Level.ERROR_INT, msg);
    }

    public static void error(Exception ex) {
        logMessage(ch.qos.logback.classic.Level.ERROR_INT, ex.getMessage());
        logExceptionStack(ex);
    }

    private static void logExceptionStack(Exception ex) {
        logMessage(ch.qos.logback.classic.Level.ERROR_INT, "----- Exception stack trace begin. -----");
        for (StackTraceElement ste : ex.getStackTrace()) {
            logMessage(ch.qos.logback.classic.Level.ERROR_INT, ste.toString());
        }
        logMessage(ch.qos.logback.classic.Level.ERROR_INT, "----- Exception stack trace end. -----");
    }

    public static void error(String msg, Exception ex) {
        error(msg + " " + ex.getMessage());
        logExceptionStack(ex);
    }

    public static void info(String msg) {
        logMessage(ch.qos.logback.classic.Level.INFO_INT, msg);
    }

    public static void debug(String msg) {
        logMessage(ch.qos.logback.classic.Level.DEBUG_INT, msg);
    }

    public static void trace(String msg) {
        logMessage(ch.qos.logback.classic.Level.TRACE_INT, msg);
    }

    public static void warn(String msg) {
        logMessage(ch.qos.logback.classic.Level.WARN_INT, msg);
    }
}
