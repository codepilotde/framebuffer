import scala.ref.Reference;
import scala._
import scala.math;

/**
 * Created by diez on 11.07.14.
 */
class FrameCore {

  val Xmax = 30
  val Ymax = 30
  var FrameBuffer = Array.ofDim[Int](Xmax + 1, Ymax + 1)

  def drawPixel(x: Int, y: Int) {
    this.FrameBuffer(x)(y) = 1;
  }

  def drawPixel(p: Point2D) {
    FrameBuffer(p.x)(p.y) = 1;
  }

  def drawLine(x1: Int, y1: Int, x2: Int, y2: Int) {
    var py, px: Int = 0
    println (x1 +" - " + x2);
    for (px <- x1 to x2) {

      println(px + " py no round " + calLineYCoord(x1, y1, x2, y2, px))
      py = math.round(calLineYCoord(x1, y1, x2, y2, px))
      drawPixel(px,py)
      var fill=0;
      print (((y2 / x2)-1 + " ---- "))
      for(fill <- 0 to ((y2 / x2) )) {
        drawPixel(px,py+fill)
      }
    }
  }

  def drawYaxis(maxSize: Int, value: Int): String = value.toString().padTo(maxSize.toString.length + 1, ' ')


  def calLineYCoord(x1: Float, y1: Float, x2: Float, y2: Float, px: Float): Float = {
    val fl: Float = x2 - x1
    if(fl == 0) {
      return 0
    }
    else {
        (y2 - y1) / fl * (px - x1) + y1
    }
  }

  //def drawLine(p1:Point2D,p2:Point2D)
  def renderBuffer() {
    var mem = 0
    drawXaxis(Xmax)
    for (y <- 0 to Ymax) {
      print(drawYaxis(Ymax, y))
      for (x <- 0 to Xmax) {
        if (FrameBuffer(y)(x) >0) {
          if((y +1< Xmax && x +1< Ymax) ) {
            //FrameBuffer(y)(x+1)=2
          }
          print(FrameBuffer(y)(x))
          mem = 1
        }
        else  {
          print("0")
        }
      }
      println
    }
  }

  def drawXaxis(xmax: Int = Xmax) {
    print ('*')
    print(' '.toString.padTo(xmax.toString.length, ' '))
    for (b <- 0 to Xmax) print(b)
    println
  }
}

class Point2D(xc: Integer, yc: Integer) {
  var x: Int = xc
  var y: Int = yc
}

object FrameCore {
  def main(args: Array[String]) {
    val Buffer = new FrameCore;
    Buffer.drawPixel(0, 0)
    //    Buffer.drawPixel(10, 10)
    //    Buffer.drawPixel(1, 1)
    //    Buffer.drawPixel(2, 2)
//    Buffer.drawPixel(2, 2)
//    Buffer.drawPixel(0, 10)
    Buffer.drawLine(0,0,29,29)
    Buffer.renderBuffer()

  }
}
