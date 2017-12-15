package statemachine.states;

import gui.core.GUI;
import gui.core.SceneContainerStage;
import statemachine.core.StateMachine;
import statemachine.utils.StateName;

public class RatingState extends State {
	private StateMachine stateMachine;
	private SceneContainerStage sceneContainerStage;
	private GUI gui;
	
	public RatingState(StateMachine stateMachine, SceneContainerStage sceneContainerStage, GUI gui) {
		this.stateMachine = stateMachine;
		this.sceneContainerStage = sceneContainerStage;
		this.gui = gui;
	}

	@Override
	public void execute(StateName param) {
		sceneContainerStage.changeScene(gui.getRatingScene());

		clicksSubmit();
	}
	
	private void clicksSubmit() {
		Double score = gui.getRatingScene().getRating().getRating();
		
		// TODO Write this score to file
		
		stateMachine.setCurrentState(StateName.DASHBOARD.toString());
		stateMachine.execute(null);
	}
}
