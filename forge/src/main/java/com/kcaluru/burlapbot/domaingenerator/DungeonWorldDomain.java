package com.kcaluru.burlapbot.domaingenerator;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.RegistryNamespaced;
import burlap.oomdp.auxiliary.DomainGenerator;
import burlap.oomdp.core.Attribute;
import burlap.oomdp.core.Attribute.AttributeType;
import burlap.oomdp.core.Domain;
import burlap.oomdp.core.ObjectClass;
import burlap.oomdp.core.ObjectInstance;
import burlap.oomdp.core.PropositionalFunction;
import burlap.oomdp.core.State;
import burlap.oomdp.core.TerminalFunction;
import burlap.oomdp.core.TransitionProbability;
import burlap.oomdp.singleagent.Action;
import burlap.oomdp.singleagent.GroundedAction;
import burlap.oomdp.singleagent.RewardFunction;
import burlap.oomdp.singleagent.SADomain;
import burlap.oomdp.singleagent.explorer.TerminalExplorer;

import com.kcaluru.burlapbot.actions.MovementAction;
import com.kcaluru.burlapbot.actions.RotateAction;
import com.kcaluru.burlapbot.actions.RotateVertAction;
import com.kcaluru.burlapbot.helpers.BurlapAIHelper;
import com.kcaluru.burlapbot.helpers.NameSpace;

import cpw.mods.fml.common.registry.GameData;

/**
 * Class to generate burlap domain for minecraft
 * @author Krishna Aluru
 *
 */

public class DungeonWorldDomain implements DomainGenerator {
		
	protected int length;
	protected int width;
	protected int height;
	
	public DungeonWorldDomain(int length, int width, int height) {
		
		this.length = length;
		this.width = width;
		this.height = height;
		
	}
	
