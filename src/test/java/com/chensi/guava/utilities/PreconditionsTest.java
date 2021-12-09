package com.chensi.guava.utilities;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.fail;


/***********************************
 * @author chensi
 * @date 2021/12/7 10:55
 ***********************************/

/**
 * Precondition
 * Object
 * assert key word(statement)
 */
public class PreconditionsTest {

	private void checkNotNull(final List<String> list) {
		Preconditions.checkNotNull(list);
	}

	@Test(expected = NullPointerException.class)
	public void testCheckNotNull() {
		checkNotNull(null);
	}

	private void checkNotNullWithMessage(final List<String> list) {
		Preconditions.checkNotNull(list, "The list should not be null.");
	}

	@Test
	public void testCheckNotNullWithMessage() {
		try {
			checkNotNullWithMessage(null);
		} catch (Exception e) {
			assertThat(e.getMessage(), equalTo("The list should not be null."));
		}
	}

	private void checkNotNullWithFormatMessage(final List<String> list) {
		Preconditions.checkNotNull(list, "The list should not be null and the size must be %s", 2);
	}

	@Test
	public void testCheckNotNullWithFormatMessage() {
		try {
			checkNotNullWithFormatMessage(null);
		} catch (Exception e) {
			assertThat(e.getMessage(), equalTo("The list should not be null and the size must be 2"));
		}
	}

	//判断状态
	@Test
	public void testCheckState() {
		try {
			final String state = "A";
			Preconditions.checkState(state.equals("B"), "The state is illegal.");
			fail("should not process to here.");
		} catch (Exception e) {
			assertThat(e.getMessage(), equalTo("The state is illegal."));
		}
	}

	@Test
	public void testCheckIndex() {
		try {
			List<String> list = ImmutableList.of();
			Preconditions.checkElementIndex(10, list.size());
		} catch (Exception e) {
//			assertThat(e, is(IndexOutOfBoundsException.class));
		}
	}

	//java8
	@Test(expected = NullPointerException.class)
	public void testaByObjects() {
		Objects.requireNonNull(null);
	}

	@Test(expected = AssertionError.class)
	public void testAssert() {
		List<String> list = null;
//		assert 1 == 1;
		assert list != null;
	}

	@Test(expected = AssertionError.class)
	public void testAssertWithMessage() {
		try {
			List<String> list = null;
			assert list != null : "The list should not be null.";
		} catch (Exception e) {
			assertThat(e.getMessage(), equalTo("The list should not be null."));
		}
	}
}



























