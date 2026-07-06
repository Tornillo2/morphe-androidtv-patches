package org.tukaani.xz;

/* JADX INFO: loaded from: classes4.dex */
public class CorruptedInputException extends XZIOException {
    public static final long serialVersionUID = 3;

    public CorruptedInputException() {
        super("Compressed data is corrupt");
    }

    public CorruptedInputException(String str) {
        super(str);
    }
}
