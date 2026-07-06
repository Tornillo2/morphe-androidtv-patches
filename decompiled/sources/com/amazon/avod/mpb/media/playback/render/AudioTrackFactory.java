package com.amazon.avod.mpb.media.playback.render;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.media.MediaFormat;
import android.os.SystemClock;
import com.amazon.avod.mpb.api.core.MpbLog;
import com.amazon.avod.mpb.config.DefaultMPBInternalConfig;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Sets;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nAudioTrackFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AudioTrackFactory.kt\ncom/amazon/avod/mpb/media/playback/render/AudioTrackFactory\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,309:1\n1#2:310\n*E\n"})
public final class AudioTrackFactory {
    public static final int BITS_PER_BYTE = 8;
    public static final int DDP_MAX_ENCODED_RATE_BYTES_PER_SECOND = 56000;
    public final long audioTrackBufferMaxSizeUs;
    public final long audioTrackBufferMinSizeUs;
    public final int audioTrackBufferMultiplicationFactor;
    public final boolean isAudioTrackRecoveryEnabled;
    public final boolean isDynamicPassthroughAudioTrackBufferSizeEnabled;
    public long lastReleaseTimeMs;

    @NotNull
    public final Object mutex;
    public final long passThroughAudioTrackBufferSizeUs;
    public final int passthroughAudioTrackBufferMultiplicationFactor;

