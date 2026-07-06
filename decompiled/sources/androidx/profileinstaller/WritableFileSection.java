package androidx.profileinstaller;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class WritableFileSection {
    public final byte[] mContents;
    public final int mExpectedInflateSize;
    public final boolean mNeedsCompression;
    public final FileSectionType mType;

    public WritableFileSection(@NonNull FileSectionType fileSectionType, int i, @NonNull byte[] bArr, boolean z) {
        this.mType = fileSectionType;
        this.mExpectedInflateSize = i;
        this.mContents = bArr;
        this.mNeedsCompression = z;
    }
}
