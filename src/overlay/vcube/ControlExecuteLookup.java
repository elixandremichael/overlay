/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overlay.vcube;

import overlay.Utils;
import overlay.message.ExecuteLockup;
import overlay.message.ExecuteProcess;
import peersim.config.Configuration;
import peersim.core.CommonState;
import peersim.core.Control;
import peersim.core.Network;
import peersim.edsim.EDSimulator;

/**
 *
 * @author elixandrebaldi
 */
public class ControlExecuteLookup implements Control {
    private static final String PAR_PROT = "protocol";
    
    private final int pid;     
    
    public ControlExecuteLookup(String prefix) {
        pid = Configuration.getPid(prefix + "." + PAR_PROT);                                 
    }        
    
    public boolean execute() {                        
        byte[] hash = Utils.generateHash(Utils.getRandomString(), "SHA-256");
        //System.out.println(Utils.stringHexa(hash));
        
        ExecuteLockup message = new ExecuteLockup(hash);
        //EDSimulator.add(50, message, Utils.getRandomNode(), pid);  
        if(VCubeCreate.flag) {
            EDSimulator.add(5, message, Network.get(5), pid);     
            VCubeCreate.flag = false;
        }        
        return false;
    }
}
