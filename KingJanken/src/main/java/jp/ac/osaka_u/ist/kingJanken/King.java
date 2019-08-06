package jp.ac.osaka_u.ist.kingJanken;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAException;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class King extends Agent {

    protected void setup() {
        final Set<AID> allSlaves = getAllSlaves();
        final Behaviour kingBehaviour = new KingBehaviour(this, allSlaves);
        addBehaviour(kingBehaviour);
    }

    private Set<AID> getAllSlaves() {
        final SearchConstraints searchConstraints = new SearchConstraints();
        searchConstraints.setMaxResults(-1L);
        try {
            return Arrays.stream(AMSService.search(this, new AMSAgentDescription(), searchConstraints))
                    .filter(this::isSlave)
                    .map(AMSAgentDescription::getName)
                    .collect(Collectors.toSet());
        } catch (final FIPAException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private boolean isSlave(final AMSAgentDescription agentDescription) {
        return agentDescription.getName().getLocalName().contains("slave");
    }
}
