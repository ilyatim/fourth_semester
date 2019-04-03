
#ifndef LAB_2_BTREE_H
#define LAB_2_BTREE_H

#include <iostream>
#include <fstream>
class BTree
{
private:
    struct Node //реализация структуры для построения дерева
    {
        Node *left; //правый потомок бинарного дерева
        Node *right; //левый потомок бинарного дерева
        int num;

        explicit Node(int n = 0, Node* l = 0, Node* r = 0):num(n), left(l), right(r){}
    };
    Node *root; //корневой элемент бинарного дерева
    void add(Node *&t, int n); //рекурсивная функция добавления элемента в выбранное поддерево
    void inorder(Node* &t); //рекурсивная функция вывода значения дерева от меньшего с выбранного узла
    void del(Node *&Tree); //удаления дерева начиная с выбранного узла
    void print(Node *Tree, long level); //рекурсивная функция вывода древообразного представления дерева с некоторого узла
    void in(Node *Tree, std::ofstream &in_file, long level); //рекурсивая функция записи дерева в файл
public:
    BTree(); //конктруктор класса

    bool hasNext(Node *&tree); //проверка на наличие следующего элемента
    void additem(int n); //добавления значения в дерево
    void showtree(); //вывод элементов дерева в виде списка
    void deleteTree(); //удаление дерева
    void showFullTree(); //вывод элементов дерева в древообразном виде
    void inTextFile(); //запись дерева в файл
};

#endif //LAB_2_BTREE_H
