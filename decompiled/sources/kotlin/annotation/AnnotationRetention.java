package kotlin.annotation;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AnnotationRetention {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ AnnotationRetention[] $VALUES;
    public static final AnnotationRetention SOURCE = new AnnotationRetention("SOURCE", 0);
    public static final AnnotationRetention BINARY = new AnnotationRetention("BINARY", 1);
    public static final AnnotationRetention RUNTIME = new AnnotationRetention("RUNTIME", 2);

    public static final /* synthetic */ AnnotationRetention[] $values() {
        return new AnnotationRetention[]{SOURCE, BINARY, RUNTIME};
    }

    static {
        AnnotationRetention[] annotationRetentionArr$values = $values();
        $VALUES = annotationRetentionArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(annotationRetentionArr$values);
    }

    public AnnotationRetention(String str, int i) {
    }

    @NotNull
    public static EnumEntries<AnnotationRetention> getEntries() {
        return $ENTRIES;
    }

    public static AnnotationRetention valueOf(String str) {
        return (AnnotationRetention) Enum.valueOf(AnnotationRetention.class, str);
    }

    public static AnnotationRetention[] values() {
        return (AnnotationRetention[]) $VALUES.clone();
    }
}
