package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class UnsupportedZipFeatureException extends ZipException {
    public static final long serialVersionUID = 20130101;
    public final ZipArchiveEntry entry;
    public final Feature reason;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Feature {
        public final String name;
        public static final Feature ENCRYPTION = new Feature("encryption");
        public static final Feature METHOD = new Feature("compression method");
        public static final Feature DATA_DESCRIPTOR = new Feature("data descriptor");
        public static final Feature SPLITTING = new Feature("splitting");

        public Feature(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }

    public UnsupportedZipFeatureException(Feature feature, ZipArchiveEntry zipArchiveEntry) {
        super("unsupported feature " + feature + " used in entry " + zipArchiveEntry.getName());
        this.reason = feature;
        this.entry = zipArchiveEntry;
    }

    public ZipArchiveEntry getEntry() {
        return this.entry;
    }

    public Feature getFeature() {
        return this.reason;
    }

    public UnsupportedZipFeatureException(ZipMethod zipMethod, ZipArchiveEntry zipArchiveEntry) {
        super("unsupported feature method '" + zipMethod.name() + "' used in entry " + zipArchiveEntry.getName());
        this.reason = Feature.METHOD;
        this.entry = zipArchiveEntry;
    }

    public UnsupportedZipFeatureException(Feature feature) {
        super("unsupported feature " + feature + " used in archive.");
        this.reason = feature;
        this.entry = null;
    }
}
