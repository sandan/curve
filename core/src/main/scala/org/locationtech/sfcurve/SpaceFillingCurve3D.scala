package org.locationtech.sfcurve

trait SpaceFillingCurve3D {
  def toIndex(x: Double, y: Double, z: Double): Long
  def toPoint(i: Long): (Double, Double, Double)
  def toRanges(xmin: Double, ymin: Double, zmin: Double,
               xmax: Double, ymax: Double, zmax: Double): Seq[(Long, Long)]
}

