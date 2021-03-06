package peer.communicate.core;

import peer.frame.core.UniversalId;
import peer.frame.core.UniversalIdResolver;

/**
 * Formats UniversalIDs to Apache Camel URIs
 *
 */
public class CamelRestletUris {
    public static String getPeerRecommendationRequest(UniversalId peerId) {
        String ipAndPort = UniversalIdResolver.resolveID(peerId);
        return Restlet.COMPONENT + Restlet.PROTOCOL + ipAndPort + Restlet.PEER_RECOMMENDATION_REQUEST + Restlet.METHOD;
    }
    
    public static String getPeerRecommendation(UniversalId peerId) {
        String ipAndPort = UniversalIdResolver.resolveID(peerId);
        return Restlet.COMPONENT + Restlet.PROTOCOL + ipAndPort + Restlet.PEER_RECOMMENDATION + Restlet.METHOD;
    }
    
    public static String getPeerRetrieveContentRequest(UniversalId peerId) {
        String ipAndPort = UniversalIdResolver.resolveID(peerId);
        return Restlet.COMPONENT + Restlet.PROTOCOL + ipAndPort + Restlet.PEER_RETRIEVE_CONTENT_REQUEST + Restlet.METHOD;
    }
    
    public static String getRetrievedContent(UniversalId peerId) {
        String ipAndPort = UniversalIdResolver.resolveID(peerId);
        return Restlet.COMPONENT + Restlet.PROTOCOL + ipAndPort + Restlet.RETRIEVED_CONTENT + Restlet.METHOD;
    }
    
    public static String getPeerWeightUpdateRequest(UniversalId peerId) {
        String ipAndPort = UniversalIdResolver.resolveID(peerId);
        return Restlet.COMPONENT + Restlet.PROTOCOL + ipAndPort + Restlet.PEER_WEIGHT_UPDATE_REQUEST + Restlet.METHOD;
    }
    
    public static String getRemotePeerWeightedLinkAddition(UniversalId peerId) {
        String ipAndPort = UniversalIdResolver.resolveID(peerId);
        return Restlet.COMPONENT + Restlet.PROTOCOL + ipAndPort + Restlet.REMOTE_PEER_WEIGHTED_LINK_ADDITION + Restlet.METHOD;
    }
}
