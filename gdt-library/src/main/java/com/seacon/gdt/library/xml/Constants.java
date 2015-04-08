package com.seacon.gdt.library.xml;

/**
 *
 * @author varsanyi.peter
 */
public class Constants {
 
    public static final String gdt = "gdt";
    public static final String servers = "servers";
    public static final String data = "data";
    public static final String parameters = "parameters";
    public static final String server = "server";
    
    public static final String domain = "domain";
    public static final String component = "component";
    public static final String jdbcresource = "jdbcresource";
    public static final String pool = "pool";
    public static final String target = "target";
    
    public static final String command = "command";
    
    public static final String deploy = "deploy";
    public static final String subcomponent = "subcomponent";
    public static final String property = "property";

    /**
     * Command execution index values.
     * The lesser is executeced first.
     */
    public static final int CI_POOL_CREATE = 200;
    public static final int CI_POOL_DROP = 30;
    public static final int CI_JDBCRESOURCE_CREATE = 300;
    public static final int CI_JDBCRESOURCE_DROP = 20;
    public static final int CI_COMPONENT_RELOAD = 500;
    public static final int CI_COMPONENT_DEPLOY = 400;
    public static final int CI_COMPONENT_REDEPLOY = CI_COMPONENT_DEPLOY;
    public static final int CI_COMPONENT_UNDEPLOY = 10;
    
    public static final int CI_DOMAIN_CREATE = 5;
    public static final int CI_DOMAIN_DROP = 900;
    public static final int CI_DOMAIN_RESTART = 800;
    public static final int CI_DOMAIN_START = 700;
    public static final int CI_DOMAIN_STOP = 600;
    
}

