//
// Created by Ilya on 27.02.2019.
//

#ifndef LAB_1_MYLIST_H
#define LAB_1_MYLIST_H

template <class T>
class MyList
{
	struct Node
	{
		T value;
		Node* next;
	};

	Node* head;
	Node* tail;
public:
	MyList();
	~MyList();
	void push(T new_value);
	T pop();
	T headValue();
	T rearValue();
	void clear();
	void print();
	bool isEmpty();
};
#endif //LAB_1_MYLIST_H
