package androidx.work;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.room.TypeConverter;
import j$.util.DesugarCollections;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Data {

    @SuppressLint({"MinMaxConstant"})
    public static final int MAX_DATA_BYTES = 10240;
    public Map<String, Object> mValues;
    public static final String TAG = Logger.tagWithPrefix("Data");
    public static final Data EMPTY = new Builder().build();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public Map<String, Object> mValues = new HashMap();

        @NonNull
        public Data build() throws Throwable {
            Data data = new Data((Map<String, ?>) this.mValues);
            Data.toByteArrayInternal(data);
            return data;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder put(@NonNull String key, @Nullable Object value) {
            if (value == null) {
                this.mValues.put(key, null);
                return this;
            }
            Class<?> cls = value.getClass();
            if (cls == Boolean.class || cls == Byte.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == String.class || cls == Boolean[].class || cls == Byte[].class || cls == Integer[].class || cls == Long[].class || cls == Float[].class || cls == Double[].class || cls == String[].class) {
                this.mValues.put(key, value);
                return this;
            }
            if (cls == boolean[].class) {
                this.mValues.put(key, Data.convertPrimitiveBooleanArray((boolean[]) value));
                return this;
            }
            if (cls == byte[].class) {
                this.mValues.put(key, Data.convertPrimitiveByteArray((byte[]) value));
                return this;
            }
            if (cls == int[].class) {
                this.mValues.put(key, Data.convertPrimitiveIntArray((int[]) value));
                return this;
            }
            if (cls == long[].class) {
                this.mValues.put(key, Data.convertPrimitiveLongArray((long[]) value));
                return this;
            }
            if (cls == float[].class) {
                this.mValues.put(key, Data.convertPrimitiveFloatArray((float[]) value));
                return this;
            }
            if (cls != double[].class) {
                throw new IllegalArgumentException(String.format("Key %s has invalid type %s", key, cls));
            }
            this.mValues.put(key, Data.convertPrimitiveDoubleArray((double[]) value));
            return this;
        }

        @NonNull
        public Builder putAll(@NonNull Data data) {
            putAll(data.mValues);
            return this;
        }

        @NonNull
        public Builder putBoolean(@NonNull String key, boolean value) {
            this.mValues.put(key, Boolean.valueOf(value));
            return this;
        }

        @NonNull
        public Builder putBooleanArray(@NonNull String key, @NonNull boolean[] value) {
            this.mValues.put(key, Data.convertPrimitiveBooleanArray(value));
            return this;
        }

        @NonNull
        public Builder putByte(@NonNull String key, byte value) {
            this.mValues.put(key, Byte.valueOf(value));
            return this;
        }

        @NonNull
        public Builder putByteArray(@NonNull String key, @NonNull byte[] value) {
            this.mValues.put(key, Data.convertPrimitiveByteArray(value));
            return this;
        }

        @NonNull
        public Builder putDouble(@NonNull String key, double value) {
            this.mValues.put(key, Double.valueOf(value));
            return this;
        }

        @NonNull
        public Builder putDoubleArray(@NonNull String key, @NonNull double[] value) {
            this.mValues.put(key, Data.convertPrimitiveDoubleArray(value));
            return this;
        }

        @NonNull
        public Builder putFloat(@NonNull String key, float value) {
            this.mValues.put(key, Float.valueOf(value));
            return this;
        }

        @NonNull
        public Builder putFloatArray(@NonNull String key, @NonNull float[] value) {
            this.mValues.put(key, Data.convertPrimitiveFloatArray(value));
            return this;
        }

        @NonNull
        public Builder putInt(@NonNull String key, int value) {
            this.mValues.put(key, Integer.valueOf(value));
            return this;
        }

        @NonNull
        public Builder putIntArray(@NonNull String key, @NonNull int[] value) {
            this.mValues.put(key, Data.convertPrimitiveIntArray(value));
            return this;
        }

        @NonNull
        public Builder putLong(@NonNull String key, long value) {
            this.mValues.put(key, Long.valueOf(value));
            return this;
        }

        @NonNull
        public Builder putLongArray(@NonNull String key, @NonNull long[] value) {
            this.mValues.put(key, Data.convertPrimitiveLongArray(value));
            return this;
        }

        @NonNull
        public Builder putString(@NonNull String key, @Nullable String value) {
            this.mValues.put(key, value);
            return this;
        }

        @NonNull
        public Builder putStringArray(@NonNull String key, @NonNull String[] value) {
            this.mValues.put(key, value);
            return this;
        }

        @NonNull
        public Builder putAll(@NonNull Map<String, Object> values) {
            for (Map.Entry<String, Object> entry : values.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
            return this;
        }
    }

    public Data() {
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Boolean[] convertPrimitiveBooleanArray(@NonNull boolean[] value) {
        Boolean[] boolArr = new Boolean[value.length];
        for (int i = 0; i < value.length; i++) {
            boolArr[i] = Boolean.valueOf(value[i]);
        }
        return boolArr;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Byte[] convertPrimitiveByteArray(@NonNull byte[] value) {
        Byte[] bArr = new Byte[value.length];
        for (int i = 0; i < value.length; i++) {
            bArr[i] = Byte.valueOf(value[i]);
        }
        return bArr;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Double[] convertPrimitiveDoubleArray(@NonNull double[] value) {
        Double[] dArr = new Double[value.length];
        for (int i = 0; i < value.length; i++) {
            dArr[i] = Double.valueOf(value[i]);
        }
        return dArr;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Float[] convertPrimitiveFloatArray(@NonNull float[] value) {
        Float[] fArr = new Float[value.length];
        for (int i = 0; i < value.length; i++) {
            fArr[i] = Float.valueOf(value[i]);
        }
        return fArr;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Integer[] convertPrimitiveIntArray(@NonNull int[] value) {
        Integer[] numArr = new Integer[value.length];
        for (int i = 0; i < value.length; i++) {
            numArr[i] = Integer.valueOf(value[i]);
        }
        return numArr;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Long[] convertPrimitiveLongArray(@NonNull long[] value) {
        Long[] lArr = new Long[value.length];
        for (int i = 0; i < value.length; i++) {
            lArr[i] = Long.valueOf(value[i]);
        }
        return lArr;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static boolean[] convertToPrimitiveArray(@NonNull Boolean[] array) {
        boolean[] zArr = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            zArr[i] = array[i].booleanValue();
        }
        return zArr;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:4|(5:55|5|56|6|(2:8|9))|49|16|53|20|33|34) */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0035, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0036, code lost:
    
        android.util.Log.e(androidx.work.Data.TAG, "Error in Data#fromByteArray: ", r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0040, code lost:
    
        android.util.Log.e(androidx.work.Data.TAG, "Error in Data#fromByteArray: ", r7);
     */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0031 A[EXC_TOP_SPLITTER, PHI: r3
      0x0031: PHI (r3v8 java.io.ObjectInputStream) = (r3v7 java.io.ObjectInputStream), (r3v10 java.io.ObjectInputStream) binds: [B:31:0x0056, B:7:0x001b] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    @androidx.annotation.NonNull
    @androidx.room.TypeConverter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.work.Data fromByteArray(@androidx.annotation.NonNull byte[] r7) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "Error in Data#fromByteArray: "
            int r1 = r7.length
            r2 = 10240(0x2800, float:1.4349E-41)
            if (r1 > r2) goto L76
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            r2.<init>(r7)
            r7 = 0
            java.io.ObjectInputStream r3 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L46 java.lang.ClassNotFoundException -> L4a java.io.IOException -> L4f
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L46 java.lang.ClassNotFoundException -> L4a java.io.IOException -> L4f
            int r7 = r3.readInt()     // Catch: java.lang.Throwable -> L2b java.lang.ClassNotFoundException -> L2d java.io.IOException -> L2f
        L1b:
            if (r7 <= 0) goto L31
            java.lang.String r4 = r3.readUTF()     // Catch: java.lang.Throwable -> L2b java.lang.ClassNotFoundException -> L2d java.io.IOException -> L2f
            java.lang.Object r5 = r3.readObject()     // Catch: java.lang.Throwable -> L2b java.lang.ClassNotFoundException -> L2d java.io.IOException -> L2f
            r1.put(r4, r5)     // Catch: java.lang.Throwable -> L2b java.lang.ClassNotFoundException -> L2d java.io.IOException -> L2f
            int r7 = r7 + (-1)
            goto L1b
        L2b:
            r7 = move-exception
            goto L5f
        L2d:
            r7 = move-exception
            goto L51
        L2f:
            r7 = move-exception
            goto L51
        L31:
            r3.close()     // Catch: java.io.IOException -> L35
            goto L3b
        L35:
            r7 = move-exception
            java.lang.String r3 = androidx.work.Data.TAG
            android.util.Log.e(r3, r0, r7)
        L3b:
            r2.close()     // Catch: java.io.IOException -> L3f
            goto L59
        L3f:
            r7 = move-exception
            java.lang.String r2 = androidx.work.Data.TAG
            android.util.Log.e(r2, r0, r7)
            goto L59
        L46:
            r1 = move-exception
            r3 = r7
            r7 = r1
            goto L5f
        L4a:
            r3 = move-exception
        L4b:
            r6 = r3
            r3 = r7
            r7 = r6
            goto L51
        L4f:
            r3 = move-exception
            goto L4b
        L51:
            java.lang.String r4 = androidx.work.Data.TAG     // Catch: java.lang.Throwable -> L2b
            android.util.Log.e(r4, r0, r7)     // Catch: java.lang.Throwable -> L2b
            if (r3 == 0) goto L3b
            goto L31
        L59:
            androidx.work.Data r7 = new androidx.work.Data
            r7.<init>(r1)
            return r7
        L5f:
            if (r3 == 0) goto L6b
            r3.close()     // Catch: java.io.IOException -> L65
            goto L6b
        L65:
            r1 = move-exception
            java.lang.String r3 = androidx.work.Data.TAG
            android.util.Log.e(r3, r0, r1)
        L6b:
            r2.close()     // Catch: java.io.IOException -> L6f
            goto L75
        L6f:
            r1 = move-exception
            java.lang.String r2 = androidx.work.Data.TAG
            android.util.Log.e(r2, r0, r1)
        L75:
            throw r7
        L76:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "Data cannot occupy more than 10240 bytes when serialized"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.Data.fromByteArray(byte[]):androidx.work.Data");
    }

    @NonNull
    @TypeConverter
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static byte[] toByteArrayInternal(@NonNull Data data) throws Throwable {
        ObjectOutputStream objectOutputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            objectOutputStream.writeInt(data.mValues.size());
            for (Map.Entry<String, Object> entry : data.mValues.entrySet()) {
                objectOutputStream.writeUTF(entry.getKey());
                objectOutputStream.writeObject(entry.getValue());
            }
            try {
                objectOutputStream.close();
            } catch (IOException e2) {
                Log.e(TAG, "Error in Data#toByteArray: ", e2);
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException e3) {
                Log.e(TAG, "Error in Data#toByteArray: ", e3);
            }
            if (byteArrayOutputStream.size() <= 10240) {
                return byteArrayOutputStream.toByteArray();
            }
            throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized");
        } catch (IOException e4) {
            e = e4;
            objectOutputStream2 = objectOutputStream;
            Log.e(TAG, "Error in Data#toByteArray: ", e);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (objectOutputStream2 != null) {
                try {
                    objectOutputStream2.close();
                } catch (IOException e5) {
                    Log.e(TAG, "Error in Data#toByteArray: ", e5);
                }
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException e6) {
                Log.e(TAG, "Error in Data#toByteArray: ", e6);
            }
            return byteArray;
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream2 = objectOutputStream;
            if (objectOutputStream2 != null) {
                try {
                    objectOutputStream2.close();
                } catch (IOException e7) {
                    Log.e(TAG, "Error in Data#toByteArray: ", e7);
                }
            }
            try {
                byteArrayOutputStream.close();
                throw th;
            } catch (IOException e8) {
                Log.e(TAG, "Error in Data#toByteArray: ", e8);
                throw th;
            }
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Data.class != o.getClass()) {
            return false;
        }
        Data data = (Data) o;
        Set<String> setKeySet = this.mValues.keySet();
        if (!setKeySet.equals(data.mValues.keySet())) {
            return false;
        }
        for (String str : setKeySet) {
            Object obj = this.mValues.get(str);
            Object obj2 = data.mValues.get(str);
            if (!((obj == null || obj2 == null) ? obj == obj2 : ((obj instanceof Object[]) && (obj2 instanceof Object[])) ? Arrays.deepEquals((Object[]) obj, (Object[]) obj2) : obj.equals(obj2))) {
                return false;
            }
        }
        return true;
    }

    public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        Object obj = this.mValues.get(key);
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : defaultValue;
    }

    @Nullable
    public boolean[] getBooleanArray(@NonNull String key) {
        Object obj = this.mValues.get(key);
        if (obj instanceof Boolean[]) {
            return convertToPrimitiveArray((Boolean[]) obj);
        }
        return null;
    }

    public byte getByte(@NonNull String key, byte defaultValue) {
        Object obj = this.mValues.get(key);
        return obj instanceof Byte ? ((Byte) obj).byteValue() : defaultValue;
    }

    @Nullable
    public byte[] getByteArray(@NonNull String key) {
        Object obj = this.mValues.get(key);
        if (obj instanceof Byte[]) {
            return convertToPrimitiveArray((Byte[]) obj);
        }
        return null;
    }

    public double getDouble(@NonNull String key, double defaultValue) {
        Object obj = this.mValues.get(key);
        return obj instanceof Double ? ((Double) obj).doubleValue() : defaultValue;
    }

    @Nullable
    public double[] getDoubleArray(@NonNull String key) {
        Object obj = this.mValues.get(key);
        if (obj instanceof Double[]) {
            return convertToPrimitiveArray((Double[]) obj);
        }
        return null;
    }

    public float getFloat(@NonNull String key, float defaultValue) {
        Object obj = this.mValues.get(key);
        return obj instanceof Float ? ((Float) obj).floatValue() : defaultValue;
    }

    @Nullable
    public float[] getFloatArray(@NonNull String key) {
        Object obj = this.mValues.get(key);
        if (obj instanceof Float[]) {
            return convertToPrimitiveArray((Float[]) obj);
        }
        return null;
    }

    public int getInt(@NonNull String key, int defaultValue) {
        Object obj = this.mValues.get(key);
        return obj instanceof Integer ? ((Integer) obj).intValue() : defaultValue;
    }

    @Nullable
    public int[] getIntArray(@NonNull String key) {
        Object obj = this.mValues.get(key);
        if (obj instanceof Integer[]) {
            return convertToPrimitiveArray((Integer[]) obj);
        }
        return null;
    }

    @NonNull
    public Map<String, Object> getKeyValueMap() {
        return DesugarCollections.unmodifiableMap(this.mValues);
    }

    public long getLong(@NonNull String key, long defaultValue) {
        Object obj = this.mValues.get(key);
        return obj instanceof Long ? ((Long) obj).longValue() : defaultValue;
    }

    @Nullable
    public long[] getLongArray(@NonNull String key) {
        Object obj = this.mValues.get(key);
        if (obj instanceof Long[]) {
            return convertToPrimitiveArray((Long[]) obj);
        }
        return null;
    }

    @Nullable
    public String getString(@NonNull String key) {
        Object obj = this.mValues.get(key);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    @Nullable
    public String[] getStringArray(@NonNull String key) {
        Object obj = this.mValues.get(key);
        if (obj instanceof String[]) {
            return (String[]) obj;
        }
        return null;
    }

    public <T> boolean hasKeyWithValueOfType(@NonNull String key, @NonNull Class<T> klass) {
        Object obj = this.mValues.get(key);
        return obj != null && klass.isAssignableFrom(obj.getClass());
    }

    public int hashCode() {
        return this.mValues.hashCode() * 31;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @VisibleForTesting
    public int size() {
        return this.mValues.size();
    }

    @NonNull
    public byte[] toByteArray() {
        return toByteArrayInternal(this);
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder("Data {");
        if (!this.mValues.isEmpty()) {
            for (String str : this.mValues.keySet()) {
                sb.append(str);
                sb.append(" : ");
                Object obj = this.mValues.get(str);
                if (obj instanceof Object[]) {
                    sb.append(Arrays.toString((Object[]) obj));
                } else {
                    sb.append(obj);
                }
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public Data(@NonNull Data other) {
        this.mValues = new HashMap(other.mValues);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Data(@NonNull Map<String, ?> values) {
        this.mValues = new HashMap(values);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static byte[] convertToPrimitiveArray(@NonNull Byte[] array) {
        byte[] bArr = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            bArr[i] = array[i].byteValue();
        }
        return bArr;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static int[] convertToPrimitiveArray(@NonNull Integer[] array) {
        int[] iArr = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            iArr[i] = array[i].intValue();
        }
        return iArr;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static long[] convertToPrimitiveArray(@NonNull Long[] array) {
        long[] jArr = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            jArr[i] = array[i].longValue();
        }
        return jArr;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static float[] convertToPrimitiveArray(@NonNull Float[] array) {
        float[] fArr = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            fArr[i] = array[i].floatValue();
        }
        return fArr;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static double[] convertToPrimitiveArray(@NonNull Double[] array) {
        double[] dArr = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            dArr[i] = array[i].doubleValue();
        }
        return dArr;
    }
}
