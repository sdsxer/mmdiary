package com.sdsxer.mmdiary.utils;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BasicTokenGenerator implements TokenGenerator {

  private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  private static final String lower = upper.toLowerCase(Locale.ROOT);

  private static final String digits = "0123456789";

  private static final String alphanum = upper + lower + digits;

  private final Random random;

  private final char[] symbols;

  private final char[] buf;

  public BasicTokenGenerator() {
    this.random = Objects.requireNonNull(new SecureRandom());
    this.symbols = alphanum.toCharArray();
    this.buf = new char[21];
  }

  @Override
  public String next() {
    for (int idx = 0; idx < buf.length; ++idx) {
      buf[idx] = symbols[random.nextInt(symbols.length)];
    }
    return new String(buf);
  }
}
