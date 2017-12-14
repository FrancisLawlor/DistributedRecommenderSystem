package peer.communicate;

import org.apache.camel.CamelContext;

import core.ActorMessage;

/**
 * Initialises the OutBound Communicator
 * Provides a Camel Context to an OutBound Communicator Actor
 * Provides this user's PeerID
 *
 */
public class OutboundCommInit extends ActorMessage {
    private CamelContext camelContext;
    
    public OutboundCommInit(CamelContext camelContext) {
        this.camelContext = camelContext;
    }
    
    public CamelContext getCamelContext() {
        return this.camelContext;
    }
}