package org.spout.vanilla.generator.normal.biomes;

import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.util.cuboid.CuboidShortBuffer;
import org.spout.vanilla.VanillaBlocks;
import org.spout.vanilla.generator.biome.AbstractBiome;

public class OceanBiome extends AbstractBiome {

	public OceanBiome() {
		super("Ocean");
	}

	@Override
	public void decorateStack(CuboidShortBuffer blockData, int x, int y, int z) {
		int blocksHit = 0;
		for(int dy = y + Chunk.CHUNK_SIZE - 1; dy >= y; dy --) {
			if(blockData.get(x, dy, z) != VanillaBlocks.air.getId()) {
				blocksHit ++;
				if(dy >= 63) {
					blockData.set(x, dy, z, VanillaBlocks.sand.getId());
				} else {
					if (blocksHit <= 4) {
						blockData.set(x, dy, z, VanillaBlocks.dirt.getId());
					}
				}
			} else if(dy < 64) {
				blockData.set(x, dy, z, VanillaBlocks.water.getId());
			}
		}
	}

	@Override
	public int getHeightAt(World world, int x, int z) {
		return 50;
	}

}
