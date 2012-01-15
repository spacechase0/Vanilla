package org.spout.vanilla.generator.biome;

import net.royawesome.jlibnoise.module.Module;

import org.spout.api.geo.World;
import org.spout.api.util.cuboid.CuboidShortBuffer;

public abstract class AbstractBiome extends Module {
	private World world;
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	@Override
	public int GetSourceModuleCount() {
		return 0;
	}

	@Override
	public double GetValue(double x, double y, double z) {
		return ((double) getHeightAt(world, (int) x, (int) z) + 0.05) / 64.0 - 1.0;
	}

	@Override
	public void SetSourceModule(int index, Module sourceModule) {
	}

	@Override
	public Module getSourceModule(int index) {
		return null;
	}

	private String name = "AbstractBiome";
	
	public AbstractBiome(String name) {
		super(0);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void decorateStack(CuboidShortBuffer blockData, int x, int y, int z);
	
	public abstract int getHeightAt(World world, int x, int z);
}
