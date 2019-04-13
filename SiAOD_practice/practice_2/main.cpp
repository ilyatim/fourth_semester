#include <iostream>
#include <algorithm>
#include <string>
#include <Windows.h>
#define ALPHABET_LENGTH 256

//Служебная переменная подсчёта итераций
size_t iterations = 0;

//Сравнить символы с учётом регистра
bool CompCharsWithReg(char a, char b)
{
    if (a == b)
        return true;
    return false;
}

//Сравнить символы без учёта регистра
bool CompCharsWithoutReg(char a, char b)
{
    if (tolower(a) == tolower(b))
        return true;
    return false;
}

//Добавить значение в массив
void AddValueInArray(size_t*& arr, int value)
{
    size_t length = 0;
    if (arr != nullptr)
        length = _msize(arr) / sizeof(size_t);

    size_t* temp = new size_t[length + 1];

    for (int i = 0; i < length; i++)
        temp[i] = arr[i];
    temp[length] = value;

    delete[] arr;
    arr = temp;
}

//Составить таблицу d
size_t* FindPrefix(/*Образ*/const std::string& image, /*Учитывать регистр*/bool considerReg)
{
    //Массив d для значений сдвигов
    size_t* const d = new size_t[image.length()];
    d[0] = 0;

    //Проверка на учёт регистров
    bool(*Compare)(char, char);
    if (considerReg)
        Compare = CompCharsWithReg;
    else
        Compare = CompCharsWithoutReg;

    //Исследовать символы строки
    for (size_t i = 1, j = 0; i < image.length(); i++)
    {
        //Пока после нескольких совпадений найдено различие
        while ((j > 0) && !Compare(image[i], image[j]))
            j = d[j - 1]; //Взять ранее рассчитанное значение (начиная с максимально возможного)

        //Если элементы образа равны
        if (Compare(image[i], image[j]))
            j++;
        d[i] = j;
    }
    return d;
}

//Алгоритм Кнута-Морриса-Пратта
size_t* KMPSearch(/*Исходная строка*/const std::string& str, /*Образ*/const std::string& image, /*Учитывать регистр*/bool considerReg)
{
    //Вычислить длину исходной строки
    size_t n = str.length();

    //Вычислить длину образа
    size_t m = image.length();

    //Если образ длиннее строки или образ пуст или строка пуста, то соответствий нет
    if (n < m || m == 0 || n == 0)
        return nullptr;

    size_t* pointers = nullptr;

    //Вычислить таблицу d
    size_t* const d = FindPrefix(image, considerReg);

    //Проверка на учёт регистров
    bool(*Compare)(char, char);
    if (considerReg)
        Compare = CompCharsWithReg;
    else
        Compare = CompCharsWithoutReg;

    //Пройти по строке
    for (int i = 0, j = 0; i < n; i++)
    {
        //Если после нескольких совпадений найдено различие
        if ((j > 0) && !Compare(image[j], str[i]))
            while ((j > 0) && !Compare(image[j], str[i]))
            {
                iterations++;
                j = d[j - 1];
            }
        else
            iterations++;

        //Если найдено соответствие между символом образа и символом строки
        if (Compare(image[j], str[i]))
            j++; //Перейти к следующему символу образа
        if (j == m) //Если все символы образа совпали с символами строки
            AddValueInArray(pointers, i - j + 1); //Добавить индекс образа в строке
    }
    delete[] d;

    return pointers;
}

//Составить таблицу для алгоритма Боуера-Мура
int* BMTable(const std::string& image, bool considerReg)
{
    int* const bmTable = new int[ALPHABET_LENGTH];
    int i;

    //Инициализировать все элементы значением '-1'
    for (i = 0; i < ALPHABET_LENGTH; i++)
        bmTable[i] = -1;

    //Заполнить массив индексами последних вхождений символов
    //с учётом регистра
    if (considerReg)
        for (i = 0; i < image.length(); i++)
            bmTable[image[i]] = i;
        //без учёта регистра
    else
        for (i = 0; i < image.length(); i++)
            bmTable[tolower(image[i])] = i;

    return bmTable;
}

