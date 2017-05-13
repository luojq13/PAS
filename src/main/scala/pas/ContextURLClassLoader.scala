/**
  * Created by vince on 2017/3/25.
  */
import java.net.{URLClassLoader, URL}

/**
  * The addURL method in URLClassLoader is protected. We subclass it to make this accessible.
  * NOTE: This is copied from Spark's ExecutorURLClassLoader, which is private[spark].
  */
class ContextURLClassLoader(urls: Array[URL], parent: ClassLoader)
  extends URLClassLoader(urls, parent) {

  override def addURL(url: URL) {
    if (!getURLs.contains(url)) {
      super.addURL(url)
    }
  }
}