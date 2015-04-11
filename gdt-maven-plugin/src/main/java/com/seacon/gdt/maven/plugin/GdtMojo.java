package com.seacon.gdt.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 *
 * @author peter
 * @version 1.0
 * @since 2015.04.08
 */
@Mojo( name = "execgdt")
public class GdtMojo extends AbstractMojo {

    @Parameter( property = "execgdt.str1", defaultValue = "str1Value" )
    private String str1;
    
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("******* GdtMojo is running... ********** - str1: " + str1);
    }
    
}
