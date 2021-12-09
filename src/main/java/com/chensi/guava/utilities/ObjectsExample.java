package com.chensi.guava.utilities;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import java.util.Calendar;

/***********************************
 * @author chensi
 * @date 2021/12/7 14:34
 ***********************************/
public class ObjectsExample {
	public static void main(String[] args) {
		final Guava guava = new Guava("Google", "23.0", Calendar.getInstance());
		System.out.println(guava);
		System.out.println(guava.hashCode());

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);
		final Guava guava2 = new Guava("Google", "23.0", calendar);
		System.out.println(guava.compareTo(guava2));
	}

	static class Guava {
		private final String manufacturer;
		private final String version;
		private final Calendar releaseDate;

		public Guava(String manufacturer, String version, Calendar releaseDate) {
			this.manufacturer = manufacturer;
			this.version = version;
			this.releaseDate = releaseDate;
		}

		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this)
					.omitNullValues()
					.add("manufacturer", manufacturer)
					.add("version", version)
					.add("releaseDate", releaseDate)
					.toString();
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Guava guava = (Guava) o;
			return Objects.equal(manufacturer, guava.manufacturer) && Objects.equal(version, guava.version) && Objects.equal(releaseDate, guava.releaseDate);
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(manufacturer, version, releaseDate);
		}

		public int compareTo(Guava o) {
			return ComparisonChain.start().compare(this.manufacturer, o.manufacturer)
					.compare(this.version, o.version)
					.compare(this.releaseDate, o.releaseDate).result();
		}

		//		@Override
//		public boolean equals(Object o) {
//			if (this == o) return true;
//			if (o == null || getClass() != o.getClass()) return false;
//
//			Guava guava = (Guava) o;
//
//			if (manufacturer != null ? !manufacturer.equals(guava.manufacturer) : guava.manufacturer != null)
//				return false;
//			if (version != null ? !version.equals(guava.version) : guava.version != null) return false;
//			return releaseDate != null ? releaseDate.equals(guava.releaseDate) : guava.releaseDate == null;
//		}
//
//		@Override
//		public int hashCode() {
//			int result = manufacturer != null ? manufacturer.hashCode() : 0;
//			result = 31 * result + (version != null ? version.hashCode() : 0);
//			result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
//			return result;
//		}
	}
}