	@Override
	public Domain generateDomain() {
		
		Domain domain = new SADomain();
		
		// Attributes
		// x-position attribute
		Attribute xAtt = new Attribute(domain, NameSpace.ATX, AttributeType.INT);
		xAtt.setLims(0, this.length - 1);
		// y-position attribute
		Attribute yAtt = new Attribute(domain, NameSpace.ATY, AttributeType.INT);
		yAtt.setLims(0, this.height - 1);
		// z-position attribute
		Attribute zAtt = new Attribute(domain, NameSpace.ATZ, AttributeType.INT);
		zAtt.setLims(0, this.width - 1);
		//Rotational direction for agent
		Attribute rotDirAt = new Attribute(domain, NameSpace.ATROTDIR, Attribute.AttributeType.DISC);
		rotDirAt.setDiscValuesForRange(0, NameSpace.RotDirection.size - 1, 1);
		//Agent's vertical direction attribute
		Attribute vertDirAt = new Attribute(domain, NameSpace.ATVERTDIR, Attribute.AttributeType.DISC);
		vertDirAt.setDiscValuesForRange(0, 1, 1);
		// block broken attribute
//		Attribute isBroken = new Attribute(domain, NameSpace.ISBROKEN, AttributeType.BOOLEAN);
		
		// Object classes
		// agent
		ObjectClass agentClass = new ObjectClass(domain, NameSpace.CLASSAGENT);
		agentClass.addAttribute(xAtt);
		agentClass.addAttribute(yAtt);
		agentClass.addAttribute(zAtt);
		agentClass.addAttribute(rotDirAt);
		agentClass.addAttribute(vertDirAt);
		// blocks
//		ObjectClass blockClass = new ObjectClass(domain, NameSpace.CLASSBLOCK);
//		blockClass.addAttribute(xAtt);
//		blockClass.addAttribute(yAtt);
//		blockClass.addAttribute(zAtt);
//		blockClass.addAttribute(isBroken);
		
		
		// Actions
		new MovementAction(NameSpace.ACTIONMOVE, domain);
		new RotateAction(NameSpace.ACTIONROTATESOUTH, domain, 0);
		new RotateAction(NameSpace.ACTIONROTATEWEST, domain, 1);
		new RotateAction(NameSpace.ACTIONROTATENORTH, domain, 2);
		new RotateAction(NameSpace.ACTIONROTATEEAST, domain, 3);
		new RotateVertAction(NameSpace.ACTIONAHEAD, domain, 0);
		new RotateVertAction(NameSpace.ACTIONDOWNONE, domain, 1);
//		new RotateVertAction(NameSpace.ACTIONDOWNTWO, domain, 2);
//		new RotateVertAction(NameSpace.ACTIONDOWNTHREE, domain, 3);
		
		return domain;
		
	}
	
//	public static State getInitialState(Domain domain, int startX, int startZ, int destX, int destZ) {
//		State s = new State();
//		ObjectInstance agent = new ObjectInstance(domain.getObjectClass(NameSpace.CLASSAGENT), "agent0");
//		agent.setValue(NameSpace.ATX, startX);
//		agent.setValue(NameSpace.ATY, 1);
//		agent.setValue(NameSpace.ATZ, startZ);
//		
//		s.addObject(agent);
//		
//		return s;
//	}
	
//	public class MovementAction extends Action {
//
//		/**
//		 * Probabilities of the actual direction the agent will go
//		 * 0: south, 1: west, 2: north, 3: east 
//		 */
//		protected double [] directionProbs = new double[4];
//		
//		public MovementAction(String actionName, Domain domain, int direction) {
//			super(actionName, domain, "");
//			for(int i = 0; i < 4; i++) {
//				if (i == direction) {
//					directionProbs[i] = 1.0;
//				}
//				else {
//					directionProbs[i] = 0;
//				}
//			}
//		}
//		@Override
//		protected State performActionHelper(State s, String[] params) {
//			ObjectInstance agent = s.getFirstObjectOfClass(NameSpace.CLASSAGENT);
//			int curX = agent.getDiscValForAttribute(NameSpace.ATX);
//			int curZ = agent.getDiscValForAttribute(NameSpace.ATZ);
//			
//			//sample directon with random roll
//			double r = Math.random();
//			double sumProb = 0.;
//			int dir = 0;
//			for(int i = 0; i < this.directionProbs.length; i++){
//				sumProb += this.directionProbs[i];
//				if(r < sumProb){
//					dir = i;
//					break; //found direction
//				}
//			}
//			
//			//get resulting position
//			int [] newPos = this.moveResult(curX, curZ, dir);
//			
//			//set the new position
//			agent.setValue(NameSpace.ATX, newPos[0]);
//			agent.setValue(NameSpace.ATY, 1);
//			agent.setValue(NameSpace.ATZ, newPos[1]);
//			
//			//return the state we just modified
//			return s;
//		}
//		
//		protected int [] moveResult(int curX, int curZ, int direction) {
//			int xdelta = 0;
//			int zdelta = 0;
//			if(direction == 0){
//				zdelta = 1;
//			}
//			else if(direction == 1){
//				xdelta = -1;
//			}
//			else if(direction == 2){
//				zdelta = -1;
//			}
//			else{
//				xdelta = 1;
//			}
//			
//			int nx = curX + xdelta;
//			int nz = curZ + zdelta;
//			
//			int length = DungeonWorldDomain.this.movementMap.length;
//			int width = DungeonWorldDomain.this.movementMap[0].length;
//			
//			//make sure new position is valid (not a wall or out of bounds)
//			if(nx < 0 || nx >= length || nz < 0 || nz >= width ||  
//				DungeonWorldDomain.this.movementMap[nx][nz] >= 1){
//				nx = curX;
//				nz = curZ;
//			}
//				
//			
//			return new int[]{nx,nz};
//		}
//		
//		@Override
//		public List<TransitionProbability> getTransitions(State s, String [] params){
//			
//			//get agent and current position
//			ObjectInstance agent = s.getFirstObjectOfClass(NameSpace.CLASSAGENT);
//			int curX = agent.getDiscValForAttribute(NameSpace.ATX);
//			int curZ = agent.getDiscValForAttribute(NameSpace.ATZ);
//			
//			List<TransitionProbability> tps = new ArrayList<TransitionProbability>(4);
//			TransitionProbability noChangeTransition = null;
//			for(int i = 0; i < this.directionProbs.length; i++){
//				int [] newPos = this.moveResult(curX, curZ, i);
//				if(newPos[0] != curX || newPos[1] != curZ) {
//					//new possible outcome
//					State ns = s.copy();
//					ObjectInstance nagent = ns.getFirstObjectOfClass(NameSpace.CLASSAGENT);
//					nagent.setValue(NameSpace.ATX, newPos[0]);
//					nagent.setValue(NameSpace.ATZ, newPos[1]);
//					
//					//create transition probability object and add to our list of outcomes
//					tps.add(new TransitionProbability(ns, this.directionProbs[i]));
//				}
//				else{
//					//this direction didn't lead anywhere new
//					//if there are existing possible directions that wouldn't lead anywhere, 
//					//aggregate with them
//					if(noChangeTransition != null){
//						noChangeTransition.p += this.directionProbs[i];
//					}
//					else{
//						//otherwise create this new state outcome
//						noChangeTransition = new TransitionProbability(s.copy(), this.directionProbs[i]);
//						tps.add(noChangeTransition);
//					}
//				}
//			}
//			
//			
//			return tps;
//		}
//		
//	}	

}
