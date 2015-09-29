package com.gvolpe.hll

import com.clearspring.analytics.stream.cardinality.HyperLogLogPlus

object CounterApp extends App {

  val data = WikipediaScraper().progRockBands

  println(data.mkString(" | "))

  val hll = new HyperLogLogPlus(5, 25)

  data foreach { elem =>
    val bytes = elem.getBytes("utf-8")
    hll.offer(bytes)
  }

  println("Estimated count: " + hll.cardinality())

}
