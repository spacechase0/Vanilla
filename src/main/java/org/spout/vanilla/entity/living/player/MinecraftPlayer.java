/*
 * This file is part of Vanilla (http://www.spout.org/).
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.spout.vanilla.entity.living.player;

import org.spout.api.entity.PlayerController;
import org.spout.api.player.Player;
import org.spout.vanilla.VanillaPlugin;

public abstract class MinecraftPlayer extends PlayerController {
	
	public MinecraftPlayer(Player p){
		super(p);
		p.getEntity().setInventorySize(45);
		p.getEntity().getInventory().setCurrentSlot(36);
	}
	
	@Override
	public void onAttached() {
		parent.setTransform(VanillaPlugin.spawnWorld.getSpawnPoint());
	}
	
	@Override
	public void onTick(float dt) {
		// TODO need to send timeout packets
	}
}