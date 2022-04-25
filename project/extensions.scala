import com.typesafe.sbt.packager.archetypes.scripts.AshScriptPlugin
import com.typesafe.sbt.packager.docker.DockerPlugin
import sbt.Keys._
import sbt._
import sbtdynver.DynVerPlugin.autoImport.dynverSeparator
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
        project.settings(
          versionScheme   := Some("early-semver"),
          dynverSeparator := "-",
          conflictManager := ConflictManager.latestRevision
        )


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
