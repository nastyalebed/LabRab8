import java.io.*;
import java.util.*;

public class Main {

    private ArrayList<String> stringArrayList;

    public Main() {
        this.stringArrayList = new ArrayList<>();
    }

    // 1. Добавление и удаление объектов.
    public void addObject(String element) {
        stringArrayList.add(element);
    }

    public void removeObject(String element) {
        stringArrayList.remove(element);
    }

    // 2. Поиск одинаковых элементов с подсчетом совпадений
    public int countOccurrences(String element) {
        return Collections.frequency(stringArrayList, element);
    }

    // 3. Выгрузка в xml-файл.
    public void exportToXML(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("<strings>");
            for (String str : stringArrayList) {
                writer.println("  <string>" + str + "</string>");
            }
            writer.println("</strings>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 4. Реверс всех строк, входящих в коллекцию
    public void reverseAllStrings() {
        Collections.reverse(stringArrayList);
    }

    // 5. Статистика по всем символам, содержащимся в строках коллекции
    public Map<Character, Integer> characterStatistics() {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (String str : stringArrayList) {
            for (char c : str.toCharArray()) {
                charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            }
        }
        return charCountMap;
    }

    // 6. Поиск подстроки в строках коллекции
    public List<String> searchSubstring(String substring) {
        List<String> result = new ArrayList<>();
        for (String str : stringArrayList) {
            if (str.contains(substring)) {
                result.add(str);
            }
        }
        return result;
    }

    // 7. Инициализация листа по текстовому файлу и вывод содержимого коллекции на экран.
    public void initializeFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringArrayList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printCollection() {
        for (String str : stringArrayList) {
            System.out.println(str);
        }
    }

    // 8. Расширить функциональность класса ArrayList методом compareInnerObjects(int firstIndex, int secondIndex)
    public boolean compareInnerObjects(int firstIndex, int secondIndex) {
        return stringArrayList.get(firstIndex).equals(stringArrayList.get(secondIndex));
    }

    // 9. Посчитать длины строк входящих в коллекцию, и вывести результат в упорядоченном виде.
    public List<Integer> calculateStringLengths() {
        List<Integer> lengths = new ArrayList<>();
        for (String str : stringArrayList) {
            lengths.add(str.length());
        }
        Collections.sort(lengths);
        return lengths;
    }

    // 10. Реализовать возможность добавления в динамическую коллекцию...
    public void addWithLimit(String element, int maxSize) {
        if (stringArrayList.size() < maxSize) {
            stringArrayList.add(element);
        } else {
            stringArrayList.remove(0);
            stringArrayList.add(element);
        }
    }

    public static void main(String[] args) {
        Main operations = new Main();
        operations.addObject("abc");
        operations.addObject("def");
        operations.addObject("abc");
        operations.addObject("ghi");

        System.out.println("Count of 'abc': " + operations.countOccurrences("abc"));

        operations.reverseAllStrings();

        System.out.println("Character Statistics: " + operations.characterStatistics());

        System.out.println("Strings with 'de': " + operations.searchSubstring("de"));

        operations.printCollection();

        System.out.println("Comparison at index 0 and 2: " + operations.compareInnerObjects(0, 2));

        System.out.println("Sorted String Lengths: " + operations.calculateStringLengths());

        operations.addWithLimit("jkl", 3);
        operations.printCollection();

        operations.exportToXML("output.xml");
        operations.initializeFromFile("input.txt");
        operations.printCollection();
    }
}
