package com.amazon.minerva.client.thirdparty.api;

import android.content.Context;
import com.amazon.minerva.client.thirdparty.api.provider.CustomerInfoProvider;
import com.amazon.minerva.client.thirdparty.compliance.ChildProfileVerifier;
import com.amazon.minerva.client.thirdparty.compliance.NonAnonymousCustomerIdProvider;
import com.amazon.minerva.client.thirdparty.compliance.UserControlVerifier;
import com.amazon.minerva.client.thirdparty.transport.OAuthProvider;
import com.amazon.minerva.client.thirdparty.utils.CustomDeviceUtil;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class AmazonMinervaServiceInitializer {
    public static AmazonMinervaServiceInitializer INSTANCE = new AmazonMinervaServiceInitializer();
    public AtomicBoolean mIsInitialized = new AtomicBoolean(false);

    public static AmazonMinervaServiceInitializer getInstance() {
        return INSTANCE;
    }

    public synchronized void initialize(Context context, String str, OAuthProvider oAuthProvider, ChildProfileVerifier childProfileVerifier, UserControlVerifier userControlVerifier) {
        initialize(context, str, oAuthProvider, childProfileVerifier, userControlVerifier, null);
    }

    public boolean isInitialized() {
        return this.mIsInitialized.get();
    }

    public synchronized void initialize(Context context, String str, OAuthProvider oAuthProvider, ChildProfileVerifier childProfileVerifier, UserControlVerifier userControlVerifier, String str2) {
        initialize(context, str, oAuthProvider, childProfileVerifier, userControlVerifier, str2, null);
    }

    public synchronized void initialize(Context context, String str, OAuthProvider oAuthProvider, ChildProfileVerifier childProfileVerifier, UserControlVerifier userControlVerifier, String str2, NonAnonymousCustomerIdProvider nonAnonymousCustomerIdProvider) {
        initialize(context, str, oAuthProvider, childProfileVerifier, userControlVerifier, str2, nonAnonymousCustomerIdProvider, null);
    }

    public synchronized void initialize(Context context, String str, OAuthProvider oAuthProvider, ChildProfileVerifier childProfileVerifier, UserControlVerifier userControlVerifier, String str2, NonAnonymousCustomerIdProvider nonAnonymousCustomerIdProvider, String str3) {
        initialize(context, str, oAuthProvider, childProfileVerifier, userControlVerifier, str2, nonAnonymousCustomerIdProvider, str3, null);
    }

    public synchronized void initialize(Context context, String str, OAuthProvider oAuthProvider, ChildProfileVerifier childProfileVerifier, UserControlVerifier userControlVerifier, String str2, NonAnonymousCustomerIdProvider nonAnonymousCustomerIdProvider, String str3, String str4) {
        initialize(context, str, oAuthProvider, childProfileVerifier, userControlVerifier, str2, nonAnonymousCustomerIdProvider, str3, str4, null);
    }

    public synchronized void initialize(Context context, String str, OAuthProvider oAuthProvider, ChildProfileVerifier childProfileVerifier, UserControlVerifier userControlVerifier, String str2, NonAnonymousCustomerIdProvider nonAnonymousCustomerIdProvider, String str3, String str4, CustomerInfoProvider customerInfoProvider) {
        if (str != null) {
            try {
                if (!str.isEmpty()) {
                    if (oAuthProvider == null) {
                        throw new IllegalStateException("OAuthProvider cannot be null");
                    }
                    if (childProfileVerifier == null) {
                        throw new IllegalStateException("ChildProfileVerifier cannot be null");
                    }
                    if (userControlVerifier != null) {
                        if (this.mIsInitialized.compareAndSet(false, true)) {
                            CustomDeviceUtil customDeviceUtil = CustomDeviceUtil.getInstance();
                            if (!customDeviceUtil.isInitialized()) {
                                customDeviceUtil.initialize();
                            }
                            customDeviceUtil.setDeviceTypeInitializer(str);
                            customDeviceUtil.setOAuthProviderInitializer(oAuthProvider);
                            customDeviceUtil.setChildProfileVerifierInitializer(childProfileVerifier);
                            customDeviceUtil.setUserControlVerifierInitializer(userControlVerifier);
                            if (nonAnonymousCustomerIdProvider != null) {
                                customDeviceUtil.setCustomerIdProviderInitializer(nonAnonymousCustomerIdProvider);
                            }
                            if (str3 != null) {
                                customDeviceUtil.setNonAnonymousDeviceIdInitializer(str3);
                            }
                            if (str4 != null) {
                                customDeviceUtil.setMarketplaceIdInitializer(str4);
                            }
                            if (customerInfoProvider != null) {
                                customDeviceUtil.setCustomerInfoProviderInitializer(customerInfoProvider);
                            }
                        }
                    } else {
                        throw new IllegalStateException("User control verifier cannot be null");
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        throw new IllegalStateException("Non-null and Non-empty device type required");
    }
}
