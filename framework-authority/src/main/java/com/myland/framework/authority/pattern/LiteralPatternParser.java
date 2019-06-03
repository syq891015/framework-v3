package com.myland.framework.authority.pattern;

public class LiteralPatternParser extends PatternParser {

		private final String literal;

		LiteralPatternParser(String literal) {
			this.literal = literal;
		}

		@Override
		public String format() {
			return this.literal;
		}
	}