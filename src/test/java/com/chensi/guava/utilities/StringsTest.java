package com.chensi.guava.utilities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import java.nio.charset.Charset;
import org.junit.Test;

/***********************************
 * @author chensi
 * @date 2021/12/7 15:00
 ***********************************/
public class StringsTest {

  //emptyToNull()、nullToEmpty()
  @Test
  public void testStringMethod() {
    assertThat(Strings.emptyToNull(""), nullValue());
    assertThat(Strings.nullToEmpty(null), equalTo(""));
    assertThat(Strings.nullToEmpty("hello"), equalTo("hello"));
    assertThat(Strings.commonPrefix("Hello", "Hit"), equalTo("H"));
    assertThat(Strings.commonSuffix("Hello", "Echo"), equalTo("o"));
    assertThat(Strings.repeat("Alex", 3), equalTo("AlexAlexAlex"));
    assertThat(Strings.isNullOrEmpty(null), equalTo(true));
    assertThat(Strings.isNullOrEmpty(""), equalTo(true));
    assertThat(Strings.padStart("Alex", 3, 'H'), equalTo("Alex"));
    assertThat(Strings.padStart("Alex", 5, 'H'), equalTo("HAlex"));
    assertThat(Strings.padEnd("Alex", 5, 'H'), equalTo("AlexH"));
  }

  @Test
  public void testCharsets() {
    Charset charset = Charset.forName("UTF-8");
    assertThat(Charsets.UTF_8, equalTo(charset));
  }

  @Test
  public void testCharMatcher() {
    assertThat(CharMatcher.javaDigit().matches('5'), equalTo(true));
    assertThat(CharMatcher.javaDigit().matches('x'), equalTo(false));

    assertThat(CharMatcher.is('A').countIn("Alex sharing the Google Guava to Us."),
        equalTo(1));
    assertThat(CharMatcher.breakingWhitespace().collapseFrom("        hello Guava             ",
        '*'), equalTo("*hello*Guava*"));

    assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).removeFrom("hello 234 world"),
        equalTo("helloworld"));

    assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).retainFrom("hello 234 world"),
        equalTo(" 234 "));
  }

}
