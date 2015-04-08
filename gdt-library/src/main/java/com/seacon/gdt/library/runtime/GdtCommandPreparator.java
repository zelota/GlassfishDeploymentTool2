package com.seacon.gdt.library.runtime;

import com.seacon.gdt.library.utility.GdtLog;
import com.seacon.gdt.library.xml.objects.Data;
import com.seacon.gdt.library.xml.objects.servers.Component;
import com.seacon.gdt.library.xml.objects.servers.Command;
import com.seacon.gdt.library.xml.objects.servers.Jdbcresource;
import com.seacon.gdt.library.xml.objects.servers.Domain;
import com.seacon.gdt.library.xml.objects.servers.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * Command preparator class.
 * 
 * @author varsanyi.peter
 * @since 2015.02.25
 * @version 1.0
 */
public class GdtCommandPreparator {

    private final String asadminPath;
    private final Target targetServer;

    public GdtCommandPreparator(String asadminPath, Target targetServer) {
        this.asadminPath = asadminPath;
        this.targetServer = targetServer;
    }

    /**
     * Command handler.
     *
     * Order: - pools - jdbcresources - domains - domain apps (in handleDomains)
     *
     * @param command
     * @param data
     */
    public List<GdtCommand> prepare(Command command, Data data) throws Exception {
        List<GdtCommand> retVal = new ArrayList<GdtCommand>();

        preparePoolCommands(retVal, command.getPools(), data);
        prepareJdbcresourceCommands(retVal, command.getJdbcresources(), data);
        prepareDomainCommands(retVal, command.getDomains(), data);

        return retVal;
    }

    private void preparePoolCommands(List<GdtCommand> commands, List<com.seacon.gdt.library.xml.objects.servers.Pool> poolsInCommand, Data data) throws Exception {
        for (com.seacon.gdt.library.xml.objects.servers.Pool poolCmd : poolsInCommand) {
            if (poolCmd.isSkip()) {
                GdtLog.info("SKIP pool command. id: '" + poolCmd.getId() + "'");
            } else {
                com.seacon.gdt.library.xml.objects.data.Pool poolData = getPoolDataById(poolCmd.getId(), data);

                GdtLog.info("Handle pool command. id: '" + poolData.getId() + "' - jndiName: " + poolData.getJndiName());

                Boolean poolExists = poolData.isExists(asadminPath, targetServer);

                if ((poolCmd.isDrop() || poolCmd.isRecreate()) && poolExists) {
                    com.seacon.gdt.library.runtime.pool.Drop poolDrop = new com.seacon.gdt.library.runtime.pool.Drop(this.asadminPath, this.targetServer);
                    poolDrop.setParameters(poolData);
                    commands.add(poolDrop);
                } else {
                    GdtLog.info("drop: SKIPPED.");
                }

                if ((poolCmd.isCreate() || poolCmd.isRecreate()) && !poolExists) {
                    com.seacon.gdt.library.runtime.pool.Create poolCreate = new com.seacon.gdt.library.runtime.pool.Create(this.asadminPath, this.targetServer);
                    poolCreate.setParameters(poolData);
                    commands.add(poolCreate);
                } else {
                    GdtLog.info("create: SKIPPED.");
                }
            }
        }
    }

    private void prepareJdbcresourceCommands(List<GdtCommand> commands, List<Jdbcresource> jdbcresoucesInCommand, Data data) throws Exception {
        for (com.seacon.gdt.library.xml.objects.servers.Jdbcresource jdbcrCmd : jdbcresoucesInCommand) {
            if (jdbcrCmd.isSkip()) {
                GdtLog.info("SKIP JDBC Resource command. id: '" + jdbcrCmd.getId() + "'");
            } else {
                com.seacon.gdt.library.xml.objects.data.Jdbcresource jdbcrData = getJdbcresourceDataById(jdbcrCmd.getId(), data);

                GdtLog.info("Handle jdbc resource command. id: '" + jdbcrData.getId() + "' - name: " + jdbcrData.getName());

                Boolean jdbcrExists = jdbcrData.isExists(asadminPath, targetServer);

                if ((jdbcrCmd.isDrop() || jdbcrCmd.isRecreate()) && jdbcrExists) {
                    com.seacon.gdt.library.runtime.jdbcresource.Drop jdbcrDrop = new com.seacon.gdt.library.runtime.jdbcresource.Drop(this.asadminPath, this.targetServer);
                    jdbcrDrop.setParameters(jdbcrData);
                    commands.add(jdbcrDrop);
                } else {
                    GdtLog.info("drop: SKIPPED.");
                }

                if ((jdbcrCmd.isCreate() || jdbcrCmd.isRecreate()) && !jdbcrExists) {
                    com.seacon.gdt.library.xml.objects.data.Pool poolData = getPoolDataById(jdbcrData.getPoolid(), data);
                    com.seacon.gdt.library.runtime.jdbcresource.Create jdbcrCreate = new com.seacon.gdt.library.runtime.jdbcresource.Create(this.asadminPath, this.targetServer);
                    jdbcrCreate.setParameters(jdbcrData, poolData);
                    commands.add(jdbcrCreate);
                } else {
                    GdtLog.info("create: SKIPPED.");
                }
            }
        }
    }

