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
	
	public void addPlayer(String p){
		Chatters.add(p);
		announce(p + " has joined the channel!", ChatColor.YELLOW);
	}
	
	public void removePlayer(String p){
		short i = 0;
		for(String s : Chatters){
			if(s.startsWith(p)){
				i++;
			}
		}
		if(i > 1){
			announceAdmins("Failed to kick player \"" + p + "\"!", ChatColor.RED);
		}
		announce(p + " has joined the channel!", ChatColor.YELLOW);
	}
	
	public void announce(String m, ChatColor c){
		for(String s : Chatters){
			core.getServer().getPlayer(s).sendMessage(c.getClass() != ChatColor.class ? "" : c + "[Channel: " + name + "] " + m);
		}
	}
	
	public void announceAdmins(String m, ChatColor c){
		for(String s : Admins){
			core.getServer().getPlayer(s).sendMessage(c.getClass() != ChatColor.class ? "" : c + "[Channel: " + name + "] " + m);
		}
	}
	
}
