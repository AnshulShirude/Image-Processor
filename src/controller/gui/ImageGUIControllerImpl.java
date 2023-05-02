package controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.commands.Load;
import controller.commands.Save;
import model.ImageModel;
import model.enums.FlipType;
import model.enums.GreyscaleComponentType;
import view.gui.ImageGUIView;

/**
 * An implementation of a controller for the GUI representation of this program. It has a model
 * and a GUI view, and it is an action listener itself for every part of the GUI view which has
 * action commands. It handles all the potential user input through the GUI view in a proper
 * manner by delegating tasks to the model and displaying things back on the GUI view.
 */
public class ImageGUIControllerImpl implements ActionListener, ImageGUIController {

  private final ImageModel model;
  private final ImageGUIView view;

  /**
   * A constructor for a ImageGUIControllerImpl which sets the fields appropriately and then sets
   * itself as the listener to the all the view components that need a listener and the
   * displays the view.
   *
   * @param model the model for this controller
   * @param view  the GUI view for this controller
   * @throws IllegalArgumentException if either the model or the view are null
   */
  public ImageGUIControllerImpl(ImageModel model, ImageGUIView view)
          throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Model or view cannot be null!");
    }

    this.model = model;
    this.view = view;
    this.view.setListener(this);
    this.view.display();
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    switch (event.getActionCommand()) {

      //read from the input textfield
      case "load": {
        final JFileChooser loadFileChooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PPM, PNG, JPG, & BMP Images", "ppm", "png", "jpg", "bmp");
        loadFileChooser.setFileFilter(filter);
        int loadRetValue = loadFileChooser.showOpenDialog(null);
        if (loadRetValue == JFileChooser.APPROVE_OPTION) {
          File f = loadFileChooser.getSelectedFile();
          new Load(f.getAbsolutePath(), "guiImage").execute(model);
          this.updateView();
        }
      }
      break;

      case "save":
        try {
          final JFileChooser saveFileChooser = new JFileChooser(".");
          int saveRetValue = saveFileChooser.showSaveDialog(null);
          if (saveRetValue == JFileChooser.APPROVE_OPTION) {
            File f = saveFileChooser.getSelectedFile();
            new Save(f.getAbsolutePath(), "guiImage").execute(model);
          }
        } catch (IllegalArgumentException e) {
          view.renderErrorMessage("An image must be loaded before it can be saved!");
        }
        break;

      case "brighten": {

        try {
          model.getImage("guiImage");
        } catch (IllegalArgumentException e) {
          view.renderErrorMessage("An image must be loaded before it can be brightened!");
          return;
        }

        // trying to get the increment and then parsing it to an int
        String increment = view.getUserInput("Enter brightening increment: ");
        int value;
        try {
          value = Integer.parseInt(increment);
        } catch (NumberFormatException e) {
          view.renderErrorMessage("Brightness increment must be a positive integer!");
          return;
        }

        model.brighten(value, "guiImage", "guiImage");
        this.updateView();
        break;
      }

      case "darken": {

        try {
          model.getImage("guiImage");
        } catch (IllegalArgumentException e) {
          view.renderErrorMessage("An image must be loaded before it can be darkened!");
          return;
        }

        // trying to get the increment and then parsing it to an int
        String increment = view.getUserInput("Enter darkening increment: ");
        int value;
        try {
          value = Integer.parseInt(increment);
        } catch (NumberFormatException e) {
          view.renderErrorMessage("Brightness increment must be a positive integer!");
          return;
        }

        model.darken(value, "guiImage", "guiImage");
        this.updateView();
        break;
      }

      case "horizontal flip":
        try {
          model.flip(FlipType.HORIZONTAL, "guiImage", "guiImage");
          this.updateView();
        } catch (IllegalArgumentException e) {
          view.renderErrorMessage("An image must be loaded before it can be manipulated or saved!");
        }
        break;

      case "vertical flip":
        try {
          model.flip(FlipType.VERTICAL, "guiImage", "guiImage");
          this.updateView();
        } catch (IllegalArgumentException e) {
          view.renderErrorMessage("An image must be loaded before it can be manipulated or saved!");
        }
        break;

      case "red component":
        try {
          model.greyscaleComponent(GreyscaleComponentType.RED, "guiImage", "guiImage");
          this.updateView();
        } catch (IllegalArgumentException e) {
          view.renderErrorMessage("An image must be loaded before it can be manipulated or saved!");
        }
        break;

      case "green component":
        try {
          model.greyscaleComponent(GreyscaleComponentType.GREEN, "guiImage", "guiImage");
          this.updateView();
        } catch (IllegalArgumentException e) {
          view.renderErrorMessage("An image must be loaded before it can be manipulated or saved!");
        }
        break;

      case "blue component":
        try {
          model.greyscaleComponent(GreyscaleComponentType.BLUE, "guiImage", "guiImage");
          this.updateView();
        } catch (IllegalArgumentException e) {
          view.renderErrorMessage("An image must be loaded before it can be manipulated or saved!");
        }
        break;

      case "value component":
        try {
          model.greyscaleComponent(GreyscaleComponentType.VALUE, "guiImage", "guiImage");
          this.updateView();
        } catch (IllegalArgumentException e) {
          view.renderErrorMessage("An image must be loaded before it can be manipulated or saved!");
        }
        break;

      case "intensity component":
        try {
          model.greyscaleComponent(GreyscaleComponentType.INTENSITY, "guiImage", "guiImage");
          this.updateView();
        } catch (IllegalArgumentException e) {
          view.renderErrorMessage("An image must be loaded before it can be manipulated or saved!");
        }
        break;

      case "luma component":
        try {
          model.greyscaleComponent(GreyscaleComponentType.LUMA, "guiImage", "guiImage");
          this.updateView();
        } catch (IllegalArgumentException e) {
          view.renderErrorMessage("An image must be loaded before it can be manipulated or saved!");
        }
        break;

      case "blur":
        try {
          model.blur("guiImage", "guiImage");
          this.updateView();
        } catch (IllegalArgumentException e) {
          view.renderErrorMessage("An image must be loaded before it can be manipulated or saved!");
        }
        break;

      case "sharpen":
        try {
          model.sharpen("guiImage", "guiImage");
          this.updateView();
        } catch (IllegalArgumentException e) {
          view.renderErrorMessage("An image must be loaded before it can be manipulated or saved!");
        }
        break;

      case "greyscale":
        try {
          model.greyscaleColorTransformation("guiImage", "guiImage");
          this.updateView();
        } catch (IllegalArgumentException e) {
          view.renderErrorMessage("An image must be loaded before it can be manipulated or saved!");
        }
        break;

      case "sepia":
        try {
          model.sepia("guiImage", "guiImage");
          this.updateView();
        } catch (IllegalArgumentException e) {
          view.renderErrorMessage("An image must be loaded before it can be manipulated or saved!");
        }
        break;

      case "downsize": {

        try {
          model.getImage("guiImage");
        } catch (IllegalArgumentException e) {
          view.renderErrorMessage("An image must be loaded before it can be downsized!");
          return;
        }

        // trying to get the downsizing increment and then parsing it to an int
        String s1 = view.getUserInput("Enter width percent: ");
        String s2 = view.getUserInput("Enter height percent: ");
        int widthPercent;
        int heightPercent;
        try {
          widthPercent = Integer.parseInt(s1);
          heightPercent = Integer.parseInt(s2);
        } catch (NumberFormatException e) {
          view.renderErrorMessage("Brightness increment must be a positive integer!");
          return;
        }

        model.downsize(widthPercent, heightPercent, "guiImage", "guiImage");
        this.updateView();
        break;
      }

      default:
        throw new IllegalArgumentException("Invalid action event!");
    }
  }

  /**
   * Ensures that the view is drawing the proper image and histogram after one has been loaded or
   * manipulated.
   */
  private void updateView() {
    view.updateImage(model.getImage("guiImage"));
    view.updateHistogram(model.getImage("guiImage"));
    view.refresh();
  }
}
