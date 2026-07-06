package org.tukaani.xz;

/* JADX INFO: loaded from: classes4.dex */
public class XZFormatException extends XZIOException {
    public static final long serialVersionUID = 3;

    public XZFormatException() {
        super("Input is not in the XZ format");
    }
}
