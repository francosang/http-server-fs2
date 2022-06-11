package fakes

import cats.Show
import fs2.{Pipe, Stream}
import server.{Pipes, Request}

trait FakePipes {
  def oneRequest[F[_]](r: Request): Pipes[F] = multipleRequests(List(r))

  def multipleRequests[F[_]](rs: List[Request]): Pipes[F] = new Pipes[F] {
    override def log[A: Show](label: String): Pipe[F, A, A] = x => x
    override def requests: Pipe[F, Byte, Request] = _ => Stream.emits(rs)
  }
}
