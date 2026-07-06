package com.amazon.minerva.client.thirdparty.metric;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import j$.util.Objects;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ValueType {
    public final Class<?> clazz;
    public static final ValueType BOOLEAN = new AnonymousClass1("BOOLEAN", 0, Boolean.class);
    public static final ValueType INTEGER = new AnonymousClass2("INTEGER", 1, Long.class);
    public static final ValueType FLOAT = new AnonymousClass3("FLOAT", 2, Double.class);
    public static final ValueType TIMESTAMP = new AnonymousClass4("TIMESTAMP", 3, Timestamp.class);
    public static final ValueType STRING = new AnonymousClass5("STRING", 4, String.class);
    public static final ValueType SYMBOL = new AnonymousClass6("SYMBOL", 5, String.class);
    public static final ValueType AGGREGATED_INTEGER = new AnonymousClass7("AGGREGATED_INTEGER", 6, AggregatedLong.class);
    public static final ValueType AGGREGATED_FLOAT = new AnonymousClass8("AGGREGATED_FLOAT", 7, AggregatedDouble.class);
    public static final /* synthetic */ ValueType[] $VALUES = $values();

    /* JADX INFO: renamed from: com.amazon.minerva.client.thirdparty.metric.ValueType$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum AnonymousClass1 extends ValueType {
        public AnonymousClass1(String str, int i, Class cls) {
            super(str, i, cls);
        }

        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> int getSizeInBytes(T t) {
            return 1;
        }

        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> boolean isEmpty(T t) {
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> String toString(T t) {
            return Boolean.toString(((Boolean) t).booleanValue());
        }
    }

    /* JADX INFO: renamed from: com.amazon.minerva.client.thirdparty.metric.ValueType$2, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum AnonymousClass2 extends ValueType {
        public AnonymousClass2(String str, int i, Class cls) {
            super(str, i, cls);
        }

        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> int getSizeInBytes(T t) {
            return 8;
        }

        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> boolean isEmpty(T t) {
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> String toString(T t) {
            return Long.toString(((Long) t).longValue());
        }
    }

    /* JADX INFO: renamed from: com.amazon.minerva.client.thirdparty.metric.ValueType$3, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum AnonymousClass3 extends ValueType {
        public AnonymousClass3(String str, int i, Class cls) {
            super(str, i, cls);
        }

        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> int getSizeInBytes(T t) {
            return 8;
        }

        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> boolean isEmpty(T t) {
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> String toString(T t) {
            return Double.toString(((Double) t).doubleValue());
        }
    }

    /* JADX INFO: renamed from: com.amazon.minerva.client.thirdparty.metric.ValueType$4, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum AnonymousClass4 extends ValueType {
        public AnonymousClass4(String str, int i, Class cls) {
            super(str, i, cls);
        }

        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> int getSizeInBytes(T t) {
            return 8;
        }

        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> boolean isEmpty(T t) {
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> String toString(T t) {
            Timestamp timestamp = (Timestamp) t;
            return timestamp.epochMillis + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + timestamp.getTimeZoneOffset();
        }
    }

    /* JADX INFO: renamed from: com.amazon.minerva.client.thirdparty.metric.ValueType$5, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum AnonymousClass5 extends ValueType {
        public AnonymousClass5(String str, int i, Class cls) {
            super(str, i, cls);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> int getSizeInBytes(T t) {
            Objects.requireNonNull(t, "value can not be null.");
            return ((String) t).length();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> boolean isEmpty(T t) {
            Objects.requireNonNull(t, "value can not be null.");
            return ((String) t).isEmpty();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> String toString(T t) {
            return (String) t;
        }
    }

    /* JADX INFO: renamed from: com.amazon.minerva.client.thirdparty.metric.ValueType$6, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum AnonymousClass6 extends ValueType {
        public AnonymousClass6(String str, int i, Class cls) {
            super(str, i, cls);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> int getSizeInBytes(T t) {
            Objects.requireNonNull(t, "value can not be null.");
            return ((String) t).length();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> boolean isEmpty(T t) {
            Objects.requireNonNull(t, "value can not be null.");
            return ((String) t).isEmpty();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> String toString(T t) {
            return (String) t;
        }
    }

    /* JADX INFO: renamed from: com.amazon.minerva.client.thirdparty.metric.ValueType$7, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum AnonymousClass7 extends ValueType {
        public AnonymousClass7(String str, int i, Class cls) {
            super(str, i, cls);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> int getSizeInBytes(T t) {
            Objects.requireNonNull(t, "value can not be null.");
            return ((AggregatedLong) t).getSizeInBytes();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> boolean isEmpty(T t) {
            Objects.requireNonNull(t, "value can not be null.");
            return ((AggregatedLong) t).isEmpty();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> String toString(T t) {
            Objects.requireNonNull(t, "value can not be null.");
            return ((AggregatedLong) t).toString();
        }
    }

    /* JADX INFO: renamed from: com.amazon.minerva.client.thirdparty.metric.ValueType$8, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum AnonymousClass8 extends ValueType {
        public AnonymousClass8(String str, int i, Class cls) {
            super(str, i, cls);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> int getSizeInBytes(T t) {
            Objects.requireNonNull(t, "value can not be null.");
            return ((AggregatedDouble) t).getSizeInBytes();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> boolean isEmpty(T t) {
            Objects.requireNonNull(t, "value can not be null.");
            return ((AggregatedDouble) t).isEmpty();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.minerva.client.thirdparty.metric.ValueType
        public <T> String toString(T t) {
            Objects.requireNonNull(t, "value can not be null.");
            return ((AggregatedDouble) t).toString();
        }
    }

    public static /* synthetic */ ValueType[] $values() {
        return new ValueType[]{BOOLEAN, INTEGER, FLOAT, TIMESTAMP, STRING, SYMBOL, AGGREGATED_INTEGER, AGGREGATED_FLOAT};
    }

    public /* synthetic */ ValueType(String str, int i, Class cls, AnonymousClass1 anonymousClass1) {
        this(str, i, cls);
    }

    public static ValueType fromInt(int i) {
        if (i < 0 || i > values().length) {
            throw new IllegalArgumentException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("invalid index: ", i));
        }
        return values()[i];
    }

    public static ValueType valueOf(String str) {
        return (ValueType) Enum.valueOf(ValueType.class, str);
    }

    public static ValueType[] values() {
        return (ValueType[]) $VALUES.clone();
    }

    public Class<?> getCompatibleClass() {
        return this.clazz;
    }

    public abstract <T> int getSizeInBytes(T t);

    public abstract <T> boolean isEmpty(T t);

    public <T> TypedValue<T> of(T t) {
        return new TypedValue<>(this, t);
    }

    public abstract <T> String toString(T t);

    public ValueType(String str, int i, Class cls) {
        this.clazz = cls;
    }
}
