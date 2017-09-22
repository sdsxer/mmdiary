package com.sdsxer.mmdiary.utils;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class TokenGenerator {

  private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  private static final String lower = upper.toLowerCase(Locale.ROOT);

  private static final String digits = "0123456789";

  private static final String alphanum = upper + lower + digits;

  private final Random random;

  private final char[] symbols;

  private final char[] buf;

  private static TokenGenerator instance = new TokenGenerator(21, new SecureRandom(), alphanum);

  private TokenGenerator(int length, Random random, String symbols) {
    if (length < 1) throw new IllegalArgumentException();
    if (symbols.length() < 2) throw new IllegalArgumentException();
    this.random = Objects.requireNonNull(random);
    this.symbols = symbols.toCharArray();
    this.buf = new char[length];
  }

  public static String next() {
    for (int idx = 0; idx < instance.buf.length; ++idx) {
      instance.buf[idx] = instance.symbols[instance.random.nextInt(instance.symbols.length)];
    }
    return new String(instance.buf);
  }
}
