package content.recommend;

import core.ActorMessageType;
import core.RequestCommunication;
import core.UniversalId;

/**
 * Requests a recommendation from the target peer for the requester peer
 *
 */
public class PeerRecommendationRequest extends RequestCommunication {
    
    public PeerRecommendationRequest(UniversalId origin, UniversalId target) {
        super(ActorMessageType.PeerRecommendationRequest, origin, target);
    }
}
