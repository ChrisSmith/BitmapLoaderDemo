## Description ##
This is a sample project for [BitmapLoader](https://github.com/ChrisSmith/BitmapLoader).

BitmapLoader is an Android Library project that makes it easier to asynchronously load over HTTP. The library also caches the requests to either external or internal storage depending on what is available.

## Snipit ##
~~~~~~ java
//onCreate()
BitmapLoader mBitmapLoader  = new BitmapLoader(this);
AsyncImageView mImageView = (AsyncImageView) findViewById(R.id.imageView1);

//Whenever you need to load an image
mImageView.setImageUrl("http://somedomain.com/awesomeimage.jpg", bitmapLoader);
~~~~~~

Simple right? Thats how things should be. Please file issues and feature requests, I'm only getting started with this.