    @NotNull
    public final Set<AudioTrack> tracks;
    public final long tunnelModeAudioTrackBufferSizeUs;
    public final float tunnelModeAudioTrackSizeScaleFactor;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final AudioTrackFactory instance = new AudioTrackFactory();

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final AudioTrackFactory getInstance() {
            return AudioTrackFactory.instance;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }
    }

    public AudioTrackFactory() {
        DefaultMPBInternalConfig defaultMPBInternalConfig = DefaultMPBInternalConfig.INSTANCE;
        defaultMPBInternalConfig.getClass();
        this.audioTrackBufferMultiplicationFactor = DefaultMPBInternalConfig.audioTrackBufferMultiplicationFactor;
        defaultMPBInternalConfig.getClass();
        this.passthroughAudioTrackBufferMultiplicationFactor = DefaultMPBInternalConfig.passthroughAudioTrackBufferMultiplicationFactor;
        defaultMPBInternalConfig.getClass();
        this.tunnelModeAudioTrackSizeScaleFactor = DefaultMPBInternalConfig.tunnelModeAudioTrackScaleFactor;
        defaultMPBInternalConfig.getClass();
        this.passThroughAudioTrackBufferSizeUs = DefaultMPBInternalConfig.passThroughAudioTrackBufferSizeUs;
        defaultMPBInternalConfig.getClass();
        this.tunnelModeAudioTrackBufferSizeUs = DefaultMPBInternalConfig.tunnelModeAudioTrackBufferSizeUs;
        defaultMPBInternalConfig.getClass();
        this.audioTrackBufferMinSizeUs = DefaultMPBInternalConfig.audioTrackBufferMinSizeUs;
        defaultMPBInternalConfig.getClass();
        this.audioTrackBufferMaxSizeUs = DefaultMPBInternalConfig.audioTrackBufferMaxSizeUs;
        defaultMPBInternalConfig.getClass();
        this.isAudioTrackRecoveryEnabled = DefaultMPBInternalConfig.audioTrackRecoveryEnabled;
        defaultMPBInternalConfig.getClass();
        this.isDynamicPassthroughAudioTrackBufferSizeEnabled = DefaultMPBInternalConfig.dynamicPassthroughAudioTrackBufferSizeEnabled;
        Set<AudioTrack> setNewConcurrentHashSet = Sets.newConcurrentHashSet();
        Intrinsics.checkNotNullExpressionValue(setNewConcurrentHashSet, "newConcurrentHashSet(...)");
        this.tracks = setNewConcurrentHashSet;
        this.mutex = new Object();
    }

    @NotNull
    public static final AudioTrackFactory getInstance() {
        Companion.getClass();
        return instance;
    }

    @VisibleForTesting
    public final int computeAudioTrackBufferSizeBytes(int i, int i2, int i3, int i4, boolean z, boolean z2, int i5) {
        if (z) {
            return getPassthroughBufferSizeInBytes(i5, z2);
        }
        int minBufferSize = AudioTrack.getMinBufferSize(i2, i3, i4);
        if (minBufferSize == -2) {
            throw new IllegalStateException("Check failed.");
        }
        int i6 = (z2 ? (int) this.tunnelModeAudioTrackSizeScaleFactor : this.audioTrackBufferMultiplicationFactor) * minBufferSize;
        int i7 = i * 2;
        int iDurationUsToBytes = (int) (z2 ? durationUsToBytes(this.tunnelModeAudioTrackBufferSizeUs, i2, i7) : durationUsToBytes(this.audioTrackBufferMinSizeUs, i2, i7));
        int iMax = Math.max(minBufferSize, (int) durationUsToBytes(this.audioTrackBufferMaxSizeUs, i2, i7));
        return i6 < iDurationUsToBytes ? iDurationUsToBytes : i6 > iMax ? iMax : i6;
    }

    public final long durationUsToBytes(long j, int i, int i2) {
        return ((j * ((long) i)) / TimeUnit.SECONDS.toMicros(1L)) * ((long) i2);
    }

    public final int getPassthroughBufferSizeInBytes(int i, boolean z) {
        return Ints.checkedCast((getPassthroughBufferSizeInUs(z) * ((long) ((!this.isDynamicPassthroughAudioTrackBufferSizeEnabled || i <= 0) ? 56000 : IntMath.divide(i, 8, RoundingMode.CEILING)))) / TimeUnit.SECONDS.toMicros(1L));
    }

    public final long getPassthroughBufferSizeInUs(boolean z) {
        return (z ? this.tunnelModeAudioTrackBufferSizeUs : this.passThroughAudioTrackBufferSizeUs) * ((long) this.passthroughAudioTrackBufferMultiplicationFactor);
    }

    @NotNull
    public final AudioTrackWrapper newAudioTrackWrapper(@NotNull MediaFormat mediaFormat, boolean z, @Nullable Integer num, int i) {
        int i2;
        AudioTrackWrapper audioTrackWrapper;
        Intrinsics.checkNotNullParameter(mediaFormat, "mediaFormat");
        synchronized (this.mutex) {
            try {
                String string = mediaFormat.getString("mime");
                if (string == null) {
                    throw new IllegalStateException("Cannot create audio track with null mime type");
                }
                int integer = mediaFormat.getInteger("sample-rate");
                int integer2 = mediaFormat.getInteger("channel-count");
                int i3 = 2;
                if (integer2 != 1) {
                    i2 = 12;
                    if (integer2 != 2 && integer2 == 6) {
                        i2 = 252;
                    }
                } else {
                    i2 = 4;
                }
                if (Intrinsics.areEqual(string, "audio/ac3")) {
                    i3 = 5;
                } else if (Intrinsics.areEqual(string, "audio/eac3")) {
                    i3 = 6;
                }
                int i4 = i3;
                int i5 = i2;
                int iComputeAudioTrackBufferSizeBytes = computeAudioTrackBufferSizeBytes(integer2, integer, i5, i4, z, num != null, i);
                int iIntValue = num != null ? num.intValue() : 0;
                AudioAttributes.Builder builder = new AudioAttributes.Builder();
                builder.setUsage(1);
                builder.setContentType(3);
                if (num != null) {
                    MpbLog.logf("TunnelMode: setting FLAG_HW_AV_SYNC during audio track creation, audioSessionId " + num, new Object[0]);
                    builder.setFlags(16);
                }
                AudioAttributes audioAttributesBuild = builder.build();
                AudioFormat.Builder builder2 = new AudioFormat.Builder();
                builder2.setEncoding(i4);
                builder2.setChannelMask(i5);
                builder2.setSampleRate(integer);
                AudioFormat audioFormatBuild = builder2.build();
                long jElapsedRealtime = this.lastReleaseTimeMs > 0 ? SystemClock.elapsedRealtime() - this.lastReleaseTimeMs : -1L;
                StringBuilder sb = new StringBuilder("Creating AudioTrack for ");
                sb.append(string);
                sb.append(" with channelCount=");
                sb.append(integer2);
                sb.append(" channelConfig=");
                sb.append(i5);
                sb.append(" encoding=");
                sb.append(i4);
                sb.append(" bufferSizeBytes=");
                sb.append(iComputeAudioTrackBufferSizeBytes);
                sb.append(" isPassthrough=");
                sb.append(z);
                sb.append(" bitrate=");
                sb.append(i);
                sb.append(" sampleRateHz=");
                sb.append(integer);
                sb.append(" sessionId=");
                int i6 = iIntValue;
                sb.append(i6);
                sb.append(" timeSinceLastReleaseMs=");
                sb.append(jElapsedRealtime);
                MpbLog.logf(sb.toString(), new Object[0]);
                try {
                } catch (Exception e) {
                    e = e;
                }
                try {
                    AudioTrack audioTrack = new AudioTrack(audioAttributesBuild, audioFormatBuild, iComputeAudioTrackBufferSizeBytes, 1, i6);
                    int state = audioTrack.getState();
                    if (state != 1) {
                        MpbLog.errorf("Created AudioTrack in state " + state + " for " + string + " with channelCount=" + integer2 + " channelConfig=" + i5 + " encoding=" + i4 + " bufferSizeBytes=" + iComputeAudioTrackBufferSizeBytes + " isPassthrough=" + z + " bitrate=" + i + " sampleRateHz=" + integer + " sessionId=" + i6 + " existing=" + this.tracks.size() + " timeSinceLastReleaseMs=" + jElapsedRealtime, new Object[0]);
                    }
                    this.tracks.add(audioTrack);
                    MpbLog.logf("AudioTrackFactory created track " + audioTrack + ", total " + this.tracks.size(), new Object[0]);
                    audioTrackWrapper = new AudioTrackWrapper(audioTrack, iComputeAudioTrackBufferSizeBytes, i4, integer2);
                } catch (Exception e2) {
                    e = e2;
                    i6 = i6;
                    Exception exc = e;
                    MpbLog.exceptionf(exc, e.getClass().getSimpleName() + " on creating AudioTrack for " + string + " with channelCount=" + integer2 + " channelConfig=" + i5 + " encoding=" + i4 + " bufferSizeBytes=" + iComputeAudioTrackBufferSizeBytes + " isPassthrough=" + z + " bitrate=" + i + " sampleRateHz=" + integer + " sessionId=" + i6 + " existing=" + this.tracks.size() + " timeSinceLastReleaseMs=" + jElapsedRealtime + ": " + e.getMessage(), new Object[0]);
                    throw exc;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return audioTrackWrapper;
    }

    public final void releaseAllTracks(@NotNull Exception trigger) {
        Intrinsics.checkNotNullParameter(trigger, "trigger");
        if (this.isAudioTrackRecoveryEnabled) {
            synchronized (this.mutex) {
                MpbLog.warnf("AudioTrackFactory releaseAllTracks called with trigger exception " + trigger, new Object[0]);
                Iterator<AudioTrack> it = this.tracks.iterator();
                while (it.hasNext()) {
                    releaseTrack(it.next());
                }
            }
        }
    }

    public final void releaseTrack(@NotNull AudioTrack track) {
        Intrinsics.checkNotNullParameter(track, "track");
        synchronized (this.mutex) {
            try {
                track.release();
                this.tracks.remove(track);
                this.lastReleaseTimeMs = SystemClock.elapsedRealtime();
                MpbLog.logf("AudioTrackFactory released track " + track + ", total " + this.tracks.size(), new Object[0]);
            } catch (Exception e) {
                MpbLog.exceptionf(e, "AudioTrackFactory threw exception when releasing " + track + ", " + e + ", total " + this.tracks.size(), new Object[0]);
                throw e;
            }
        }
    }
}
