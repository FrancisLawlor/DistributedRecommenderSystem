package peer.graph.link;

import peer.core.ActorMessage;
import peer.core.ActorMessageType;
import peer.core.UniversalId;
import peer.graph.weight.Weight;

/**
 * Signals a Link needs to be recorded to this Peer ID
 *
 */
public class PeerLinkAddition extends ActorMessage {
    private UniversalId peerId;
    private Weight startingWeight;
    
    public PeerLinkAddition(UniversalId peerId, Weight startingWeight) {
        super(ActorMessageType.PeerLinkAddition);
        this.peerId = peerId;
        this.startingWeight = startingWeight;
    }
    
    public UniversalId getPeerId() {
        return this.peerId;
    }
    
    public Weight getStartingWeight() {
        return this.startingWeight;
    }
}
