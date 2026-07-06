package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Deprecated;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Target({ElementType.METHOD})
@Deprecated(message = "Replaced by the androidx.resourceinpsection package.")
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER})
@Retention(RetentionPolicy.SOURCE)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
public @interface InspectableProperty {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @kotlin.annotation.Target(allowedTargets = {AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS})
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    public @interface EnumEntry {
        String name();

        int value();
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @kotlin.annotation.Target(allowedTargets = {AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS})
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    public @interface FlagEntry {
        int mask() default 0;

        String name();

        int target();
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ValueType {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ ValueType[] $VALUES;
        public static final ValueType NONE = new ValueType("NONE", 0);
        public static final ValueType INFERRED = new ValueType("INFERRED", 1);
        public static final ValueType INT_ENUM = new ValueType("INT_ENUM", 2);
        public static final ValueType INT_FLAG = new ValueType("INT_FLAG", 3);
        public static final ValueType COLOR = new ValueType("COLOR", 4);
        public static final ValueType GRAVITY = new ValueType("GRAVITY", 5);
        public static final ValueType RESOURCE_ID = new ValueType("RESOURCE_ID", 6);

        public static final /* synthetic */ ValueType[] $values() {
            return new ValueType[]{NONE, INFERRED, INT_ENUM, INT_FLAG, COLOR, GRAVITY, RESOURCE_ID};
        }

        static {
            ValueType[] valueTypeArr$values = $values();
            $VALUES = valueTypeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(valueTypeArr$values);
        }

        public ValueType(String str, int i) {
        }

        @NotNull
        public static EnumEntries<ValueType> getEntries() {
            return $ENTRIES;
        }

        public static ValueType valueOf(String str) {
            return (ValueType) Enum.valueOf(ValueType.class, str);
        }

        public static ValueType[] values() {
            return (ValueType[]) $VALUES.clone();
        }
    }

    int attributeId() default 0;

    EnumEntry[] enumMapping() default {};

    FlagEntry[] flagMapping() default {};

    boolean hasAttributeId() default true;

    String name() default "";

    ValueType valueType() default ValueType.INFERRED;
}
