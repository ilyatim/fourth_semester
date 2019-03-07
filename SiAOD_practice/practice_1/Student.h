
#ifndef PRACTICE_1_STUDENT_H
#define PRACTICE_1_STUDENT_H

#include <iostream>
class Student
{
private:
	int id;
	std::string name;
	int age;
public:
	Student();
	Student(int id, int age, std::string name);
	int getId();
	int getAge();
	std::string getName();

};

#endif //PRACTICE_1_STUDENT_H
