package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * Author Jana Vadillo.
 * for csc207
 * program will take a phrase, a key, a cipher to decode it with,
 * and if to encode or decode and decodes it for you
 */
public class Cipher {
  /**
   * number of arguments taken in.
   */
  public static final int NUM_OF_ARGS = 4;
  /**
   * code to signify an invalid input.
   */
  public static final int INVALID = 3;
  /**
   * code to signify a valid all lowercase word.
   */
  public static final int VALID_WORD = 2;
  /**
   * code to signify input is encode or decode.
   */
  public static final int ENCODE_OR_DECODE = 1;
  /**
   * code to signify that the input is a ceasar or vigenere signifier.
   */
  public static final int CAESAR_OR_VIGENERE = 0;

  /**
   * main function used to launch the program.
   *
   * @param args arguments given by the user
   */
  public static void main(String[] args) {
    if (args.length != NUM_OF_ARGS) {
      System.err.println("Error: expected 4 parameters, recieved" + args.length);
      System.exit(0);
    } // checks if the necesarry amount of parameters where given
    // parameters to keep track of all the inputed values
    PrintWriter pen = new PrintWriter(System.out, true);
    boolean encode = true;
    String cipher = "";
    String key = "";
    String phrase = "";

    boolean phraseEntered = false;
    boolean keyEntered = false;
    boolean encodeEntered = false;

    for (int i = 0; i < args.length; i++) {
      int command = whichCommand(args[i]);

      if (command == INVALID) {
        System.err.println("Error:" + args[i] + "is an invalid command, make sure"
            + "your key and phrase are all lowercase and that a valid action is created");
        System.exit(0);
      } // if an invalid command (not all invalidlowercase or a cypher/encode) raise
        // error
      if (command == CAESAR_OR_VIGENERE) {
        cipher = args[i];
      } // if the command given is a ceaser or vigenere, set the cipher to its value
      if (command == ENCODE_OR_DECODE) {
        encodeEntered = true;
        if ("-encode".equals(args[i])) {
          encode = true;
        } else {
          encode = false;
        } // checks if the value is being encoded or decoded and sets encode to the
          // corresponding value
      } // end of checking if encode or decodes
      if (command == VALID_WORD) {
        if (!(phraseEntered)) {
          phrase = args[i];
          phraseEntered = true;
        } else {
          key = args[i];
          keyEntered = true;
        } // checks if the command was a validword ie all lowercases, and if so determines
          // if its a phrase or key
      } // same as above
    } // end of loop taking in all four arguments
    checkInputs(cipher, key, keyEntered, encodeEntered);
    String translatedPhrase;
    if ("-caesar".equals(cipher)) {
      char newKey = key.charAt(0);
      translatedPhrase = CipherUtils.caesarTransform(phrase, newKey, encode);
    } else {
      translatedPhrase = CipherUtils.vigenereTransform(phrase, key, encode);
    } // checks if to use ceasar or vigenere format, and simply plugs in the verified
      // keys and values

    pen.printf("%s\n", translatedPhrase);

    pen.close();

  } // end of main

  /**
   * checks all four inputs to make sure they are valid.
   *
   * @param cipher        the cipher string, used to ensure that if working with
   *                      ceasar key is a char
   * @param key           key used to verify that it is one charachter if in
   *                      ceasar cipher
   * @param keyEntered    checks if a key was entered and by extension a phrase as
   *                      this value becomes positive after phrase entered
   * @param encodeEntered checks if the encode/decode function was entered
   */
  public static void checkInputs(String cipher, String key, boolean keyEntered,
      boolean encodeEntered) {
    if (!(keyEntered)) {
      System.err.println("Error: no valid key and/or phrase where entered, please ensure"
          + "you entered 2 all lower case parameters as your phrase and key");
      System.exit(0);
    } // raises an error if no key and or phrase was entered

    if (!(encodeEntered)) {
      System.err.println("Error: No valid action specified.  "
          + "Legal values are '-encode' and '-decode");
      System.exit(0);
    } // checks to make sure encode was entered

    if ("".equals(cipher)) {
      System.err.println("Error: No valid cipher to decrypt specified.  Legal"
          + "values are '-caesar' and '-vigenere");
      System.exit(0);
    } // if the cipher is empty, no valid cipher has been enered yet, raise error

    if ((key.length() != 1) && ("-caesar".equals(cipher))) {
      System.err.println("Error: Caesar ciphers require a one-character key");
      System.exit(0);
    } // checks that a ceasar cipher is only one charachter

    if (key.length() == 0) {
      System.err.println("Error: key must be at least one charachter long");
      System.exit(0);
    } // makes sure that the key is at least one value long

  } // end of checking function

  /**
   * checks which command has been entered.
   *
   * @param arg the current argument being fed
   * @return noted at the end, returns 0,1,2,3 depending on what type of command
   *         was given
   */
  public static int whichCommand(String arg) {

    if (("-vigenere".equals(arg)) || ("-caesar".equals(arg))) {
      return CAESAR_OR_VIGENERE;
    } // returns 0 if cipher entered
    if (("-decode".equals(arg)) || ("-encode".equals(arg))) {
      return ENCODE_OR_DECODE;
    } // retrns 1 if an encode/decode was entered
    if (CipherUtils.checkLowercase(arg)) {
      return VALID_WORD;
    } // return 2 if its an all lowercase word
    return INVALID;

  } // returns 3 if no return yet ie invalud value
} // end of class
