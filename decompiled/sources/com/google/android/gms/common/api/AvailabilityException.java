package com.google.android.gms.common.api;

import android.support.v4.media.MediaMetadataCompat$Builder$$ExternalSyntheticOutline0;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class AvailabilityException extends Exception {
    public final ArrayMap zaa;

    public AvailabilityException(@NonNull ArrayMap arrayMap) {
        this.zaa = arrayMap;
    }

    @NonNull
    public ConnectionResult getConnectionResult(@NonNull GoogleApi<? extends Api.ApiOptions> googleApi) {
        ArrayMap arrayMap = this.zaa;
        ApiKey apiKey = googleApi.zaf;
        Object obj = arrayMap.get(apiKey);
        Preconditions.checkArgument(obj != null, MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("The given API (", apiKey.zab.zac, ") was not part of the availability request."));
        ConnectionResult connectionResult = (ConnectionResult) this.zaa.get(apiKey);
        Preconditions.checkNotNull(connectionResult);
        return connectionResult;
    }

    @Override // java.lang.Throwable
    @NonNull
    public String getMessage() {
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (ApiKey apiKey : (ArrayMap.KeySet) this.zaa.keySet()) {
            ConnectionResult connectionResult = (ConnectionResult) this.zaa.get(apiKey);
            Preconditions.checkNotNull(connectionResult);
            z &= !connectionResult.isSuccess();
            arrayList.add(apiKey.zab.zac + ": " + String.valueOf(connectionResult));
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("None of the queried APIs are available. ");
        } else {
            sb.append("Some of the queried APIs are unavailable. ");
        }
        sb.append(TextUtils.join("; ", arrayList));
        return sb.toString();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @NonNull
    public ConnectionResult getConnectionResult(@NonNull HasApiKey<? extends Api.ApiOptions> hasApiKey) {
        ArrayMap arrayMap = this.zaa;
        ApiKey<O> apiKey = hasApiKey.getApiKey();
        Object obj = arrayMap.get(apiKey);
        Preconditions.checkArgument(obj != null, MediaMetadataCompat$Builder$$ExternalSyntheticOutline0.m("The given API (", apiKey.zab.zac, ") was not part of the availability request."));
        ConnectionResult connectionResult = (ConnectionResult) this.zaa.get(apiKey);
        Preconditions.checkNotNull(connectionResult);
        return connectionResult;
    }
}
