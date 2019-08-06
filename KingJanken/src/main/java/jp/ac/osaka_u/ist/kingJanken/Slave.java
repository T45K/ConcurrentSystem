package jp.ac.osaka_u.ist.kingJanken;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class Slave extends Agent {
    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                final ACLMessage message = receive();
                if (message == null) {
                    return;
                }

                final ACLMessage reply = message.createReply();
                reply.setPerformative(ACLMessage.INFORM);
                reply.setContent(generateRandom());
                send(reply);
            }
        });
    }

    private String generateRandom() {
        final long randomSeed = this.hashCode() + System.currentTimeMillis();
        final int randomNumber = new Random(randomSeed).nextInt(Integer.MAX_VALUE);
        return Integer.toString(randomNumber);
    }
}
