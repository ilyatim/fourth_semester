#include <iostream>
#include <vector>
#include <fstream>
#include "MyList.h"

void sort_even_numbers(std::vector<int>& array); //сортирует массив по четным элементам

void sort_plus(std::vector<int>& array); //сортирует положительные элементы массива

void fill(std::vector<int>& array); //заполняет массив vector из текстового документа

void output() // выводит варианты действий с массивом
{
	std::cout << "enter number from 1 to 4" << std::endl;
	std::cout << "1 - sort even numbers" << std::endl;
	std::cout << "2 - sort positive numbers" << std::endl;
	std::cout << "3 - 1 and 2" << std::endl;
	std::cout << "4 - 2 and 1" << std::endl;
	std::cout << std::endl;
}
int main()
{
	std::vector<int> array;
	fill(array);
	output();
	int choice;
	std::cin >> choice;
	std::cout << std::endl;
	switch(choice)
	{
		case 1:
			sort_even_numbers(array);
			break;
		case 2:
			sort_plus(array);
			break;
		case 3:
			sort_even_numbers(array);
			sort_plus(array);
			break;
		case 4:
			sort_plus(array);
			sort_even_numbers(array);
			break;
	}
	for(auto& it : array)
		std::cout << it << std::endl;
	return 0;
}

void fill(std::vector<int>& array)
{
	std::string string = "C:\\Users\\Ilya\\CLionProjects\\lab_1\\list.txt";
	std::ifstream from_file;
	from_file.open(string);
	int value;
	if(from_file.is_open())
	{
		while(!from_file.eof())
		{
			from_file >> value;
			array.emplace_back(value);
		}
	}
	from_file.close();
}

void sort_even_numbers(std::vector<int>& array)
{
	for(int i = 0; i < array.size() - 1; i += 2)
		for(int j = 0; j < array.size(); j += 2)
			if(array[j] > array[j + 2])
				std::swap(array[j], array[j + 2]);
}

void sort_plus(std::vector<int>& array)
{
	for(int i = 0; i < array.size() - 1; i++)
		if(array[i] > 0)
			for(int j = i + 1; j < array.size(); j++)
				if(array[j] > 0 && array[i] > array[j])
					std::swap(array[i], array[j]);
}