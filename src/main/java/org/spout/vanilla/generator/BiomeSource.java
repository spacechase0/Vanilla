package org.spout.vanilla.generator;

import java.util.ArrayList;

import net.royawesome.jlibnoise.module.source.Perlin;

import org.spout.api.generator.WorldGenerator;
import org.spout.api.geo.World;
import org.spout.vanilla.generator.biome.AbstractBiome;

public abstract class BiomeSource {

	private ArrayList<AbstractBiome> biomes = new ArrayList<AbstractBiome>();
	
	public static final int SEED_SHIFT = 3982462;
	protected Perlin biomeType = new Perlin();
	
	public BiomeSource() {
		biomeType.setFrequency(0.5);
		biomeType.setPersistence(0.25);
	}
	
	public void clearBiomes() {
		biomes.clear();
	}

	public void addBiome(AbstractBiome biome) {
		biomes.add(biome);
	}

	public AbstractBiome getBiomeAt(World world, int x, int z) {
		if(biomes.size() == 0) {
			return null;
		}
		
		biomeType.setSeed((int)world.getSeed() + SEED_SHIFT);
		double biome = (biomeType.GetValue(x / 64.0, 0.05, z / 64.0) + 1.0) * biomes.size();
		
		AbstractBiome primary = biomes.get((int) biome);
		AbstractBiome secondary = null;
		if((int) biome + 1>= biomes.size()) {
			secondary = biomes.get(0);
		} else {
			secondary = biomes.get((int) biome + 1);
		}
		
		double factor = biome - (int) biome;
		if(factor >= 0.5) {
			return secondary;
		} else {
			return primary;
		}
	}
	
	public int getHeightAt(World world, int x, int z) {
		if(biomes.size() == 0) {
			return 0;
		}
		
		biomeType.setSeed((int)world.getSeed() + SEED_SHIFT);
		double biome = (biomeType.GetValue(x / 64.0, 0.05, z / 64.0) + 1.0) * biomes.size();
		
		AbstractBiome primary = biomes.get((int) biome);
		AbstractBiome secondary = null;
		if((int) biome + 1>= biomes.size()) {
			secondary = biomes.get(0);
		} else {
			secondary = biomes.get((int) biome + 1);
		}
		
		double factor = biome - (int) biome;
		int height1 = primary.getHeightAt(world, x, z);
		int height2 = secondary.getHeightAt(world, x, z);
		return (int) (height1 * factor + height2 * (1 - factor));
	}
}
