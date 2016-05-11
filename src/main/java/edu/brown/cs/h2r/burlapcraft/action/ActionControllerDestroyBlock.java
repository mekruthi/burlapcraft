package edu.brown.cs.h2r.burlapcraft.action;

import burlap.mdp.singleagent.GroundedAction;
import burlap.mdp.singleagent.environment.Environment;
import edu.brown.cs.h2r.burlapcraft.helper.HelperActions;


public class ActionControllerDestroyBlock implements ActionController {

	protected int delayMS;
	protected Environment environment;
	
	public ActionControllerDestroyBlock(int delayMS, Environment e) {
		this.delayMS = delayMS;
		this.environment = e;
	}
	
	@Override
	public int executeAction(GroundedAction ga) {
		System.out.println("Destroy Block");
		HelperActions.destroyBlock();
		
		return this.delayMS;
	}

}
