package peer.communicate;

import akka.actor.ActorRef;
import content.retrieve.PeerRetrieveContentRequest;

/**
 * Actor that doubles as an Apache Camel Processor for JSON messages
 * Converts JSON to PeerRetrieveContentRequest Actor Message
 * Sends this message to InboundCommunicator
 *
 */
public class PeerRetrieveContentRequestProcessor extends JsonProcessorActor {
    private PeerRetrieveContentRequest retrieveContentRequest;
    
    public PeerRetrieveContentRequestProcessor(ActorRef inboundCommunicator) {
        super(inboundCommunicator);
    }
    
    protected void processSpecificMessage(String exchangeMessage) {
        this.retrieveContentRequest = super.gson.fromJson(exchangeMessage, PeerRetrieveContentRequest.class);
        super.inboundCommunicator.tell(this.retrieveContentRequest, null);
    }
}
