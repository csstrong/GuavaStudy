package com.chensi.guava.utilities;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

/***********************************
 * @author chensi
 * @date 2021/12/6 20:07
 ***********************************/
public class SplitterTest {

	//按照指定字符进行分割
	@Test
	public void testSplitOnSplit() {
		List<String> result = Splitter.on("|").splitToList("hello|world");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(2));
		System.out.println(result);
		assertThat(result.get(0), equalTo("hello"));
		assertThat(result.get(1), equalTo("world"));
	}

	//omitEmptyStrings去除空格字符
	@Test
	public void testSplit_On_Split_OmitEmpty() {
		List<String> result = Splitter.on("|").splitToList("hello|world||||");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(6));

		result = Splitter.on("|").omitEmptyStrings().splitToList("hello|world||||");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(2));
	}


	//trimResult去除两侧的空格
	@Test
	public void testSplit_On_Split_OmitEmpty_TrimResult() {
		List<String> result = Splitter.on("|").omitEmptyStrings().splitToList("hello | world||||");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(2));
		assertThat(result.get(0), equalTo("hello "));
		assertThat(result.get(1), equalTo(" world"));

		result = Splitter.on("|").trimResults().splitToList("hello | world||||");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(6));
		assertThat(result.get(0), equalTo("hello"));
		assertThat(result.get(1), equalTo("world"));
	}

	/**
	 * aaaabbbbccccdddd
	 */
	@Test
	public void testSplitFixLength() {
		List<String> result = Splitter.fixedLength(4).splitToList("aaaabbbbccccdddd");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(4));
		assertThat(result.get(0), equalTo("aaaa"));
		assertThat(result.get(1), equalTo("bbbb"));
		assertThat(result.get(2), equalTo("cccc"));
		assertThat(result.get(3), equalTo("dddd"));
	}


	//limit限制大小
	@Test
	public void testSplitOnSplitLimit() {
		List<String> result = Splitter.on("#").limit(3).splitToList("hello#world#java#google#scala");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(3));
		assertThat(result.get(0), equalTo("hello"));
		assertThat(result.get(1), equalTo("world"));
		assertThat(result.get(2), equalTo("java#google#scala"));
	}

	//传入正则的字符串
	@Test
	public void testSplitOnpatternString() {
		List<String> result = Splitter.onPattern("\\|").trimResults().omitEmptyStrings()
				.splitToList("hello | world|||");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(2));
		assertThat(result.get(0), equalTo("hello"));
		assertThat(result.get(1), equalTo("world"));
	}

	//Pattern.compile("\\|")
	@Test
	public void testSplitOnpatter() {
		List<String> result = Splitter.on(Pattern.compile("\\|")).trimResults().omitEmptyStrings()
				.splitToList("hello | world|||");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(2));
		assertThat(result.get(0), equalTo("hello"));
		assertThat(result.get(1), equalTo("world"));
	}

	//withKeyValueSeparator("=")
	@Test
	public void testSplitOnSplitToMap() {
		Map<String, String> result = Splitter.on(Pattern.compile("\\|")).trimResults()
				.omitEmptyStrings()
				.withKeyValueSeparator("=")
				.split("hello=HELLO | world=WORLD|||");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(2));
//        System.out.println(result);
		assertThat(result.get("hello"), equalTo("HELLO"));
		assertThat(result.get("world"), equalTo("WORLD"));
	}
}

























