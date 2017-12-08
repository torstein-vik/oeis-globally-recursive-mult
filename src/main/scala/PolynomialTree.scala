package io.github.torsteinvik.oeisgloballyrecursivemult

abstract sealed class PolynomialTree

object PolynomialTree {
    case class :*:  (left : PolynomialTree, right : PolynomialTree) extends PolynomialTree
    case class :**: (base : PolynomialTree, exp : Int)              extends PolynomialTree
    case class :+:  (left : PolynomialTree, right : PolynomialTree) extends PolynomialTree
    case class :-:  (left : PolynomialTree, right : PolynomialTree) extends PolynomialTree

}
