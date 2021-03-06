package adt.distributedmap.messages;

import peer.frame.core.ActorMessageType;

/**
 * Ask if a key is contained by a DistributedHashMappor
 *
 */
public class DistributedMapContainsRequest extends DistributedMapRequest {
    
    public DistributedMapContainsRequest(int requestNum, Object k) {
        super(requestNum, k, ActorMessageType.DistributedMapContainsRequest);
    }
    
}
