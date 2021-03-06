package tests.peer.graph;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import peer.frame.core.ActorNames;
import peer.frame.core.UniversalId;
import peer.frame.messages.PeerToPeerActorInit;
import peer.graph.actors.PeerWeightedLinkor;
import tests.core.ActorTestLogger;
import tests.core.DummyInit;
import tests.core.StartTest;

public class TestPeerWeightedLinkor {
    public static final String TESTOR_NAME = "PeerWeightedLinkorTestor";
    
    public static void main(String[] args) throws Exception {
        UniversalId peerOneId = new UniversalId(PeerWeightedLinkorTestor.PEER_ONE);
        
        ActorSystem actorSystem = ActorSystem.create("ContentSystem");
        
        final ActorRef peerWeightedLinkor = actorSystem.actorOf(Props.create(PeerWeightedLinkor.class), ActorNames.PEER_LINKER);
        PeerToPeerActorInit peerIdInit = new PeerToPeerActorInit(peerOneId, ActorNames.PEER_LINKER);
        peerWeightedLinkor.tell(peerIdInit, ActorRef.noSender());
        
        final ActorRef peerWeightedLinkorTestor = actorSystem.actorOf(Props.create(PeerWeightedLinkorTestor.class), TESTOR_NAME);
        peerIdInit = new PeerToPeerActorInit(peerOneId, TESTOR_NAME);
        peerWeightedLinkorTestor.tell(peerIdInit, ActorRef.noSender());
        
        final ActorRef dummyOutboundComm = actorSystem.actorOf(Props.create(DummyOutboundCommunicator.class), ActorNames.OUTBOUND_COMM);
        peerIdInit = new PeerToPeerActorInit(peerOneId, ActorNames.OUTBOUND_COMM);
        dummyOutboundComm.tell(peerIdInit, ActorRef.noSender());
        
        final ActorRef dummyDatabaser = actorSystem.actorOf(Props.create(DummyDatabaser.class), ActorNames.DATABASER);
        peerIdInit = new PeerToPeerActorInit(peerOneId, ActorNames.DATABASER);
        dummyDatabaser.tell(peerIdInit, ActorRef.noSender());
        
        // Logger
        ActorTestLogger logger = new ActorTestLogger();
        DummyInit loggerInit = new DummyInit(logger);
        peerWeightedLinkorTestor.tell(loggerInit, ActorRef.noSender());
        dummyOutboundComm.tell(loggerInit, ActorRef.noSender());
        dummyDatabaser.tell(loggerInit, ActorRef.noSender());
        
        // Start Test
        peerWeightedLinkorTestor.tell(new StartTest(), null);
        Thread.sleep(1000);
        peerWeightedLinkorTestor.tell(new StartTest(), null);
        Thread.sleep(1000);
        peerWeightedLinkorTestor.tell(new StartTest(), null);
        Thread.sleep(1000);
        peerWeightedLinkorTestor.tell(new StartTest(), null);
        Thread.sleep(1000);
        peerWeightedLinkorTestor.tell(new StartTest(), null);
        Thread.sleep(1000);
        peerWeightedLinkorTestor.tell(new StartTest(), null);
        Thread.sleep(1000);
        peerWeightedLinkorTestor.tell(new StartTest(), null);
        Thread.sleep(1000);
        
        for (String message : logger) {
            System.out.println(message);
        }
    }
}
