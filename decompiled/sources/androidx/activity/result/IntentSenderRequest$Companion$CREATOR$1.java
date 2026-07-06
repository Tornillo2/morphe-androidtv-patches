package androidx.activity.result;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IntentSenderRequest$Companion$CREATOR$1 implements Parcelable.Creator<IntentSenderRequest> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    @NotNull
    public IntentSenderRequest[] newArray(int i) {
        return new IntentSenderRequest[i];
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    @NotNull
    public IntentSenderRequest createFromParcel(@NotNull Parcel inParcel) {
        Intrinsics.checkNotNullParameter(inParcel, "inParcel");
        return new IntentSenderRequest(inParcel);
    }

    @Override // android.os.Parcelable.Creator
    public IntentSenderRequest[] newArray(int i) {
        return new IntentSenderRequest[i];
    }
}
