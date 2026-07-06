package com.amazon.ion;

import com.amazon.ion.BufferConfiguration;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonBufferConfiguration extends BufferConfiguration<IonBufferConfiguration> {
    public final OversizedSymbolTableHandler oversizedSymbolTableHandler;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface OversizedSymbolTableHandler {
        void onOversizedSymbolTable() throws Exception;
    }

    public OversizedSymbolTableHandler getOversizedSymbolTableHandler() {
        return this.oversizedSymbolTableHandler;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder extends BufferConfiguration.Builder<IonBufferConfiguration, Builder> {
        public static final int MINIMUM_MAX_VALUE_SIZE = 5;
        public OversizedSymbolTableHandler oversizedSymbolTableHandler = null;
        public static final BufferConfiguration.OversizedValueHandler NO_OP_OVERSIZED_VALUE_HANDLER = new AnonymousClass1();
        public static final BufferConfiguration.DataHandler NO_OP_DATA_HANDLER = new AnonymousClass2();
        public static final OversizedSymbolTableHandler NO_OP_OVERSIZED_SYMBOL_TABLE_HANDLER = new AnonymousClass3();

        public static Builder standard() {
            return new Builder();
        }

        @Override // com.amazon.ion.BufferConfiguration.Builder
        public BufferConfiguration build() {
            return new IonBufferConfiguration(this);
        }

        @Override // com.amazon.ion.BufferConfiguration.Builder
        public int getMinimumMaximumBufferSize() {
            return 5;
        }

        @Override // com.amazon.ion.BufferConfiguration.Builder
        public BufferConfiguration.DataHandler getNoOpDataHandler() {
            return NO_OP_DATA_HANDLER;
        }

        public OversizedSymbolTableHandler getNoOpOversizedSymbolTableHandler() {
            return NO_OP_OVERSIZED_SYMBOL_TABLE_HANDLER;
        }

        @Override // com.amazon.ion.BufferConfiguration.Builder
        public BufferConfiguration.OversizedValueHandler getNoOpOversizedValueHandler() {
            return NO_OP_OVERSIZED_VALUE_HANDLER;
        }

        public OversizedSymbolTableHandler getOversizedSymbolTableHandler() {
            return this.oversizedSymbolTableHandler;
        }

        public Builder onOversizedSymbolTable(OversizedSymbolTableHandler oversizedSymbolTableHandler) {
            this.oversizedSymbolTableHandler = oversizedSymbolTableHandler;
            return this;
        }

        @Override // com.amazon.ion.BufferConfiguration.Builder
        public IonBufferConfiguration build() {
            return new IonBufferConfiguration(this);
        }

        /* JADX INFO: renamed from: com.amazon.ion.IonBufferConfiguration$Builder$1, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static class AnonymousClass1 implements BufferConfiguration.OversizedValueHandler {
            @Override // com.amazon.ion.BufferConfiguration.OversizedValueHandler
            public void onOversizedValue() {
            }
        }

        /* JADX INFO: renamed from: com.amazon.ion.IonBufferConfiguration$Builder$3, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static class AnonymousClass3 implements OversizedSymbolTableHandler {
            @Override // com.amazon.ion.IonBufferConfiguration.OversizedSymbolTableHandler
            public void onOversizedSymbolTable() {
            }
        }

        /* JADX INFO: renamed from: com.amazon.ion.IonBufferConfiguration$Builder$2, reason: invalid class name */
        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static class AnonymousClass2 implements BufferConfiguration.DataHandler {
            @Override // com.amazon.ion.BufferConfiguration.DataHandler
            public void onData(int i) {
            }
        }
    }

    public IonBufferConfiguration(Builder builder) {
        super(builder);
        OversizedSymbolTableHandler oversizedSymbolTableHandler = builder.oversizedSymbolTableHandler;
        if (oversizedSymbolTableHandler != null) {
            this.oversizedSymbolTableHandler = oversizedSymbolTableHandler;
        } else {
            requireUnlimitedBufferSize();
            this.oversizedSymbolTableHandler = Builder.NO_OP_OVERSIZED_SYMBOL_TABLE_HANDLER;
        }
    }
}
