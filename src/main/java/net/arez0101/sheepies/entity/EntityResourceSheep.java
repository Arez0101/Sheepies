package net.arez0101.sheepies.entity;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Maps;

import net.arez0101.sheepies.block.BlockResourceWool;
import net.arez0101.sheepies.init.SheepRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityResourceSheep extends EntitySheep {
	
	private static final DataParameter<Float> COAT_SIZE = EntityDataManager.createKey(EntityResourceSheep.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> IMMUNITY = EntityDataManager.createKey(EntityResourceSheep.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> RESOURCE_DENSITY = EntityDataManager.createKey(EntityResourceSheep.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> GENETIC_STRENGTH = EntityDataManager.createKey(EntityResourceSheep.class, DataSerializers.FLOAT);
	
	private boolean sheared = false;
	private Item resource;
	private Color color;
	private String resourceName;
	private float defaultTraitValue = 0.1F;
	
	/****************************************************************************************/
		
	public EntityResourceSheep(World worldIn, String resourceName) {
		super(worldIn);
		this.resourceName = resourceName;
		this.resource = SheepRegistry.getResource(this.resourceName);
		this.color = SheepRegistry.getColor(this.resourceName);
	}
	
	/****************************************************************************************/
	
	public Color getColor() {
		return this.color;
	}
	
	public Item getResource() {
		return this.resource;
	}
	
	public float getCoatSize() {
		return this.dataManager.get(COAT_SIZE);
	}
	
	private void setCoatSize(float coatSize) {
		this.dataManager.set(COAT_SIZE, coatSize);
	}
	
	public float getImmunity() {
		return this.dataManager.get(IMMUNITY);
	}
	
	private void setImmunity(float immunity) {
		this.dataManager.set(IMMUNITY, immunity);
	}
	
	public float getResourceDensity() {
		return this.dataManager.get(RESOURCE_DENSITY);
	}
	
	private void setResourceDensity(float resourceDensity) {
		this.dataManager.set(RESOURCE_DENSITY, resourceDensity);
	}
	
	public float getGeneticStrength() {
		return this.dataManager.get(GENETIC_STRENGTH);
	}
	
	private void setGeneticStrength(float geneticStrength) {
		this.dataManager.set(GENETIC_STRENGTH, geneticStrength);
	}
	
	/****************************************************************************************/
	
	@Override
	public String getName() {
		if (this.hasCustomName()) {
			return this.getCustomNameTag();
		}
		
		return "entity.sheepie" + this.getResource().getUnlocalizedName() + ".name";
	}
	
	public String getResourceName() {
		return this.resourceName;
	}
	
	/****************************************************************************************/
	
	private EntityResourceSheep breed(EntityResourceSheep parent1, EntityResourceSheep parent2) {
		// TODO
		return null;
	}
	
	private float breedTrait(Random random, DataParameter<Float> trait, EntityResourceSheep parent1, EntityResourceSheep parent2) {
		// TODO
		return 0.0F;
	}
	
	@Override
	public EntitySheep createChild(EntityAgeable ageable) {
		// TODO
		return null;
	}
	
	/****************************************************************************************/

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		// TODO
		return super.isShearable(item, world, pos);
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		// TODO
		return super.onSheared(item, world, pos, fortune);
	}
	
	@Override
	public boolean getSheared() {
		return this.sheared;
	}
	
	@Override
	public void setSheared(boolean sheared) {
		this.sheared = sheared;
	}
	
	/****************************************************************************************/
	
	@Override
	protected ResourceLocation getLootTable() {
		if (!this.getSheared()) {
			return this.getResource().getRegistryName();
		}
		
		return LootTableList.ENTITIES_SHEEP;
	}
	
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (player.getHeldItem(hand).getItem() == Items.SHEARS && !this.getSheared() && !this.isChild()) {
			if (!this.world.isRemote) {
				float coat = this.getCoatSize();
				float immunity = this.getImmunity();
				this.setSheared(true);
				int amount = Math.round(Math.max(1, (1 * coat) + immunity));
				
				for (int i = 0; i < amount; i++) {
					EntityItem item = this.entityDropItem(new ItemStack(new BlockResourceWool(this.getResource(), this.getResourceDensity())), 1.0F);
					item.motionX = this.rand.nextDouble() * 0.1D;
					item.motionY = this.rand.nextDouble() * 0.5D;
					item.motionZ = this.rand.nextDouble() * 0.1D;
				}
			}
			
			player.getHeldItem(hand).damageItem(1, player);
			this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
		}
		return super.processInteract(player, hand);
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		livingdata = super.onInitialSpawn(difficulty, livingdata);
		
		if (livingdata instanceof SheepieLivingData) {
			EntityResourceSheep sheepToSpawn = SheepRegistry.getSheepToSpawn(rand.nextInt(SheepRegistry.getRegistrySize()));
			livingdata = new SheepieLivingData(sheepToSpawn.getResourceName());
		}
		
		return livingdata;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(COAT_SIZE, 0.0F);
		this.dataManager.register(IMMUNITY, 0.0F);
		this.dataManager.register(RESOURCE_DENSITY, 0.0F);
		this.dataManager.register(GENETIC_STRENGTH, 0.0F);
	}
	
	/****************************************************************************************/
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("Sheared", this.getSheared());
		compound.setString("ResourceName", this.resourceName);
		compound.setFloat("CoatSize", this.getCoatSize());
		compound.setFloat("Immunity", this.getImmunity());
		compound.setFloat("ResourceDensity", this.getResourceDensity());
		compound.setFloat("GeneticStrength", this.getGeneticStrength());
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.resourceName = compound.getString("ResourceName");
		this.setSheared(compound.getBoolean("Sheared"));
		this.setCoatSize(compound.getFloat("CoatSize"));
		this.setImmunity(compound.getFloat("Immunity"));
		this.setResourceDensity(compound.getFloat("ResourceDensity"));
		this.setGeneticStrength(compound.getFloat("GeneticStrength"));
	}
	
	public class SheepieLivingData implements IEntityLivingData {
		
		private String name;
		
		public SheepieLivingData(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
	}
}
