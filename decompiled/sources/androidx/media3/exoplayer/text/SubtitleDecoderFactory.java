package androidx.media3.exoplayer.text;

import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import androidx.media3.common.Format;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleDecoder;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.cea.Cea608Decoder;
import androidx.media3.extractor.text.cea.Cea708Decoder;
import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public interface SubtitleDecoderFactory {
    public static final SubtitleDecoderFactory DEFAULT = new SubtitleDecoderFactory() { // from class: androidx.media3.exoplayer.text.SubtitleDecoderFactory.1
        public final DefaultSubtitleParserFactory delegate = new DefaultSubtitleParserFactory();

        @Override // androidx.media3.exoplayer.text.SubtitleDecoderFactory
        public SubtitleDecoder createDecoder(Format format) {
            String str = format.sampleMimeType;
            if (str != null) {
                switch (str) {
                    case "application/x-mp4-cea-608":
                    case "application/cea-608":
                        return new Cea608Decoder(str, format.accessibilityChannel, 16000L);
                    case "application/cea-708":
                        return new Cea708Decoder(format.accessibilityChannel, format.initializationData);
                }
            }
            if (!this.delegate.supportsFormat(format)) {
                throw new IllegalArgumentException(RemoteInput$$ExternalSyntheticOutline0.m("Attempted to create decoder for unsupported MIME type: ", str));
            }
            SubtitleParser subtitleParserCreate = this.delegate.create(format);
            return new DelegatingSubtitleDecoder(subtitleParserCreate.getClass().getSimpleName().concat("Decoder"), subtitleParserCreate);
        }

        @Override // androidx.media3.exoplayer.text.SubtitleDecoderFactory
        public boolean supportsFormat(Format format) {
            String str = format.sampleMimeType;
            return this.delegate.supportsFormat(format) || Objects.equals(str, "application/cea-608") || Objects.equals(str, "application/x-mp4-cea-608") || Objects.equals(str, "application/cea-708");
        }
    };

    SubtitleDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}
