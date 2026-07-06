package org.apache.commons.text.lookup;

import j$.util.Objects;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class ScriptStringLookup extends AbstractStringLookup {
    public static final ScriptStringLookup INSTANCE = new ScriptStringLookup();

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split(AbstractStringLookup.SPLIT_STR);
        if (strArrSplit.length != 2) {
            throw IllegalArgumentExceptions.format("Bad script key format [%s]; expected format is DocumentPath:Key.", str);
        }
        String str2 = strArrSplit[0];
        String str3 = strArrSplit[1];
        try {
            ScriptEngine engineByName = new ScriptEngineManager().getEngineByName(str2);
            if (engineByName != null) {
                return Objects.toString(engineByName.eval(str3), null);
            }
            throw new IllegalArgumentException("No script engine named " + str2);
        } catch (Exception e) {
            throw IllegalArgumentExceptions.format(e, "Error in script engine [%s] evaluating script [%s].", str2, str3);
        }
    }
}
