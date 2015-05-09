package com.kcaluru.burlapbot.actions;

import com.kcaluru.burlapbot.helpers.BurlapAIHelper;
import com.kcaluru.burlapbot.helpers.NameSpace;
import com.kcaluru.burlapbot.helpers.Pos;

import burlap.oomdp.core.Domain;
import burlap.oomdp.core.ObjectInstance;
import burlap.oomdp.core.State;
import burlap.oomdp.singleagent.SADomain;

public class MovementAction extends AgentAction {

	public MovementAction(String name, Domain domain) {
		
		super(name, domain);
		
	}

	@Override
	void doAction(State state) {
		
		BurlapAIHelper.moveForward(false);
		
	}

}