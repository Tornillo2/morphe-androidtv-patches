package com.google.common.hash;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.primitives.Longs;
import j$.util.Objects;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class LittleEndianByteArray {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final LittleEndianBytes byteArray = makeGetter();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum JavaLittleEndianBytes implements LittleEndianBytes {
        INSTANCE { // from class: com.google.common.hash.LittleEndianByteArray.JavaLittleEndianBytes.1
            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public long getLongLittleEndian(byte[] source, int offset) {
                return Longs.fromBytes(source[offset + 7], source[offset + 6], source[offset + 5], source[offset + 4], source[offset + 3], source[offset + 2], source[offset + 1], source[offset]);
            }

            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public void putLongLittleEndian(byte[] sink, int offset, long value) {
                long j = 255;
                for (int i = 0; i < 8; i++) {
                    sink[offset + i] = (byte) ((value & j) >> (i * 8));
                    j <<= 8;
                }
            }

            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public boolean usesFastPath() {
                return false;
            }
        };

        public static /* synthetic */ JavaLittleEndianBytes[] $values() {
            return new JavaLittleEndianBytes[]{INSTANCE};
        }

        JavaLittleEndianBytes(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface LittleEndianBytes {
        long getLongLittleEndian(byte[] array, int offset);

        void putLongLittleEndian(byte[] array, int offset, long value);

        boolean usesFastPath();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @VisibleForTesting
    public enum UnsafeByteArray implements LittleEndianBytes {
        UNSAFE_LITTLE_ENDIAN { // from class: com.google.common.hash.LittleEndianByteArray.UnsafeByteArray.1
            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public long getLongLittleEndian(byte[] array, int offset) {
                return UnsafeByteArray.theUnsafe.getLong(array, ((long) offset) + ((long) UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET));
            }

            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public void putLongLittleEndian(byte[] array, int offset, long value) {
                UnsafeByteArray.theUnsafe.putLong(array, ((long) offset) + ((long) UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET), value);
            }
        },
        UNSAFE_BIG_ENDIAN { // from class: com.google.common.hash.LittleEndianByteArray.UnsafeByteArray.2
            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public long getLongLittleEndian(byte[] array, int offset) {
                return Long.reverseBytes(UnsafeByteArray.theUnsafe.getLong(array, ((long) offset) + ((long) UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET)));
            }

            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public void putLongLittleEndian(byte[] array, int offset, long value) {
                UnsafeByteArray.theUnsafe.putLong(array, ((long) offset) + ((long) UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET), Long.reverseBytes(value));
            }
        };

        public static final int BYTE_ARRAY_BASE_OFFSET;
        public static final Unsafe theUnsafe;

        static {
            Unsafe unsafe = getUnsafe();
            theUnsafe = unsafe;
            BYTE_ARRAY_BASE_OFFSET = unsafe.arrayBaseOffset(byte[].class);
            if (unsafe.arrayIndexScale(byte[].class) != 1) {
                throw new AssertionError();
            }
        }

        public static Unsafe getUnsafe() {
            try {
                try {
                    return Unsafe.getUnsafe();
                } catch (PrivilegedActionException e) {
                    throw new RuntimeException("Could not initialize intrinsics", e.getCause());
                }
            } catch (SecurityException unused) {
                return (Unsafe) AccessController.doPrivileged(new LittleEndianByteArray$UnsafeByteArray$$ExternalSyntheticLambda0());
            }
        }

        public static /* synthetic */ Unsafe lambda$getUnsafe$0() throws Exception {
            for (Field field : Unsafe.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(null);
                if (Unsafe.class.isInstance(obj)) {
                    return (Unsafe) Unsafe.class.cast(obj);
                }
            }
            throw new NoSuchFieldError("the Unsafe");
        }

        @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
        public boolean usesFastPath() {
            return true;
        }

        UnsafeByteArray(AnonymousClass1 anonymousClass1) {
        }
    }

    public static int load32(byte[] source, int offset) {
        return ((source[offset + 3] & 255) << 24) | (source[offset] & 255) | ((source[offset + 1] & 255) << 8) | ((source[offset + 2] & 255) << 16);
    }

    public static long load64(byte[] input, int offset) {
        return byteArray.getLongLittleEndian(input, offset);
    }

    public static long load64Safely(byte[] input, int offset, int length) {
        int iMin = Math.min(length, 8);
        long j = 0;
        for (int i = 0; i < iMin; i++) {
            j |= (((long) input[offset + i]) & 255) << (i * 8);
        }
        return j;
    }

    public static LittleEndianBytes makeGetter() {
        try {
            if (Objects.equals(System.getProperty("os.arch"), "amd64")) {
                return ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN) ? UnsafeByteArray.UNSAFE_LITTLE_ENDIAN : UnsafeByteArray.UNSAFE_BIG_ENDIAN;
            }
        } catch (Throwable unused) {
        }
        return JavaLittleEndianBytes.INSTANCE;
    }

    public static void store64(byte[] sink, int offset, long value) {
        byteArray.putLongLittleEndian(sink, offset, value);
    }

    public static boolean usingFastPath() {
        return byteArray.usesFastPath();
    }
}
