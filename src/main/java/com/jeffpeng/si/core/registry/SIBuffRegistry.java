package com.jeffpeng.si.core.registry;

import iguanaman.hungeroverhaul.HungerOverhaul;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.Loader;
import net.minecraft.potion.Potion;

public class SIBuffRegistry {
	
	private static SIBuffRegistry instance = new SIBuffRegistry();
	private Map<String,Potion>buffMap = new HashMap<String,Potion>();
			
	public static SIBuffRegistry getInstance(){
		return instance;
	}
	
	public SIBuffRegistry(){
		buffMap.put("movespeed", Potion.moveSpeed);
		buffMap.put("moveslowdown", Potion.moveSlowdown);
		buffMap.put("digspeed", Potion.digSpeed);
		buffMap.put("digslowdown", Potion.digSlowdown);
		buffMap.put("damagesoost", Potion.damageBoost);
		buffMap.put("heal",Potion.heal);
		buffMap.put("harm",Potion.harm);
		buffMap.put("jump", Potion.jump);
		buffMap.put("confusion", Potion.confusion);
		buffMap.put("regeneration", Potion.regeneration);
		buffMap.put("resistance", Potion.resistance);
		buffMap.put("fireResistance", Potion.fireResistance);
		buffMap.put("waterBreathing", Potion.waterBreathing);
		buffMap.put("invisibility", Potion.invisibility);
		buffMap.put("blindness", Potion.blindness);
		buffMap.put("nightvision", Potion.nightVision);
		buffMap.put("hunger", Potion.hunger);
		buffMap.put("weakness", Potion.weakness);
		buffMap.put("poison", Potion.poison);
		buffMap.put("wither", Potion.wither);
		buffMap.put("healthBoost", Potion.field_76434_w);
		buffMap.put("absorption", Potion.field_76444_x);
		buffMap.put("saturation", Potion.field_76443_y);
		if(Loader.isModLoaded("HungerOverhaul")) buffMap.put("wellfed", HungerOverhaul.potionWellFed);
	}
	
	public void registerBuff(){
		
	}
	
	public Potion getBuff(String name){
		if(buffMap.containsKey(name)) return buffMap.get(name);
		return null;
	}
}
