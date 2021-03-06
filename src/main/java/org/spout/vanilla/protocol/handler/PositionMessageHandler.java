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
package org.spout.vanilla.protocol.handler;

import org.spout.api.entity.Entity;
import org.spout.api.geo.discrete.Point;
import org.spout.api.math.Vector3;
import org.spout.api.player.Player;
import org.spout.api.protocol.MessageHandler;
import org.spout.api.protocol.Session;
import org.spout.vanilla.protocol.msg.PositionMessage;
import org.spout.vanilla.protocol.msg.PositionRotationMessage;

public final class PositionMessageHandler extends MessageHandler<PositionMessage> {
	@Override
	public void handleServer(Session session, Player player, PositionMessage message) {
		if (player == null) {
			return;
		}

		Entity entity = player.getEntity();

		if (entity == null) {
			return;
		}

		message.getStance();
		double x = message.getX();
		double y = message.getY();
		double z = message.getZ();
		/*
		//Figure out how much in X and Z the player has moved
		Vector3 newPos = new Vector3(x,y,z);
		newPos = newPos.normalize();
		Vector3 pPos = entity.getPosition().normalize();
		
		Vector3 difference = pPos.subtract(newPos);
		//Figure out how much in Forward we have moved
		//Using gram-schmidt orthonormalization.  It's magic.
		float projX = difference.dot(Vector3.Forward) / (difference.lengthSquared());
		Vector3 xdiff = difference.subtract(difference.multiply(projX));
		float diffInX = xdiff.length();
		
		//Figure out how much horizantal we have
		float projZ = difference.dot(Vector3.Right) / (difference.lengthSquared());
		Vector3 zdiff = difference.subtract(difference.multiply(projZ));
		float diffInZ = zdiff.length();
		
		player.input().setForward(diffInX);
		player.input().setHorizantal(diffInZ);
		*/
		
		
	

		Point p = new Point(entity.getWorld(), (float) x, (float) y, (float) z);
		entity.setPosition(p);
	}

	public void handleClient(Session session, Player player, PositionRotationMessage message) {
	}
}
