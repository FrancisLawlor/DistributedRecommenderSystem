package peer.communicate;

import akka.actor.ActorRef;
import content.retrieve.RetrievedContent;

/**
 * Actor that doubles as an Apache Camel Processor for JSON messages
 * Converts JSON to RetrievedContent Actor Message
 * Sends RetrievedContent to InboundCommunicator
 *
 */
public class RetrievedContentProcessor extends JsonProcessorActor {
    private RetrievedContent retrievedContent;
    
    public RetrievedContentProcessor(ActorRef inboundCommunicator) {
        super(inboundCommunicator);
    }
    
    protected void processSpecificMessage(String exchangeMessage) {
        this.retrievedContent = super.gson.fromJson(exchangeMessage, RetrievedContent.class);
        super.inboundCommunicator.tell(this.retrievedContent, null);
    }
}
