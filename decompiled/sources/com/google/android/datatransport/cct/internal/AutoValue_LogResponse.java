package com.google.android.datatransport.cct.internal;

import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AutoValue_LogResponse extends LogResponse {
    public final long nextRequestWaitMillis;

    public AutoValue_LogResponse(long j) {
        this.nextRequestWaitMillis = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof LogResponse) && this.nextRequestWaitMillis == ((LogResponse) obj).getNextRequestWaitMillis();
    }

    @Override // com.google.android.datatransport.cct.internal.LogResponse
    public long getNextRequestWaitMillis() {
        return this.nextRequestWaitMillis;
    }

    public int hashCode() {
        long j = this.nextRequestWaitMillis;
        return 1000003 ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(new StringBuilder("LogResponse{nextRequestWaitMillis="), this.nextRequestWaitMillis, "}");
    }
}
