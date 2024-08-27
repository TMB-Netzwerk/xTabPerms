package de.xenodev;

import de.xenodev.listener.ChatListener;
import de.xenodev.listener.JoinListener;
import de.xenodev.mysql.MySQL;
import de.xenodev.utils.BoardBuilder;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.rmi.server.LogStream.log;

public class xTabPerms extends JavaPlugin {

    private static xTabPerms instance;
    private static MySQL mySQL;

    private LuckPerms luckPermsAPI;

    @Override
    public void onEnable() {
        instance = this;

        if(!new File("plugins/" + getName(), "config.yml").exists()){
            saveDefaultConfig();
        }

        checkMySQL();
        loadLuckPerms();
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);

        BoardBuilder.updateScoreboard();
    }

    @Override
    public void onDisable() {

    }

    private void loadLuckPerms(){
        final Plugin luckPerms = Bukkit.getPluginManager().getPlugin("LuckPerms");

        if(luckPerms != null && luckPerms.isEnabled()){
            RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
            if (provider != null) {
                luckPermsAPI = provider.getProvider();
            }
            return;
        }

        log("§4LuckPerms konnte nicht gefunden werden...");
        log("§4Das Plugin wird nun deaktiviert.");
        Bukkit.getPluginManager().disablePlugin(this);
    }

    public LuckPerms getLuckPermsAPI() {
        return luckPermsAPI;
    }

    public static xTabPerms getInstance() {
        return instance;
    }

    public static MySQL getMySQL() {
        return mySQL;
    }

    private void checkMySQL(){
        mySQL = new MySQL(getConfig().getString("MySQL.Host"), getConfig().getString("MySQL.Database"), getConfig().getString("MySQL.Username"), getConfig().getString("MySQL.Password"));
        try (Connection connection = xTabPerms.getMySQL().dataSource.getConnection()) {
            PreparedStatement preparedStatement1 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Players(UUID VARCHAR(100),COLOR VARCHAR(25))");
            preparedStatement1.execute();

            preparedStatement1.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
