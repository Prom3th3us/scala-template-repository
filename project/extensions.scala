import com.typesafe.sbt.packager.archetypes.scripts.AshScriptPlugin
import com.typesafe.sbt.packager.docker.DockerPlugin
import sbt.Keys._
import sbt._
import scalafix.sbt.ScalafixPlugin.autoImport.{scalafixOnCompile, scalafixSemanticdb}

object extensions {

  implicit class implicit_instances(project: Project) {

    object opinions {

      def shouldHaveScalafix: Project =
        project.settings(
          addCompilerPlugin(scalafixSemanticdb),
          semanticdbEnabled := true,
          scalafixOnCompile := true
        )

      def shouldHaveForking: Project =
        project.settings(
          run / fork := true,
          Test / fork := true
        )

      def shouldHaveDocker: Project =
        project.enablePlugins(DockerPlugin, AshScriptPlugin)

      def shouldHaveDynamicVersioning: Project =
        project.settings{
          import sbt.inputKey
          val versionBump =
            inputKey[Unit](
              """
                |ie.: sbt "versionBump major"
                |ie.: sbt "versionBump minor"
                |ie.: sbt "versionBump patch"
                |""".stripMargin)
          versionBump := {
            import complete.DefaultParsers._
            new VersionBump(
              currentVersion = version.value
            ).apply(
              arg = spaceDelimited("<arg>").parsed.headOption
            )
          }
        }

      def shouldUseJava11: Project =
        project.settings(
          javacOptions ++= Seq("-source", "11", "-target", "11"),
        )

    }
    def withOpinionatedDefaults: Project =
      project
        .opinions.shouldHaveDocker
        .opinions.shouldHaveForking
        .opinions.shouldHaveScalafix
        .opinions.shouldHaveDynamicVersioning
        .opinions.shouldUseJava11
  }
}
