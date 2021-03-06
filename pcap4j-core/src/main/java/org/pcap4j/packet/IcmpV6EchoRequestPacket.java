/*_##########################################################################
  _##
  _##  Copyright (C) 2013  Kaito Yamada
  _##
  _##########################################################################
*/

package org.pcap4j.packet;

import org.pcap4j.util.ByteArrays;

/**
 * @author Kaito Yamada
 * @since pcap4j 0.9.15
 */
public final class IcmpV6EchoRequestPacket extends IcmpIdentifiablePacket {

  /**
   *
   */
  private static final long serialVersionUID = 1447480467515593011L;

  private final IcmpV6EchoRequestHeader header;
  private final Packet payload;

  /**
   *
   * @param rawData
   * @return a new IcmpV6EchoRequestPacket object
   */
  public static IcmpV6EchoRequestPacket newPacket(byte[] rawData) {
    return new IcmpV6EchoRequestPacket(rawData);
  }

  private IcmpV6EchoRequestPacket(byte[] rawData) {
    this.header = new IcmpV6EchoRequestHeader(rawData);

    byte[] rawPayload
      = ByteArrays.getSubArray(
          rawData,
          header.length(),
          rawData.length - header.length()
        );

    this.payload
      = UnknownPacket.newPacket(rawPayload);
  }

  private IcmpV6EchoRequestPacket(Builder builder) {
    super(builder);

    if (builder.payloadBuilder == null) {
      throw new NullPointerException("builder.payloadBuilder must not be null");
    }

    this.payload = builder.payloadBuilder.build();
    this.header = new IcmpV6EchoRequestHeader(builder);
  }

  @Override
  public IcmpV6EchoRequestHeader getHeader() {
    return header;
  }

  @Override
  public Packet getPayload() {
    return payload;
  }

  @Override
  public Builder getBuilder() {
    return new Builder(this);
  }

  /**
   * @author Kaito Yamada
   * @since pcap4j 0.9.15
   */
  public static
  final class Builder extends org.pcap4j.packet.IcmpIdentifiablePacket.Builder {

    private Packet.Builder payloadBuilder;

    /**
     *
     */
    public Builder() {}

    private Builder(IcmpV6EchoRequestPacket packet) {
      super(packet);
      this.payloadBuilder = packet.payload.getBuilder();
    }

    @Override
    public Builder identifier(short identifier) {
      super.identifier(identifier);
      return this;
    }

    @Override
    public Builder sequenceNumber(short sequenceNumber) {
      super.sequenceNumber(sequenceNumber);
      return this;
    }

    @Override
    public Builder payloadBuilder(Packet.Builder payloadBuilder) {
      this.payloadBuilder = payloadBuilder;
      return this;
    }

    @Override
    public Packet.Builder getPayloadBuilder() {
      return payloadBuilder;
    }

    @Override
    public IcmpV6EchoRequestPacket build() {
      return new IcmpV6EchoRequestPacket(this);
    }

  }

  /**
   * @author Kaito Yamada
   * @since pcap4j 0.9.15
   */
  public static final class IcmpV6EchoRequestHeader extends IcmpIdentifiableHeader {

    /*
     *  0                            15
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * |         Identifier            |
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * |       Sequence Number         |
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     *
     */


    /**
     *
     */
    private static final long serialVersionUID = -6510139039546388892L;

    private IcmpV6EchoRequestHeader(byte[] rawData) { super(rawData); }

    private IcmpV6EchoRequestHeader(Builder builder) { super(builder); }

    @Override
    protected String getHeaderName() {
      return "ICMPv6 Echo Request Header";
    }

  }

}
