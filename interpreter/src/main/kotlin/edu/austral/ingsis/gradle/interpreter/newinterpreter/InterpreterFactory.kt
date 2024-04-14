package edu.austral.ingsis.gradle.interpreter.newinterpreter

import edu.austral.ingsis.gradle.common.ast.newast.AST
import java.io.File
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

class InterpreterFactory private constructor() {
    companion object {
        private val instance: InterpreterFactory by lazy { InterpreterFactory() }

        fun internalGetInstance(): InterpreterFactory {
            return instance
    }
    }

    inline fun <reified T : Interpreter> createInterpreter(node: AST, vararg args: Any): T {
        val implementingClasses = findImplementingClasses(T::class)
        for (clazz in implementingClasses) {
            val constructor = clazz.primaryConstructor
            if (constructor != null && constructor.parameters.size == args.size +1) {
                try {
                    val interpreter = constructor.call(node, *args) as T
                    if (interpreter.canInterpret(node)) {
                        return interpreter
                    }
                } catch (e: Exception) {
                    // Log or handle any exceptions
                }
            }
        }
        throw IllegalArgumentException("No suitable interpreter found for the AST node of type ${node::class.simpleName}")
    }

    fun findImplementingClasses(interfaceClass: KClass<out Interpreter>): List<KClass<out Interpreter>> {
        val implementingClasses = mutableListOf<KClass<out Interpreter>>()
        val interfacePackage = interfaceClass.java.`package`
        val packageName = interfacePackage?.name ?: return emptyList() // Check if package is accessible

        val classLoader = Thread.currentThread().contextClassLoader
        val classes = classLoader.getResources(packageName.replace('.', '/'))
        while (classes.hasMoreElements()) {
            val url = classes.nextElement()
            val file = File(url.file)
            if (file.isDirectory) {
                val classNames = file.walkTopDown()
                    .filter { it.isFile }
                    .map { it.relativeTo(file).path.replace(File.separator, ".").removeSuffix(".class") }
                classNames.forEach { className ->
                    try {
                        val clazz = Class.forName("$packageName.$className").kotlin
                        if (interfaceClass.java.isAssignableFrom(clazz.java) && clazz != interfaceClass) {
                            implementingClasses.add(clazz as KClass<out Interpreter>)
                        }
                    } catch (e: ClassNotFoundException) {
                        // Log or handle class not found exceptions
                    }
                }
            }
        }
        return implementingClasses
    }
}

