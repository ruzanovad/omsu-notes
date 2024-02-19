//
// Created by light on 19.02.2024.
//

#include <iostream>
//#include "matrix.h"
using namespace std;
void LU(){

}
// (1, -1, 1) => (2, 1, 1) (свободный столбец)
// тестирующая часть
// генерация матрицы
// Ax = f
//x - приближенное решение

int method(double ** A, double ** f, double ** x, int n){

}

int main(){
    int n = 3;
    double ** mat = new double * [n];
    for (int i = 0; i < n; ++i){
        mat[i] = new double[n];

    }
    int j = 0;
    for(auto& x: {1, 2, 1}){
        mat[0][j] = x;
        j++;
    }
    j = 0;
    for(auto& x: {2, 2, 1}){
        mat[1][j] = x;
        j++;
    }
    j = 0;
    for(auto& x: {1, 1, 1}){
        mat[2][j] = x;
        j++;
    }
    for(int i = 0; i < n; ++i){
        for (int k = 0; k < n; ++k)
            cout << mat[i][k] << " ";
        cout << endl;
    }


    double ** x = new double * [n];
    for (int i = 0; i < n; ++i){
        x[i] = new double[1];

    }
    x[0][0] = 1;
    x[1][0] = -1;
    x[2][0] = 1;

    for(int i = 0; i < n; ++i){
        for (int k = 0; k < 1; ++k)
            cout << x[i][k] << " ";
        cout << endl;
    }

    double ** f = new double *[n];
    for (int i = 0; i < n; ++i){
        f[i] = new double[1];

    }

    f[0][0] = 2;
    f[1][0] = 1;
    f[2][0] = 1;

    for(int i = 0; i < n; ++i){
        for (int k = 0; k < 1; ++k)
            cout << f[i][k] << " ";
        cout << endl;
    }

    double ** copy = new double * [n];
    for (int i = 0; i < n; ++i){
        copy[i] = new double[n];

    }
    for (int i = 0; i < n; ++i){
        for (int k = 0; k < n; ++k){
            copy[i][k] = mat[i][k];
        }
    }

    for(int i = 0; i < n; ++i){
        for (int k = 0; k < n; ++k)
            cout << copy[i][k] << " ";
        cout << endl;
    }

    double ** xcopy = new double * [n];
    for (int i = 0; i < n; ++i){
        xcopy[i] = new double[1];
        xcopy[i][0] = x[i][0];
    }

    for(int i = 0; i < n; ++i){
        for (int k = 0; k < 1; ++k)
            cout << xcopy[i][k] << " ";
        cout << endl;
    }

    for (int i = 0; i < n; ++i){
        delete[] mat[i];
    }
    delete[] mat;

    for (int i = 0; i < n; ++i){
        delete[] x[i];
    }
    delete[] x;

    for (int i = 0; i < n; ++i){
        delete[] copy[i];
    }
    delete[] copy;

    for (int i = 0; i < n; ++i){
        delete[] xcopy[i];
    }
    delete[] xcopy;
}