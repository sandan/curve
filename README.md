SFCurve
=====

![sfcurve-space-diagram](https://cloud.githubusercontent.com/assets/2320142/6543539/449db6e2-c4ed-11e4-865a-584e056b5469.png)

### What is SFCurve?

This library represents a collaborative attempt to create a solid, robust and modular library for dealing with space filling curves on the JVM.

To read up on the collaborative effort to define the interface, and to participate in the discussion, see [Issue #3](https://github.com/geotrellis/curve/issues/3).

### Where can I learn more about the project?

A more detailed account of the origin and intention can be found in the [proposal of the SFCurve project submitted to LocationTech](http://www.locationtech.org/proposals/sfcurve). You can also find more information by contacting any of the current collaborators, or by asking about it on the [GeoTrellis](https://github.com/geotrellis/geotrellis), [GeoMesa](https://github.com/locationtech/geomesa), or [GeoWave](https://github.com/ngageoint/geowave) mailing lists.

### Can I use SFCurve now?

This library is a complete work in progress, and is __NOT__ recommended for current use. In the future, though, we hope to be the definitive library for working with space filling curves on the JVM. If you have ideas on how to get us there, please participate!

### Working with SFCurve

The build tool used in this project is [sbt](http://www.scala-sbt.org/). A script is included that will download the necessary `sbt` software, so you do not need `sbt` installed on the machine to work with this project.

To drop into the `sbt` console, where you can execute various commands, simply type

```
> ./sbt
```

in your shell.

In the below examples `curve >`, `bechmarks >` etc. represents the sbt console prompt.

Once in the `sbt` console, to compile the code, issue the command:

```
curve > compile
```

To test the code:

```
curve > test
```

To start a scala console which has the core sfcurve code in the classpath:

```
curve > console
```

To run the benchmarks, drop into the benchmark subproject using the `project` command, and run:

```
curve > project benchmarks
benchmarks > run
```
Note: you can run the benchmarks on various resolutions by specifying them in the res variable as follows:

```
benchmarks > run -Dres=2,4,6,8,10
```
