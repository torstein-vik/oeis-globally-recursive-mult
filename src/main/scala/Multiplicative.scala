package io.github.torsteinvik.oeisgloballyrecursivemult

import scala.io.Source

object Multiplicatives {
    import collection.mutable.HashSet
    
    private var status : Option[Boolean] = Some(false)
    private var multiplicatives : HashSet[OEIS] = new HashSet()
    
    def apply : collection.immutable.HashSet[OEIS] = {
        
    }
    
    val query = "https://oeis.org/search?q=keyword:mult&fmt=json"
}
