package com.equipmentlock;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class EquipmentLockPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(EquipmentLockPlugin.class);
		RuneLite.main(args);
	}
}