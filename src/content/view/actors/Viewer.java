package content.view.actors;

import java.util.concurrent.BlockingQueue;

import akka.actor.ActorSelection;
import content.recommend.messages.RecommendationsForUser;
import content.recommend.messages.RecommendationsForUserRequest;
import content.retrieve.messages.LocalRetrieveContentRequest;
import content.retrieve.messages.RetrievedContent;
import content.view.messages.RecordContentView;
import content.view.messages.ViewerInit;
import peer.data.messages.LoadSavedContentFileRequest;
import peer.data.messages.LoadedContent;
import peer.data.messages.LocalSavedContentRequest;
import peer.data.messages.LocalSavedContentResponse;
import peer.data.messages.SaveContentFileRequest;
import peer.frame.actors.PeerToPeerActor;
import peer.frame.core.ActorPaths;
import peer.frame.exceptions.UnknownMessageException;
import peer.frame.messages.PeerToPeerActorInit;

/**
 * Handles view related matters
 * Sends requests to local Recommender
 * Receives back Recommendations For User
 * When a recommendation is selected it retrieves content with Retrievers
 *
 */
public class Viewer extends PeerToPeerActor {
    private BlockingQueue<RecommendationsForUser> recommendationsQueue;
    private BlockingQueue<RetrievedContent> retrievedContentQueue;
    private BlockingQueue<LocalSavedContentResponse> savedContentQueue;
    private BlockingQueue<LoadedContent> loadedContentQueue;
    
    /**
     * Actor Message Processing
     */
    @Override
    public void onReceive(Object message) {
        if (message instanceof PeerToPeerActorInit) {
            PeerToPeerActorInit init = (PeerToPeerActorInit) message;
            super.initialisePeerToPeerActor(init);
        }
        else if (message instanceof ViewerInit) {
            ViewerInit init = (ViewerInit) message;
            this.recommendationsQueue = init.getRecommendationsQueue();
            this.retrievedContentQueue = init.getRetrievedContentQueue();
            this.savedContentQueue = init.getSavedContentQueue();
            this.loadedContentQueue = init.getLoadedContentQueue();
        }
        else if (message instanceof RecommendationsForUserRequest) {
            RecommendationsForUserRequest request = (RecommendationsForUserRequest) message;
            this.processRecommendationsForUserRequest(request);
        }
        else if (message instanceof LocalRetrieveContentRequest) {
            LocalRetrieveContentRequest request = (LocalRetrieveContentRequest) message;
            this.processLocalRetrieveContentRequest(request);
        }
        else if (message instanceof RecommendationsForUser) {
            RecommendationsForUser recommendations = (RecommendationsForUser) message;
            this.processRecommendationsForUser(recommendations);
        }
        else if (message instanceof RetrievedContent) {
            RetrievedContent content = (RetrievedContent) message;
            this.processRetrievedContent(content);
        }
        else if (message instanceof RecordContentView) {
            RecordContentView recordContentView = (RecordContentView) message;
            this.processRecordContentView(recordContentView);
        }
        else if (message instanceof SaveContentFileRequest) {
            SaveContentFileRequest request = (SaveContentFileRequest) message;
            this.processSaveContentFileRequest(request);
        }
        else if (message instanceof LocalSavedContentRequest) {
            LocalSavedContentRequest request = (LocalSavedContentRequest) message;
            this.processLocalSavedContentRequest(request);
        }
        else if (message instanceof LocalSavedContentResponse) {
            LocalSavedContentResponse response = (LocalSavedContentResponse) message;
            this.processLocalSavedContentResponse(response);
        }
        else if (message instanceof LoadSavedContentFileRequest) {
            LoadSavedContentFileRequest request = (LoadSavedContentFileRequest) message;
            this.processLoadSavedContentFileRequest(request);
        }
        else if (message instanceof LoadedContent) {
            LoadedContent loadedContent = (LoadedContent) message;
            this.processLoadedContent(loadedContent);
        }
        else {
            throw new UnknownMessageException();
        }
    }
    
    /**
     * Viewer will ask the Recommender Actor for Recommendations For User
     */
    public void processRecommendationsForUserRequest(RecommendationsForUserRequest request) {
        ActorSelection recommender = getContext().actorSelection(ActorPaths.getPathToRecommender());
        recommender.tell(request, getSelf());
    }
    
    /**
     * Viewer will ask the Retriever Actor to retrieve Content for viewing
     * @param content
     * @param fromPeerId
     */
    public void processLocalRetrieveContentRequest(LocalRetrieveContentRequest request) {
        ActorSelection retriever = getContext().actorSelection(ActorPaths.getPathToRetriever());
        retriever.tell(request, getSelf());
    }
    
    /**
     * Send Recommendation for User to State Machine Controller
     * @param recommendations
     */
    protected void processRecommendationsForUser(RecommendationsForUser recommendations) {
        this.recommendationsQueue.add(recommendations);
    }
    
    /**
     * Send Retrieved Content for User to State Machine Controller
     * @param content
     */
    protected void processRetrievedContent(RetrievedContent retrievedContent) {
        this.retrievedContentQueue.add(retrievedContent);
    }
    
    /**
     * Viewer must record a content view with the view historian
     * @param recordContentView
     */
    protected void processRecordContentView(RecordContentView recordContentView) {
        ActorSelection viewHistorian = getContext().actorSelection(ActorPaths.getPathToViewHistorian());
        viewHistorian.tell(recordContentView, getSelf());
    }
    
    /**
     * Viewer passes on a content file to the databaser to be saved
     * @param request
     */
    protected void processSaveContentFileRequest(SaveContentFileRequest request) {
        ActorSelection databaser = getContext().actorSelection(ActorPaths.getPathToDatabaser());
        databaser.tell(request, getSelf());
    }
    
    /**
     * Viewer asks Databaser for some saved content
     * @param request
     */
    protected void processLocalSavedContentRequest(LocalSavedContentRequest request) {
        ActorSelection databaser = getContext().actorSelection(ActorPaths.getPathToDatabaser());
        databaser.tell(request, getSelf());
    }
    
    /**
     * Viewer will put this in the blocking queue for the GUI to access through the ViewerToUIChannel
     * @param response
     */
    protected void processLocalSavedContentResponse(LocalSavedContentResponse response) {
        this.savedContentQueue.add(response);
    }
    
    /**
     * Viewer will pass on a request to the databaser 
     * @param request
     */
    protected void processLoadSavedContentFileRequest(LoadSavedContentFileRequest request) {
        ActorSelection databaser = getContext().actorSelection(ActorPaths.getPathToDatabaser());
        databaser.tell(request, getSelf());
    }
    
    /**
     * Viewer will put this in the blocking queue for the GUI to access through the ViewerToUIChannel
     * @param loadedContent
     */
    protected void processLoadedContent(LoadedContent loadedContent) {
        this.loadedContentQueue.add(loadedContent);
    }
}
