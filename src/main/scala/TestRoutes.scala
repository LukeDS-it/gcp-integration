package gcptest

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directives, Route}
import buildinfo.BuildInfo

class TestRoutes extends Directives {

  def route: Route = pathPrefix("") {
    pathEnd {
      get {
        complete((StatusCodes.OK, BuildInfo.version))
      }
    }
  }

}
