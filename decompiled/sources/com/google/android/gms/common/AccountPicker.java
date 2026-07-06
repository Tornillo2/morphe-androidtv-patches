package com.google.android.gms.common;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class AccountPicker {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AccountChooserOptions {

        @Nullable
        public Account zza;
        public boolean zzb;

        @Nullable
        public ArrayList zzc;

        @Nullable
        public ArrayList zzd;
        public boolean zze;

        @Nullable
        public String zzf;

        @Nullable
        public Bundle zzg;
        public boolean zzh;
        public int zzi;

        @Nullable
        public String zzj;
        public boolean zzk;

        @Nullable
        public zza zzl;

        @Nullable
        public String zzm;
        public boolean zzn;
        public boolean zzo;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static class Builder {

            @Nullable
            public Account zza;

            @Nullable
            public ArrayList zzb;

            @Nullable
            public ArrayList zzc;
            public boolean zzd = false;

            @Nullable
            public String zze;

            @Nullable
            public Bundle zzf;

            @NonNull
            public AccountChooserOptions build() {
                AccountChooserOptions accountChooserOptions = new AccountChooserOptions();
                accountChooserOptions.zzd = this.zzc;
                accountChooserOptions.zzc = this.zzb;
                accountChooserOptions.zze = this.zzd;
                accountChooserOptions.zzl = null;
                accountChooserOptions.zzj = null;
                accountChooserOptions.zzg = this.zzf;
                accountChooserOptions.zza = this.zza;
                accountChooserOptions.zzb = false;
                accountChooserOptions.zzh = false;
                accountChooserOptions.zzm = null;
                accountChooserOptions.zzi = 0;
                accountChooserOptions.zzf = this.zze;
                accountChooserOptions.zzk = false;
                accountChooserOptions.zzn = false;
                accountChooserOptions.zzo = false;
                return accountChooserOptions;
            }

            @NonNull
            @CanIgnoreReturnValue
            public Builder setAllowableAccounts(@Nullable List<Account> list) {
                this.zzb = list == null ? null : new ArrayList(list);
                return this;
            }

            @NonNull
            @CanIgnoreReturnValue
            public Builder setAllowableAccountsTypes(@Nullable List<String> list) {
                this.zzc = list == null ? null : new ArrayList(list);
                return this;
            }

            @NonNull
            @CanIgnoreReturnValue
            public Builder setAlwaysShowAccountPicker(boolean z) {
                this.zzd = z;
                return this;
            }

            @NonNull
            @CanIgnoreReturnValue
            public Builder setOptionsForAddingAccount(@Nullable Bundle bundle) {
                this.zzf = bundle;
                return this;
            }

            @NonNull
            @CanIgnoreReturnValue
            public Builder setSelectedAccount(@Nullable Account account) {
                this.zza = account;
                return this;
            }

            @NonNull
            @CanIgnoreReturnValue
            public Builder setTitleOverrideText(@Nullable String str) {
                this.zze = str;
                return this;
            }
        }

        public static /* bridge */ /* synthetic */ boolean zzA(AccountChooserOptions accountChooserOptions) {
            boolean z = accountChooserOptions.zzo;
            return false;
        }

        public static /* bridge */ /* synthetic */ boolean zzB(AccountChooserOptions accountChooserOptions) {
            boolean z = accountChooserOptions.zzb;
            return false;
        }

        public static /* bridge */ /* synthetic */ boolean zzC(AccountChooserOptions accountChooserOptions) {
            boolean z = accountChooserOptions.zzh;
            return false;
        }

        public static /* bridge */ /* synthetic */ boolean zzD(AccountChooserOptions accountChooserOptions) {
            boolean z = accountChooserOptions.zzk;
            return false;
        }

        public static /* bridge */ /* synthetic */ int zza(AccountChooserOptions accountChooserOptions) {
            int i = accountChooserOptions.zzi;
            return 0;
        }

        public static /* bridge */ /* synthetic */ zza zzd(AccountChooserOptions accountChooserOptions) {
            zza zzaVar = accountChooserOptions.zzl;
            return null;
        }

        public static /* bridge */ /* synthetic */ String zze(AccountChooserOptions accountChooserOptions) {
            String str = accountChooserOptions.zzj;
            return null;
        }

        public static /* bridge */ /* synthetic */ String zzf(AccountChooserOptions accountChooserOptions) {
            String str = accountChooserOptions.zzm;
            return null;
        }

        public static /* bridge */ /* synthetic */ boolean zzz(AccountChooserOptions accountChooserOptions) {
            boolean z = accountChooserOptions.zzn;
            return false;
        }
    }

    @NonNull
    @ResultIgnorabilityUnspecified
    @Deprecated
    public static Intent newChooseAccountIntent(@Nullable Account account, @Nullable ArrayList<Account> arrayList, @Nullable String[] strArr, boolean z, @Nullable String str, @Nullable String str2, @Nullable String[] strArr2, @Nullable Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction("com.google.android.gms.common.account.CHOOSE_ACCOUNT");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("allowableAccounts", arrayList);
        intent.putExtra("allowableAccountTypes", strArr);
        intent.putExtra("addAccountOptions", bundle);
        intent.putExtra("selectedAccount", account);
        intent.putExtra("alwaysPromptForAccount", z);
        intent.putExtra("descriptionTextOverride", str);
        intent.putExtra("authTokenType", str2);
        intent.putExtra("addAccountRequiredFeatures", strArr2);
        intent.putExtra("setGmsCoreAccount", false);
        intent.putExtra("overrideTheme", 0);
        intent.putExtra("overrideCustomTheme", 0);
        intent.putExtra("hostedDomainFilter", (String) null);
        return intent;
    }

    @NonNull
    public static Intent newChooseAccountIntent(@NonNull AccountChooserOptions accountChooserOptions) {
        Intent intent = new Intent();
        boolean z = accountChooserOptions.zzk;
        intent.setAction("com.google.android.gms.common.account.CHOOSE_ACCOUNT");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("allowableAccounts", accountChooserOptions.zzc);
        ArrayList arrayList = accountChooserOptions.zzd;
        if (arrayList != null) {
            intent.putExtra("allowableAccountTypes", (String[]) arrayList.toArray(new String[0]));
        }
        intent.putExtra("addAccountOptions", accountChooserOptions.zzg);
        intent.putExtra("selectedAccount", accountChooserOptions.zza);
        intent.putExtra("selectedAccountIsNotClickable", false);
        intent.putExtra("alwaysPromptForAccount", accountChooserOptions.zze);
        intent.putExtra("descriptionTextOverride", accountChooserOptions.zzf);
        intent.putExtra("setGmsCoreAccount", false);
        intent.putExtra("realClientPackage", (String) null);
        intent.putExtra("overrideTheme", 0);
        intent.putExtra("overrideCustomTheme", 0);
        intent.putExtra("hostedDomainFilter", (String) null);
        Bundle bundle = new Bundle();
        if (!bundle.isEmpty()) {
            intent.putExtra("first_party_options_bundle", bundle);
        }
        return intent;
    }
}
