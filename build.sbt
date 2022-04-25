import Dependencies.Testing
import extensions._

lazy val root = (project in file("."))
  .settings(
    scalaVersion     := "2.13.8",
    name := "template",
    organization     := "com.example",
    organizationName := "example",
    libraryDependencies ++= Testing.apply
  )
  .withOpinionatedDefaults

