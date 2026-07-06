package org.apache.commons.text.lookup;

import java.util.Date;
import org.apache.commons.lang3.time.FastDateFormat;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class DateStringLookup extends AbstractStringLookup {
    public static final DateStringLookup INSTANCE = new DateStringLookup();

    public final String formatDate(long j, String str) {
        FastDateFormat fastDateFormat;
        if (str != null) {
            try {
                fastDateFormat = FastDateFormat.getInstance(str);
            } catch (Exception e) {
                throw IllegalArgumentExceptions.format(e, "Invalid date format: [%s]", str);
            }
        } else {
            fastDateFormat = null;
        }
        if (fastDateFormat == null) {
            fastDateFormat = FastDateFormat.getInstance();
        }
        return fastDateFormat.format(new Date(j));
    }

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        return formatDate(System.currentTimeMillis(), str);
    }
}
