package com.amazon.primevideo.nativebilling;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class BillingState {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ BillingState[] $VALUES;
    public static final BillingState IDLE = new BillingState("IDLE", 0);
    public static final BillingState PENDING_VALIDATION_REQUEST = new BillingState("PENDING_VALIDATION_REQUEST", 1);
    public static final BillingState PURCHASE_FLOW_IN_PROGRESS = new BillingState("PURCHASE_FLOW_IN_PROGRESS", 2);
    public static final BillingState SHOWN_ALTERNATIVE_BILLING_DIALOG = new BillingState("SHOWN_ALTERNATIVE_BILLING_DIALOG", 3);
    public static final BillingState REQUESTED_ANOTHER_BILLING_TOKEN = new BillingState("REQUESTED_ANOTHER_BILLING_TOKEN", 4);

    public static final /* synthetic */ BillingState[] $values() {
        return new BillingState[]{IDLE, PENDING_VALIDATION_REQUEST, PURCHASE_FLOW_IN_PROGRESS, SHOWN_ALTERNATIVE_BILLING_DIALOG, REQUESTED_ANOTHER_BILLING_TOKEN};
    }

    static {
        BillingState[] billingStateArr$values = $values();
        $VALUES = billingStateArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(billingStateArr$values);
    }

    public BillingState(String str, int i) {
    }

    @NotNull
    public static EnumEntries<BillingState> getEntries() {
        return $ENTRIES;
    }

    public static BillingState valueOf(String str) {
        return (BillingState) Enum.valueOf(BillingState.class, str);
    }

    public static BillingState[] values() {
        return (BillingState[]) $VALUES.clone();
    }
}
