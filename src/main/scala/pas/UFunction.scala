package pas

/**
  * Created by vince on 2017/3/25.
  */
trait Utrait {
  def square(x: Int): Int
}

class UFunction extends Utrait{
  override def square(x: Int) = {
    x*x
  }
}
