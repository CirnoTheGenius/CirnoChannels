package toshir0z.Cirno;

import java.util.ArrayList;

import org.bukkit.ChatColor;

public class Channel {

	String name = "";
	
	Nineball core;
	
	ArrayList<String> Chatters = new ArrayList<String>();
	ArrayList<String> Admins = new ArrayList<String>();
	
	public Channel(String s, Nineball n){
		name = s;
		core = n;
	}
	
	private boolean isCommonName(String p, ArrayList<String> al){
		short i = 0;
		for(String s : al){
			if(s.startsWith(p)){
				i++;
			}
		}
		if(i > 1){
			return true;
		}
		return false;
	}
	
	public void addPlayer(String p){
		Chatters.add(core.getServer().getPlayer(p).getName());
		announce(p + " has joined the channel!", ChatColor.YELLOW);
	}
	
	public void removePlayer(String p){
		if(isCommonName(p, Chatters)){
			announceAdmins("Failed to kick player \"" + p + "\"! Reason: Multiple players exist with that name!", ChatColor.RED);
			return;
		}
		
		String playerName = core.getServer().getPlayer(p).getName();
		
		if(!Chatters.contains(playerName)){
			announceAdmins("Failed to kick player \"" + p + "\"! Reason: Player \"" + p + "\" not exist!", ChatColor.RED);
			return;
		}
		Chatters.remove(playerName);
		announce(p + " has been kicked from the channel!", ChatColor.YELLOW);
	}
	
	public void adminPlayer(String p){
		if(isCommonName(p, Chatters)) {
			announceAdmins("Failed to admin player \"" + p + "\"! Reason: Multiple players exist with that name!", ChatColor.RED);
			return;
		}
		
		String playerName = core.getServer().getPlayer(p).getName();
		
		if(!Chatters.contains(playerName)){
			announceAdmins("Failed to admin player \"" + p + "\"! Reason: Player \"" + p + "\" is not in the channel!", ChatColor.RED);
			return;
		} else if(Admins.contains(playerName)){
			announceAdmins("Failed to admin player \"" + p + "\"! Reason: Player already an admin!", ChatColor.RED);
			return;
		}
		Admins.add(core.getServer().getPlayer(p).getName());
		announceAdmins("Player \"" + p + "\" is now an admin!", ChatColor.GREEN);
	}
	
	public void announce(String m, ChatColor c){
		for(String s : Chatters){
			core.getServer().getPlayer(s).sendMessage(c == null || c.getClass() != ChatColor.class ? "" : c + "[Channel: " + name + "] " + m);
		}
	}
	
	public void announceAdmins(String m, ChatColor c){
		for(String s : Admins){
			core.getServer().getPlayer(s).sendMessage(c == null || c.getClass() != ChatColor.class ? "" : c + "[Channel: " + name + "] " + m);
		}
	}
	
}
