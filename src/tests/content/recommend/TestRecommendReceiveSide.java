package tests.content.recommend;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import content.recommend.actors.Recommender;
import content.recommend.messages.PeerRecommendationRequest;
import peer.frame.core.ActorNames;
import peer.frame.core.UniversalId;
import peer.frame.messages.PeerToPeerActorInit;
import tests.core.ActorTestLogger;
import tests.core.DummyInit;

public class TestRecommendReceiveSide {
    public static void main(String[] args) throws Exception {
        ActorSystem actSys = ActorSystem.create("ContentSystem");
        UniversalId peerOneId = new UniversalId("Peer1");
        UniversalId peerTwoId = new UniversalId("Peer2");
        
        // Create logger and put in Init message
        final ActorTestLogger logger = new ActorTestLogger();
        DummyInit dummyInit = new DummyInit(logger);
        
        final ActorRef recommenderToTest = actSys.actorOf(Props.create(Recommender.class), ActorNames.RECOMMENDER);
        PeerToPeerActorInit init = new PeerToPeerActorInit(peerTwoId, ActorNames.RECOMMENDER);
        recommenderToTest.tell(init, null);
        
        final ActorRef dummyViewHistorian = actSys.actorOf(Props.create(DummyViewHistorian.class), ActorNames.VIEW_HISTORIAN);
        init = new PeerToPeerActorInit(peerTwoId, ActorNames.VIEW_HISTORIAN);
        dummyViewHistorian.tell(init, null);
        dummyViewHistorian.tell(dummyInit, null);
        
        final ActorRef dummyOutboundCommunicator = actSys.actorOf(Props.create(DummyOutboundCommunicator.class), ActorNames.OUTBOUND_COMM);
        init = new PeerToPeerActorInit(peerTwoId, ActorNames.OUTBOUND_COMM);
        dummyOutboundCommunicator.tell(init, null);
        dummyOutboundCommunicator.tell(dummyInit, null);
        
        PeerRecommendationRequest request = new PeerRecommendationRequest(peerOneId, peerTwoId);
        recommenderToTest.tell(request, null); //Starts test
        
        // Waits 10 seconds for the test to complete
        Thread.sleep(10000);
        
        //Print out messages
        for (String message : logger) {
            System.out.println(message);
        }
    }
}
