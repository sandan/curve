package org.locationtech.sfcurve.rowmajor

import org.scalatest._
import org.locationtech.sfcurve.rowmajor._

class RowMajorCurve2DSpec extends FunSpec with Matchers {

  val EPSILON: Double = 1E-3

  describe("RowMajorCurve2D Test") {

    it("implements a range query 3x3"){
      //3x3 grid
      val res = 2
      val sfc = new RowMajorCurve2D(res)
      val range = sfc.toRanges(-180,-90,180,90)
      range(0) should be((0,3))
      range(1) should be((3,6))
      range(2) should be((6,9))
    }

    it("implements a range query 4x4"){
      //4x4 grid
      val res = 3
      val sfc = new RowMajorCurve2D(res)
      val range = sfc.toRanges(60,0,180,90)
      //val range = sfc.toRanges(-90,-45,90,45)
      //val range = sfc.toRanges(-180,-90,180,90)
      println(range)
    } 


    it("Converts Point (Double, Double) to a Long value"){
      val res = 180 // This is the max resolution where I can use integer spaced queries via for loop
                    // Any larger and cellheight or cellwidth will be less than 1 but positive.
	            // This has the effect of increasing the number of indexes possible but in order to 
                    // do so, I'd have to query for (i,j) doubles to get their indexes. Notice the +1 on using res
      val sfc = new RowMajorCurve2D(res)
      val keys = 
        for (i <- -180 to 180;
              j <- -90 to 90) yield {
            sfc.toIndex(i.toDouble ,j.toDouble)
         }
      keys.distinct.size should be ((res+1) * (res+1))
      keys.min should be (0)
      keys.max should be ((res + 1) * (res + 1) - 1)
    }

  }
}
