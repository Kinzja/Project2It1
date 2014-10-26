/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * GUI to implement the MicrowaveDisplay interface
 * A pretty elementary interface
 *
 */
public class GUIDisplay extends JFrame implements ActionListener, MicrowaveDisplay {
  private Microwave microwave;
  private JButton doorCloser = new JButton("close door");
  private JButton doorOpener = new JButton("open door");
  private JButton cookButton = new JButton("cook");
  private JLabel doorStatus = new JLabel("Door Closed");
  private JLabel timerValue = new JLabel("            ");
  private JLabel lightStatus = new JLabel("Light Off");
  private JLabel cookingStatus = new JLabel("Not cooking");
  /**
   * Do the usual layout of the frame
   */
  public GUIDisplay() {
    super("Microwave");
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
        System.exit(0);
      }
    });
    getContentPane().setLayout(new FlowLayout());
    getContentPane().add(doorStatus);
    getContentPane().add(lightStatus);
    getContentPane().add(timerValue);
    getContentPane().add(cookingStatus);
    getContentPane().add(doorCloser);
    getContentPane().add(doorOpener);
    getContentPane().add(cookButton);
    doorCloser.addActionListener(this);
    doorOpener.addActionListener(this);
    cookButton.addActionListener(this);
    pack();
    setVisible(true);
  }
  /**
   * Process the door buttons (closer and opener) and cook button
   * by simply calling the Microwave object's methods.
   * 
   */
  public void actionPerformed(ActionEvent event) {
    if (event.getSource().equals(doorCloser)) {
      microwave.processDoorClose();
    } else if (event.getSource().equals(doorOpener)) {
      microwave.processDoorOpen();
    } else if (event.getSource().equals(cookButton)) {
      microwave.processCookRequest();
    }
  }
  /**
   * Indicate that the light is on by displaying a piece of text
   */
  public void turnLightOn() {
    lightStatus.setText("Light On");
  }
  /**
   * Indicate that the light is off by displaying a piece of text
   */
  public void turnLightOff() {
    lightStatus.setText("Light Off");
  }
  /**
   * Indicate that the door is closed by displaying a piece of text
   */
  public void doorClosed() {
    doorStatus.setText("Door Closed");
  }
  /**
   * Indicate that the door is open by displaying a piece of text
   */
  public void doorOpened() {
    doorStatus.setText("Door Opened");
  }
  /**
   * Display the remaining time
   * @param value the remaining time
   */
  public void timeRemaining(int value) {
    timerValue.setText(" " + value);
  }
  /**
   * Indicate that the cooking is in progress by displaying a piece of text
   */
  public void startCooking() {
    cookingStatus.setText("Cooking Started");
  }
  /**
   * Indicate that the cooking has ended by displaying a piece of text
   */
  public void notCooking() {
    cookingStatus.setText("Not Cooking");
  }

  /**
   * Remember the Microwave object
   * @param microwave the Microwave object
   */
  public void setMicrowave(Microwave microwave) {
    this.microwave = microwave;
  }
}
