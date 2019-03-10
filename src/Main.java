import java.util.*;


public class Main {

    public static void main(String[] args) {

        System.out.println("Данный простейший калькулятор производит простые арифмитические действия:");
        System.out.println("сложение, вычитание, умножение и деление.");
        System.out.println("Умеет работать с целыми числами, как c арабскими от 1 до 10,");
        System.out.println("так и c римскими от I до X.");
        System.out.println("В одном вычислении не допускаются операции с римскими и арабскими цифрами.");
        System.out.println("Операции с получением дробного результата откругляются до целого значения в меньшую сторону.");


        // Считываем строку и удаляем пробелы
        Scanner readInput = new Scanner(System.in);
        String inputString;
        inputString = readInput.nextLine().replaceAll(" ", "");

        // Проверяем на ниличие как римскимх так и арабских чисел
        if (inputString.matches("\\d+\\D+")) {
            System.out.println("Ошибка: Выражение содержит как римские, так и арабские числа.");
            System.exit(0);
        }
        // Запускаем калькулятор
        ArrayList<String> savedInputDigit = parseToCalculate(inputString);
        System.out.println(calculatorArab(savedInputDigit));
    }

    private static boolean flagIsArab;

    // Задаем матОперации для разбивки введенного выражения на числа
    private static String mathOperations = "+-*/";

    private static boolean isMathOperation(String token) {
        // проверка, является ли переданное значение матОперацией
        return mathOperations.contains(token);
    }

    private static boolean isRomanOperand(String token) {
        return token.matches("^X|IX|IV|V?I{0,3}$");
    }

    private static boolean isNumeric(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void validateNumber(int number) {
        if (number < 0 || number > 10) {
            System.out.println("Ошибка: Одно из введённых чисел больше 10.");
            System.exit(0);
        }
    }

    // Разделяем ввведенное выражение: числа - в массив, матОперацию - в строку
    private static ArrayList<String> parseToCalculate(String inputString) {
        // массив с числами
        ArrayList<String> savedInputDigit = new ArrayList<>();
        // строка с матОперацией
        String currOperation = "";


        // создаем анализатор для разбивки на токены по матОперациям
        StringTokenizer tokenizer = new StringTokenizer(inputString, mathOperations, true);
        // сохраняем текущее значение токена
        String currentToken;

        // проходимся по каждому токену
        while (tokenizer.hasMoreTokens()) {
            // выбираем следующий токен
            currentToken = tokenizer.nextToken();

            // если токен - это матОперация
            if (isMathOperation(currentToken)) {
                // записываем токен в матОперацию
                currOperation = currentToken;
            } else if (isRomanOperand(currentToken)) {
                flagIsArab = false;

                String arabNumber = RomanNumber.toArab(currentToken);
                savedInputDigit.add(arabNumber);
            } else if (isNumeric(currentToken)) {
                validateNumber(Integer.valueOf(currentToken));

                flagIsArab = true;
                savedInputDigit.add(currentToken);
            } else {
                System.out.println("Ошибка: не является числом или не удовлетворяет начальным условиям.");
                System.exit(0);
            }
        }
        savedInputDigit.add(currOperation);
        // возвращаем массив с числами
        return savedInputDigit;
    }

    private static String calculatorArab(List<String> postfix) {
        // масив вычеслений
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;

        // выполняем операцию
        for (String x : postfix) {
            switch (x) {
                case "+": {
                    Integer a = stack.pop(), b = stack.pop();
                    result = a + b;
                    break;
                }
                case "-": {
                    Integer a = stack.pop(), b = stack.pop();
                    result = a - b;
                    break;
                }
                case "*": {
                    Integer a = stack.pop(), b = stack.pop();
                    result = a * b;
                    break;
                }
                case "/": {
                    Integer a = stack.pop(), b = stack.pop();
                    result = a / b;
                    break;
                }
                default: {
                    stack.addLast(Integer.valueOf(x));
                    break;
                }
            }
        }

        // возвращаем результат
        if (flagIsArab) {
            return Integer.toString(result);
        } else {
            return RomanNumber.toRoman(result);
        }
    }
}

