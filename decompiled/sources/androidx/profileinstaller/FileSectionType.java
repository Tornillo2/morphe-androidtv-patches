package androidx.profileinstaller;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public enum FileSectionType {
    DEX_FILES(0),
    EXTRA_DESCRIPTORS(1),
    CLASSES(2),
    METHODS(3),
    AGGREGATION_COUNT(4);

    public final long mValue;

    FileSectionType(long j) {
        this.mValue = j;
    }

    public static FileSectionType fromValue(long j) {
        for (FileSectionType fileSectionType : values()) {
            if (fileSectionType.mValue == j) {
                return fileSectionType;
            }
        }
        throw new IllegalArgumentException("Unsupported FileSection Type " + j);
    }

    public long getValue() {
        return this.mValue;
    }
}
