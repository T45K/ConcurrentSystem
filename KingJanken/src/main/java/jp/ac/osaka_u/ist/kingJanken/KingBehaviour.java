package jp.ac.osaka_u.ist.kingJanken;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class KingBehaviour extends SimpleBehaviour {
    private Agent king;
    private Set<AID> slaveIDs;

    KingBehaviour(final Agent king, final Set<AID> slaveIDs) {
        super(king);
        this.king = king;
        this.slaveIDs = slaveIDs;
    }

    @Override
    public void action() {
        sendMessage();

        System.out.println("king - \"Rock-Paper-Scissors\"");
        final JankenOperation kingsOperation = JankenOperation.getOperationFromString(generateRandomNumber());
        System.out.println("king - " + kingsOperation.toString());

        janken(kingsOperation);
    }

    @Override
    public boolean done() {
        if (slaveIDs.size() > 1) {
            final String nexts = slaveIDs.stream()
                    .map(AID::getLocalName)
                    .collect(Collectors.joining(","));
            System.out.println("Next stage: " + nexts);
            return false;
        }

        System.out.println("winner: " + slaveIDs.iterator().next().getLocalName());
        return true;
    }

    private void sendMessage() {
        final ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.setContent("ready");
        for (final AID agentID : slaveIDs) {
            message.addReceiver(agentID);
        }
        king.send(message);
    }

    private void janken(final JankenOperation kingsOperation) {
        int numOfCheckedSlaves = 0;
        final int numOfSlaves = slaveIDs.size();
        final Set<AID> winnerSlaves = new HashSet<>();
        final Set<AID> drawerSlaves = new HashSet<>();

        while (numOfCheckedSlaves < numOfSlaves) {
            final ACLMessage message = king.receive();
            if (message == null) {
                continue;
            }

            final AID slaveID = message.getSender();
            final JankenOperation slavesOperation = JankenOperation.getOperationFromString(message.getContent());
            System.out.println(slaveID.getLocalName() + " - " + slavesOperation.toString());

            final JankenResult result = slavesOperation.janken(kingsOperation);
            System.out.println(slaveID.getLocalName() + " - " + result.toString());

            if (result == JankenResult.WIN) {
                winnerSlaves.add(slaveID);
            } else if (result == JankenResult.DRAW) {
                drawerSlaves.add(slaveID);
            }

            numOfCheckedSlaves++;
        }

        if (winnerSlaves.size() >= 1) {
            slaveIDs = winnerSlaves;
        } else if (drawerSlaves.size() >= 1) {
            slaveIDs = drawerSlaves;
        }
    }

    private String generateRandomNumber() {
        final long randomSeed = this.hashCode() + System.currentTimeMillis();
        final int randomNumber = new Random(randomSeed).nextInt(Integer.MAX_VALUE);
        return Integer.toString(randomNumber);
    }
}
