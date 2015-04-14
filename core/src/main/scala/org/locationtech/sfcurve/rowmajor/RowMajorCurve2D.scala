package org.locationtech.sfcurve.rowmajor

import org.locationtect.sfcurve._

/**
*  A 2D row major sfc.
*/
class RowMajorCurve2D(resolution: Int) extends SpaceFillingCurve2D {

  // We are assuming Lat Lng extent for the whole world
  val xmin = -180.0
  val ymin = -90.0
  val xmax = 180.0
  val ymax = 90.0

  val Ncols = xmax - xmin
  val Nrows = ymax - ymin

  def toIndex(x: Double, y: Double): Long = {
    val X = x.toLong()
    val Y = y.toLong()
    
    val index = 
  }

  def toPoint(i: Long): (Long, Long) = {

  }

  def toRanges(xmin: Long, ymin: Long, xmax: Long, ymax: Long): Seq[(Long,Long)] = {


  }


}