//Алгоритм Боуера-Мура
size_t* BMSearch(const std::string& str, const std::string& image, bool considerReg)
{
    //Вычислить длину исходной строки
    size_t n = str.length();

    //Вычислить длину образа
    size_t m = image.length();

    //Если образ длиннее строки или образ пуст или строка пуста, то соответствий нет
    if (n < m || m == 0 || n == 0)
        return nullptr;

    size_t* pointers = nullptr;

    //Проверка на учёт регистров
    bool(*Compare)(char, char);
    if (considerReg)
        Compare = CompCharsWithReg;
    else
        Compare = CompCharsWithoutReg;

    //Заполнить таблицу для этого алгоритма
    int* const bmTable = BMTable(image, considerReg);

    //Переменная смещения образа по отношению к тексту
    int s = 0;

    //Пока s меньше, чем разность длины строки и образа
    while (s <= (n - m))
    {
        int j = m - 1;

        //Если образ совпадает со строкой, начиная с конца
        if ((j >= 0) && Compare(image[j], str[s + j]))
            while ((j >= 0) && Compare(image[j], str[s + j]))
            {
                iterations++;
                j--; //Двигаться от конца образа
            }
        else
            iterations++;

        //Если образ полностью совпал со строкой
        if (j < 0)
        {
            AddValueInArray(pointers, s); //Добавить индекс образа в строке

            //Сдвинуть образ так, чтобы следующий символ в тексте совпадал с последним
            //таким же символом в образе или на 1, если длины строки не хватает
            if (considerReg)
                s += ((s + m) < n) ? m - bmTable[str[s + m]] : 1;
            else
                s += ((s + m) < n) ? m - bmTable[tolower(str[s + m])] : 1;
        }
            //Иначе
        else
        {
            //Сдвинуть образ так, чтобы символ в тексте, не совпавший с символом
            //в образе, совпадал с последним таким же символом в образе или на 1,
            //если совпадающий символ правее текущего символа в образе
            if (considerReg)
                s += (1 >= (j - bmTable[str[s + j]])) ? 1 : (j - bmTable[str[s + j]]);
            else
                s += (1 >= (j - bmTable[tolower(str[s + j])])) ? 1 : (j - bmTable[tolower(str[s + j])]);
        }
    }
    delete[] bmTable;

    return pointers;
}

//Объединённый алгоритм поиска Кнута-Морриса-Пратта и Боуера-Мура
size_t* KMPAndBMSearch(const std::string& str, const std::string& image, bool considerReg)
{
    //Вычислить длину исходной строки
    size_t n = str.length();

    //Вычислить длину образа
    size_t m = image.length();

    //Если образ длиннее строки или образ пуст или строка пуста, то соответствий нет
    if (n < m || m == 0 || n == 0)
        return nullptr;

    size_t* pointers = nullptr;

    //Проверка на учёт регистров
    bool(*Compare)(char, char);
    if (considerReg)
        Compare = CompCharsWithReg;
    else
        Compare = CompCharsWithoutReg;

    //Заполнить таблицу d
    size_t* const d = FindPrefix(image, considerReg);

    //Заполнить таблицу Боуера-Мура
    int* const bmTable = BMTable(image, considerReg);

    //Переменная смещения образа по отношению к тексту
    int s = 0;

    //Пока s меньше, чем разность длины строки и образа
    while (s <= (n - m))
    {
        int j = m - 1;

        //Пока образ совпадает со строкой, начиная с конца
        while ((j >= 0) && Compare(image[j], str[s + j]))
            j--; //Двигаться от конца образа

        //Если образ полностью совпал со строкой
        if (j < 0)
        {
            AddValueInArray(pointers, s); //Добавить индекс образа в строке

            int k = m;
            while ((k > 0) && !Compare(image[k], str[m]))
                k = d[k - 1];

            //Если сдвиг Кнута-Морриса-Пратта больше, чем сдвиг Боуера-Мура
            if (k > (m - bmTable[str[s + m]]))
                s += ((s + k) < n) ? k : 1;
                //Иначе
            else
                //Сдвинуть образ так, чтобы следующий символ в тексте совпадал с последним
                //таким же символом в образе
            if (considerReg)
                s += ((s + m) < n) ? (m - bmTable[str[s + m]]) : 1;
            else
                s += ((s + m) < n) ? (m - bmTable[tolower(str[s + m])]) : 1;
        }
            //Иначе
        else
        {
            int i = s + j;
            int k = j;
            while ((k > 0) && !Compare(image[k], str[i]))
                k = d[k - 1];

            if (considerReg)
            {
                //Если сдвиг Кнута-Морриса-Пратта больше, чем сдвиг Боуера-Мура
                if (k > (j - bmTable[str[s + j]]))
                    s += ((s + k) < n) ? k : 1;
                    //Иначе
                else
                    //Сдвинуть образ так, чтобы следующий символ в тексте совпадал с последним
                    //таким же символом в образе
                    s += ((j - bmTable[str[s + j]]) <= 1) ? 1 : (j - bmTable[str[s + j]]);
            }
            else
            {
                //Если сдвиг Кнута-Морриса-Пратта больше, чем сдвиг Боуера-Мура
                if (k > (j - bmTable[tolower(str[s + j])]))
                    s += ((s + k) < n) ? k : 1;
                    //Иначе
                else
                    //Сдвинуть образ так, чтобы следующий символ в тексте совпадал с последним
                    //таким же символом в образе
                    s += ((j - bmTable[tolower(str[s + j])]) <= 1) ? 1 : (j - bmTable[tolower(str[s + j])]);
            }
        }
        iterations++;
    }
    delete[] d, bmTable;

    return pointers;
}

