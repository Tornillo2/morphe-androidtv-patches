package com.amazon.ignitionshared;

import android.content.Context;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class TokenMigration {
    public static boolean clearKindleTokensTable(Context context) {
        try {
            new MapSqliteHelper(context).clearTokensTable();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String getKindleAppRefreshToken(Context context) {
        return new MapSqliteHelper(context).getLegacyRefreshToken("A3EFHJ9BGBJ8L2");
    }
}
