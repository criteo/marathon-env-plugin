organization := "com.criteo"
name := "marathon-env-plugin"
scalaVersion := "2.11.11"

resolvers += Resolver.sonatypeRepo("releases")
version := "1.0"

resolvers += "Mesosphere Public Repo" at "http://downloads.mesosphere.io/maven"

libraryDependencies += "mesosphere.marathon" %% "plugin-interface" % "1.5.1" % "provided"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"
libraryDependencies += "org.scalamock" %% "scalamock" % "4.0.0" % Test
