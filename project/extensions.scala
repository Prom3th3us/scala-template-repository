import com.typesafe.sbt.packager.archetypes.scripts.AshScriptPlugin
import com.typesafe.sbt.packager.docker.DockerPlugin
import sbt.Keys._
import sbt._
import scalafix.sbt.ScalafixPlugin.autoImport.{scalafixOnCompile, scalafixSemanticdb}

object extensions {

  implicit class implicit_instances(project: Project) {
    def withScalafix: Project =
      project.settings(
        addCompilerPlugin(scalafixSemanticdb),
        semanticdbEnabled := true,
        scalafixOnCompile := true
      )


    def withForking: Project =
      project.settings(
        run / fork := true,
        Test / fork := true
      )

    def withDocker: Project =
      project.enablePlugins(DockerPlugin, AshScriptPlugin)

  }
}
