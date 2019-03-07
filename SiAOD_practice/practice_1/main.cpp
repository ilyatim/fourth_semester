#include <iostream>
#include <cstring>
#include <vector>
#include <fstream>
#include <algorithm>
#include <memory>
#include <math.h>
#include "Student.h"


std::vector<int> brute_force(std::vector<Student>& list, std::string name);

int barrier_search(std::vector<Student>& list, int size, int id);

std::string binary_search_1(std::vector<Student>& list, int id);

std::string binary_search_2(std::vector<Student>& list, int id);

void quick_sort(std::vector<Student>& fake_list, int left, int right);

void fill(std::vector<Student>& list);

int main()
{
    std::vector<Student> list;
	int id_student;
	std::string name;
    fill(list);

    std::cout << "enter name name for brute force" << std::endl;
    std::cin >> name;
    std::vector<int> id = brute_force(list, name);
    std::cout << "id students with same name:" << std::endl;
    for(int i = 0; i < id.size(); i++)
        std::cout << id.at(i) << std::endl;
    id.clear();
    std::cout << std::endl;

    std::cout << "enter id for binary search 1" << std::endl;
	std::cin >> id_student;
    std::cout << "name of student with id - " << id_student << ":" << std::endl;
    name = binary_search_1(list, id_student);
    std::cout << name << std::endl;
	std::cout << std::endl;

	std::cout << "enter id for barrier search" << std::endl;
	std::cin >> id_student;
    int age = barrier_search(list, int(list.size()), id_student);
	std::cout << "Age of student with id - " << id_student << ":" << std::endl;
    std::cout << age << std::endl;
	std::cout << std::endl;

	std::cout << "enter id for binary search" << std::endl;
	std::cin >> id_student;
	std::cout << "Name of student with id - " << id_student << ":" << std::endl;
    name = binary_search_2(list, id_student);
    std::cout << name << std::endl;
    return 0;
}

std::vector<int> brute_force(std::vector<Student>& list, std::string name) //возвращает id студентов с одинаковым именем
{
    std::vector<int> names;
    int count = 0;
    for(unsigned int i = 0; i < list.size(); i++)
	{
		if(list[i].getName() == name)
			names.push_back(list[i].getId());
		count++;
	}
    std::cout << "Number of comparison brute force - " << count << std::endl;
    return names;
}

int barrier_search(std::vector<Student>& list, int size, int id) //возвращает возраст студента по его id
{
	int position = 0;
	int count = 0;
    if(list[size - 1].getId() != id)
	{
		list.emplace_back(Student(id, list[size - 1].getAge(), list[size - 1].getName()));
		for( ; list[position].getId() != id; position++)
			count++;
		//list.pop_back();
		std::cout << "Number of comparison barrier search - " << count + 1 << std::endl;
	}
	else
	{
		std::cout << "Number of comparison barrier search - " << count + 1 << std::endl;
		return list[size - 1].getAge();
	}
	return list[position].getAge();
}

std::string binary_search_1(std::vector<Student>& list, int id) //возвращает имя студента по его id
{
	std::vector<Student> fake_list = list;
	int tmp_right = int(fake_list.size() - 1);

	quick_sort(fake_list, 0, tmp_right);
	int middle, left = 0, right = fake_list.size() - 1;
	while(1)
	{

		middle = (left + right) / 2;
		if(id < fake_list[middle].getId())
			right = middle - 1;
		else if(id > fake_list[middle].getId())
			left = middle + 1;
		else
		{
			std::string name = fake_list[middle].getName();
			fake_list.clear();
			return name;
		}

		if(left > right)
			return "\0";
	}
}

std::string binary_search_2(std::vector<Student>& list, int id) //возвращает имя студента по его id // нужно доделать
{
	std::vector<Student> fake_list = list;
	int tmp_right = (int)fake_list.size() - 1;
	quick_sort(fake_list, 0, tmp_right);

	int count = 0;
	int size = (int)fake_list.size();
	int left = 0, right = size - 1, middle;

	while(left <= right)
	{
		middle = (int)round(left + (2 / (sqrt(5) + 1)) * (right - left));
		if(id < fake_list[middle].getId() && count++)
			right = middle + 1;
		else if(id > fake_list[middle].getId() && count++)
			left = middle - 1;
		else
		{
			std::cout << "count of iteration - " << count + 1 << std::endl;
			return fake_list[middle].getName();
		}
	}
	std::cout << "count of iteration" << count << std::endl;
	return "\0";

}

void quick_sort(std::vector<Student>& fake_list, int left, int right)
{
	int i = left, j = right;
	Student tmp;
	int pivot = fake_list[(left + right) / 2].getId();
	while(i <= j)
	{
		while(fake_list[i].getId() < pivot)
			i++;
		while(fake_list[j].getId() > pivot)
			j--;
		if(i <= j)
		{
			tmp = fake_list[i];
			fake_list[i] = fake_list[j];
			fake_list[j] = tmp;
			i++;
			j--;
		}
	}

	if(left < j)
		quick_sort(fake_list, left, j);
	if(i < right)
		quick_sort(fake_list, i, right);
}

void fill(std::vector<Student>& list)
{
	std::string string = "C:\\Users\\Ilya\\CLionProjects\\practice_1\\list.txt";
	std::ifstream from_file;
	from_file.open(string);
	if(from_file.is_open())
	{
		list.clear();
		int size, id, age;
		std::string name;
		from_file >> size;
		for(int i = 0; i < size; i++)
		{
			from_file >> id >> age >> name;
			list.emplace_back(Student(id, age, name));
		}
	}
	from_file.close();
}