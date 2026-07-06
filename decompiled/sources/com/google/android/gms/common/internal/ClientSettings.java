package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.collection.ArraySet;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.signin.SignInOptions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import j$.util.DesugarCollections;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@KeepForSdk
public final class ClientSettings {

    @Nullable
    public final Account zaa;
    public final Set zab;
    public final Set zac;
    public final Map zad;
    public final int zae;

    @Nullable
    public final View zaf;
    public final String zag;
    public final String zah;
    public final SignInOptions zai;
    public Integer zaj;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @KeepForSdk
    public static final class Builder {

        @Nullable
        public Account zaa;
        public ArraySet zab;
        public String zac;
        public String zad;
        public final SignInOptions zae = SignInOptions.zaa;

        @NonNull
        @KeepForSdk
        public ClientSettings build() {
            return new ClientSettings(this.zaa, this.zab, null, 0, null, this.zac, this.zad, this.zae, false);
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder setRealClientPackageName(@NonNull String str) {
            this.zac = str;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public final Builder zaa(@NonNull Collection collection) {
            if (this.zab == null) {
                this.zab = new ArraySet();
            }
            this.zab.addAll(collection);
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public final Builder zab(@Nullable Account account) {
            this.zaa = account;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public final Builder zac(@NonNull String str) {
            this.zad = str;
            return this;
        }
    }

    @KeepForSdk
    public ClientSettings(@NonNull Account account, @NonNull Set<Scope> set, @NonNull Map<Api<?>, zab> map, int i, @Nullable View view, @NonNull String str, @NonNull String str2, @Nullable SignInOptions signInOptions) {
        this(account, set, map, i, view, str, str2, signInOptions, false);
    }

    @NonNull
    @KeepForSdk
    public static ClientSettings createDefault(@NonNull Context context) {
        return new GoogleApiClient.Builder(context).zaa();
    }

    @androidx.annotation.Nullable
    @KeepForSdk
    public Account getAccount() {
        return this.zaa;
    }

    @androidx.annotation.Nullable
    @KeepForSdk
    @Deprecated
    public String getAccountName() {
        Account account = this.zaa;
        if (account != null) {
            return account.name;
        }
        return null;
    }

    @NonNull
    @KeepForSdk
    public Account getAccountOrDefault() {
        Account account = this.zaa;
        return account != null ? account : new Account("<<default account>>", AccountType.GOOGLE);
    }

    @NonNull
    @KeepForSdk
    public Set<Scope> getAllRequestedScopes() {
        return this.zac;
    }

    @NonNull
    @KeepForSdk
    public Set<Scope> getApplicableScopes(@NonNull Api<?> api) {
        zab zabVar = (zab) this.zad.get(api);
        if (zabVar == null || zabVar.zaa.isEmpty()) {
            return this.zab;
        }
        HashSet hashSet = new HashSet(this.zab);
        hashSet.addAll(zabVar.zaa);
        return hashSet;
    }

    @KeepForSdk
    public int getGravityForPopups() {
        return this.zae;
    }

    @NonNull
    @KeepForSdk
    public String getRealClientPackageName() {
        return this.zag;
    }

    @NonNull
    @KeepForSdk
    public Set<Scope> getRequiredScopes() {
        return this.zab;
    }

    @androidx.annotation.Nullable
    @KeepForSdk
    public View getViewForPopups() {
        return this.zaf;
    }

    @NonNull
    public final SignInOptions zaa() {
        return this.zai;
    }

    @androidx.annotation.Nullable
    public final Integer zab() {
        return this.zaj;
    }

    @androidx.annotation.Nullable
    public final String zac() {
        return this.zah;
    }

    @NonNull
    public final Map zad() {
        return this.zad;
    }

    public final void zae(@NonNull Integer num) {
        this.zaj = num;
    }

    public ClientSettings(@Nullable Account account, @NonNull Set set, @NonNull Map map, int i, @Nullable View view, @NonNull String str, @NonNull String str2, @Nullable SignInOptions signInOptions, boolean z) {
        this.zaa = account;
        Set setUnmodifiableSet = set == null ? Collections.EMPTY_SET : DesugarCollections.unmodifiableSet(set);
        this.zab = setUnmodifiableSet;
        map = map == null ? Collections.EMPTY_MAP : map;
        this.zad = map;
        this.zaf = view;
        this.zae = i;
        this.zag = str;
        this.zah = str2;
        this.zai = signInOptions == null ? SignInOptions.zaa : signInOptions;
        HashSet hashSet = new HashSet(setUnmodifiableSet);
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            hashSet.addAll(((zab) it.next()).zaa);
        }
        this.zac = DesugarCollections.unmodifiableSet(hashSet);
    }
}
