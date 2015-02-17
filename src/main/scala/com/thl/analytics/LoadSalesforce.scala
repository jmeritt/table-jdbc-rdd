/**
 * Created by jmeritt on 2/8/15.
 */

package com.thl.analytics

import java.sql.DriverManager

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql._
import org.apache.spark.sql.hive.HiveContext

object LoadSalesforce {


  val driverClass = "com.ddtek.jdbc.ddcloud.DDCloudDriver"
  val url = "jdbc:datadirect:ddcloud://service.datadirectcloud.com;databaseName=SFDC"
  val username = "jmeritt"
  val password = "7ju$u7kJ"
  val sparkHome = "/Users/jmeritt/Documents/Development/spark-1.2.0"

  val jars = Array(
    "/Users/jmeritt/Documents/Development/TableJdbcRDD/target/scala-2.10/table-jdbcrdd_2.10-0.1.0.jar",
    "/Users/jmeritt/Documents/Development/D2CDriver2/lib/ddcloud.jar"
  )


  def main(args: Array[String]) {
    Class.forName(driverClass)
    val conf = new SparkConf().setAppName("SFDCAnalytics").setMaster("local").setJars(jars);
    val sc = new SparkContext(conf)
    val rdd = new TableJdbcRDD[Row](sc, () => DriverManager.getConnection(url, username, password), "Account", 8)
    rdd.persist()
    val first100 = rdd.take(100)
    val hc = new HiveContext(sc)
    hc.applySchema(rdd, TableJdbcRDD.schema)
  }
}


