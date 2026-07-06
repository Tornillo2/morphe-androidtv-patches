package org.apache.commons.compress.archivers.dump;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class UnsupportedCompressionAlgorithmException extends DumpArchiveException {
    public static final long serialVersionUID = 1;

    public UnsupportedCompressionAlgorithmException() {
        super("this file uses an unsupported compression algorithm.");
    }

    public UnsupportedCompressionAlgorithmException(String str) {
        super(MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("this file uses an unsupported compression algorithm: ", str, ExternalFourCCMapper.CODEC_NAME_SPLITTER));
    }
}
