package com.google.android.exoplayer2.trackselection;

import android.content.Context;
import android.graphics.Point;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.Spatializer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.emoji2.text.ConcurrencyHelpers$$ExternalSyntheticLambda0;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.BundleableUtil;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Predicate;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.Ordering;
import com.google.common.collect.RegularImmutableList;
import com.google.common.collect.ReverseNaturalOrdering;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class DefaultTrackSelector extends MappingTrackSelector {
    public static final String AUDIO_CHANNEL_COUNT_CONSTRAINTS_WARN_MESSAGE = "Audio channel count constraints cannot be applied without reference to Context. Build the track selector instance with one of the non-deprecated constructors that take a Context argument.";
    public static final float FRACTION_TO_CONSIDER_FULLSCREEN = 0.98f;
    public static final int SELECTION_ELIGIBILITY_ADAPTIVE = 2;
    public static final int SELECTION_ELIGIBILITY_FIXED = 1;
    public static final int SELECTION_ELIGIBILITY_NO = 0;
    public static final String TAG = "DefaultTrackSelector";

    @GuardedBy("lock")
    public AudioAttributes audioAttributes;

    @Nullable
    public final Context context;
    public final boolean deviceIsTV;
    public final Object lock;

    @GuardedBy("lock")
    public Parameters parameters;

    @Nullable
    @GuardedBy("lock")
    public SpatializerWrapperV32 spatializer;
    public final ExoTrackSelection.Factory trackSelectionFactory;
    public static final Ordering<Integer> FORMAT_VALUE_ORDERING = Ordering.from(new DefaultTrackSelector$$ExternalSyntheticLambda0());
    public static final Ordering<Integer> NO_ORDER = Ordering.from(new DefaultTrackSelector$$ExternalSyntheticLambda1());

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AudioTrackInfo extends TrackInfo<AudioTrackInfo> implements Comparable<AudioTrackInfo> {
        public final int bitrate;
        public final int channelCount;
        public final boolean hasMainOrNoRoleFlag;
        public final boolean isDefaultSelectionFlag;
        public final boolean isWithinConstraints;
        public final boolean isWithinRendererCapabilities;

        @Nullable
        public final String language;
        public final int localeLanguageMatchIndex;
        public final int localeLanguageScore;
        public final Parameters parameters;
        public final int preferredLanguageIndex;
        public final int preferredLanguageScore;
        public final int preferredMimeTypeMatchIndex;
        public final int preferredRoleFlagsScore;
        public final int sampleRate;
        public final int selectionEligibility;
        public final boolean usesHardwareAcceleration;
        public final boolean usesPrimaryDecoder;

        public AudioTrackInfo(int i, TrackGroup trackGroup, int i2, Parameters parameters, int i3, boolean z, Predicate<Format> predicate) {
            int i4;
            int formatLanguageScore;
            int formatLanguageScore2;
            super(i, trackGroup, i2);
            this.parameters = parameters;
            this.language = DefaultTrackSelector.normalizeUndeterminedLanguageToNull(this.format.language);
            this.isWithinRendererCapabilities = DefaultTrackSelector.isSupported(i3, false);
            int i5 = 0;
            while (true) {
                i4 = Integer.MAX_VALUE;
                if (i5 >= parameters.preferredAudioLanguages.size()) {
                    i5 = Integer.MAX_VALUE;
                    formatLanguageScore = 0;
                    break;
                } else {
                    formatLanguageScore = DefaultTrackSelector.getFormatLanguageScore(this.format, parameters.preferredAudioLanguages.get(i5), false);
                    if (formatLanguageScore > 0) {
                        break;
                    } else {
                        i5++;
                    }
                }
            }
            this.preferredLanguageIndex = i5;
            this.preferredLanguageScore = formatLanguageScore;
            this.preferredRoleFlagsScore = DefaultTrackSelector.getRoleFlagMatchScore(this.format.roleFlags, parameters.preferredAudioRoleFlags);
            Format format = this.format;
            int i6 = format.roleFlags;
            this.hasMainOrNoRoleFlag = i6 == 0 || (i6 & 1) != 0;
            this.isDefaultSelectionFlag = (format.selectionFlags & 1) != 0;
            int i7 = format.channelCount;
            this.channelCount = i7;
            this.sampleRate = format.sampleRate;
            int i8 = format.bitrate;
            this.bitrate = i8;
            this.isWithinConstraints = (i8 == -1 || i8 <= parameters.maxAudioBitrate) && (i7 == -1 || i7 <= parameters.maxAudioChannelCount) && predicate.apply(format);
            String[] systemLanguageCodes = Util.getSystemLanguageCodes();
            int i9 = 0;
            while (true) {
                if (i9 >= systemLanguageCodes.length) {
                    i9 = Integer.MAX_VALUE;
                    formatLanguageScore2 = 0;
                    break;
                } else {
                    formatLanguageScore2 = DefaultTrackSelector.getFormatLanguageScore(this.format, systemLanguageCodes[i9], false);
                    if (formatLanguageScore2 > 0) {
                        break;
                    } else {
                        i9++;
                    }
                }
            }
            this.localeLanguageMatchIndex = i9;
            this.localeLanguageScore = formatLanguageScore2;
            int i10 = 0;
            while (true) {
                if (i10 < parameters.preferredAudioMimeTypes.size()) {
                    String str = this.format.sampleMimeType;
                    if (str != null && str.equals(parameters.preferredAudioMimeTypes.get(i10))) {
                        i4 = i10;
                        break;
                    }
                    i10++;
                } else {
                    break;
                }
            }
            this.preferredMimeTypeMatchIndex = i4;
            this.usesPrimaryDecoder = (i3 & 384) == 128;
            this.usesHardwareAcceleration = (i3 & 64) == 64;
            this.selectionEligibility = evaluateSelectionEligibility(i3, z);
        }

        public static int compareSelections(List<AudioTrackInfo> list, List<AudioTrackInfo> list2) {
            return ((AudioTrackInfo) Collections.max(list)).compareTo((AudioTrackInfo) Collections.max(list2));
        }

        public static ImmutableList<AudioTrackInfo> createForTrackGroup(int i, TrackGroup trackGroup, Parameters parameters, int[] iArr, boolean z, Predicate<Format> predicate) {
            ImmutableList.Builder builder = ImmutableList.builder();
            for (int i2 = 0; i2 < trackGroup.length; i2++) {
                builder.add(new AudioTrackInfo(i, trackGroup, i2, parameters, iArr[i2], z, predicate));
            }
            return builder.build();
        }

        public final int evaluateSelectionEligibility(int i, boolean z) {
            if (!DefaultTrackSelector.isSupported(i, this.parameters.exceedRendererCapabilitiesIfNecessary)) {
                return 0;
            }
            if (!this.isWithinConstraints && !this.parameters.exceedAudioConstraintsIfNecessary) {
                return 0;
            }
            if (!DefaultTrackSelector.isSupported(i, false) || !this.isWithinConstraints || this.format.bitrate == -1) {
                return 1;
            }
            Parameters parameters = this.parameters;
            if (parameters.forceHighestSupportedBitrate || parameters.forceLowestBitrate) {
                return 1;
            }
            return (parameters.allowMultipleAdaptiveSelections || !z) ? 2 : 1;
        }

        @Override // com.google.android.exoplayer2.trackselection.DefaultTrackSelector.TrackInfo
        public int getSelectionEligibility() {
            return this.selectionEligibility;
        }

        @Override // java.lang.Comparable
        public int compareTo(AudioTrackInfo audioTrackInfo) {
            Ordering orderingReverse = (this.isWithinConstraints && this.isWithinRendererCapabilities) ? DefaultTrackSelector.FORMAT_VALUE_ORDERING : DefaultTrackSelector.FORMAT_VALUE_ORDERING.reverse();
            ComparisonChain comparisonChainCompareFalseFirst = ComparisonChain.ACTIVE.compareFalseFirst(this.isWithinRendererCapabilities, audioTrackInfo.isWithinRendererCapabilities);
            Integer numValueOf = Integer.valueOf(this.preferredLanguageIndex);
            Integer numValueOf2 = Integer.valueOf(audioTrackInfo.preferredLanguageIndex);
            NaturalOrdering.INSTANCE.getClass();
            ReverseNaturalOrdering reverseNaturalOrdering = ReverseNaturalOrdering.INSTANCE;
            ComparisonChain comparisonChainCompare = comparisonChainCompareFalseFirst.compare(numValueOf, numValueOf2, reverseNaturalOrdering).compare(this.preferredLanguageScore, audioTrackInfo.preferredLanguageScore).compare(this.preferredRoleFlagsScore, audioTrackInfo.preferredRoleFlagsScore).compareFalseFirst(this.isDefaultSelectionFlag, audioTrackInfo.isDefaultSelectionFlag).compareFalseFirst(this.hasMainOrNoRoleFlag, audioTrackInfo.hasMainOrNoRoleFlag).compare(Integer.valueOf(this.localeLanguageMatchIndex), Integer.valueOf(audioTrackInfo.localeLanguageMatchIndex), reverseNaturalOrdering).compare(this.localeLanguageScore, audioTrackInfo.localeLanguageScore).compareFalseFirst(this.isWithinConstraints, audioTrackInfo.isWithinConstraints).compare(Integer.valueOf(this.preferredMimeTypeMatchIndex), Integer.valueOf(audioTrackInfo.preferredMimeTypeMatchIndex), reverseNaturalOrdering).compare(Integer.valueOf(this.bitrate), Integer.valueOf(audioTrackInfo.bitrate), this.parameters.forceLowestBitrate ? DefaultTrackSelector.FORMAT_VALUE_ORDERING.reverse() : DefaultTrackSelector.NO_ORDER).compareFalseFirst(this.usesPrimaryDecoder, audioTrackInfo.usesPrimaryDecoder).compareFalseFirst(this.usesHardwareAcceleration, audioTrackInfo.usesHardwareAcceleration).compare(Integer.valueOf(this.channelCount), Integer.valueOf(audioTrackInfo.channelCount), orderingReverse).compare(Integer.valueOf(this.sampleRate), Integer.valueOf(audioTrackInfo.sampleRate), orderingReverse);
            Integer numValueOf3 = Integer.valueOf(this.bitrate);
            Integer numValueOf4 = Integer.valueOf(audioTrackInfo.bitrate);
            if (!Util.areEqual(this.language, audioTrackInfo.language)) {
                orderingReverse = DefaultTrackSelector.NO_ORDER;
            }
            return comparisonChainCompare.compare(numValueOf3, numValueOf4, orderingReverse).result();
        }

        @Override // com.google.android.exoplayer2.trackselection.DefaultTrackSelector.TrackInfo
        public boolean isCompatibleForAdaptationWith(AudioTrackInfo audioTrackInfo) {
            int i;
            String str;
            int i2;
            Parameters parameters = this.parameters;
            if (!parameters.allowAudioMixedChannelCountAdaptiveness && ((i2 = this.format.channelCount) == -1 || i2 != audioTrackInfo.format.channelCount)) {
                return false;
            }
            if (!parameters.allowAudioMixedMimeTypeAdaptiveness && ((str = this.format.sampleMimeType) == null || !TextUtils.equals(str, audioTrackInfo.format.sampleMimeType))) {
                return false;
            }
            Parameters parameters2 = this.parameters;
            if (!parameters2.allowAudioMixedSampleRateAdaptiveness && ((i = this.format.sampleRate) == -1 || i != audioTrackInfo.format.sampleRate)) {
                return false;
            }
            if (parameters2.allowAudioMixedDecoderSupportAdaptiveness) {
                return true;
            }
            return this.usesPrimaryDecoder == audioTrackInfo.usesPrimaryDecoder && this.usesHardwareAcceleration == audioTrackInfo.usesHardwareAcceleration;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class OtherTrackScore implements Comparable<OtherTrackScore> {
        public final boolean isDefault;
        public final boolean isWithinRendererCapabilities;

        public OtherTrackScore(Format format, int i) {
            this.isDefault = (format.selectionFlags & 1) != 0;
            this.isWithinRendererCapabilities = DefaultTrackSelector.isSupported(i, false);
        }

        @Override // java.lang.Comparable
        public int compareTo(OtherTrackScore otherTrackScore) {
            return ComparisonChain.ACTIVE.compareFalseFirst(this.isWithinRendererCapabilities, otherTrackScore.isWithinRendererCapabilities).compareFalseFirst(this.isDefault, otherTrackScore.isDefault).result();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Parameters extends TrackSelectionParameters implements Bundleable {
        public static final Bundleable.Creator<Parameters> CREATOR;

        @Deprecated
        public static final Parameters DEFAULT;
        public static final Parameters DEFAULT_WITHOUT_CONTEXT;
        public static final String FIELD_ALLOW_AUDIO_MIXED_CHANNEL_COUNT_ADAPTIVENESS;
        public static final String FIELD_ALLOW_AUDIO_MIXED_DECODER_SUPPORT_ADAPTIVENESS;
        public static final String FIELD_ALLOW_AUDIO_MIXED_MIME_TYPE_ADAPTIVENESS;
        public static final String FIELD_ALLOW_AUDIO_MIXED_SAMPLE_RATE_ADAPTIVENESS;
        public static final String FIELD_ALLOW_MULTIPLE_ADAPTIVE_SELECTIONS;
        public static final String FIELD_ALLOW_VIDEO_MIXED_DECODER_SUPPORT_ADAPTIVENESS;
        public static final String FIELD_ALLOW_VIDEO_MIXED_MIME_TYPE_ADAPTIVENESS;
        public static final String FIELD_ALLOW_VIDEO_NON_SEAMLESS_ADAPTIVENESS;
        public static final String FIELD_CONSTRAIN_AUDIO_CHANNEL_COUNT_TO_DEVICE_CAPABILITIES;
        public static final String FIELD_EXCEED_AUDIO_CONSTRAINTS_IF_NECESSARY;
        public static final String FIELD_EXCEED_RENDERER_CAPABILITIES_IF_NECESSARY;
        public static final String FIELD_EXCEED_VIDEO_CONSTRAINTS_IF_NECESSARY;
        public static final String FIELD_RENDERER_DISABLED_INDICES;
        public static final String FIELD_SELECTION_OVERRIDES;
        public static final String FIELD_SELECTION_OVERRIDES_RENDERER_INDICES;
        public static final String FIELD_SELECTION_OVERRIDES_TRACK_GROUP_ARRAYS;
        public static final String FIELD_TUNNELING_ENABLED;
        public final boolean allowAudioMixedChannelCountAdaptiveness;
        public final boolean allowAudioMixedDecoderSupportAdaptiveness;
        public final boolean allowAudioMixedMimeTypeAdaptiveness;
        public final boolean allowAudioMixedSampleRateAdaptiveness;
        public final boolean allowMultipleAdaptiveSelections;
        public final boolean allowVideoMixedDecoderSupportAdaptiveness;
        public final boolean allowVideoMixedMimeTypeAdaptiveness;
        public final boolean allowVideoNonSeamlessAdaptiveness;
        public final boolean constrainAudioChannelCountToDeviceCapabilities;
        public final boolean exceedAudioConstraintsIfNecessary;
        public final boolean exceedRendererCapabilitiesIfNecessary;
        public final boolean exceedVideoConstraintsIfNecessary;
        public final SparseBooleanArray rendererDisabledFlags;
        public final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides;
        public final boolean tunnelingEnabled;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static final class Builder extends TrackSelectionParameters.Builder {
            public boolean allowAudioMixedChannelCountAdaptiveness;
            public boolean allowAudioMixedDecoderSupportAdaptiveness;
            public boolean allowAudioMixedMimeTypeAdaptiveness;
            public boolean allowAudioMixedSampleRateAdaptiveness;
            public boolean allowMultipleAdaptiveSelections;
            public boolean allowVideoMixedDecoderSupportAdaptiveness;
            public boolean allowVideoMixedMimeTypeAdaptiveness;
            public boolean allowVideoNonSeamlessAdaptiveness;
            public boolean constrainAudioChannelCountToDeviceCapabilities;
            public boolean exceedAudioConstraintsIfNecessary;
            public boolean exceedRendererCapabilitiesIfNecessary;
            public boolean exceedVideoConstraintsIfNecessary;
            public final SparseBooleanArray rendererDisabledFlags;
            public final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides;
            public boolean tunnelingEnabled;

            public static SparseArray<Map<TrackGroupArray, SelectionOverride>> cloneSelectionOverrides(SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray) {
                SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray2 = new SparseArray<>();
                for (int i = 0; i < sparseArray.size(); i++) {
                    sparseArray2.put(sparseArray.keyAt(i), new HashMap(sparseArray.valueAt(i)));
                }
                return sparseArray2;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder addOverride(TrackSelectionOverride trackSelectionOverride) {
                super.addOverride(trackSelectionOverride);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            public Parameters build() {
                return new Parameters(this);
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder clearOverride(TrackGroup trackGroup) {
                super.clearOverride(trackGroup);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder clearOverrides() {
                super.clearOverrides();
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder clearOverridesOfType(int i) {
                super.clearOverridesOfType(i);
                return this;
            }

            @CanIgnoreReturnValue
            @Deprecated
            public Builder clearSelectionOverride(int i, TrackGroupArray trackGroupArray) {
                Map<TrackGroupArray, SelectionOverride> map = this.selectionOverrides.get(i);
                if (map != null && map.containsKey(trackGroupArray)) {
                    map.remove(trackGroupArray);
                    if (map.isEmpty()) {
                        this.selectionOverrides.remove(i);
                    }
                }
                return this;
            }

            @CanIgnoreReturnValue
            @Deprecated
            public Builder clearSelectionOverrides(int i) {
                Map<TrackGroupArray, SelectionOverride> map = this.selectionOverrides.get(i);
                if (map != null && !map.isEmpty()) {
                    this.selectionOverrides.remove(i);
                }
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder clearVideoSizeConstraints() {
                super.clearVideoSizeConstraints();
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder clearViewportSizeConstraints() {
                super.clearViewportSizeConstraints();
                return this;
            }

            public final void init() {
                this.exceedVideoConstraintsIfNecessary = true;
                this.allowVideoMixedMimeTypeAdaptiveness = false;
                this.allowVideoNonSeamlessAdaptiveness = true;
                this.allowVideoMixedDecoderSupportAdaptiveness = false;
                this.exceedAudioConstraintsIfNecessary = true;
                this.allowAudioMixedMimeTypeAdaptiveness = false;
                this.allowAudioMixedSampleRateAdaptiveness = false;
                this.allowAudioMixedChannelCountAdaptiveness = false;
                this.allowAudioMixedDecoderSupportAdaptiveness = false;
                this.constrainAudioChannelCountToDeviceCapabilities = true;
                this.exceedRendererCapabilitiesIfNecessary = true;
                this.tunnelingEnabled = false;
                this.allowMultipleAdaptiveSelections = true;
            }

            public final SparseBooleanArray makeSparseBooleanArrayFromTrueKeys(@Nullable int[] iArr) {
                if (iArr == null) {
                    return new SparseBooleanArray();
                }
                SparseBooleanArray sparseBooleanArray = new SparseBooleanArray(iArr.length);
                for (int i : iArr) {
                    sparseBooleanArray.append(i, true);
                }
                return sparseBooleanArray;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder set(TrackSelectionParameters trackSelectionParameters) {
                init(trackSelectionParameters);
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setAllowAudioMixedChannelCountAdaptiveness(boolean z) {
                this.allowAudioMixedChannelCountAdaptiveness = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setAllowAudioMixedDecoderSupportAdaptiveness(boolean z) {
                this.allowAudioMixedDecoderSupportAdaptiveness = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setAllowAudioMixedMimeTypeAdaptiveness(boolean z) {
                this.allowAudioMixedMimeTypeAdaptiveness = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setAllowAudioMixedSampleRateAdaptiveness(boolean z) {
                this.allowAudioMixedSampleRateAdaptiveness = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setAllowMultipleAdaptiveSelections(boolean z) {
                this.allowMultipleAdaptiveSelections = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setAllowVideoMixedDecoderSupportAdaptiveness(boolean z) {
                this.allowVideoMixedDecoderSupportAdaptiveness = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setAllowVideoMixedMimeTypeAdaptiveness(boolean z) {
                this.allowVideoMixedMimeTypeAdaptiveness = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setAllowVideoNonSeamlessAdaptiveness(boolean z) {
                this.allowVideoNonSeamlessAdaptiveness = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setConstrainAudioChannelCountToDeviceCapabilities(boolean z) {
                this.constrainAudioChannelCountToDeviceCapabilities = z;
                return this;
            }

            @CanIgnoreReturnValue
            @Deprecated
            public Builder setDisabledTextTrackSelectionFlags(int i) {
                this.ignoredTextSelectionFlags = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            @Deprecated
            public Builder setDisabledTrackTypes(Set<Integer> set) {
                super.setDisabledTrackTypes(set);
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setExceedAudioConstraintsIfNecessary(boolean z) {
                this.exceedAudioConstraintsIfNecessary = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setExceedRendererCapabilitiesIfNecessary(boolean z) {
                this.exceedRendererCapabilitiesIfNecessary = z;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setExceedVideoConstraintsIfNecessary(boolean z) {
                this.exceedVideoConstraintsIfNecessary = z;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setForceHighestSupportedBitrate(boolean z) {
                this.forceHighestSupportedBitrate = z;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setForceLowestBitrate(boolean z) {
                this.forceLowestBitrate = z;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setIgnoredTextSelectionFlags(int i) {
                this.ignoredTextSelectionFlags = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setMaxAudioBitrate(int i) {
                this.maxAudioBitrate = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setMaxAudioChannelCount(int i) {
                this.maxAudioChannelCount = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setMaxVideoBitrate(int i) {
                this.maxVideoBitrate = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setMaxVideoFrameRate(int i) {
                this.maxVideoFrameRate = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setMaxVideoSize(int i, int i2) {
                this.maxVideoWidth = i;
                this.maxVideoHeight = i2;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setMaxVideoSizeSd() {
                super.setMaxVideoSizeSd();
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setMinVideoBitrate(int i) {
                this.minVideoBitrate = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setMinVideoFrameRate(int i) {
                this.minVideoFrameRate = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setMinVideoSize(int i, int i2) {
                this.minVideoWidth = i;
                this.minVideoHeight = i2;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setOverrideForType(TrackSelectionOverride trackSelectionOverride) {
                super.setOverrideForType(trackSelectionOverride);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setPreferredAudioLanguage(@Nullable String str) {
                super.setPreferredAudioLanguage(str);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setPreferredAudioLanguages(String... strArr) {
                super.setPreferredAudioLanguages(strArr);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setPreferredAudioMimeType(@Nullable String str) {
                super.setPreferredAudioMimeType(str);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setPreferredAudioMimeTypes(String... strArr) {
                super.setPreferredAudioMimeTypes(strArr);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setPreferredAudioRoleFlags(int i) {
                this.preferredAudioRoleFlags = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setPreferredTextLanguage(@Nullable String str) {
                super.setPreferredTextLanguage(str);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(Context context) {
                super.setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(context);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setPreferredTextLanguages(String... strArr) {
                super.setPreferredTextLanguages(strArr);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setPreferredTextRoleFlags(int i) {
                this.preferredTextRoleFlags = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setPreferredVideoMimeType(@Nullable String str) {
                super.setPreferredVideoMimeType(str);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setPreferredVideoMimeTypes(String... strArr) {
                super.setPreferredVideoMimeTypes(strArr);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setPreferredVideoRoleFlags(int i) {
                this.preferredVideoRoleFlags = i;
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setRendererDisabled(int i, boolean z) {
                if (this.rendererDisabledFlags.get(i) == z) {
                    return this;
                }
                if (z) {
                    this.rendererDisabledFlags.put(i, true);
                    return this;
                }
                this.rendererDisabledFlags.delete(i);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setSelectUndeterminedTextLanguage(boolean z) {
                this.selectUndeterminedTextLanguage = z;
                return this;
            }

            @CanIgnoreReturnValue
            @Deprecated
            public Builder setSelectionOverride(int i, TrackGroupArray trackGroupArray, @Nullable SelectionOverride selectionOverride) {
                Map<TrackGroupArray, SelectionOverride> map = this.selectionOverrides.get(i);
                if (map == null) {
                    map = new HashMap<>();
                    this.selectionOverrides.put(i, map);
                }
                if (map.containsKey(trackGroupArray) && Util.areEqual(map.get(trackGroupArray), selectionOverride)) {
                    return this;
                }
                map.put(trackGroupArray, selectionOverride);
                return this;
            }

            public final void setSelectionOverridesFromBundle(Bundle bundle) {
                int[] intArray = bundle.getIntArray(Parameters.FIELD_SELECTION_OVERRIDES_RENDERER_INDICES);
                ArrayList parcelableArrayList = bundle.getParcelableArrayList(Parameters.FIELD_SELECTION_OVERRIDES_TRACK_GROUP_ARRAYS);
                ImmutableList<Object> immutableListFromBundleList = parcelableArrayList == null ? RegularImmutableList.EMPTY : BundleableUtil.fromBundleList(TrackGroupArray.CREATOR, parcelableArrayList);
                SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(Parameters.FIELD_SELECTION_OVERRIDES);
                SparseArray sparseArray = sparseParcelableArray == null ? new SparseArray() : BundleableUtil.fromBundleSparseArray(SelectionOverride.CREATOR, sparseParcelableArray);
                if (intArray == null || intArray.length != immutableListFromBundleList.size()) {
                    return;
                }
                for (int i = 0; i < intArray.length; i++) {
                    setSelectionOverride(intArray[i], (TrackGroupArray) immutableListFromBundleList.get(i), (SelectionOverride) sparseArray.get(i));
                }
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setTrackTypeDisabled(int i, boolean z) {
                super.setTrackTypeDisabled(i, z);
                return this;
            }

            @CanIgnoreReturnValue
            public Builder setTunnelingEnabled(boolean z) {
                this.tunnelingEnabled = z;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setViewportSize(int i, int i2, boolean z) {
                super.setViewportSize(i, i2, z);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public Builder setViewportSizeToPhysicalDisplaySize(Context context, boolean z) {
                super.setViewportSizeToPhysicalDisplaySize(context, z);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder addOverride(TrackSelectionOverride trackSelectionOverride) {
                super.addOverride(trackSelectionOverride);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder clearOverride(TrackGroup trackGroup) {
                super.clearOverride(trackGroup);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder clearOverrides() {
                super.clearOverrides();
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder clearOverridesOfType(int i) {
                super.clearOverridesOfType(i);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder clearVideoSizeConstraints() {
                super.clearVideoSizeConstraints();
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder clearViewportSizeConstraints() {
                super.clearViewportSizeConstraints();
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder set(TrackSelectionParameters trackSelectionParameters) {
                init(trackSelectionParameters);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            @Deprecated
            public TrackSelectionParameters.Builder setDisabledTrackTypes(Set set) {
                super.setDisabledTrackTypes((Set<Integer>) set);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setForceHighestSupportedBitrate(boolean z) {
                this.forceHighestSupportedBitrate = z;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setForceLowestBitrate(boolean z) {
                this.forceLowestBitrate = z;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setIgnoredTextSelectionFlags(int i) {
                this.ignoredTextSelectionFlags = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setMaxAudioBitrate(int i) {
                this.maxAudioBitrate = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setMaxAudioChannelCount(int i) {
                this.maxAudioChannelCount = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setMaxVideoBitrate(int i) {
                this.maxVideoBitrate = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setMaxVideoFrameRate(int i) {
                this.maxVideoFrameRate = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setMaxVideoSizeSd() {
                super.setMaxVideoSizeSd();
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setMinVideoBitrate(int i) {
                this.minVideoBitrate = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setMinVideoFrameRate(int i) {
                this.minVideoFrameRate = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setOverrideForType(TrackSelectionOverride trackSelectionOverride) {
                super.setOverrideForType(trackSelectionOverride);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setPreferredAudioLanguage(@Nullable String str) {
                super.setPreferredAudioLanguage(str);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setPreferredAudioLanguages(String[] strArr) {
                super.setPreferredAudioLanguages(strArr);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setPreferredAudioMimeType(@Nullable String str) {
                super.setPreferredAudioMimeType(str);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setPreferredAudioMimeTypes(String[] strArr) {
                super.setPreferredAudioMimeTypes(strArr);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setPreferredAudioRoleFlags(int i) {
                this.preferredAudioRoleFlags = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setPreferredTextLanguage(@Nullable String str) {
                super.setPreferredTextLanguage(str);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(Context context) {
                super.setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(context);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setPreferredTextLanguages(String[] strArr) {
                super.setPreferredTextLanguages(strArr);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setPreferredTextRoleFlags(int i) {
                this.preferredTextRoleFlags = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setPreferredVideoMimeType(@Nullable String str) {
                super.setPreferredVideoMimeType(str);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setPreferredVideoMimeTypes(String[] strArr) {
                super.setPreferredVideoMimeTypes(strArr);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setPreferredVideoRoleFlags(int i) {
                this.preferredVideoRoleFlags = i;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setSelectUndeterminedTextLanguage(boolean z) {
                this.selectUndeterminedTextLanguage = z;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setTrackTypeDisabled(int i, boolean z) {
                super.setTrackTypeDisabled(i, z);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setViewportSize(int i, int i2, boolean z) {
                super.setViewportSize(i, i2, z);
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setViewportSizeToPhysicalDisplaySize(Context context, boolean z) {
                super.setViewportSizeToPhysicalDisplaySize(context, z);
                return this;
            }

            @Deprecated
            public Builder() {
                this.selectionOverrides = new SparseArray<>();
                this.rendererDisabledFlags = new SparseBooleanArray();
                init();
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            public TrackSelectionParameters build() {
                return new Parameters(this);
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setMaxVideoSize(int i, int i2) {
                this.maxVideoWidth = i;
                this.maxVideoHeight = i2;
                return this;
            }

            @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
            @CanIgnoreReturnValue
            public TrackSelectionParameters.Builder setMinVideoSize(int i, int i2) {
                this.minVideoWidth = i;
                this.minVideoHeight = i2;
                return this;
            }

            @CanIgnoreReturnValue
            @Deprecated
            public Builder clearSelectionOverrides() {
                if (this.selectionOverrides.size() == 0) {
                    return this;
                }
                this.selectionOverrides.clear();
                return this;
            }

            public Builder(Context context) {
                super(context);
                this.selectionOverrides = new SparseArray<>();
                this.rendererDisabledFlags = new SparseBooleanArray();
                init();
            }

            public Builder(Parameters parameters) {
                super(parameters);
                this.exceedVideoConstraintsIfNecessary = parameters.exceedVideoConstraintsIfNecessary;
                this.allowVideoMixedMimeTypeAdaptiveness = parameters.allowVideoMixedMimeTypeAdaptiveness;
                this.allowVideoNonSeamlessAdaptiveness = parameters.allowVideoNonSeamlessAdaptiveness;
                this.allowVideoMixedDecoderSupportAdaptiveness = parameters.allowVideoMixedDecoderSupportAdaptiveness;
                this.exceedAudioConstraintsIfNecessary = parameters.exceedAudioConstraintsIfNecessary;
                this.allowAudioMixedMimeTypeAdaptiveness = parameters.allowAudioMixedMimeTypeAdaptiveness;
                this.allowAudioMixedSampleRateAdaptiveness = parameters.allowAudioMixedSampleRateAdaptiveness;
                this.allowAudioMixedChannelCountAdaptiveness = parameters.allowAudioMixedChannelCountAdaptiveness;
                this.allowAudioMixedDecoderSupportAdaptiveness = parameters.allowAudioMixedDecoderSupportAdaptiveness;
                this.constrainAudioChannelCountToDeviceCapabilities = parameters.constrainAudioChannelCountToDeviceCapabilities;
                this.exceedRendererCapabilitiesIfNecessary = parameters.exceedRendererCapabilitiesIfNecessary;
                this.tunnelingEnabled = parameters.tunnelingEnabled;
                this.allowMultipleAdaptiveSelections = parameters.allowMultipleAdaptiveSelections;
                this.selectionOverrides = cloneSelectionOverrides(parameters.selectionOverrides);
                this.rendererDisabledFlags = parameters.rendererDisabledFlags.clone();
            }

            public Builder(Bundle bundle) {
                super(bundle);
                init();
                Parameters parameters = Parameters.DEFAULT_WITHOUT_CONTEXT;
                this.exceedVideoConstraintsIfNecessary = bundle.getBoolean(Parameters.FIELD_EXCEED_VIDEO_CONSTRAINTS_IF_NECESSARY, parameters.exceedVideoConstraintsIfNecessary);
                this.allowVideoMixedMimeTypeAdaptiveness = bundle.getBoolean(Parameters.FIELD_ALLOW_VIDEO_MIXED_MIME_TYPE_ADAPTIVENESS, parameters.allowVideoMixedMimeTypeAdaptiveness);
                this.allowVideoNonSeamlessAdaptiveness = bundle.getBoolean(Parameters.FIELD_ALLOW_VIDEO_NON_SEAMLESS_ADAPTIVENESS, parameters.allowVideoNonSeamlessAdaptiveness);
                this.allowVideoMixedDecoderSupportAdaptiveness = bundle.getBoolean(Parameters.FIELD_ALLOW_VIDEO_MIXED_DECODER_SUPPORT_ADAPTIVENESS, parameters.allowVideoMixedDecoderSupportAdaptiveness);
                this.exceedAudioConstraintsIfNecessary = bundle.getBoolean(Parameters.FIELD_EXCEED_AUDIO_CONSTRAINTS_IF_NECESSARY, parameters.exceedAudioConstraintsIfNecessary);
                this.allowAudioMixedMimeTypeAdaptiveness = bundle.getBoolean(Parameters.FIELD_ALLOW_AUDIO_MIXED_MIME_TYPE_ADAPTIVENESS, parameters.allowAudioMixedMimeTypeAdaptiveness);
                this.allowAudioMixedSampleRateAdaptiveness = bundle.getBoolean(Parameters.FIELD_ALLOW_AUDIO_MIXED_SAMPLE_RATE_ADAPTIVENESS, parameters.allowAudioMixedSampleRateAdaptiveness);
                this.allowAudioMixedChannelCountAdaptiveness = bundle.getBoolean(Parameters.FIELD_ALLOW_AUDIO_MIXED_CHANNEL_COUNT_ADAPTIVENESS, parameters.allowAudioMixedChannelCountAdaptiveness);
                this.allowAudioMixedDecoderSupportAdaptiveness = bundle.getBoolean(Parameters.FIELD_ALLOW_AUDIO_MIXED_DECODER_SUPPORT_ADAPTIVENESS, parameters.allowAudioMixedDecoderSupportAdaptiveness);
                this.constrainAudioChannelCountToDeviceCapabilities = bundle.getBoolean(Parameters.FIELD_CONSTRAIN_AUDIO_CHANNEL_COUNT_TO_DEVICE_CAPABILITIES, parameters.constrainAudioChannelCountToDeviceCapabilities);
                this.exceedRendererCapabilitiesIfNecessary = bundle.getBoolean(Parameters.FIELD_EXCEED_RENDERER_CAPABILITIES_IF_NECESSARY, parameters.exceedRendererCapabilitiesIfNecessary);
                this.tunnelingEnabled = bundle.getBoolean(Parameters.FIELD_TUNNELING_ENABLED, parameters.tunnelingEnabled);
                this.allowMultipleAdaptiveSelections = bundle.getBoolean(Parameters.FIELD_ALLOW_MULTIPLE_ADAPTIVE_SELECTIONS, parameters.allowMultipleAdaptiveSelections);
                this.selectionOverrides = new SparseArray<>();
                setSelectionOverridesFromBundle(bundle);
                this.rendererDisabledFlags = makeSparseBooleanArrayFromTrueKeys(bundle.getIntArray(Parameters.FIELD_RENDERER_DISABLED_INDICES));
            }
        }

        public static Parameters $r8$lambda$yZHSG6Cl_7ZFhubvSrcIsShlwWw(Bundle bundle) {
            return new Parameters(new Builder(bundle));
        }

        static {
            Parameters parameters = new Parameters(new Builder());
            DEFAULT_WITHOUT_CONTEXT = parameters;
            DEFAULT = parameters;
            FIELD_EXCEED_VIDEO_CONSTRAINTS_IF_NECESSARY = Util.intToStringMaxRadix(1000);
            FIELD_ALLOW_VIDEO_MIXED_MIME_TYPE_ADAPTIVENESS = Integer.toString(1001, 36);
            FIELD_ALLOW_VIDEO_NON_SEAMLESS_ADAPTIVENESS = Integer.toString(1002, 36);
            FIELD_EXCEED_AUDIO_CONSTRAINTS_IF_NECESSARY = Integer.toString(1003, 36);
            FIELD_ALLOW_AUDIO_MIXED_MIME_TYPE_ADAPTIVENESS = Integer.toString(1004, 36);
            FIELD_ALLOW_AUDIO_MIXED_SAMPLE_RATE_ADAPTIVENESS = Integer.toString(1005, 36);
            FIELD_ALLOW_AUDIO_MIXED_CHANNEL_COUNT_ADAPTIVENESS = Integer.toString(1006, 36);
            FIELD_EXCEED_RENDERER_CAPABILITIES_IF_NECESSARY = Integer.toString(1007, 36);
            FIELD_TUNNELING_ENABLED = Integer.toString(1008, 36);
            FIELD_ALLOW_MULTIPLE_ADAPTIVE_SELECTIONS = Integer.toString(1009, 36);
            FIELD_SELECTION_OVERRIDES_RENDERER_INDICES = Integer.toString(1010, 36);
            FIELD_SELECTION_OVERRIDES_TRACK_GROUP_ARRAYS = Integer.toString(1011, 36);
            FIELD_SELECTION_OVERRIDES = Integer.toString(1012, 36);
            FIELD_RENDERER_DISABLED_INDICES = Integer.toString(1013, 36);
            FIELD_ALLOW_VIDEO_MIXED_DECODER_SUPPORT_ADAPTIVENESS = Integer.toString(1014, 36);
            FIELD_ALLOW_AUDIO_MIXED_DECODER_SUPPORT_ADAPTIVENESS = Integer.toString(1015, 36);
            FIELD_CONSTRAIN_AUDIO_CHANNEL_COUNT_TO_DEVICE_CAPABILITIES = Integer.toString(1016, 36);
            CREATOR = new DefaultTrackSelector$Parameters$$ExternalSyntheticLambda0();
        }

        public static boolean areRendererDisabledFlagsEqual(SparseBooleanArray sparseBooleanArray, SparseBooleanArray sparseBooleanArray2) {
            int size = sparseBooleanArray.size();
            if (sparseBooleanArray2.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (sparseBooleanArray2.indexOfKey(sparseBooleanArray.keyAt(i)) < 0) {
                    return false;
                }
            }
            return true;
        }

        public static boolean areSelectionOverridesEqual(SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray, SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray2) {
            int size = sparseArray.size();
            if (sparseArray2.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                int iIndexOfKey = sparseArray2.indexOfKey(sparseArray.keyAt(i));
                if (iIndexOfKey < 0 || !areSelectionOverridesEqual(sparseArray.valueAt(i), sparseArray2.valueAt(iIndexOfKey))) {
                    return false;
                }
            }
            return true;
        }

        public static Parameters getDefaults(Context context) {
            return new Parameters(new Builder(context));
        }

        public static int[] getKeysFromSparseBooleanArray(SparseBooleanArray sparseBooleanArray) {
            int[] iArr = new int[sparseBooleanArray.size()];
            for (int i = 0; i < sparseBooleanArray.size(); i++) {
                iArr[i] = sparseBooleanArray.keyAt(i);
            }
            return iArr;
        }

        public static void putSelectionOverridesToBundle(Bundle bundle, SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            SparseArray sparseArray2 = new SparseArray();
            for (int i = 0; i < sparseArray.size(); i++) {
                int iKeyAt = sparseArray.keyAt(i);
                for (Map.Entry<TrackGroupArray, SelectionOverride> entry : sparseArray.valueAt(i).entrySet()) {
                    SelectionOverride value = entry.getValue();
                    if (value != null) {
                        sparseArray2.put(arrayList2.size(), value);
                    }
                    arrayList2.add(entry.getKey());
                    arrayList.add(Integer.valueOf(iKeyAt));
                }
                bundle.putIntArray(FIELD_SELECTION_OVERRIDES_RENDERER_INDICES, Ints.toArray(arrayList));
                bundle.putParcelableArrayList(FIELD_SELECTION_OVERRIDES_TRACK_GROUP_ARRAYS, BundleableUtil.toBundleArrayList(arrayList2));
                bundle.putSparseParcelableArray(FIELD_SELECTION_OVERRIDES, BundleableUtil.toBundleSparseArray(sparseArray2));
            }
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters
        public Builder buildUpon() {
            return new Builder(this);
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters
        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && Parameters.class == obj.getClass()) {
                Parameters parameters = (Parameters) obj;
                if (super.equals(parameters) && this.exceedVideoConstraintsIfNecessary == parameters.exceedVideoConstraintsIfNecessary && this.allowVideoMixedMimeTypeAdaptiveness == parameters.allowVideoMixedMimeTypeAdaptiveness && this.allowVideoNonSeamlessAdaptiveness == parameters.allowVideoNonSeamlessAdaptiveness && this.allowVideoMixedDecoderSupportAdaptiveness == parameters.allowVideoMixedDecoderSupportAdaptiveness && this.exceedAudioConstraintsIfNecessary == parameters.exceedAudioConstraintsIfNecessary && this.allowAudioMixedMimeTypeAdaptiveness == parameters.allowAudioMixedMimeTypeAdaptiveness && this.allowAudioMixedSampleRateAdaptiveness == parameters.allowAudioMixedSampleRateAdaptiveness && this.allowAudioMixedChannelCountAdaptiveness == parameters.allowAudioMixedChannelCountAdaptiveness && this.allowAudioMixedDecoderSupportAdaptiveness == parameters.allowAudioMixedDecoderSupportAdaptiveness && this.constrainAudioChannelCountToDeviceCapabilities == parameters.constrainAudioChannelCountToDeviceCapabilities && this.exceedRendererCapabilitiesIfNecessary == parameters.exceedRendererCapabilitiesIfNecessary && this.tunnelingEnabled == parameters.tunnelingEnabled && this.allowMultipleAdaptiveSelections == parameters.allowMultipleAdaptiveSelections && areRendererDisabledFlagsEqual(this.rendererDisabledFlags, parameters.rendererDisabledFlags) && areSelectionOverridesEqual(this.selectionOverrides, parameters.selectionOverrides)) {
                    return true;
                }
            }
            return false;
        }

        public boolean getRendererDisabled(int i) {
            return this.rendererDisabledFlags.get(i);
        }

        @Nullable
        @Deprecated
        public SelectionOverride getSelectionOverride(int i, TrackGroupArray trackGroupArray) {
            Map<TrackGroupArray, SelectionOverride> map = this.selectionOverrides.get(i);
            if (map != null) {
                return map.get(trackGroupArray);
            }
            return null;
        }

        @Deprecated
        public boolean hasSelectionOverride(int i, TrackGroupArray trackGroupArray) {
            Map<TrackGroupArray, SelectionOverride> map = this.selectionOverrides.get(i);
            return map != null && map.containsKey(trackGroupArray);
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters
        public int hashCode() {
            return ((((((((((((((((((((((((((super.hashCode() + 31) * 31) + (this.exceedVideoConstraintsIfNecessary ? 1 : 0)) * 31) + (this.allowVideoMixedMimeTypeAdaptiveness ? 1 : 0)) * 31) + (this.allowVideoNonSeamlessAdaptiveness ? 1 : 0)) * 31) + (this.allowVideoMixedDecoderSupportAdaptiveness ? 1 : 0)) * 31) + (this.exceedAudioConstraintsIfNecessary ? 1 : 0)) * 31) + (this.allowAudioMixedMimeTypeAdaptiveness ? 1 : 0)) * 31) + (this.allowAudioMixedSampleRateAdaptiveness ? 1 : 0)) * 31) + (this.allowAudioMixedChannelCountAdaptiveness ? 1 : 0)) * 31) + (this.allowAudioMixedDecoderSupportAdaptiveness ? 1 : 0)) * 31) + (this.constrainAudioChannelCountToDeviceCapabilities ? 1 : 0)) * 31) + (this.exceedRendererCapabilitiesIfNecessary ? 1 : 0)) * 31) + (this.tunnelingEnabled ? 1 : 0)) * 31) + (this.allowMultipleAdaptiveSelections ? 1 : 0);
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters, com.google.android.exoplayer2.Bundleable
        public Bundle toBundle() {
            Bundle bundle = super.toBundle();
            bundle.putBoolean(FIELD_EXCEED_VIDEO_CONSTRAINTS_IF_NECESSARY, this.exceedVideoConstraintsIfNecessary);
            bundle.putBoolean(FIELD_ALLOW_VIDEO_MIXED_MIME_TYPE_ADAPTIVENESS, this.allowVideoMixedMimeTypeAdaptiveness);
            bundle.putBoolean(FIELD_ALLOW_VIDEO_NON_SEAMLESS_ADAPTIVENESS, this.allowVideoNonSeamlessAdaptiveness);
            bundle.putBoolean(FIELD_ALLOW_VIDEO_MIXED_DECODER_SUPPORT_ADAPTIVENESS, this.allowVideoMixedDecoderSupportAdaptiveness);
            bundle.putBoolean(FIELD_EXCEED_AUDIO_CONSTRAINTS_IF_NECESSARY, this.exceedAudioConstraintsIfNecessary);
            bundle.putBoolean(FIELD_ALLOW_AUDIO_MIXED_MIME_TYPE_ADAPTIVENESS, this.allowAudioMixedMimeTypeAdaptiveness);
            bundle.putBoolean(FIELD_ALLOW_AUDIO_MIXED_SAMPLE_RATE_ADAPTIVENESS, this.allowAudioMixedSampleRateAdaptiveness);
            bundle.putBoolean(FIELD_ALLOW_AUDIO_MIXED_CHANNEL_COUNT_ADAPTIVENESS, this.allowAudioMixedChannelCountAdaptiveness);
            bundle.putBoolean(FIELD_ALLOW_AUDIO_MIXED_DECODER_SUPPORT_ADAPTIVENESS, this.allowAudioMixedDecoderSupportAdaptiveness);
            bundle.putBoolean(FIELD_CONSTRAIN_AUDIO_CHANNEL_COUNT_TO_DEVICE_CAPABILITIES, this.constrainAudioChannelCountToDeviceCapabilities);
            bundle.putBoolean(FIELD_EXCEED_RENDERER_CAPABILITIES_IF_NECESSARY, this.exceedRendererCapabilitiesIfNecessary);
            bundle.putBoolean(FIELD_TUNNELING_ENABLED, this.tunnelingEnabled);
            bundle.putBoolean(FIELD_ALLOW_MULTIPLE_ADAPTIVE_SELECTIONS, this.allowMultipleAdaptiveSelections);
            putSelectionOverridesToBundle(bundle, this.selectionOverrides);
            bundle.putIntArray(FIELD_RENDERER_DISABLED_INDICES, getKeysFromSparseBooleanArray(this.rendererDisabledFlags));
            return bundle;
        }

        public Parameters(Builder builder) {
            super(builder);
            this.exceedVideoConstraintsIfNecessary = builder.exceedVideoConstraintsIfNecessary;
            this.allowVideoMixedMimeTypeAdaptiveness = builder.allowVideoMixedMimeTypeAdaptiveness;
            this.allowVideoNonSeamlessAdaptiveness = builder.allowVideoNonSeamlessAdaptiveness;
            this.allowVideoMixedDecoderSupportAdaptiveness = builder.allowVideoMixedDecoderSupportAdaptiveness;
            this.exceedAudioConstraintsIfNecessary = builder.exceedAudioConstraintsIfNecessary;
            this.allowAudioMixedMimeTypeAdaptiveness = builder.allowAudioMixedMimeTypeAdaptiveness;
            this.allowAudioMixedSampleRateAdaptiveness = builder.allowAudioMixedSampleRateAdaptiveness;
            this.allowAudioMixedChannelCountAdaptiveness = builder.allowAudioMixedChannelCountAdaptiveness;
            this.allowAudioMixedDecoderSupportAdaptiveness = builder.allowAudioMixedDecoderSupportAdaptiveness;
            this.constrainAudioChannelCountToDeviceCapabilities = builder.constrainAudioChannelCountToDeviceCapabilities;
            this.exceedRendererCapabilitiesIfNecessary = builder.exceedRendererCapabilitiesIfNecessary;
            this.tunnelingEnabled = builder.tunnelingEnabled;
            this.allowMultipleAdaptiveSelections = builder.allowMultipleAdaptiveSelections;
            this.selectionOverrides = builder.selectionOverrides;
            this.rendererDisabledFlags = builder.rendererDisabledFlags;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters
        public TrackSelectionParameters.Builder buildUpon() {
            return new Builder(this);
        }

        public static boolean areSelectionOverridesEqual(Map<TrackGroupArray, SelectionOverride> map, Map<TrackGroupArray, SelectionOverride> map2) {
            if (map2.size() != map.size()) {
                return false;
            }
            for (Map.Entry<TrackGroupArray, SelectionOverride> entry : map.entrySet()) {
                TrackGroupArray key = entry.getKey();
                if (!map2.containsKey(key) || !Util.areEqual(entry.getValue(), map2.get(key))) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Deprecated
    public static final class ParametersBuilder extends TrackSelectionParameters.Builder {
        public final Parameters.Builder delegate;

        @Deprecated
        public ParametersBuilder() {
            this.delegate = new Parameters.Builder();
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder addOverride(TrackSelectionOverride trackSelectionOverride) {
            this.delegate.addOverride(trackSelectionOverride);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder clearOverride(TrackGroup trackGroup) {
            this.delegate.clearOverride(trackGroup);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder clearOverrides() {
            this.delegate.clearOverrides();
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder clearOverridesOfType(int i) {
            this.delegate.clearOverridesOfType(i);
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public ParametersBuilder clearSelectionOverride(int i, TrackGroupArray trackGroupArray) {
            this.delegate.clearSelectionOverride(i, trackGroupArray);
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public ParametersBuilder clearSelectionOverrides(int i) {
            this.delegate.clearSelectionOverrides(i);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder clearVideoSizeConstraints() {
            this.delegate.clearVideoSizeConstraints();
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder clearViewportSizeConstraints() {
            this.delegate.clearViewportSizeConstraints();
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder set(TrackSelectionParameters trackSelectionParameters) {
            this.delegate.init(trackSelectionParameters);
            return this;
        }

        @CanIgnoreReturnValue
        public ParametersBuilder setAllowAudioMixedChannelCountAdaptiveness(boolean z) {
            this.delegate.allowAudioMixedChannelCountAdaptiveness = z;
            return this;
        }

        @CanIgnoreReturnValue
        public ParametersBuilder setAllowAudioMixedDecoderSupportAdaptiveness(boolean z) {
            this.delegate.allowAudioMixedDecoderSupportAdaptiveness = z;
            return this;
        }

        @CanIgnoreReturnValue
        public ParametersBuilder setAllowAudioMixedMimeTypeAdaptiveness(boolean z) {
            this.delegate.allowAudioMixedMimeTypeAdaptiveness = z;
            return this;
        }

        @CanIgnoreReturnValue
        public ParametersBuilder setAllowAudioMixedSampleRateAdaptiveness(boolean z) {
            this.delegate.allowAudioMixedSampleRateAdaptiveness = z;
            return this;
        }

        @CanIgnoreReturnValue
        public ParametersBuilder setAllowMultipleAdaptiveSelections(boolean z) {
            this.delegate.allowMultipleAdaptiveSelections = z;
            return this;
        }

        @CanIgnoreReturnValue
        public ParametersBuilder setAllowVideoMixedDecoderSupportAdaptiveness(boolean z) {
            this.delegate.allowVideoMixedDecoderSupportAdaptiveness = z;
            return this;
        }

        @CanIgnoreReturnValue
        public ParametersBuilder setAllowVideoMixedMimeTypeAdaptiveness(boolean z) {
            this.delegate.allowVideoMixedMimeTypeAdaptiveness = z;
            return this;
        }

        @CanIgnoreReturnValue
        public ParametersBuilder setAllowVideoNonSeamlessAdaptiveness(boolean z) {
            this.delegate.allowVideoNonSeamlessAdaptiveness = z;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public ParametersBuilder setDisabledTextTrackSelectionFlags(int i) {
            this.delegate.ignoredTextSelectionFlags = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        @Deprecated
        public ParametersBuilder setDisabledTrackTypes(Set<Integer> set) {
            this.delegate.setDisabledTrackTypes(set);
            return this;
        }

        @CanIgnoreReturnValue
        public ParametersBuilder setExceedAudioConstraintsIfNecessary(boolean z) {
            this.delegate.exceedAudioConstraintsIfNecessary = z;
            return this;
        }

        @CanIgnoreReturnValue
        public ParametersBuilder setExceedRendererCapabilitiesIfNecessary(boolean z) {
            this.delegate.exceedRendererCapabilitiesIfNecessary = z;
            return this;
        }

        @CanIgnoreReturnValue
        public ParametersBuilder setExceedVideoConstraintsIfNecessary(boolean z) {
            this.delegate.exceedVideoConstraintsIfNecessary = z;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setForceHighestSupportedBitrate(boolean z) {
            this.delegate.forceHighestSupportedBitrate = z;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setForceLowestBitrate(boolean z) {
            this.delegate.forceLowestBitrate = z;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setIgnoredTextSelectionFlags(int i) {
            this.delegate.ignoredTextSelectionFlags = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setMaxAudioBitrate(int i) {
            this.delegate.maxAudioBitrate = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setMaxAudioChannelCount(int i) {
            this.delegate.maxAudioChannelCount = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setMaxVideoBitrate(int i) {
            this.delegate.maxVideoBitrate = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setMaxVideoFrameRate(int i) {
            this.delegate.maxVideoFrameRate = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ TrackSelectionParameters.Builder setMaxVideoSize(int i, int i2) {
            setMaxVideoSize(i, i2);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setMaxVideoSizeSd() {
            this.delegate.setMaxVideoSizeSd();
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setMinVideoBitrate(int i) {
            this.delegate.minVideoBitrate = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setMinVideoFrameRate(int i) {
            this.delegate.minVideoFrameRate = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ TrackSelectionParameters.Builder setMinVideoSize(int i, int i2) {
            setMinVideoSize(i, i2);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setOverrideForType(TrackSelectionOverride trackSelectionOverride) {
            this.delegate.setOverrideForType(trackSelectionOverride);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setPreferredAudioLanguage(@Nullable String str) {
            this.delegate.setPreferredAudioLanguage(str);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setPreferredAudioLanguages(String... strArr) {
            this.delegate.setPreferredAudioLanguages(strArr);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setPreferredAudioMimeType(@Nullable String str) {
            this.delegate.setPreferredAudioMimeType(str);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setPreferredAudioMimeTypes(String... strArr) {
            this.delegate.setPreferredAudioMimeTypes(strArr);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setPreferredAudioRoleFlags(int i) {
            this.delegate.preferredAudioRoleFlags = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setPreferredTextLanguage(@Nullable String str) {
            this.delegate.setPreferredTextLanguage(str);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(Context context) {
            this.delegate.setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(context);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setPreferredTextLanguages(String... strArr) {
            this.delegate.setPreferredTextLanguages(strArr);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setPreferredTextRoleFlags(int i) {
            this.delegate.preferredTextRoleFlags = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setPreferredVideoMimeType(@Nullable String str) {
            this.delegate.setPreferredVideoMimeType(str);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setPreferredVideoMimeTypes(String... strArr) {
            this.delegate.setPreferredVideoMimeTypes(strArr);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setPreferredVideoRoleFlags(int i) {
            this.delegate.preferredVideoRoleFlags = i;
            return this;
        }

        @CanIgnoreReturnValue
        public ParametersBuilder setRendererDisabled(int i, boolean z) {
            this.delegate.setRendererDisabled(i, z);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setSelectUndeterminedTextLanguage(boolean z) {
            this.delegate.selectUndeterminedTextLanguage = z;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public ParametersBuilder setSelectionOverride(int i, TrackGroupArray trackGroupArray, @Nullable SelectionOverride selectionOverride) {
            this.delegate.setSelectionOverride(i, trackGroupArray, selectionOverride);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setTrackTypeDisabled(int i, boolean z) {
            this.delegate.setTrackTypeDisabled(i, z);
            return this;
        }

        @CanIgnoreReturnValue
        public ParametersBuilder setTunnelingEnabled(boolean z) {
            this.delegate.tunnelingEnabled = z;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setViewportSize(int i, int i2, boolean z) {
            this.delegate.setViewportSize(i, i2, z);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setViewportSizeToPhysicalDisplaySize(Context context, boolean z) {
            this.delegate.setViewportSizeToPhysicalDisplaySize(context, z);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder addOverride(TrackSelectionOverride trackSelectionOverride) {
            this.delegate.addOverride(trackSelectionOverride);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        public Parameters build() {
            Parameters.Builder builder = this.delegate;
            builder.getClass();
            return new Parameters(builder);
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder clearOverride(TrackGroup trackGroup) {
            this.delegate.clearOverride(trackGroup);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder clearOverrides() {
            this.delegate.clearOverrides();
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder clearOverridesOfType(int i) {
            this.delegate.clearOverridesOfType(i);
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public ParametersBuilder clearSelectionOverrides() {
            this.delegate.clearSelectionOverrides();
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder clearVideoSizeConstraints() {
            this.delegate.clearVideoSizeConstraints();
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder clearViewportSizeConstraints() {
            this.delegate.clearViewportSizeConstraints();
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        @Deprecated
        public TrackSelectionParameters.Builder setDisabledTrackTypes(Set set) {
            this.delegate.setDisabledTrackTypes((Set<Integer>) set);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setMaxVideoSize(int i, int i2) {
            Parameters.Builder builder = this.delegate;
            builder.maxVideoWidth = i;
            builder.maxVideoHeight = i2;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setMaxVideoSizeSd() {
            this.delegate.setMaxVideoSizeSd();
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public ParametersBuilder setMinVideoSize(int i, int i2) {
            Parameters.Builder builder = this.delegate;
            builder.minVideoWidth = i;
            builder.minVideoHeight = i2;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setOverrideForType(TrackSelectionOverride trackSelectionOverride) {
            this.delegate.setOverrideForType(trackSelectionOverride);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setPreferredAudioLanguage(@Nullable String str) {
            this.delegate.setPreferredAudioLanguage(str);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setPreferredAudioLanguages(String[] strArr) {
            this.delegate.setPreferredAudioLanguages(strArr);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setPreferredAudioMimeType(@Nullable String str) {
            this.delegate.setPreferredAudioMimeType(str);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setPreferredAudioMimeTypes(String[] strArr) {
            this.delegate.setPreferredAudioMimeTypes(strArr);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setPreferredTextLanguage(@Nullable String str) {
            this.delegate.setPreferredTextLanguage(str);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(Context context) {
            this.delegate.setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(context);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setPreferredTextLanguages(String[] strArr) {
            this.delegate.setPreferredTextLanguages(strArr);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setPreferredVideoMimeType(@Nullable String str) {
            this.delegate.setPreferredVideoMimeType(str);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setPreferredVideoMimeTypes(String[] strArr) {
            this.delegate.setPreferredVideoMimeTypes(strArr);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setTrackTypeDisabled(int i, boolean z) {
            this.delegate.setTrackTypeDisabled(i, z);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setViewportSize(int i, int i2, boolean z) {
            this.delegate.setViewportSize(i, i2, z);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setViewportSizeToPhysicalDisplaySize(Context context, boolean z) {
            this.delegate.setViewportSizeToPhysicalDisplaySize(context, z);
            return this;
        }

        public ParametersBuilder(Context context) {
            this.delegate = new Parameters.Builder(context);
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder set(TrackSelectionParameters trackSelectionParameters) {
            this.delegate.init(trackSelectionParameters);
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setForceHighestSupportedBitrate(boolean z) {
            this.delegate.forceHighestSupportedBitrate = z;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setForceLowestBitrate(boolean z) {
            this.delegate.forceLowestBitrate = z;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setIgnoredTextSelectionFlags(int i) {
            this.delegate.ignoredTextSelectionFlags = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setMaxAudioBitrate(int i) {
            this.delegate.maxAudioBitrate = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setMaxAudioChannelCount(int i) {
            this.delegate.maxAudioChannelCount = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setMaxVideoBitrate(int i) {
            this.delegate.maxVideoBitrate = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setMaxVideoFrameRate(int i) {
            this.delegate.maxVideoFrameRate = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setMinVideoBitrate(int i) {
            this.delegate.minVideoBitrate = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setMinVideoFrameRate(int i) {
            this.delegate.minVideoFrameRate = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setPreferredAudioRoleFlags(int i) {
            this.delegate.preferredAudioRoleFlags = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setPreferredTextRoleFlags(int i) {
            this.delegate.preferredTextRoleFlags = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setPreferredVideoRoleFlags(int i) {
            this.delegate.preferredVideoRoleFlags = i;
            return this;
        }

        @Override // com.google.android.exoplayer2.trackselection.TrackSelectionParameters.Builder
        @CanIgnoreReturnValue
        public TrackSelectionParameters.Builder setSelectUndeterminedTextLanguage(boolean z) {
            this.delegate.selectUndeterminedTextLanguage = z;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class SelectionOverride implements Bundleable {
        public final int groupIndex;
        public final int length;
        public final int[] tracks;
        public final int type;
        public static final String FIELD_GROUP_INDEX = Util.intToStringMaxRadix(0);
        public static final String FIELD_TRACKS = Integer.toString(1, 36);
        public static final String FIELD_TRACK_TYPE = Integer.toString(2, 36);
        public static final Bundleable.Creator<SelectionOverride> CREATOR = new DefaultTrackSelector$SelectionOverride$$ExternalSyntheticLambda0();

        public static SelectionOverride $r8$lambda$NrcuImclxfyMS7IzFykzM9hD3rk(Bundle bundle) {
            int i = bundle.getInt(FIELD_GROUP_INDEX, -1);
            int[] intArray = bundle.getIntArray(FIELD_TRACKS);
            int i2 = bundle.getInt(FIELD_TRACK_TYPE, -1);
            Assertions.checkArgument(i >= 0 && i2 >= 0);
            intArray.getClass();
            return new SelectionOverride(i, intArray, i2);
        }

        public SelectionOverride(int i, int... iArr) {
            this(i, iArr, 0);
        }

        public boolean containsTrack(int i) {
            for (int i2 : this.tracks) {
                if (i2 == i) {
                    return true;
                }
            }
            return false;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && SelectionOverride.class == obj.getClass()) {
                SelectionOverride selectionOverride = (SelectionOverride) obj;
                if (this.groupIndex == selectionOverride.groupIndex && Arrays.equals(this.tracks, selectionOverride.tracks) && this.type == selectionOverride.type) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return ((Arrays.hashCode(this.tracks) + (this.groupIndex * 31)) * 31) + this.type;
        }

        @Override // com.google.android.exoplayer2.Bundleable
        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putInt(FIELD_GROUP_INDEX, this.groupIndex);
            bundle.putIntArray(FIELD_TRACKS, this.tracks);
            bundle.putInt(FIELD_TRACK_TYPE, this.type);
            return bundle;
        }

        public SelectionOverride(int i, int[] iArr, int i2) {
            this.groupIndex = i;
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
            this.tracks = iArrCopyOf;
            this.length = iArr.length;
            this.type = i2;
            Arrays.sort(iArrCopyOf);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @RequiresApi(32)
    public static class SpatializerWrapperV32 {

        @Nullable
        public Handler handler;

        @Nullable
        public Spatializer.OnSpatializerStateChangedListener listener;
        public final boolean spatializationSupported;
        public final Spatializer spatializer;

        public SpatializerWrapperV32(Spatializer spatializer) {
            this.spatializer = spatializer;
            this.spatializationSupported = spatializer.getImmersiveAudioLevel() != 0;
        }

        @Nullable
        public static SpatializerWrapperV32 tryCreateInstance(Context context) {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            if (audioManager == null) {
                return null;
            }
            return new SpatializerWrapperV32(audioManager.getSpatializer());
        }

        public boolean canBeSpatialized(AudioAttributes audioAttributes, Format format) {
            AudioFormat.Builder channelMask = new AudioFormat.Builder().setEncoding(2).setChannelMask(Util.getAudioTrackChannelConfig(("audio/eac3-joc".equals(format.sampleMimeType) && format.channelCount == 16) ? 12 : format.channelCount));
            int i = format.sampleRate;
            if (i != -1) {
                channelMask.setSampleRate(i);
            }
            return this.spatializer.canBeSpatialized(audioAttributes.getAudioAttributesV21().audioAttributes, channelMask.build());
        }

        public void ensureInitialized(final DefaultTrackSelector defaultTrackSelector, Looper looper) {
            if (this.listener == null && this.handler == null) {
                this.listener = new Spatializer.OnSpatializerStateChangedListener() { // from class: com.google.android.exoplayer2.trackselection.DefaultTrackSelector.SpatializerWrapperV32.1
                    @Override // android.media.Spatializer.OnSpatializerStateChangedListener
                    public void onSpatializerAvailableChanged(Spatializer spatializer, boolean z) {
                        defaultTrackSelector.maybeInvalidateForAudioChannelCountConstraints();
                    }

                    @Override // android.media.Spatializer.OnSpatializerStateChangedListener
                    public void onSpatializerEnabledChanged(Spatializer spatializer, boolean z) {
                        defaultTrackSelector.maybeInvalidateForAudioChannelCountConstraints();
                    }
                };
                Handler handler = new Handler(looper);
                this.handler = handler;
                Spatializer spatializer = this.spatializer;
                Objects.requireNonNull(handler);
                spatializer.addOnSpatializerStateChangedListener(new ConcurrencyHelpers$$ExternalSyntheticLambda0(handler), this.listener);
            }
        }

        public boolean isAvailable() {
            return this.spatializer.isAvailable();
        }

        public boolean isEnabled() {
            return this.spatializer.isEnabled();
        }

        public boolean isSpatializationSupported() {
            return this.spatializationSupported;
        }

        public void release() {
            Spatializer.OnSpatializerStateChangedListener onSpatializerStateChangedListener = this.listener;
            if (onSpatializerStateChangedListener == null || this.handler == null) {
                return;
            }
            this.spatializer.removeOnSpatializerStateChangedListener(onSpatializerStateChangedListener);
            Handler handler = this.handler;
            Util.castNonNull(handler);
            handler.removeCallbacksAndMessages(null);
            this.handler = null;
            this.listener = null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class TextTrackInfo extends TrackInfo<TextTrackInfo> implements Comparable<TextTrackInfo> {
        public final boolean hasCaptionRoleFlags;
        public final boolean isDefault;
        public final boolean isForced;
        public final boolean isWithinRendererCapabilities;
        public final int preferredLanguageIndex;
        public final int preferredLanguageScore;
        public final int preferredRoleFlagsScore;
        public final int selectedAudioLanguageScore;
        public final int selectionEligibility;

        public TextTrackInfo(int i, TrackGroup trackGroup, int i2, Parameters parameters, int i3, @Nullable String str) {
            int formatLanguageScore;
            super(i, trackGroup, i2);
            int i4 = 0;
            this.isWithinRendererCapabilities = DefaultTrackSelector.isSupported(i3, false);
            int i5 = this.format.selectionFlags & (~parameters.ignoredTextSelectionFlags);
            this.isDefault = (i5 & 1) != 0;
            this.isForced = (i5 & 2) != 0;
            ImmutableList<String> immutableListOf = parameters.preferredTextLanguages.isEmpty() ? ImmutableList.of("") : parameters.preferredTextLanguages;
            int i6 = 0;
            while (true) {
                if (i6 >= immutableListOf.size()) {
                    i6 = Integer.MAX_VALUE;
                    formatLanguageScore = 0;
                    break;
                } else {
                    formatLanguageScore = DefaultTrackSelector.getFormatLanguageScore(this.format, immutableListOf.get(i6), parameters.selectUndeterminedTextLanguage);
                    if (formatLanguageScore > 0) {
                        break;
                    } else {
                        i6++;
                    }
                }
            }
            this.preferredLanguageIndex = i6;
            this.preferredLanguageScore = formatLanguageScore;
            int roleFlagMatchScore = DefaultTrackSelector.getRoleFlagMatchScore(this.format.roleFlags, parameters.preferredTextRoleFlags);
            this.preferredRoleFlagsScore = roleFlagMatchScore;
            this.hasCaptionRoleFlags = (this.format.roleFlags & 1088) != 0;
            int formatLanguageScore2 = DefaultTrackSelector.getFormatLanguageScore(this.format, str, DefaultTrackSelector.normalizeUndeterminedLanguageToNull(str) == null);
            this.selectedAudioLanguageScore = formatLanguageScore2;
            boolean z = formatLanguageScore > 0 || (parameters.preferredTextLanguages.isEmpty() && roleFlagMatchScore > 0) || this.isDefault || (this.isForced && formatLanguageScore2 > 0);
            if (DefaultTrackSelector.isSupported(i3, parameters.exceedRendererCapabilitiesIfNecessary) && z) {
                i4 = 1;
            }
            this.selectionEligibility = i4;
        }

        public static int compareSelections(List<TextTrackInfo> list, List<TextTrackInfo> list2) {
            return list.get(0).compareTo(list2.get(0));
        }

        public static ImmutableList<TextTrackInfo> createForTrackGroup(int i, TrackGroup trackGroup, Parameters parameters, int[] iArr, @Nullable String str) {
            ImmutableList.Builder builder = ImmutableList.builder();
            for (int i2 = 0; i2 < trackGroup.length; i2++) {
                builder.add(new TextTrackInfo(i, trackGroup, i2, parameters, iArr[i2], str));
            }
            return builder.build();
        }

        @Override // java.lang.Comparable
        public int compareTo(TextTrackInfo textTrackInfo) {
            ComparisonChain comparisonChainCompareFalseFirst = ComparisonChain.ACTIVE.compareFalseFirst(this.isWithinRendererCapabilities, textTrackInfo.isWithinRendererCapabilities);
            Integer numValueOf = Integer.valueOf(this.preferredLanguageIndex);
            Integer numValueOf2 = Integer.valueOf(textTrackInfo.preferredLanguageIndex);
            Ordering ordering = NaturalOrdering.INSTANCE;
            ordering.getClass();
            ReverseNaturalOrdering reverseNaturalOrdering = ReverseNaturalOrdering.INSTANCE;
            ComparisonChain comparisonChainCompareFalseFirst2 = comparisonChainCompareFalseFirst.compare(numValueOf, numValueOf2, reverseNaturalOrdering).compare(this.preferredLanguageScore, textTrackInfo.preferredLanguageScore).compare(this.preferredRoleFlagsScore, textTrackInfo.preferredRoleFlagsScore).compareFalseFirst(this.isDefault, textTrackInfo.isDefault);
            Boolean boolValueOf = Boolean.valueOf(this.isForced);
            Boolean boolValueOf2 = Boolean.valueOf(textTrackInfo.isForced);
            if (this.preferredLanguageScore != 0) {
                ordering = reverseNaturalOrdering;
            }
            ComparisonChain comparisonChainCompare = comparisonChainCompareFalseFirst2.compare(boolValueOf, boolValueOf2, ordering).compare(this.selectedAudioLanguageScore, textTrackInfo.selectedAudioLanguageScore);
            if (this.preferredRoleFlagsScore == 0) {
                comparisonChainCompare = comparisonChainCompare.compareTrueFirst(this.hasCaptionRoleFlags, textTrackInfo.hasCaptionRoleFlags);
            }
            return comparisonChainCompare.result();
        }

        @Override // com.google.android.exoplayer2.trackselection.DefaultTrackSelector.TrackInfo
        public int getSelectionEligibility() {
            return this.selectionEligibility;
        }

        public boolean isCompatibleForAdaptationWith(TextTrackInfo textTrackInfo) {
            return false;
        }

        @Override // com.google.android.exoplayer2.trackselection.DefaultTrackSelector.TrackInfo
        public /* bridge */ /* synthetic */ boolean isCompatibleForAdaptationWith(TrackInfo trackInfo) {
            return false;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static abstract class TrackInfo<T extends TrackInfo<T>> {
        public final Format format;
        public final int rendererIndex;
        public final TrackGroup trackGroup;
        public final int trackIndex;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public interface Factory<T extends TrackInfo<T>> {
            List<T> create(int i, TrackGroup trackGroup, int[] iArr);
        }

        public TrackInfo(int i, TrackGroup trackGroup, int i2) {
            this.rendererIndex = i;
            this.trackGroup = trackGroup;
            this.trackIndex = i2;
            this.format = trackGroup.formats[i2];
        }

        public abstract int getSelectionEligibility();

        public abstract boolean isCompatibleForAdaptationWith(T t);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class VideoTrackInfo extends TrackInfo<VideoTrackInfo> {
        public final boolean allowMixedMimeTypes;
        public final int bitrate;
        public final int codecPreferenceScore;
        public final boolean hasMainOrNoRoleFlag;
        public final boolean isWithinMaxConstraints;
        public final boolean isWithinMinConstraints;
        public final boolean isWithinRendererCapabilities;
        public final Parameters parameters;
        public final int pixelCount;
        public final int preferredMimeTypeMatchIndex;
        public final int preferredRoleFlagsScore;
        public final int selectionEligibility;
        public final boolean usesHardwareAcceleration;
        public final boolean usesPrimaryDecoder;

        /* JADX WARN: Removed duplicated region for block: B:31:0x004b  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x0079  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public VideoTrackInfo(int r5, com.google.android.exoplayer2.source.TrackGroup r6, int r7, com.google.android.exoplayer2.trackselection.DefaultTrackSelector.Parameters r8, int r9, int r10, boolean r11) {
            /*
                Method dump skipped, instruction units count: 242
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.trackselection.DefaultTrackSelector.VideoTrackInfo.<init>(int, com.google.android.exoplayer2.source.TrackGroup, int, com.google.android.exoplayer2.trackselection.DefaultTrackSelector$Parameters, int, int, boolean):void");
        }

        public static int compareNonQualityPreferences(VideoTrackInfo videoTrackInfo, VideoTrackInfo videoTrackInfo2) {
            ComparisonChain comparisonChainCompareFalseFirst = ComparisonChain.ACTIVE.compareFalseFirst(videoTrackInfo.isWithinRendererCapabilities, videoTrackInfo2.isWithinRendererCapabilities).compare(videoTrackInfo.preferredRoleFlagsScore, videoTrackInfo2.preferredRoleFlagsScore).compareFalseFirst(videoTrackInfo.hasMainOrNoRoleFlag, videoTrackInfo2.hasMainOrNoRoleFlag).compareFalseFirst(videoTrackInfo.isWithinMaxConstraints, videoTrackInfo2.isWithinMaxConstraints).compareFalseFirst(videoTrackInfo.isWithinMinConstraints, videoTrackInfo2.isWithinMinConstraints);
            Integer numValueOf = Integer.valueOf(videoTrackInfo.preferredMimeTypeMatchIndex);
            Integer numValueOf2 = Integer.valueOf(videoTrackInfo2.preferredMimeTypeMatchIndex);
            NaturalOrdering.INSTANCE.getClass();
            ComparisonChain comparisonChainCompareFalseFirst2 = comparisonChainCompareFalseFirst.compare(numValueOf, numValueOf2, ReverseNaturalOrdering.INSTANCE).compareFalseFirst(videoTrackInfo.usesPrimaryDecoder, videoTrackInfo2.usesPrimaryDecoder).compareFalseFirst(videoTrackInfo.usesHardwareAcceleration, videoTrackInfo2.usesHardwareAcceleration);
            if (videoTrackInfo.usesPrimaryDecoder && videoTrackInfo.usesHardwareAcceleration) {
                comparisonChainCompareFalseFirst2 = comparisonChainCompareFalseFirst2.compare(videoTrackInfo.codecPreferenceScore, videoTrackInfo2.codecPreferenceScore);
            }
            return comparisonChainCompareFalseFirst2.result();
        }

        public static int compareQualityPreferences(VideoTrackInfo videoTrackInfo, VideoTrackInfo videoTrackInfo2) {
            Ordering orderingReverse = (videoTrackInfo.isWithinMaxConstraints && videoTrackInfo.isWithinRendererCapabilities) ? DefaultTrackSelector.FORMAT_VALUE_ORDERING : DefaultTrackSelector.FORMAT_VALUE_ORDERING.reverse();
            return ComparisonChain.ACTIVE.compare(Integer.valueOf(videoTrackInfo.bitrate), Integer.valueOf(videoTrackInfo2.bitrate), videoTrackInfo.parameters.forceLowestBitrate ? DefaultTrackSelector.FORMAT_VALUE_ORDERING.reverse() : DefaultTrackSelector.NO_ORDER).compare(Integer.valueOf(videoTrackInfo.pixelCount), Integer.valueOf(videoTrackInfo2.pixelCount), orderingReverse).compare(Integer.valueOf(videoTrackInfo.bitrate), Integer.valueOf(videoTrackInfo2.bitrate), orderingReverse).result();
        }

        public static int compareSelections(List<VideoTrackInfo> list, List<VideoTrackInfo> list2) {
            return ComparisonChain.ACTIVE.compare((VideoTrackInfo) Collections.max(list, new DefaultTrackSelector$VideoTrackInfo$$ExternalSyntheticLambda0()), (VideoTrackInfo) Collections.max(list2, new DefaultTrackSelector$VideoTrackInfo$$ExternalSyntheticLambda0()), new DefaultTrackSelector$VideoTrackInfo$$ExternalSyntheticLambda0()).compare(list.size(), list2.size()).compare((VideoTrackInfo) Collections.max(list, new DefaultTrackSelector$VideoTrackInfo$$ExternalSyntheticLambda1()), (VideoTrackInfo) Collections.max(list2, new DefaultTrackSelector$VideoTrackInfo$$ExternalSyntheticLambda1()), new DefaultTrackSelector$VideoTrackInfo$$ExternalSyntheticLambda1()).result();
        }

        public static ImmutableList<VideoTrackInfo> createForTrackGroup(int i, TrackGroup trackGroup, Parameters parameters, int[] iArr, int i2) {
            int maxVideoPixelsToRetainForViewport = DefaultTrackSelector.getMaxVideoPixelsToRetainForViewport(trackGroup, parameters.viewportWidth, parameters.viewportHeight, parameters.viewportOrientationMayChange);
            ImmutableList.Builder builder = ImmutableList.builder();
            for (int i3 = 0; i3 < trackGroup.length; i3++) {
                int pixelCount = trackGroup.formats[i3].getPixelCount();
                builder.add(new VideoTrackInfo(i, trackGroup, i3, parameters, iArr[i3], i2, maxVideoPixelsToRetainForViewport == Integer.MAX_VALUE || (pixelCount != -1 && pixelCount <= maxVideoPixelsToRetainForViewport)));
            }
            return builder.build();
        }

        public final int evaluateSelectionEligibility(int i, int i2) {
            if ((this.format.roleFlags & 16384) != 0 || !DefaultTrackSelector.isSupported(i, this.parameters.exceedRendererCapabilitiesIfNecessary)) {
                return 0;
            }
            if (!this.isWithinMaxConstraints && !this.parameters.exceedVideoConstraintsIfNecessary) {
                return 0;
            }
            if (!DefaultTrackSelector.isSupported(i, false) || !this.isWithinMinConstraints || !this.isWithinMaxConstraints || this.format.bitrate == -1) {
                return 1;
            }
            Parameters parameters = this.parameters;
            return (parameters.forceHighestSupportedBitrate || parameters.forceLowestBitrate || (i & i2) == 0) ? 1 : 2;
        }

        @Override // com.google.android.exoplayer2.trackselection.DefaultTrackSelector.TrackInfo
        public int getSelectionEligibility() {
            return this.selectionEligibility;
        }

        @Override // com.google.android.exoplayer2.trackselection.DefaultTrackSelector.TrackInfo
        public boolean isCompatibleForAdaptationWith(VideoTrackInfo videoTrackInfo) {
            if (!this.allowMixedMimeTypes && !Util.areEqual(this.format.sampleMimeType, videoTrackInfo.format.sampleMimeType)) {
                return false;
            }
            if (this.parameters.allowVideoMixedDecoderSupportAdaptiveness) {
                return true;
            }
            return this.usesPrimaryDecoder == videoTrackInfo.usesPrimaryDecoder && this.usesHardwareAcceleration == videoTrackInfo.usesHardwareAcceleration;
        }
    }

    public static /* synthetic */ int $r8$lambda$TgzEdaT8vw77vbxe4MS8W_aJRBE(Integer num, Integer num2) {
        return 0;
    }

    public static /* synthetic */ int $r8$lambda$ab5pcQn94AtNhTwBQsSm2Lfz01g(Integer num, Integer num2) {
        if (num.intValue() == -1) {
            return num2.intValue() == -1 ? 0 : -1;
        }
        if (num2.intValue() == -1) {
            return 1;
        }
        return num.intValue() - num2.intValue();
    }

    public static /* synthetic */ List $r8$lambda$sih5Pv2vBFLqgJovgGnPmMZfQd4(final DefaultTrackSelector defaultTrackSelector, Parameters parameters, boolean z, int i, TrackGroup trackGroup, int[] iArr) {
        defaultTrackSelector.getClass();
        return AudioTrackInfo.createForTrackGroup(i, trackGroup, parameters, iArr, z, new Predicate() { // from class: com.google.android.exoplayer2.trackselection.DefaultTrackSelector$$ExternalSyntheticLambda4
            @Override // com.google.common.base.Predicate
            public final boolean apply(Object obj) {
                return this.f$0.isAudioFormatWithinAudioChannelCountConstraints((Format) obj);
            }
        });
    }

    public DefaultTrackSelector(TrackSelectionParameters trackSelectionParameters, ExoTrackSelection.Factory factory, @Nullable Context context) {
        this.lock = new Object();
        this.context = context != null ? context.getApplicationContext() : null;
        this.trackSelectionFactory = factory;
        if (trackSelectionParameters instanceof Parameters) {
            this.parameters = (Parameters) trackSelectionParameters;
        } else {
            Parameters defaults = context == null ? Parameters.DEFAULT_WITHOUT_CONTEXT : Parameters.getDefaults(context);
            defaults.getClass();
            Parameters.Builder builder = new Parameters.Builder(defaults);
            builder.init(trackSelectionParameters);
            this.parameters = new Parameters(builder);
        }
        this.audioAttributes = AudioAttributes.DEFAULT;
        boolean z = context != null && Util.isTv(context);
        this.deviceIsTV = z;
        if (!z && context != null && Util.SDK_INT >= 32) {
            this.spatializer = SpatializerWrapperV32.tryCreateInstance(context);
        }
        if (this.parameters.constrainAudioChannelCountToDeviceCapabilities && context == null) {
            Log.w("DefaultTrackSelector", "Audio channel count constraints cannot be applied without reference to Context. Build the track selector instance with one of the non-deprecated constructors that take a Context argument.");
        }
    }

    public static void applyLegacyRendererOverrides(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, Parameters parameters, ExoTrackSelection.Definition[] definitionArr) {
        int i = mappedTrackInfo.rendererCount;
        for (int i2 = 0; i2 < i; i2++) {
            TrackGroupArray trackGroupArray = mappedTrackInfo.rendererTrackGroups[i2];
            if (parameters.hasSelectionOverride(i2, trackGroupArray)) {
                SelectionOverride selectionOverride = parameters.getSelectionOverride(i2, trackGroupArray);
                definitionArr[i2] = (selectionOverride == null || selectionOverride.tracks.length == 0) ? null : new ExoTrackSelection.Definition(trackGroupArray.get(selectionOverride.groupIndex), selectionOverride.tracks, selectionOverride.type);
            }
        }
    }

    public static void applyTrackSelectionOverrides(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, TrackSelectionParameters trackSelectionParameters, ExoTrackSelection.Definition[] definitionArr) {
        int i = mappedTrackInfo.rendererCount;
        HashMap map = new HashMap();
        for (int i2 = 0; i2 < i; i2++) {
            collectTrackSelectionOverrides(mappedTrackInfo.rendererTrackGroups[i2], trackSelectionParameters, map);
        }
        collectTrackSelectionOverrides(mappedTrackInfo.unmappedTrackGroups, trackSelectionParameters, map);
        for (int i3 = 0; i3 < i; i3++) {
            TrackSelectionOverride trackSelectionOverride = (TrackSelectionOverride) map.get(Integer.valueOf(mappedTrackInfo.rendererTrackTypes[i3]));
            if (trackSelectionOverride != null) {
                definitionArr[i3] = (trackSelectionOverride.trackIndices.isEmpty() || mappedTrackInfo.rendererTrackGroups[i3].indexOf(trackSelectionOverride.mediaTrackGroup) == -1) ? null : new ExoTrackSelection.Definition(trackSelectionOverride.mediaTrackGroup, Ints.toArray(trackSelectionOverride.trackIndices), 0);
            }
        }
    }

    public static void collectTrackSelectionOverrides(TrackGroupArray trackGroupArray, TrackSelectionParameters trackSelectionParameters, Map<Integer, TrackSelectionOverride> map) {
        TrackSelectionOverride trackSelectionOverride;
        for (int i = 0; i < trackGroupArray.length; i++) {
            TrackSelectionOverride trackSelectionOverride2 = trackSelectionParameters.overrides.get(trackGroupArray.get(i));
            if (trackSelectionOverride2 != null && ((trackSelectionOverride = map.get(Integer.valueOf(trackSelectionOverride2.mediaTrackGroup.type))) == null || (trackSelectionOverride.trackIndices.isEmpty() && !trackSelectionOverride2.trackIndices.isEmpty()))) {
                map.put(Integer.valueOf(trackSelectionOverride2.mediaTrackGroup.type), trackSelectionOverride2);
            }
        }
    }

    public static int getFormatLanguageScore(Format format, @Nullable String str, boolean z) {
        if (!TextUtils.isEmpty(str) && str.equals(format.language)) {
            return 4;
        }
        String strNormalizeUndeterminedLanguageToNull = normalizeUndeterminedLanguageToNull(str);
        String strNormalizeUndeterminedLanguageToNull2 = normalizeUndeterminedLanguageToNull(format.language);
        if (strNormalizeUndeterminedLanguageToNull2 == null || strNormalizeUndeterminedLanguageToNull == null) {
            return (z && strNormalizeUndeterminedLanguageToNull2 == null) ? 1 : 0;
        }
        if (strNormalizeUndeterminedLanguageToNull2.startsWith(strNormalizeUndeterminedLanguageToNull) || strNormalizeUndeterminedLanguageToNull.startsWith(strNormalizeUndeterminedLanguageToNull2)) {
            return 3;
        }
        return Util.splitAtFirst(strNormalizeUndeterminedLanguageToNull2, "-")[0].equals(strNormalizeUndeterminedLanguageToNull.split("-", 2)[0]) ? 2 : 0;
    }

    public static int getMaxVideoPixelsToRetainForViewport(TrackGroup trackGroup, int i, int i2, boolean z) {
        int i3;
        int i4 = Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE && i2 != Integer.MAX_VALUE) {
            for (int i5 = 0; i5 < trackGroup.length; i5++) {
                Format format = trackGroup.formats[i5];
                int i6 = format.width;
                if (i6 > 0 && (i3 = format.height) > 0) {
                    Point maxVideoSizeInViewport = getMaxVideoSizeInViewport(z, i, i2, i6, i3);
                    int i7 = format.width;
                    int i8 = format.height;
                    int i9 = i7 * i8;
                    if (i7 >= ((int) (maxVideoSizeInViewport.x * 0.98f)) && i8 >= ((int) (maxVideoSizeInViewport.y * 0.98f)) && i9 < i4) {
                        i4 = i9;
                    }
                }
            }
        }
        return i4;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Point getMaxVideoSizeInViewport(boolean r3, int r4, int r5, int r6, int r7) {
        /*
            if (r3 == 0) goto Lf
            r3 = 0
            r0 = 1
            if (r6 <= r7) goto L8
            r1 = 1
            goto L9
        L8:
            r1 = 0
        L9:
            if (r4 <= r5) goto Lc
            r3 = 1
        Lc:
            if (r1 == r3) goto Lf
            goto L12
        Lf:
            r2 = r5
            r5 = r4
            r4 = r2
        L12:
            int r3 = r6 * r4
            int r0 = r7 * r5
            if (r3 < r0) goto L22
            android.graphics.Point r3 = new android.graphics.Point
            int r4 = com.google.android.exoplayer2.util.Util.ceilDivide(r0, r6)
            r3.<init>(r5, r4)
            return r3
        L22:
            android.graphics.Point r5 = new android.graphics.Point
            int r3 = com.google.android.exoplayer2.util.Util.ceilDivide(r3, r7)
            r5.<init>(r3, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.trackselection.DefaultTrackSelector.getMaxVideoSizeInViewport(boolean, int, int, int, int):android.graphics.Point");
    }

    public static int getRoleFlagMatchScore(int i, int i2) {
        if (i == 0 || i != i2) {
            return Integer.bitCount(i & i2);
        }
        return Integer.MAX_VALUE;
    }

    public static int getVideoCodecPreferenceScore(@Nullable String str) {
        if (str == null) {
            return 0;
        }
        switch (str) {
        }
        return 0;
    }

    public static boolean isDolbyAudio(Format format) {
        String str = format.sampleMimeType;
        if (str == null) {
            return false;
        }
        str.getClass();
        switch (str) {
        }
        return false;
    }

    public static boolean isSupported(int i, boolean z) {
        int i2 = i & 7;
        if (i2 != 4) {
            return z && i2 == 3;
        }
        return true;
    }

    public static /* synthetic */ int lambda$static$1(Integer num, Integer num2) {
        return 0;
    }

    public static void maybeConfigureRenderersForTunneling(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, RendererConfiguration[] rendererConfigurationArr, ExoTrackSelection[] exoTrackSelectionArr) {
        boolean z;
        boolean z2 = false;
        int i = -1;
        int i2 = -1;
        for (int i3 = 0; i3 < mappedTrackInfo.rendererCount; i3++) {
            int i4 = mappedTrackInfo.rendererTrackTypes[i3];
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i3];
            if ((i4 == 1 || i4 == 2) && exoTrackSelection != null && rendererSupportsTunneling(iArr[i3], mappedTrackInfo.rendererTrackGroups[i3], exoTrackSelection)) {
                if (i4 == 1) {
                    if (i2 != -1) {
                        z = false;
                        break;
                    }
                    i2 = i3;
                } else {
                    if (i != -1) {
                        z = false;
                        break;
                    }
                    i = i3;
                }
            }
        }
        z = true;
        if (i2 != -1 && i != -1) {
            z2 = true;
        }
        if (z && z2) {
            RendererConfiguration rendererConfiguration = new RendererConfiguration(true);
            rendererConfigurationArr[i2] = rendererConfiguration;
            rendererConfigurationArr[i] = rendererConfiguration;
        }
    }

    @Nullable
    public static String normalizeUndeterminedLanguageToNull(@Nullable String str) {
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, "und")) {
            return null;
        }
        return str;
    }

    public static boolean rendererSupportsTunneling(int[][] iArr, TrackGroupArray trackGroupArray, ExoTrackSelection exoTrackSelection) {
        if (exoTrackSelection != null) {
            int iIndexOf = trackGroupArray.indexOf(exoTrackSelection.getTrackGroup());
            for (int i = 0; i < exoTrackSelection.length(); i++) {
                if ((iArr[iIndexOf][exoTrackSelection.getIndexInTrackGroup(i)] & 32) == 32) {
                }
            }
            return true;
        }
        return false;
    }

    public Parameters.Builder buildUponParameters() {
        Parameters parameters = getParameters();
        parameters.getClass();
        return new Parameters.Builder(parameters);
    }

    public final boolean isAudioFormatWithinAudioChannelCountConstraints(Format format) {
        boolean z;
        SpatializerWrapperV32 spatializerWrapperV32;
        SpatializerWrapperV32 spatializerWrapperV322;
        synchronized (this.lock) {
            try {
                if (this.parameters.constrainAudioChannelCountToDeviceCapabilities && !this.deviceIsTV && format.channelCount > 2 && (!isDolbyAudio(format) || (Util.SDK_INT >= 32 && (spatializerWrapperV322 = this.spatializer) != null && spatializerWrapperV322.isSpatializationSupported()))) {
                    z = Util.SDK_INT >= 32 && (spatializerWrapperV32 = this.spatializer) != null && spatializerWrapperV32.isSpatializationSupported() && this.spatializer.isAvailable() && this.spatializer.isEnabled() && this.spatializer.canBeSpatialized(this.audioAttributes, format);
                }
            } finally {
            }
        }
        return z;
    }

    public final void maybeInvalidateForAudioChannelCountConstraints() {
        boolean z;
        SpatializerWrapperV32 spatializerWrapperV32;
        synchronized (this.lock) {
            try {
                z = this.parameters.constrainAudioChannelCountToDeviceCapabilities && !this.deviceIsTV && Util.SDK_INT >= 32 && (spatializerWrapperV32 = this.spatializer) != null && spatializerWrapperV32.isSpatializationSupported();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z) {
            invalidate();
        }
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelector
    public void release() {
        SpatializerWrapperV32 spatializerWrapperV32;
        synchronized (this.lock) {
            try {
                if (Util.SDK_INT >= 32 && (spatializerWrapperV32 = this.spatializer) != null) {
                    spatializerWrapperV32.release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        super.release();
    }

    public ExoTrackSelection.Definition[] selectAllTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, Parameters parameters) throws ExoPlaybackException {
        String str;
        int i = mappedTrackInfo.rendererCount;
        ExoTrackSelection.Definition[] definitionArr = new ExoTrackSelection.Definition[i];
        Pair<ExoTrackSelection.Definition, Integer> pairSelectVideoTrack = selectVideoTrack(mappedTrackInfo, iArr, iArr2, parameters);
        if (pairSelectVideoTrack != null) {
            definitionArr[((Integer) pairSelectVideoTrack.second).intValue()] = (ExoTrackSelection.Definition) pairSelectVideoTrack.first;
        }
        Pair<ExoTrackSelection.Definition, Integer> pairSelectAudioTrack = selectAudioTrack(mappedTrackInfo, iArr, iArr2, parameters);
        if (pairSelectAudioTrack != null) {
            definitionArr[((Integer) pairSelectAudioTrack.second).intValue()] = (ExoTrackSelection.Definition) pairSelectAudioTrack.first;
        }
        if (pairSelectAudioTrack == null) {
            str = null;
        } else {
            ExoTrackSelection.Definition definition = (ExoTrackSelection.Definition) pairSelectAudioTrack.first;
            TrackGroup trackGroup = definition.group;
            str = trackGroup.formats[definition.tracks[0]].language;
        }
        Pair<ExoTrackSelection.Definition, Integer> pairSelectTextTrack = selectTextTrack(mappedTrackInfo, iArr, parameters, str);
        if (pairSelectTextTrack != null) {
            definitionArr[((Integer) pairSelectTextTrack.second).intValue()] = (ExoTrackSelection.Definition) pairSelectTextTrack.first;
        }
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = mappedTrackInfo.rendererTrackTypes[i2];
            if (i3 != 2 && i3 != 1 && i3 != 3) {
                definitionArr[i2] = selectOtherTrack(i3, mappedTrackInfo.rendererTrackGroups[i2], iArr[i2], parameters);
            }
        }
        return definitionArr;
    }

    @Nullable
    public Pair<ExoTrackSelection.Definition, Integer> selectAudioTrack(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, final Parameters parameters) throws ExoPlaybackException {
        final boolean z = false;
        int i = 0;
        while (true) {
            if (i < mappedTrackInfo.rendererCount) {
                if (2 == mappedTrackInfo.rendererTrackTypes[i] && mappedTrackInfo.rendererTrackGroups[i].length > 0) {
                    z = true;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        return selectTracksForType(1, mappedTrackInfo, iArr, new TrackInfo.Factory() { // from class: com.google.android.exoplayer2.trackselection.DefaultTrackSelector$$ExternalSyntheticLambda5
            @Override // com.google.android.exoplayer2.trackselection.DefaultTrackSelector.TrackInfo.Factory
            public final List create(int i2, TrackGroup trackGroup, int[] iArr3) {
                return DefaultTrackSelector.$r8$lambda$sih5Pv2vBFLqgJovgGnPmMZfQd4(this.f$0, parameters, z, i2, trackGroup, iArr3);
            }
        }, new DefaultTrackSelector$$ExternalSyntheticLambda6());
    }

    @Nullable
    public ExoTrackSelection.Definition selectOtherTrack(int i, TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters) throws ExoPlaybackException {
        TrackGroup trackGroup = null;
        OtherTrackScore otherTrackScore = null;
        int i2 = 0;
        for (int i3 = 0; i3 < trackGroupArray.length; i3++) {
            TrackGroup trackGroup2 = trackGroupArray.get(i3);
            int[] iArr2 = iArr[i3];
            for (int i4 = 0; i4 < trackGroup2.length; i4++) {
                if (isSupported(iArr2[i4], parameters.exceedRendererCapabilitiesIfNecessary)) {
                    OtherTrackScore otherTrackScore2 = new OtherTrackScore(trackGroup2.formats[i4], iArr2[i4]);
                    if (otherTrackScore == null || otherTrackScore2.compareTo(otherTrackScore) > 0) {
                        trackGroup = trackGroup2;
                        i2 = i4;
                        otherTrackScore = otherTrackScore2;
                    }
                }
            }
        }
        if (trackGroup == null) {
            return null;
        }
        return new ExoTrackSelection.Definition(trackGroup, new int[]{i2}, 0);
    }

    @Nullable
    public Pair<ExoTrackSelection.Definition, Integer> selectTextTrack(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, final Parameters parameters, @Nullable final String str) throws ExoPlaybackException {
        return selectTracksForType(3, mappedTrackInfo, iArr, new TrackInfo.Factory() { // from class: com.google.android.exoplayer2.trackselection.DefaultTrackSelector$$ExternalSyntheticLambda7
            @Override // com.google.android.exoplayer2.trackselection.DefaultTrackSelector.TrackInfo.Factory
            public final List create(int i, TrackGroup trackGroup, int[] iArr2) {
                return DefaultTrackSelector.TextTrackInfo.createForTrackGroup(i, trackGroup, parameters, iArr2, str);
            }
        }, new DefaultTrackSelector$$ExternalSyntheticLambda8());
    }

    @Override // com.google.android.exoplayer2.trackselection.MappingTrackSelector
    public final Pair<RendererConfiguration[], ExoTrackSelection[]> selectTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) throws ExoPlaybackException {
        Parameters parameters;
        SpatializerWrapperV32 spatializerWrapperV32;
        synchronized (this.lock) {
            try {
                parameters = this.parameters;
                if (parameters.constrainAudioChannelCountToDeviceCapabilities && Util.SDK_INT >= 32 && (spatializerWrapperV32 = this.spatializer) != null) {
                    Looper looperMyLooper = Looper.myLooper();
                    Assertions.checkStateNotNull(looperMyLooper);
                    spatializerWrapperV32.ensureInitialized(this, looperMyLooper);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        int i = mappedTrackInfo.rendererCount;
        ExoTrackSelection.Definition[] definitionArrSelectAllTracks = selectAllTracks(mappedTrackInfo, iArr, iArr2, parameters);
        applyTrackSelectionOverrides(mappedTrackInfo, parameters, definitionArrSelectAllTracks);
        applyLegacyRendererOverrides(mappedTrackInfo, parameters, definitionArrSelectAllTracks);
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = mappedTrackInfo.rendererTrackTypes[i2];
            if (parameters.rendererDisabledFlags.get(i2) || parameters.disabledTrackTypes.contains(Integer.valueOf(i3))) {
                definitionArrSelectAllTracks[i2] = null;
            }
        }
        ExoTrackSelection[] exoTrackSelectionArrCreateTrackSelections = this.trackSelectionFactory.createTrackSelections(definitionArrSelectAllTracks, getBandwidthMeter(), mediaPeriodId, timeline);
        RendererConfiguration[] rendererConfigurationArr = new RendererConfiguration[i];
        for (int i4 = 0; i4 < i; i4++) {
            rendererConfigurationArr[i4] = (parameters.rendererDisabledFlags.get(i4) || parameters.disabledTrackTypes.contains(Integer.valueOf(mappedTrackInfo.rendererTrackTypes[i4])) || (mappedTrackInfo.rendererTrackTypes[i4] != -2 && exoTrackSelectionArrCreateTrackSelections[i4] == null)) ? null : RendererConfiguration.DEFAULT;
        }
        if (parameters.tunnelingEnabled) {
            maybeConfigureRenderersForTunneling(mappedTrackInfo, iArr, rendererConfigurationArr, exoTrackSelectionArrCreateTrackSelections);
        }
        return Pair.create(rendererConfigurationArr, exoTrackSelectionArrCreateTrackSelections);
    }

    @Nullable
    public final <T extends TrackInfo<T>> Pair<ExoTrackSelection.Definition, Integer> selectTracksForType(int i, MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, TrackInfo.Factory<T> factory, Comparator<List<T>> comparator) {
        int i2;
        RandomAccess randomAccessOf;
        MappingTrackSelector.MappedTrackInfo mappedTrackInfo2 = mappedTrackInfo;
        ArrayList arrayList = new ArrayList();
        int i3 = mappedTrackInfo2.rendererCount;
        int i4 = 0;
        while (i4 < i3) {
            if (i == mappedTrackInfo2.rendererTrackTypes[i4]) {
                TrackGroupArray trackGroupArray = mappedTrackInfo2.rendererTrackGroups[i4];
                for (int i5 = 0; i5 < trackGroupArray.length; i5++) {
                    TrackGroup trackGroup = trackGroupArray.get(i5);
                    List<T> listCreate = factory.create(i4, trackGroup, iArr[i4][i5]);
                    boolean[] zArr = new boolean[trackGroup.length];
                    int i6 = 0;
                    while (i6 < trackGroup.length) {
                        T t = listCreate.get(i6);
                        int selectionEligibility = t.getSelectionEligibility();
                        if (zArr[i6] || selectionEligibility == 0) {
                            i2 = i3;
                        } else {
                            if (selectionEligibility == 1) {
                                randomAccessOf = ImmutableList.of(t);
                            } else {
                                ArrayList arrayList2 = new ArrayList();
                                arrayList2.add(t);
                                int i7 = i6 + 1;
                                while (i7 < trackGroup.length) {
                                    T t2 = listCreate.get(i7);
                                    int i8 = i3;
                                    if (t2.getSelectionEligibility() == 2 && t.isCompatibleForAdaptationWith(t2)) {
                                        arrayList2.add(t2);
                                        zArr[i7] = true;
                                    }
                                    i7++;
                                    i3 = i8;
                                }
                                randomAccessOf = arrayList2;
                            }
                            i2 = i3;
                            arrayList.add(randomAccessOf);
                        }
                        i6++;
                        i3 = i2;
                    }
                }
            }
            i4++;
            mappedTrackInfo2 = mappedTrackInfo;
            i3 = i3;
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        List list = (List) Collections.max(arrayList, comparator);
        int[] iArr2 = new int[list.size()];
        for (int i9 = 0; i9 < list.size(); i9++) {
            iArr2[i9] = ((TrackInfo) list.get(i9)).trackIndex;
        }
        TrackInfo trackInfo = (TrackInfo) list.get(0);
        return Pair.create(new ExoTrackSelection.Definition(trackInfo.trackGroup, iArr2, 0), Integer.valueOf(trackInfo.rendererIndex));
    }

    @Nullable
    public Pair<ExoTrackSelection.Definition, Integer> selectVideoTrack(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, final int[] iArr2, final Parameters parameters) throws ExoPlaybackException {
        return selectTracksForType(2, mappedTrackInfo, iArr, new TrackInfo.Factory() { // from class: com.google.android.exoplayer2.trackselection.DefaultTrackSelector$$ExternalSyntheticLambda2
            @Override // com.google.android.exoplayer2.trackselection.DefaultTrackSelector.TrackInfo.Factory
            public final List create(int i, TrackGroup trackGroup, int[] iArr3) {
                return DefaultTrackSelector.VideoTrackInfo.createForTrackGroup(i, trackGroup, parameters, iArr3, iArr2[i]);
            }
        }, new DefaultTrackSelector$$ExternalSyntheticLambda3());
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelector
    public void setAudioAttributes(AudioAttributes audioAttributes) {
        boolean zEquals;
        synchronized (this.lock) {
            zEquals = this.audioAttributes.equals(audioAttributes);
            this.audioAttributes = audioAttributes;
        }
        if (zEquals) {
            return;
        }
        maybeInvalidateForAudioChannelCountConstraints();
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelector
    public void setParameters(TrackSelectionParameters trackSelectionParameters) {
        if (trackSelectionParameters instanceof Parameters) {
            setParametersInternal((Parameters) trackSelectionParameters);
        }
        Parameters.Builder builder = new Parameters.Builder(getParameters());
        builder.init(trackSelectionParameters);
        setParametersInternal(new Parameters(builder));
    }

    public final void setParametersInternal(Parameters parameters) {
        boolean zEquals;
        parameters.getClass();
        synchronized (this.lock) {
            zEquals = this.parameters.equals(parameters);
            this.parameters = parameters;
        }
        if (zEquals) {
            return;
        }
        if (parameters.constrainAudioChannelCountToDeviceCapabilities && this.context == null) {
            Log.w("DefaultTrackSelector", "Audio channel count constraints cannot be applied without reference to Context. Build the track selector instance with one of the non-deprecated constructors that take a Context argument.");
        }
        invalidate();
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelector
    public Parameters getParameters() {
        Parameters parameters;
        synchronized (this.lock) {
            parameters = this.parameters;
        }
        return parameters;
    }

    @Deprecated
    public void setParameters(ParametersBuilder parametersBuilder) {
        setParametersInternal(parametersBuilder.build());
    }

    public void setParameters(Parameters.Builder builder) {
        builder.getClass();
        setParametersInternal(new Parameters(builder));
    }

    @Deprecated
    public DefaultTrackSelector() {
        this(Parameters.DEFAULT_WITHOUT_CONTEXT, new AdaptiveTrackSelection.Factory(), (Context) null);
    }

    public DefaultTrackSelector(Context context) {
        this(context, new AdaptiveTrackSelection.Factory());
    }

    public DefaultTrackSelector(Context context, ExoTrackSelection.Factory factory) {
        this(Parameters.getDefaults(context), factory, context);
    }

    public DefaultTrackSelector(Context context, TrackSelectionParameters trackSelectionParameters) {
        this(trackSelectionParameters, new AdaptiveTrackSelection.Factory(), context);
    }

    @Deprecated
    public DefaultTrackSelector(TrackSelectionParameters trackSelectionParameters, ExoTrackSelection.Factory factory) {
        this(trackSelectionParameters, factory, (Context) null);
    }

    public DefaultTrackSelector(Context context, TrackSelectionParameters trackSelectionParameters, ExoTrackSelection.Factory factory) {
        this(trackSelectionParameters, factory, context);
    }
}
