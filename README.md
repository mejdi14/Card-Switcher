<h1 align="center">Welcome to Card Switcher Library 👋</h1>

<p align="center">
  <a href="https://github.com/frinyvonnick/gitmoji-changelog">
    <img src="https://img.shields.io/badge/API-15%2B-blue.svg?style=flat" alt="gitmoji-changelog">
  </a>  <a href="https://github.com/frinyvonnick/gitmoji-changelog">
    <img src="https://jitpack.io/v/mejdi14/AndroidColorPicker.svg" alt="gitmoji-changelog">
  </a>
  </a>
	<a href="https://github.com/kefranabg/readme-md-generator/blob/master/LICENSE">
    <img alt="License: MIT" src="https://img.shields.io/badge/license-MIT-yellow.svg" target="_blank" />
  </a>
  <a href="https://codecov.io/gh/kefranabg/readme-md-generator">
    <img src="https://codecov.io/gh/kefranabg/readme-md-generator/branch/master/graph/badge.svg" />
  </a>
</p>

<p align="center">
<img src="https://github.com/mejdi14/AndroidColorPicker/blob/master/images/logo.png" height="300" width="300" >
	</p>
	

## ✨ Demo
<p align="center">
<img src="https://github.com/mejdi14/AndroidColorPicker/blob/master/images/original.gif" height="400" width="550" >
	</p>
	

## Installation

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
``` 
## :hammer:Dependency

Add this to your module's `build.gradle` file (make sure the version matches the JitPack badge above):

```gradle
dependencies {
	...
	implementation 'com.github.mejdi14:AndroidColorPicker:1.0.2'
}
```


## :fire:How to use with kotlin

``` java
    MHColorsDialog(this)
                .setColorListener { color, colorHex ->
                    // color and colorHex are the chosen color
                }
                .show()
```

## :coffee:How to use with Java

``` java
  MHColorsDialog mhColorsDialog=new MHColorsDialog(MainActivity.this);
                mhColorsDialog.setColorListener(new ColorListener() {
                    @Override
                    public void onColorSelected(int color, @NotNull String colorHex) {
                          // color and colorHex are the chosen color
                    }
                });
                mhColorsDialog.show();
```


## :art:Customization

Dark Mode
-----
<img src="https://github.com/mejdi14/AndroidColorPicker/blob/master/images/dark4.png" alt="sample" title="sample" width="320" height="350" align="right" vspace="52" />

``` java
 .withDarkMode()
```
Add new colors
-----

``` java
 .addColors(colorsList,ColorsPosition.START)
```
where colorsList is an ArrayList<Int> (every Int represent a color)</br>
ColorsPosition is where your colors should be in the final list of colors (Start or End)
	
Use your own colors
-----

``` java
 .withMyOwnColors(colorsList)
```
where colorsList is an ArrayList<Int> (every Int represent a color)</br>
this will make the library ignore the default colors and use only your colors from colorsList	




## 🤝 Contributing

Contributions, issues and feature requests are welcome.<br />
Feel free to check [issues page] if you want to contribute.<br />


## Author

👤 **Mejdi Hafiane**

- profile: [@MejdiHafiane](https://mejdi14.github.io/mejdi/#/about)

## Show your support

Please ⭐️ this repository if this project helped you!


## 📝 License

Copyright © 2019 [Mejdi Hafiane](https://github.com/mejdi14).<br />
This project is [MIT](https://github.com/mejdi14/readme-md-generator/blob/master/LICENSE) licensed.
