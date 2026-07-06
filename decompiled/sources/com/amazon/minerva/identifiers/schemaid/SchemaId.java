package com.amazon.minerva.identifiers.schemaid;

import com.amazon.minerva.identifiers.schemaid.attribute.attributes.VersionedAttributes;
import com.amazon.minerva.identifiers.schemaid.attribute.parser.Version2AttributesParser;
import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class SchemaId {
    public static final int FORMAT_VERSION_2 = 2;
    public static final String METRIC_SCHEMA_ID_DELIMITER = "/";
    public Integer formatVersion;
    public String identifier;
    public VersionedAttributes versionedAttributes;

    public SchemaId(String str) {
        Objects.requireNonNull(str, "Metric Schema ID cannot be empty.");
        String[] strArrSplitMetricSchemaId = splitMetricSchemaId(str);
        String str2 = strArrSplitMetricSchemaId[0];
        int i = Integer.parseInt(strArrSplitMetricSchemaId[1]);
        initialize(str2, i, parseControlBits(i, strArrSplitMetricSchemaId[2]));
    }

    public static String getMetricSchemaId(String str, int i, VersionedAttributes versionedAttributes) {
        return str + METRIC_SCHEMA_ID_DELIMITER + i + METRIC_SCHEMA_ID_DELIMITER + getControlBits(i, versionedAttributes);
    }

    public static VersionedAttributes parseControlBits(int i, String str) {
        Objects.requireNonNull(str, "ControlBits can not be null");
        if (i == 2) {
            return Version2AttributesParser.parseAttributes(str);
        }
        throw new UnsupportedOperationException("Format version is not supported.");
    }

    public static String[] splitMetricSchemaId(String str) {
        Objects.requireNonNull(str, "Metric Schema ID cannot be empty.");
        String[] strArrSplit = str.split(METRIC_SCHEMA_ID_DELIMITER);
        if (strArrSplit.length == 3) {
            return strArrSplit;
        }
        throw new IllegalArgumentException("SchemaId string should have a format of luid/version/controlBits.");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            SchemaId schemaId = (SchemaId) obj;
            if (Objects.equals(this.identifier, schemaId.identifier) && Objects.equals(this.formatVersion, schemaId.formatVersion) && Objects.equals(getControlBits(), schemaId.getControlBits())) {
                return true;
            }
        }
        return false;
    }

    public String getControlBits() {
        return getControlBits(this.formatVersion.intValue(), this.versionedAttributes);
    }

    public Integer getFormatVersion() {
        return this.formatVersion;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public VersionedAttributes getVersionedAttributes() {
        return this.versionedAttributes;
    }

    public int hashCode() {
        return Objects.hash(this.identifier, this.formatVersion, getControlBits());
    }

    public final void initialize(String str, int i, VersionedAttributes versionedAttributes) {
        this.identifier = str;
        this.formatVersion = Integer.valueOf(i);
        this.versionedAttributes = versionedAttributes;
    }

    public String toString() {
        return getMetricSchemaId(this.identifier, this.formatVersion.intValue(), this.versionedAttributes);
    }

    public static String getControlBits(int i, VersionedAttributes versionedAttributes) {
        if (i == 2) {
            return Version2AttributesParser.getAttributesHexString(versionedAttributes);
        }
        throw new UnsupportedOperationException("Format version is not supported.");
    }

    public SchemaId(String str, int i, VersionedAttributes versionedAttributes) {
        initialize(str, i, versionedAttributes);
    }
}
