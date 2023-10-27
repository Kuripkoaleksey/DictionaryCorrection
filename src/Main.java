import java.util.*;

import java.util.ArrayList;

class Slovar {
    private Map<String, Map<String, List<String>>> dictionary;

    {
        dictionary = new TreeMap<String, Map<String, List<String>>>();
    }

    public boolean addLang(String lang) {
        lang = lang.toLowerCase();
        if (dictionary.containsKey(lang)) {
            return false;
        }
        dictionary.put(lang, new TreeMap<String, List<String>>());
        return true;
    }

    public boolean removeLang(String langR) {
        langR = langR.toLowerCase();
        if (dictionary.containsKey(langR)) {
            dictionary.remove(langR);
            return true;
        }
        return false;
    }

    private List<String> toLowerCase(List<String> list) {
        List<String> listArr = new ArrayList<String>();
        for (String word : list) {
            listArr.add(word.toLowerCase());
        }
        return listArr;
    }

    public List<String> checkList(List<String> nowArrWord, List<String> newArrWord) {
        for (String word : newArrWord) {
            if (!nowArrWord.contains(word)) {
                nowArrWord.add(word);
            }
        }
        return nowArrWord;
    }

    public boolean addWord(String keyLang, String originalWord, List<String> arrWord) {
        originalWord = originalWord.toLowerCase();
        keyLang = keyLang.toLowerCase();
        arrWord = toLowerCase(arrWord);
        if (dictionary.containsKey(keyLang)) {
            if (dictionary.get(keyLang).containsKey(originalWord)) {
                List<String> arr = dictionary.get(keyLang).get(originalWord);
                List<String> arrNew;
                arrNew = checkList(arr, arrWord);
                if (arr.size() != arrNew.size()) {
                    dictionary.get(keyLang).put(originalWord, arrNew);
                    return true;
                }
                return false;
            } else {
                dictionary.get(keyLang).put(originalWord, arrWord);
                return true;
            }
        } else {
            if (addLang(keyLang))
                return addWord(keyLang, originalWord, arrWord);
            else return false;
        }
    }

    public void printSlovarLang(String newKey) {
        int count = 1;
        if (dictionary.containsKey(newKey)) {
            System.out.println(newKey);
            System.out.println("--------------------------------");
            for (String word : dictionary.get(newKey).keySet()) {
                System.out.print(word + " : ");
                for (String tr : dictionary.get(newKey).get(word)) {
                    System.out.print(tr + " . ");
                }
                System.out.println();
            }
        }
    }

    public void printSlovarLang() {
        for (String word : dictionary.keySet()) {
            System.out.print(word + " : ");
            for (String tr : dictionary.keySet()) {
                System.out.print(tr + " . ");
            }
            System.out.println();
        }
    }

    public void searchWord(String lang, String search) {
        int count = 1;
        search = search.toLowerCase();
        if (dictionary.containsKey(lang)) {
            for (String word : dictionary.get(lang).keySet()) {
                if (word.startsWith(search)) {
                    if (count == 1)
                        System.out.println("Search : " + search);
                    System.out.println(count++ + ") " + word);
                }
            }
            if (count == 1) {
                System.out.println("Такого слова нет!!!");
            }
        }
    }

    public boolean addWord(String keyLang, String originalWord, String word) {
        originalWord = originalWord.toLowerCase();
        keyLang = keyLang.toLowerCase();
        word = word.toLowerCase();
        if (dictionary.containsKey(keyLang)) {
            if (dictionary.get(keyLang).containsKey(originalWord)) {
                if (dictionary.get(keyLang).get(originalWord).contains(word)) {
                    return false;
                } else {
                    dictionary.get(keyLang).get(originalWord).add(word);
                    return true;
                }
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(word);
                dictionary.get(keyLang).put(originalWord, list);
                return true;
            }
        } else {
            if (addLang(keyLang)) {
                boolean isadd = addWord(keyLang, originalWord, word);
                return isadd;
            } else {
                return false;
            }
        }
    }

    public String printLang(String lang) {
        lang = lang.toLowerCase();
        int count = 1;
        for (String language : dictionary.keySet()) {
            System.out.println(count++ + ") " + language);
        }
        return lang;
    }

