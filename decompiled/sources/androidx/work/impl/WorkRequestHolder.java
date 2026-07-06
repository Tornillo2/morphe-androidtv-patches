package androidx.work.impl;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.WorkRequest;
import androidx.work.impl.model.WorkSpec;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class WorkRequestHolder extends WorkRequest {
    public WorkRequestHolder(@NonNull UUID id, @NonNull WorkSpec workSpec, @NonNull Set<String> tags) {
        super(id, workSpec, tags);
    }
}
