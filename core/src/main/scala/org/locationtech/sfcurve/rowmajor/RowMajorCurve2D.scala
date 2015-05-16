package org.locationtech.sfcurve.rowmajor

import org.locationtech.sfcurve._

 /** Represents a 2D row major order curve that we will use for benchmarking purposes in the early stages.
  * @param    resolution     The number of cells in each dimension of the grid space that will be indexed.
  */

class RowMajorCurve2D(resolution: Int) extends SpaceFillingCurve2D {

  // We are assuming Lat Lng extent for the whole world
  // We assume that -180<= x <= 180 and -90 <= y <= 90

  val xmin = -180.0
  val ymin = -90.0
  val xmax = 180.0
  val ymax = 90.0

  // Code taken from geotrellis.raster.RasterExtent
  val cellwidth = (xmax - xmin) / resolution
  val cellheight = (ymax - ymin) / resolution 

 // print("cellwidth: "+cellwidth+", cellheight: "+cellheight+"\n")

  //the closer x is to the left, the smaller the col
  private final def mapToCol(x: Double) =
    ((x - xmin) / cellwidth).toInt

  //the closer y is to the north, the smaller the row
  private final def mapToRow(y: Double) =
    ((ymax - y) / cellheight).toInt

  private final def colToMap(col: Int) =
    math.max(math.min(col * cellwidth + xmin + (cellwidth / 2), xmax), xmin)

  private final def rowToMap(row: Int) =
    math.min(math.max(ymax - (row * cellheight) - (cellheight / 2), ymin), ymax)


  def toIndex(x: Double, y: Double): Long = {
    val col = mapToCol(x)
    val row = mapToRow(y)
    val curve = new RowMajor2D(0,0, resolution, resolution)
    curve.toIndex(col,row)
  }

  def toPoint(i: Long): (Double, Double) = {
    //TODO: implement
    (0.0, 0.0)
  }

  def toRanges(xmin: Double, ymin: Double, xmax: Double, ymax: Double): Seq[(Long, Long)] = {
    val colMin = mapToCol(xmin)
    val rowMin = mapToRow(ymax)
    val colMax = mapToCol(xmax)
    val rowMax = mapToRow(ymin)
    val curve = new RowMajor2D(0,0,resolution, resolution)
    // need to switch for rows bc the higher it is numerically, the smaller the image is
    curve.indexRanges(colMin,rowMin, colMax, rowMax)
  }
}


