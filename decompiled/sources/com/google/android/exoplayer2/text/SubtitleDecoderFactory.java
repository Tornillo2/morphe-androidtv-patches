package com.google.android.exoplayer2.text;

import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.text.cea.Cea608Decoder;
import com.google.android.exoplayer2.text.cea.Cea708Decoder;
import com.google.android.exoplayer2.text.dvb.DvbDecoder;
import com.google.android.exoplayer2.text.pgs.PgsDecoder;
import com.google.android.exoplayer2.text.ssa.SsaDecoder;
import com.google.android.exoplayer2.text.subrip.SubripDecoder;
import com.google.android.exoplayer2.text.ttml.TtmlDecoder;
import com.google.android.exoplayer2.text.tx3g.Tx3gDecoder;
import com.google.android.exoplayer2.text.webvtt.Mp4WebvttDecoder;
import com.google.android.exoplayer2.text.webvtt.WebvttDecoder;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.base.Ascii;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface SubtitleDecoderFactory {
    public static final SubtitleDecoderFactory DEFAULT = new AnonymousClass1();

    /* JADX INFO: renamed from: com.google.android.exoplayer2.text.SubtitleDecoderFactory$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public class AnonymousClass1 implements SubtitleDecoderFactory {
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.google.android.exoplayer2.text.SubtitleDecoderFactory
        public SubtitleDecoder createDecoder(Format format) {
            String str = format.sampleMimeType;
            if (str != null) {
                byte b = -1;
                switch (str.hashCode()) {
                    case -1351681404:
                        if (str.equals("application/dvbsubs")) {
                            b = 0;
                        }
                        break;
                    case -1248334819:
                        if (str.equals("application/pgs")) {
                            b = 1;
                        }
                        break;
                    case -1026075066:
                        if (str.equals("application/x-mp4-vtt")) {
                            b = 2;
                        }
                        break;
                    case -1004728940:
                        if (str.equals("text/vtt")) {
                            b = 3;
                        }
                        break;
                    case 691401887:
                        if (str.equals("application/x-quicktime-tx3g")) {
                            b = 4;
                        }
                        break;
                    case 822864842:
                        if (str.equals("text/x-ssa")) {
                            b = 5;
                        }
                        break;
                    case 930165504:
                        if (str.equals("application/x-mp4-cea-608")) {
                            b = 6;
                        }
                        break;
                    case 1201784583:
                        if (str.equals(MimeTypes.TEXT_EXOPLAYER_CUES)) {
                            b = 7;
                        }
                        break;
                    case 1566015601:
                        if (str.equals("application/cea-608")) {
                            b = 8;
                        }
                        break;
                    case 1566016562:
                        if (str.equals("application/cea-708")) {
                            b = 9;
                        }
                        break;
                    case 1668750253:
                        if (str.equals("application/x-subrip")) {
                            b = 10;
                        }
                        break;
                    case 1693976202:
                        if (str.equals("application/ttml+xml")) {
                            b = Ascii.VT;
                        }
                        break;
                }
                switch (b) {
                    case 0:
                        return new DvbDecoder(format.initializationData);
                    case 1:
                        return new PgsDecoder();
                    case 2:
                        return new Mp4WebvttDecoder();
                    case 3:
                        return new WebvttDecoder();
                    case 4:
                        return new Tx3gDecoder(format.initializationData);
                    case 5:
                        return new SsaDecoder(format.initializationData);
                    case 6:
                    case 8:
                        return new Cea608Decoder(str, format.accessibilityChannel, 16000L);
                    case 7:
                        return new ExoplayerCuesDecoder();
                    case 9:
                        return new Cea708Decoder(format.accessibilityChannel, format.initializationData);
                    case 10:
                        return new SubripDecoder();
                    case 11:
                        return new TtmlDecoder();
                }
            }
            throw new IllegalArgumentException(RemoteInput$$ExternalSyntheticOutline0.m("Attempted to create decoder for unsupported MIME type: ", str));
        }

        @Override // com.google.android.exoplayer2.text.SubtitleDecoderFactory
        public boolean supportsFormat(Format format) {
            String str = format.sampleMimeType;
            return "text/vtt".equals(str) || "text/x-ssa".equals(str) || "application/ttml+xml".equals(str) || "application/x-mp4-vtt".equals(str) || "application/x-subrip".equals(str) || "application/x-quicktime-tx3g".equals(str) || "application/cea-608".equals(str) || "application/x-mp4-cea-608".equals(str) || "application/cea-708".equals(str) || "application/dvbsubs".equals(str) || "application/pgs".equals(str) || MimeTypes.TEXT_EXOPLAYER_CUES.equals(str);
        }
    }

    SubtitleDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}
