# polish-notation-calculator
[Polish notation](http://en.wikipedia.org/wiki/Polish_notation) calculator implemented using Scala

### Build

```
sbt assembly
```

### Run

```
java -cp target/scala-2.10/polish-notation-calculator-assembly-1.0.jar Calculator "poish" "/ (** 2 3) (- 4 2)"

java -cp target/scala-2.10/polish-notation-calculator-assembly-1.0.jar Calculator "infix" "(2 ** 3) / (4 - 2)"
```
Or

```
$ sbt

> run "polish" "/ (** 2 3) (- 4 2)"

> run "infix" "(2 ** 3) / (4 - 2)"
```

Notes: 

* `**` represents `pow`