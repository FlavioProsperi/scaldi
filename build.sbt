name := "scaldi"

description := "Scaldi - Scala Dependency Injection Library"

organization := "org.scaldi"

version := "0.3.3-SNAPSHOT"

crossScalaVersions := Seq("2.10.4", "2.11.0")

scalaVersion := "2.11.0"

scalacOptions += "-deprecation"

fork := true

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.1.3" % "test",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
) ++ (
  // `scala-xml` is introduced in 2.11 so we need to exclude it,
  // if cross-compiling to 2.10
  if (scalaVersion.value.substring(2, 4).toInt >= 11)
    Seq("org.scala-lang.modules" %% "scala-xml" % "1.0.1" % "test")
  else
    Nil
)

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { x => false }

publishTo <<= version { v: String =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

// nice prompt!
shellPrompt in ThisBuild := { state =>
  scala.Console.MAGENTA + Project.extract(state).currentRef.project + "> " + scala.Console.RESET
}

git.remoteRepo := "git@github.com:scaldi/scaldi.git"

site.settings

site.includeScaladoc()

ghpages.settings

instrumentSettings

pomExtra := <xml:group>
  <url>http://scaldi.org</url>
  <inceptionYear>2011</inceptionYear>
  <licenses>
    <license>
      <name>Apache License, ASL Version 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <developers>
    <developer>
      <id>OlegIlyenko</id>
      <name>Oleg Ilyenko</name>
    </developer>
  </developers>
  <issueManagement>
    <system>GitHub</system>
    <url>http://github.com/scaldi/scaldi/issues</url>
  </issueManagement>
  <scm>
    <connection>scm:git:git@github.com:scaldi/scaldi.git</connection>
    <url>git@github.com:scaldi/scaldi.git</url>
  </scm>
</xml:group>
