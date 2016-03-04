package com.jeffpeng.si.core.asm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.launchwrapper.Launch;

public class SIObfuscationHelper {
	
	private static boolean deobf = false;
	
	
	
	private static Map<String,String> list = new HashMap<>();
	
	
	public static void init(){
		byte[] bs;
		try {
			bs = Launch.classLoader.getClassBytes("net.minecraft.world.World");
			if (bs != null)
	        {
	            deobf = true;
	        }
		} catch (IOException e) {}
		
		list.put("net.minecraft.inventory.ContainerRepair$2", "zw");
	}
	
	public static boolean isDeobf(){
		return deobf;
	}
	
	public static String get(String name){
		if(deobf) return name; else return list.get(name);
	}

}
