package androidx.media3.exoplayer.trackselection;

import android.os.SystemClock;
import androidx.annotation.Nullable;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.Tracks;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.MappingTrackSelector;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@UnstableApi
public final class TrackSelectionUtil {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface AdaptiveTrackSelectionFactory {
        ExoTrackSelection createAdaptiveTrackSelection(ExoTrackSelection.Definition definition);
    }

    public static Tracks buildTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, TrackSelection[] trackSelectionArr) {
        List[] listArr = new List[trackSelectionArr.length];
        for (int i = 0; i < trackSelectionArr.length; i++) {
            TrackSelection trackSelection = trackSelectionArr[i];
            listArr[i] = trackSelection != null ? ImmutableList.of(trackSelection) : ImmutableList.of();
        }
        return buildTracks(mappedTrackInfo, (List<? extends TrackSelection>[]) listArr);
    }

    public static LoadErrorHandlingPolicy.FallbackOptions createFallbackOptions(ExoTrackSelection exoTrackSelection) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        int length = exoTrackSelection.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (exoTrackSelection.isTrackExcluded(i2, jElapsedRealtime)) {
                i++;
            }
        }
        return new LoadErrorHandlingPolicy.FallbackOptions(1, 0, length, i);
    }

    public static ExoTrackSelection[] createTrackSelectionsForDefinitions(ExoTrackSelection.Definition[] definitionArr, AdaptiveTrackSelectionFactory adaptiveTrackSelectionFactory) {
        ExoTrackSelection[] exoTrackSelectionArr = new ExoTrackSelection[definitionArr.length];
        boolean z = false;
        for (int i = 0; i < definitionArr.length; i++) {
            ExoTrackSelection.Definition definition = definitionArr[i];
            if (definition != null) {
                int[] iArr = definition.tracks;
                if (iArr.length <= 1 || z) {
                    exoTrackSelectionArr[i] = new FixedTrackSelection(definition.group, iArr[0], definition.type);
                } else {
                    exoTrackSelectionArr[i] = adaptiveTrackSelectionFactory.createAdaptiveTrackSelection(definition);
                    z = true;
                }
            }
        }
        return exoTrackSelectionArr;
    }

    @Deprecated
    public static DefaultTrackSelector.Parameters updateParametersWithOverride(DefaultTrackSelector.Parameters parameters, int i, TrackGroupArray trackGroupArray, boolean z, @Nullable DefaultTrackSelector.SelectionOverride selectionOverride) {
        parameters.getClass();
        DefaultTrackSelector.Parameters.Builder builder = new DefaultTrackSelector.Parameters.Builder(parameters);
        builder.clearSelectionOverrides(i);
        builder.setRendererDisabled(i, z);
        if (selectionOverride != null) {
            builder.setSelectionOverride(i, trackGroupArray, selectionOverride);
        }
        return new DefaultTrackSelector.Parameters(builder);
    }

    public static Tracks buildTracks(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, List<? extends TrackSelection>[] listArr) {
        boolean z;
        ImmutableList.Builder builder = new ImmutableList.Builder(4);
        for (int i = 0; i < mappedTrackInfo.rendererCount; i++) {
            TrackGroupArray trackGroupArray = mappedTrackInfo.rendererTrackGroups[i];
            List<? extends TrackSelection> list = listArr[i];
            for (int i2 = 0; i2 < trackGroupArray.length; i2++) {
                TrackGroup trackGroup = trackGroupArray.get(i2);
                boolean z2 = mappedTrackInfo.getAdaptiveSupport(i, i2, false) != 0;
                int i3 = trackGroup.length;
                int[] iArr = new int[i3];
                boolean[] zArr = new boolean[i3];
                for (int i4 = 0; i4 < trackGroup.length; i4++) {
                    iArr[i4] = mappedTrackInfo.getTrackSupport(i, i2, i4);
                    int i5 = 0;
                    while (true) {
                        if (i5 >= list.size()) {
                            z = false;
                            break;
                        }
                        TrackSelection trackSelection = list.get(i5);
                        if (trackSelection.getTrackGroup().equals(trackGroup) && trackSelection.indexOf(i4) != -1) {
                            z = true;
                            break;
                        }
                        i5++;
                    }
                    zArr[i4] = z;
                }
                builder.add(new Tracks.Group(trackGroup, z2, iArr, zArr));
            }
        }
        TrackGroupArray trackGroupArray2 = mappedTrackInfo.unmappedTrackGroups;
        for (int i6 = 0; i6 < trackGroupArray2.length; i6++) {
            TrackGroup trackGroup2 = trackGroupArray2.get(i6);
            int[] iArr2 = new int[trackGroup2.length];
            Arrays.fill(iArr2, 0);
            builder.add(new Tracks.Group(trackGroup2, false, iArr2, new boolean[trackGroup2.length]));
        }
        return new Tracks(builder.build());
    }
}
