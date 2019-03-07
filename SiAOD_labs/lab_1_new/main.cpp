#include <iostream>
#include <vector>
#include <fstream>
#include "MyList.h"

template <typename T>
void fill(MyList<T>*&); //заполняет массив vector из текстового документа

int main()
{
	auto list = new MyList<int>();
	fill(list);
	auto new_list = new MyList<int>();
	fill(new_list);
	std::cout << "list 1 - ";
	list->print();
	std::cout << "list 2 - ";
	new_list->print();
	std::cout << std::endl;
	list->union_list(new_list);
	list->print();
	list->bubblesort();
	std::cout << "result of program - ";
	list->print();
	return 0;
}

template <typename T>
void fill(MyList<T>*& list)
{
	std::string string = "C:\\Users\\Ilya\\CLionProjects\\lab_1_new\\list.txt";
	std::ifstream from_file;
	from_file.open(string);
	int value;
	if(from_file.is_open())
	{
		while(!from_file.eof())
		{
			from_file >> value;
			list->push(value);
		}
	}
	from_file.close();
}

