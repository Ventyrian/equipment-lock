package com.equipmentenforcer;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemEquipmentStats;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.ItemStats;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@PluginDescriptor(
	name = "Equipment Enforcer"
)
public class EquipmentEnforcerPlugin extends Plugin
{
	@Inject
	private Client client;

    @Inject
    private ItemManager itemManager;

	@Inject
	private EquipmentEnforcerConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.debug("Equipment Lock started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.debug("Equipment Lock stopped!");
	}

    @Subscribe
    public void onMenuEntryAdded(MenuEntryAdded event)
    {

        // Make sure event is happening inside UI element like inventory, and it also has an itemID associated with it
        if (event.getType() != MenuAction.CC_OP.getId() || event.getItemId() == -1)
        {
            return;
        }

        // Check the text action after we know it's a valid item
        String option = Text.removeTags(event.getOption()).toLowerCase();
        boolean isEquipAction = option.equals("equip") || option.equals("wield") || option.equals("wear");

        if (isEquipAction)
        {
            // Get Item ID from the menu entry
            int itemId = event.getMenuEntry().getItemId();
            // Look up the item's stats
            ItemStats itemStats = itemManager.getItemStats(itemId);

            // If it is not a valid equipable item, remove the equip option from the menu and have "examine" as the left click option
            if (!isValidEquip(itemStats))
            {
                rewriteMenu(event.getMenuEntry());
            }
        }
    }


	@Provides
    EquipmentEnforcerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(EquipmentEnforcerConfig.class);
	}

    // Checks all stats defined in config. Returns true if the item meets all requirements (or if requirements are 0). Returns false if item fails any check
    private boolean isValidEquip(ItemStats itemStats)
    {
        if (itemStats == null || !itemStats.isEquipable())
        {
            return true;
        }

        ItemEquipmentStats equipmentStats = itemStats.getEquipment();
        if (equipmentStats == null)
        {
            return true;
        }

        // Attack Bonuses
        if (failsCheck(config.attackCrush(), equipmentStats.getAcrush()))
        {
            log.info("attackCrush failed check");
            return false;
        }
        if (failsCheck(config.attackMagic(), equipmentStats.getAmagic()))
        {
            log.info("attackMagic failed check");
            return false;
        }
        if (failsCheck(config.attackRanged(), equipmentStats.getArange()))
        {
            log.info("attackRanged failed check");
            return false;
        }
        if (failsCheck(config.attackSlash(), equipmentStats.getAslash()))
        {
            log.info("attackSlash failed check");
            return false;
        }
        if (failsCheck(config.attackSpeed(), equipmentStats.getAspeed()))
        {
            log.info("attackSpeed failed check");
            return false;
        }
        if (failsCheck(config.attackStab(), equipmentStats.getAstab()))
        {
            log.info("attackStab failed check");
            return false;
        }

        // Defence Bonuses
        if (failsCheck(config.defenceCrush(), equipmentStats.getDcrush()))
        {
            log.info("defenceCrush failed check");
            return false;
        }
        if (failsCheck(config.defenceMagic(), equipmentStats.getDmagic()))
        {
            log.info("defenceMagic failed check");
            return false;
        }
        if (failsCheck(config.defenceRanged(), equipmentStats.getDrange()))
        {
            log.info("defenceRanged failed check");
            return false;
        }
        if (failsCheck(config.defenceSlash(), equipmentStats.getDslash()))
        {
            log.info("defenceSlash failed check");
            return false;
        }
        if (failsCheck(config.defenceStab(), equipmentStats.getDstab()))
        {
            log.info("defenceStab failed check");
            return false;
        }

        // Strength Bonuses
        if (failsCheck(config.magicDamage(), equipmentStats.getMdmg()))
        {
            log.info("magicDamage failed check");
            return false;
        }
        if (failsCheck(config.meleeStrength(), equipmentStats.getStr()))
        {
            log.info("meleeStrength failed check");
            return false;
        }
        if (failsCheck(config.rangedStrength(), equipmentStats.getRstr()))
        {
            log.info("rangedStrength failed check");
            return false;
        }

        // Other Bonuses
        if (failsCheck(config.prayer(), equipmentStats.getPrayer()))
        {
            log.info("prayer failed check");
            return false;
        }
        if (failsCheck(config.weight(), itemStats.getWeight()))
        {
            log.info("weight failed check");
            return false;
        }

        return true;
    }

    // Helper functions used to see if config check fails
    private boolean failsCheck(int required, int actual)
    {
        // "A value of 0 ignores the bonus."
        if (required == 0)
        {
            return false;
        }
        // Return true if actual stats are lower than required
        return actual < required;
    }

    private boolean failsCheck(double required, double actual)
    {
        // "A value of 0 ignores the bonus."
        if (required == 0)
        {
            return false;
        }
        // Return true if actual stats are lower than required
        return actual < required;
    }

    // Function to remove the "equip" option and replace the left click option with examine
    private void rewriteMenu(MenuEntry entryToRemove)
    {
        MenuEntry[] currentEntries = client.getMenuEntries();
        List<MenuEntry> newEntries = new ArrayList<>();
        MenuEntry examineEntry = null;

        for (MenuEntry entry : currentEntries)
        {
            if (entry == entryToRemove)
            {
                continue; // Skip the "Equip" option
            }

            String opt = Text.removeTags(entry.getOption()).toLowerCase();
            if (opt.equals("examine"))
            {
                examineEntry = entry; // Save examine for later
            }
            else
            {
                newEntries.add(entry);
            }
        }

        // Add Examine last (making it the default Left-Click)
        if (examineEntry != null)
        {
            newEntries.add(examineEntry);
        }

        client.setMenuEntries(newEntries.toArray(new MenuEntry[0]));

    }
}
