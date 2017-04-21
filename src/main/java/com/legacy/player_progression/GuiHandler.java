package com.legacy.player_progression;

import com.legacy.player_progression.client.gui.GuiSynthesizer;
import com.legacy.player_progression.container.ContainerSynthesizer;
import com.legacy.player_progression.tile_entities.TileEntitySynthesizer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	public static int SYNTHESIZER = 1;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == SYNTHESIZER)
		{
			return new ContainerSynthesizer(player.inventory, (TileEntitySynthesizer) world.getTileEntity(new BlockPos(x, y, z)));
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if (ID == SYNTHESIZER)
		{
			return new GuiSynthesizer(player.inventory, (TileEntitySynthesizer) world.getTileEntity(new BlockPos(x, y, z)));
		}

		return null;
	}

}