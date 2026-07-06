package com.google.android.exoplayer2.trackselection;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Looper;
import android.view.accessibility.CaptioningManager;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.util.BundleableUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableList;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class TrackSelectionParameters implements Bundleable {

    @Deprecated
    public static final Bundleable.Creator<TrackSelectionParameters> CREATOR;

    @Deprecated
    public static final TrackSelectionParameters DEFAULT;
    public static final TrackSelectionParameters DEFAULT_WITHOUT_CONTEXT;
    public static final int FIELD_CUSTOM_ID_BASE = 1000;
    public static final String FIELD_DISABLED_TRACK_TYPE;
    public static final String FIELD_FORCE_HIGHEST_SUPPORTED_BITRATE;
    public static final String FIELD_FORCE_LOWEST_BITRATE;
    public static final String FIELD_IGNORED_TEXT_SELECTION_FLAGS;
    public static final String FIELD_MAX_AUDIO_BITRATE;
    public static final String FIELD_MAX_AUDIO_CHANNEL_COUNT;
    public static final String FIELD_MAX_VIDEO_BITRATE;
    public static final String FIELD_MAX_VIDEO_FRAMERATE;
    public static final String FIELD_MAX_VIDEO_HEIGHT;
    public static final String FIELD_MAX_VIDEO_WIDTH;
    public static final String FIELD_MIN_VIDEO_BITRATE;
    public static final String FIELD_MIN_VIDEO_FRAMERATE;
    public static final String FIELD_MIN_VIDEO_HEIGHT;
    public static final String FIELD_MIN_VIDEO_WIDTH;
    public static final String FIELD_PREFERRED_AUDIO_LANGUAGES;
    public static final String FIELD_PREFERRED_AUDIO_MIME_TYPES;
    public static final String FIELD_PREFERRED_AUDIO_ROLE_FLAGS;
    public static final String FIELD_PREFERRED_TEXT_LANGUAGES;
    public static final String FIELD_PREFERRED_TEXT_ROLE_FLAGS;
    public static final String FIELD_PREFERRED_VIDEO_MIMETYPES;
    public static final String FIELD_PREFERRED_VIDEO_ROLE_FLAGS;
    public static final String FIELD_SELECTION_OVERRIDES;
    public static final String FIELD_SELECT_UNDETERMINED_TEXT_LANGUAGE;
    public static final String FIELD_VIEWPORT_HEIGHT;
    public static final String FIELD_VIEWPORT_ORIENTATION_MAY_CHANGE;
    public static final String FIELD_VIEWPORT_WIDTH;
    public final ImmutableSet<Integer> disabledTrackTypes;
    public final boolean forceHighestSupportedBitrate;
    public final boolean forceLowestBitrate;
    public final int ignoredTextSelectionFlags;
    public final int maxAudioBitrate;
    public final int maxAudioChannelCount;
    public final int maxVideoBitrate;
    public final int maxVideoFrameRate;
    public final int maxVideoHeight;
    public final int maxVideoWidth;
    public final int minVideoBitrate;
    public final int minVideoFrameRate;
    public final int minVideoHeight;
    public final int minVideoWidth;
    public final ImmutableMap<TrackGroup, TrackSelectionOverride> overrides;
    public final ImmutableList<String> preferredAudioLanguages;
    public final ImmutableList<String> preferredAudioMimeTypes;
    public final int preferredAudioRoleFlags;
    public final ImmutableList<String> preferredTextLanguages;
    public final int preferredTextRoleFlags;
    public final ImmutableList<String> preferredVideoMimeTypes;
    public final int preferredVideoRoleFlags;
    public final boolean selectUndeterminedTextLanguage;
    public final int viewportHeight;
    public final boolean viewportOrientationMayChange;
    public final int viewportWidth;

    static {
        TrackSelectionParameters trackSelectionParameters = new TrackSelectionParameters(new Builder());
        DEFAULT_WITHOUT_CONTEXT = trackSelectionParameters;
        DEFAULT = trackSelectionParameters;
        FIELD_PREFERRED_AUDIO_LANGUAGES = Util.intToStringMaxRadix(1);
        FIELD_PREFERRED_AUDIO_ROLE_FLAGS = Integer.toString(2, 36);
        FIELD_PREFERRED_TEXT_LANGUAGES = Integer.toString(3, 36);
        FIELD_PREFERRED_TEXT_ROLE_FLAGS = Integer.toString(4, 36);
        FIELD_SELECT_UNDETERMINED_TEXT_LANGUAGE = Integer.toString(5, 36);
        FIELD_MAX_VIDEO_WIDTH = Integer.toString(6, 36);
        FIELD_MAX_VIDEO_HEIGHT = Integer.toString(7, 36);
        FIELD_MAX_VIDEO_FRAMERATE = Integer.toString(8, 36);
        FIELD_MAX_VIDEO_BITRATE = Integer.toString(9, 36);
        FIELD_MIN_VIDEO_WIDTH = Integer.toString(10, 36);
        FIELD_MIN_VIDEO_HEIGHT = Integer.toString(11, 36);
        FIELD_MIN_VIDEO_FRAMERATE = Integer.toString(12, 36);
        FIELD_MIN_VIDEO_BITRATE = Integer.toString(13, 36);
        FIELD_VIEWPORT_WIDTH = Integer.toString(14, 36);
        FIELD_VIEWPORT_HEIGHT = Integer.toString(15, 36);
        FIELD_VIEWPORT_ORIENTATION_MAY_CHANGE = Integer.toString(16, 36);
        FIELD_PREFERRED_VIDEO_MIMETYPES = Integer.toString(17, 36);
        FIELD_MAX_AUDIO_CHANNEL_COUNT = Integer.toString(18, 36);
        FIELD_MAX_AUDIO_BITRATE = Integer.toString(19, 36);
        FIELD_PREFERRED_AUDIO_MIME_TYPES = Integer.toString(20, 36);
        FIELD_FORCE_LOWEST_BITRATE = Integer.toString(21, 36);
        FIELD_FORCE_HIGHEST_SUPPORTED_BITRATE = Integer.toString(22, 36);
        FIELD_SELECTION_OVERRIDES = Integer.toString(23, 36);
        FIELD_DISABLED_TRACK_TYPE = Integer.toString(24, 36);
        FIELD_PREFERRED_VIDEO_ROLE_FLAGS = Integer.toString(25, 36);
        FIELD_IGNORED_TEXT_SELECTION_FLAGS = Integer.toString(26, 36);
        CREATOR = new TrackSelectionParameters$$ExternalSyntheticLambda0();
    }

    public TrackSelectionParameters(Builder builder) {
        this.maxVideoWidth = builder.maxVideoWidth;
        this.maxVideoHeight = builder.maxVideoHeight;
        this.maxVideoFrameRate = builder.maxVideoFrameRate;
        this.maxVideoBitrate = builder.maxVideoBitrate;
        this.minVideoWidth = builder.minVideoWidth;
        this.minVideoHeight = builder.minVideoHeight;
        this.minVideoFrameRate = builder.minVideoFrameRate;
        this.minVideoBitrate = builder.minVideoBitrate;
        this.viewportWidth = builder.viewportWidth;
        this.viewportHeight = builder.viewportHeight;
        this.viewportOrientationMayChange = builder.viewportOrientationMayChange;
        this.preferredVideoMimeTypes = builder.preferredVideoMimeTypes;
        this.preferredVideoRoleFlags = builder.preferredVideoRoleFlags;
        this.preferredAudioLanguages = builder.preferredAudioLanguages;
        this.preferredAudioRoleFlags = builder.preferredAudioRoleFlags;
        this.maxAudioChannelCount = builder.maxAudioChannelCount;
        this.maxAudioBitrate = builder.maxAudioBitrate;
        this.preferredAudioMimeTypes = builder.preferredAudioMimeTypes;
        this.preferredTextLanguages = builder.preferredTextLanguages;
        this.preferredTextRoleFlags = builder.preferredTextRoleFlags;
        this.ignoredTextSelectionFlags = builder.ignoredTextSelectionFlags;
        this.selectUndeterminedTextLanguage = builder.selectUndeterminedTextLanguage;
        this.forceLowestBitrate = builder.forceLowestBitrate;
        this.forceHighestSupportedBitrate = builder.forceHighestSupportedBitrate;
        this.overrides = ImmutableMap.copyOf((Map) builder.overrides);
        this.disabledTrackTypes = ImmutableSet.copyOf((Collection) builder.disabledTrackTypes);
    }

    public static TrackSelectionParameters fromBundle(Bundle bundle) {
        return new TrackSelectionParameters(new Builder(bundle));
    }

    public static TrackSelectionParameters getDefaults(Context context) {
        return new TrackSelectionParameters(new Builder(context));
    }

    public Builder buildUpon() {
        Builder builder = new Builder();
        builder.init(this);
        return builder;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            TrackSelectionParameters trackSelectionParameters = (TrackSelectionParameters) obj;
            if (this.maxVideoWidth == trackSelectionParameters.maxVideoWidth && this.maxVideoHeight == trackSelectionParameters.maxVideoHeight && this.maxVideoFrameRate == trackSelectionParameters.maxVideoFrameRate && this.maxVideoBitrate == trackSelectionParameters.maxVideoBitrate && this.minVideoWidth == trackSelectionParameters.minVideoWidth && this.minVideoHeight == trackSelectionParameters.minVideoHeight && this.minVideoFrameRate == trackSelectionParameters.minVideoFrameRate && this.minVideoBitrate == trackSelectionParameters.minVideoBitrate && this.viewportOrientationMayChange == trackSelectionParameters.viewportOrientationMayChange && this.viewportWidth == trackSelectionParameters.viewportWidth && this.viewportHeight == trackSelectionParameters.viewportHeight && this.preferredVideoMimeTypes.equals(trackSelectionParameters.preferredVideoMimeTypes) && this.preferredVideoRoleFlags == trackSelectionParameters.preferredVideoRoleFlags && this.preferredAudioLanguages.equals(trackSelectionParameters.preferredAudioLanguages) && this.preferredAudioRoleFlags == trackSelectionParameters.preferredAudioRoleFlags && this.maxAudioChannelCount == trackSelectionParameters.maxAudioChannelCount && this.maxAudioBitrate == trackSelectionParameters.maxAudioBitrate && this.preferredAudioMimeTypes.equals(trackSelectionParameters.preferredAudioMimeTypes) && this.preferredTextLanguages.equals(trackSelectionParameters.preferredTextLanguages) && this.preferredTextRoleFlags == trackSelectionParameters.preferredTextRoleFlags && this.ignoredTextSelectionFlags == trackSelectionParameters.ignoredTextSelectionFlags && this.selectUndeterminedTextLanguage == trackSelectionParameters.selectUndeterminedTextLanguage && this.forceLowestBitrate == trackSelectionParameters.forceLowestBitrate && this.forceHighestSupportedBitrate == trackSelectionParameters.forceHighestSupportedBitrate && this.overrides.equals(trackSelectionParameters.overrides) && this.disabledTrackTypes.equals(trackSelectionParameters.disabledTrackTypes)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.disabledTrackTypes.hashCode() + ((this.overrides.hashCode() + ((((((((((((this.preferredTextLanguages.hashCode() + ((this.preferredAudioMimeTypes.hashCode() + ((((((((this.preferredAudioLanguages.hashCode() + ((((this.preferredVideoMimeTypes.hashCode() + ((((((((((((((((((((((this.maxVideoWidth + 31) * 31) + this.maxVideoHeight) * 31) + this.maxVideoFrameRate) * 31) + this.maxVideoBitrate) * 31) + this.minVideoWidth) * 31) + this.minVideoHeight) * 31) + this.minVideoFrameRate) * 31) + this.minVideoBitrate) * 31) + (this.viewportOrientationMayChange ? 1 : 0)) * 31) + this.viewportWidth) * 31) + this.viewportHeight) * 31)) * 31) + this.preferredVideoRoleFlags) * 31)) * 31) + this.preferredAudioRoleFlags) * 31) + this.maxAudioChannelCount) * 31) + this.maxAudioBitrate) * 31)) * 31)) * 31) + this.preferredTextRoleFlags) * 31) + this.ignoredTextSelectionFlags) * 31) + (this.selectUndeterminedTextLanguage ? 1 : 0)) * 31) + (this.forceLowestBitrate ? 1 : 0)) * 31) + (this.forceHighestSupportedBitrate ? 1 : 0)) * 31)) * 31);
    }

    @Override // com.google.android.exoplayer2.Bundleable
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_MAX_VIDEO_WIDTH, this.maxVideoWidth);
        bundle.putInt(FIELD_MAX_VIDEO_HEIGHT, this.maxVideoHeight);
        bundle.putInt(FIELD_MAX_VIDEO_FRAMERATE, this.maxVideoFrameRate);
        bundle.putInt(FIELD_MAX_VIDEO_BITRATE, this.maxVideoBitrate);
        bundle.putInt(FIELD_MIN_VIDEO_WIDTH, this.minVideoWidth);
        bundle.putInt(FIELD_MIN_VIDEO_HEIGHT, this.minVideoHeight);
        bundle.putInt(FIELD_MIN_VIDEO_FRAMERATE, this.minVideoFrameRate);
        bundle.putInt(FIELD_MIN_VIDEO_BITRATE, this.minVideoBitrate);
        bundle.putInt(FIELD_VIEWPORT_WIDTH, this.viewportWidth);
        bundle.putInt(FIELD_VIEWPORT_HEIGHT, this.viewportHeight);
        bundle.putBoolean(FIELD_VIEWPORT_ORIENTATION_MAY_CHANGE, this.viewportOrientationMayChange);
        bundle.putStringArray(FIELD_PREFERRED_VIDEO_MIMETYPES, (String[]) this.preferredVideoMimeTypes.toArray(new String[0]));
        bundle.putInt(FIELD_PREFERRED_VIDEO_ROLE_FLAGS, this.preferredVideoRoleFlags);
        bundle.putStringArray(FIELD_PREFERRED_AUDIO_LANGUAGES, (String[]) this.preferredAudioLanguages.toArray(new String[0]));
        bundle.putInt(FIELD_PREFERRED_AUDIO_ROLE_FLAGS, this.preferredAudioRoleFlags);
        bundle.putInt(FIELD_MAX_AUDIO_CHANNEL_COUNT, this.maxAudioChannelCount);
        bundle.putInt(FIELD_MAX_AUDIO_BITRATE, this.maxAudioBitrate);
        bundle.putStringArray(FIELD_PREFERRED_AUDIO_MIME_TYPES, (String[]) this.preferredAudioMimeTypes.toArray(new String[0]));
        bundle.putStringArray(FIELD_PREFERRED_TEXT_LANGUAGES, (String[]) this.preferredTextLanguages.toArray(new String[0]));
        bundle.putInt(FIELD_PREFERRED_TEXT_ROLE_FLAGS, this.preferredTextRoleFlags);
        bundle.putInt(FIELD_IGNORED_TEXT_SELECTION_FLAGS, this.ignoredTextSelectionFlags);
        bundle.putBoolean(FIELD_SELECT_UNDETERMINED_TEXT_LANGUAGE, this.selectUndeterminedTextLanguage);
        bundle.putBoolean(FIELD_FORCE_LOWEST_BITRATE, this.forceLowestBitrate);
        bundle.putBoolean(FIELD_FORCE_HIGHEST_SUPPORTED_BITRATE, this.forceHighestSupportedBitrate);
        bundle.putParcelableArrayList(FIELD_SELECTION_OVERRIDES, BundleableUtil.toBundleArrayList(this.overrides.values()));
        bundle.putIntArray(FIELD_DISABLED_TRACK_TYPE, Ints.toArray(this.disabledTrackTypes));
        return bundle;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Builder {
        public HashSet<Integer> disabledTrackTypes;
        public boolean forceHighestSupportedBitrate;
        public boolean forceLowestBitrate;
        public int ignoredTextSelectionFlags;
        public int maxAudioBitrate;
        public int maxAudioChannelCount;
        public int maxVideoBitrate;
        public int maxVideoFrameRate;
        public int maxVideoHeight;
        public int maxVideoWidth;
        public int minVideoBitrate;
        public int minVideoFrameRate;
        public int minVideoHeight;
        public int minVideoWidth;
        public HashMap<TrackGroup, TrackSelectionOverride> overrides;
        public ImmutableList<String> preferredAudioLanguages;
        public ImmutableList<String> preferredAudioMimeTypes;
        public int preferredAudioRoleFlags;
        public ImmutableList<String> preferredTextLanguages;
        public int preferredTextRoleFlags;
        public ImmutableList<String> preferredVideoMimeTypes;
        public int preferredVideoRoleFlags;
        public boolean selectUndeterminedTextLanguage;
        public int viewportHeight;
        public boolean viewportOrientationMayChange;
        public int viewportWidth;

        @Deprecated
        public Builder() {
            this.maxVideoWidth = Integer.MAX_VALUE;
            this.maxVideoHeight = Integer.MAX_VALUE;
            this.maxVideoFrameRate = Integer.MAX_VALUE;
            this.maxVideoBitrate = Integer.MAX_VALUE;
            this.viewportWidth = Integer.MAX_VALUE;
            this.viewportHeight = Integer.MAX_VALUE;
            this.viewportOrientationMayChange = true;
            this.preferredVideoMimeTypes = ImmutableList.of();
            this.preferredVideoRoleFlags = 0;
            ImmutableList immutableList = RegularImmutableList.EMPTY;
            this.preferredAudioLanguages = immutableList;
            this.preferredAudioRoleFlags = 0;
            this.maxAudioChannelCount = Integer.MAX_VALUE;
            this.maxAudioBitrate = Integer.MAX_VALUE;
            this.preferredAudioMimeTypes = immutableList;
            this.preferredTextLanguages = immutableList;
            this.preferredTextRoleFlags = 0;
            this.ignoredTextSelectionFlags = 0;
            this.selectUndeterminedTextLanguage = false;
            this.forceLowestBitrate = false;
            this.forceHighestSupportedBitrate = false;
            this.overrides = new HashMap<>();
            this.disabledTrackTypes = new HashSet<>();
        }

        public static ImmutableList<String> normalizeLanguageCodes(String[] strArr) {
            ImmutableList.Builder builder = ImmutableList.builder();
            strArr.getClass();
            for (String str : strArr) {
                str.getClass();
                builder.add(Util.normalizeLanguageCode(str));
            }
            return builder.build();
        }

        @CanIgnoreReturnValue
        public Builder addOverride(TrackSelectionOverride trackSelectionOverride) {
            this.overrides.put(trackSelectionOverride.mediaTrackGroup, trackSelectionOverride);
            return this;
        }

        public TrackSelectionParameters build() {
            return new TrackSelectionParameters(this);
        }

        @CanIgnoreReturnValue
        public Builder clearOverride(TrackGroup trackGroup) {
            this.overrides.remove(trackGroup);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder clearOverrides() {
            this.overrides.clear();
            return this;
        }

        @CanIgnoreReturnValue
        public Builder clearOverridesOfType(int i) {
            Iterator<TrackSelectionOverride> it = this.overrides.values().iterator();
            while (it.hasNext()) {
                if (it.next().mediaTrackGroup.type == i) {
                    it.remove();
                }
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder clearVideoSizeConstraints() {
            return setMaxVideoSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        @CanIgnoreReturnValue
        public Builder clearViewportSizeConstraints() {
            return setViewportSize(Integer.MAX_VALUE, Integer.MAX_VALUE, true);
        }

        @EnsuresNonNull({"preferredVideoMimeTypes", "preferredAudioLanguages", "preferredAudioMimeTypes", "preferredTextLanguages", "overrides", "disabledTrackTypes"})
        public final void init(TrackSelectionParameters trackSelectionParameters) {
            this.maxVideoWidth = trackSelectionParameters.maxVideoWidth;
            this.maxVideoHeight = trackSelectionParameters.maxVideoHeight;
            this.maxVideoFrameRate = trackSelectionParameters.maxVideoFrameRate;
            this.maxVideoBitrate = trackSelectionParameters.maxVideoBitrate;
            this.minVideoWidth = trackSelectionParameters.minVideoWidth;
            this.minVideoHeight = trackSelectionParameters.minVideoHeight;
            this.minVideoFrameRate = trackSelectionParameters.minVideoFrameRate;
            this.minVideoBitrate = trackSelectionParameters.minVideoBitrate;
            this.viewportWidth = trackSelectionParameters.viewportWidth;
            this.viewportHeight = trackSelectionParameters.viewportHeight;
            this.viewportOrientationMayChange = trackSelectionParameters.viewportOrientationMayChange;
            this.preferredVideoMimeTypes = trackSelectionParameters.preferredVideoMimeTypes;
            this.preferredVideoRoleFlags = trackSelectionParameters.preferredVideoRoleFlags;
            this.preferredAudioLanguages = trackSelectionParameters.preferredAudioLanguages;
            this.preferredAudioRoleFlags = trackSelectionParameters.preferredAudioRoleFlags;
            this.maxAudioChannelCount = trackSelectionParameters.maxAudioChannelCount;
            this.maxAudioBitrate = trackSelectionParameters.maxAudioBitrate;
            this.preferredAudioMimeTypes = trackSelectionParameters.preferredAudioMimeTypes;
            this.preferredTextLanguages = trackSelectionParameters.preferredTextLanguages;
            this.preferredTextRoleFlags = trackSelectionParameters.preferredTextRoleFlags;
            this.ignoredTextSelectionFlags = trackSelectionParameters.ignoredTextSelectionFlags;
            this.selectUndeterminedTextLanguage = trackSelectionParameters.selectUndeterminedTextLanguage;
            this.forceLowestBitrate = trackSelectionParameters.forceLowestBitrate;
            this.forceHighestSupportedBitrate = trackSelectionParameters.forceHighestSupportedBitrate;
            this.disabledTrackTypes = new HashSet<>(trackSelectionParameters.disabledTrackTypes);
            this.overrides = new HashMap<>(trackSelectionParameters.overrides);
        }

        @CanIgnoreReturnValue
        public Builder set(TrackSelectionParameters trackSelectionParameters) {
            init(trackSelectionParameters);
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder setDisabledTrackTypes(Set<Integer> set) {
            this.disabledTrackTypes.clear();
            this.disabledTrackTypes.addAll(set);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setForceHighestSupportedBitrate(boolean z) {
            this.forceHighestSupportedBitrate = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setForceLowestBitrate(boolean z) {
            this.forceLowestBitrate = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setIgnoredTextSelectionFlags(int i) {
            this.ignoredTextSelectionFlags = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMaxAudioBitrate(int i) {
            this.maxAudioBitrate = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMaxAudioChannelCount(int i) {
            this.maxAudioChannelCount = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMaxVideoBitrate(int i) {
            this.maxVideoBitrate = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMaxVideoFrameRate(int i) {
            this.maxVideoFrameRate = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMaxVideoSize(int i, int i2) {
            this.maxVideoWidth = i;
            this.maxVideoHeight = i2;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMaxVideoSizeSd() {
            return setMaxVideoSize(1279, 719);
        }

        @CanIgnoreReturnValue
        public Builder setMinVideoBitrate(int i) {
            this.minVideoBitrate = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMinVideoFrameRate(int i) {
            this.minVideoFrameRate = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setMinVideoSize(int i, int i2) {
            this.minVideoWidth = i;
            this.minVideoHeight = i2;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setOverrideForType(TrackSelectionOverride trackSelectionOverride) {
            clearOverridesOfType(trackSelectionOverride.mediaTrackGroup.type);
            this.overrides.put(trackSelectionOverride.mediaTrackGroup, trackSelectionOverride);
            return this;
        }

        public Builder setPreferredAudioLanguage(@Nullable String str) {
            return str == null ? setPreferredAudioLanguages(new String[0]) : setPreferredAudioLanguages(str);
        }

        @CanIgnoreReturnValue
        public Builder setPreferredAudioLanguages(String... strArr) {
            this.preferredAudioLanguages = normalizeLanguageCodes(strArr);
            return this;
        }

        public Builder setPreferredAudioMimeType(@Nullable String str) {
            return str == null ? setPreferredAudioMimeTypes(new String[0]) : setPreferredAudioMimeTypes(str);
        }

        @CanIgnoreReturnValue
        public Builder setPreferredAudioMimeTypes(String... strArr) {
            this.preferredAudioMimeTypes = ImmutableList.copyOf(strArr);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPreferredAudioRoleFlags(int i) {
            this.preferredAudioRoleFlags = i;
            return this;
        }

        public Builder setPreferredTextLanguage(@Nullable String str) {
            return str == null ? setPreferredTextLanguages(new String[0]) : setPreferredTextLanguages(str);
        }

        @CanIgnoreReturnValue
        public Builder setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(Context context) {
            if (Util.SDK_INT >= 19) {
                setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettingsV19(context);
            }
            return this;
        }

        @RequiresApi(19)
        public final void setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettingsV19(Context context) {
            CaptioningManager captioningManager;
            if ((Util.SDK_INT >= 23 || Looper.myLooper() != null) && (captioningManager = (CaptioningManager) context.getSystemService("captioning")) != null && captioningManager.isEnabled()) {
                this.preferredTextRoleFlags = 1088;
                Locale locale = captioningManager.getLocale();
                if (locale != null) {
                    this.preferredTextLanguages = ImmutableList.of(Util.getLocaleLanguageTag(locale));
                }
            }
        }

        @CanIgnoreReturnValue
        public Builder setPreferredTextLanguages(String... strArr) {
            this.preferredTextLanguages = normalizeLanguageCodes(strArr);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPreferredTextRoleFlags(int i) {
            this.preferredTextRoleFlags = i;
            return this;
        }

        public Builder setPreferredVideoMimeType(@Nullable String str) {
            return str == null ? setPreferredVideoMimeTypes(new String[0]) : setPreferredVideoMimeTypes(str);
        }

        @CanIgnoreReturnValue
        public Builder setPreferredVideoMimeTypes(String... strArr) {
            this.preferredVideoMimeTypes = ImmutableList.copyOf(strArr);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPreferredVideoRoleFlags(int i) {
            this.preferredVideoRoleFlags = i;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setSelectUndeterminedTextLanguage(boolean z) {
            this.selectUndeterminedTextLanguage = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setTrackTypeDisabled(int i, boolean z) {
            if (z) {
                this.disabledTrackTypes.add(Integer.valueOf(i));
                return this;
            }
            this.disabledTrackTypes.remove(Integer.valueOf(i));
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setViewportSize(int i, int i2, boolean z) {
            this.viewportWidth = i;
            this.viewportHeight = i2;
            this.viewportOrientationMayChange = z;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setViewportSizeToPhysicalDisplaySize(Context context, boolean z) {
            Point currentDisplayModeSize = Util.getCurrentDisplayModeSize(context);
            return setViewportSize(currentDisplayModeSize.x, currentDisplayModeSize.y, z);
        }

        public Builder(Context context) {
            this();
            setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(context);
            setViewportSizeToPhysicalDisplaySize(context, true);
        }

        public Builder(TrackSelectionParameters trackSelectionParameters) {
            init(trackSelectionParameters);
        }

        public Builder(Bundle bundle) {
            ImmutableList<Object> immutableListFromBundleList;
            String str = TrackSelectionParameters.FIELD_MAX_VIDEO_WIDTH;
            TrackSelectionParameters trackSelectionParameters = TrackSelectionParameters.DEFAULT_WITHOUT_CONTEXT;
            this.maxVideoWidth = bundle.getInt(str, trackSelectionParameters.maxVideoWidth);
            this.maxVideoHeight = bundle.getInt(TrackSelectionParameters.FIELD_MAX_VIDEO_HEIGHT, trackSelectionParameters.maxVideoHeight);
            this.maxVideoFrameRate = bundle.getInt(TrackSelectionParameters.FIELD_MAX_VIDEO_FRAMERATE, trackSelectionParameters.maxVideoFrameRate);
            this.maxVideoBitrate = bundle.getInt(TrackSelectionParameters.FIELD_MAX_VIDEO_BITRATE, trackSelectionParameters.maxVideoBitrate);
            this.minVideoWidth = bundle.getInt(TrackSelectionParameters.FIELD_MIN_VIDEO_WIDTH, trackSelectionParameters.minVideoWidth);
            this.minVideoHeight = bundle.getInt(TrackSelectionParameters.FIELD_MIN_VIDEO_HEIGHT, trackSelectionParameters.minVideoHeight);
            this.minVideoFrameRate = bundle.getInt(TrackSelectionParameters.FIELD_MIN_VIDEO_FRAMERATE, trackSelectionParameters.minVideoFrameRate);
            this.minVideoBitrate = bundle.getInt(TrackSelectionParameters.FIELD_MIN_VIDEO_BITRATE, trackSelectionParameters.minVideoBitrate);
            this.viewportWidth = bundle.getInt(TrackSelectionParameters.FIELD_VIEWPORT_WIDTH, trackSelectionParameters.viewportWidth);
            this.viewportHeight = bundle.getInt(TrackSelectionParameters.FIELD_VIEWPORT_HEIGHT, trackSelectionParameters.viewportHeight);
            this.viewportOrientationMayChange = bundle.getBoolean(TrackSelectionParameters.FIELD_VIEWPORT_ORIENTATION_MAY_CHANGE, trackSelectionParameters.viewportOrientationMayChange);
            this.preferredVideoMimeTypes = ImmutableList.copyOf((String[]) MoreObjects.firstNonNull(bundle.getStringArray(TrackSelectionParameters.FIELD_PREFERRED_VIDEO_MIMETYPES), new String[0]));
            this.preferredVideoRoleFlags = bundle.getInt(TrackSelectionParameters.FIELD_PREFERRED_VIDEO_ROLE_FLAGS, trackSelectionParameters.preferredVideoRoleFlags);
            this.preferredAudioLanguages = normalizeLanguageCodes((String[]) MoreObjects.firstNonNull(bundle.getStringArray(TrackSelectionParameters.FIELD_PREFERRED_AUDIO_LANGUAGES), new String[0]));
            this.preferredAudioRoleFlags = bundle.getInt(TrackSelectionParameters.FIELD_PREFERRED_AUDIO_ROLE_FLAGS, trackSelectionParameters.preferredAudioRoleFlags);
            this.maxAudioChannelCount = bundle.getInt(TrackSelectionParameters.FIELD_MAX_AUDIO_CHANNEL_COUNT, trackSelectionParameters.maxAudioChannelCount);
            this.maxAudioBitrate = bundle.getInt(TrackSelectionParameters.FIELD_MAX_AUDIO_BITRATE, trackSelectionParameters.maxAudioBitrate);
            this.preferredAudioMimeTypes = ImmutableList.copyOf((String[]) MoreObjects.firstNonNull(bundle.getStringArray(TrackSelectionParameters.FIELD_PREFERRED_AUDIO_MIME_TYPES), new String[0]));
            this.preferredTextLanguages = normalizeLanguageCodes((String[]) MoreObjects.firstNonNull(bundle.getStringArray(TrackSelectionParameters.FIELD_PREFERRED_TEXT_LANGUAGES), new String[0]));
            this.preferredTextRoleFlags = bundle.getInt(TrackSelectionParameters.FIELD_PREFERRED_TEXT_ROLE_FLAGS, trackSelectionParameters.preferredTextRoleFlags);
            this.ignoredTextSelectionFlags = bundle.getInt(TrackSelectionParameters.FIELD_IGNORED_TEXT_SELECTION_FLAGS, trackSelectionParameters.ignoredTextSelectionFlags);
            this.selectUndeterminedTextLanguage = bundle.getBoolean(TrackSelectionParameters.FIELD_SELECT_UNDETERMINED_TEXT_LANGUAGE, trackSelectionParameters.selectUndeterminedTextLanguage);
            this.forceLowestBitrate = bundle.getBoolean(TrackSelectionParameters.FIELD_FORCE_LOWEST_BITRATE, trackSelectionParameters.forceLowestBitrate);
            this.forceHighestSupportedBitrate = bundle.getBoolean(TrackSelectionParameters.FIELD_FORCE_HIGHEST_SUPPORTED_BITRATE, trackSelectionParameters.forceHighestSupportedBitrate);
            ArrayList parcelableArrayList = bundle.getParcelableArrayList(TrackSelectionParameters.FIELD_SELECTION_OVERRIDES);
            if (parcelableArrayList == null) {
                immutableListFromBundleList = RegularImmutableList.EMPTY;
            } else {
                immutableListFromBundleList = BundleableUtil.fromBundleList(TrackSelectionOverride.CREATOR, parcelableArrayList);
            }
            this.overrides = new HashMap<>();
            for (int i = 0; i < immutableListFromBundleList.size(); i++) {
                TrackSelectionOverride trackSelectionOverride = (TrackSelectionOverride) immutableListFromBundleList.get(i);
                this.overrides.put(trackSelectionOverride.mediaTrackGroup, trackSelectionOverride);
            }
            int[] iArr = (int[]) MoreObjects.firstNonNull(bundle.getIntArray(TrackSelectionParameters.FIELD_DISABLED_TRACK_TYPE), new int[0]);
            this.disabledTrackTypes = new HashSet<>();
            for (int i2 : iArr) {
                this.disabledTrackTypes.add(Integer.valueOf(i2));
            }
        }
    }
}
