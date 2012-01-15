package org.spout.vanilla.generator.normal.biomes;

import org.spout.api.geo.World;
import org.spout.api.util.cuboid.CuboidShortBuffer;
import org.spout.vanilla.generator.biome.AbstractBiome;

public class PlateauBiome extends AbstractBiome {

	public PlateauBiome() {
		super("Plateau");
	}

	@Override
	public void decorateStack(CuboidShortBuffer blockData, int x, int y, int z) {
	}

	@Override
	public int getHeightAt(World world, int x, int z) {
		return 80;
	}

}
