package com.myland.framework.authority.pattern;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SunYanQing
 * @version 1.0
 * @date 2019/6/3 11:12
 */
public class PatternLayout {

	/**
	 * 模板
	 */
	private String pattern;

	private int patternLength;

	/**
	 * 用来对模板进行循环时存储当前模板的下标
	 */
	private int i;

	/**
	 * 当前的原生词
	 */
	private StringBuilder currentLiteral = new StringBuilder(32);

	private List<PatternParser> parsers = new ArrayList<>(5);

	public PatternLayout(String pattern) {
		this.pattern = pattern;
		this.patternLength = pattern.length();
		this.parse();
	}

	private void parse() {
		int state = 0;

		while (this.i < this.patternLength) {
			char c = this.pattern.charAt(this.i++);
			switch (state) {
				case 0:
					if (this.i == this.patternLength) {
						this.currentLiteral.append(c);
						if (currentLiteral.length() != 0) {
							parsers.add(new LiteralPatternParser(currentLiteral.toString()));
						}
					} else if (c == '%') {
						switch (pattern.charAt(i)) {
							case '%':
								currentLiteral.append(c);
								++i;
								break;
							default:
								if (currentLiteral.length() != 0) {
									parsers.add(new LiteralPatternParser(currentLiteral.toString()));
								}

								currentLiteral.setLength(0);
								currentLiteral.append(c);
								state = 1;
						}
					} else {
						this.currentLiteral.append(c);
					}
					break;
				case 1:
					currentLiteral.append(c);
					switch (c) {
						case '.':
							state = 3;
							break;
						default:
							finalizeConverter(c);
							state = 0;
							currentLiteral.setLength(0);
					}
					break;
				default:
					break;
			}
		}
	}

	private void finalizeConverter(char c) {
		PatternParser pc;
		switch (c) {
			case 'd':
				DateFormat df = new SimpleDateFormat(extractOption());

				pc = new DatePatternConverter(df);
				this.currentLiteral.setLength(0);
				this.parsers.add(pc);
				break;
			default:
				break;
		}

	}

	private String extractOption() {
		if (this.i < this.patternLength && this.pattern.charAt(this.i) == '{') {
			int end = this.pattern.indexOf(125, this.i);
			if (end > this.i) {
				String r = this.pattern.substring(this.i + 1, end);
				this.i = end + 1;
				return r;
			}
		}

		return null;
	}

	public String format() {
		StringBuilder sb = new StringBuilder(20);
		for (PatternParser parser : parsers) {
			sb.append(parser.format());
		}
		return sb.toString();
	}
}
