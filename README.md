# weather-forecast

### Description

user can see current weather and weekly weather forecast 

# Technology used

## By using
* Kotlin
* MVVM
* Retrofit 2
* Material Design
* View Binding
* Coil (for image caching)
* Navigation Component

## Libraries

```bash
apply plugin: 'kotlin-android-extensions'

```
```bash
implementation 'androidx.appcompat:appcompat:1.1.0-alpha05'
```    
```bash  
    //ViewModel and LiveData
    implementation 'androidx.lifecycle:lifecycle-extensions:2.0.0'

    //Recyclerview & ConstraintLayout
    implementation 'androidx.constraintlayout:constraintlayout:2.0.0-beta6'

    // Retrofit
    implementation "com.squareup.retrofit2:retrofit:2.5.0"

     //Moshi
    implementation 'com.squareup.retrofit2:converter-moshi:2.9.0'

// Dynamic Feature Module Support
    implementation "androidx.navigation:navigation-dynamic-features-fragment:$nav_version"
    
//Coil image loading library for Android backed by Kotlin   
   implementation("io.coil-kt:coil:0.11.0")
```


```bash
 // Navigation Component
  def nav_version = '2.3.0'
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"
```
