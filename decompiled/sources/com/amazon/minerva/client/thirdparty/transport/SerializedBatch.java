package com.amazon.minerva.client.thirdparty.transport;

import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class SerializedBatch {
    public byte[] batchContent;
    public String fileName;

    public SerializedBatch(byte[] bArr, String str) {
        this.batchContent = bArr;
        this.fileName = str;
    }

    public byte[] getBatchContent() {
        return this.batchContent;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getRegion() {
        return this.fileName.split(Attributes.PREDEFINED_ATTRIBUTE_PREFIX, 3)[1];
    }
}
