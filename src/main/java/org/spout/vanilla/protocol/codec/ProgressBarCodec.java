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
package org.spout.vanilla.protocol.codec;

import java.io.IOException;

import org.spout.api.protocol.MessageCodec;
import org.spout.vanilla.protocol.msg.ProgressBarMessage;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public final class ProgressBarCodec extends MessageCodec<ProgressBarMessage> {
	public ProgressBarCodec() {
		super(ProgressBarMessage.class, 0x69);
	}

	@Override
	public ProgressBarMessage decode(ChannelBuffer buffer) throws IOException {
		int id = buffer.readUnsignedByte();
		int progressBar = buffer.readUnsignedShort();
		int value = buffer.readUnsignedShort();
		return new ProgressBarMessage(id, progressBar, value);
	}

	@Override
	public ChannelBuffer encode(ProgressBarMessage message) throws IOException {
		ChannelBuffer buffer = ChannelBuffers.buffer(5);
		buffer.writeByte(message.getId());
		buffer.writeShort(message.getProgressBar());
		buffer.writeShort(message.getValue());
		return buffer;
	}
}