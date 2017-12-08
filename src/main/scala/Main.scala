package io.github.torsteinvik.oeisgloballyrecursivemult

import scala.io.Source

object Main extends App {
    
    val source = Source.fromURL(getClass.getResource("/oeis_gf.py"))
    
    val result = try Parser.parseFile(source) finally source.close
    
}