//Алгоритм прямого поиска
size_t* BruteForceSearch(const std::string& str, const std::string& image, bool considerReg)
{
    //Вычислить длину строки
    size_t n = str.length();

    //Вычислить длину образа
    size_t m = image.length();

    //Если образ длиннее строки или образ пуст или строка пуста, то соответствий нет
    if (n < m || m == 0 || n == 0)
        return nullptr;

    size_t* pointers = nullptr;

    //Проверка на учёт регистров
    bool(*Compare)(char, char);
    if (considerReg)
        Compare = CompCharsWithReg;
    else
        Compare = CompCharsWithoutReg;

    //Пройти по строке
    for (int i = 0; i < n - m; i++)
    {
        //Пройти по образу
        for (int j = 0; j < m; j++)
        {
            iterations++;
            //Выйти из цикла прохода образа, если найдено несоответствие
            if (!Compare(str[i + j], image[j]))
                break;

            //Если образ полностью совпал со строкой, то добавить индекс
            if (j == m - 1)
                AddValueInArray(pointers, i);
        }
    }
    return pointers;
}

int main()
{

    std::string input1;
    std::string input2;
    std::cout << "Enter string: ";
    std::getline(std::cin, input1);
    std::cout << "Enter substring: ";
    std::getline(std::cin, input2);

    size_t res[4];

    size_t* arr1 = BruteForceSearch(input1, input2, false);
    std::cout << "Direct search method - " << iterations << " iteration" << std::endl;
    res[0] = iterations;
    iterations = 0;

    size_t* arr2 = KMPSearch(input1, input2, false);
    std::cout << "KPM method - " << iterations << " iteration" << std::endl;
    res[1] = iterations;
    iterations = 0;

    size_t* arr3 = BMSearch(input1, input2, false);
    std::cout << "BM method - " << iterations << " iteration" << std::endl;
    res[2] = iterations;
    iterations = 0;

    size_t* arr4 = KMPAndBMSearch(input1, input2, false);
    std::cout << "BM plus KPM - " << iterations << " iteration" << std::endl;
    res[3] = iterations;
    iterations = 0;
    if (arr4 != nullptr)
    {
        size_t n = _msize(arr4) / sizeof(arr4[0]);

        for (int i = 0; i < n; i++)
            std::cout << arr4[i] << " ";
        std::cout << std::endl;
    }
    else
        std::cout << "No match found" << std::endl;

    delete[] arr1, arr2, arr3, arr4;

    return 0;
}