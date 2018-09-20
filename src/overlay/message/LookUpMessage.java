/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overlay.message;

import java.math.BigInteger;
import java.util.ArrayList;
import overlay.vcube.Parameters;
import overlay.vcube.VCubeProtocol;
import peersim.core.Network;
import peersim.core.Node;
import peersim.transport.Transport;

/**
 *
 * @author elixandrebaldi
 */

public class LookUpMessage implements Action{
    private Node sender;
    
    private BigInteger targetId;
    
    private int hopCounter = -1;
    
    private ArrayList<Integer> visited;
    
    public LookUpMessage(Node sender, BigInteger targetId) {
        this.sender = sender;
        this.targetId = targetId;
        this.visited = new ArrayList<Integer>();
        visited.add(sender.getIndex());
    }
    
    public void increaseHop() {
        this.hopCounter++;
    }
    
    public Node getSender() {
        return sender;
    }
    
    public BigInteger getTarget() {
        return targetId;
    }
    
    public int getHopCounter() {
        return hopCounter;
    }
    
    public boolean verifyVisited(int index) {        
        for(int i = 0; i < visited.size(); i++) {
            if(visited.get(i) == index) {                
                return true;
            }
        }
        
        visited.add(index);
        return false;
    }

    @Override
    public void run(Node node, VCubeProtocol protocol) {
        Parameters p = protocol.getP();
        // TODO
        /*ArrayList neighbor = protocol.getNeighbor();
        int pid = p.getPid();
>>>>>>> d144711232f9c24d660783e2e7e4a393af769c4f
        this.increaseHop();
        BigInteger target = this.getTarget();
            
        Transport t = (Transport) node.getProtocol(p.getTid());
        Node sender = this.getSender();

        if(target != ((VCubeProtocol) node.getProtocol(pid)).getVCubeId()) { //não chegou no alvo                                                          
            for(int i = 0; i < neighbor.size(); i++) {
<<<<<<< HEAD
                if(!this.verifyVisited(neighbor.get(i).getIndex())) {
                    //System.out.println("Salto em "+node.getIndex());
                    t.send(this.getSender(), neighbor.get(i), this, pid);
=======
                Node visit = (Node) neighbor.get(i);
                if(!this.verifyVisited(visit.getIndex())) {
                    //System.out.println("Salto em "+node.getIndex());
                    t.send(this.getSender(), (Node) neighbor.get(i), this, pid);
>>>>>>> d144711232f9c24d660783e2e7e4a393af769c4f
                    break;
                }                                                                                     
            }
        } else { //chegou no alvo, fazer envio de confirmação de entrega                
            //System.out.println("Nodo "+node.getIndex()+" recebeu mensagem de nodo "+sender.getIndex());
            t.send(node, sender, new FinalMessage(node, this.getHopCounter()), pid);
<<<<<<< HEAD
        }
=======
        }*/
    }
}