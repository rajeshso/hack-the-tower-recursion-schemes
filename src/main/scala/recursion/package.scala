import cats._
import cats.implicits._

package object recursion {

  def cata[F[_]: Functor, A](fix: Fix[F])(algebra: F[A] => A): A = {
    algebra(fix.unfix.map(ffix => cata(ffix)(algebra)))
  }

  def ana[F[_]: Functor, A](a: A)(coalgebra: A => F[A]): Fix[F] = {
    Fix(coalgebra(a).map(a => ana(a)(coalgebra)))
  }
}
