import org.apache.spark.sql.{SparkSession, _}

object TaskJoinTest {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("TaskJoin1").master("local[*]").getOrCreate()
    spark.sparkContext.setLogLevel("OFF")
    val snmpDF = spark.createDataFrame(Seq(
      (1,"10.148.158.118", "name1", 34),
      (2,"10.12.15.17", "name2", 31),
      (3,"10.12.15.00", "name3", 25),
      (4,"10.12.15.11", "name4", 25),
      (5,"10.12.15.15", "name5", 25))).toDF("serialNo","address", "name", "age")
    snmpDF.show
    val acol = snmpDF.columns.toList
    val lookupDF = spark.createDataFrame(Seq(
      ("10.12.15.10", "EEMTTN01"),
      ("10.12.15.11", "EEMTTN02"),
      ("10.12.15.12", "EEMTTN03"),
      ("10.12.15.13", "EEMTTN04"),
      ("10.12.15.14", "EEMTTN05"),
      ("10.12.15.15", "EEMTTN06"),
      ("10.12.15.16", "EEMTTN07"),
      ("10.12.15.17", "EEMTTN08"))).toDF("ip", "hostname")

    lookupDF.show
    val bcol = lookupDF.columns.toList

    //snmpDF.select("address").join(lookupDF,snmpDF("address")===lookupDF("ip"))
    snmpDF.select("address").join(lookupDF,snmpDF("address")===lookupDF("ip")).show
    println("***joining data frame ***")
    val joinDF = snmpDF.join(lookupDF, snmpDF("address")===lookupDF("ip"), "left_outer")
    joinDF.show
    val finalDF = joinDF.filter("ip is not null")
    finalDF.show()

    val resolvedHostDF = joinDF.filter("ip is not null").select(acol.head,acol.tail:_*)
    val unresolvedHostDF = joinDF.filter("ip is null").select(acol.head,acol.tail:_*)


  }
  def lookupIP(df:DataFrame,lookupdf:DataFrame,col:Column): DataFrame={
    df.select(col).join(lookupdf,df("col")===lookupdf("col"))

  }


}