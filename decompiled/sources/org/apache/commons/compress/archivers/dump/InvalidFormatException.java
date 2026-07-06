package org.apache.commons.compress.archivers.dump;

import androidx.tvprovider.media.tv.ChannelLogoUtils$$ExternalSyntheticOutline0;
import com.amazon.avod.mpb.media.ExternalFourCCMapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class InvalidFormatException extends DumpArchiveException {
    public static final long serialVersionUID = 1;
    public long offset;

    public InvalidFormatException() {
        super("there was an error decoding a tape segment");
    }

    public long getOffset() {
        return this.offset;
    }

    public InvalidFormatException(long j) {
        super(ChannelLogoUtils$$ExternalSyntheticOutline0.m("there was an error decoding a tape segment header at offset ", j, ExternalFourCCMapper.CODEC_NAME_SPLITTER));
        this.offset = j;
    }
}
