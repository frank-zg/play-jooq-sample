import play.api.Logger
import play.api.http.HttpErrorHandler
import play.api.mvc.{RequestHeader, Results}

/**
  * Created by zg on 2016/6/21.
  */
class ErrorHandler extends HttpErrorHandler {
  override def onClientError(request: RequestHeader, statusCode: Int, message: String) = {
    Logger.warn("request errorCode: " + statusCode.toString)
    scala.concurrent.Future.successful {
      Results.Status(statusCode)("A client error occurred: " + statusCode)
    }
  }

  override def onServerError(request: RequestHeader, exception: Throwable) = {
    scala.concurrent.Future.successful {
      Results.InternalServerError("A server error occurred: " + exception.getMessage)
    }
  }
}
