package com.google.android.exoplayer2.text;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public interface TextOutput {
    void onCues(CueGroup cueGroup);

    @Deprecated
    void onCues(List<Cue> list);

    /* JADX INFO: renamed from: com.google.android.exoplayer2.text.TextOutput$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        @Deprecated
        public static void $default$onCues(TextOutput textOutput, List list) {
        }
    }
}
