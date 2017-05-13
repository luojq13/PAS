/**
  * Created by vince on 2017/3/25.
  */
package pas

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD
import java.io.File
import java.net.{URL, URLClassLoader}

class ContextURLClassLoader(urls: Array[URL], parent: ClassLoader)
  extends URLClassLoader(urls, parent) {

  override def addURL(url: URL) {
    if (!getURLs.contains(url)) {
      super.addURL(url)
    }
  }
}


/*object pas {
  val conf: SparkConf = new SparkConf().setAppName("PAS").setMaster("local[*]")
  val sc: SparkContext = new SparkContext(conf)

  def main(args: Array[String]) ={

    val someClassTrait:Utrait = Class.forName("UFunction").newInstance().asInstanceOf[Utrait]
    println(someClassTrait.square(5))

    /*val url = this.getClass.getClassLoader.getResource("UserFunction.jar")

    var loader:ContextURLClassLoader = new ContextURLClassLoader(Array[URL](), this.getClass.getClassLoader)
    loader.addURL(new URL("file:" + "//Users/vince/project/pas/src/main/resources/UFunction.jar"))
    val uMethod = JarUtils.loadClassOrObject[Utrait]("UserFunction",loader)
    //uMethod.square(x)

    val nums = sc.parallelize(List(1,2,3,4))

    val count = nums.map{x =>
      var loader:URLClassLoader = new URLClassLoader(Array[URL](new URL("file:" + "//Users/vince/project/pas/src/main/resources/UserFunction.jar")))
      //loader.addURL(new URL("file:" + "//Users/vince/project/pas/src/main/resources/UserFunction.jar"))
      //loader.addURL(new URL("file:" + "UserFunction.jar"))
      val uMethod = JarUtils.loadClassOrObject[Utrait]("UserFunction",loader)
      //uMethod.square(x);
    }.count()*/
  }
}*/

trait SomeTrait { def someMethod: String}
object SomeObject extends SomeTrait { def someMethod = "something"}

class SomeClass extends SomeTrait { def someMethod = "something"}

object pas {
  def main(args:Array[String]) = {
    val someClassTrait:SomeTrait = Class.forName("SomeClass").newInstance().asInstanceOf[SomeTrait]
    println("calling someClassTrait: " + someClassTrait.someMethod)
    val objectName = "SomeObject$"
    val cons = Class.forName(objectName).getDeclaredConstructors()
    cons(0).setAccessible(true);
    val someObjectTrait:SomeTrait = cons(0).newInstance().asInstanceOf[SomeTrait]
    println("calling someObjectTrait: " + someObjectTrait.someMethod)
  }
}