    private void prepareDomainCommands(List<GdtCommand> commands, List<Domain> domainsInCommand, Data data) throws Exception {
        for (com.seacon.gdt.library.xml.objects.servers.Domain domainCmd : domainsInCommand) {
            if (domainCmd.isSkip()) {
                GdtLog.info("SKIP domain command. id: '" + domainCmd.getId() + "'");
            } else {
                com.seacon.gdt.library.xml.objects.data.Domain domainData = getDomainDataById(domainCmd.getId(), data);

                GdtLog.info("Handle domain command. id: '" + domainData.getId() + "' - name: " + domainData.getName());

                Boolean domainExists = domainData.isExists(asadminPath, targetServer);

                if ((domainCmd.isDrop() && domainExists)) {
                    com.seacon.gdt.library.runtime.domain.Stop domainStop = new com.seacon.gdt.library.runtime.domain.Stop(this.asadminPath, this.targetServer);
                    domainStop.setParameters(domainData);
                    commands.add(domainStop);

                    com.seacon.gdt.library.runtime.domain.Drop domainDrop = new com.seacon.gdt.library.runtime.domain.Drop(this.asadminPath, this.targetServer);
                    domainDrop.setParameters(domainData);
                    commands.add(domainDrop);
                } else {
                    GdtLog.info("drop: SKIPPED.");
                }

                if (domainCmd.isCreate() && !domainExists) {
                    com.seacon.gdt.library.runtime.domain.Create domainCreate = new com.seacon.gdt.library.runtime.domain.Create(this.asadminPath, this.targetServer);
                    domainCreate.setParameters(domainData);
                    commands.add(domainCreate);

                    com.seacon.gdt.library.runtime.domain.Start domainStart = new com.seacon.gdt.library.runtime.domain.Start(this.asadminPath, this.targetServer);
                    domainStart.setParameters(domainData);
                    commands.add(domainStart);
                } else {
                    GdtLog.info("create: SKIPPED.");
                }

                if (domainCmd.isStop() && domainExists && !isDomainStopInCollection(commands, domainData) && domainData.isRunning(this.asadminPath, this.targetServer)) {
                    com.seacon.gdt.library.runtime.domain.Stop domainStop = new com.seacon.gdt.library.runtime.domain.Stop(this.asadminPath, this.targetServer);
                    domainStop.setParameters(domainData);
                    commands.add(domainStop);
                } else {
                    GdtLog.info("stop: SKIPPED.");
                }

                if (domainCmd.isStart() && domainExists && !isDomainStartInCollection(commands, domainData) && !domainData.isRunning(this.asadminPath, this.targetServer)) {
                    com.seacon.gdt.library.runtime.domain.Start domainStart = new com.seacon.gdt.library.runtime.domain.Start(this.asadminPath, this.targetServer);
                    domainStart.setParameters(domainData);
                    commands.add(domainStart);
                } else {
                    GdtLog.info("start: SKIPPED.");
                }

                if (domainCmd.isRestart() && domainExists) {
                    com.seacon.gdt.library.runtime.domain.Restart domainRestart = new com.seacon.gdt.library.runtime.domain.Restart(this.asadminPath, this.targetServer);
                    domainRestart.setParameters(domainData);
                    commands.add(domainRestart);
                } else {
                    GdtLog.info("restart: SKIPPED.");
                }

                if (domainExists) {
                    prepareComponentCommands(commands, domainCmd.getComponents(), domainData, data);
                }
            }
        }
    }

