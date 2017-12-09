package io.github.torsteinvik.oeisgloballyrecursivemult

import scala.io.Source

object Main extends App {
    
    val source = Source.fromURL(getClass.getResource("/oeis_gf.txt"))
    
    val oeis_ids : Set[OEIS] = try Parser.parseFile(Set())(source).map(_._1).toSet finally source.close
    
    println("Amount gf: " + oeis_ids.size)
    
    val mults : Set[OEIS] = Multiplicatives.apply
    
    /*
    val tobeparsed = Set(OEIS("A257848"), OEIS("A257849"))
    
    
    val source2 = Source.fromURL(getClass.getResource("/oeis_gf.txt"))
    
    val parsed : Seq[(OEIS, Seq[PolynomialTree])] = try Parser.parseFile(tobeparsed)(source2).flatMap{
        case (id, None) => Seq()
        case (id, Some(rec)) => Seq((id, rec))
    }  finally source2.close
    
    println(parsed)
    */
}
