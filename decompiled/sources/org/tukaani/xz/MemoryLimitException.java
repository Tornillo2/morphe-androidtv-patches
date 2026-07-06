package org.tukaani.xz;

/* JADX INFO: loaded from: classes4.dex */
public class MemoryLimitException extends XZIOException {
    public static final long serialVersionUID = 3;
    public final int memoryLimit;
    public final int memoryNeeded;

    public MemoryLimitException(int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append(i);
        stringBuffer.append(" KiB of memory would be needed; limit was ");
        stringBuffer.append(i2);
        stringBuffer.append(" KiB");
        super(stringBuffer.toString());
        this.memoryNeeded = i;
        this.memoryLimit = i2;
    }

    public int getMemoryLimit() {
        return this.memoryLimit;
    }

    public int getMemoryNeeded() {
        return this.memoryNeeded;
    }
}
