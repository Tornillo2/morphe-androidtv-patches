package androidx.profileinstaller;

import androidx.annotation.NonNull;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class DexProfileData {

    @NonNull
    public final String apkName;
    public int classSetSize;

    @NonNull
    public int[] classes;
    public final long dexChecksum;

    @NonNull
    public final String dexName;
    public final int hotMethodRegionSize;
    public long mTypeIdCount;

    @NonNull
    public final TreeMap<Integer, Integer> methods;
    public final int numMethodIds;

    public DexProfileData(@NonNull String str, @NonNull String str2, long j, long j2, int i, int i2, int i3, @NonNull int[] iArr, @NonNull TreeMap<Integer, Integer> treeMap) {
        this.apkName = str;
        this.dexName = str2;
        this.dexChecksum = j;
        this.mTypeIdCount = j2;
        this.classSetSize = i;
        this.hotMethodRegionSize = i2;
        this.numMethodIds = i3;
        this.classes = iArr;
        this.methods = treeMap;
    }
}
