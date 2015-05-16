package org.locationtech.sfcurve.rowmajor

import org.locationtech.sfcurve._
import spire.syntax.cfor._

/** 
  Represents a row major ordering for SpatialKey 
  Taken from the geotrellis project
*/

class RowMajor2D(xmin: Int, ymin: Int, xmax: Int, ymax: Int) {

  val minCol = xmin
  val minRow = ymin
  val layoutCols = xmax - xmin + 1

  def toIndex(col: Int, row: Int): Long =
    (layoutCols * (row - minRow) + (col - minCol)).toLong

  def indexRanges(x: Int, y:Int, X:Int, Y:Int): Seq[(Long, Long)] = {
    val cols = X - x + 1
    val rows = Y - y

    val result = Array.ofDim[(Long, Long)](Y - y + 1)

    cfor(0)(_ <= rows, _ + 1) { i =>
      val row = y + i
      val min = toIndex(x, row)
      result(i) = (min, min + cols)
    }
    result
  }
}
