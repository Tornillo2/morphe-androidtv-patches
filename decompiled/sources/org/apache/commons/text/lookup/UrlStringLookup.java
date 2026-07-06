package org.apache.commons.text.lookup;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class UrlStringLookup extends AbstractStringLookup {
    public static final UrlStringLookup INSTANCE = new UrlStringLookup();

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split(AbstractStringLookup.SPLIT_STR);
        if (strArrSplit.length < 2) {
            throw IllegalArgumentExceptions.format("Bad URL key format [%s]; expected format is DocumentPath:Key.", str);
        }
        String str2 = strArrSplit[0];
        String strSubstringAfter = substringAfter(str, ':');
        try {
            URL url = new URL(strSubstringAfter);
            StringWriter stringWriter = new StringWriter(8192);
            char[] cArr = new char[8192];
            InputStreamReader inputStreamReader = new InputStreamReader(new BufferedInputStream(url.openStream()), str2);
            while (true) {
                try {
                    int i = inputStreamReader.read(cArr);
                    if (-1 == i) {
                        inputStreamReader.close();
                        return stringWriter.toString();
                    }
                    stringWriter.write(cArr, 0, i);
                } finally {
                }
            }
        } catch (Exception e) {
            throw IllegalArgumentExceptions.format(e, "Error looking up URL [%s] with Charset [%s].", strSubstringAfter, str2);
        }
    }
}
