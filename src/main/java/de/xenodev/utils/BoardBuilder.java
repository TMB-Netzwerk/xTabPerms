package de.xenodev.utils;

import de.xenodev.mysql.PlayersAPI;
import de.xenodev.xTabPerms;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class BoardBuilder {

    private static Team team;

    public static void createBoard(Player player) {
        var manager = player.getServer().getScoreboardManager();
        if (manager != null && player.getScoreboard().equals(manager.getMainScoreboard())) {
            player.setScoreboard(manager.getNewScoreboard());
        }
        Scoreboard scoreboard = player.getScoreboard();

        player.setScoreboard(scoreboard);
        setTabList(player);
    }

    public static void updateScoreboard() {
        new BukkitRunnable(){

            @Override
            public void run() {
                for(final Player players : Bukkit.getOnlinePlayers()){
                    setTabList(players);
                }
            }
        }.runTaskTimerAsynchronously(xTabPerms.getInstance(), 0, 20L*10);
    }

    public static void setTabList(final Player p) {
        for (final Player p2 : Bukkit.getOnlinePlayers()) {
            User user = xTabPerms.getInstance().getLuckPermsAPI().getUserManager().getUser(p2.getUniqueId());
            Group group = xTabPerms.getInstance().getLuckPermsAPI().getGroupManager().getGroup(user.getPrimaryGroup());
            String prefix = group.getCachedData().getMetaData().getPrefix().replace("&", "§");
            String pn = p2.getName();
            final String getGroups = getGroup(p2);
            pn = pn.substring(0, Math.min(pn.length(), 12));
            try {
                if (p.getScoreboard().getTeam(getGroups.toString() + pn) == null) {
                    team = p.getScoreboard().registerNewTeam(getWeight(getGroups).toString() + pn);
                }
            }
            catch (Exception e1) {
                team = p.getScoreboard().getTeam(getWeight(getGroups).toString() + pn);
            }
            team.addPlayer((OfflinePlayer)p2);
            team.setColor(ChatColor.valueOf(PlayersAPI.getColor(p2.getUniqueId())));
            team.setPrefix(String.valueOf(String.valueOf(ChatColor.translateAlternateColorCodes('&', prefix))) + " §8| ");
        }
    }

    public static String getGroup(final Player player) {
        final User user = xTabPerms.getInstance().getLuckPermsAPI().getUserManager().getUser(player.getUniqueId());
        final String group = user.getPrimaryGroup();
        return group;
    }

    public static String getWeight(final String g) {
        final int w = xTabPerms.getInstance().getLuckPermsAPI().getGroupManager().getGroup(g).getWeight().getAsInt();
        String we = Integer.toString(w);
        we = String.valueOf(String.valueOf(we)) + "0000000";
        String wee = "";
        if (we.charAt(0) == '1') {
            wee = String.valueOf(String.valueOf(wee)) + "i";
        }
        if (we.charAt(0) == '2') {
            wee = String.valueOf(String.valueOf(wee)) + "h";
        }
        if (we.charAt(0) == '3') {
            wee = String.valueOf(String.valueOf(wee)) + "g";
        }
        if (we.charAt(0) == '4') {
            wee = String.valueOf(String.valueOf(wee)) + "f";
        }
        if (we.charAt(0) == '5') {
            wee = String.valueOf(String.valueOf(wee)) + "e";
        }
        if (we.charAt(0) == '6') {
            wee = String.valueOf(String.valueOf(wee)) + "d";
        }
        if (we.charAt(0) == '7') {
            wee = String.valueOf(String.valueOf(wee)) + "c";
        }
        if (we.charAt(0) == '8') {
            wee = String.valueOf(String.valueOf(wee)) + "b";
        }
        if (we.charAt(0) == '9') {
            wee = String.valueOf(String.valueOf(wee)) + "a";
        }
        if (we.charAt(0) == '0') {
            wee = String.valueOf(String.valueOf(wee)) + "j";
        }
        if (we.charAt(1) == '1') {
            wee = String.valueOf(String.valueOf(wee)) + "i";
        }
        if (we.charAt(1) == '2') {
            wee = String.valueOf(String.valueOf(wee)) + "h";
        }
        if (we.charAt(1) == '3') {
            wee = String.valueOf(String.valueOf(wee)) + "g";
        }
        if (we.charAt(1) == '4') {
            wee = String.valueOf(String.valueOf(wee)) + "f";
        }
        if (we.charAt(1) == '5') {
            wee = String.valueOf(String.valueOf(wee)) + "e";
        }
        if (we.charAt(1) == '6') {
            wee = String.valueOf(String.valueOf(wee)) + "d";
        }
        if (we.charAt(1) == '7') {
            wee = String.valueOf(String.valueOf(wee)) + "c";
        }
        if (we.charAt(1) == '8') {
            wee = String.valueOf(String.valueOf(wee)) + "b";
        }
        if (we.charAt(1) == '9') {
            wee = String.valueOf(String.valueOf(wee)) + "a";
        }
        if (we.charAt(1) == '0') {
            wee = String.valueOf(String.valueOf(wee)) + "j";
        }
        if (we.charAt(2) == '1') {
            wee = String.valueOf(String.valueOf(wee)) + "i";
        }
        if (we.charAt(2) == '2') {
            wee = String.valueOf(String.valueOf(wee)) + "h";
        }
        if (we.charAt(2) == '3') {
            wee = String.valueOf(String.valueOf(wee)) + "g";
        }
        if (we.charAt(2) == '4') {
            wee = String.valueOf(String.valueOf(wee)) + "f";
        }
        if (we.charAt(2) == '5') {
            wee = String.valueOf(String.valueOf(wee)) + "e";
        }
        if (we.charAt(2) == '6') {
            wee = String.valueOf(String.valueOf(wee)) + "d";
        }
        if (we.charAt(2) == '7') {
            wee = String.valueOf(String.valueOf(wee)) + "c";
        }
        if (we.charAt(2) == '8') {
            wee = String.valueOf(String.valueOf(wee)) + "b";
        }
        if (we.charAt(2) == '9') {
            wee = String.valueOf(String.valueOf(wee)) + "a";
        }
        if (we.charAt(2) == '0') {
            wee = String.valueOf(String.valueOf(wee)) + "j";
        }
        if (we.charAt(3) == '1') {
            wee = String.valueOf(String.valueOf(wee)) + "i";
        }
        if (we.charAt(3) == '2') {
            wee = String.valueOf(String.valueOf(wee)) + "h";
        }
        if (we.charAt(3) == '3') {
            wee = String.valueOf(String.valueOf(wee)) + "g";
        }
        if (we.charAt(3) == '4') {
            wee = String.valueOf(String.valueOf(wee)) + "f";
        }
        if (we.charAt(3) == '5') {
            wee = String.valueOf(String.valueOf(wee)) + "e";
        }
        if (we.charAt(3) == '6') {
            wee = String.valueOf(String.valueOf(wee)) + "d";
        }
        if (we.charAt(3) == '7') {
            wee = String.valueOf(String.valueOf(wee)) + "c";
        }
        if (we.charAt(3) == '8') {
            wee = String.valueOf(String.valueOf(wee)) + "b";
        }
        if (we.charAt(3) == '9') {
            wee = String.valueOf(String.valueOf(wee)) + "a";
        }
        if (we.charAt(3) == '0') {
            wee = String.valueOf(String.valueOf(wee)) + "j";
        }
        return wee;
    }
}
