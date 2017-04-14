# DisplayMandelbrot
The Mandelbrot Set Explorer is an application intended to display and explore the Mandelbrot Set in a variety of visual techniques.  The application is programmed 100% in the Java language, and takes advantage of the Java Swing libraries to achieve its beautiful display and design.

The Mandelbrot Set is a set achieved from the quirks of mathematical behavior.  While a better, full explanation of how the Set is derived can be found at https://en.wikipedia.org/wiki/Mandelbrot_set , a brief explanation of the set and how it is represented in the application will be given here.

Let’s begin by defining a function, f(z) = z^2 + c, where both z and c are complex numbers.  

In mathematics, complex numbers are those which consist of both a real and imaginary part, the imaginary part being some real number multiplied by i, the square root of -1.  Complex numbers are often denoted as a + bi, where a is the real part of the number and b is the complex part of the number.

The Mandelbrot set is obtained based on the behavior when some complex number c is chosen for the function f(z) given above.  In the case of the Mandelbrot set, z always begins at the value of 0 (0 + 0i).  After f(0) is obtained, f(0) is plugged back into the function f(f(0)).

The “plugging back in” is done many upon many times until the value f(z) begins to diverge and grow exponentially.  It has been proven that once the absolute value of a complex number (https://goo.gl/xJpSKX) gets larger than 2, the function will diverge and grow exponentially.

Traditionally, the Mandelbrot Set was used to display the contrasts between values of c that did not grow exponentially and those that did.  In most programs, the x-axis represents the real part of the number, or the a value, and the y-axis represents the imaginary part of the number, or the b value.  Such is the case with this application.

However, many modern applications displaying the Mandelbrot set will not simply assign black to points that lie within the set and white to those that don’t (or vice versa).  Instead, most modern applications will assign a multitude of colors that respond to the number of iterations it takes a point to grow exponentially and diverge out of the Mandelbrot set.  Such is the case with this application.

In this application, the user drags around a highlighted rectangle which is a miniature version of the overall program’s dimensions, exactly one-tenth the width and the height of its surrounding frame in size.  When the user clicks, the entire window scales down to the selection to compute the values of the Mandelbrot set within the selection (thus making those points at a higher resolution as well).

The Mandelbrot Set is just one of many of the fascinating derivations of quirks of advanced mathematics, and its beauty goes to illustrate the complex and amazing results mathematics can yield when diligent human minds are willing to explore its innermost secrets.
