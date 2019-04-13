package br.com.pocbackend.springboot.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Teste {

	static List<String> lista;

	public static void popula(List<String> lista) {

		orderedJunctionBoxes(4, lista);
	}

	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	public static List<String> orderedJunctionBoxes(int numberOfBoxes, List<String> boxList)

	{

		List<String> l = new ArrayList<String>();
		
		List<String> l2 = new ArrayList<String>();
		
		List<String> lnova = new ArrayList<String>();
		
		String[] val;
		for(String s: boxList) {
			l.add(s.split(" ")[0].toLowerCase());
			l2.add(s.split(" ")[1]);
		}
		
		l.sort(Comparator.naturalOrder());
		l2.sort(Comparator.naturalOrder());
		
		for(String sn: l) {
			for(String s: boxList) {
				if(s.split(" ")[0].equals(sn)) {
					lnova.add(s);
					
				}
			}
		}
		
		for(String sn: l2) {
			for(String s: boxList) {
				if(s.split(" ")[1].equals(sn)) {
					lnova.add(s);
					
				}
			}
		}
		
		
		System.out.println(l);
		System.out.println(l2);
		System.out.println(lnova);
	
		
		// WRITE YOUR CODE HERE
		return lnova;
	}
	// METHOD SIGNATURE ENDS

	public static void main(String args[]) {
		
		lista = new ArrayList<String>();
		lista.add("mi2 jog mid pet");
		lista.add("wz3 34 54 398");
		lista.add("a1 alps cow bar");
		lista.add("x4 45 21 7");
		
		popula(lista);
		
		lista = new ArrayList<String>();
		lista.add("t2 13 121 98");
		lista.add("r1 box ape bit");
		lista.add("b4 xi me nu");
		lista.add("br8 eat nim did");
		lista.add("w1 has uni gry");
		lista.add("f3 52 54 31");
		
		popula(lista);
	}

}
