/**This is the debug class it servers one purpos,  main ma set debug to true or false if in true thern debug staments will run,
 classes can cheack if in debug mode do decide wether or not to print extra information.
**/

public class Debug {
  
  //debug is false by deafult
  private static boolean isDebugMode=false;
  
  //lets you saet the debug to true or false
  public static void setDebugMode(boolean b) {
    isDebugMode = b;
  }
  
  //returns the curent debug mode
  public static boolean isDebug() {
    return isDebugMode;
  }
  
  //some other stuff for it.
  
  //turns debug mode on.
  public static void on() {
    isDebugMode = true;
  }
  
  //tuens debug mode off
  public static void off() {
    isDebugMode = false;
  }

  //same as above but has a short name.
  public static boolean mode() {
    return isDebugMode;
  }
}