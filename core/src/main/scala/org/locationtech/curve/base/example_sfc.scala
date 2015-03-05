/**
 * A baseline 'space filling curve' that does a traversal on a 'grid'
 * Points here are just (long, long)
 * BoundingBoxPoints is really a lower left point and an uper right point
 * The idea is that R^N for R some set of objects is really just a one dim set of tuples of length N
 * So given a range query, all we do is return a subset of R^N
 */

def answerQuery(query: Seq[NTupleOfPoints], in: Seq[NTupleOfPoints]): Seq[NTupleOfPoints] = in match{
  case Nil => Nil
  case head :: tail if satisfiesQuery(head) => head :: answerQuery(query, tail)
  case _ :: tail => answerQuery(query, tail) 
}


