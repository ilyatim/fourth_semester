#include <iostream>
#include <vector>
#include <ctime>
#include <cstring>

int shakerSort(std::vector<int> &array);   //задание 1 г

int shellSort(std::vector<int> &array);    //задание 1 д

void sortBySelection(std::vector<int> &array);  //задание 2 сортировка выбором

void sortByExchange(std::vector<int> &array);   //задание 2 сортировка обменом

void insertSort(std::vector<int> &array);    //задание 2 сортировка включением

std::vector<int> search(std::vector<int> &array, int k);    //задание 6

bool notIncluded(std::vector<int> &array, int number);

bool more(std::vector<int> &array, int number);

void print(std::vector<int> &array);    //вывод массива в консоль

int main()
{
    std::vector<int> arrayForSort;
    std::vector<int> primaryArray;

    int size, number, k, iteration;

    std::cout << "Enter size of array" << std::endl;
    std::cin >> size;
    std::cout << "Pls, enter numbers in array" << std::endl;
    std::cout << "The program involves the use of only positive numbers" << std::endl;
    for(int i = 0; i < size; i++)
    {
        std::cin >> number;
        primaryArray.emplace_back(number);
    }
    arrayForSort = primaryArray;

    std::cout << "First step - shaker sorting" << std::endl;
    iteration = shakerSort(arrayForSort);
    std::cout << "Number of iteration - shaker sorting - " << iteration << std::endl;
    print(arrayForSort);
    arrayForSort = primaryArray;

    std::cout << "Second step - shell sorting" << std::endl;
    iteration = shellSort(arrayForSort);
    std::cout << "Number of iteration - shell sorting - " << iteration << std::endl;
    print(arrayForSort);
    arrayForSort = primaryArray;

    std::cout << "Third step - sort by selection" << std::endl;
    sortBySelection(arrayForSort);
    print(arrayForSort);
    arrayForSort = primaryArray;

    std::cout << "Fourth step - sort by exchange" << std::endl;
    sortByExchange(arrayForSort);
    print(arrayForSort);
    arrayForSort = primaryArray;

    std::cout << "Fifth step - enable sorting" << std::endl;
    insertSort(arrayForSort);
    print(arrayForSort);
    arrayForSort = primaryArray;

    std::cout << "Sixth step - search min elements" << std::endl;
    std::cout << "Pls, enter numbers of min elements to search" << std::endl;
    std::cin >> k;
    arrayForSort = search(arrayForSort, k);
    print(arrayForSort);

    primaryArray.clear();
    arrayForSort.clear();
    return 0;
}

int shakerSort(std::vector<int> &array)
{
    int size, left, right, iteration;
    iteration = 0;
    size = array.size();
    left = 1;
    right = size - 1;

    while (left <= right)   //пока левая и правая границы не сомкнутся
    {
        for (int i = right; i >= left; i--)     //проход справа налево
        {
            if (array[i - 1] > array[i])    //перестановка
            {
                std::swap(array[i - 1], array[i]);
                iteration++;
            }
        }
        left++;


        for (int i = left; i <= right; i++)     //проход слева направо
        {
            if (array[i - 1] > array[i])    //перестановка
            {
                std::swap(array[i - 1], array[i]);
                iteration++;
            }
        }
        right--;
    }
    return iteration;
}
int shellSort(std::vector<int> &array)
{
    int d, j, i, iteration;
    iteration = 0;
    d = array.size() / 2;
    while(d > 0)
    {
        for(i = 0; i < array.size() - d; i++)
        {
            j = i;
            while(j >= 0 && array[j] > array[j + d])
            {
                std::swap(array[j], array[j + d]);
                j--;
                iteration++;
            }
        }
        d = d / 2;
    }
    return iteration;
}
void sortBySelection(std::vector<int> &array)
{
    clock_t time;
    time = clock();
    int min = 0;
    for(int i = 0; i < array.size(); i++)
    {
        min = i;    // запомним номер текущей ячейки, как ячейки с минимальным значением
                    // в цикле найдем реальный номер ячейки с минимальным значением
        for(int j = i + 1; j < array.size(); j++)
        {
            min = (array[j] < array[min] ) ? j : min;
        }

        if(i != min)    // перестановка этого элемента, поменяв его местами с текущим
        {
            std::swap(array[i], array[min]);
        }
    }
    time = clock() - time;
    printf ("%f seconds.\n",((float)time)/CLOCKS_PER_SEC);
}
void sortByExchange(std::vector<int> &array)
{
    clock_t time;
    time = clock();
    int i, j;
    int size = array.size();
    for(i = 0; i < size - 1; i++)   //стандартная сортировка пузырьком
    {
        for(j = i + 1; j < size; j++)
        {
            if(array[i] > array[j])
            {
                std::swap(array[i], array[j]);
            }
        }
    }
    time = clock() - time;
    printf ("%f seconds.\n",((float)time)/CLOCKS_PER_SEC);
}
void insertSort(std::vector<int> &array)
{
    clock_t time;
    time = clock();
    int size = array.size();
    int tmp;
    for (int i = 1, j; i < size; ++i)   // цикл проходов, i - номер прохода
    {
        tmp = array[i];
        for (j = i - 1; j >= 0 && array[j] > tmp; --j)  // поиск места элемента в готовой последовательности
            array[j + 1] = array[j];    // сдвигаем элемент направо, пока не дошли
        array[j + 1] = tmp;             // место найдено, вставить элемент
    }
    time = clock() - time;
    printf ("%f seconds.\n",((float)time)/CLOCKS_PER_SEC);
}

std::vector<int> search(std::vector<int> &array, int k)
{
    shellSort(array);
    std::vector<int> minElements;

    if(more(array, k))
    {
        return static_cast<std::vector<int>>(0);
    }

    minElements.reserve(k);
    for(int i = 0; i < k; i++)
    {
        if(notIncluded(minElements, array[i]))
        {
            minElements.emplace_back(array[i]);
        }
        else
        {
            if(!more(array, ++k))
            {
                continue;
            }
            else
            {
                return minElements;
            }
        }
    }
    return minElements;
}
bool more(std::vector<int> &array, int number)
{
    if(number > array.size())
    {
        return true;
    }
}
bool notIncluded(std::vector<int> &array, int number)
{
    for(int i : array)
    {
        if(number == i)
            return false;
    }
    return true;
}
void print(std::vector<int> &array)
{
    for(int i : array)
    {
       std::cout << i << std::endl;
    }
}