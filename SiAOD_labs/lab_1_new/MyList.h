
#ifndef LAB_1_MYLIST_H
#define LAB_1_MYLIST_H

template <class T>

class MyList
{
private:
	struct Node
	{
		T value;
		Node* next;
		Node* prev;
	};

	Node* head;
	Node* tail;
	int size;
public:
	MyList();
	~MyList();
	void push(T new_value);
	T pop();
	T pop_back();
	T headValue();
	T rearValue();
	void clear();
	void print();
	bool isEmpty();
	int getSize();
	void bubblesort();
	void union_list(MyList<T>*& list);
};


template <typename T>
MyList<T>::MyList():head(NULL),tail(NULL)
{
	this->size = 0;
}
template <typename T>
MyList<T>::~MyList()
= default;

template <typename T>
void MyList<T>::push(T new_value)
{
	Node* variable = new Node;
	variable->value = new_value;
	variable->next = NULL;
	if(head != NULL)
	{
		tail->prev = tail;
		tail->next = variable;
		tail = variable;

	}
	else
	{
		head = tail = variable;
		head->prev = NULL;
	}
	size++;
}

template <typename T>
T MyList<T>::pop()
{
	if(isEmpty())
		return -1;
	T value = head->value;
	Node* time_node = new Node;
	time_node = head;
	head = head->next;
	delete time_node;
	size--;
	return value;
}
template <typename T>
T MyList<T>::pop_back()
{
	if(isEmpty())
		return -1;
	Node* time_node = head;
	T value = tail->value;
	while (time_node->next->next != NULL) {
		time_node = time_node->next;
	}
	tail = time_node;
	delete tail->next;
	tail->next = NULL;
	--size;
	return value;
}
template <typename T>
T MyList<T>::headValue()
{
	T variable = head->value;
	return variable;
}
template <typename T>
T MyList<T>::rearValue()
{
	T variable = tail->value;
	return variable;
}
template <typename T>
void MyList<T>::clear()
{
	while(head)
	{
		pop();
	}
	size = 0;
}
template <typename T>
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
template <typename T>
bool MyList<T>::isEmpty()
{
	return head ? false : true;
}
template <typename T>
int MyList<T>::getSize()
{
	return size;
}

template <typename T>
void MyList<T>::bubblesort()
{
	Node* t, *m, *a, *b;
	if(head == NULL)
		return;
	for(bool go = true; go; )
	{
		go = false;

		a = t = head;
		b = head->next;
		while(b != NULL)
		{
			if(a->value < b->value)
			{
				if(t == a)
					head = b;
				else
					t->next = b;

				a->next = b->next;
				b->next = a;

				m  = a, a = b, b = m;
				go = true;
			}
			t = a;
			a = a->next;
			b = b->next;
		}
	}
}

template<class T>
void MyList<T>::union_list(MyList<T>*& list)
{
	if(head == NULL || list->isEmpty())
		return;
	while(list->head)
	{
		T value = list->pop();
		this->push(value);
	}
	delete list;
}

#endif //LAB_1_MYLIST_H
