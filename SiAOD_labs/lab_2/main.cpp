#include <iostream>
#include <vector>
#include <fstream>
#include "BTree.h"


void fill(BTree &bt); //загрузка чисел для бинарного дерева из тектового файла

int main()
{
    BTree bt; //экземпляр класса дерева
    fill(bt);
    std::cout << "Tree as a list:" << std::endl;
    bt.showtree();
    std::cout << std::endl;
    std::cout << "Tree in a normal form:" << std::endl;
    bt.showFullTree();

    bt.inTextFile(); //загрузка дерева в граф представление в файл
    bt.deleteTree(); //очистка памяти
    return 1;
}

void fill(BTree &bt)
{
    std::string string = R"(C:\MyProgram\CLion\CLionProject\lab_2\numbers.txt)";
    std::ifstream from_file;
    from_file.open(string);
    if(from_file.is_open())
    {
        int number;
        while(!from_file.eof())
        {
            from_file >> number;
            bt.additem(number);
        }
    }
    from_file.close();
}


