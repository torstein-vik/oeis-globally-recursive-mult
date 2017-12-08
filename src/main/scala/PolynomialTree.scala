package io.github.torsteinvik.oeisgloballyrecursivemult

abstract sealed class PolynomialTree {
    import PolynomialTree._
    def :* (right : PolynomialTree) = new :*(this, right)
    def :** (exp : Int) = new :**(this, exp)
    def :+ (right : PolynomialTree) = new :+(this, right)
    def :- (right : PolynomialTree) = new :-(this, right)
}

object PolynomialTree {
    case class :*  (left : PolynomialTree, right : PolynomialTree) extends PolynomialTree
    case class :** (base : PolynomialTree, exp : Int)              extends PolynomialTree
    case class :+  (left : PolynomialTree, right : PolynomialTree) extends PolynomialTree
    case class :-  (left : PolynomialTree, right : PolynomialTree) extends PolynomialTree

    case class Number(num : Int) extends PolynomialTree
    case object Variable extends PolynomialTree
}
