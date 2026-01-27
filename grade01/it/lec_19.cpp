#include <iostream>
#include <fstream>
using namespace std;
//const NMax = 1000;

struct Data
{
	int key;
	char data;
};

void InsertionSort(Data*, int);
void SelectionSort(Data*, int);
void BubbleSort(Data*, int);

// простые сортировки
int main()
{
	ifstream fin;
	fin.open("in.txt");

	int n;
	fin >> n;

	Data* arr = new Data[n];

	for (int i = 0; i < n; i++) fin >> arr[i].data >> arr[i].key;

//    InsertionSort(arr, n);
    SelectionSort(arr, n);
//	BubbleSort(arr, n);

	for (int i = 0; i < n; i++) cout << arr[i].data << " " << arr[i].key << endl;

	fin.close();
	delete[] arr;

	return 0;
}

void InsertionSort(Data* arr, int n) // устойчивая
{
	Data current;
	int j;

	for (int i = 1; i < n; i++)
	{
		current = arr[i];
		j = i - 1;
		while (current.key < arr[j].key && j >= 0)
		{
			arr[j + 1] = arr[j];
			j--;
		}
		arr[j + 1] = current;

	}
}

void SelectionSort(Data* arr, int n) // неустойчивая сортировка
{
	Data curr_min;
	int ind_min;

	for (int i = 0; i < n - 1; i++)
	{
		curr_min = arr[i];
		ind_min = i;
		for (int j = i + 1; j < n; j++)
		{
			if (arr[j].key < curr_min.key)
			{
				curr_min = arr[j];
				ind_min = j;
			}
		}
		arr[ind_min] = arr[i];
		arr[i] = curr_min;
	}
}

void BubbleSort(Data* arr, int n) // устойчивая сортировка
{
	Data tmp;
	for (int i = 0; i < n; i++)
	{
		for (int j = n - 1; j > i; j--)
		{
			if (arr[j - 1].key > arr[j].key)
			{
				tmp = arr[j - 1];
				arr[j - 1] = arr[j];
				arr[j] = tmp;
			}
		}
	}
}
