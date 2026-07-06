package org.apache.commons.lang3;

import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.amazon.minerva.identifiers.schemaid.SchemaId;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ClassPathUtils {
    public static String toFullyQualifiedName(Class<?> cls, String str) {
        Validate.notNull(cls, "Parameter '%s' must not be null!", "context");
        Validate.notNull(str, "Parameter '%s' must not be null!", "resourceName");
        return toFullyQualifiedName(cls.getPackage(), str);
    }

    public static String toFullyQualifiedPath(Class<?> cls, String str) {
        Validate.notNull(cls, "Parameter '%s' must not be null!", "context");
        Validate.notNull(str, "Parameter '%s' must not be null!", "resourceName");
        return toFullyQualifiedPath(cls.getPackage(), str);
    }

    public static String toFullyQualifiedName(Package r4, String str) {
        Validate.notNull(r4, "Parameter '%s' must not be null!", "context");
        Validate.notNull(str, "Parameter '%s' must not be null!", "resourceName");
        return r4.getName() + ExternalFourCCMapper.CODEC_NAME_SPLITTER + str;
    }

    public static String toFullyQualifiedPath(Package r4, String str) {
        Validate.notNull(r4, "Parameter '%s' must not be null!", "context");
        Validate.notNull(str, "Parameter '%s' must not be null!", "resourceName");
        return r4.getName().replace('.', '/') + SchemaId.METRIC_SCHEMA_ID_DELIMITER + str;
    }
}
