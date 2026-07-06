package com.amazon.livingroom.voice.models;

import com.amazon.minerva.client.thirdparty.transport.UploadResult;
import java.lang.annotation.Annotation;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.EnumsKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public final class ResultCode {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ ResultCode[] $VALUES;

    @NotNull
    public static final Lazy<KSerializer<Object>> $cachedSerializer$delegate;

    @NotNull
    public static final Companion Companion;

    @SerialName("Failure")
    public static final ResultCode FAILURE = new ResultCode("FAILURE", 0);

    @SerialName("InvalidMessage")
    public static final ResultCode INVALID_MESSAGE = new ResultCode("INVALID_MESSAGE", 1);

    @SerialName("UnsupportedVoiceAssistant")
    public static final ResultCode UNSUPPORTED_VOICE_ASSISTANT = new ResultCode("UNSUPPORTED_VOICE_ASSISTANT", 2);

    @SerialName("UnsupportedCommand")
    public static final ResultCode UNSUPPORTED_COMMAND = new ResultCode("UNSUPPORTED_COMMAND", 3);

    @SerialName("Success")
    public static final ResultCode SUCCESS = new ResultCode(UploadResult.SUCCESS, 4);

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public final /* synthetic */ KSerializer get$cachedSerializer() {
            return (KSerializer) ResultCode.$cachedSerializer$delegate.getValue();
        }

        @NotNull
        public final KSerializer<ResultCode> serializer() {
            return get$cachedSerializer();
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public static final /* synthetic */ ResultCode[] $values() {
        return new ResultCode[]{FAILURE, INVALID_MESSAGE, UNSUPPORTED_VOICE_ASSISTANT, UNSUPPORTED_COMMAND, SUCCESS};
    }

    static {
        ResultCode[] resultCodeArr$values = $values();
        $VALUES = resultCodeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(resultCodeArr$values);
        Companion = new Companion();
        $cachedSerializer$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new ResultCode$$ExternalSyntheticLambda0());
    }

    public ResultCode(String str, int i) {
    }

    public static final /* synthetic */ KSerializer _init_$_anonymous_() {
        return EnumsKt.createAnnotatedEnumSerializer("com.amazon.livingroom.voice.models.ResultCode", values(), new String[]{"Failure", "InvalidMessage", "UnsupportedVoiceAssistant", "UnsupportedCommand", "Success"}, new Annotation[][]{null, null, null, null, null}, null);
    }

    @NotNull
    public static EnumEntries<ResultCode> getEntries() {
        return $ENTRIES;
    }

    public static ResultCode valueOf(String str) {
        return (ResultCode) Enum.valueOf(ResultCode.class, str);
    }

    public static ResultCode[] values() {
        return (ResultCode[]) $VALUES.clone();
    }
}
