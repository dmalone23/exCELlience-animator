We created 2 supporting groups of classes for our models called IShapes and operation.
IShapes represents the types of shapes we can visualize in our model, these being
rectangular and circular shapes at this time. Operations class and its subclasses are function objects
meant to represent the different operations on the animation the user can use, these being move,
changing color, changing height, changing width. These are used by the model to animate the user
input. We use a enum to control the types of shapes the model can build these being circular
and rectangular.

In addition to adding feedback from our last assignment, we implemented the 3 views: one for representing
the animation as a block of text, one as a .svg file and one as a JSwing application.

We were confused about the desired specifications of assignment 6 so our code was pretty wrong.
We have since updated the IView interface to better fit the specifications outlined in Step 0.
A controller has been added to allow the views to be run in the proper way.
We updated the format of output of the SVG view and
the Textual view to output the information in the way the course staff wants. We redid how the model
will reset when the controller tells it. We did this by creating default values for shapes, by doing
this we can simplify the process and make it much more straight forward.

-----------------
Animations:
animation1.txt: sorts a series of rectangles according to height and then proceeds to change their colors.
animation2.txt: visualizes a basketball bouncing and then falling into a net, upon which the net changes color.