/*
Description
        An isogram is a word that has no repeating letters, consecutive or non-consecutive.
        Implement a function that determines whether a string that contains only letters is an isogram.
        Assume the empty string is an isogram. Ignore letter case.

        isIsogram("Dermatoglyphics") == true
        isIsogram("aba") == false
        isIsogram("moOse") == false -- ignore letter case*/


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Isograms {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("usage: isograms arg1");
            return;
        }
        System.out.println(isIsogram(args[0]));
    }

    public static boolean isIsogram(String s) {
        if (s.isEmpty()) {
            return true;
        }
        String[] arrayIsogram = s.toLowerCase().split("");
        Set<String> set = new HashSet<>(Arrays.asList(arrayIsogram));
        return (Pattern.matches("[a-zA-zа-яА-Я]+", s) && set.size() == arrayIsogram.length);
    }
}