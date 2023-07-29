import com.typesafe.sbt.GitPlugin.autoImport.git
import com.typesafe.sbt.SbtNativePackager.autoImport.packageName
import com.typesafe.sbt.packager.docker.DockerAlias
import com.typesafe.sbt.packager.docker.DockerPlugin.autoImport.*
import sbt.{Def, *}
import sbt.Keys.*
import sbt.librarymanagement.*
import sbtrelease.ReleasePlugin.autoImport.releaseCommitMessage

object ReleaseSettings extends LibraryManagementSyntax {

  lazy val settings: Seq[Def.Setting[?]] = Seq(
    Docker / packageName := sys.env.getOrElse("DOCKER_IMAGE_NAME", name.value),
    dockerAlias := DockerAlias(
      dockerRepository.value,
      dockerUsername.value,
      packageName.value,
      Option(git.gitDescribedVersion.value.getOrElse((ThisBuild / version).value))
    ),
    packagedArtifacts := Map.empty,
    publish := (Docker / publish).value,
    publishMavenStyle := false,
    pomIncludeRepository := { _ => false },
    publishArtifact := false,
    publishTo := Some("releases" at "https://example.com/"), // Needs to be set by sbt-release plugin
    releaseCommitMessage := s"Setting version to ${(ThisBuild / version).value} [ci-skip]"
  )
}