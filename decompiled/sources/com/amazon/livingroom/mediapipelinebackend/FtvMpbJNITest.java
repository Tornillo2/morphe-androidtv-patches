package com.amazon.livingroom.mediapipelinebackend;

import com.amazon.avod.mpb.api.core.MediaPipelineBackendResultCode;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class FtvMpbJNITest {

    @NotNull
    public static final FtvMpbJNITest INSTANCE = new FtvMpbJNITest();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class JNIExceptionFailIncorrectName extends Exception {
        public final int resultCode;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public JNIExceptionFailIncorrectName(@NotNull String message, int i) {
            super(message);
            Intrinsics.checkNotNullParameter(message, "message");
            this.resultCode = i;
        }

        @CalledFromNative
        public final int getErrorCode() {
            return this.resultCode;
        }

        public final int getResultCode() {
            return this.resultCode;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class JNIExceptionFailIncorrectType extends Exception {
        public final int resultCode;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public JNIExceptionFailIncorrectType(@NotNull String message, int i) {
            super(message);
            Intrinsics.checkNotNullParameter(message, "message");
            this.resultCode = i;
        }

        @CalledFromNative
        public final long getIgniteMpbErrorCode() {
            return this.resultCode;
        }

        public final int getResultCode() {
            return this.resultCode;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class JNIExceptionPass extends Exception {
        public final int resultCode;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public JNIExceptionPass(@NotNull String message, int i) {
            super(message);
            Intrinsics.checkNotNullParameter(message, "message");
            this.resultCode = i;
        }

        @CalledFromNative
        public final int getIgniteMpbErrorCode() {
            return this.resultCode;
        }

        public final int getResultCode() {
            return this.resultCode;
        }
    }

    @CalledFromNative
    public final int testDoNotConvertException() throws JNIExceptionFailIncorrectName {
        throw new JNIExceptionFailIncorrectName("this exception should NOT convert to a result code", MediaPipelineBackendResultCode.AV_MPB_BUFFER_FULL.resultCode);
    }

    @CalledFromNative
    public final int testDoNotConvertExceptionBasedOnType() throws JNIExceptionFailIncorrectType {
        throw new JNIExceptionFailIncorrectType("this exception should NOT convert to a result code", MediaPipelineBackendResultCode.AV_MPB_BUFFER_FULL.resultCode);
    }

    @CalledFromNative
    public final int testDoNotThrowException() {
        return MediaPipelineBackendResultCode.AV_MPB_SUCCESS.resultCode;
    }

    @CalledFromNative
    public final int testThrowBufferFullException() throws JNIExceptionPass {
        throw new JNIExceptionPass("this exception should convert to a result code", MediaPipelineBackendResultCode.AV_MPB_BUFFER_FULL.resultCode);
    }
}
