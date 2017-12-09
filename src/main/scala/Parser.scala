package io.github.torsteinvik.oeisgloballyrecursivemult

import scala.util.parsing.combinator._
import scala.util.parsing.input._
import scala.io.Source

object Parser extends RegexParsers {
    
    def parseFile (formulalist : Set[OEIS])(src : Source) = getFormulae(formulalist)(new CharSequenceReader(src.mkString))
    
    def getFormulae (formulalist : Set[OEIS])(in : Input) : Seq[(OEIS, Option[Seq[PolynomialTree]])] = parseAll(formulae(formulalist), in) match {
        case Success(x, _) => x
        case Failure(msg, next) => throw new Exception("Error at line " + next.pos.line + " column " + next.pos.column + "\n" + next.pos.longString + "\n" + msg)
        case Error(msg, next) => throw new Exception("Error at line " + next.pos.line + " column " + next.pos.column + "\n" + next.pos.longString + "\n" + msg)
    }

    def formulae(formulalist : Set[OEIS]) : Parser[Seq[(OEIS, Option[Seq[PolynomialTree]])]] = (pairwrapped(formulalist)).*

    def pairwrapped(formulalist : Set[OEIS]) : Parser[(OEIS, Option[Seq[PolynomialTree]])] = oeiswrapped >> (oeis => success(oeis) ~
        (if (formulalist.contains(oeis)) formulaswrapped ^^ (Some(_)) else """((?!def)(.|\r?\n))*""".r ^^^ None)) ^^ {case x ~ y => (x, y) }
    
    def oeiswrapped : Parser[OEIS] = "def " ~> oeis <~ "():" 
    def oeis : Parser[OEIS] = """A\d{6}""".r ^^ {str => println(str); OEIS(str)}
    
    def formulaswrapped : Parser[Seq[PolynomialTree]] = "x = SR.var('x')" ~> "return { 'ogf': " ~> formulas <~ "}"
    def formulas : Parser[Seq[PolynomialTree]] = "[" ~> repsep(polynomial0, ",") <~ "]"
    
    def polynomial0 : Parser[PolynomialTree] = terms | factors | div | pow | variable | number | paren
    def polynomial1 : Parser[PolynomialTree] = factors | div | pow | variable | number | paren
    def polynomial2 : Parser[PolynomialTree] = pow | variable | number | paren
    def polynomial3 : Parser[PolynomialTree] = variable | number | paren
    
    import PolynomialTree._
    
    def paren : Parser[PolynomialTree] = "(" ~> polynomial0 <~ ")"
    
    def number : Parser[Number] = """\d+""".r ^^ (str => Number(str.toInt))
    
    def variable : Parser[Variable.type] = "x" ^^^ Variable
    
    def terms : Parser[PolynomialTree] = polynomial1 ~ (("+" | "-") ~ polynomial1 ).+ ^^ { case first ~ terms => terms.foldLeft(first){
        case (acc, "+" ~ x) => acc :+ x 
        case (acc, "-" ~ x) => acc :- x
    }}
    
    def factors : Parser[PolynomialTree] = polynomial2 ~ ("*" ~> polynomial2).+ ^^ {case first ~ factors => factors.foldLeft(first){
        case (acc, x) => acc :* x
    }}
    
    def div : Parser[:/] = polynomial2 ~ ("/" ~> polynomial2) ^^ {case left ~ right => left :/ right}
    
    def pow : Parser[:**] = polynomial3 ~ ("**" ~> number) ^^ {case base ~ Number(exp) => base :** exp}
    
}
