package net.arez0101.sheepies.block;

import java.util.Random;

import net.arez0101.sheepies.Sheepies;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockResourceWool extends Block {
	
	private PropertyInteger DENSITY = PropertyInteger.create("density", 0, 5);

	public BlockResourceWool(Item resourceIn, float resourceDensity) {
		super(Material.CLOTH);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DENSITY, Math.round((resourceDensity * 5))));
		this.setCreativeTab(Sheepies.TAB);
	}
	
	public int getDensity() {
		return this.getBlockState().getBaseState().getValue(DENSITY);
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(DENSITY);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		Item item = Item.getItemFromBlock(this);
		item.setDamage(new ItemStack(item), this.getMetaFromState(state));
		return item;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(DENSITY);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(DENSITY, meta);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, DENSITY);
	}
}
