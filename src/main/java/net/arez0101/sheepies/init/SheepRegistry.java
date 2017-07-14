package net.arez0101.sheepies.init;

import java.awt.Color;
import java.util.Map;

import com.google.common.collect.Maps;

import net.arez0101.sheepies.entity.EntityResourceSheep;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class SheepRegistry {
	
	private static Map<String, Item> RESOURCE_REGISTRY = Maps.<String, Item>newHashMap();
	private static Map<String, Color> COLOR_REGISTRY = Maps.<String, Color>newHashMap();
	
	public static void register(String name, Item resource, Color color) {
		RESOURCE_REGISTRY.put(name, resource);
		COLOR_REGISTRY.put(name, color);
	}
	
	public static Item getResource(String name) {
		return RESOURCE_REGISTRY.getOrDefault(name, Item.getItemFromBlock(Blocks.COBBLESTONE));
	}
	
	public static Color getColor(String name) {
		return COLOR_REGISTRY.getOrDefault(name, Color.WHITE);
	}
	
	public static ResourceLocation getResourceLocation(String name) {
		Item item = getResource(name);
		return item.getRegistryName();
	}
	
	public static ResourceLocation getResourceLocation(Item resource) {
		return resource.getRegistryName();
	}
	
	public static int getRegistrySize() {
		return RESOURCE_REGISTRY.size();
	}
	
	public static EntityResourceSheep getSheepToSpawn(int key) {
		return new EntityResourceSheep(Minecraft.getMinecraft().world, RESOURCE_REGISTRY.keySet().toArray(new String[] {})[key]);
	}
	
	public static Map<String, Item> getRegistry() {
		return RESOURCE_REGISTRY;
	}
}
