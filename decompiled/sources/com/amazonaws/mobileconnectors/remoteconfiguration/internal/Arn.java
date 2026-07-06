package com.amazonaws.mobileconnectors.remoteconfiguration.internal;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class Arn {
    public final String account;
    public final String id;
    public final String region;
    public final String type;
    public final String vendor;

    public Arn(String str, String str2, String str3, String str4, String str5) {
        this.vendor = str;
        this.region = str2;
        this.account = str3;
        this.type = str4;
        this.id = str5;
    }

    public static Arn fromArn(String str) {
        String[] strArrSplit = str.split(":", 6);
        if (strArrSplit.length != 6) {
            throw new IllegalArgumentException("Invalid ARN");
        }
        String[] strArrSplit2 = strArrSplit[5].split("[:/]", 2);
        return new Arn(strArrSplit[2], strArrSplit[3], strArrSplit[4], strArrSplit2[0], strArrSplit2[1]);
    }

    public String getAccount() {
        return this.account;
    }

    public String getId() {
        return this.id;
    }

    public String getRegion() {
        return this.region;
    }

    public String getType() {
        return this.type;
    }

    public String getVendor() {
        return this.vendor;
    }

    public String toArn() {
        return String.format("arn:aws:%s:%s:%s:%s:%s", this.vendor, this.region, this.account, this.type, this.id);
    }
}
