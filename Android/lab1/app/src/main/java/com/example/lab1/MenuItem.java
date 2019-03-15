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
    public MenuItem()
    {
        this.number = "Пусто";
    }

    public String getNumber()
    {
        return number;
    }
    public void setNumber(int number)
    {
        this.number = convert(number);
    }
    public String convert(int number)
    {
        //проверяю сколько чисел в числе
        //вызываю функцию для количества чисел
        //возвращаю конвертированое число
        int value = number;
        int count = 0;
        while(value > 0)
        {
            value /= 10;
            count++;
        }
        switch(count)
        {
            case 1:
                return decades(number);
            case 2:
                return hundreds(number);
            case 3:
                return chiliad(number);
            case 4:
                return thousand(number);
            case 5:
                return tensOfThousands(number);
            case 6:
                return hundredsOfThousands(number);
            case 7:
                return million;
            default:
                return zero;
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
        else
        {
            if(number % 10 == 0)
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
        else if(number % 100 > 10 && number % 100 < 100)
        {
            String num = chiliad[(number / 100) - 1] + " " + hundreds(number % 100);
            return num;
        }
        else
            return zero;
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
        else if(number % 1000 > 10 && number % 1000 < 100)
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
    private String tensOfThousands(int number) // функция возвращает значение числа с 5 цифрами
    {
        if(number % 10000 == 0)
            return hundreds[number / 10000 - 1] + thousands;
        else if(number % 10000 > 999)
        {
            String num = hundreds[number / 10000 - 1] + thousands + " " + thousand(number % 10000);
            return num;
        }
        else if(number % 10000 > 99 && number % 10000 < 1000)
        {
            String num = hundreds[number / 10000 - 1] + thousands + " " +  chiliad(number % 10000);
            return num;
        }
        else if(number % 10000 > 9 && number % 10000 < 100)
        {
            String num = hundreds[number / 10000 - 1] + thousands + " " +  hundreds(number % 10000);
            return num;
        }
        else
        {
            String num = hundreds[number / 10000 - 1] + thousands + " " +  decades(number % 10000);
            return num;
        }
    }
    private String hundredsOfThousands(int number) // функция возвращает значение числа с 6 цифрами
    {
        if(number % 100000 == 0)
            return chiliad[number / 100000 - 1] + thousands;
        else if(number % 100000 > 9999)
        {
            String num = chiliad[number / 100000 - 1] + tensOfThousands(number % 100000);
            return num;
        }
        else if(number % 100000 > 999 && number % 10000 < 10000)
        {
            String num = chiliad[number / 100000 - 1] + thousand(number % 100000);
            return num;
        }
        else if(number % 100000 > 99 && number % 10000 < 1000)
        {
            String num = chiliad[number / 100000 - 1] + chiliad(number % 100000);
            return num;
        }
        else if(number % 100000 > 9 && number % 10000 < 100)
        {
            String num = chiliad[number / 100000 - 1] + hundreds(number % 100000);
            return num;
        }
        else
        {
            String num = chiliad[number / 100000 - 1] + decades(number % 100000);
            return num;
        }
    }
}
