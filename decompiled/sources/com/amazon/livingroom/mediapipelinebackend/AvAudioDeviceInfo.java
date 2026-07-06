package com.amazon.livingroom.mediapipelinebackend;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AvAudioDeviceInfo {
    public short channels;
    public int[] encodings;

    public AvAudioDeviceInfo(AvAudioDeviceInfo avAudioDeviceInfo) {
        avAudioDeviceInfo.getClass();
        this.channels = avAudioDeviceInfo.channels;
        this.encodings = avAudioDeviceInfo.encodings;
    }

    public short getChannels() {
        return this.channels;
    }

    public int[] getEncodings() {
        return this.encodings;
    }

    public void setChannels(short s) {
        this.channels = s;
    }

    public void setEncodings(int[] iArr) {
        this.encodings = iArr;
    }

    public AvAudioDeviceInfo(short s, int[] iArr) {
        this.channels = s;
        this.encodings = iArr;
    }
}
