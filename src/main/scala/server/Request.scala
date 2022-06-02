package server

import cats.Show

case class Request(
    method: String,
    url: String,
    httpVersion: String,
    headers: Map[String, String],
    body: Array[Byte]
)

object Request {
  val empty = new Request("", "", "", Map.empty, Array.empty)

  implicit val show: Show[Request] = Show.show { req =>
    s"""Request(
       |  method = ${req.method}
       |  url = ${req.url}
       |  httpVersion = ${req.httpVersion}
       |  headers = [
       |    ${req.headers.map { case (k, v) => s"$k: $v" }.mkString("\n    ")}
       |  ]
       |  body = ${new String(req.body)}
       |)""".stripMargin
  }
}