
// Packing and Publishing
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.8.1")
addSbtPlugin("com.mintbeans"    % "sbt-ecr"             % "0.16.0")

// Linting & Styling
addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat" % "0.1.20")
addSbtPlugin("org.scalameta"             % "sbt-scalafmt" % "2.4.3")
addSbtPlugin("ch.epfl.scala"             % "sbt-scalafix" % "0.9.34")

addDependencyTreePlugin
