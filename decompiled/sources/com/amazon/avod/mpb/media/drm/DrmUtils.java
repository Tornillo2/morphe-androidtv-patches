package com.amazon.avod.mpb.media.drm;

import java.util.Arrays;
import java.util.UUID;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class DrmUtils {

    @NotNull
    public static final DrmUtils INSTANCE = new DrmUtils();

    @NotNull
    public static final UUID PLAYREADY_QC_UUID;

    @NotNull
    public static final UUID PLAYREADY_UUID;

    @NotNull
    public static final UUID WIDEVINE_UUID;

    static {
        UUID uuidFromString = UUID.fromString("11ef8ba9-79d6-4ace-a3c8-27dcd51d2111");
        Intrinsics.checkNotNullExpressionValue(uuidFromString, "fromString(...)");
        PLAYREADY_QC_UUID = uuidFromString;
        UUID uuidFromString2 = UUID.fromString("9a04f079-9840-4286-ab92-e65be0885f95");
        Intrinsics.checkNotNullExpressionValue(uuidFromString2, "fromString(...)");
        PLAYREADY_UUID = uuidFromString2;
        UUID uuidFromString3 = UUID.fromString("edef8ba9-79d6-4ace-a3c8-27dcd51d21ed");
        Intrinsics.checkNotNullExpressionValue(uuidFromString3, "fromString(...)");
        WIDEVINE_UUID = uuidFromString3;
    }

    public static final CharSequence toHexString$lambda$0(byte b) {
        return String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
    }

    @NotNull
    public final UUID getPLAYREADY_QC_UUID() {
        return PLAYREADY_QC_UUID;
    }

    @NotNull
    public final UUID getPLAYREADY_UUID() {
        return PLAYREADY_UUID;
    }

    @NotNull
    public final UUID getWIDEVINE_UUID() {
        return WIDEVINE_UUID;
    }

    @NotNull
    public final String toHexString(@Nullable byte[] bArr) {
        String strJoinToString$default;
        return (bArr == null || (strJoinToString$default = ArraysKt___ArraysKt.joinToString$default(bArr, (CharSequence) "", (CharSequence) "0x", (CharSequence) null, 0, (CharSequence) null, (Function1) new DrmUtils$$ExternalSyntheticLambda0(), 28, (Object) null)) == null) ? AbstractJsonLexerKt.NULL : strJoinToString$default;
    }
}
