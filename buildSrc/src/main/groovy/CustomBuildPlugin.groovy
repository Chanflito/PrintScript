import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.jvm.tasks.Jar

class CustomBuildPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.tasks.register('customBuild') {
            doLast {
                println "██████╗ ██████╗ ██╗███╗   ██╗████████╗███████╗ ██████╗██████╗ ██╗██████╗ ████████╗\n" +
                        "██╔══██╗██╔══██╗██║████╗  ██║╚══██╔══╝██╔════╝██╔════╝██╔══██╗██║██╔══██╗╚══██╔══╝\n" +
                        "██████╔╝██████╔╝██║██╔██╗ ██║   ██║   ███████╗██║     ██████╔╝██║██████╔╝   ██║   \n" +
                        "██╔═══╝ ██╔══██╗██║██║╚██╗██║   ██║   ╚════██║██║     ██╔══██╗██║██╔═══╝    ██║   \n" +
                        "██║     ██║  ██║██║██║ ╚████║   ██║   ███████║╚██████╗██║  ██║██║██║        ██║   \n" +
                        "╚═╝     ╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝ ╚═════╝╚═╝  ╚═╝╚═╝╚═╝        ╚═╝   \n" +
                        "v1.0.0                                                                              \n" +
                        "                                                                                  "
                println "Performing custom build..."

                // Execute the build tasks for all subprojects
                project.subprojects { subproject ->
                    println "\nBuilding ${subproject.name}..."
                    subproject.tasks.withType(JavaCompile).each { compileTask ->
                        println "  Compiling source files for ${subproject.name}..."
                    }
                    subproject.tasks.withType(Test).each { testTask ->
                        println "  Running tests for ${subproject.name}..."
                    }
                    subproject.tasks.withType(Jar).each { jarTask ->
                        println "  Packaging JAR file for ${subproject.name}..."
                    }
                }
            }
        }
    }
}
