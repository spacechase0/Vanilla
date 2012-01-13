package org.spout.vanilla.generator.biome;

import org.spout.api.geo.World;
import org.spout.api.util.cuboid.CuboidShortBuffer;

public abstract class AbstractBiome {
	private String name = "AbstractBiome";
	
	public AbstractBiome(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void decorateStack(CuboidShortBuffer blockData, int x, int y, int z);
	
	public abstract int getHeightAt(World world, int x, int z);
}
