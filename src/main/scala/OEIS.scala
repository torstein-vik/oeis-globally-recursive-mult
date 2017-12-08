package io.github.torsteinvik.oeisgloballyrecursivemult

case class OEIS (id : String)

object OEIS {
    def fromNumber (n : Int) = if (0 < n && n <= 999999) OEIS("A%06d".format(n)) else throw new Exception("Not a valid OEIS ID number!")
}
