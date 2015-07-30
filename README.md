# NASA Astronomy Picture Of The Day (NAPOD)
Simple Android Application that uses NASA's [APOD API](https://api.nasa.gov/api.html#apod).

Main goal of this app was to get familiar with some essential libraries. The space pics are a nice bonus.

![](https://raw.githubusercontent.com/kokhouser/NAPOD/master/screenshot.png?token=ADvdVXEXpNKXEe7VNky8uRRBcipncU83ks5Vwg3NwA%3D%3D)

#FEATURES
- Current picture of the day will be loaded upon starting the app.
- Swipe right to obtain a random picture from sometime in the past year.
- Download the pictures, and set them as your device's wallpaper.
- [Muzei](https://play.google.com/store/apps/details?id=net.nurik.roman.muzei&hl=en) support: Select NAPOD as a wallpaper source, and a new NAPOD wallpaper would automatically be chosen every day. *Warning: NAPOD pictures are not guaranteed to be perfect wallpaper material.*
- As a bonus, as of Muzei 2.0, Android Wear watchface support is also provided with Muzei. Select "Muzei" on your Android Wear device to use the watchface.

#TODO
- Add ability to track pictures across multiple days instead of doing it randomly (limited by NASA's API).
- Add some form of gallery view.

#Libraries Used
- [Android Design Support Library](http://android-developers.blogspot.be/2015/05/android-design-support-library.html)

  `compile 'com.android.support:design:22.2.0'`
- [OKHTTP](http://square.github.io/okhttp/)

  `compile 'com.squareup.okhttp:okhttp:2.4.0'`
- [Retrofit](http://square.github.io/retrofit/)

  `compile 'com.squareup.retrofit:retrofit:1.9.0'`
- [GSON](https://github.com/google/gson)

  `compile 'com.google.code.gson:gson:2.3'`
- [Picasso](http://square.github.io/picasso/)

  `compile 'com.squareup.picasso:picasso:2.5.2'`
- [Material Icon Library](https://github.com/code-mc/material-icon-lib)

  `compile 'net.steamcrafted:materialiconlib:1.0.3'`
- [Butterknife](https://github.com/JakeWharton/butterknife)

  `compile 'com.jakewharton:butterknife:6.1.0'`
- [Muzei Live Wallpaper API](https://github.com/romannurik/muzei/wiki/API)

  `compile 'com.google.android.apps.muzei:muzei-api:+'`
