public class PalindromeChecker {

    // Функція для перевірки, чи слово є паліндромом
    public static boolean isPalindrome(String word) {
        // Видаляємо знаки пунктуації та перетворюємо на нижній регістр
        String cleanedWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

        // Перевірка, чи слово є паліндромом
        int left = 0;
        int right = cleanedWord.length() - 1;
        while (left < right) {
            if (cleanedWord.charAt(left) != cleanedWord.charAt(right)) {
                return false; // не є паліндромом
            }
            left++;
            right--;
        }
        return true; // є паліндромом
    }

    public static void main(String[] args) {
        // Приклад тестування
        String[] testWords = {"radar", "level", "Java", "palindrome", "A man, a plan, a canal, Panama!"};

        for (String word : testWords) {
            if (isPalindrome(word)) {
                System.out.println(word + " - є паліндромом");
            } else {
                System.out.println(word + " - не є паліндромом");
            }
        }
    }
}