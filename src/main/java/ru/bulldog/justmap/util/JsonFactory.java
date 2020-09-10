package ru.bulldog.justmap.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.resource.Resource;
import ru.bulldog.justmap.JustMap;

public class JsonFactory {
	
	public final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	
	public static JsonObject loadJson(String path) throws IOException {
		try (InputStream is = JsonFactory.class.getResourceAsStream(path)) {
			Reader reader = new InputStreamReader(is);
			JsonObject jsonObject = GSON.fromJson(reader, JsonObject.class);
			if (jsonObject == null) {
				return new JsonObject();
			}
			return jsonObject;
		}
	}
	
	public static JsonObject loadJson(Resource jsonSource) {
		if (jsonSource != null) {
			try (InputStream is = jsonSource.getInputStream()) {
				Reader reader = new InputStreamReader(is);
				JsonObject jsonObject = GSON.fromJson(reader, JsonObject.class);
				if (jsonObject == null) {
					return new JsonObject();
				}
				return jsonObject;
			} catch (IOException ex) {
				JustMap.LOGGER.catching(ex);
				return new JsonObject();
			}
		}
		
		return new JsonObject();
	}
	
	public static JsonObject loadJson(File jsonFile) {
		if (jsonFile.exists()) {
			try {
				Reader reader = new FileReader(jsonFile);
				JsonObject jsonObject = GSON.fromJson(reader, JsonObject.class);
				if (jsonObject == null) {
					return new JsonObject();
				}
				return jsonObject;
			} catch (FileNotFoundException ex) {
				JustMap.LOGGER.catching(ex);
				return new JsonObject();
			}
		}
		
		return new JsonObject();
	}
	
	public static void storeJson(File jsonFile, JsonElement jsonObject) {
		try(FileWriter writer = new FileWriter(jsonFile)) {			
			String json = GSON.toJson(jsonObject);
			writer.write(json);
			writer.flush();
		} catch (IOException ex) {
			JustMap.LOGGER.catching(ex);
		}
	}
}
