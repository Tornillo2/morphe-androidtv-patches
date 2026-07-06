package com.amazon.minerva.client.thirdparty.utils;

import android.util.Log;
import com.amazon.minerva.client.thirdparty.api.provider.CustomerInfoProvider;
import com.amazon.minerva.client.thirdparty.compliance.ChildProfileVerifier;
import com.amazon.minerva.client.thirdparty.compliance.NonAnonymousCustomerIdProvider;
import com.amazon.minerva.client.thirdparty.compliance.UserControlVerifier;
import com.amazon.minerva.client.thirdparty.transport.OAuthProvider;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class CustomDeviceUtil {
    public static final CustomDeviceUtil INSTANCE = new CustomDeviceUtil();
    public static final String TAG = "CustomDeviceUtil";
    public static final String UNKNOWN = "UNKNOWN";
    public String mAppConfigId;
    public ChildProfileVerifier mChildProfileVerifier;
    public CustomerInfoProvider mCustomerInfoProvider;
    public String mDeviceType;
    public String mHashedDeviceType;
    public String mMarketplaceId;
    public NonAnonymousCustomerIdProvider mNonAnonymousCustomerIdProvider;
    public String mNonAnonymousDeviceId;
    public OAuthProvider mOAuthProvider;
    public UserControlVerifier mUserControlVerifier;
    public AtomicBoolean initialized = new AtomicBoolean(false);
    public boolean mIsInitializerSetDeviceType = false;
    public boolean mIsInitializerSetOAuthProvider = false;
    public boolean mIsInitializerSetChildProfileVerifier = false;
    public boolean mIsInitializerSetUserControlVerifier = false;
    public boolean mIsInitializerSetNonAnonymousCustomerIdProvider = false;
    public boolean mIsInitializerSetNonAnonymousDeviceId = false;
    public boolean mIsInitializerSetMarketplaceId = false;
    public boolean mIsInitializerSetCustomerInfoProvider = false;

    public static CustomDeviceUtil getInstance() {
        return INSTANCE;
    }

    public final String calculateHashedDeviceType(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            String string = new BigInteger(1, messageDigest.digest()).toString(16);
            return string.length() > 63 ? string.substring(1) : string;
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "Failed to generate hash value of device type", e);
            return "UNKNOWN";
        }
    }

    public String getAppConfigId() {
        return this.mAppConfigId;
    }

    public ChildProfileVerifier getChildProfileVerifier() {
        return this.mChildProfileVerifier;
    }

    public CustomerInfoProvider getCustomerInfoProvider() {
        return this.mCustomerInfoProvider;
    }

    public String getDeviceType() {
        return this.mDeviceType;
    }

    public String getHashedDeviceType() {
        return this.mHashedDeviceType;
    }

    public String getMarketplaceId() {
        return this.mMarketplaceId;
    }

    public NonAnonymousCustomerIdProvider getNonAnonymousCustomerIdProvider() {
        return this.mNonAnonymousCustomerIdProvider;
    }

    public String getNonAnonymousDeviceId() {
        return this.mNonAnonymousDeviceId;
    }

    public OAuthProvider getOAuthProvider() {
        return this.mOAuthProvider;
    }

    public UserControlVerifier getUserControlVerifier() {
        return this.mUserControlVerifier;
    }

    public synchronized void initialize() {
        if (this.initialized.compareAndSet(false, true)) {
            Log.i(TAG, "Initializing CustomDeviceUtil");
        }
    }

    public boolean isInitialized() {
        return this.initialized.get();
    }

    public void setAppConfigId(String str) {
        this.mAppConfigId = str;
    }

    public void setChildProfileVerifier(ChildProfileVerifier childProfileVerifier) {
        if (this.mIsInitializerSetChildProfileVerifier) {
            return;
        }
        this.mChildProfileVerifier = childProfileVerifier;
    }

    public void setChildProfileVerifierInitializer(ChildProfileVerifier childProfileVerifier) {
        if (this.mIsInitializerSetChildProfileVerifier) {
            return;
        }
        this.mChildProfileVerifier = childProfileVerifier;
        this.mIsInitializerSetChildProfileVerifier = true;
    }

    public void setCustomerIdProvider(NonAnonymousCustomerIdProvider nonAnonymousCustomerIdProvider) {
        if (this.mIsInitializerSetNonAnonymousCustomerIdProvider) {
            return;
        }
        this.mNonAnonymousCustomerIdProvider = nonAnonymousCustomerIdProvider;
    }

    public void setCustomerIdProviderInitializer(NonAnonymousCustomerIdProvider nonAnonymousCustomerIdProvider) {
        if (this.mIsInitializerSetNonAnonymousCustomerIdProvider) {
            return;
        }
        this.mNonAnonymousCustomerIdProvider = nonAnonymousCustomerIdProvider;
        this.mIsInitializerSetNonAnonymousCustomerIdProvider = true;
    }

    public void setCustomerInfoProviderInitializer(CustomerInfoProvider customerInfoProvider) {
        if (this.mIsInitializerSetCustomerInfoProvider) {
            return;
        }
        this.mCustomerInfoProvider = customerInfoProvider;
        this.mIsInitializerSetCustomerInfoProvider = true;
    }

    public void setDeviceType(String str) {
        if (this.mIsInitializerSetDeviceType) {
            return;
        }
        this.mDeviceType = str;
        this.mHashedDeviceType = calculateHashedDeviceType(str);
    }

    public void setDeviceTypeInitializer(String str) {
        if (this.mIsInitializerSetDeviceType) {
            return;
        }
        this.mDeviceType = str;
        this.mHashedDeviceType = calculateHashedDeviceType(str);
        this.mIsInitializerSetDeviceType = true;
    }

    public void setMarketplaceId(String str) {
        if (this.mIsInitializerSetMarketplaceId) {
            return;
        }
        this.mMarketplaceId = str;
    }

    public void setMarketplaceIdInitializer(String str) {
        if (this.mIsInitializerSetMarketplaceId) {
            return;
        }
        this.mMarketplaceId = str;
        this.mIsInitializerSetMarketplaceId = true;
    }

    public void setNonAnonymousDeviceId(String str) {
        if (this.mIsInitializerSetNonAnonymousDeviceId) {
            return;
        }
        this.mNonAnonymousDeviceId = str;
    }

    public void setNonAnonymousDeviceIdInitializer(String str) {
        if (this.mIsInitializerSetNonAnonymousDeviceId) {
            return;
        }
        this.mNonAnonymousDeviceId = str;
        this.mIsInitializerSetNonAnonymousDeviceId = true;
    }

    public void setOAuthProvider(OAuthProvider oAuthProvider) {
        if (this.mIsInitializerSetOAuthProvider) {
            return;
        }
        this.mOAuthProvider = oAuthProvider;
    }

    public void setOAuthProviderInitializer(OAuthProvider oAuthProvider) {
        if (this.mIsInitializerSetOAuthProvider) {
            return;
        }
        this.mOAuthProvider = oAuthProvider;
        this.mIsInitializerSetOAuthProvider = true;
    }

    public void setUserControlVerifier(UserControlVerifier userControlVerifier) {
        if (this.mIsInitializerSetUserControlVerifier) {
            return;
        }
        this.mUserControlVerifier = userControlVerifier;
    }

    public void setUserControlVerifierInitializer(UserControlVerifier userControlVerifier) {
        if (this.mIsInitializerSetUserControlVerifier) {
            return;
        }
        this.mUserControlVerifier = userControlVerifier;
        this.mIsInitializerSetUserControlVerifier = true;
    }
}
