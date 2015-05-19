package org.locationtech.sfcurve.benchmarks

import org.locationtech.sfcurve.zorder._
import com.google.caliper.Param
import org.eichelberger.sfc.ZCurve
import org.eichelberger.sfc.SpaceFillingCurve._

import org.joda.time.DateTime
import org.joda.time.Days

/**
 * benchmarks for counting range queries for various static bounding boxes and resolutions
 */
object ZCurve3DRangeQueryBenchmark extends BenchmarkRunner(classOf[ZCurve3DRangeQueryBenchmark])
class ZCurve3DRangeQueryBenchmark extends CurveBenchmark {
   
  val res = 1 //max res
  val y2k = new DateTime(2000, 1, 1, 0, 0)
  val time_bound = new DateTime(2015 , 1, 1, 0, 0)
  val sfc = new ZCurve3D(Math.pow(2,res).toInt, { dt => Days.daysBetween(dt, y2k).getDays })
  val sfz3D = new ZCurve(OrdinalVector(res,res,res))

  //UR
  def timeZCurve3DQuadrantUR(reps: Int) = run(reps)(ZCurve3DQuadrantUR)
  def ZCurve3DQuadrantUR = {
         // sfc.toRanges(0.0, 0.0, y2ka, 180.0, 90.0, y2ka)
  }

  //LL
  def timeZCurve3DQuadrantLL(reps: Int) = run(reps)(ZCurve3DQuadrantLL)
  def ZCurve3DQuadrantLL = {
         // sfc.toRanges(-180.0, -90.0, y2ka,  0.0, 0.0, y2ka)
  }

  //UL
  def timeZCurve3DQuadrantUL(reps: Int) = run(reps)(ZCurve3DQuadrantUL)
  def ZCurve3DQuadrantUL = {
         // sfc.toRanges(-180.0, 0.0, y2ka, 0.0, 90.0, y2ka)
  }

  //LR
  def timeZCurve3DQuadrantLR(reps: Int) = run(reps)(ZCurve3DQuadrantLR)
  def ZCurve3DQuadrantLR = {
         // sfc.toRanges(0.0, -90.0, y2ka, 180.0, 0.0, y2ka)
  }



  //queries are a function of resolution
  val numtiles = (Math.pow(2,res) * Math.pow(2,res)).toInt
  val center = (Math.pow(2,res)/2).toInt
  val end = (2 * center) - 1 //0 indexed square grid
  val URQuery = Query(Seq(
                 OrdinalRanges(OrdinalPair(center,end)),
                 OrdinalRanges(OrdinalPair(center,end))))

  val ULQuery = Query(Seq(
                 OrdinalRanges(OrdinalPair(0,center)),
                 OrdinalRanges(OrdinalPair(center,end))))

  val LRQuery = Query(Seq(
                 OrdinalRanges(OrdinalPair(center,end)),
                 OrdinalRanges(OrdinalPair(0,center))))

  val LLQuery = Query(Seq(
                 OrdinalRanges(OrdinalPair(0,center)),
                 OrdinalRanges(OrdinalPair(0,center))))

  //sfseize
  def timeZCurve3DQuadrantsfUR(reps: Int) = run(reps)(ZCurve3DQuadrantsfUR)
  def ZCurve3DQuadrantsfUR = {
          sfz3D.getRangesCoveringQuery(URQuery)
  }

  //LL
  def timeZCurve3DQuadrantsfLL(reps: Int) = run(reps)(ZCurve3DQuadrantsfLL)
  def ZCurve3DQuadrantsfLL = {
          sfz3D.getRangesCoveringQuery(LLQuery)
  }

  //UL
  def timeZCurve3DQuadrantsfUL(reps: Int) = run(reps)(ZCurve3DQuadrantsfUL)
  def ZCurve3DQuadrantsfUL = {
          sfz3D.getRangesCoveringQuery(ULQuery)
  }

  //LR
  def timeZCurve3DQuadrantsfLR(reps: Int) = run(reps)(ZCurve3DQuadrantsfLR)
  def ZCurve3DQuadrantsfLR = {
          sfz3D.getRangesCoveringQuery(LRQuery)
  }

}
