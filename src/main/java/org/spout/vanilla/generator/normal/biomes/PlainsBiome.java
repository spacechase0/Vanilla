package org.spout.vanilla.generator.normal.biomes;

import org.spout.api.geo.World;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.util.cuboid.CuboidShortBuffer;
import org.spout.vanilla.VanillaBlocks;
import org.spout.vanilla.generator.biome.AbstractBiome;

public class PlainsBiome extends AbstractBiome {

	public PlainsBiome() {
		super("Plains");
	}

	@Override
	public void decorateStack(CuboidShortBuffer blockData, int x, int y, int z) {
		int blocksHit = 0;
		for(int dy = y + Chunk.CHUNK_SIZE - 1; dy >= y; dy --) {
			if(blockData.get(x, dy, z) != VanillaBlocks.air.getId()) {
				blocksHit ++;
				if(blocksHit == 1) {
					blockData.set(x, dy, z, VanillaBlocks.grass.getId());
				} else if (blocksHit <= 4) {
					blockData.set(x, dy, z, VanillaBlocks.dirt.getId());
				}
			}
		}
	}

	@Override
	public int getHeightAt(World world, int x, int z) {
		return 64;
	}

}
