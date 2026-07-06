package com.amazon.avod.mpb.api.sample;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline2;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.config.DefaultMPBInternalConfig;
import com.google.common.io.BaseEncoding;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class MediaSample {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final ByteBuffer data;
    public final long durationMs;

    @Nullable
    public final EncryptionInfo encryptionInfo;
    public final boolean isEncrypted;
    public final long ptsMs;
    public final long size;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final String byteBufferToHexString(@NotNull ByteBuffer buffer, int i) {
            Intrinsics.checkNotNullParameter(buffer, "buffer");
            ByteBuffer byteBufferDuplicate = buffer.duplicate();
            byteBufferDuplicate.clear();
            int iMin = Math.min(i, buffer.capacity());
            byte[] bArr = new byte[iMin];
            byteBufferDuplicate.get(bArr, 0, iMin);
            String strEncode = BaseEncoding.base16().upperCase().encode(bArr);
            if (iMin < buffer.capacity()) {
                return AbstractResolvableFuture$$ExternalSyntheticOutline0.m(strEncode, "...");
            }
            Intrinsics.checkNotNull(strEncode);
            return strEncode;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public MediaSample(@NotNull ByteBuffer data, long j, long j2, long j3, boolean z, @Nullable EncryptionInfo encryptionInfo) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
        this.size = j;
        this.ptsMs = j2;
        this.durationMs = j3;
        this.isEncrypted = z;
        this.encryptionInfo = encryptionInfo;
    }

    @NotNull
    public ByteBuffer getData() {
        return this.data;
    }

    public long getDurationMs() {
        return this.durationMs;
    }

    @Nullable
    public EncryptionInfo getEncryptionInfo() {
        return this.encryptionInfo;
    }

    public long getPtsMs() {
        return this.ptsMs;
    }

    public long getSize() {
        return this.size;
    }

    public boolean isEncrypted() {
        return this.isEncrypted;
    }

    @NotNull
    public String toString() {
        String string;
        Companion companion = Companion;
        ByteBuffer data = getData();
        DefaultMPBInternalConfig.INSTANCE.getClass();
        String strByteBufferToHexString = companion.byteBufferToHexString(data, DefaultMPBInternalConfig.dumpFullMediaSampleInToString ? getData().capacity() : 64);
        EncryptionInfo encryptionInfo = getEncryptionInfo();
        if (encryptionInfo == null || (string = encryptionInfo.toString()) == null) {
            string = AbstractJsonLexerKt.NULL;
        }
        long size = getSize();
        long ptsMs = getPtsMs();
        long durationMs = getDurationMs();
        boolean zIsEncrypted = isEncrypted();
        StringBuilder sbM = AbstractResolvableFuture$$ExternalSyntheticOutline2.m("{\"size\":", size, ",\"ptsMs\":");
        sbM.append(ptsMs);
        sbM.append(",\"durationMs\":");
        sbM.append(durationMs);
        sbM.append(",\"isEncrypted\":");
        sbM.append(zIsEncrypted);
        sbM.append(",\"encryptionInfo\":");
        sbM.append(string);
        sbM.append(",\"data\":\"");
        return ActivityCompat$$ExternalSyntheticOutline0.m(sbM, strByteBufferToHexString, "\"}");
    }
}
