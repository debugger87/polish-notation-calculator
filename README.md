# polish-notation-calculator
[Polish notation](http://en.wikipedia.org/wiki/Polish_notation) calculator implemented using Scala

### Build

```
sbt assembly
```

### Run

```
java -cp target/scala-2.10/polish-notation-calculator-assembly-1.0.jar Calculator "/ (** 2 3) (- 4 2)"
```
Or

```
$ sbt

> run "/ (** 2 3) (- 4 2)"
```

Notes: 

* `**` represents `pow`


