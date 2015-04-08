package com.seacon.gdt.library.runtime;

import com.seacon.gdt.library.utility.GdtLog;
import com.seacon.gdt.library.xml.objects.servers.Target;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The base command execution class.
 * 
 * @author varsanyi.peter
 * @verison 1.0
 * @since 2015.01.01
 */
public class GdtCommand {

    private Target targetServer;
    private String processInfo;
    private String asadminPath;
    private Process process;
    private List<String> parameters;
    private List<String> outputLines;
    private int commandExecuteIndex;

    /**
     * A base class for a command.
     * 
     * @param asadminPath String - The right asadmin path.
     * @param targetServer  Target - the target XML node.
     */
    public GdtCommand(String asadminPath, Target targetServer) {
        this.asadminPath = asadminPath;
        this.targetServer = targetServer;
        this.parameters = new ArrayList<String>();
        this.outputLines = new ArrayList<String>();
        this.parameters.add(this.asadminPath);
        this.commandExecuteIndex = -1;
    }

    /**
     * A command execution.
     * 
     * Execute command with <code>ProcessBuilder</code>.
     * The messasges are written to console and to the log file.
     * 
     * @throws Exception 
     */
    public void execute() throws Exception {
        executeBeforeCommand();
        GdtLog.info("-==  " + processInfo + " command execute begin.  ==-");
        GdtLog.info(getCommandAsString());
        this.outputLines.clear();
        this.process = new ProcessBuilder(getParameters()).start();
        
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            this.outputLines.add(line);
            GdtLog.info(line);
        }
        
        this.process.destroy();
        GdtLog.info("-==  " + processInfo + " command execute end.  ==-");
        executeAfterCommand();
    }

    /**
     * Do someting before command execution.
     * 
     * @throws Exception 
     */
    public void executeBeforeCommand() throws Exception {
        // Override for execte something before command
    }
    
    /**
     * Do someting after command execution.
     * 
     * @throws Exception 
     */
    public void executeAfterCommand() throws Exception {
        // Override for execte something before command
    }
    
    /**
     * Get <code>Target</code> XML node - the target server data.
     * @return 
     */
    public Target getTargetServer() {
        return targetServer;
    }

    /**
     * Get command lines: the command and parameters.
     * 
     * @return 
     */
    public List<String> getParameters() {
        return parameters;
    }

    /**
     * Set command lines: the command and parameters.
     * 
     * @param parameters 
     */
    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    /**
     * Get process information string.
     * 
     * @return 
     */
    public String getProcessInfo() {
        return processInfo;
    }

    /**
     * Set process information string.
     * 
     * @param processInfo 
     */
    public void setProcessInfo(String processInfo) {
        this.processInfo = processInfo;
    }

    /**
     * Get command as a string.
     * For print to the log and to the console.
     * 
     * @return 
     */
    private String getCommandAsString() {
        String retVal = "";
        for (String s : this.parameters) {
            retVal = retVal + s + " ";
        }
        return retVal;
    }

    /**
     * Get given output lines for examine outputs, the command result.
     * 
     * @return 
     */
    public List<String> getOutputLines() {
        return outputLines;
    }

    /**
     * Get the command execution index.
     * 
     * @return 
     */
    public int getCommandExecuteIndex() {
        return commandExecuteIndex;
    }

    /**
     * Set the command execution index.
     * 
     * @param commandExecuteIndex 
     */
    public void setCommandExecuteIndex(int commandExecuteIndex) {
        this.commandExecuteIndex = commandExecuteIndex;
    }
}