    private void prepareComponentCommands(List<GdtCommand> commands, List<Component> componentsInCommand, com.seacon.gdt.library.xml.objects.data.Domain domainData, Data data) throws Exception {
        for (com.seacon.gdt.library.xml.objects.servers.Component componentCmd : componentsInCommand) {
            if (componentCmd.isSkip()) {
                GdtLog.info("SKIP component command. id: '" + componentCmd.getId() + "'");
            } else {
                com.seacon.gdt.library.xml.objects.data.Component componentData = getComponentDataById(componentCmd.getId(), data);

                GdtLog.info("Handle component command. id: '" + componentData.getId() + "' - name: " + componentData.getName() + " - component type: " + componentData.getCtype());

                com.seacon.gdt.library.xml.objects.data.Component parentAppData = null;
                if (componentData.isTypeSubcomponent() && componentData.getScappid() != null && !componentData.getScappid().isEmpty()) {
                    parentAppData = getComponentDataById(componentData.getScappid(), data);
                }

                Boolean componentExists = componentData.isExists(asadminPath, targetServer, parentAppData, domainData);

                if (componentCmd.isUndeploy() && componentExists) {
                    com.seacon.gdt.library.runtime.component.Undeploy componentUndeploy = new com.seacon.gdt.library.runtime.component.Undeploy(this.asadminPath, this.targetServer);
                    componentUndeploy.setParameters(componentData, domainData);
                    commands.add(componentUndeploy);
                }

                if (componentCmd.isDeploy() && (!componentExists || componentCmd.isForce())) {
                    com.seacon.gdt.library.runtime.component.Deploy componentDeploy = new com.seacon.gdt.library.runtime.component.Deploy(this.asadminPath, this.targetServer);
                    componentDeploy.setParameters(componentData, parentAppData, domainData);
                    commands.add(componentDeploy);
                }

                if (componentCmd.isRedeploy() && componentExists) {
                    com.seacon.gdt.library.runtime.component.Redeploy componentRedeploy = new com.seacon.gdt.library.runtime.component.Redeploy(this.asadminPath, this.targetServer);
                    componentRedeploy.setParameters(componentData, domainData);
                    commands.add(componentRedeploy);
                }

                if (componentCmd.isReload() && componentExists) {
                    com.seacon.gdt.library.runtime.component.Reload componentReload = new com.seacon.gdt.library.runtime.component.Reload(this.asadminPath, this.targetServer);
                    componentReload.setParameters(componentData, domainData, targetServer.getDomainsrootdir());
                    commands.add(componentReload);
                }
            }
        }
    }

    private com.seacon.gdt.library.xml.objects.data.Pool getPoolDataById(String id, Data data) throws Exception {
        com.seacon.gdt.library.xml.objects.data.Pool retVal = null;

        for (int i = 0; i < data.getPools().size() && retVal == null; i++) {
            if (data.getPools().get(i).getId().equals(id)) {
                retVal = data.getPools().get(i);
            }
        }

        if (retVal == null) {
            throw new Exception("Not found pool data by id! (id: '" + id + "')");
        }
        return retVal;
    }

    private com.seacon.gdt.library.xml.objects.data.Jdbcresource getJdbcresourceDataById(String id, Data data) throws Exception {
        com.seacon.gdt.library.xml.objects.data.Jdbcresource retVal = null;

        for (int i = 0; i < data.getJdbcresources().size() && retVal == null; i++) {
            if (data.getJdbcresources().get(i).getId().equals(id)) {
                retVal = data.getJdbcresources().get(i);
            }
        }

        if (retVal == null) {
            throw new Exception("Not found jdbc resource data by id! (id: '" + id + "')");
        }
        return retVal;
    }

    private com.seacon.gdt.library.xml.objects.data.Domain getDomainDataById(String id, Data data) throws Exception {
        com.seacon.gdt.library.xml.objects.data.Domain retVal = null;

        for (int i = 0; i < data.getDomains().size() && retVal == null; i++) {
            if (data.getDomains().get(i).getId().equals(id)) {
                retVal = data.getDomains().get(i);
            }
        }

        if (retVal == null) {
            throw new Exception("Not found domain data by id! (id: '" + id + "')");
        }
        return retVal;
    }

    private com.seacon.gdt.library.xml.objects.data.Component getComponentDataById(String id, Data data) throws Exception {
        com.seacon.gdt.library.xml.objects.data.Component retVal = null;

        for (int i = 0; i < data.getComponents().size() && retVal == null; i++) {
            if (data.getComponents().get(i).getId().equals(id)) {
                retVal = data.getComponents().get(i);
            }
        }

        if (retVal == null) {
            throw new Exception("Not found component data by id! (id: '" + id + "')");
        }
        return retVal;
    }

    private Boolean isDomainStopInCollection(List<GdtCommand> commands, com.seacon.gdt.library.xml.objects.data.Domain domainData) {
        Boolean retVal = false;

        for (int i = 0; i < commands.size() && retVal == false; i++) {
            if (commands.get(i) instanceof com.seacon.gdt.library.runtime.domain.Stop) {
                for (int j = 0; j < commands.get(i).getParameters().size() && retVal == false; j++) {
                    if (commands.get(i).getParameters().get(j).contains(domainData.getName())) {
                        retVal = true;
                    }
                }
            }
        }

        return retVal;
    }

    private boolean isDomainStartInCollection(List<GdtCommand> commands, com.seacon.gdt.library.xml.objects.data.Domain domainData) {
        Boolean retVal = false;

        for (int i = 0; i < commands.size() && retVal == false; i++) {
            if (commands.get(i) instanceof com.seacon.gdt.library.runtime.domain.Start) {
                for (int j = 0; j < commands.get(i).getParameters().size() && retVal == false; j++) {
                    if (commands.get(i).getParameters().get(j).contains(domainData.getName())) {
                        retVal = true;
                    }
                }
            }
        }

        return retVal;
    }

}
