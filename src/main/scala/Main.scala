package io.github.torsteinvik.oeisgloballyrecursivemult

import scala.io.Source

object Main extends App {
    
    val source = Source.fromURL(getClass.getResource("/oeis_gf.txt"))
    
    val oeis_ids : Seq[OEIS] = try Parser.parseFile(Set())(source).map(_._1) finally source.close
    
    println("Amount: " + oeis_ids.length)
    
    val tobeparsed = Set(OEIS("A257848"), OEIS("A257849"))
    
    
}
