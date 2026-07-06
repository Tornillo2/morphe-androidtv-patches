package com.amazon.ion;

import com.amazon.ion.BufferConfiguration;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class BufferConfiguration<Configuration extends BufferConfiguration<Configuration>> {
    public final DataHandler dataHandler;
    public final int initialBufferSize;
    public final int maximumBufferSize;
    public final OversizedValueHandler oversizedValueHandler;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class Builder<Configuration extends BufferConfiguration<Configuration>, BuilderType extends Builder<Configuration, BuilderType>> {
        public static final int DEFAULT_INITIAL_BUFFER_SIZE = 32768;
        public int initialBufferSize = 32768;
        public int maximumBufferSize = Integer.MAX_VALUE;
        public OversizedValueHandler oversizedValueHandler = null;
        public DataHandler dataHandler = null;

        public abstract Configuration build();

        public final DataHandler getDataHandler() {
            return this.dataHandler;
        }

        public final int getInitialBufferSize() {
            return this.initialBufferSize;
        }

        public int getMaximumBufferSize() {
            return this.maximumBufferSize;
        }

        public abstract int getMinimumMaximumBufferSize();

        public abstract DataHandler getNoOpDataHandler();

        public abstract OversizedValueHandler getNoOpOversizedValueHandler();

        public final OversizedValueHandler getOversizedValueHandler() {
            return this.oversizedValueHandler;
        }

        public final BuilderType onData(DataHandler dataHandler) {
            this.dataHandler = dataHandler;
            return this;
        }

        public final BuilderType onOversizedValue(OversizedValueHandler oversizedValueHandler) {
            this.oversizedValueHandler = oversizedValueHandler;
            return this;
        }

        public final BuilderType withInitialBufferSize(int i) {
            this.initialBufferSize = i;
            return this;
        }

        public final BuilderType withMaximumBufferSize(int i) {
            this.maximumBufferSize = i;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface DataHandler {
        void onData(int i) throws Exception;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface OversizedValueHandler {
        void onOversizedValue() throws Exception;
    }

    public BufferConfiguration(Builder<Configuration, ?> builder) {
        int i = builder.initialBufferSize;
        this.initialBufferSize = i;
        int maximumBufferSize = builder.getMaximumBufferSize();
        this.maximumBufferSize = maximumBufferSize;
        if (i > maximumBufferSize) {
            throw new IllegalArgumentException("Initial buffer size may not exceed the maximum buffer size.");
        }
        if (maximumBufferSize < 5) {
            throw new IllegalArgumentException(String.format("Maximum buffer size must be at least %d bytes.", 5));
        }
        OversizedValueHandler oversizedValueHandler = builder.oversizedValueHandler;
        if (oversizedValueHandler == null) {
            requireUnlimitedBufferSize();
            this.oversizedValueHandler = builder.getNoOpOversizedValueHandler();
        } else {
            this.oversizedValueHandler = oversizedValueHandler;
        }
        DataHandler dataHandler = builder.dataHandler;
        if (dataHandler == null) {
            this.dataHandler = builder.getNoOpDataHandler();
        } else {
            this.dataHandler = dataHandler;
        }
    }

    public final DataHandler getDataHandler() {
        return this.dataHandler;
    }

    public final int getInitialBufferSize() {
        return this.initialBufferSize;
    }

    public final int getMaximumBufferSize() {
        return this.maximumBufferSize;
    }

    public final OversizedValueHandler getOversizedValueHandler() {
        return this.oversizedValueHandler;
    }

    public void requireUnlimitedBufferSize() {
        if (this.maximumBufferSize < Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Must specify an OversizedValueHandler when a maximum buffer size is specified.");
        }
    }
}
