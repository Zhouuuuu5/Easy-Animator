# Final Project - The Easy Animator
---
This project builds an application that helps to create simple but effective 2D animations from shapes. The application will show animations generated manually or using any other program, unrelated to the application. It is built using the classic Model-View-Controller architecture. 
This `main()` method is the entry point for this program. The program needs to take inputs as command-line arguments. The command-line arguments will be of the form
`-in "name-of-animation-file" -view "type-of-view" -out "where-output-show-go" -speed "integer-ticks-per-second"`.

# Model
---
The model represents the animation. In theory it supports various 2D shapes, but this program mainly implements rectangles and ellipses. In addition, the model can add various animations, including moving, changing color and scaling.

## Model Interface
The model interface includes getters to get information about the model and all the operations that all models should support, including addShape, removeShape and addAnimation.

## ModelImpl
This class is used to implement the model interface.
First, the model information is stored in different data structures: all shapes are stored in the map (ordered, using LinkedHashMap), animations ordered by start time are stored in list, all animations are stored in the map (ordered, using LinkedHashMap), and the shape state of each frame is stored in list. Four getters are implemented to return the above required information.
Next, three model operations are implemented.
- **addShape**: Adds a new shape. If the name of the added shape already exists, an exception is thrown.
- **removeShape**: Removes the shape with the specified name and removes any information about the shape from the data structure. If the specified name does not exist, an exception is thrown.
- **addAnimation**: Adds an animation and update the animation of the shape in the data structure. Exceptions are thrown if the shape does not exist, if the animation time is not in the start and end interval, or if the animation time conflicts with an existing animation.

## Animation subpackage
This sub-package contains the relevant animation interface, abstract class, enumerated class and concrete classes.
- **Animation interface**: used to represent animations, contains getters that are supported by all animations, such as shape name, shape type, animation type, start time and end time.
- **AbstractAnimation**: abstract class that implements the getters of the animation interface. In addition, tweening methods are added to get the animation frames.
- **AnimationType**: enumerates all types of animations, including moving, changing color and scaling.
- **Move**: includes getter to get position for creating svg views. Overrides the toString method, used to create text views. The currentPosition method applies tweening to get the position at the specified time, used to create visual views.
- **ChangeColor**: includes getter to get RGB, used to create svg view. Overrides the toString method for creating text views. The currentColor method applies tweening to get the color at the specified time and is used to create visual views.
- **Scale**: includes getter to get the size, used to create svg view. Overrides the toString method for creating text views. The currentSize method applies tweening to get the size at the specified time for creating visual views.

## Shape subpackage
This sub-package includes related shape interface, abstract class, enumerated class and concrete classes.
- **Shape interface**: used to represent shapes. It contains getters and setters supported by all shapes, such as shape name, shape type, position, size, color, start time and end time. Also includes the copyShape method for converting shapes to frames.
- **AbstractShape**: abstract class implements the getters and setters of the shape interface, and adds tweening methods to get animated frames.
- **ShapeType**: enumerates all types of shapes, including rectangle and ellipse.
- **Rectangle**: overrides the cloneShape method and the toString method.
- **Oval**: overrides the cloneShape method and the toString method.

## Util subpackage
This subpackage contains some classes that are reused in the model.
- **Canvas**: This class represents the Canvas. The position of the canvas is represented by the coordinates in the upper left corner. The size of the canvas is represented by the width and height. Four getters are implemented to get the relevant parameters, as well as an override of the toString method.
- **Color**: This class represents the color of the shape. The color is in RGB format. Three getters are implemented to get the relevant parameters, and the toString method is overridden.
- **Position**: This class represents the position of the shape. It uses x and y coordinate to represent the position. Two getters are implemented to get the relevant parameters, and the toString method is overridden.
- **Size**: this class represents the size of the shape. For a rectangle, two parameters are the width and the height. For an oval, two parameters are the x radius and the y radius. There are two getters to get the size.

# View
---
The View package includes a view interface, four specific views and a panel for the VisualAnimationView.
- **View interface**: This interface represents the View and includes a display method for displaying the user interface.
- **VisualAnimationPanel**: This class extends JPanel and overrides paintComponent method to draw shapes for VisualAnimationView and PlaybackView.
- **TextualView**: This class converts shape and animation information into the specified text format for displaying in the console or saving as a text file. It first describes the shapes that are part of the animation and their details, and next it describes how the shapes will move as the animation progresses from start to finish. Overrides the display method and the toString method.
- **VisualAnimationView**: This class creates animations that can be played in a single pass, using the Java Swing GUI to display the animation. frame, panel, scroll pane and window refresh are created in Constructor. Override the display method .
- **SVGAnimationView**: This class converts the shape and animation information into the specified SVG format and display in the console or saved as an SVG file. The display method and toString method are overridden.
- **PlaybackView**: This class creates a visual view to play the animation and provides start, pause/continue, restart, loop, speed up and slow down function buttons. setEventListeners sets listeners for the above buttons. Methods are created to control start, pause/resume, restart, loop, speed up and slow down.

## Updates
Added tweening method for animation subpackage in model and applied tweening to three specific classes to get the state at specified time for getting animation complement frames in Modelmpl.

# Controller
---
The purpose of the controller is to mediate the interaction between the view and the model. The controller tells the view "Please use the following objects to react to events". There is one controller in this application.

## Controller interface
It contains all the operations that a controller should support. This interface includes two signatures. The run method runs the program, which will call different views based on different user commands. It contains all the operations that a controller should support. It implements the actionPerformed method required by the PlaybackView to perform different actions depending on which button the user clicks.

## ControllerImpl
This class implements the controller interface. run function calls different windows depending on the different view types given by the user. actionPerformed performs different PlaybackView methods depending on the commands given.

# Animator
---
## EasyAnimator
This main() method will be the entry point for the program. User will need to create an Application run configuration in Eclipse/IntelliJ that chooses cs5004.animator. In this run configuration, user can also specify command-line arguments, such as the file to read in, and the view name to use. The options for the view name are "text", "visual", "playback" and "svg".

## Util Subpackages
- **AnimationBuilder interface**: This AnimationBuilder interface is to connect the given parsing code with the model.
- **AnimationReader class**: A helper to read animation data and construct an animation from it.
- **AnimationBuilderImpl**: This class implements the AnimationBuilder interface. It constructs shape-by-shape and animation-by-animation from the input file.

## Update
Add AnimationBuilderImpl of Util subpackage in Animator, which constructs a shape-by-shape and animation-by-animation from the input file.
