package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import androidx.emoji2.text.flatbuffer.Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zaz extends RemoteCreator {
    public static final zaz zaa = new zaz();

    public zaz() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View zaa(Context context, int i, int i2) throws RemoteCreator.RemoteCreatorException {
        zaz zazVar = zaa;
        try {
            zax zaxVar = new zax(1, i, i2, null);
            return (View) ObjectWrapper.unwrap(((zam) zazVar.getRemoteCreatorInstance(context)).zae(ObjectWrapper.wrap(context), zaxVar));
        } catch (Exception e) {
            throw new RemoteCreator.RemoteCreatorException(Utf8$UnpairedSurrogateException$$ExternalSyntheticOutline0.m("Could not get button with size ", i, " and color ", i2), e);
        }
    }

    @Override // com.google.android.gms.dynamic.RemoteCreator
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
        return iInterfaceQueryLocalInterface instanceof zam ? (zam) iInterfaceQueryLocalInterface : new zam(iBinder);
    }
}
