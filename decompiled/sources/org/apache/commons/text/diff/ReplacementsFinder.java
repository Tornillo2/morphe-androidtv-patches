package org.apache.commons.text.diff;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ReplacementsFinder<T> implements CommandVisitor<T> {
    public final ReplacementsHandler<T> handler;
    public final List<T> pendingInsertions = new ArrayList();
    public final List<T> pendingDeletions = new ArrayList();
    public int skipped = 0;

    public ReplacementsFinder(ReplacementsHandler<T> replacementsHandler) {
        this.handler = replacementsHandler;
    }

    @Override // org.apache.commons.text.diff.CommandVisitor
    public void visitDeleteCommand(T t) {
        this.pendingDeletions.add(t);
    }

    @Override // org.apache.commons.text.diff.CommandVisitor
    public void visitInsertCommand(T t) {
        this.pendingInsertions.add(t);
    }

    @Override // org.apache.commons.text.diff.CommandVisitor
    public void visitKeepCommand(T t) {
        if (this.pendingDeletions.isEmpty() && this.pendingInsertions.isEmpty()) {
            this.skipped++;
            return;
        }
        this.handler.handleReplacement(this.skipped, this.pendingDeletions, this.pendingInsertions);
        this.pendingDeletions.clear();
        this.pendingInsertions.clear();
        this.skipped = 1;
    }
}
