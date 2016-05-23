package edu.brown.cs.h2r.burlapcraft.command;

import burlap.mdp.core.Domain;
import burlap.mdp.core.state.State;
import edu.brown.cs.h2r.burlapcraft.BurlapCraft;
import edu.brown.cs.h2r.burlapcraft.domaingenerator.MinecraftDomainGenerator;
import edu.brown.cs.h2r.burlapcraft.stategenerator.StateGenerator;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandCheckMap implements ICommand {

	List aliases = new ArrayList();
	Domain domain;

	public CommandCheckMap() {
		this.aliases.add("checkMap");
		MinecraftDomainGenerator mdg = new MinecraftDomainGenerator();
		domain = mdg.generateDomain();
	}

	@Override
	public String getCommandName() {
		return "checkMap";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "checkMap";
	}

	@Override
	public List getCommandAliases() {
		return aliases;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		State s = StateGenerator.getCurrentState(domain, BurlapCraft.currentDungeon);
		System.out.println(s.toString());
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		return false;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
}
