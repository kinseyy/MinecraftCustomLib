package az.ender.customlib;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class main extends JavaPlugin {

    @Override
    public void onLoad() {
        saveDefaultConfig(); // XUYETA
        new Placeholders(this).register();
        if(main.getInstance().getConfig().getString("key").equalsIgnoreCase("123456789")){
            System.out.println("Key nema");
            Bukkit.getServer().getPluginManager().disablePlugins();
        }
    }

    private static Plugin getInstance() {
        return null;
    }

    @Override
    public void onEnable() {
        if(getConfig().getBoolean("bStats", true)){
            new Metrics(this,15275);
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(args.length>0){
            if(args[0].equalsIgnoreCase("reload")){
                reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.reload","&aPlugin config reloaded.")));
            } else if(args[0].equalsIgnoreCase("version")){
                sender.sendMessage("This server is running "+ getDescription().getName() +" version "+ getDescription().getVersion()+" by "+ getDescription().getAuthors().toString().replace("[","").replace("]",""));
            }
            else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.unknown","&cUnknown command, try /clib reload.")));
            }
        } else sender.sendMessage("This server is running "+ getDescription().getName() +" version "+ getDescription().getVersion()+" by "+ getDescription().getAuthors().toString().replace("[","").replace("]",""));
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> list = new ArrayList<>();
        if(args.length>0){
            if("reload".startsWith(args[0]))
                list.add("reload");
            if("version".startsWith(args[0]))
                list.add("version");
        }
        return list;
    }
}
