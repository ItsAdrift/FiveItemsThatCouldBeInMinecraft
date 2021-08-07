package me.itsadrift.fiveitemsthatcouldbeinminecraft;

import net.minecraft.server.v1_16_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftItem;
import org.bukkit.craftbukkit.v1_16_R1.inventory.CraftItemStack;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class Weapon {

    private String id = "NONE";

    public Weapon(String id) {
        this.id = id;
    }

    public Weapon(ItemStack item) {
        if (item.getItemMeta().getPersistentDataContainer().has(WeaponKeys.THORS_HAMMER_KEY, PersistentDataType.STRING)) {
            id = "THORS_HAMMER";
        } else if (item.getItemMeta().getPersistentDataContainer().has(WeaponKeys.CAPTAIN_AMERICAS_SHIELD_KEY, PersistentDataType.STRING)) {
            id = "CAPTAIN_AMERICAS_SHIELD";
        } else if (item.getItemMeta().getPersistentDataContainer().has(WeaponKeys.CURSED_BLADE_KEY, PersistentDataType.STRING)) {
            id = "CURSED_BLADE";
        } else if (item.getItemMeta().getPersistentDataContainer().has(WeaponKeys.REAPERS_SCYTHE_KEY, PersistentDataType.STRING)) {
            id = "REAPERS_SCYTHE";
        } else if (item.getItemMeta().getPersistentDataContainer().has(WeaponKeys.BALLOON_SWORD_KEY, PersistentDataType.STRING)) {
            id = "BALLOON_SWORD";
        } else {
            id = "NONE";
        }
    }

    public ItemStack getItem() {
        if (id.equalsIgnoreCase("THORS_HAMMER")) {
            ItemStack i = new ItemStack(Material.IRON_AXE);
            ItemMeta im = i.getItemMeta();
            im.getPersistentDataContainer().set(WeaponKeys.THORS_HAMMER_KEY, PersistentDataType.STRING, "THORS_HAMMER");
            im.setDisplayName(colour("&e&l⚡ &4&lThor's Hammer &e&l⚡"));

            AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
            im.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);

            i.setItemMeta(im);

            return i;
        } else if (id.equalsIgnoreCase("CAPTAIN_AMERICAS_SHIELD")) {
            ItemStack i = new ItemStack(Material.SHIELD);
            ItemMeta im = i.getItemMeta();
            im.getPersistentDataContainer().set(WeaponKeys.CAPTAIN_AMERICAS_SHIELD_KEY, PersistentDataType.STRING, "CAPTAIN_AMERICAS_SHIELD");
            im.setDisplayName(colour("&f&l✯ &c&lCaptain&r &f&lAmerica's&r &b&lShield&r &f&l✯"));

            i.setItemMeta(im);
            return i;
        } else if (id.equalsIgnoreCase("CURSED_BLADE")) {
            ItemStack i = new ItemStack(Material.IRON_SWORD);
            ItemMeta im = i.getItemMeta();
            im.getPersistentDataContainer().set(WeaponKeys.CURSED_BLADE_KEY, PersistentDataType.STRING, "CURSED_BLADE");
            im.setDisplayName(colour("&5&lThe Cursed Blade"));

            i.setItemMeta(im);
            return i;
        } else if (id.equalsIgnoreCase("REAPERS_SCYTHE")) {
            ItemStack i = new ItemStack(Material.WOODEN_HOE);
            ItemMeta im = i.getItemMeta();
            im.getPersistentDataContainer().set(WeaponKeys.REAPERS_SCYTHE_KEY, PersistentDataType.STRING, "REAPERS_SCYTHE");
            im.setDisplayName(colour("&2☠ &2&lREAPERS SCYTHE &2☠"));

            Damageable a = (Damageable) im;
            a.setDamage(38);

            i.setItemMeta(im);

            return i;
        } else if (id.equalsIgnoreCase("BALLOON_SWORD")) {
            ItemStack i = new ItemStack(Material.GOLDEN_SWORD);
            ItemMeta im = i.getItemMeta();
            im.getPersistentDataContainer().set(WeaponKeys.BALLOON_SWORD_KEY, PersistentDataType.STRING, "BALLOON_SWORD");
            im.setDisplayName(colour("&e&lBalloon &b&lSword"));

            Damageable a = (Damageable) im;
            a.setDamage(22);

            i.setItemMeta(im);
            return i;
        }

        return new ItemStack(Material.AIR);

    }

    public String getId() {
        return id;
    }

    private String colour(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
