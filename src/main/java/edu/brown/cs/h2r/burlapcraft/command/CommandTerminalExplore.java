package edu.brown.cs.h2r.burlapcraft.command;

import java.util.ArrayList;
import java.util.List;

import burlap.oomdp.core.Domain;
import burlap.oomdp.singleagent.explorer.TerminalExplorer;
import edu.brown.cs.h2r.burlapcraft.BurlapCraft;
import edu.brown.cs.h2r.burlapcraft.domaingenerator.DomainGeneratorReal;
import edu.brown.cs.h2r.burlapcraft.domaingenerator.DomainGeneratorSimulated;
import edu.brown.cs.h2r.burlapcraft.stategenerator.StateGenerator;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

public class CommandTerminalExplore implements ICommand {

	private final List aliases;
	Domain domain;
	
	public CommandTerminalExplore() {
		aliases = new ArrayList();
		aliases.add(getCommandName());
	}
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandName() {
		return "terminalExplore";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "terminalExplore";
	}

	@Override
	public List getCommandAliases() {
		return aliases;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		
		DomainGeneratorSimulated sdg = new DomainGeneratorSimulated(StateGenerator.getMap(BurlapCraft.currentDungeon));
		domain = sdg.generateDomain();
		
		TerminalExplorer exp = new TerminalExplorer(domain);
		exp.exploreFromState(StateGenerator.getCurrentState(domain, BurlapCraft.currentDungeon));
		
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_,
			String[] p_71516_2_) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		return false;
	}
	
}