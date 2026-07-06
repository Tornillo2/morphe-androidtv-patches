package com.google.android.gms.internal.ads;

import java.util.Iterator;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzfpj implements Iterable {
    public final /* synthetic */ CharSequence zza;
    public final /* synthetic */ zzfpm zzb;

    public zzfpj(zzfpm zzfpmVar, CharSequence charSequence) {
        this.zzb = zzfpmVar;
        this.zza = charSequence;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        zzfpm zzfpmVar = this.zzb;
        return zzfpmVar.zzb.zza(zzfpmVar, this.zza);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        zzfoo.zzb(sb, this, ", ");
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }
}
