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
/**
 * Represents a single microwave. Has methods to close and open
 * doors, cook (start or add time), and process clock ticks.
 *
 */
public class Microwave {
  public enum States {DOOR_CLOSED_STATE, DOOR_OPENED_STATE, COOKING_STATE};
  private int timeRemaining;
  private States currentState;
  private static Microwave instance;
  private MicrowaveDisplay display;
  /**
   * Sets the state to closed door state, turns light off, and creates the GUI display.
   * 
   */
  private Microwave() {
    currentState = States.DOOR_CLOSED_STATE;
    timeRemaining = 0;
    display = new GUIDisplay();
    display.setMicrowave(this);
    display.timeRemaining(timeRemaining);
    display.turnLightOff(); display.doorClosed();
    display.notCooking();
  }
  /**
   * For the singleton pattern
   * @return the instance
   */
  public static Microwave instance() {
    if (instance == null) {
      return instance = new Microwave();
    }
    return instance;
  }
  /**
   * processes the door close event. Turns the light off and
   * sets time to 0.
   */
  public void processDoorClose() {
    if (currentState == States.DOOR_OPENED_STATE) {
      currentState = States.DOOR_CLOSED_STATE;
      display.doorClosed();
      display.notCooking();
      display.turnLightOff();
      timeRemaining = 0;
      display.timeRemaining(0);
    }
  }
  /**
   * processes the door opoen event. Turns the light on and
   * cancels cooking.
   */
  public void processDoorOpen() {
    if (currentState == States.DOOR_CLOSED_STATE || currentState == States.COOKING_STATE) {
      currentState = States.DOOR_OPENED_STATE;
      display.doorOpened();
      display.notCooking();
      display.turnLightOn();
      timeRemaining = 0;
      display.timeRemaining(0);
    }
  }
  /**
   * When the cook request is given, it starts cooking and turns the
   * light on.
   */
  public void processCookRequest() {
    if (currentState == States.DOOR_CLOSED_STATE) {
      currentState = States.COOKING_STATE;
      display.startCooking();
      display.turnLightOn();
      timeRemaining = 10;
      display.timeRemaining(timeRemaining);
    } else if (currentState == States.COOKING_STATE) {
      timeRemaining += 10;
      display.timeRemaining(timeRemaining);
    }
  }
  /**
   * For each clock tick, the time to cook is decremented, if applicable.
   * If cooking and timer runs out, the dispaly now is set to not cooking
   * and the light is turned off.
   * 
   */
  public void clockTicked(){
    if (currentState == States.COOKING_STATE){
      timeRemaining--;
      display.timeRemaining(timeRemaining);
      if (timeRemaining == 0) {
        currentState = States.DOOR_CLOSED_STATE;
        display.notCooking();
        display.turnLightOff();
      }
    }
  }
  /**
   * Starts the microwave by starting the clock. The Microwave itself is a singleton,
   * which will get instantiated by the Clock class
   * @param s not used
   */
  public static void main(String[] s) {
    Clock clock = new Clock();
  }

}