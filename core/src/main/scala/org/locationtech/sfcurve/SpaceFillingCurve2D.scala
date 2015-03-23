package org.locationtech.sfcurve

trait SpaceFillingCurve2D {
  def toIndex(x: Double, y: Double): Long
  def toPoint(i: Long): (Double, Double)
  def toRanges(xmin: Double, ymin: Double, xmax: Double, ymax: Double): Seq[(Long, Long)]
}
