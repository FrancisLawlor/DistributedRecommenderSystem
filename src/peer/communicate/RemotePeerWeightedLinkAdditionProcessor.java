package peer.communicate;

import akka.actor.ActorRef;
import peer.graph.distributedmap.RemotePeerWeightedLinkAddition;

/**
 * Actor that doubles as an Apache Camel Processor for JSON messages
 * Converts JSON to RemotePeerWeightedLinkAddition Actor Message
 * Sends this message to InboundCommunicator
 *
 */
public class RemotePeerWeightedLinkAdditionProcessor extends JsonProcessorActor {
    private RemotePeerWeightedLinkAddition remotePeerWeightedLinkAddition;
    
    public RemotePeerWeightedLinkAdditionProcessor(ActorRef inboundCommunicator) {
        super(inboundCommunicator);
    }
    
    protected void processSpecificMessage(String exchangeMessage) {
        this.remotePeerWeightedLinkAddition = super.gson.fromJson(exchangeMessage, RemotePeerWeightedLinkAddition.class);
        super.inboundCommunicator.tell(this.remotePeerWeightedLinkAddition, ActorRef.noSender());
    }
}
