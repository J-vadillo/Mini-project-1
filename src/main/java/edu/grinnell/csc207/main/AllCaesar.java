
package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * Author Jana Vadillo.
 * for csc207
 * program will take a phrase and return all possible ceasar transformations
 */
public class AllCaesar {
  /**
   * main functon used to launch the program.
   *
   * @param args used as user input, should have two parts, encode/decode and a
   *             phrase
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    boolean encode = true;

    if (args.length != 2) {
      System.err.println("Error: Incorrect number of parameters");
      System.exit(0);
    } // checks to ensure correct amount of parameters
    String str = args[1];

    if ("encode".equals(args[0])) {
      encode = true;
    } else if ("decode".equals(args[0])) {
      encode = false;
    } else {
      System.err.println("Error: Invalid option: \""
          + args[0] + "\". Valid options are \"encode\" or \"decode\".");
      System.exit(0);
    } // if an invalid encode decode option is chosen, prompts user to write a correct
      // one

    if (!(CipherUtils.checkLowercase(args[1]))) {
      System.err.println("Error: String contains characters other than lowercase letters.");
      System.exit(0);
    } // if non lowercase word is chosen gives an error and propts user to write a
      // corrected version

    for (char ch = 'a'; ch <= 'z'; ch++) {
      if (encode) {
        pen.printf("n = %c: %s\n", ch, CipherUtils.caesarEncrypt(str, ch));
      } else {
        pen.printf("n = %c: %s\n", ch, CipherUtils.caesarDecrypt(str, ch));
      } // checks if we are encrypting or decrypting and uses the propper form
    } // goes through the word and decodes every element in the alphabet
    pen.close();
  } // end of main
} // end of class
