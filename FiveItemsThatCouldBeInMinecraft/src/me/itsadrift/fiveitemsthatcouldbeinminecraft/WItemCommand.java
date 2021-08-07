package me.itsadrift.fiveitemsthatcouldbeinminecraft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("ALL")) {
                    player.getInventory().addItem(new Weapon("THORS_HAMMER").getItem());
                    player.getInventory().addItem(new Weapon("CAPTAIN_AMERICAS_SHIELD").getItem());
                    player.getInventory().addItem(new Weapon("CURSED_BLADE").getItem());
                    player.getInventory().addItem(new Weapon("REAPERS_SCYTHE").getItem());
                    player.getInventory().addItem(new Weapon("BALLOON_SWORD").getItem());
                } else if (!new Weapon(args[0].toUpperCase()).getId().equals("NONE")) {
                    player.getInventory().addItem(new Weapon(args[0].toUpperCase()).getItem());
                }
            } else if (args.length == 2) {

            }

        }

        return false;
    }
}
