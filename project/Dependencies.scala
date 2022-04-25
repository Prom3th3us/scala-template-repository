import sbt._

object Dependencies {

  object Versions {
    val scalaTestVersion      = "3.2.11"
  }
  object Testing {
    val scalaTest         = "org.scalatest" %% "scalatest"                     % Versions.scalaTestVersion      % Test
  }

}
