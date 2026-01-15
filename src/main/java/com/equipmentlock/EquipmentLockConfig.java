package com.equipmentlock;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("equipmentlock")
public interface EquipmentLockConfig extends Config
{

    @ConfigSection(
            name = "Attack Bonuses",
            description = "Set the minimum attack bonuses required for the item to be equipable. A value of 0 ignores the bonus.",
            position = 1
    )
    String attackBonuses = "attackBonuses";

	@ConfigItem(
		keyName = "attackCrush",
		name = "Crush",
		description = "The minimum crush attack bonus required for the item to be equipable.",
        section = attackBonuses,
        position = 2
	)
	default int attackCrush()
	{
		return 0;
	}

    @ConfigItem(
            keyName = "attackMagic",
            name = "Magic",
            description = "The minimum magic attack bonus required for the item to be equipable.",
            section = attackBonuses,
            position = 2
    )
    default int attackMagic()
    {
        return 0;
    }

    @ConfigItem(
            keyName = "attackRanged",
            name = "Ranged",
            description = "The minimum ranged attack bonus required for the item to be equipable.",
            section = attackBonuses,
            position = 3
    )
    default int attackRanged()
    {
        return 0;
    }

    @ConfigItem(
            keyName = "attackSlash",
            name = "Slash",
            description = "The minimum slash attack bonus required for the item to be equipable.",
            section = attackBonuses,
            position = 4
    )
    default int attackSlash()
    {
        return 0;
    }

    @ConfigItem(
            keyName = "attackSpeed",
            name = "Speed",
            description = "The minimum attack speed bonus required for the item to be equipable.",
            section = attackBonuses,
            position = 5
    )
    default int attackSpeed()
    {
        return 0;
    }

    @ConfigItem(
            keyName = "attackStab",
            name = "Stab",
            description = "The minimum stab attack bonus required for the item to be equipable.",
            section = attackBonuses,
            position = 6
    )
    default int attackStab()
    {
        return 0;
    }

    @ConfigSection(
            name = "Defence Bonuses",
            description = "Set the minimum defence bonuses required for the item to be equipable. A value of 0 ignores the bonus.",
            position = 7
    )
    String defenceBonuses = "defenceBonuses";

    @ConfigItem(
            keyName = "defenceCrush",
            name = "Crush",
            description = "The minimum crush defence bonus required for the item to be equipable.",
            section = defenceBonuses,
            position = 8
    )
    default int defenceCrush()
    {
        return 0;
    }

    @ConfigItem(
            keyName = "defenceMagic",
            name = "Magic",
            description = "The minimum magic defence bonus required for the item to be equipable.",
            section = defenceBonuses,
            position = 9
    )
    default int defenceMagic()
    {
        return 0;
    }

    @ConfigItem(
            keyName = "defenceRanged",
            name = "Ranged",
            description = "The minimum ranged defence bonus required for the item to be equipable.",
            section = defenceBonuses,
            position = 10
    )
    default int defenceRanged()
    {
        return 0;
    }

    @ConfigItem(
            keyName = "defenceSlash",
            name = "Slash",
            description = "The minimum slash defence bonus required for the item to be equipable.",
            section = defenceBonuses,
            position = 11
    )
    default int defenceSlash()
    {
        return 0;
    }

    @ConfigItem(
            keyName = "defenceStab",
            name = "Stab",
            description = "The minimum stab defence bonus required for the item to be equipable.",
            section = defenceBonuses,
            position = 12
    )
    default int defenceStab()
    {
        return 0;
    }

    @ConfigSection(
            name = "Strength Bonuses",
            description = "Set the minimum strength bonuses required for the item to be equipable. A value of 0 ignores the bonus.",
            position = 13
    )
    String strengthBonuses = "strengthBonuses";

    @ConfigItem(
            keyName = "magicDamage",
            name = "Magic",
            description = "The minimum magic damage bonus required for the item to be equipable.",
            section = strengthBonuses,
            position = 14
    )
    default double magicDamage()
    {
        return 0;
    }

    @ConfigItem(
            keyName = "meleeStrength",
            name = "Melee",
            description = "The minimum melee strength bonus required for the item to be equipable.",
            section = strengthBonuses,
            position = 15
    )
    default int meleeStrength()
    {
        return 0;
    }

    @ConfigItem(
            keyName = "rangedStrength",
            name = "Ranged",
            description = "The minimum ranged strength bonus required for the item to be equipable.",
            section = strengthBonuses,
            position = 14
    )
    default int rangedStrength()
    {
        return 0;
    }

    @ConfigSection(
            name = "Other",
            description = "Set the minimum bonuses required for the item to be equipable. A value of 0 ignores the bonus.",
            position = 15
    )
    String otherBonuses = "otherBonuses";

    @ConfigItem(
            keyName = "prayer",
            name = "Prayer",
            description = "The minimum prayer bonus required for the item to be equipable.",
            section = otherBonuses,
            position = 16
    )
    default int prayer()
    {
        return 0;
    }

    @ConfigItem(
            keyName = "weight",
            name = "Weight",
            description = "The minimum weight required for the item to be equipable.",
            section = otherBonuses,
            position = 16
    )
    default double weight()
    {
        return 0;
    }

}
