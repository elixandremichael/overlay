/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overlay.message;

import overlay.Utils;
import overlay.vcube.VCubeProtocol;
import peersim.core.Node;

/**
 *
 * @author elixandre
 */
public class ExecuteLockup implements Action{
    private byte[] hash;
    
    public ExecuteLockup(byte[] hash) {
        this.hash = hash;
    }       
    
    @Override
    public void run(Node node, VCubeProtocol protocol, boolean execute) {
        if(execute) {
            protocol.getProcessQueue().add(this);           
            return;
        }
        Utils.executeLookup(hash, node, protocol);
    }
}
