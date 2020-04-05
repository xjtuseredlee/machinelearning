package com.lihong.study


import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.rdd.RDD


object TestScala {

  def main(args: Array[String]) {

    // 屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    //设置运行环境
    val conf = new SparkConf().setAppName("Kmeans").setMaster("local[4]")
    val sc = new SparkContext(conf)
    val data: RDD[String] = sc.textFile("D:\\git\\Spark\\src\\main\\resources\\kmeans\\kmeans_data.txt")


    val parsedData = data.map(s => Vectors.dense(s.split(' ').map(_.toDouble)))
    // 将数据集聚类，2个类，20次迭代，进行模型训练形成数据模型
    val numClusters = 2
    val numIterations = 20
    val model = KMeans.train(parsedData, numClusters, numIterations)

    // 打印数据模型的中心点

    println("Cluster centers:")
    for (c <- model.clusterCenters) {
      println("  " + c.toString)
    }


    // 使用误差平方之和来评估数据模型
    val cost = model.computeCost(parsedData)
    println("Within Set Sum of Squared Errors = " + cost)

    // 交叉评估1，只返回结果

    val testdata = data.map(s => Vectors.dense(s.split(' ').map(_.toDouble)))
    model.save(sc, "D:\\git\\Spark\\src\\main\\resources\\kmeans\\kmeans_model")
    val result1: RDD[Int] = model.predict(testdata)
    println(result1)

    sc.stop()

  }

}