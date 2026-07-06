package com.android.volley.toolbox;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import com.android.volley.AuthFailureError;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"MissingPermission"})
public class AndroidAuthenticator implements Authenticator {
    public final Account mAccount;
    public final AccountManager mAccountManager;
    public final String mAuthTokenType;
    public final boolean mNotifyAuthFailure;

    public AndroidAuthenticator(Context context, Account account, String str) {
        this(context, account, str, false);
    }

    public Account getAccount() {
        return this.mAccount;
    }

    @Override // com.android.volley.toolbox.Authenticator
    public String getAuthToken() throws AuthFailureError {
        String string;
        AccountManagerFuture<Bundle> authToken = this.mAccountManager.getAuthToken(this.mAccount, this.mAuthTokenType, this.mNotifyAuthFailure, null, null);
        try {
            Bundle result = authToken.getResult();
            if (!authToken.isDone() || authToken.isCancelled()) {
                string = null;
            } else {
                if (result.containsKey("intent")) {
                    throw new AuthFailureError((Intent) result.getParcelable("intent"));
                }
                string = result.getString("authtoken");
            }
            if (string != null) {
                return string;
            }
            throw new AuthFailureError("Got null auth token for type: " + this.mAuthTokenType);
        } catch (Exception e) {
            throw new AuthFailureError("Error while retrieving auth token", (Throwable) e);
        }
    }

    public String getAuthTokenType() {
        return this.mAuthTokenType;
    }

    @Override // com.android.volley.toolbox.Authenticator
    public void invalidateAuthToken(String str) {
        this.mAccountManager.invalidateAuthToken(this.mAccount.type, str);
    }

    public AndroidAuthenticator(Context context, Account account, String str, boolean z) {
        this(AccountManager.get(context), account, str, z);
    }

    @VisibleForTesting
    public AndroidAuthenticator(AccountManager accountManager, Account account, String str, boolean z) {
        this.mAccountManager = accountManager;
        this.mAccount = account;
        this.mAuthTokenType = str;
        this.mNotifyAuthFailure = z;
    }
}
