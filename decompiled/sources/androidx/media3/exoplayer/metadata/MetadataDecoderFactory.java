package androidx.media3.exoplayer.metadata;

import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import androidx.media3.common.Format;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.metadata.MetadataDecoder;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.metadata.emsg.EventMessageDecoder;
import androidx.media3.extractor.metadata.icy.IcyDecoder;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import androidx.media3.extractor.metadata.scte35.SpliceInfoDecoder;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface MetadataDecoderFactory {
    public static final MetadataDecoderFactory DEFAULT = new AnonymousClass1();

    /* JADX INFO: renamed from: androidx.media3.exoplayer.metadata.MetadataDecoderFactory$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements MetadataDecoderFactory {
        @Override // androidx.media3.exoplayer.metadata.MetadataDecoderFactory
        public MetadataDecoder createDecoder(Format format) {
            String str = format.sampleMimeType;
            if (str != null) {
                switch (str) {
                    case "application/vnd.dvb.ait":
                        return new AppInfoTableDecoder();
                    case "application/x-icy":
                        return new IcyDecoder();
                    case "application/id3":
                        return new Id3Decoder(null);
                    case "application/x-emsg":
                        return new EventMessageDecoder();
                    case "application/x-scte35":
                        return new SpliceInfoDecoder();
                }
            }
            throw new IllegalArgumentException(RemoteInput$$ExternalSyntheticOutline0.m("Attempted to create decoder for unsupported MIME type: ", str));
        }

        @Override // androidx.media3.exoplayer.metadata.MetadataDecoderFactory
        public boolean supportsFormat(Format format) {
            String str = format.sampleMimeType;
            return "application/id3".equals(str) || "application/x-emsg".equals(str) || "application/x-scte35".equals(str) || "application/x-icy".equals(str) || "application/vnd.dvb.ait".equals(str);
        }
    }

    MetadataDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}
