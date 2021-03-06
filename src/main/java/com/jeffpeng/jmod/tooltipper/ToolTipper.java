package com.jeffpeng.jmod.tooltipper;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import com.jeffpeng.jmod.Config;
import com.jeffpeng.jmod.JMODRepresentation;
import com.jeffpeng.jmod.Lib;
import com.jeffpeng.jmod.descriptors.TooltipDescriptor;
import com.jeffpeng.jmod.util.Reflector;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;


public class ToolTipper {
	
	private JMODRepresentation jmod;
	private Config config;
	private Lib lib;
	

	
	private Map<ItemStack, String[]> tooltips = new HashMap<ItemStack, String[]>();
	

	public ToolTipper(JMODRepresentation jmod){
		this.jmod = jmod;
		this.config = jmod.getConfig();
		this.lib = jmod.getLib();
		
		
		for(TooltipDescriptor entry : config.tooltips){
			for(String target : entry.target){
				Object is = lib.stringToItemStack(target);
				if(is instanceof ItemStack){
					tooltips.put((ItemStack) is, entry.lines);
				}
			}
			
		}
	}
	
	@SubscribeEvent
	public void onHoverItem(ItemTooltipEvent event){
		ItemStack itemstack = event.itemStack;
		Item item = itemstack.getItem();
		
		for(Map.Entry<ItemStack, String[]> entry : tooltips.entrySet()){
			if(item == entry.getKey().getItem())
				if(!item.getHasSubtypes() || (itemstack.getItemDamage() == 32767 || itemstack.getItemDamage() == entry.getKey().getItemDamage())){
					for(String tooltiprow : entry.getValue()){
						event.toolTip.add(StatCollector.translateToLocal(tooltiprow));
					}
				
			}
		}
		
		if(config.showArmorValues)
		if(item instanceof ItemArmor){
			event.toolTip.add(StatCollector.translateToLocal("info." + jmod.getModId() + ".armorvalue")+" " + ((ItemArmor)item).damageReduceAmount);
		}
		
		
		if(config.showToolHarvestLevels){
			if(item instanceof ItemBlock){
				try{
					Block block = ((ItemBlock)item).field_150939_a;
					Reflector blockreflector = new Reflector(block,Block.class);
					Float resistance = blockreflector.getFloat("field_149781_w");
					if(resistance >= 135F){
						event.toolTip.add(StatCollector.translateToLocal("info." + jmod.getModId() + ".tntsafe"));
					}
					
				} catch(Exception e){
					e.printStackTrace();
				}
			}
	        
			
			if(item instanceof ItemPickaxe){
				try{
					Integer hlevel = ((ItemPickaxe)item).getHarvestLevel(itemstack, "pickaxe");
					if(hlevel >= 255) hlevel=255;
					event.toolTip.add(StatCollector.translateToLocal("info." + jmod.getModId() + ".harvestlevel") + ": " + StatCollector.translateToLocal("info." + jmod.getModId() + ".harvestlevel" + hlevel));
				} catch(Exception e){
					
				}
			}
			
			if(item instanceof ItemAxe){
				try{
					Integer hlevel = ((ItemAxe)item).getHarvestLevel(itemstack, "axe");
					if(hlevel >= 255) hlevel=255;
					event.toolTip.add(StatCollector.translateToLocal("info." + jmod.getModId() + ".harvestlevel") + ": " + StatCollector.translateToLocal("info." + jmod.getModId() + ".harvestlevel" + hlevel));
				} catch(Exception e){
					
				}
			}
			
			if(item instanceof ItemSpade){
				try{
					Integer hlevel = ((ItemSpade)item).getHarvestLevel(itemstack, "shovel");
					if(hlevel >= 255) hlevel=255;
					event.toolTip.add(StatCollector.translateToLocal("info." + jmod.getModId() + ".harvestlevel") + ": " + StatCollector.translateToLocal("info." + jmod.getModId() + ".harvestlevel" + hlevel));
				} catch(Exception e){
					
				}
			}
			
			if(item instanceof ItemTool)
			{
				if(item.getClass().getCanonicalName().contains("fi.dy.masa.enderutilities") /*&& ((ItemEnderTool)item).getToolType(itemstack).equals(ToolType.PICKAXE)*/){
					try{
						Integer hlevel = ToolMaterial.valueOf(((ItemTool)item).getToolMaterialName()).getHarvestLevel();
						if(hlevel >= 255) hlevel=255;
						event.toolTip.add(StatCollector.translateToLocal("info." + jmod.getModId() + ".harvestlevel") + ": " + StatCollector.translateToLocal("info." + jmod.getModId() + ".harvestlevel" + hlevel));
					} catch(Exception e){
						
					}
				}
			}
		}
		
		
		if(config.showBlockHarvestLevels && event.itemStack.getItem() instanceof ItemBlock ){
			ItemBlock itemblock = ((ItemBlock)event.itemStack.getItem());
			try{
				Integer hlevel = itemblock.field_150939_a.getHarvestLevel(itemblock.getDamage(event.itemStack));
				if(hlevel >= 255) hlevel=255;
				if(hlevel>1)event.toolTip.add(StatCollector.translateToLocal("info." + jmod.getModId() + ".harvestlevel") + ": " + StatCollector.translateToLocal("info." + jmod.getModId() + ".harvestlevel" + hlevel));
			} catch(Exception e){
				//TODO: Do something maybe?
			}
		}
		
		
	}
	
}
