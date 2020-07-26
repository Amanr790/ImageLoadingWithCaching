# ImageLoadingWithCaching
An App which loads Images Using Cache Enabled

Add below line under dependencies{..} tag of app.gradle of your project

compile project(path: ':imageloader')

Load Your Image like given example below:-

ImageLoader.ImageLoaderWrapper wrapper = new ImageLoader.ImageLoaderWrapper();
       
       wrapper.with(imageView.getContext())
               .load(url)
               .resize(new ResizeOptions(500, 500))
               .placeholder(ContextCompat.getDrawable(imageView.getContext(), R.drawable.ic_launcher_foreground))
               .into(imageView);
                
 
