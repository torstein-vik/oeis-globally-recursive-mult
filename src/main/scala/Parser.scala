package io.github.torsteinvik.oeisgloballyrecursivemult

import scala.util.parsing.combinator._
import scala.util.parsing.input._
import scala.io.Source

object Parser extends RegexParsers {
    
    
    def getFormulae (in : Input) : Seq[(OEIS, Recursion)] = parseAll(formulae, in) match {
        case Success(x, _) => x
        case Failure(msg, next) => throw new Exception("Error at line " + next.pos.line + " column " + next.pos.column + "\n" + next.pos.longString + "\n" + msg)
        case Error(msg, next) => throw new Exception("Error at line " + next.pos.line + " column " + next.pos.column + "\n" + next.pos.longString + "\n" + msg)
    }

    def formulae : Parser[Seq[(OEIS, Recursion)]] = ???
    
}