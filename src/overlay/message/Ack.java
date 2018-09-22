/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overlay.message;

import java.util.Queue;
import overlay.vcube.VCubeProtocol;
import peersim.core.CommonState;
import peersim.core.Node;

/**
 *
 * @author elixandre
 */
public class Ack implements Action{
    private int sender;
    private int[] timestampSender;
    
    public Ack(int sender, int[] timestamp) {
        this.sender = sender;
        this.timestampSender = timestamp;
    }

    @Override
    public void run(Node node, VCubeProtocol protocol, boolean execute) {        
        if(execute) {
            protocol.getProcessQueue().add(this);            
            return;
        }        
        System.out.println("Nodo: "+protocol.getCurrentId()+" recebeu ack de nodo "+sender);     
        updateTimestampLocal(protocol.getTimestamp(), protocol.getCurrentId());
    }    
    
    void updateTimestampLocal(int[] timestampLocal, int index) {
        boolean changes = false;
        for(int i = 0; i < timestampLocal.length; i++) {
            if(timestampLocal[i] < timestampSender[i] && i != index) {
                timestampLocal[i] = timestampSender[i];
                changes = true;
            }
        }        
        if(timestampLocal[sender] % 2 != 0) {
            timestampLocal[sender]++;
            changes = true;
        }        
    }
}
