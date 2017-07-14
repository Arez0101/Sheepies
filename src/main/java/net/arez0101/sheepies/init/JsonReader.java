package net.arez0101.sheepies.init;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.arez0101.sheepies.Reference;
import net.arez0101.sheepies.entity.EntityResourceSheep;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class JsonReader {
	
	private static ResourceLocation jsonFile = new ResourceLocation(Reference.MOD_ID, "sheepies.json");
	private static Gson gson = new Gson();
	
	public static void registerSheepFromJson() {
		JsonObject object = initJson();
		readJsonData(object);
	}
	
	private static JsonObject initJson() {		
		try {
			InputStream input = Minecraft.getMinecraft().getResourceManager().getResource(jsonFile).getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			JsonElement element = gson.fromJson(reader, JsonElement.class);
			return element.getAsJsonObject();
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static void readJsonData(JsonObject object) {
		for (JsonElement element : object.getAsJsonArray()) {
			String name = object.get("name").getAsString();
			String[] material = object.get("material").getAsString().split(":");
			Color color = Color.decode(object.get("color").getAsString());
			Item item = (Item) GameRegistry.findRegistry(Item.class).getValue(new ResourceLocation(material[0], material[1]));
			
			// TODO: register new resource sheep instance
		}
	}
}
