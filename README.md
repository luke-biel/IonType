# IonType
Plugin to IntelliJ to help with Anixe ACLR8 .ion file workflow

# What is `.ion`
`ION` is format merging `TOML` and `CSV` formats into one.
You keep TOML feature of being section based dictionary, but also allow to write CSV fragments under sections.
This way we can have multidimensional matrices of interconnected data while also allowing to keep simple configuration data along.

Example of .ion file would be:
```
[CONFIG]
matrix_operation = "multiplication"
matrix_array = ["MATRIX.A", "MATRIX.B"]

[MATRIX.A]
|1|
|3|

[MATRIX.B]
|2|-1|

[TEST]
|2|-1|
|6|-3|
```

# Installation
## Download plugin using any JetBrains IDE
**Preferences** -> **Plugins** -> **Marketplace** -> **search _IonType_** -> **Install**

## Download jar from JetBrains website
1) visit [plugin website](https://plugins.jetbrains.com/plugin/13632-iontype) & goto Versions  
**or**    
2) [download from releases](https://github.com/luke-biel/IonType/releases)
3) get latest `.jar`
4) **Preferences** -> **Plugins** -> **Gear icon** -> **Install Plugin from Disk**

## Compile sources
Clone the repo and run `:buildPlugin` gradle job

# Contributing
Anyone can contribute.
Plugin is being developed in `kotlin`, with code generated by `jflex` in `java`.
Keep commits sane and features usefull.

# Plans
.ion format is quite hermetic, especially in ARIZ extension, so probably at some point I'll split this plugin into ARIZ plugin and standalone .ion plugin