    public void printLang() {
        int count = 1;
        for (String language : dictionary.keySet()) {
            System.out.println(count++ + ") " + language);
        }
    }

    public static String mScanerString() {
        String k = null;
        Scanner scanner = new Scanner(System.in);
        boolean isString = scanner.hasNextLine();
        if (isString) k = scanner.nextLine();
        {
            return k;
        }
    }

    public static String[] mScanerStringArr() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Сколько значений перевода слов вы хотите ввести? ");
        int numWords = scanner.nextInt();
        String dumb = scanner.nextLine();
        String words[] = new String[numWords];
        for (int i = 0; i < numWords; i++) {
            words[i] = scanner.nextLine();
        }
        return words;
    }

    public static int mScanerInt() throws Exception {
        int k = 0;
        Scanner scanner = new Scanner(System.in);
        boolean isInt = scanner.hasNextInt();
        if (isInt) k = scanner.nextInt();
        if (k >= 1 & k <= 10) {
            return k;
        }
        throw new Exception("Ввести нужно только число число от 1 до 10, буквы вводить нельзя");
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        Slovar slovar = new Slovar();
        slovar.addLang("en");
        slovar.addLang("ru");

        slovar.addWord("en", "Coat", "пальто");
        slovar.addWord("en", "Cat", "кот");
        slovar.addWord("en", "Car", "автомобиль");
        slovar.addWord("en", "dog", "собака");
        slovar.addWord("en", "Power", "мощность");
        slovar.addWord("en", "Head", "Голова");
        slovar.addWord("ru", "кот", "cat");
        int m = 0;
        String k = null;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Для использования словаря выберите пункт меню:");
            System.out.println("1 Добавить расскалдку"); //да+
            System.out.println("2 Добавить слово"); //да+
            System.out.println("3 Добавить несколько слов");//да+
            System.out.println("4 Напечатать расскладку"); //да+
            System.out.println("5 Напечатать словарь"); //да+
            System.out.println("6 Удаление расскалдки"); //да+
            System.out.println("7 Поиск слова");//да+
            System.out.println("8 Выход");//да+

            m = slovar.mScanerInt();
            switch (m) {
                case 1:
                    System.out.println("Ввдите название словаря, который хотите добавить");
                    String lang = scanner.nextLine();
                    slovar.addLang(lang);
                    System.out.println("имеются следующие словари:");
                    slovar.printLang();
                    break;
                case 2:
                    System.out.println("Напишите название словаря, нажмите Enter, потом введите слово и его перевод");
                    String s = slovar.mScanerString();
                    slovar.addWord(s, slovar.mScanerString(), slovar.mScanerString());
                    System.out.println("Теперь словарь имеет такие слова");
                    slovar.printSlovarLang(s);
                    break;
                case 3:
                    ArrayList<String> arr = new ArrayList<String>();
                    System.out.println("Напишите название словаря, нажмите Enter, напишите слово, нажмите Enter и напишите перевод этого слова");
                    String a = slovar.mScanerString();
                    slovar.addWord(a, slovar.mScanerString(), List.of(slovar.mScanerStringArr()));
                    System.out.println("Теперь словарь имеет такие слова");
                    slovar.printSlovarLang(a);
                    break;
                case 4:
                    System.out.println("Сейчас имеются следующие словари:");
                    slovar.printLang();
                    break;
                case 5:
                    System.out.println("Напишите название словаря который хотите полностью отобразить");
                    String v = slovar.mScanerString();
                    slovar.printSlovarLang(v);
                    break;
                case 6:
                    System.out.println("Сейчас имеются следующие словари:");
                    slovar.printLang();
                    System.out.println("Введите название словаря, который хотите удалить");
                    String f = slovar.mScanerString();
                    slovar.removeLang(String.valueOf(f));
                    System.out.println("Теперь остались следующие словари:");
                    slovar.printSlovarLang();
                    break;
                case 7:
                    System.out.println("Ввдите название словаря где хотите найти слово");
                    String langWordSearch = scanner.nextLine();
                    System.out.println("Введите искомое слово");
                    String originalWordSearch = scanner.nextLine();
                    slovar.searchWord(langWordSearch, originalWordSearch);
                    break;
                case 8:
                    System.out.println("Досвидания!");
                    System.exit(0);
                    break;
            }
        } while (true);
    }
}
