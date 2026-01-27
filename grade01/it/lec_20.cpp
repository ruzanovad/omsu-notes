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
void InsertionSortBinary(Data*, int);
void SelectionSort(Data*, int);
void BubbleSort(Data*, int);
void ShellSort(Data*, int, int[], int);
void QuickSort(Data*, int, int);

// простые сортировки
int main()
{
	ifstream fin;
	fin.open("in.txt");

	int n;
	fin >> n;

	Data* arr = new Data[n];

	for (int i = 0; i < n; i++) fin >> arr[i].data >> arr[i].key;


	int h[] = { 5, 3, 1 };

//	InsertionSort(arr, n);
//  InsertionSortBinary(arr, n);
//  SelectionSort(arr, n);
//	BubbleSort(arr, n);
	ShellSort(arr, n, h, 3);
//	QuickSort(arr, 0, n - 1);

	for (int i = 0; i < n; i++) cout << arr[i].data << " " << arr[i].key << endl;

	fin.close();
	delete[] arr;

	return 0;
}


// устойчивая
void InsertionSort(Data* arr, int n)
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

//не устойчивая(
void ShellSort(Data* arr, int n, int h[], int m) // усовершенствованная сортировка включениями
{
	Data current;
	int j, k;

	for (int t = 0; t < m; t++)
	{
		k = h[t];
		for (int i = k; i < n; i++)
		{
			current = arr[i];
			j = i - k;
			while (current.key < arr[j].key && j >= 0)
			{
				arr[j + k] = arr[j];
				j -= k;
			}
			arr[j + k] = current;
		}
	}
}

// неустойчивая сортировка
void SelectionSort(Data* arr, int n)
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

// устойчивая сортировка
void BubbleSort(Data* arr, int n)
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

void InsertionSortBinary(Data* arr, int n)
{
	Data current;
	int left, right, mid;
	for (int i = 1; i < n; i++)
	{
		current = arr[i];
		left = 0;
		right = i;
		while (left < right)
		{
			mid = (left + right) / 2;
			if (arr[mid].key <= current.key)
				left = mid + 1;
			else
				right = mid;
		}
		for (int j = i; j > left; j--)
			arr[j] = arr[j - 1];
		arr[left] = current;
	}
}

void QuickSort(Data* arr, int l, int r)
{
	Data pivot, tmp;
	int i, j;
	i = l; j = r;
	pivot = arr[(l + r) / 2];
	while (i < j)
	{
		while (arr[i].key < pivot.key) i++;
		while (arr[j].key > pivot.key) j--;
		if (i <= j)
		{
			tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
			i++; j--;
		}
	}
	if (l < j) QuickSort(arr, l, j);
	if (i < r) QuickSort(arr, i, r);
}
