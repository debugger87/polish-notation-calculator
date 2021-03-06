import scala.util.parsing.combinator._

trait Maths {
  def add(x: Float, y: Float)   = x + y
  def sub(x: Float, y: Float)   = x - y
  def mul(x: Float, y: Float)   = x * y
  def div(x: Float, y: Float)   = if (y > 0) (x / y) else 0.0f
  def pow(x: Float, exp: Float) = math.pow(x, exp).toFloat
}

class PolishCalculator extends JavaTokenParsers with Maths {
  def expr: Parser[Float] = rep(operator ~ term) ^^ {
    case terms =>
      var stack = List.empty[Float]
      var firstOp: (Float, Float) => Float = add
      terms.reverse.foreach(t =>
        t match {
          case op ~ nums => firstOp = op; stack = reduce(nums ++ stack, op)
        }
      )
      stack.reduceLeft((x, y) => firstOp(x, y))
  }

  def term: Parser[List[Float]] = rep(factor)
  def factor: Parser[Float] = num | "(" ~> expr <~ ")" ^^ (_.toFloat)
  def num: Parser[Float] = floatingPointNumber ^^ (_.toFloat)
  def operator: Parser[(Float, Float) => Float] = ("**" | "*" | "/" | "+" | "-") ^^ {
    case "**" => pow
    case "+"  => add
    case "-"  => sub
    case "*"  => mul
    case "/"  => div
  }

  def reduce(nums: List[Float], op: (Float, Float) => Float): List[Float] = {
    val result = nums match {
      case x :: y :: xs => List(nums.reduceLeft((x, y) => op(x, y)))
      case List(x)      => List(x)
      case _            => List.empty[Float]
    }
    result
  }

  def calculate(expression: String) = parseAll(expr, expression)
}

class InfixCalculator extends JavaTokenParsers with Maths {
  def expr: Parser[Float] = term ~ operator ~ term ^^ {
    case t1 ~ op ~ t2 => op(t1, t2)
  }

  def term: Parser[Float] = num | "(" ~> expr <~ ")" ^^ (_.toFloat)
  def num: Parser[Float] = floatingPointNumber ^^ (_.toFloat)

  def operator: Parser[(Float, Float) => Float] = ("**" | "*" | "/" | "+" | "-") ^^ {
    case "**" => pow
    case "+"  => add
    case "-"  => sub
    case "*"  => mul
    case "/"  => div
  }

  def calculate(expression: String) = parseAll(expr, expression)
}

object Calculator extends PolishCalculator with App {
  val polish = new PolishCalculator
  val infix = new InfixCalculator

  if (args.length < 2) {
    println("input the type of calculator: polish or infix, and your expression!")
  }

  if (args(0).toLowerCase == "polish") {
    println(polish.calculate(args(1)).get)
  } else if (args(0).toLowerCase == "infix") {
    println(infix.calculate(args(1)).get)
  }
}