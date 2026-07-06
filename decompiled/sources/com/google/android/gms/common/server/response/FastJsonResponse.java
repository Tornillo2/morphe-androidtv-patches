package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.builder.ToStringStyle;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@ShowFirstParty
@KeepForSdk
public abstract class FastJsonResponse {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @ShowFirstParty
    public interface FieldConverter<I, O> {
        int zaa();

        int zab();

        @Nullable
        Object zac(@NonNull Object obj);

        @NonNull
        Object zad(@NonNull Object obj);
    }

    @NonNull
    public static final Object zaD(@NonNull Field field, @Nullable Object obj) {
        return field.zak != null ? field.zaf(obj) : obj;
    }

    public static final void zaF(StringBuilder sb, Field field, Object obj) {
        int i = field.zaa;
        if (i == 11) {
            Class cls = field.zag;
            Preconditions.checkNotNull(cls);
            sb.append(((FastJsonResponse) cls.cast(obj)).toString());
        } else {
            if (i != 7) {
                sb.append(obj);
                return;
            }
            sb.append(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
            sb.append(JsonUtils.escapeString((String) obj));
            sb.append(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
        }
    }

    public static final void zaG(String str) {
        if (Log.isLoggable("FastJsonResponse", 6)) {
            Log.e("FastJsonResponse", "Output field (" + str + ") has a null value, but expected a primitive");
        }
    }

    @KeepForSdk
    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(@NonNull Field field, @NonNull String str, @Nullable ArrayList<T> arrayList) {
        throw new UnsupportedOperationException("Concrete type array not supported");
    }

    @KeepForSdk
    public <T extends FastJsonResponse> void addConcreteTypeInternal(@NonNull Field field, @NonNull String str, @NonNull T t) {
        throw new UnsupportedOperationException("Concrete type not supported");
    }

    @NonNull
    @KeepForSdk
    public abstract Map<String, Field<?, ?>> getFieldMappings();

    @Nullable
    @KeepForSdk
    public Object getFieldValue(@NonNull Field field) {
        String str = field.zae;
        if (field.zag == null) {
            return getValueObject(str);
        }
        Preconditions.checkState(getValueObject(str) == null, "Concrete field shouldn't be value object: %s", field.zae);
        try {
            return getClass().getMethod("get" + Character.toUpperCase(str.charAt(0)) + str.substring(1), null).invoke(this, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    @KeepForSdk
    public abstract Object getValueObject(@NonNull String str);

    @KeepForSdk
    public boolean isFieldSet(@NonNull Field field) {
        if (field.zac != 11) {
            return isPrimitiveFieldSet(field.zae);
        }
        if (field.zad) {
            throw new UnsupportedOperationException("Concrete type arrays not supported");
        }
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    @KeepForSdk
    public abstract boolean isPrimitiveFieldSet(@NonNull String str);

    @KeepForSdk
    public void setBooleanInternal(@NonNull Field<?, ?> field, @NonNull String str, boolean z) {
        throw new UnsupportedOperationException("Boolean not supported");
    }

    @KeepForSdk
    public void setDecodedBytesInternal(@NonNull Field<?, ?> field, @NonNull String str, @Nullable byte[] bArr) {
        throw new UnsupportedOperationException("byte[] not supported");
    }

    @KeepForSdk
    public void setIntegerInternal(@NonNull Field<?, ?> field, @NonNull String str, int i) {
        throw new UnsupportedOperationException("Integer not supported");
    }

    @KeepForSdk
    public void setLongInternal(@NonNull Field<?, ?> field, @NonNull String str, long j) {
        throw new UnsupportedOperationException("Long not supported");
    }

    @KeepForSdk
    public void setStringInternal(@NonNull Field<?, ?> field, @NonNull String str, @Nullable String str2) {
        throw new UnsupportedOperationException("String not supported");
    }

    @KeepForSdk
    public void setStringMapInternal(@NonNull Field<?, ?> field, @NonNull String str, @Nullable Map<String, String> map) {
        throw new UnsupportedOperationException("String map not supported");
    }

    @KeepForSdk
    public void setStringsInternal(@NonNull Field<?, ?> field, @NonNull String str, @Nullable ArrayList<String> arrayList) {
        throw new UnsupportedOperationException("String list not supported");
    }

    @NonNull
    @KeepForSdk
    public String toString() {
        Map<String, Field<?, ?>> fieldMappings = getFieldMappings();
        StringBuilder sb = new StringBuilder(100);
        for (String str : fieldMappings.keySet()) {
            Field<?, ?> field = fieldMappings.get(str);
            if (isFieldSet(field)) {
                Object fieldValue = getFieldValue(field);
                if (field.zak != null) {
                    fieldValue = field.zaf(fieldValue);
                }
                if (sb.length() == 0) {
                    sb.append("{");
                } else {
                    sb.append(",");
                }
                sb.append(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
                sb.append(str);
                sb.append("\":");
                if (fieldValue != null) {
                    switch (field.zac) {
                        case 8:
                            sb.append(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
                            sb.append(Base64.encodeToString((byte[]) fieldValue, 0));
                            sb.append(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
                            break;
                        case 9:
                            sb.append(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
                            sb.append(Base64.encodeToString((byte[]) fieldValue, 10));
                            sb.append(ToStringStyle.JsonToStringStyle.FIELD_NAME_QUOTE);
                            break;
                        case 10:
                            MapUtils.writeStringMapToJson(sb, (HashMap) fieldValue);
                            break;
                        default:
                            if (field.zab) {
                                ArrayList arrayList = (ArrayList) fieldValue;
                                sb.append("[");
                                int size = arrayList.size();
                                for (int i = 0; i < size; i++) {
                                    if (i > 0) {
                                        sb.append(",");
                                    }
                                    Object obj = arrayList.get(i);
                                    if (obj != null) {
                                        zaF(sb, field, obj);
                                    }
                                }
                                sb.append("]");
                            } else {
                                zaF(sb, field, fieldValue);
                            }
                            break;
                    }
                } else {
                    sb.append(AbstractJsonLexerKt.NULL);
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        } else {
            sb.append("{}");
        }
        return sb.toString();
    }

    public final void zaA(@NonNull Field field, @Nullable String str) {
        if (field.zak != null) {
            zaE(field, str);
        } else {
            setStringInternal(field, field.zae, str);
        }
    }

    public final void zaB(@NonNull Field field, @Nullable Map map) {
        if (field.zak != null) {
            zaE(field, map);
        } else {
            setStringMapInternal(field, field.zae, map);
        }
    }

    public final void zaC(@NonNull Field field, @Nullable ArrayList arrayList) {
        if (field.zak != null) {
            zaE(field, arrayList);
        } else {
            setStringsInternal(field, field.zae, arrayList);
        }
    }

    public final void zaE(Field field, @Nullable Object obj) {
        int i = field.zac;
        Object objZae = field.zae(obj);
        String str = field.zae;
        switch (i) {
            case 0:
                setIntegerInternal(field, str, ((Integer) objZae).intValue());
                return;
            case 1:
                zaf(field, str, (BigInteger) objZae);
                return;
            case 2:
                setLongInternal(field, str, ((Long) objZae).longValue());
                return;
            case 3:
            default:
                throw new IllegalStateException(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Unsupported type for conversion: ", i));
            case 4:
                zan(field, str, ((Double) objZae).doubleValue());
                return;
            case 5:
                zab(field, str, (BigDecimal) objZae);
                return;
            case 6:
                setBooleanInternal(field, str, ((Boolean) objZae).booleanValue());
                return;
            case 7:
                setStringInternal(field, str, (String) objZae);
                return;
            case 8:
            case 9:
                setDecodedBytesInternal(field, str, (byte[]) objZae);
                return;
        }
    }

    public final void zaa(@NonNull Field field, @Nullable BigDecimal bigDecimal) {
        if (field.zak != null) {
            zaE(field, bigDecimal);
        } else {
            zab(field, field.zae, bigDecimal);
        }
    }

    public void zab(@NonNull Field field, @NonNull String str, @Nullable BigDecimal bigDecimal) {
        throw new UnsupportedOperationException("BigDecimal not supported");
    }

    public final void zac(@NonNull Field field, @Nullable ArrayList arrayList) {
        if (field.zak != null) {
            zaE(field, arrayList);
        } else {
            zad(field, field.zae, arrayList);
        }
    }

    public void zad(@NonNull Field field, @NonNull String str, @Nullable ArrayList arrayList) {
        throw new UnsupportedOperationException("BigDecimal list not supported");
    }

    public final void zae(@NonNull Field field, @Nullable BigInteger bigInteger) {
        if (field.zak != null) {
            zaE(field, bigInteger);
        } else {
            zaf(field, field.zae, bigInteger);
        }
    }

    public void zaf(@NonNull Field field, @NonNull String str, @Nullable BigInteger bigInteger) {
        throw new UnsupportedOperationException("BigInteger not supported");
    }

    public final void zag(@NonNull Field field, @Nullable ArrayList arrayList) {
        if (field.zak != null) {
            zaE(field, arrayList);
        } else {
            zah(field, field.zae, arrayList);
        }
    }

    public void zah(@NonNull Field field, @NonNull String str, @Nullable ArrayList arrayList) {
        throw new UnsupportedOperationException("BigInteger list not supported");
    }

    public final void zai(@NonNull Field field, boolean z) {
        if (field.zak != null) {
            zaE(field, Boolean.valueOf(z));
        } else {
            setBooleanInternal(field, field.zae, z);
        }
    }

    public final void zaj(@NonNull Field field, @Nullable ArrayList arrayList) {
        if (field.zak != null) {
            zaE(field, arrayList);
        } else {
            zak(field, field.zae, arrayList);
        }
    }

    public void zak(@NonNull Field field, @NonNull String str, @Nullable ArrayList arrayList) {
        throw new UnsupportedOperationException("Boolean list not supported");
    }

    public final void zal(@NonNull Field field, @Nullable byte[] bArr) {
        if (field.zak != null) {
            zaE(field, bArr);
        } else {
            setDecodedBytesInternal(field, field.zae, bArr);
        }
    }

    public final void zam(@NonNull Field field, double d) {
        if (field.zak != null) {
            zaE(field, Double.valueOf(d));
        } else {
            zan(field, field.zae, d);
        }
    }

    public void zan(@NonNull Field field, @NonNull String str, double d) {
        throw new UnsupportedOperationException("Double not supported");
    }

    public final void zao(@NonNull Field field, @Nullable ArrayList arrayList) {
        if (field.zak != null) {
            zaE(field, arrayList);
        } else {
            zap(field, field.zae, arrayList);
        }
    }

    public void zap(@NonNull Field field, @NonNull String str, @Nullable ArrayList arrayList) {
        throw new UnsupportedOperationException("Double list not supported");
    }

    public final void zaq(@NonNull Field field, float f) {
        if (field.zak != null) {
            zaE(field, Float.valueOf(f));
        } else {
            zar(field, field.zae, f);
        }
    }

    public void zar(@NonNull Field field, @NonNull String str, float f) {
        throw new UnsupportedOperationException("Float not supported");
    }

    public final void zas(@NonNull Field field, @Nullable ArrayList arrayList) {
        if (field.zak != null) {
            zaE(field, arrayList);
        } else {
            zat(field, field.zae, arrayList);
        }
    }

    public void zat(@NonNull Field field, @NonNull String str, @Nullable ArrayList arrayList) {
        throw new UnsupportedOperationException("Float list not supported");
    }

    public final void zau(@NonNull Field field, int i) {
        if (field.zak != null) {
            zaE(field, Integer.valueOf(i));
        } else {
            setIntegerInternal(field, field.zae, i);
        }
    }

    public final void zav(@NonNull Field field, @Nullable ArrayList arrayList) {
        if (field.zak != null) {
            zaE(field, arrayList);
        } else {
            zaw(field, field.zae, arrayList);
        }
    }

    public void zaw(@NonNull Field field, @NonNull String str, @Nullable ArrayList arrayList) {
        throw new UnsupportedOperationException("Integer list not supported");
    }

    public final void zax(@NonNull Field field, long j) {
        if (field.zak != null) {
            zaE(field, Long.valueOf(j));
        } else {
            setLongInternal(field, field.zae, j);
        }
    }

    public final void zay(@NonNull Field field, @Nullable ArrayList arrayList) {
        if (field.zak != null) {
            zaE(field, arrayList);
        } else {
            zaz(field, field.zae, arrayList);
        }
    }

    public void zaz(@NonNull Field field, @NonNull String str, @Nullable ArrayList arrayList) {
        throw new UnsupportedOperationException("Long list not supported");
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SafeParcelable.Class(creator = "FieldCreator")
    @ShowFirstParty
    @KeepForSdk
    public static class Field<I, O> extends AbstractSafeParcelable {
        public static final zaj CREATOR = new zaj();

        @SafeParcelable.Field(getter = "getTypeIn", id = 2)
        public final int zaa;

        @SafeParcelable.Field(getter = "isTypeInArray", id = 3)
        public final boolean zab;

        @SafeParcelable.Field(getter = "getTypeOut", id = 4)
        public final int zac;

        @SafeParcelable.Field(getter = "isTypeOutArray", id = 5)
        public final boolean zad;

        @NonNull
        @SafeParcelable.Field(getter = "getOutputFieldName", id = 6)
        public final String zae;

        @SafeParcelable.Field(getter = "getSafeParcelableFieldId", id = 7)
        public final int zaf;

        @Nullable
        public final Class zag;

        @Nullable
        @SafeParcelable.Field(getter = "getConcreteTypeName", id = 8)
        public final String zah;

        @SafeParcelable.VersionField(getter = "getVersionCode", id = 1)
        public final int zai;
        public zan zaj;

        @Nullable
        @SafeParcelable.Field(getter = "getWrappedConverter", id = 9, type = "com.google.android.gms.common.server.converter.ConverterWrapper")
        public final FieldConverter zak;

        @SafeParcelable.Constructor
        public Field(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) boolean z, @SafeParcelable.Param(id = 4) int i3, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) String str, @SafeParcelable.Param(id = 7) int i4, @Nullable @SafeParcelable.Param(id = 8) String str2, @Nullable @SafeParcelable.Param(id = 9) com.google.android.gms.common.server.converter.zaa zaaVar) {
            this.zai = i;
            this.zaa = i2;
            this.zab = z;
            this.zac = i3;
            this.zad = z2;
            this.zae = str;
            this.zaf = i4;
            if (str2 == null) {
                this.zag = null;
                this.zah = null;
            } else {
                this.zag = SafeParcelResponse.class;
                this.zah = str2;
            }
            if (zaaVar == null) {
                this.zak = null;
            } else {
                this.zak = zaaVar.zab();
            }
        }

        @NonNull
        @KeepForSdk
        public static Field<byte[], byte[]> forBase64(@NonNull String str, int i) {
            return new Field<>(8, false, 8, false, str, i, null, null);
        }

        @NonNull
        @KeepForSdk
        public static Field<Boolean, Boolean> forBoolean(@NonNull String str, int i) {
            return new Field<>(6, false, 6, false, str, i, null, null);
        }

        @NonNull
        @KeepForSdk
        public static <T extends FastJsonResponse> Field<T, T> forConcreteType(@NonNull String str, int i, @NonNull Class<T> cls) {
            return new Field<>(11, false, 11, false, str, i, cls, null);
        }

        @NonNull
        @KeepForSdk
        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> forConcreteTypeArray(@NonNull String str, int i, @NonNull Class<T> cls) {
            return new Field<>(11, true, 11, true, str, i, cls, null);
        }

        @NonNull
        @KeepForSdk
        public static Field<Double, Double> forDouble(@NonNull String str, int i) {
            return new Field<>(4, false, 4, false, str, i, null, null);
        }

        @NonNull
        @KeepForSdk
        public static Field<Float, Float> forFloat(@NonNull String str, int i) {
            return new Field<>(3, false, 3, false, str, i, null, null);
        }

        @NonNull
        @KeepForSdk
        public static Field<Integer, Integer> forInteger(@NonNull String str, int i) {
            return new Field<>(0, false, 0, false, str, i, null, null);
        }

        @NonNull
        @KeepForSdk
        public static Field<Long, Long> forLong(@NonNull String str, int i) {
            return new Field<>(2, false, 2, false, str, i, null, null);
        }

        @NonNull
        @KeepForSdk
        public static Field<String, String> forString(@NonNull String str, int i) {
            return new Field<>(7, false, 7, false, str, i, null, null);
        }

        @NonNull
        @KeepForSdk
        public static Field<HashMap<String, String>, HashMap<String, String>> forStringMap(@NonNull String str, int i) {
            return new Field<>(10, false, 10, false, str, i, null, null);
        }

        @NonNull
        @KeepForSdk
        public static Field<ArrayList<String>, ArrayList<String>> forStrings(@NonNull String str, int i) {
            return new Field<>(7, true, 7, true, str, i, null, null);
        }

        @NonNull
        @KeepForSdk
        public static Field withConverter(@NonNull String str, int i, @NonNull FieldConverter<?, ?> fieldConverter, boolean z) {
            fieldConverter.getClass();
            return new Field(7, z, 0, false, str, i, null, fieldConverter);
        }

        @KeepForSdk
        public int getSafeParcelableFieldId() {
            return this.zaf;
        }

        @NonNull
        public final String toString() {
            Objects.ToStringHelper toStringHelper = new Objects.ToStringHelper(this, null);
            toStringHelper.add("versionCode", Integer.valueOf(this.zai));
            toStringHelper.add("typeIn", Integer.valueOf(this.zaa));
            toStringHelper.add("typeInArray", Boolean.valueOf(this.zab));
            toStringHelper.add("typeOut", Integer.valueOf(this.zac));
            toStringHelper.add("typeOutArray", Boolean.valueOf(this.zad));
            toStringHelper.add("outputFieldName", this.zae);
            toStringHelper.add("safeParcelFieldId", Integer.valueOf(this.zaf));
            String str = this.zah;
            toStringHelper.add("concreteTypeName", str != null ? str : null);
            Class cls = this.zag;
            if (cls != null) {
                toStringHelper.add("concreteType.class", cls.getCanonicalName());
            }
            FieldConverter fieldConverter = this.zak;
            if (fieldConverter != null) {
                toStringHelper.add("converterName", fieldConverter.getClass().getCanonicalName());
            }
            return toStringHelper.toString();
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NonNull Parcel parcel, int i) {
            int i2 = this.zai;
            int iZza = SafeParcelWriter.zza(parcel, 20293);
            SafeParcelWriter.zzc(parcel, 1, 4);
            parcel.writeInt(i2);
            int i3 = this.zaa;
            SafeParcelWriter.zzc(parcel, 2, 4);
            parcel.writeInt(i3);
            boolean z = this.zab;
            SafeParcelWriter.zzc(parcel, 3, 4);
            parcel.writeInt(z ? 1 : 0);
            int i4 = this.zac;
            SafeParcelWriter.zzc(parcel, 4, 4);
            parcel.writeInt(i4);
            boolean z2 = this.zad;
            SafeParcelWriter.zzc(parcel, 5, 4);
            parcel.writeInt(z2 ? 1 : 0);
            SafeParcelWriter.writeString(parcel, 6, this.zae, false);
            int safeParcelableFieldId = getSafeParcelableFieldId();
            SafeParcelWriter.zzc(parcel, 7, 4);
            parcel.writeInt(safeParcelableFieldId);
            String str = this.zah;
            if (str == null) {
                str = null;
            }
            SafeParcelWriter.writeString(parcel, 8, str, false);
            SafeParcelWriter.writeParcelable(parcel, 9, zaa(), i, false);
            SafeParcelWriter.zzb(parcel, iZza);
        }

        @Nullable
        public final com.google.android.gms.common.server.converter.zaa zaa() {
            FieldConverter fieldConverter = this.zak;
            if (fieldConverter == null) {
                return null;
            }
            return com.google.android.gms.common.server.converter.zaa.zaa(fieldConverter);
        }

        @NonNull
        public final Field zab() {
            return new Field(this.zai, this.zaa, this.zab, this.zac, this.zad, this.zae, this.zaf, this.zah, zaa());
        }

        @NonNull
        public final FastJsonResponse zad() throws IllegalAccessException, InstantiationException {
            Preconditions.checkNotNull(this.zag);
            Class cls = this.zag;
            if (cls != SafeParcelResponse.class) {
                return (FastJsonResponse) cls.newInstance();
            }
            Preconditions.checkNotNull(this.zah);
            Preconditions.checkNotNull(this.zaj, "The field mapping dictionary must be set if the concrete type is a SafeParcelResponse object.");
            return new SafeParcelResponse(this.zaj, this.zah);
        }

        @NonNull
        public final Object zae(@Nullable Object obj) {
            Preconditions.checkNotNull(this.zak);
            Object objZac = this.zak.zac(obj);
            Preconditions.checkNotNull(objZac);
            return objZac;
        }

        @NonNull
        public final Object zaf(@NonNull Object obj) {
            Preconditions.checkNotNull(this.zak);
            return this.zak.zad(obj);
        }

        @Nullable
        public final String zag() {
            String str = this.zah;
            if (str == null) {
                return null;
            }
            return str;
        }

        @NonNull
        public final Map zah() {
            Preconditions.checkNotNull(this.zah);
            Preconditions.checkNotNull(this.zaj);
            Map mapZab = this.zaj.zab(this.zah);
            Preconditions.checkNotNull(mapZab);
            return mapZab;
        }

        public final void zai(zan zanVar) {
            this.zaj = zanVar;
        }

        public final boolean zaj() {
            return this.zak != null;
        }

        public Field(int i, boolean z, int i2, boolean z2, @NonNull String str, int i3, @Nullable Class cls, @Nullable FieldConverter fieldConverter) {
            this.zai = 1;
            this.zaa = i;
            this.zab = z;
            this.zac = i2;
            this.zad = z2;
            this.zae = str;
            this.zaf = i3;
            this.zag = cls;
            if (cls == null) {
                this.zah = null;
            } else {
                this.zah = cls.getCanonicalName();
            }
            this.zak = fieldConverter;
        }
    }
}
