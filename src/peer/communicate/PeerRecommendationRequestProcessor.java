package peer.communicate;

import akka.actor.ActorRef;
import content.recommend.PeerRecommendationRequest;

/**
 * Actor that doubles as an Apache Camel Processor for JSON messages
 * Converts JSON to PeerRetrieveContentRequest Actor Message
 * Sends this message to InboundCommunicator
 *
 */
public class PeerRecommendationRequestProcessor extends JsonProcessorActor {
    private PeerRecommendationRequest recommendRequest;
    
    public PeerRecommendationRequestProcessor(ActorRef inboundCommunicator) {
        super(inboundCommunicator);
    }
    
    protected void processSpecificMessage(String exchangeMessage) {
        this.recommendRequest = super.gson.fromJson(exchangeMessage, PeerRecommendationRequest.class);
        super.inboundCommunicator.tell(this.recommendRequest, null);
    }
}
