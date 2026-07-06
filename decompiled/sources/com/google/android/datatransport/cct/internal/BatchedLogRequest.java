package com.google.android.datatransport.cct.internal;

import androidx.annotation.NonNull;
import com.google.auto.value.AutoValue;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.annotations.Encodable;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder.AnonymousClass1;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@AutoValue
@Encodable
public abstract class BatchedLogRequest {
    @NonNull
    public static BatchedLogRequest create(@NonNull List<LogRequest> list) {
        return new AutoValue_BatchedLogRequest(list);
    }

    @NonNull
    public static DataEncoder createDataEncoder() {
        JsonDataEncoderBuilder jsonDataEncoderBuilder = new JsonDataEncoderBuilder();
        AutoBatchedLogRequestEncoder.CONFIG.configure(jsonDataEncoderBuilder);
        jsonDataEncoderBuilder.ignoreNullValues = true;
        return jsonDataEncoderBuilder.new AnonymousClass1();
    }

    @NonNull
    @Encodable.Field(name = "logRequest")
    public abstract List<LogRequest> getLogRequests();
}
