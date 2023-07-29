import com.typesafe.sbt.packager.docker.DockerPlugin.autoImport.*
import com.typesafe.sbt.packager.linux.LinuxKeys
import sbt.*

object DockerSettings extends LinuxKeys {

  lazy val settings: Seq[Def.Setting[?]] = Seq(
    dockerExposedPorts := Seq(8080),
    dockerBaseImage := "openjdk:11"
  )

}
