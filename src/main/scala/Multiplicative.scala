package io.github.torsteinvik.oeisgloballyrecursivemult

import scala.io.Source

import org.json4s._

object Multiplicatives {
    import collection.mutable.HashSet
    
    private var status : Option[Boolean] = Some(false)
    private var multiplicatives : HashSet[OEIS] = new HashSet()
    
    def apply : collection.immutable.HashSet[OEIS] = {
        if (status == Some(true)) return multiplicatives.to[collection.immutable.HashSet] else if (status == Some(false)){
            ???
        } else throw new Exception("Please wait for data to be loaded...")
        
    }
    
    def queryurl(i : Int) = "https://oeis.org/search?q=keyword:mult&fmt=json&start=" + (i * 10)
}
