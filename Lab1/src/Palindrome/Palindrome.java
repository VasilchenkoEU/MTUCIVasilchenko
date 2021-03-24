package Palindrome;

public class Palindrome {
    public static void main(String[] args){
        for (int i = 0; i < args.length; i++){
            String s = args[i];
            System.out.println(isPalindrome(s));
        }
    }

    // Метод для переворачивания слов
    public static String reverseString(String s){
        String finalWord = ""; // локальная переменная для хранения нового слова
        for (int i = s.length() - 1; i >= 0; i--){
            finalWord = finalWord + s.charAt(i);
        }
        return finalWord;
    }

    // Метод для проверки исходного слова с полученным
    public static boolean isPalindrome(String s){
        return reverseString(s).equals(s);
    }
}