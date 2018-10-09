/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overlay.message;

import overlay.Utils;
import overlay.vcube.VCubeProtocol;
import peersim.core.CommonState;
import peersim.core.Node;

/**
 *
 * @author elixandre
 */
public class VerifyTimestampLookup implements Action{
    private int startTime;    
    private byte[] hash;    

    public VerifyTimestampLookup(int startTime, byte[] hash) {
        this.startTime = startTime;        
        this.hash = hash;
    }

    public VerifyTimestampLookup() {    
    }    
    
    public int getStartTime() {
        return startTime;
    }
    
    @Override
    public void run(Node node, VCubeProtocol protocol, boolean execute) {
        if(execute) {
            protocol.getProcessQueue().add(this);            
            return;
        }        
        if(CommonState.getIntTime() - startTime >= 100) {            
            Utils.executeLookup(hash, node, protocol);
        } else {
            Utils.addVerifyTimestampLookup(hash, node, startTime);
        }
    }
    
}