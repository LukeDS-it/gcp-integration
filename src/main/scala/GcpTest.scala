package gcptest

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import com.typesafe.scalalogging.LazyLogging

import scala.util.{Failure, Success}

object GcpTest extends App with LazyLogging {

  private def startHttpServer(routes: Route)(implicit system: ActorSystem[?]): Unit = {
    import system.executionContext

    Http()
      .newServerAt("0.0.0.0", 8080)
      .bind(routes)
      .onComplete {
        case Success(binding)   => logger.info(s"Server started at ${binding.localAddress.getAddress}:${binding.localAddress.getPort}")
        case Failure(exception) => logger.error("Failed to start server", exception)
      }
  }

  private val rootBehavior = Behaviors.setup[Nothing] { context =>
    val routes = new TestRoutes()
    startHttpServer(routes.route)(context.system)

    Behaviors.empty
  }

  ActorSystem[Nothing](rootBehavior, "gcp-test-server")

}
