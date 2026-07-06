package com.amazon.ion.impl;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.UnknownSymbolException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class SymbolTokenImpl implements _Private_SymbolToken {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public final int mySid;
    public final String myText;

    public SymbolTokenImpl(String str, int i) {
        this.myText = str;
        this.mySid = i;
    }

    @Override // com.amazon.ion.SymbolToken
    public String assumeText() {
        String str = this.myText;
        if (str != null) {
            return str;
        }
        throw new UnknownSymbolException(this.mySid);
    }

    @Override // com.amazon.ion.impl._Private_SymbolToken
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SymbolToken)) {
            return false;
        }
        SymbolToken symbolToken = (SymbolToken) obj;
        return (this.myText == null || symbolToken.getText() == null) ? this.myText == symbolToken.getText() : this.myText.equals(symbolToken.getText());
    }

    @Override // com.amazon.ion.SymbolToken
    public int getSid() {
        return this.mySid;
    }

    @Override // com.amazon.ion.SymbolToken
    public String getText() {
        return this.myText;
    }

    @Override // com.amazon.ion.impl._Private_SymbolToken
    public int hashCode() {
        String str = this.myText;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SymbolToken::{text:");
        sb.append(this.myText);
        sb.append(",id:");
        return MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline1.m(sb, this.mySid, "}");
    }

    public SymbolTokenImpl(int i) {
        this.myText = null;
        this.mySid = i;
    }
}
