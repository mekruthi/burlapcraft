package edu.brown.cs.h2r.burlapcraft.action;

import burlap.mdp.core.Domain;
import burlap.mdp.core.oo.state.OOState;
import burlap.mdp.core.oo.state.ObjectInstance;
import burlap.mdp.core.oo.state.generic.GenericOOState;
import burlap.mdp.core.state.State;
import burlap.mdp.singleagent.GroundedAction;
import burlap.mdp.singleagent.common.SimpleAction;
import edu.brown.cs.h2r.burlapcraft.helper.HelperActions;
import edu.brown.cs.h2r.burlapcraft.helper.HelperNameSpace;
import edu.brown.cs.h2r.burlapcraft.stategenerator.BCAgent;
import edu.brown.cs.h2r.burlapcraft.stategenerator.BCBlock;
import net.minecraft.block.Block;

import java.util.List;

import static edu.brown.cs.h2r.burlapcraft.helper.HelperNameSpace.CLASS_AGENT;

public class ActionChangePitchSimulated extends SimpleAction.SimpleDeterministicAction {

	private int vertDirection;
	
	public ActionChangePitchSimulated(String name, Domain domain, int rotateVertDirection) {
		super(name, domain);
		this.vertDirection = rotateVertDirection;
	}
	
	@Override
	protected State sampleHelper(State s, GroundedAction groundedAction) {

		GenericOOState gs = (GenericOOState)s;

		//get agent and current position
		BCAgent agent = (BCAgent)gs.touch(CLASS_AGENT);
		
		//set agent's vert dir
		agent.vdir = this.vertDirection;
		
		//return the state we just modified
		return s;
	}
	
	@Override
	public boolean applicableInState(State s, GroundedAction groundedAction) {
		BCAgent a = (BCAgent)((GenericOOState)s).object(CLASS_AGENT);

		List<ObjectInstance> blocks = ((OOState)s).objectsOfClass(HelperNameSpace.CLASS_BLOCK);
		for (ObjectInstance block : blocks) {
			if (HelperActions.blockIsOneOf(Block.getBlockById(((BCBlock)block).type), HelperActions.dangerBlocks)) {
				int dangerX = ((BCBlock)block).x;
				int dangerY = ((BCBlock)block).y;
				int dangerZ = ((BCBlock)block).z;
				if ((a.x == dangerX) && (a.y - 1 == dangerY) && (a.z == dangerZ) || (a.x == dangerX) && (a.y == dangerY) && (a.z == dangerZ)) {
					return false;
				}
			}
		}
		return true;
	}

}
