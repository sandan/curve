package org.locationtech.sfcurve

import com.github.nscala_time.time.Imports._

trait SpaceFillingCurve3D {
  def toIndex(x: Double, y: Double, z: DateTime): Long
  def toPoint(i: Long): (Double, Double, Double)
//  def toRanges(xmin: Double, ymin: Double, zmin: Double, xmax: Double, ymax: Double, zmax: Double): Seq[(Long, Long)]
  def toRanges(xmin: Double, ymin: Double, zmin: DateTime, xmax: Double, ymax: Double, zmax: DateTime): Seq[(Long, Long)]
}

