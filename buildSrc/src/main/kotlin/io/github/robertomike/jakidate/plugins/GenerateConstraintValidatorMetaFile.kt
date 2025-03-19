package io.github.robertomike.jakidate.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.withType

open class GenerateConstraintValidatorMetaFileExtension {
    var packagePath: String? = null
    var excludedClasses: Set<String> = setOf()
}

class GenerateConstraintValidatorMetaFile: Plugin<Project> {
    override fun apply(project: Project) {
        val config = project.extensions.create("generateConstraintValidatorMetaFile", GenerateConstraintValidatorMetaFileExtension::class.java)

        val layout = project.layout
        // This is necessary because on linux take the base dir of the machine
        val buildDirObject = layout.buildDirectory
        val buildDir = buildDirObject.get().asFile.path


        project.tasks.withType<Jar>().configureEach {
            dependsOn("generateConstraintValidatorMetaFile")
        }

        project.tasks.register("generateConstraintValidatorMetaFile") {
            println("Starting generation of Constraint Validator Meta file")

            val packagePath = config.packagePath ?: throw RuntimeException("packagePath is required")

            val classesDir = layout.buildDirectory
                .dir("$buildDir/classes/kotlin/main/${packagePath.replace('\\', '/')}").get().asFile

            // Create a FileCollection of class files
            val classFiles = classesDir.walk().filter {
                it.name.endsWith("Constraint.class") && !it.path.contains("\$") && !config.excludedClasses.contains(it.name.replace(".class", ""))
            }


            val startPath = buildDirObject.file("$buildDir/resources/main/META-INF/services")
            startPath.get().asFile.mkdirs()
            val propertiesFileOnResource = buildDirObject.file("$buildDir/resources/main/META-INF/services/javax.validation.ConstraintValidator")
            propertiesFileOnResource.get().asFile.createNewFile()
            propertiesFileOnResource.get().asFile.outputStream().use {
                val packagePathDot = packagePath.replace('\\', '.')

                classFiles.forEach { clazz ->
                    var name = clazz.path.replace(".class", "")
                    name = name.split(packagePath).last()
                    name = name.replace('\\', '.')

                    it.write("$packagePathDot${name}\n".toByteArray())
                }
            }
        }

    }
}