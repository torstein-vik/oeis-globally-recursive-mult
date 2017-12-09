package io.github.torsteinvik.oeisgloballyrecursivemult

import scala.io.Source

import org.json4s._

object Multiplicatives {
    import collection.mutable.HashSet
    
    private var status : Option[Boolean] = Some(false)
    private var multiplicatives : HashSet[OEIS] = new HashSet()
    
    private implicit val formats = DefaultFormats
        
    def apply : collection.immutable.HashSet[OEIS] = {
        if (status == Some(true)) return multiplicatives.to[collection.immutable.HashSet] else if (status == Some(false)){
            status = None
            
            val first = query(0)
            
            val count = (first \ "count").extract[Int]
            val amt = math.ceil(count.toFloat / 10).toInt
            
            println("count: " + count)
            println("queries: " + amt)
            
            for (i <- 0 to (amt - 1)) {
            }
            
            status = Some(true)
            
            return apply
        } else throw new Exception("Please wait for data to be loaded...")
        
    }
    
    def queryurl(i : Int) = "https://oeis.org/search?q=keyword:mult&fmt=json&start=" + (i * 10)
    
    def query (i : Int) : JValue = {
        val source = Source.fromURL(queryurl(i))("UTF-8")
        val result = try source.mkString finally source.close
        
        import org.json4s.native.JsonMethods._
        return parse(result)
    }
}
