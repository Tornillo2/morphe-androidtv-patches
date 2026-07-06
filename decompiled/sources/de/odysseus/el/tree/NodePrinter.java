package de.odysseus.el.tree;

import com.amazon.minerva.identifiers.schemaid.MetricSchemaUUID;
import java.io.PrintWriter;
import java.util.Stack;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class NodePrinter {
    public static void dump(PrintWriter printWriter, Node node, Stack<Node> stack) {
        Node node2 = null;
        if (!stack.isEmpty()) {
            Node node3 = null;
            for (Node node4 : stack) {
                if (isLastSibling(node4, node3)) {
                    printWriter.print("   ");
                } else {
                    printWriter.print("|  ");
                }
                node3 = node4;
            }
            printWriter.println(MetricSchemaUUID.METRIC_COMPOSITE_ID_DELIMITER);
        }
        for (Node node5 : stack) {
            if (isLastSibling(node5, node2)) {
                printWriter.print("   ");
            } else {
                printWriter.print("|  ");
            }
            node2 = node5;
        }
        printWriter.print("+- ");
        printWriter.println(node.toString());
        stack.push(node);
        for (int i = 0; i < node.getCardinality(); i++) {
            dump(printWriter, node.getChild(i), stack);
        }
        stack.pop();
    }

    public static boolean isLastSibling(Node node, Node node2) {
        return node2 == null || node == node2.getChild(node2.getCardinality() - 1);
    }

    public static void dump(PrintWriter printWriter, Node node) {
        dump(printWriter, node, new Stack());
    }
}
