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
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.vanilla.protocol.entity.living;

import org.spout.api.entity.Controller;
import org.spout.api.entity.Entity;
import org.spout.api.inventory.ItemStack;
import org.spout.api.protocol.EntityProtocol;
import org.spout.api.protocol.Message;
import org.spout.vanilla.entity.living.player.VanillaPlayer;
import org.spout.vanilla.protocol.VanillaEntityProtocol;
import org.spout.vanilla.protocol.msg.SpawnPlayerMessage;

public class VanillaPlayerEntityProtocol extends VanillaEntityProtocol implements EntityProtocol {
	@Override
	public Message getSpawnMessage(Entity entity) {
		Controller c = entity.getController();
		if (c == null) {
			return null;
		}
		int id = entity.getId();
		int x = (int) (entity.getPosition().getX() * 32);
		int y = (int) (entity.getPosition().getY() * 32);
		int z = (int) (entity.getPosition().getZ() * 32);
		int r = (int) (entity.getYaw() * 32);
		int p = (int) (entity.getPitch() * 32);

		if (c instanceof VanillaPlayer) {
			VanillaPlayer mcp = (VanillaPlayer) c;
			String name = mcp.getPlayer().getName();
			int item = 0;
			ItemStack hand = entity.getInventory().getCurrentItem();
			if (hand != null) {
				item = hand.getMaterial().getId();
			}
			return new SpawnPlayerMessage(id, name, x, y, z, r, p, item);
		}

		return null;
	}
}
