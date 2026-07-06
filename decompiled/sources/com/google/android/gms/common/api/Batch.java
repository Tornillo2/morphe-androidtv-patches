package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class Batch extends BasePendingResult<BatchResult> {
    public int zae;
    public boolean zaf;
    public boolean zag;
    public final PendingResult[] zah;
    public final Object zai;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public final List zaa = new ArrayList();
        public final GoogleApiClient zab;

        public Builder(@NonNull GoogleApiClient googleApiClient) {
            this.zab = googleApiClient;
        }

        @NonNull
        @ResultIgnorabilityUnspecified
        public <R extends Result> BatchResultToken<R> add(@NonNull PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken<>(this.zaa.size());
            this.zaa.add(pendingResult);
            return batchResultToken;
        }

        @NonNull
        public Batch build() {
            return new Batch(this.zaa, this.zab, null);
        }
    }

    public /* synthetic */ Batch(List list, GoogleApiClient googleApiClient, zac zacVar) {
        super(googleApiClient);
        this.zai = new Object();
        int size = list.size();
        this.zae = size;
        PendingResult[] pendingResultArr = new PendingResult[size];
        this.zah = pendingResultArr;
        if (list.isEmpty()) {
            setResult(new BatchResult(Status.RESULT_SUCCESS, pendingResultArr));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            PendingResult pendingResult = (PendingResult) list.get(i);
            this.zah[i] = pendingResult;
            pendingResult.addStatusListener(new zab(this));
        }
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult, com.google.android.gms.common.api.PendingResult
    public void cancel() {
        super.cancel();
        int i = 0;
        while (true) {
            PendingResult[] pendingResultArr = this.zah;
            if (i >= pendingResultArr.length) {
                return;
            }
            pendingResultArr[i].cancel();
            i++;
        }
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    @NonNull
    public BatchResult createFailedResult(@NonNull Status status) {
        return new BatchResult(status, this.zah);
    }
}
