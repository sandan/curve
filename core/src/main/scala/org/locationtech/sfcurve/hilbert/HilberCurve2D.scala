package org.locationtech.sfcurve.hilbert

import org.locationtech.sfcurve._

import com.google.uzaygezen.core._

class HilbertCurve2D(resolution: Int) extends SpaceFillingCurve2D {
  val precision = math.pow(2, resolution).toLong
  val chc = new CompactHilbertCurve(Array(resolution, resolution))

  final def getNormalizedLongitude(x: Double): Long = 
    (x * (precision - 1) / 360d).toLong

  final def getNormalizedLatitude(y: Double): Long =
    (y * (precision - 1) / 180d).toLong

  final def setNormalizedLatitude(latNormal: Long) = {
    if(!(latNormal >= 0 && latNormal <= precision))
      throw new NumberFormatException("Normalized latitude must be greater than 0 and less than the maximum precision")

    latNormal * 180d / (precision - 1)
  }

  final def setNormalizedLongitude(lonNormal: Long) = {
    if(!(lonNormal >= 0 && lonNormal <= precision))
      throw new NumberFormatException("Normalized longitude must be greater than 0 and less than the maximum precision")

    lonNormal * 360d / (precision - 1)
  }


  def toIndex(x: Double, y: Double): Long = {
    val normX = getNormalizedLongitude(x)
    val normY = getNormalizedLatitude(y)
    val p = 
      Array[BitVector](
        BitVectorFactories.OPTIMAL(resolution),
        BitVectorFactories.OPTIMAL(resolution)
      )

    p(0).copyFrom(normX)
    p(1).copyFrom(normY)

    val hilbert = BitVectorFactories.OPTIMAL.apply(resolution * 2)

    chc.index(p,0,hilbert)
    hilbert.toLong
  }

  def toPoint(i: Long): (Double, Double) = {
    val h = BitVectorFactories.OPTIMAL.apply(resolution*2)
    h.copyFrom(i)
    val p = 
      Array[BitVector](
        BitVectorFactories.OPTIMAL(resolution),
        BitVectorFactories.OPTIMAL(resolution)
      )

    chc.indexInverse(h,p)

    val x = setNormalizedLongitude(p(0).toLong) - 180
    val y = setNormalizedLatitude(p(1).toLong) - 90
    (x, y)
  }

  def toRanges(xmin: Double, ymin: Double, xmax: Double, ymax: Double): Seq[(Long, Long)] = ???
}
