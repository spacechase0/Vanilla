/*
 * This file is part of Vanilla (http://www.spout.org/).
 *
 * Vanilla is licensed under the SpoutDev License Version 1.  
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the 
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev license version 1 along with this program.  
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license, 
 * including the MIT license.
 */
package org.spout.vanilla.generator.normal;

import org.spout.api.generator.Populator;
import org.spout.api.generator.WorldGenerator;
import org.spout.api.geo.cuboid.Chunk;
import org.spout.api.util.cuboid.CuboidShortBuffer;
import org.spout.vanilla.VanillaBlocks;
import org.spout.vanilla.generator.BiomeSource;
import org.spout.vanilla.generator.biome.AbstractBiome;
import org.spout.vanilla.generator.normal.biomes.OceanBiome;
import org.spout.vanilla.generator.normal.biomes.PlainsBiome;
import org.spout.vanilla.generator.normal.biomes.PlateauBiome;

public class NormalGenerator implements WorldGenerator {
	
	private BiomeSource biomes = new BiomeSource();
	
	public NormalGenerator() {
		biomes.addBiome(new PlainsBiome());
		biomes.addBiome(new PlateauBiome());
		biomes.addBiome(new OceanBiome());
	}

	private final Populator[] populators = new Populator[]{new TreePopulator(), new PondPopulator(), new StrongholdPopulator(), new VillagePopulator(), new AbandonedMineshaftPopulator(), new DungeonPopulator()};

	public Populator[] getPopulators() {
		return populators;
	}

	public void generate(CuboidShortBuffer blockData, int chunkX, int chunkY, int chunkZ) {
		int x = chunkX * 16;
		int y = chunkY * 16;
		int z = chunkZ * 16;

		if (y > 127) {
			blockData.flood((short)0);
			//return;
		}
		if (chunkY < 0) {
			blockData.flood(VanillaBlocks.bedrock.getId());
			//return;
		}

		for (int dx = x; dx < (x+16); dx++) {
			for (int dz = z; dz < (z+16); dz++) {
				
				int height = biomes.getHeightAt(blockData.getWorld(), dx, dz);
				
				CuboidShortBuffer stack = new CuboidShortBuffer(blockData.getWorld(), dx, y, dz, 1, Chunk.CHUNK_SIZE, 1);
				for(int dy = y; dy < y + 16; dy++) {
					if(dy <= height) {
						stack.set(dx, dy, dz, VanillaBlocks.stone.getId());
					}
				}	
				
				AbstractBiome biome = biomes.getBiomeAt(blockData.getWorld(), dx, dz);
				biome.decorateStack(stack, dx, y, dz);
				
				for(int dy = y; dy < y + 16; dy++) {
					blockData.set(dx, dy, dz, stack.get(dx, dy, dz));
				}
			}
		}
	}
}