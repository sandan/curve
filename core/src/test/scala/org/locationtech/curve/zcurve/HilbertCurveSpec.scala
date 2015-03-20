package org.locationtech.curve.hilbertCurve

import org.scalatest._

class HilbertCurveSpec extends FunSpec with Matchers {
  describe("A HilbertCurve implementation using UG lib") {

    it("implements a range query"){

      val sfc = HilbertCurve(3)
      val range = sfc.RangeQuery(-178.123456, -86.398493, 179.3211113, 87.393483)

      range should have length 3
    }

  }
}
