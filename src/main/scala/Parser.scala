package io.github.torsteinvik.oeisgloballyrecursivemult

import scala.util.parsing.combinator._
import scala.util.parsing.input._
import scala.io.Source

object Parser extends RegexParsers {
    
    def parseFile (src : Source) = getFormulae(new CharSequenceReader(src.mkString))
    
    def getFormulae (in : Input) : Seq[(OEIS, Seq[PolynomialTree])] = parseAll(formulae, in) match {
        case Success(x, _) => x
        case Failure(msg, next) => throw new Exception("Error at line " + next.pos.line + " column " + next.pos.column + "\n" + next.pos.longString + "\n" + msg)
        case Error(msg, next) => throw new Exception("Error at line " + next.pos.line + " column " + next.pos.column + "\n" + next.pos.longString + "\n" + msg)
    }

    def formulae : Parser[Seq[(OEIS, Seq[PolynomialTree])]] = (pairwrapped).*

    def pairwrapped : Parser[(OEIS, Seq[PolynomialTree])] = oeiswrapped ~ formulaswrapped ^^ {case x ~ y => (x, y)}
    
    def oeiswrapped : Parser[OEIS] = "def " ~> oeis <~ "():" 
    def oeis : Parser[OEIS] = """A\d{6}""".r ^^ {str => println(str); OEIS(str)}
    
}
