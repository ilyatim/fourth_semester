//
// Created by Ilya on 27.02.2019.
//

#include <iostream>
#include "MyList.h"


template<class T>
MyList<T>::MyList():head(NULL),tail(NULL)
{

}

template<class T>
MyList<T>::~MyList()
{

}

template<class T>
void MyList<T>::push(T new_value)
{
	Node* variable = new Node;
	variable->value = new_value;
	variable->next = NULL;
	if(head != NULL)
	{
		tail->next = variable;
		tail = variable;
	}
	else
		head = tail = variable;
}

template<class T>
T MyList<T>::pop()
{
	if(isEmpty())
		return -1;
	T value = head->value;
	Node* time_node = new Node;
	time_node = head;
	head = head->next;
	delete time_node;
	return value;
}

template<class T>
T MyList<T>::headValue()
{
	T variable = head->value;
	return variable;
}

template<class T>
T MyList<T>::rearValue()
{
	T variable = tail->value;
	return variable;
}
template<class T>
void MyList<T>::clear()
{
	while(head)
		pop();
}

template<class T>
void MyList<T>::print()
{
	Node* time_head = new Node;
	time_head = head;
	while(time_head)
	{
		std::cout << time_head->value << ' ';
		time_head = time_head->next;
	}
	std::cout << std::endl;
}

template<class T>
bool MyList<T>::isEmpty()
{
	return head ? false : true;
}
void MyList::print()
{}