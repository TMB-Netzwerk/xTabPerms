package de.xenodev.listener;

import de.xenodev.mysql.PlayersAPI;
import de.xenodev.xTabPerms;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Team;

public class ChatListener implements Listener {

    @EventHandler
    public void handlePlayerChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();

        User user = xTabPerms.getInstance().getLuckPermsAPI().getUserManager().getUser(player.getUniqueId());
        Group group = xTabPerms.getInstance().getLuckPermsAPI().getGroupManager().getGroup(user.getPrimaryGroup());
        String prefix = group.getCachedData().getMetaData().getPrefix().replace("&", "§");

        if(player.hasPermission("tmb.chat.color")){
            event.setFormat(prefix + " §8| " + ChatColor.valueOf(PlayersAPI.getColor(player.getUniqueId())) + player.getName() + " §8➥ §f" + event.getMessage().replace("&", "§").replace("%", "%%"));
        }else{
            event.setFormat(prefix + " §8| " + ChatColor.valueOf(PlayersAPI.getColor(player.getUniqueId())) + player.getName() + " §8➥ §f" + event.getMessage());
        }

        if(event.getMessage().equalsIgnoreCase("TEAM")){
            event.setCancelled(true);
            for(Team team : player.getScoreboard().getTeams()){
                player.sendMessage("TEAM: " + team.getName());
            }
        }
    }

}
