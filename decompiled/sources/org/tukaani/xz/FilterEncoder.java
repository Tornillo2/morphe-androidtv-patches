package org.tukaani.xz;

/* JADX INFO: loaded from: classes4.dex */
public interface FilterEncoder extends FilterCoder {
    long getFilterID();

    byte[] getFilterProps();

    FinishableOutputStream getOutputStream(FinishableOutputStream finishableOutputStream);

    boolean supportsFlushing();
}
