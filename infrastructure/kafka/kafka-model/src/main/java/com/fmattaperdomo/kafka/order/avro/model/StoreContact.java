/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.fmattaperdomo.kafka.order.avro.model;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class StoreContact extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 600585936666309352L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"StoreContact\",\"namespace\":\"com.fmattaperdomo.kafka.order.avro.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"logicalType\":\"uuid\"},{\"name\":\"name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"phone\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<StoreContact> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<StoreContact> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<StoreContact> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<StoreContact> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<StoreContact> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this StoreContact to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a StoreContact from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a StoreContact instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static StoreContact fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String id;
  private java.lang.String name;
  private java.lang.String phone;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public StoreContact() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param name The new value for name
   * @param phone The new value for phone
   */
  public StoreContact(java.lang.String id, java.lang.String name, java.lang.String phone) {
    this.id = id;
    this.name = name;
    this.phone = phone;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return name;
    case 2: return phone;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = value$ != null ? value$.toString() : null; break;
    case 1: name = value$ != null ? value$.toString() : null; break;
    case 2: phone = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.String getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.String value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'name' field.
   * @return The value of the 'name' field.
   */
  public java.lang.String getName() {
    return name;
  }


  /**
   * Sets the value of the 'name' field.
   * @param value the value to set.
   */
  public void setName(java.lang.String value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'phone' field.
   * @return The value of the 'phone' field.
   */
  public java.lang.String getPhone() {
    return phone;
  }


  /**
   * Sets the value of the 'phone' field.
   * @param value the value to set.
   */
  public void setPhone(java.lang.String value) {
    this.phone = value;
  }

  /**
   * Creates a new StoreContact RecordBuilder.
   * @return A new StoreContact RecordBuilder
   */
  public static com.fmattaperdomo.kafka.order.avro.model.StoreContact.Builder newBuilder() {
    return new com.fmattaperdomo.kafka.order.avro.model.StoreContact.Builder();
  }

  /**
   * Creates a new StoreContact RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new StoreContact RecordBuilder
   */
  public static com.fmattaperdomo.kafka.order.avro.model.StoreContact.Builder newBuilder(com.fmattaperdomo.kafka.order.avro.model.StoreContact.Builder other) {
    if (other == null) {
      return new com.fmattaperdomo.kafka.order.avro.model.StoreContact.Builder();
    } else {
      return new com.fmattaperdomo.kafka.order.avro.model.StoreContact.Builder(other);
    }
  }

  /**
   * Creates a new StoreContact RecordBuilder by copying an existing StoreContact instance.
   * @param other The existing instance to copy.
   * @return A new StoreContact RecordBuilder
   */
  public static com.fmattaperdomo.kafka.order.avro.model.StoreContact.Builder newBuilder(com.fmattaperdomo.kafka.order.avro.model.StoreContact other) {
    if (other == null) {
      return new com.fmattaperdomo.kafka.order.avro.model.StoreContact.Builder();
    } else {
      return new com.fmattaperdomo.kafka.order.avro.model.StoreContact.Builder(other);
    }
  }

  /**
   * RecordBuilder for StoreContact instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<StoreContact>
    implements org.apache.avro.data.RecordBuilder<StoreContact> {

    private java.lang.String id;
    private java.lang.String name;
    private java.lang.String phone;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.fmattaperdomo.kafka.order.avro.model.StoreContact.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.name)) {
        this.name = data().deepCopy(fields()[1].schema(), other.name);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.phone)) {
        this.phone = data().deepCopy(fields()[2].schema(), other.phone);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing StoreContact instance
     * @param other The existing instance to copy.
     */
    private Builder(com.fmattaperdomo.kafka.order.avro.model.StoreContact other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.name)) {
        this.name = data().deepCopy(fields()[1].schema(), other.name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.phone)) {
        this.phone = data().deepCopy(fields()[2].schema(), other.phone);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.String getId() {
      return id;
    }


    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.fmattaperdomo.kafka.order.avro.model.StoreContact.Builder setId(java.lang.String value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.fmattaperdomo.kafka.order.avro.model.StoreContact.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'name' field.
      * @return The value.
      */
    public java.lang.String getName() {
      return name;
    }


    /**
      * Sets the value of the 'name' field.
      * @param value The value of 'name'.
      * @return This builder.
      */
    public com.fmattaperdomo.kafka.order.avro.model.StoreContact.Builder setName(java.lang.String value) {
      validate(fields()[1], value);
      this.name = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'name' field.
      * @return This builder.
      */
    public com.fmattaperdomo.kafka.order.avro.model.StoreContact.Builder clearName() {
      name = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'phone' field.
      * @return The value.
      */
    public java.lang.String getPhone() {
      return phone;
    }


    /**
      * Sets the value of the 'phone' field.
      * @param value The value of 'phone'.
      * @return This builder.
      */
    public com.fmattaperdomo.kafka.order.avro.model.StoreContact.Builder setPhone(java.lang.String value) {
      validate(fields()[2], value);
      this.phone = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'phone' field has been set.
      * @return True if the 'phone' field has been set, false otherwise.
      */
    public boolean hasPhone() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'phone' field.
      * @return This builder.
      */
    public com.fmattaperdomo.kafka.order.avro.model.StoreContact.Builder clearPhone() {
      phone = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public StoreContact build() {
      try {
        StoreContact record = new StoreContact();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.String) defaultValue(fields()[0]);
        record.name = fieldSetFlags()[1] ? this.name : (java.lang.String) defaultValue(fields()[1]);
        record.phone = fieldSetFlags()[2] ? this.phone : (java.lang.String) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<StoreContact>
    WRITER$ = (org.apache.avro.io.DatumWriter<StoreContact>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<StoreContact>
    READER$ = (org.apache.avro.io.DatumReader<StoreContact>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.id);

    out.writeString(this.name);

    out.writeString(this.phone);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.id = in.readString();

      this.name = in.readString();

      this.phone = in.readString();

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.id = in.readString();
          break;

        case 1:
          this.name = in.readString();
          break;

        case 2:
          this.phone = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










