package de.odysseus.el.tree.impl;

import de.odysseus.el.tree.Bindings;
import de.odysseus.el.tree.NodePrinter;
import de.odysseus.el.tree.Tree;
import de.odysseus.el.tree.TreeBuilder;
import de.odysseus.el.tree.TreeBuilderException;
import de.odysseus.el.tree.impl.Parser;
import de.odysseus.el.tree.impl.Scanner;
import java.io.PrintWriter;
import java.util.EnumSet;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ELResolver;
import javax.el.FunctionMapper;
import javax.el.VariableMapper;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class Builder implements TreeBuilder {
    public static final long serialVersionUID = 1;
    public final EnumSet<Feature> features;

    /* JADX INFO: renamed from: de.odysseus.el.tree.impl.Builder$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class AnonymousClass1 extends ELContext {
        @Override // javax.el.ELContext
        public ELResolver getELResolver() {
            return null;
        }

        @Override // javax.el.ELContext
        public FunctionMapper getFunctionMapper() {
            return null;
        }

        @Override // javax.el.ELContext
        public VariableMapper getVariableMapper() {
            return null;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Feature {
        METHOD_INVOCATIONS,
        NULL_PROPERTIES,
        VARARGS,
        IGNORE_RETURN_TYPE
    }

    public Builder() {
        this.features = EnumSet.noneOf(Feature.class);
    }

    public static void main(String[] strArr) {
        Tree treeBuild;
        if (strArr.length != 1) {
            System.err.println("usage: java " + Builder.class.getName() + " <expression string>");
            System.exit(1);
        }
        PrintWriter printWriter = new PrintWriter(System.out);
        try {
            treeBuild = new Builder(Feature.METHOD_INVOCATIONS).build(strArr[0]);
        } catch (TreeBuilderException e) {
            System.out.println(e.getMessage());
            System.exit(0);
            treeBuild = null;
        }
        NodePrinter.dump(printWriter, treeBuild.getRoot());
        if (!treeBuild.getFunctionNodes().iterator().hasNext() && !treeBuild.getIdentifierNodes().iterator().hasNext()) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            printWriter.print(">> ");
            try {
                printWriter.println(treeBuild.getRoot().getValue(new Bindings(null, null), anonymousClass1, null));
            } catch (ELException e2) {
                printWriter.println(e2.getMessage());
            }
        }
        printWriter.flush();
    }

    @Override // de.odysseus.el.tree.TreeBuilder
    public Tree build(String str) throws TreeBuilderException {
        String str2;
        try {
            try {
            } catch (Scanner.ScanException e) {
                e = e;
                str2 = str;
            }
            try {
                return createParser(str).tree();
            } catch (Scanner.ScanException e2) {
                e = e2;
                str2 = str;
                throw new TreeBuilderException(str2, e.position, e.encountered, e.expected, e.getMessage());
            }
        } catch (Parser.ParseException e3) {
            throw new TreeBuilderException(str, e3.position, e3.encountered, e3.expected, e3.getMessage());
        }
    }

    public Parser createParser(String str) {
        return new Parser(this, str);
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this.features.equals(((Builder) obj).features);
    }

    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean isEnabled(Feature feature) {
        return this.features.contains(feature);
    }

    public Builder(Feature... featureArr) {
        if (featureArr != null && featureArr.length != 0) {
            if (featureArr.length == 1) {
                this.features = EnumSet.of(featureArr[0]);
                return;
            }
            Feature[] featureArr2 = new Feature[featureArr.length - 1];
            for (int i = 1; i < featureArr.length; i++) {
                featureArr2[i - 1] = featureArr[i];
            }
            this.features = EnumSet.of(featureArr[0], featureArr2);
            return;
        }
        this.features = EnumSet.noneOf(Feature.class);
    }
}
