package org.locationtech.sfcurve.zorder

import org.scalatest._
import org.locationtech.sfcurve.zorder._

class ZCurve2DRangeQuerySpec extends FunSpec with Matchers {
  describe("ZCurve2D RangeQuery Test") {

    it("gets ranges in all 4 quadrants from various resolutions"){
      for(res <- 1 until 8){
        val sfc = new ZCurve2D(Math.pow(2,res).toInt)
           println("resolution: "+res)
        //UR   
                println("UR: "+sfc.toRanges(0.0, 0.0, 180.0, 90.0).size)
      
        //LL
                println("LL: "+sfc.toRanges(-180.0, -90.0, 0.0, 0.0).size)
      
        //UL
                println("UL: "+sfc.toRanges(-180.0, 0.0, 0.0, 90.0).size)
      
        //LR
                println("LR: "+sfc.toRanges(0.0, -90.0, 180.0, 0.0).size)
      }
    }
  }
}
