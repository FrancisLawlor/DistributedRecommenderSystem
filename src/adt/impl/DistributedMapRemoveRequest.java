package adt.impl;

import peer.core.ActorMessageType;

/**
 * Request a key value pair be removed from a DistributedHashMappor
 *
 */
public class DistributedMapRemoveRequest extends DistributedMapRequest {
    
    public DistributedMapRemoveRequest(Object k) {
        super(k, ActorMessageType.DistributedMapRemoveRequest);
    }
    
}
