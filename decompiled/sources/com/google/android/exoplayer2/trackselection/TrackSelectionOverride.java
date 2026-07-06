package com.google.android.exoplayer2.trackselection;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class TrackSelectionOverride implements Bundleable {
    public final TrackGroup mediaTrackGroup;
    public final ImmutableList<Integer> trackIndices;
    public static final String FIELD_TRACK_GROUP = Util.intToStringMaxRadix(0);
    public static final String FIELD_TRACKS = Integer.toString(1, 36);
    public static final Bundleable.Creator<TrackSelectionOverride> CREATOR = new TrackSelectionOverride$$ExternalSyntheticLambda0();

    public static TrackSelectionOverride $r8$lambda$BS012SxU3aJ7SgDeDI8bFkc99rg(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(FIELD_TRACK_GROUP);
        bundle2.getClass();
        TrackGroup trackGroup = (TrackGroup) TrackGroup.CREATOR.fromBundle(bundle2);
        int[] intArray = bundle.getIntArray(FIELD_TRACKS);
        intArray.getClass();
        return new TrackSelectionOverride(trackGroup, Ints.asList(intArray));
    }

    public TrackSelectionOverride(TrackGroup trackGroup, int i) {
        this(trackGroup, ImmutableList.of(Integer.valueOf(i)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && TrackSelectionOverride.class == obj.getClass()) {
            TrackSelectionOverride trackSelectionOverride = (TrackSelectionOverride) obj;
            if (this.mediaTrackGroup.equals(trackSelectionOverride.mediaTrackGroup) && this.trackIndices.equals(trackSelectionOverride.trackIndices)) {
                return true;
            }
        }
        return false;
    }

    public int getType() {
        return this.mediaTrackGroup.type;
    }

    public int hashCode() {
        return (this.trackIndices.hashCode() * 31) + this.mediaTrackGroup.hashCode();
    }

    @Override // com.google.android.exoplayer2.Bundleable
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putBundle(FIELD_TRACK_GROUP, this.mediaTrackGroup.toBundle());
        bundle.putIntArray(FIELD_TRACKS, Ints.toArray(this.trackIndices));
        return bundle;
    }

    public TrackSelectionOverride(TrackGroup trackGroup, List<Integer> list) {
        if (!list.isEmpty() && (((Integer) Collections.min(list)).intValue() < 0 || ((Integer) Collections.max(list)).intValue() >= trackGroup.length)) {
            throw new IndexOutOfBoundsException();
        }
        this.mediaTrackGroup = trackGroup;
        this.trackIndices = ImmutableList.copyOf((Collection) list);
    }
}
