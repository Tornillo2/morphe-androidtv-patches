package com.amazon.livingroom.mediapipelinebackend;

import androidx.annotation.OptIn;
import androidx.media3.common.util.UnstableApi;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@OptIn(markerClass = {UnstableApi.class})
public interface AvCodecType {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Audio implements AvCodecType {
        kCodecTypeUnknown("audio/x-unknown"),
        kCodecTypeAAC("audio/mp4a-latm"),
        kCodecTypeAACH("audio/mp4a-latm"),
        kCodecTypeEAC3("audio/eac3"),
        kCodecTypePCM("audio/raw"),
        kCodecTypeAACHv2("audio/mp4a-latm");

        public String mimeType;

        Audio(String str) {
            this.mimeType = str;
        }

        public static Audio findById(int i) {
            return (Audio) AvCodecTypeHelper.findByIndex(Audio.class, i);
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.AvCodecType
        public int getId() {
            return ordinal();
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.AvCodecType
        public String getMimeType() {
            return this.mimeType;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Video implements AvCodecType {
        kCodecTypeUnknown("video/x-unknown"),
        kCodecTypeAVC("video/avc"),
        kCodecTypeHEVC("video/hevc"),
        kCodecTypeDVHE_STN("video/dolby-vision"),
        kCodecTypeAV1("video/av01");

        public final String mimeType;

        Video(String str) {
            this.mimeType = str;
        }

        public static Video findById(int i) {
            return (Video) AvCodecTypeHelper.findByIndex(Video.class, i);
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.AvCodecType
        public int getId() {
            return ordinal();
        }

        @Override // com.amazon.livingroom.mediapipelinebackend.AvCodecType
        public String getMimeType() {
            return this.mimeType;
        }
    }

    int getId();

    String getMimeType();
}
