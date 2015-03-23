import AssemblyKeys._

name := "polish-notation-calculator"

version := "1.0"

mainClass in (Compile, run) := Some("Calculator")

assemblySettings

net.virtualvoid.sbt.graph.Plugin.graphSettings
