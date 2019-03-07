#include <iostream>
#include "Student.h"

Student::Student()
{
	id = 0;
	age = 0;
	name = "";
}
Student::Student(int id, int age, std::string name)
{
	this->id = id;
	this->age = age;
	this->name = name;
}
int Student::getId()
{
	return id;
}
int Student::getAge()
{
	return age;
}
std::string Student::getName()
{
	return name;
}

