package org.locationtech.sfcurve.hilbert

import org.scalatest._

class HilbertCurveSpec extends FunSpec with Matchers {

  val EPSILON: Double = 1E-3

  describe("A HilbertCurve implementation using UG lib") {

    it("translates (Double,Double) to Long and Long to (Double, Double)"){
      val sfc = new HilbertCurve2D(16)
      val index: Long = sfc.toIndex(0.0,0.0)

      //result should be close to (0.0,0.0)
      sfc.toPoint(index) should be < (0.0, 0.0)
      sfc.toPoint(index) should be > (-EPSILON, -EPSILON)
      
    }

    it("implements a range query"){

      val sfc = new HilbertCurve2D(3)
      val range = sfc.toRanges(-178.123456, -86.398493, 179.3211113, 87.393483)

      range should have length 3
    }

    it("Takes a Long value to a Point (Double, Double)"){

      val value = 0L
      val sfc = new HilbertCurve2D(8)
      val point: (Double, Double) = sfc.toPoint(value)
      print(point)     

    }

    it("Takes a Point (Double, Double) to a Long value"){

      val sfc = new HilbertCurve2D(8)
      val value: Long = sfc.toIndex(0.0,0.0)
      print(value)     

    }

  }
}
