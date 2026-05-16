package patches.core

class ClassContext(private val className: String) {

    fun forMethod(methodName: String, block: MethodContext.() -> Unit) {
        val methodContext = MethodContext(methodName)
        methodContext.block()
    }
}
