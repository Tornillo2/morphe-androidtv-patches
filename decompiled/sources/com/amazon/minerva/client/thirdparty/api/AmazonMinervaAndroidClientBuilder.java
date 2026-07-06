package com.amazon.minerva.client.thirdparty.api;

import android.content.Context;
import android.os.Build;
import com.amazon.minerva.client.thirdparty.MinervaServiceAndroidAdapter;
import com.amazon.minerva.client.thirdparty.api.impl.AmazonMinervaImpl;
import com.amazon.minerva.client.thirdparty.api.impl.AmazonMinervaV2Impl;
import com.amazon.minerva.client.thirdparty.compliance.ChildProfileVerifier;
import com.amazon.minerva.client.thirdparty.compliance.NonAnonymousCustomerIdProvider;
import com.amazon.minerva.client.thirdparty.compliance.UserControlVerifier;
import com.amazon.minerva.client.thirdparty.transport.OAuthProvider;
import com.amazon.minerva.client.thirdparty.utils.CustomDeviceUtil;
import j$.util.Objects;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AmazonMinervaAndroidClientBuilder {
    public static final String TAG = "AmazonMinervaAndroidClientBuilder";
    public static CustomDeviceUtil sCustomDeviceUtil;
    public final Context context;
    public ChildProfileVerifier mChildProfileVerifier;
    public NonAnonymousCustomerIdProvider mNonAnonymousCustomerIdProvider;
    public OAuthProvider mOAuthProvider;
    public UserControlVerifier mUserControlVerifier;
    public MinervaServiceAndroidAdapter minervaServiceAndroidAdapter;
    public String mRegion = "";
    public String mDeviceType = "";
    public String mNonAnonymousDeviceId = "";
    public boolean mIsUseDES = false;
    public boolean mIsRunInitialUpload = true;

    public AmazonMinervaAndroidClientBuilder(Context context) {
        this.context = context;
    }

    public static AmazonMinervaAndroidClientBuilder standard(Context context, String str) {
        Objects.requireNonNull(context, "parameter context can not be null.");
        Objects.requireNonNull(str, "customAppConfigId can not be null");
        CustomDeviceUtil customDeviceUtil = CustomDeviceUtil.getInstance();
        sCustomDeviceUtil = customDeviceUtil;
        if (!customDeviceUtil.isInitialized()) {
            sCustomDeviceUtil.initialize();
        }
        sCustomDeviceUtil.setAppConfigId(str);
        return new AmazonMinervaAndroidClientBuilder(context);
    }

    public AmazonMinerva build() {
        buildChecker();
        Context contextToUse = getContextToUse();
        getAndInitAdapter(contextToUse);
        return new AmazonMinervaImpl(contextToUse, this.minervaServiceAndroidAdapter, this.mRegion, this.mDeviceType);
    }

    public final void buildChecker() {
        if (this.mRegion.isEmpty()) {
            throw new IllegalStateException("withRegion must be called with a valid region before build.");
        }
        if (this.mDeviceType.isEmpty()) {
            throw new IllegalStateException("withDeviceType must be called with a valid deviceType before build.");
        }
        if (this.mOAuthProvider == null) {
            throw new IllegalStateException("withOAuthProvider must be called before build.");
        }
        if (this.mChildProfileVerifier == null) {
            throw new IllegalStateException("withChildProfileVerifier must be called before build.");
        }
        if (this.mUserControlVerifier == null) {
            throw new IllegalStateException("withUserControlVerifier must be called before build.");
        }
    }

    public AmazonMinervaV2 buildV2() {
        buildChecker();
        Context contextToUse = getContextToUse();
        getAndInitAdapter(contextToUse);
        return new AmazonMinervaV2Impl(contextToUse, this.minervaServiceAndroidAdapter, this.mRegion, this.mDeviceType);
    }

    public final void getAndInitAdapter(Context context) {
        MinervaServiceAndroidAdapter minervaServiceAndroidAdapter = MinervaServiceAndroidAdapter.getInstance();
        this.minervaServiceAndroidAdapter = minervaServiceAndroidAdapter;
        if (minervaServiceAndroidAdapter.isInitialized()) {
            return;
        }
        this.minervaServiceAndroidAdapter.initialize(context, this.mIsRunInitialUpload);
    }

    public final Context getContextToUse() {
        Context applicationContext = this.context.getApplicationContext();
        return (!this.mIsUseDES || Build.VERSION.SDK_INT < 24) ? applicationContext : applicationContext.createDeviceProtectedStorageContext();
    }

    public AmazonMinervaAndroidClientBuilder withChildProfileVerifier(ChildProfileVerifier childProfileVerifier) {
        Objects.requireNonNull(childProfileVerifier, "parameter childProfileVerifier can not be null.");
        this.mChildProfileVerifier = childProfileVerifier;
        sCustomDeviceUtil.setChildProfileVerifier(childProfileVerifier);
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withDeviceType(String str) {
        Objects.requireNonNull(str, "parameter deviceType can not be null.");
        this.mDeviceType = str;
        sCustomDeviceUtil.setDeviceType(str);
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withMarketplaceId(String str) {
        sCustomDeviceUtil.setMarketplaceId(str);
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withNonAnonymousCustomerIdProvider(NonAnonymousCustomerIdProvider nonAnonymousCustomerIdProvider) {
        Objects.requireNonNull(nonAnonymousCustomerIdProvider, "parameter nonAnonymousCustomerIdProvider can not be null.");
        this.mNonAnonymousCustomerIdProvider = nonAnonymousCustomerIdProvider;
        sCustomDeviceUtil.setCustomerIdProvider(nonAnonymousCustomerIdProvider);
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withNonAnonymousDeviceId(String str) {
        Objects.requireNonNull(str, "parameter nonAnonymousDeviceId can not be null.");
        this.mNonAnonymousDeviceId = str;
        sCustomDeviceUtil.setNonAnonymousDeviceId(str);
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withOAuthProvider(OAuthProvider oAuthProvider) {
        Objects.requireNonNull(oAuthProvider, "parameter oAuthProvider can not be null.");
        this.mOAuthProvider = oAuthProvider;
        sCustomDeviceUtil.setOAuthProvider(oAuthProvider);
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withRegion(String str) {
        Objects.requireNonNull(str, "parameter region can not be null.");
        this.mRegion = str;
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withRunInitialUpload(boolean z) {
        this.mIsRunInitialUpload = z;
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withUseDES(boolean z) {
        this.mIsUseDES = z;
        return this;
    }

    public AmazonMinervaAndroidClientBuilder withUserControlVerifier(UserControlVerifier userControlVerifier) {
        Objects.requireNonNull(userControlVerifier, "parameter userControlVerifier can not be null.");
        this.mUserControlVerifier = userControlVerifier;
        sCustomDeviceUtil.setUserControlVerifier(userControlVerifier);
        return this;
    }

    public static AmazonMinervaAndroidClientBuilder standard(Context context) {
        Objects.requireNonNull(context, "parameter context can not be null.");
        CustomDeviceUtil customDeviceUtil = CustomDeviceUtil.getInstance();
        sCustomDeviceUtil = customDeviceUtil;
        if (!customDeviceUtil.isInitialized()) {
            sCustomDeviceUtil.initialize();
        }
        return new AmazonMinervaAndroidClientBuilder(context);
    }
}
