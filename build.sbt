name := "play-jooq-sample"

version := "1.0"

lazy val `palmmagic` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "org.jooq" % "jooq" % "3.8.2",
  "org.jooq" % "jooq-meta" % "3.8.2",
  "org.jooq" % "jooq-codegen" % "3.8.2",
  "org.projectlombok" % "lombok" % "1.16.8",
  "mysql" % "mysql-connector-java" % "5.1.38",
  "com.zaxxer" % "HikariCP" % "2.4.6",
  "junit" % "junit" % "4.12")

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "bintray-sbt-plugins" at "https://dl.bintray.com/sbt/sbt-plugin-releases/"

routesGenerator := InjectedRoutesGenerator
