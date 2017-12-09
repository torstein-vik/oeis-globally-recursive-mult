package io.github.torsteinvik.oeisgloballyrecursivemult

import scala.io.Source

object Main extends App {
    
    val source = Source.fromURL(getClass.getResource("/oeis_gf.txt"))
    
    val result = try Parser.parseFile(Set())(source) finally source.close
    
    println("Amount: " + result.length)
    
}
