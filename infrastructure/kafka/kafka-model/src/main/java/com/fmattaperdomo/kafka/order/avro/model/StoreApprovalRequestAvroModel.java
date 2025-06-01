package com.fmattaperdomo.kafka.order.avro.model;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class StoreApprovalRequestAvroModel extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord{
    private static final long serialVersionUID = -3917361261016430486L;


    public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"StoreApprovalRequestAvroModel\",\"namespace\":\"com.fmattaperdomo.kafka.order.avro.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"sagaId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"storeId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"orderId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"storeOrderStatus\",\"type\":{\"type\":\"enum\",\"name\":\"StoreOrderStatus\",\"symbols\":[\"PAID\"]}},{\"name\":\"products\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Product\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"logicalType\":\"uuid\"},{\"name\":\"quantity\",\"type\":\"int\"}]}}},{\"name\":\"price\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":10,\"scale\":2}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}}]}");
    public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

    private static final SpecificData MODEL$ = new SpecificData();
    static {
        MODEL$.addLogicalTypeConversion(new org.apache.avro.data.TimeConversions.TimestampMillisConversion());
        MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.DecimalConversion());
    }

    private static final BinaryMessageEncoder<StoreApprovalRequestAvroModel> ENCODER =
            new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

    private static final BinaryMessageDecoder<StoreApprovalRequestAvroModel> DECODER =
            new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

    /**
     * Return the BinaryMessageEncoder instance used by this class.
     * @return the message encoder used by this class
     */
    public static BinaryMessageEncoder<StoreApprovalRequestAvroModel> getEncoder() {
        return ENCODER;
    }

    /**
     * Return the BinaryMessageDecoder instance used by this class.
     * @return the message decoder used by this class
     */
    public static BinaryMessageDecoder<StoreApprovalRequestAvroModel> getDecoder() {
        return DECODER;
    }

    /**
     * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
     * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
     * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
     */
    public static BinaryMessageDecoder<StoreApprovalRequestAvroModel> createDecoder(SchemaStore resolver) {
        return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
    }

    /**
     * Serializes this StoreApprovalRequestAvroModel to a ByteBuffer.
     * @return a buffer holding the serialized data for this instance
     * @throws java.io.IOException if this instance could not be serialized
     */
    public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
        return ENCODER.encode(this);
    }

    /**
     * Deserializes a StoreApprovalRequestAvroModel from a ByteBuffer.
     * @param b a byte buffer holding serialized data for an instance of this class
     * @return a StoreApprovalRequestAvroModel instance decoded from the given buffer
     * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
     */
    public static StoreApprovalRequestAvroModel fromByteBuffer(
            java.nio.ByteBuffer b) throws java.io.IOException {
        return DECODER.decode(b);
    }

    private java.lang.String id;
    private java.lang.String sagaId;
    private java.lang.String storeId;
    private java.lang.String orderId;
    private com.fmattaperdomo.kafka.order.avro.model.StoreOrderStatus storeOrderStatus;
    private java.util.List<com.fmattaperdomo.kafka.order.avro.model.Product> products;
    private java.math.BigDecimal price;
    private java.time.Instant createdAt;

    /**
     * Default constructor.  Note that this does not initialize fields
     * to their default values from the schema.  If that is desired then
     * one should use <code>newBuilder()</code>.
     */
    public StoreApprovalRequestAvroModel() {}

    /**
     * All-args constructor.
     * @param id The new value for id
     * @param sagaId The new value for sagaId
     * @param storeId The new value for storeId
     * @param orderId The new value for orderId
     * @param storeOrderStatus The new value for storeOrderStatus
     * @param products The new value for products
     * @param price The new value for price
     * @param createdAt The new value for createdAt
     */
    public StoreApprovalRequestAvroModel(java.lang.String id, java.lang.String sagaId, java.lang.String storeId, java.lang.String orderId, com.fmattaperdomo.kafka.order.avro.model.StoreOrderStatus storeOrderStatus, java.util.List<com.fmattaperdomo.kafka.order.avro.model.Product> products, java.math.BigDecimal price, java.time.Instant createdAt) {
        this.id = id;
        this.sagaId = sagaId;
        this.storeId = storeId;
        this.orderId = orderId;
        this.storeOrderStatus = storeOrderStatus;
        this.products = products;
        this.price = price;
        this.createdAt = createdAt.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
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
            case 1: return sagaId;
            case 2: return storeId;
            case 3: return orderId;
            case 4: return storeOrderStatus;
            case 5: return products;
            case 6: return price;
            case 7: return createdAt;
            default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
        }
    }

    private static final org.apache.avro.Conversion<?>[] conversions =
            new org.apache.avro.Conversion<?>[] {
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    new org.apache.avro.Conversions.DecimalConversion(),
                    new org.apache.avro.data.TimeConversions.TimestampMillisConversion(),
                    null
            };

    @Override
    public org.apache.avro.Conversion<?> getConversion(int field) {
        return conversions[field];
    }

    // Used by DatumReader.  Applications should not call.
    @Override
    @SuppressWarnings(value="unchecked")
    public void put(int field$, java.lang.Object value$) {
        switch (field$) {
            case 0: id = value$ != null ? value$.toString() : null; break;
            case 1: sagaId = value$ != null ? value$.toString() : null; break;
            case 2: storeId = value$ != null ? value$.toString() : null; break;
            case 3: orderId = value$ != null ? value$.toString() : null; break;
            case 4: storeOrderStatus = (com.fmattaperdomo.kafka.order.avro.model.StoreOrderStatus)value$; break;
            case 5: products = (java.util.List<com.fmattaperdomo.kafka.order.avro.model.Product>)value$; break;
            case 6: price = (java.math.BigDecimal)value$; break;
            case 7: createdAt = (java.time.Instant)value$; break;
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
     * Gets the value of the 'sagaId' field.
     * @return The value of the 'sagaId' field.
     */
    public java.lang.String getSagaId() {
        return sagaId;
    }


    /**
     * Sets the value of the 'sagaId' field.
     * @param value the value to set.
     */
    public void setSagaId(java.lang.String value) {
        this.sagaId = value;
    }

    /**
     * Gets the value of the 'storeId' field.
     * @return The value of the 'storeId' field.
     */
    public java.lang.String getStoreId() {
        return storeId;
    }


    /**
     * Sets the value of the 'storeId' field.
     * @param value the value to set.
     */
    public void setStoreId(java.lang.String value) {
        this.storeId = value;
    }

    /**
     * Gets the value of the 'orderId' field.
     * @return The value of the 'orderId' field.
     */
    public java.lang.String getOrderId() {
        return orderId;
    }


    /**
     * Sets the value of the 'orderId' field.
     * @param value the value to set.
     */
    public void setOrderId(java.lang.String value) {
        this.orderId = value;
    }

    /**
     * Gets the value of the 'storeOrderStatus' field.
     * @return The value of the 'storeOrderStatus' field.
     */
    public com.fmattaperdomo.kafka.order.avro.model.StoreOrderStatus getStoreOrderStatus() {
        return storeOrderStatus;
    }


    /**
     * Sets the value of the 'storeOrderStatus' field.
     * @param value the value to set.
     */
    public void setStoreOrderStatus(com.fmattaperdomo.kafka.order.avro.model.StoreOrderStatus value) {
        this.storeOrderStatus = value;
    }

    /**
     * Gets the value of the 'products' field.
     * @return The value of the 'products' field.
     */
    public java.util.List<com.fmattaperdomo.kafka.order.avro.model.Product> getProducts() {
        return products;
    }


    /**
     * Sets the value of the 'products' field.
     * @param value the value to set.
     */
    public void setProducts(java.util.List<com.fmattaperdomo.kafka.order.avro.model.Product> value) {
        this.products = value;
    }

    /**
     * Gets the value of the 'price' field.
     * @return The value of the 'price' field.
     */
    public java.math.BigDecimal getPrice() {
        return price;
    }


    /**
     * Sets the value of the 'price' field.
     * @param value the value to set.
     */
    public void setPrice(java.math.BigDecimal value) {
        this.price = value;
    }

    /**
     * Gets the value of the 'createdAt' field.
     * @return The value of the 'createdAt' field.
     */
    public java.time.Instant getCreatedAt() {
        return createdAt;
    }


    /**
     * Sets the value of the 'createdAt' field.
     * @param value the value to set.
     */
    public void setCreatedAt(java.time.Instant value) {
        this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
    }

    /**
     * Creates a new StoreApprovalRequestAvroModel RecordBuilder.
     * @return A new StoreApprovalRequestAvroModel RecordBuilder
     */
    public static com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder newBuilder() {
        return new com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder();
    }

    /**
     * Creates a new StoreApprovalRequestAvroModel RecordBuilder by copying an existing Builder.
     * @param other The existing builder to copy.
     * @return A new StoreApprovalRequestAvroModel RecordBuilder
     */
    public static com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder newBuilder(com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder other) {
        if (other == null) {
            return new com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder();
        } else {
            return new com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder(other);
        }
    }

    /**
     * Creates a new StoreApprovalRequestAvroModel RecordBuilder by copying an existing StoreApprovalRequestAvroModel instance.
     * @param other The existing instance to copy.
     * @return A new StoreApprovalRequestAvroModel RecordBuilder
     */
    public static com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder newBuilder(com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel other) {
        if (other == null) {
            return new com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder();
        } else {
            return new com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder(other);
        }
    }

    /**
     * RecordBuilder for StoreApprovalRequestAvroModel instances.
     */
    @org.apache.avro.specific.AvroGenerated
    public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<StoreApprovalRequestAvroModel>
            implements org.apache.avro.data.RecordBuilder<StoreApprovalRequestAvroModel> {

        private java.lang.String id;
        private java.lang.String sagaId;
        private java.lang.String storeId;
        private java.lang.String orderId;
        private com.fmattaperdomo.kafka.order.avro.model.StoreOrderStatus storeOrderStatus;
        private java.util.List<com.fmattaperdomo.kafka.order.avro.model.Product> products;
        private java.math.BigDecimal price;
        private java.time.Instant createdAt;

        /** Creates a new Builder */
        private Builder() {
            super(SCHEMA$, MODEL$);
        }

        /**
         * Creates a Builder by copying an existing Builder.
         * @param other The existing Builder to copy.
         */
        private Builder(com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder other) {
            super(other);
            if (isValidValue(fields()[0], other.id)) {
                this.id = data().deepCopy(fields()[0].schema(), other.id);
                fieldSetFlags()[0] = other.fieldSetFlags()[0];
            }
            if (isValidValue(fields()[1], other.sagaId)) {
                this.sagaId = data().deepCopy(fields()[1].schema(), other.sagaId);
                fieldSetFlags()[1] = other.fieldSetFlags()[1];
            }
            if (isValidValue(fields()[2], other.storeId)) {
                this.storeId = data().deepCopy(fields()[2].schema(), other.storeId);
                fieldSetFlags()[2] = other.fieldSetFlags()[2];
            }
            if (isValidValue(fields()[3], other.orderId)) {
                this.orderId = data().deepCopy(fields()[3].schema(), other.orderId);
                fieldSetFlags()[3] = other.fieldSetFlags()[3];
            }
            if (isValidValue(fields()[4], other.storeOrderStatus)) {
                this.storeOrderStatus = data().deepCopy(fields()[4].schema(), other.storeOrderStatus);
                fieldSetFlags()[4] = other.fieldSetFlags()[4];
            }
            if (isValidValue(fields()[5], other.products)) {
                this.products = data().deepCopy(fields()[5].schema(), other.products);
                fieldSetFlags()[5] = other.fieldSetFlags()[5];
            }
            if (isValidValue(fields()[6], other.price)) {
                this.price = data().deepCopy(fields()[6].schema(), other.price);
                fieldSetFlags()[6] = other.fieldSetFlags()[6];
            }
            if (isValidValue(fields()[7], other.createdAt)) {
                this.createdAt = data().deepCopy(fields()[7].schema(), other.createdAt);
                fieldSetFlags()[7] = other.fieldSetFlags()[7];
            }
        }

        /**
         * Creates a Builder by copying an existing StoreApprovalRequestAvroModel instance
         * @param other The existing instance to copy.
         */
        private Builder(com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel other) {
            super(SCHEMA$, MODEL$);
            if (isValidValue(fields()[0], other.id)) {
                this.id = data().deepCopy(fields()[0].schema(), other.id);
                fieldSetFlags()[0] = true;
            }
            if (isValidValue(fields()[1], other.sagaId)) {
                this.sagaId = data().deepCopy(fields()[1].schema(), other.sagaId);
                fieldSetFlags()[1] = true;
            }
            if (isValidValue(fields()[2], other.storeId)) {
                this.storeId = data().deepCopy(fields()[2].schema(), other.storeId);
                fieldSetFlags()[2] = true;
            }
            if (isValidValue(fields()[3], other.orderId)) {
                this.orderId = data().deepCopy(fields()[3].schema(), other.orderId);
                fieldSetFlags()[3] = true;
            }
            if (isValidValue(fields()[4], other.storeOrderStatus)) {
                this.storeOrderStatus = data().deepCopy(fields()[4].schema(), other.storeOrderStatus);
                fieldSetFlags()[4] = true;
            }
            if (isValidValue(fields()[5], other.products)) {
                this.products = data().deepCopy(fields()[5].schema(), other.products);
                fieldSetFlags()[5] = true;
            }
            if (isValidValue(fields()[6], other.price)) {
                this.price = data().deepCopy(fields()[6].schema(), other.price);
                fieldSetFlags()[6] = true;
            }
            if (isValidValue(fields()[7], other.createdAt)) {
                this.createdAt = data().deepCopy(fields()[7].schema(), other.createdAt);
                fieldSetFlags()[7] = true;
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
        public com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder setId(java.lang.String value) {
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
        public com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder clearId() {
            id = null;
            fieldSetFlags()[0] = false;
            return this;
        }

        /**
         * Gets the value of the 'sagaId' field.
         * @return The value.
         */
        public java.lang.String getSagaId() {
            return sagaId;
        }


        /**
         * Sets the value of the 'sagaId' field.
         * @param value The value of 'sagaId'.
         * @return This builder.
         */
        public com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder setSagaId(java.lang.String value) {
            validate(fields()[1], value);
            this.sagaId = value;
            fieldSetFlags()[1] = true;
            return this;
        }

        /**
         * Checks whether the 'sagaId' field has been set.
         * @return True if the 'sagaId' field has been set, false otherwise.
         */
        public boolean hasSagaId() {
            return fieldSetFlags()[1];
        }


        /**
         * Clears the value of the 'sagaId' field.
         * @return This builder.
         */
        public com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder clearSagaId() {
            sagaId = null;
            fieldSetFlags()[1] = false;
            return this;
        }

        /**
         * Gets the value of the 'storeId' field.
         * @return The value.
         */
        public java.lang.String getStoreId() {
            return storeId;
        }


        /**
         * Sets the value of the 'storeId' field.
         * @param value The value of 'storeId'.
         * @return This builder.
         */
        public com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder setStoreId(java.lang.String value) {
            validate(fields()[2], value);
            this.storeId = value;
            fieldSetFlags()[2] = true;
            return this;
        }

        /**
         * Checks whether the 'storeId' field has been set.
         * @return True if the 'storeId' field has been set, false otherwise.
         */
        public boolean hasStoreId() {
            return fieldSetFlags()[2];
        }


        /**
         * Clears the value of the 'storeId' field.
         * @return This builder.
         */
        public com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder clearStoreId() {
            storeId = null;
            fieldSetFlags()[2] = false;
            return this;
        }

        /**
         * Gets the value of the 'orderId' field.
         * @return The value.
         */
        public java.lang.String getOrderId() {
            return orderId;
        }


        /**
         * Sets the value of the 'orderId' field.
         * @param value The value of 'orderId'.
         * @return This builder.
         */
        public com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder setOrderId(java.lang.String value) {
            validate(fields()[3], value);
            this.orderId = value;
            fieldSetFlags()[3] = true;
            return this;
        }

        /**
         * Checks whether the 'orderId' field has been set.
         * @return True if the 'orderId' field has been set, false otherwise.
         */
        public boolean hasOrderId() {
            return fieldSetFlags()[3];
        }


        /**
         * Clears the value of the 'orderId' field.
         * @return This builder.
         */
        public com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder clearOrderId() {
            orderId = null;
            fieldSetFlags()[3] = false;
            return this;
        }

        /**
         * Gets the value of the 'storeOrderStatus' field.
         * @return The value.
         */
        public com.fmattaperdomo.kafka.order.avro.model.StoreOrderStatus getStoreOrderStatus() {
            return storeOrderStatus;
        }


        /**
         * Sets the value of the 'storeOrderStatus' field.
         * @param value The value of 'storeOrderStatus'.
         * @return This builder.
         */
        public com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder setStoreOrderStatus(com.fmattaperdomo.kafka.order.avro.model.StoreOrderStatus value) {
            validate(fields()[4], value);
            this.storeOrderStatus = value;
            fieldSetFlags()[4] = true;
            return this;
        }

        /**
         * Checks whether the 'storeOrderStatus' field has been set.
         * @return True if the 'storeOrderStatus' field has been set, false otherwise.
         */
        public boolean hasStoreOrderStatus() {
            return fieldSetFlags()[4];
        }


        /**
         * Clears the value of the 'storeOrderStatus' field.
         * @return This builder.
         */
        public com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder clearStoreOrderStatus() {
            storeOrderStatus = null;
            fieldSetFlags()[4] = false;
            return this;
        }

        /**
         * Gets the value of the 'products' field.
         * @return The value.
         */
        public java.util.List<com.fmattaperdomo.kafka.order.avro.model.Product> getProducts() {
            return products;
        }


        /**
         * Sets the value of the 'products' field.
         * @param value The value of 'products'.
         * @return This builder.
         */
        public com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder setProducts(java.util.List<com.fmattaperdomo.kafka.order.avro.model.Product> value) {
            validate(fields()[5], value);
            this.products = value;
            fieldSetFlags()[5] = true;
            return this;
        }

        /**
         * Checks whether the 'products' field has been set.
         * @return True if the 'products' field has been set, false otherwise.
         */
        public boolean hasProducts() {
            return fieldSetFlags()[5];
        }


        /**
         * Clears the value of the 'products' field.
         * @return This builder.
         */
        public com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder clearProducts() {
            products = null;
            fieldSetFlags()[5] = false;
            return this;
        }

        /**
         * Gets the value of the 'price' field.
         * @return The value.
         */
        public java.math.BigDecimal getPrice() {
            return price;
        }


        /**
         * Sets the value of the 'price' field.
         * @param value The value of 'price'.
         * @return This builder.
         */
        public com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder setPrice(java.math.BigDecimal value) {
            validate(fields()[6], value);
            this.price = value;
            fieldSetFlags()[6] = true;
            return this;
        }

        /**
         * Checks whether the 'price' field has been set.
         * @return True if the 'price' field has been set, false otherwise.
         */
        public boolean hasPrice() {
            return fieldSetFlags()[6];
        }


        /**
         * Clears the value of the 'price' field.
         * @return This builder.
         */
        public com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder clearPrice() {
            price = null;
            fieldSetFlags()[6] = false;
            return this;
        }

        /**
         * Gets the value of the 'createdAt' field.
         * @return The value.
         */
        public java.time.Instant getCreatedAt() {
            return createdAt;
        }


        /**
         * Sets the value of the 'createdAt' field.
         * @param value The value of 'createdAt'.
         * @return This builder.
         */
        public com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder setCreatedAt(java.time.Instant value) {
            validate(fields()[7], value);
            this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
            fieldSetFlags()[7] = true;
            return this;
        }

        /**
         * Checks whether the 'createdAt' field has been set.
         * @return True if the 'createdAt' field has been set, false otherwise.
         */
        public boolean hasCreatedAt() {
            return fieldSetFlags()[7];
        }


        /**
         * Clears the value of the 'createdAt' field.
         * @return This builder.
         */
        public com.fmattaperdomo.kafka.order.avro.model.StoreApprovalRequestAvroModel.Builder clearCreatedAt() {
            fieldSetFlags()[7] = false;
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public StoreApprovalRequestAvroModel build() {
            try {
                StoreApprovalRequestAvroModel record = new StoreApprovalRequestAvroModel();
                record.id = fieldSetFlags()[0] ? this.id : (java.lang.String) defaultValue(fields()[0]);
                record.sagaId = fieldSetFlags()[1] ? this.sagaId : (java.lang.String) defaultValue(fields()[1]);
                record.storeId = fieldSetFlags()[2] ? this.storeId : (java.lang.String) defaultValue(fields()[2]);
                record.orderId = fieldSetFlags()[3] ? this.orderId : (java.lang.String) defaultValue(fields()[3]);
                record.storeOrderStatus = fieldSetFlags()[4] ? this.storeOrderStatus : (com.fmattaperdomo.kafka.order.avro.model.StoreOrderStatus) defaultValue(fields()[4]);
                record.products = fieldSetFlags()[5] ? this.products : (java.util.List<com.fmattaperdomo.kafka.order.avro.model.Product>) defaultValue(fields()[5]);
                record.price = fieldSetFlags()[6] ? this.price : (java.math.BigDecimal) defaultValue(fields()[6]);
                record.createdAt = fieldSetFlags()[7] ? this.createdAt : (java.time.Instant) defaultValue(fields()[7]);
                return record;
            } catch (org.apache.avro.AvroMissingFieldException e) {
                throw e;
            } catch (java.lang.Exception e) {
                throw new org.apache.avro.AvroRuntimeException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumWriter<StoreApprovalRequestAvroModel>
            WRITER$ = (org.apache.avro.io.DatumWriter<StoreApprovalRequestAvroModel>)MODEL$.createDatumWriter(SCHEMA$);

    @Override public void writeExternal(java.io.ObjectOutput out)
            throws java.io.IOException {
        WRITER$.write(this, SpecificData.getEncoder(out));
    }

    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumReader<StoreApprovalRequestAvroModel>
            READER$ = (org.apache.avro.io.DatumReader<StoreApprovalRequestAvroModel>)MODEL$.createDatumReader(SCHEMA$);

    @Override public void readExternal(java.io.ObjectInput in)
            throws java.io.IOException {
        READER$.read(this, SpecificData.getDecoder(in));
    }

}











