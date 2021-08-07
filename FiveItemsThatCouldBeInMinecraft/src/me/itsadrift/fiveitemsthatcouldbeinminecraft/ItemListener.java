package me.itsadrift.fiveitemsthatcouldbeinminecraft;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class ItemListener implements Listener {

    List<Integer> struckByLightning = new ArrayList<>();

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getDamager().getType() == EntityType.PLAYER) {
            Player damager = (Player) e.getDamager();
            World world = damager.getWorld();

            if (e.getEntity().getType() == EntityType.PLAYER) {
                Player en = (Player) e.getEntity();
                if (en.isBlocking()) {
                    if (en.getInventory().getItemInOffHand().getType() != Material.AIR) {
                        Weapon w = new Weapon(en.getInventory().getItemInOffHand());
                        if (w.getId().equals("CAPTAIN_AMERICAS_SHIELD")) {
                            e.setCancelled(true);
                        }
                    }

                    if (en.getInventory().getItemInMainHand().getType() != Material.AIR) {
                        Weapon w = new Weapon(en.getInventory().getItemInMainHand());
                        if (w.getId().equals("CAPTAIN_AMERICAS_SHIELD")) {
                            e.setCancelled(true);
                        }
                    }

                }
            }

            if (damager.getInventory().getItemInMainHand() != null && damager.getInventory().getItemInMainHand().getType() != Material.AIR) {
                ItemStack i = damager.getInventory().getItemInMainHand();
                if (!new Weapon(i).getId().equals("NONE")) {
                    Weapon w = new Weapon(i);

                    if (w.getId().equals("THORS_HAMMER")) {
                        e.setDamage(6);
                        world.strikeLightningEffect(e.getEntity().getLocation());
                    } else if (w.getId().equals("CURSED_BLADE")) {
                        e.setDamage(8);
                        if (e.getEntity() instanceof LivingEntity) {
                            ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 2, true));
                        }
                        damager.damage(4);
                    } else if (w.getId().equals("REAPERS_SCYTHE")) {
                        if (e.getEntity() instanceof LivingEntity) {
                            e.setDamage(1000);
                        }

                        ItemMeta im = i.getItemMeta();
                        Damageable a = (Damageable) im;
                        a.setDamage(a.getDamage() + 2);
                        i.setItemMeta(im);

                        if (a.getDamage() >= 58) {
                            damager.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                            damager.setHealth(0);
                        } else {
                            damager.getInventory().setItemInMainHand(i);
                        }

                    }
                }

            }
        }
    }

    @EventHandler
    public void onItemBreak(PlayerItemBreakEvent e) {
        Player player = e.getPlayer();
        ItemStack i = e.getBrokenItem();
        if (!new Weapon(i).getId().equals("NONE")) {
            Weapon w = new Weapon(i);

            if (w.getId().equals("REAPERS_SCYTHE")) {
                player.setHealth(0);
            } else if (w.getId().equals("BALLOON_SWORD")) {
                for (Entity en : player.getNearbyEntities(10, 10, 10)) {
                    Vector v = new Vector(0, 1, 0);
                    v.subtract(en.getFacing().getDirection()).subtract(en.getFacing().getDirection());

                    en.setVelocity(v);
                }
            }
        }
    }

}
