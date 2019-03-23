package com.example.lab1;

public class MenuItem
{
    private String number;
    private final String zero = "Ноль";
    private final String thousands = "Тысяч";
    private final String[] thousand = {"Одна Тысяча", "Две Тысячи", "Три тысячи", "Четыре Тысячи", "Пять Тысяч", "Шесть Тысяч", "Семь Тысяч", "Восемь Тысяч", "Девять Тысячь"};
    private final String[] decades = {"Один", "Два", "Три", "Четыре", "Пять", "Шесть", "Семь", "Восемь", "Девять"};
    private final String[] hundreds_2 = {"Одинадцать", "Двенадцать", "Тринадцать", "Четырнадцать", "Пятнадцать", "Шестнадцать", "Семнадцать", "Восемнадцать", "Девятнадцать"};
    private final String[] hundreds = {"Десять", "Двадцать", "Тридцать", "Сорок", "Пятьдесят", "Шестьдесят", "Семьдесят", "Восемьдесят", "Девяносто"};
    private final String[] chiliad = {"Сто", "Двести", "Триста", "Четыреста", "Пятьсот", "Шестьсот", "Семьсот", "Восемьсот", "Девятьсот"};
    private final String million = "Миллион";

    public MenuItem(int number)
    {
        this.number = convert(number);
    }

    public String getNumber() //получение значения из числа
    {
        return number;
    }

    public String convert(int number)
    {
        //проверяю сколько чисел в числе
        //вызываю функцию для количества чисел
        //возвращаю конвертированое число
        int value = number;
        int count = 0;
        while(value > 0) //проверка на количество чисел
        {
            value /= 10;
            count++;
        }
        switch(count)
        {
            case 1:
                return decades(number); //вызов функции для 1-го символа
            case 2:
                return hundreds(number); //вызов функции для 2-х символом
            case 3:
                return chiliad(number); //вызов функции для 3-х символом
            case 4:
                return thousand(number); //вызов функции для 4-х символом
            case 5:
                return tensOfThousands(number); //вызов функции для 5-ти символом
            case 6:
                return hundredsOfThousands(number); //вызов функции для 6-ти символом
            case 7:
                return million;
            default:
                return zero; //если количество символом больше 7, возвращается "ноль"
        }
    }
    private String decades(int number) // функция возвращает значение числа с 1 цифрой
    {
        return decades[number - 1];
    }
    private String hundreds(int number) // функция возвращает значение числа с 2 цифрами
    {
        if(number > 10 && number < 20)
        {
            String num = hundreds_2[(number % 10) - 1];
            return num;
        }
        else if(number % 10 == 0)
        {
            String num = hundreds[(number / 10) - 1];
            return num;
        }
        else
        {
            String num = hundreds[(number / 10) - 1] + " " +  decades(number % 10);
            return num;
        }
    }
    private String chiliad(int number) // функция возвращает значение числа с 3 цифрами
    {
        if(number % 100 == 0)
            return chiliad[(number / 100) - 1];
        else if(number % 100 < 10)
        {
            String num = chiliad[(number / 100) - 1] + " " + decades(number % 100);
            return num;
        }
        else
        {
            String num = chiliad[(number / 100) - 1] + " " + hundreds(number % 100);
            return num;
        }
    }
    private String thousand(int number) // функция возвращает значение числа с 4 цифрами
    {
        if(number % 1000 == 0)
            return thousand[(number / 1000) - 1];
        else if(number % 1000 < 10)
        {
            String num = thousand[(number / 1000) - 1] + " " + decades(number % 1000);
            return num;
        }
        else if(number % 1000 > 9 && number % 1000 < 100)
        {
            String num = thousand[(number / 1000) - 1] + " " + hundreds(number % 1000);
            return num;
        }
        else
        {
            String num = thousand[(number / 1000) - 1] + " " + chiliad(number % 1000);
            return num;
        }
    }
    private String thousand_2(int number) // функция возвращает значение числа с 4 цифрами
    {
        if(number % 1000 == 0)
            return thousands;
        else if(number % 1000 < 10)
        {
            String num = thousands + " " + decades(number % 1000);
            return num;
        }
        else if(number % 1000 > 9 && number % 1000 < 100)
        {
            String num = thousands + " " + hundreds(number % 1000);
            return num;
        }
        else
        {
            String num = thousands + " " + chiliad(number % 1000);
            return num;
        }
    }
    private String tensOfThousands(int number) // функция возвращает значение числа с 5 цифрами
    {
        if((number / 1000) > 10 && (number / 1000) < 20)
        {
            if(number % 10000 < 10)
            {
                String num = hundreds_2[(number / 1000) % 10 - 1] + " " +  decades(number % 10000);
                return num;
            }
            else if(number % 10000 > 9 && number % 10000 < 100)
            {
                String num = hundreds_2[(number / 1000) % 10 - 1] + " " +  hundreds(number % 10000);
                return num;
            }
            else if(number % 10000 > 99 && number % 10000 < 1000)
            {
                String num = hundreds_2[(number / 1000) % 10 - 1] + " " +  chiliad(number % 10000);
                return num;
            }
            else
            {
                String num = hundreds_2[(number / 1000) % 10 - 1] + " " + thousand_2(number % 10000);
                return num;
            }
        }
        else
        {
            if(number % 10000 == 0)
                return hundreds[number / 10000 - 1] + thousands;
            else if(number % 10000 < 10)
            {
                String num = hundreds[number / 10000 - 1] + thousands + " " +  decades(number % 10000);
                return num;
            }
            else if(number % 10000 > 9 && number % 10000 < 100)
            {
                String num = hundreds[number / 10000 - 1] + thousands + " " +  hundreds(number % 10000);
                return num;
            }
            else if(number % 10000 > 99 && number % 10000 < 1000)
            {
                String num = hundreds[number / 10000 - 1] + thousands + " " +  chiliad(number % 10000);
                return num;
            }
            else
            {
                String num = hundreds[number / 10000 - 1] + thousands + " " + thousand(number % 10000);
                return num;
            }
        }


    }
    private String hundredsOfThousands(int number) // функция возвращает значение числа с 6 цифрами
    {
        if(number % 100000 == 0)
            return chiliad[number / 100000 - 1] + thousands;
        else if(number % 100000 > 9999)
        {
            String num = chiliad[number / 100000 - 1] + " " + tensOfThousands(number % 100000);
            return num;
        }
        else if(number % 100000 > 999 && number % 10000 < 10000)
        {
            String num = chiliad[number / 100000 - 1] + " " + thousands + " " + thousand(number % 100000);
            return num;
        }
        else if(number % 100000 > 99 && number % 10000 < 1000)
        {
            String num = chiliad[number / 100000 - 1] + " " + thousands + " " + chiliad(number % 100000);
            return num;
        }
        else if(number % 100000 > 9 && number % 10000 < 100)
        {
            String num = chiliad[number / 100000 - 1] + " " + thousands + " " + hundreds(number % 100000);
            return num;
        }
        else
        {
            String num = chiliad[number / 100000 - 1] + " " + thousands + " " + decades(number % 100000);
            return num;
        }
    }

}
