#include "BTree.h"

void BTree::add(Node *&t, int n)
{
    if(t == NULL) //если узел пустой, создается новый узел
    {
        t = new Node(n);
    }
    else
    {
        if(n < t->num) //если значение меньше корневого, помещается в левого сына
        {
            add(t->left, n);
        }
        else //если больше, то в правого
        {
            add(t->right, n);
        }
    }
}

void BTree::inorder(Node* &t)
{
    if(t != NULL) //если узел не нулевой
    {
        inorder(t->left); //вызов рекурсия для левого сына
        std::cout << t->num << " ";
        inorder(t->right); //вызов рекурсии для правого сына
    }
}

void BTree::del(Node *&Tree)
{
    if(Tree != NULL) //если узел не нулевой
    {
        del(Tree->left); //вызов рекурсии удаления для левого сына
        del(Tree->right); //вызов рекурсии удаления для правого сына
        delete Tree; // удаляется сам корень
    }
}

void BTree::print(Node *Tree, long level)
{
    if(Tree) //если узел не нулевой
    {
        print(Tree->right, level + 5); //вызов для правого потомка
        for(long i = 0; i < level; i++)
            std::cout << " ";
        std::cout << Tree->num << std::endl; //вывод в консоль
        print(Tree->left, level + 5); //вызов для левого потомка
    }
}

void BTree::in(Node *Tree, std::ofstream &in_file, long level)
{
    if (Tree) //если узел не нулевой
    {
        in(Tree->right, in_file, level + 5); //вызов для правого потомка
        for(long i = 0; i < level; i++)
            in_file << " ";
        in_file << Tree->num << std::endl; // запись в файл
        in(Tree->left, in_file, level + 5); //вызов для левого потомка
    }
}

BTree::BTree()
{
    root = NULL;
}

bool BTree::hasNext(Node *&tree)
{
    return tree->left != NULL || tree->right != NULL;
}

void BTree::additem(int n)
{
    add(root, n);
}

void BTree::showtree()
{
    inorder(root);
}
void BTree::deleteTree()
{
    del(root);
}

void BTree::showFullTree()
{
    print(root, 0);
}
void BTree::inTextFile()
{
    std::string string = R"(C:\MyProgram\CLion\CLionProject\lab_2\tree.txt)";
    std::ofstream in_file;
    in_file.open(string);
    if(in_file.is_open())
    {
        in(root, in_file, 0);
    }
    in_file.close();
}