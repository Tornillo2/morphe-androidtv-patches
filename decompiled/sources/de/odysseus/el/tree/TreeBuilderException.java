package de.odysseus.el.tree;

import de.odysseus.el.misc.LocalMessages;
import javax.el.ELException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class TreeBuilderException extends ELException {
    public static final long serialVersionUID = 1;
    public final String encountered;
    public final String expected;
    public final String expression;
    public final int position;

    public TreeBuilderException(String str, int i, String str2, String str3, String str4) {
        super(LocalMessages.get("error.build", str, str4));
        this.expression = str;
        this.position = i;
        this.encountered = str2;
        this.expected = str3;
    }

    public String getEncountered() {
        return this.encountered;
    }

    public String getExpected() {
        return this.expected;
    }

    public String getExpression() {
        return this.expression;
    }

    public int getPosition() {
        return this.position;
    }
}
