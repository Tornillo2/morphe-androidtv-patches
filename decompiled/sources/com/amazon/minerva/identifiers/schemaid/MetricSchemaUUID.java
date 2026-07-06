package com.amazon.minerva.identifiers.schemaid;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import j$.util.Objects;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MetricSchemaUUID {
    public static final String METRIC_COMPOSITE_ID_DELIMITER = "|";
    public static final Pattern PATTERN_METRIC_GROUP_ID = Pattern.compile("[0-9a-z]{8}");
    public static final Pattern PATTERN_SCHEMA_ID = Pattern.compile("[0-9a-z]{4}/\\d+/[0-9a-f]{8}");
    public final String metricGroupId;
    public final SchemaId schemaId;

    public MetricSchemaUUID(String str, String str2) {
        if (!validateMetricGroupId(str)) {
            throw new IllegalArgumentException("Invalid argument for metricGroupId, validation failed.");
        }
        if (!validateSchemaId(str2)) {
            throw new IllegalArgumentException("Invalid argument for schemaId, validation failed.");
        }
        this.metricGroupId = str;
        this.schemaId = new SchemaId(str2);
    }

    public static String getMetricCompositeId(String str, String str2) {
        return AbstractResolvableFuture$$ExternalSyntheticOutline1.m(str, METRIC_COMPOSITE_ID_DELIMITER, str2);
    }

    public static boolean validateMetricGroupId(String str) {
        Objects.requireNonNull(str, "metricGroupId cannot be null.");
        return PATTERN_METRIC_GROUP_ID.matcher(str).matches();
    }

    public static boolean validateSchemaId(String str) {
        Objects.requireNonNull(str, "schemaId cannot be null.");
        return PATTERN_SCHEMA_ID.matcher(str).matches();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && MetricSchemaUUID.class == obj.getClass()) {
            MetricSchemaUUID metricSchemaUUID = (MetricSchemaUUID) obj;
            if (Objects.equals(this.metricGroupId, metricSchemaUUID.metricGroupId) && Objects.equals(this.schemaId, metricSchemaUUID.schemaId)) {
                return true;
            }
        }
        return false;
    }

    public String getMetricGroupId() {
        return this.metricGroupId;
    }

    public SchemaId getSchemaId() {
        return this.schemaId;
    }

    public int hashCode() {
        return Objects.hash(this.metricGroupId, this.schemaId);
    }

    public String toString() {
        return AbstractResolvableFuture$$ExternalSyntheticOutline1.m(this.metricGroupId, SchemaId.METRIC_SCHEMA_ID_DELIMITER, this.schemaId.toString());
    }
}
