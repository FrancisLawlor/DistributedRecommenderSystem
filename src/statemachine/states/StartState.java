package statemachine.states;

import gui.core.GUI;
import gui.core.SceneContainerStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import statemachine.core.StateMachine;
import statemachine.utils.StateName;

public class StartState extends State {
	private StateMachine stateMachine;
	private SceneContainerStage sceneContainerStage;
	private GUI gui;
	
	public StartState(StateMachine stateMachine, SceneContainerStage sceneContainerStage, GUI gui) {
		this.stateMachine = stateMachine;
		this.sceneContainerStage = sceneContainerStage;
		this.gui = gui;
		
		configureButtons();
	}

	private void configureButtons() {
		gui.getDashBoardScene().getMyFilesButton().setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	    	    		stateMachine.setCurrentState(StateName.VIEWING_FILES.toString());
	    	    		stateMachine.execute(null);
	    	    }
	    	});
		
		gui.getDashBoardScene().getRefreshButton().setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	    	    		//TODO add logic for refresh
	    	    }
	    	});
		
		gui.getDashBoardScene().getAddFileButton().setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	    	    		stateMachine.setCurrentState(StateName.ADD_FILE.toString());
	    	    		stateMachine.execute(null);
	    	    }
	    	});
		
		gui.getFileRetrievalQueryScene().getNoButton().setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	    	    		stateMachine.setCurrentState(StateName.DASHBOARD.toString());
	    	    		stateMachine.execute(null);
	    	    }
	    	});
		
		gui.getFileRetrievalQueryScene().getYesButton().setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	    	    		stateMachine.setCurrentState(StateName.RETRIEVING_FILE.toString());
	    	    		stateMachine.execute(null);
	    	    }
	    	});
		
		gui.getMyFilesScene().getBackButton().setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	    	    		stateMachine.setCurrentState(StateName.DASHBOARD.toString());
	    	    		stateMachine.execute(null);
	    	    }
		});
		
		gui.getRatingScene().getBackButton().setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	    	    		stateMachine.setCurrentState(StateName.DASHBOARD.toString());
	    	    		stateMachine.execute(null);
	    	    }
		});
		
		gui.getRatingScene().getSubmitButton().setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	    	    		stateMachine.execute(null);
	    	    }
		});
		
		gui.getSetupScene().getNextButton().setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
    	    			stateMachine.setCurrentState(StateName.RETRIEVING_FILE.toString());
    	    			sceneContainerStage.init(gui.getRetrieveRecommendationsScene());
	    	    		stateMachine.execute(null);
	    	    }
		});
	}

	@Override
	public void execute(StateName param) {
		if (!configFileExists()) {
			stateMachine.setCurrentState(StateName.SETUP.toString());
			sceneContainerStage.init(gui.getSetupScene());
			sceneContainerStage.show();
		} else {
			stateMachine.setCurrentState(StateName.RETRIEVE_RECOMMENDATIONS.toString());
			sceneContainerStage.init(gui.getRetrieveRecommendationsScene());
			sceneContainerStage.show();
		}
	}
	
	// TODO
	private boolean configFileExists() {
		return false;
	}

}
