package edu.grinnell.csc207.util;

/**
 * Author Jana Vadillo.
 * for csc207
 * series of functions used for the ceasar cipher encoding and decoding ciphers
 */
public class CipherUtils {
  /**
   * length of the alphabet.
   */
  public static final int ALPHABET_LENGTH = 26;

  /**
   * function used to turn letters into their corresponding integers, only works
   * lowercase.
   *
   * @param letter the letter to be inputed
   * @return returns a corresponding integer
   */
  private static int letter2int(char letter) {
    int base = (int) 'a';
    int n = (int) letter;
    n -= base;
    return n; // takes in a letter and returns the corresponding integer
  } // end of function letter2int

  /**
   * turns integers into a corresponding letter in the alphabet.
   *
   * @param i the integer to be converted
   * @return returns corresponding lowercase charachter
   */
  private static char int2letter(int i) {
    int base = (int) 'a';
    i += base;
    char letter = (char) i;
    return letter;
  } // end of function int2letter

  /**
   * letter transform takes in a letter, and a key and transforms that letter by
   * that key.
   *
   * @param letter  the letter to be transformed, a charachter
   * @param key     the integer to be used for the key ie how much to shift by
   * @param encrypt // boolean stating if to encrypt (true) or decrypt (false)
   * @return returns a transformed charachter shifted by key
   */
  public static char letterTransform(char letter, int key, boolean encrypt) {
    int charInt = letter2int(letter);
    if (encrypt) {
      charInt = (charInt + key) % ALPHABET_LENGTH;
    } else {
      charInt = (charInt - key + ALPHABET_LENGTH) % ALPHABET_LENGTH;
    } // end of if else statement changing the way we shift the letter for
      // encryption/decription
    return int2letter(charInt);
  } // end of lettertransform function

  /**
   * a more general encript decrypt function for the ceasar cipher, used for both
   * encripting and decripting.
   *
   * @param str     //string to encrypt decript
   * @param letter  letter to use as a key to encript or decript
   * @param encrypt boolean stating if to encript or decript
   * @return transformed word
   */
  public static String caesarTransform(String str, char letter, boolean encrypt) {
    char[] strArray = str.toCharArray();
    int length = str.length();
    int key = letter2int(letter);
    for (int i = 0; i < length; i++) {
      strArray[i] = letterTransform(strArray[i], key, encrypt);
    } // for loop transforming each letter in the word
    String newStr = new String(strArray);

    return newStr;
  } // end of ceasartransform function

  /**
   * applies ceasar transform in encrypt mode.
   *
   * @param str    string to encript
   * @param letter letter to use as key
   * @return encripted message
   */
  public static String caesarEncrypt(String str, char letter) {

    return caesarTransform(str, letter, true);
  } // end of ceaserEncrypt function

  /**
   * same as above for decripting.
   *
   * @param str    string to decript
   * @param letter letter to use as key
   * @return decripted word
   */
  public static String caesarDecrypt(String str, char letter) {
    return caesarTransform(str, letter, false);
  } // end of ceasardecrypt function

  /**
   * same as Ceasardecript but using vigenere cipher.
   *
   * @param str     string to encript/decript
   * @param key     key string to use
   * @param encrypt boolean stating if to encript or decript
   * @return // returns transformed phrase
   */
  public static String vigenereTransform(String str, String key, boolean encrypt) {
    char[] strArray = str.toCharArray();
    int strLength = str.length();
    int keyLength = key.length();
    char[] keyArray = new char[strLength];

    if (keyLength < strLength) {
      char[] ogKeyArray = key.toCharArray();
      for (int i = 0; i < strLength; i++) {
        int keyLoc = i % keyLength;
        keyArray[i] = ogKeyArray[keyLoc];
      } // end of loop copying the key over and over again until it matches the length
        // to decript
    } else {
      keyArray = key.toCharArray();
    } // if the key is too short, the key array is modified and looped, otherwise it
      // is not

    for (int i = 0; i < strLength; i++) {
      strArray[i] = letterTransform(strArray[i], letter2int(keyArray[i]), encrypt);
    } // transforms each letter using the corresponding cipher and encripts if needed

    String newStr = new String(strArray);

    return newStr;
  } // end of function

  /**
   * runs vigenere transform in encript mode.
   *
   * @param str string to encode
   * @param key key used
   * @return returns encripted word
   */
  public static String vigenereEncrypt(String str, String key) {
    return vigenereTransform(str, key, true);
  } // end of function

  /**
   * runs vigenere transform in decript mode.
   *
   * @param str string to decript
   * @param key keyword to use
   * @return returns decripted phrase
   */
  public static String vigenereDecrypt(String str, String key) {
    return vigenereTransform(str, key, false);
  } // end if vigenere decrypt function

  /**
   * checks if a word is all lowercase charachters excluding "".
   *
   * @param str string to check
   * @return true or false depending on if its all lowercase or not
   */
  public static boolean checkLowercase(String str) {
    boolean lowercase = true;
    char[] strArray = str.toCharArray();
    int strLength = str.length();

    for (int i = 0; i < strLength; i++) {
      int currentChar = (int) strArray[i];
      int lowercaseStart = (int) 'a';
      int lowercaseEnd = (int) 'z';
      int quotation = (int) '"';
      if ((currentChar > lowercaseEnd) || (currentChar < lowercaseStart)) {
        if ((currentChar == quotation) && ((i == 0) || i == (strLength - 1))) {
          continue;
        } // allos an exeption for "" at the start and end
        lowercase = false;
      } // triggers a switch to not lowercase if lowercase detected

    } // works its way checking each letter to make sure its lowercase, making it
      // false if not
    return lowercase;
  } // end of function
} // end of class
