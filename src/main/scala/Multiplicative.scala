package io.github.torsteinvik.oeisgloballyrecursivemult

import scala.io.Source

object Multiplicatives {
    import collection.mutable.HashSet
    
    private var multiplicatives : HashSet[OEIS] = new HashSet()
    
    
    val query = "https://oeis.org/search?q=keyword:mult&fmt=json"
}
