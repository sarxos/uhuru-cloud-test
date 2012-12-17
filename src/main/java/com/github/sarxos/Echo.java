package com.github.sarxos;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Echo {

	public int[] getIntegers(int i) {
		int[] ints = new int[i];
		for (i = 0; i < ints.length; i++) {
			ints[i] = i;
		}
		return ints;
	}

	public String[] getStrings() {
		String[] strings = new String[5];
		for (int i = 0; i < strings.length; i++) {
			strings[i] = Integer.toString(i) + "-uuutest";
		}
		return strings;
	}
}
