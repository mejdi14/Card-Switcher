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

## ✨ Demo
<p align="center">
<img src="https://github.com/mejdi14/Card-Switcher/blob/main/app/images/demo.gif" height="400" width="550" >
	</p>
	
## :art:Design inspiration
many thanks goes to [Kim Baschet](https://twitter.com/Kim_____B) for the beautiful design and animation


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
	implementation 'com.github.mejdi14:Card-Switcher:1.0.0'
}
```


## :fire:How to use

``` java
     SwitchedCard(SwitchedCardsData(
                            cardModifier = Modifier.size(200.dp, 350.dp),
                            triggerOnClick = false,
                            listener = animationListener,
                            topCardContent = { triggerAnimation ->
                                CardContent(
                                    imageResId = R.drawable.dog,
                                    text = "Main Card",
                                    onClick = triggerAnimation
                                )
                            },
                            bottomCardContent = { triggerAnimation ->
                                CardContent(
                                    imageResId = R.drawable.dog,
                                    text = "Details Card",
                                    onClick = triggerAnimation
                                )
                            }
                        ))
```

Animation Listener
-----

``` java
 val animationListener = object : SwitchedCardAnimationListener {
                            override fun onAnimationStart() {
                                // Animation started
                            }

                            override fun onAnimationEnd() {
                                // Animation finished
                            }
                        }
```
Animation duration
-----

``` java
 .animationDuration
```

Hold animation duration after separation
-----

``` java
 .timeBetweenAnimations
```

swipe option
-----

``` java
 .enableSwipe
 .swipeSensibility
```
trigger the animation start with a swipe gesture

animation direction 
-----
``` java
 .animationDirection
```
the default behaviour is that the top card goes right and the bottom card goes left when they separate, you can reverse that

Default click behaviour to trigger animation
-----
``` java
 .triggerOnClick
```
setting this value true will make the animation start when clicking on one of the cards
it's set to false by default so that you can start the animation from another composable using 'triggerAnimation' like in the example



## 🤝 Contributing

Contributions, issues and feature requests are welcome.<br />
Feel free to check [issues page] if you want to contribute.<br />


## Author

👤 **Mejdi Hafiane**

- profile: [@MejdiHafiane](https://twitter.com/mejdi141)

## Show your support

Please ⭐️ this repository if this project helped you!


## 📝 License

Copyright © 2019 [Mejdi Hafiane](https://github.com/mejdi14).<br />
This project is [MIT](https://github.com/mejdi14/readme-md-generator/blob/master/LICENSE) licensed.
