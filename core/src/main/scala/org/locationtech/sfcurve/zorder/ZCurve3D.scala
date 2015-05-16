package org.locationtech.sfcurve.zorder

import org.locationtech.sfcurve._

import com.github.nscala_time.time.Imports._

/**
 * Taken from the geotrellis project.
 * Should we keep time as a third dimension ? How do we abstract additional dimensions?
 * Should we just use the CompactHilbertCurve's representation for time (normalized)?
 */

class ZCurve3D(resolution: Int, timeToGrid: DateTime => Int) extends SpaceFillingCurve3D {

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

  private def toZ(col: Int, row: Int, time: DateTime): Z3 = Z3(col, row, timeToGrid(time))

  def toIndex(x: Double, y: Double, z: DateTime): Long = toZ(mapToCol(x), mapToRow(y),z).z

  def toPoint(v: Long): (Double, Double, Double) = (0,0,0) //TODO: Implement

  def toRanges(xmin: Double, ymin: Double, zmin: DateTime,
               xmax: Double, ymax: Double, zmax: DateTime): Seq[(Long, Long)] =
    Z3.zranges(toZ(mapToCol(xmin), mapToRow(ymax), zmin), 
               toZ(mapToCol(xmax), mapToRow(ymin), zmax))
}
