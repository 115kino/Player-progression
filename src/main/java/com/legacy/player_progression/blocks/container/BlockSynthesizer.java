package com.legacy.player_progression.blocks.container;

import javax.annotation.Nullable;

import com.legacy.player_progression.GuiHandler;
import com.legacy.player_progression.PlayerProgression;
import com.legacy.player_progression.tile_entities.TileEntitySynthesizer;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSynthesizer extends BlockContainer
{

	public BlockSynthesizer()
	{
		super(Material.ROCK);

		this.setHardness(5.0F);
		this.setResistance(9000.0F);
		this.setCreativeTab(CreativeTabs.DECORATIONS);
	}

	@Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
    	return EnumBlockRenderType.MODEL;
    }

	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing heldItem, float side, float hitX, float hitY)
    {
		playerIn.openGui(PlayerProgression.instance, GuiHandler.SYNTHESIZER, worldIn, pos.getX(), pos.getY(), pos.getZ());

    	return true;
    }

	@Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
    	super.harvestBlock(worldIn, player, pos, state, te, stack);

    	if (te instanceof TileEntitySynthesizer)
    	{
    		for (ItemStack item : ((TileEntitySynthesizer)te).inventory)
    		{
    			if (item != ItemStack.field_190927_a)
    			{
    				spawnAsEntity(worldIn, pos, item);
    			}
    		}
    	}
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntitySynthesizer();
	}

}