# Image Project

In this project, there are multiple functions that transform images.
Functions can be accessed using the [client](src/main/java/com/cybemos/client).

## Functions

Following functions were executed with this image as source :

![Reference image](documentation/images/lenna.png)

### Reverse function

Arguments to give to client :
```
reverse --source path-to-source --dest path-to-dest
```

Reverse function allow to reverse color for an image.

Example of reverse :

![Reverse](documentation/images/revserse/lenna.png)

### Blur function

Arguments to give to client :
```
blur --source path-to-source --dest path-to-dest --blurlevel blur-level
```

Blur function allow to blur an image.
The blur level can be configured to have an image more blurred.

Some example of blur :

![Blur level 2](documentation/images/blur/lenna_blur_level_2.png)
![Blur level 4](documentation/images/blur/lenna_blur_level_4.png)
![Blur level 7](documentation/images/blur/lenna_blur_level_7.png)
![Blur level 15](documentation/images/blur/lenna_blur_level_15.png)

### Shape function

Arguments to give to client :
```
shape [--unicolor] --source path-to-source --dest path-to-dest --blurlevel blur-level
```

Shape function allow to see shapes of an image.

Some example of Shape with a single color :

![Deepness 3](documentation/images/shape/unicolor_lenna_blur_level_3.png)
![Deepness 7](documentation/images/shape/unicolor_lenna_blur_level_7.png)

Some example of Shape with multiple colors :

![Deepness 3](documentation/images/shape/lenna_blur_level_3.png)
![Deepness 7](documentation/images/shape/lenna_blur_level_7.png)

### QuadTree function

Arguments to give to client :
```
quadtree --source path-to-source --dest path-to-dest  --deepness max-deepness
```

QuadTree function transform a source image into a [QuadTree](https://en.wikipedia.org/wiki/Quadtree).
Then re-transform it into an image to save it as a file.

Some example of QuadTree :

![Deepness 10](documentation/images/quadtree/lenna_deepness_10.png)
![Deepness 9](documentation/images/quadtree/lenna_deepness_9.png)
![Deepness 7](documentation/images/quadtree/lenna_deepness_7.png)
![Deepness 6](documentation/images/quadtree/lenna_deepness_6.png)