package org.apache.commons.text.lookup;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class XmlStringLookup extends AbstractStringLookup {
    public static final XmlStringLookup INSTANCE = new XmlStringLookup();

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split(AbstractStringLookup.SPLIT_STR);
        if (strArrSplit.length != 2) {
            throw IllegalArgumentExceptions.format("Bad XML key format [%s]; expected format is DocumentPath:XPath.", str);
        }
        String str2 = strArrSplit[0];
        String strSubstringAfter = substringAfter(str, ':');
        try {
            return XPathFactory.newInstance().newXPath().evaluate(strSubstringAfter, new InputSource(Files.newInputStream(Paths.get(str2, new String[0]), new OpenOption[0])));
        } catch (Exception e) {
            throw IllegalArgumentExceptions.format(e, "Error looking up XML document [%s] and XPath [%s].", str2, strSubstringAfter);
        }
    }
}
