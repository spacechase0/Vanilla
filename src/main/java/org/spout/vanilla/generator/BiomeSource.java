package org.spout.vanilla.generator;

import java.util.ArrayList;

import net.royawesome.jlibnoise.module.combiner.Select;
import net.royawesome.jlibnoise.module.source.Perlin;
import net.royawesome.jlibnoise.module.source.Voronoi;

import org.spout.api.geo.World;
import org.spout.vanilla.generator.biome.AbstractBiome;

public class BiomeSource {

	private ArrayList<AbstractBiome> biomes = new ArrayList<AbstractBiome>();
	
	public static final int SEED_SHIFT = 3982462;
	protected Perlin biomeType = new Perlin();
	protected Select finalTerrain;
	
	public BiomeSource() {
		biomeType.setOctaveCount(10);
		biomeType.setPersistence(0.25);
		setupSelect();
	}
	
	private void setupSelect() {
		finalTerrain = new Select();
		finalTerrain.setControlModule(biomeType);
		finalTerrain.setEdgeFalloff(0.125);
		//finalTerrain.setBounds(1000, 0);
	}
	
	public void clearBiomes() {
		biomes.clear();
		setupSelect();
	}

	public void addBiome(AbstractBiome biome) {
		biomes.add(biome);
		finalTerrain.SetSourceModule(biomes.size() - 1, biome);
	}

	public AbstractBiome getBiomeAt(World world, int x, int z) {
		biomeType.setSeed((int) (world.getSeed() + SEED_SHIFT));
		double value = (biomeType.GetValue(x / 64.0, 0.01, z / 64.0) + 1.0) * (biomes.size() - 1) / 2.0;
		return biomes.get((int) value);
	}
	
	public int getHeightAt(World world, int x, int z) {
		biomeType.setSeed((int) (world.getSeed() + SEED_SHIFT));
		for(AbstractBiome biome : biomes) {
			biome.setWorld(world);
		}
		double v = (finalTerrain.GetValue(x + 0.05, 0.01, z + 0.05) + 1.0) * 64.0;
		//System.out.println(v);
		return (int) v;
	}
}
